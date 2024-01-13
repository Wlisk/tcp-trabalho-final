package entities.boss;

import entities.player.Player;
import entities.Entity;
import exceptions.NumberOverflowException;
import utils.Number;
import utils.Randomic;
import scene.TextureId;
import scene.bars.Bars;

/** Define propriedades e métodos para o chefão (Boss) do jogo.  */
public final class Boss extends Entity {
    public static final int DEFENDED = -1;
    // constants to change the boss BERSERK state statistics
    private static final double 
        BERSERK_DAMAGE_MULTIPLIER  = 1.5, 
        BERSERK_CRITCHANCE_ADD = 0.2,  
        BERSERK_CRITMULT_ADD = 0.2,
        BERSERK_ACCURACY_ADD = -0.1; 
    // constants to change the boss WEAK state statistics
    private static final double 
        WEAK_DAMAGE_MULTIPLIER = 0.5, 
        WEAK_CRITCHANCE_ADD = -0.1, 
        WEAK_CRITMULT_ADD = -0.2;

    private final int expReward;
    private StateType currState;
    private double berserkThreshold;
    private double berserkChance;
    private double baseSpecialChance;
    private double baseDefendChance;
    private final String description;
    private int stateDuration;


    public Boss(
        String name, 
        TextureId textureId,
        int expReward,
        int maxHP, 
        int maxMP,
        int baseDamage,
        int baseDef,
        double baseCritChance,
        double baseCritMult,
        double baseAccuracy,
        double baseDefenseMult,
        double berserkChance,
        double berserkThreshold,
        double baseSpecialChance,
        double baseDefendChance,
        String description
    )
    throws NumberOverflowException
    {
        super(name);

        Number.limitTo(Number.MIN_DPERCENTAGE, Number.MAX_DPERCENTAGE, berserkThreshold);
        currState = StateType.BASE;
        this.setTextureId(textureId);
        this.expReward = expReward;

        setMaxHP(maxHP);
        setCurrHP(maxHP);
        setMaxMP(maxMP);
        setCurrMP(maxMP);
        setBaseDamage(baseDamage);
        setCurrDamage(baseDamage);
        setBaseDefense(baseDef);
        setCurrDefense(baseDef);
        setBaseCritChance(baseCritChance);
        setCurrCritChance(baseCritChance);
        setBaseCritMultiplier(baseCritMult);
        setCurrCritMultiplier(baseCritMult);
        setBaseAccuracy(baseAccuracy);
        setCurrAccuracy(baseAccuracy);
        setBaseDefenseMultiplier(baseDefenseMult);
        setCurrDefenseMultiplier(baseDefenseMult);
        
        this.berserkChance = berserkChance;
        this.berserkThreshold = berserkThreshold;
        this.baseSpecialChance = baseSpecialChance;
        this.baseDefendChance = baseDefendChance;
        this.description = description;

        setHealthBar(Bars.newBossHealthBar(this));
        setManaBar(Bars.newBossManaBar(this));
    }
    
    @Override
    public void updateEndTurn(){
        super.updateEndTurn();
        updateState();
    }
    
    private void setBase(){
        currState = StateType.BASE;
        stateDuration = 0;
        this.setCurrDamage(this.getBaseDamage());
        this.setCurrCritChance(this.getBaseCritChance());
        this.setCurrCritMultiplier(this.getBaseCritMultiplier());
        this.setCurrAccuracy(this.getBaseAccuracy());
    }

    private void setBerserk(){ // When berserk, boss does extra damage and has higher crit chance, but lower accuracy
        currState = StateType.BERSERK;
        stateDuration = 2;
        this.setCurrDamage((int) (this.getBaseDamage() * BERSERK_DAMAGE_MULTIPLIER));
        this.setCurrCritChance(this.getBaseCritChance() + BERSERK_CRITCHANCE_ADD);
        this.setCurrCritMultiplier(this.getBaseCritMultiplier() + BERSERK_CRITMULT_ADD);
        this.setCurrAccuracy(this.getBaseAccuracy() + BERSERK_ACCURACY_ADD);
    }

    private void setWeak(){ // When weak, boss does less damage and has less accuracy
        currState = StateType.WEAK;
        stateDuration = 2;
        this.setCurrDamage((int) (this.getBaseDamage() * WEAK_DAMAGE_MULTIPLIER));
        this.setCurrCritChance(this.getBaseCritChance() + WEAK_CRITCHANCE_ADD);
        this.setCurrCritMultiplier(this.getBaseCritMultiplier() + WEAK_CRITMULT_ADD);
        this.setCurrAccuracy(this.getBaseAccuracy());
    }
    
    public StateType updateState() {
        switch (stateDuration){
            case 0: // If state duration is 0, current state is BASE.
                if (reachedBerserkThreshold()){
                    double _rand = Randomic.between(0.0, 1.0);
                    if (_rand < this.berserkChance){
                        setBerserk();
                    }
                }
                break;

            case 1: // If state duration is 1, current state is WEAK or BERSERK, therefore transition states
                if (currState == StateType.BERSERK){ // Boss goes to WEAK after BERSERK
                    setWeak();
                } else { // Boss goes to BASE after WEAK
                    setBase();
                }   
                break; 

            default:
                stateDuration--;
        }
        return currState;
    }
    
    public int chooseAction(Player player) throws NumberOverflowException {
        double _rand = Randomic.between(0.0, 1.0);

        switch (currState){
            case BASE: // In BASE state, Boss chooses between attack, special and defending
                if (_rand < baseSpecialChance){
                    if (canSuper()) return attackSuper(player); 
                    else return attack(player);
                } else if (_rand < baseSpecialChance + baseDefendChance){
                    return defend();
                } else {
                    return attack(player);
                }
            case BERSERK: // When berserk, boss only attacks or defends, always using a super when possible
                if (canSuper()) return attackSuper(player); 
                else return attack(player);
            default: // When weak, Boss only attacks or defends
                if (_rand < 0.5){
                    return attack(player);
                } else {
                    return defend();
                }
        }
    }

    protected int calcDamage(Player player) {
        boolean _hasCrit = getCurrCritChance() >= Randomic.between(0.0, MAX_BASE_CRIT);

        int _damage = 0;

        if (_hasCrit) { 
            _damage = (int)((double) getCurrDamage() * getCurrCritMultiplier());
        } else {
            _damage = getCurrDamage(); 
        }

        int dmgReduction = (int) ((double) player.getTotalDefense() * player.getCurrDefenseMultiplier());
        if (_damage - dmgReduction < _damage * 0.2){ // Defence damage reduction caps at 80%
            return (int) (_damage * 0.2);
        } else {
            return _damage - dmgReduction;
        }
    }
    
    public int attack(Player player) {
        boolean _haveHit = getCurrAccuracy() >= Randomic.between(0.0, MAX_BASE_ACCUR);

        if(!_haveHit) return ATTACK_MISSED;

        int _damage = calcDamage(player);
        player.takeDamage(_damage);

        return _damage;
    }
    
    public int attackSuper(Player player) throws NumberOverflowException {
        int _damage = calcDamage(player);
        setCurrMP(getCurrMP() / 2);

        player.takeDamage((int)(_damage * 1.5));
        return _damage;
    }
    

    public StateType getCurrState() { return currState; }
    public double getPercentageBerserk() { return berserkThreshold; }
    public String getDescription() { return description; }
    public int getExpReward() { return this.expReward; }

    /**
     * Checa se o Boss atingiu o ponto de estado BERSERK (o ponto de troca para o estado)
     * @return (boolean) se o Boss pode atingir o estado ou não
     */
    private boolean reachedBerserkThreshold() {
        double healthPercent = (double)this.getCurrHP() / (double)this.getMaxHP();
        return healthPercent < berserkThreshold;
    }
}
