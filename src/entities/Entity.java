package entities;

import utils.Randomic;
import utils.Text;
import entities.player.Player;
import exceptions.EmptyStringException;
import exceptions.MaxStringSizeException;
import exceptions.NumberOverflowException;
import scene.TextureId;
import scene.bars.Bar;
import scene.statbox.Statbox;
import utils.Number;

/**
 * Classe base para Player e Boss.
 */
public abstract class Entity {
    /** Número (int) máximo de carácteres para o nome */
    public static final int MAX_NAME_SIZE = 32;
    /** Definição de dano igual a zero como ataque perdido */
    public static final int ATTACK_MISSED = 0;
    /** Valor (double) máximo do crítico (base) */
    public static final double MAX_BASE_CRIT = 1.0;
    /** Valor (double) máximo da precisão (base) */
    public static final double MAX_BASE_ACCUR = 1.0;
    /** Valor (double) máximo do multiplicador de crítico (base) */
    public static final double MAX_CRIT_MULTIPLIER = 10.0;
    /** Porcentagem em dicimal (double) do multiplicador de defesa quando se defende */
    public static final double DEFEND_MULTIPLIER = 0.75;
    /**?  */
    public static final double MIN_DAMAGE_POST_REDUCTION = 0.2;

    protected final int MAX_DEFEND_DURATION = 2;
    private static int countEntities = 0;
    private static final double HEAL_DPercentage = Number.dPercentage(5);
    private static final double RCVR_DPercentage = Number.dPercentage(10);
    private static final double SATTACK_MP_REDUCTION = Number.dPercentage(50);
    private static final double DAMAGE_MULTIPLIER_SATTACK = Number.dPercentage(150);
    private static final double MINIMUM_DAMAGE_DPERCENTAGE = Number.dPercentage(10);

    // base statistics
    private int maxHP;
    private int maxMP;
    private int baseDamage;
    private int baseDefense;
    private int baseRecRateHP;
    private int baseRecRateMP;
    private double baseCritChance;
    private double baseCritMultiplier;
    private double baseAccuracy;
    private double baseDefenseMultiplier;

    // current statistics
    private int currHP;
    private int currMP; 
    private int currDamage;
    private int currDefense;
    private int currRecRateHP;
    private int currRecRateMP;
    private double currCritChance;
    private double currCritMultiplier;
    private double currAccuracy;
    private double currDefenseMultiplier;

    private String name;
    private boolean isDead;
    private boolean isDefending;
    private int defendDuration;

    private TextureId textureId;
    private Bar healthBar, manaBar;
    private Statbox statbox;

    /**
     * Construtor da entidade base (Player e Boss)
     * @param name (String) o nome da entidade 
     */
    public Entity(String name) {
        resetToZero();
        this.name = name;
        ++countEntities;
    }

    /**
     * Cura a entidade (Player ou Boss) dado o valor de cura
     * @param amount a quantidade para curar o HP
     * @return (int) o valor curado
     */
    protected int healHP(int amount) {
        if(amount >= 0) {
            final int _oldHP = currHP;
            final int _hpWithHeal = currHP + amount;

            currHP = (_hpWithHeal > maxHP) ? maxHP : _hpWithHeal;

            return (currHP - _oldHP);
        }
        return 0;
    }

    /**
     * Recupera o MP da entidade (Player ou Boss) dado o valor de recuperação
     * @param amount a quantidade para recuperar o MP
     * @return (int) o valor recuperado
     */
    protected int recoverMP(int amount) {
        if(amount >= 0) {
            final int _oldMP = currMP;
            final int _mpWithRecover = currMP + amount;

            currMP = (_mpWithRecover > maxMP) ? maxMP : _mpWithRecover;

            return (currHP - _oldMP);
        }
        return 0;
    }

    /**
     * Calcula a defesa pura do jogador com multiplicador (caso aplicável)
     * @return (int) o valor da defesa pura
     */
    private int computeDefense() {
        int _multipliedDefense = 0;

        if(defendDuration > 0) 
            _multipliedDefense = (int)((double)currDefense * currDefenseMultiplier);

        return (_multipliedDefense < currDefense) ? currDefense : _multipliedDefense;
    }

    /**
     * recebe dano, não faz nada além disso
     * @param damage o valor (int) do dano
     */
    private void takeDamage(int damage) {
        final int _hpWithLoss = currHP - damage;

        if(_hpWithLoss <= 0) { 
            currHP = 0;
            isDead = true;
        }
        currHP = _hpWithLoss;
    }

    /**
     * Defende contra um dano, podendo reduzí-lo a valores mínimos ou zero
     * @param damage o dano a ser causado na entidade (Player ou Boss)
     * @return (int) o dano recebido ou ATTACK_MISSED;
     * @see #ATTACK_MISSED
     */
    public int defend(int damage) {
        final int _defenseValue = computeDefense() - damage;
        int _damageTaken = -_defenseValue;

        if(_damageTaken <= 0) {
            _damageTaken = (int)(damage * MINIMUM_DAMAGE_DPERCENTAGE);
        }

        takeDamage(_damageTaken);

        return _damageTaken;
    }

    /**
     * Calcula o valor de dano puro da entidade (Player ou Boss) com chance de crítico
     * @return (int) o valor de dano puro
     */
    private int calcDamage() {
        final boolean _hasCrit = currCritChance >= Randomic.between(0.0, MAX_BASE_CRIT);
        int _damage = currDamage;

        if(_hasCrit) { 
            _damage = (int)(currDamage * currCritMultiplier);
        }
        return _damage;
    }

    /**
     * Realiza o ataque na entidade (Player ou Boss)
     * @param enemy a entidade a ser atacada
     * @return (int) o dano causado na entidade ou ATTACK_MISSED
     * @see #ATTACK_MISSED
     */
    public int attack(Entity enemy) {
        final boolean _haveHit = 
            baseAccuracy >= Randomic.between(0.0, MAX_BASE_ACCUR);

        if(!_haveHit) return ATTACK_MISSED;

        final int _damage = calcDamage();
        final int _damageDone = enemy.defend(_damage);
        return _damageDone;
    }

    /**
     * Verifica se a entidade (Player ou Boss) pode realizar ataque especial
     * @return (boolean) se pode realizar ataque especial
     */
    public boolean canSuper() {
        return getCurrMP() >= (int)(getMaxMP() * SATTACK_MP_REDUCTION);
    }

    /**
     * Realiza o ataque especial na entidade (Player ou Boss)
     * <p>
     * O dano não pose der desviado ou perdido
     * @param enemy a entidade a ser atacada com o especial
     * @return (int) o dano causado na entidade
     * @throws NumberOverflowException 
     * @see exceptions.NumberOverflowException
     */
    public int attackSuper(Entity enemy) throws NumberOverflowException {
        final int _mpReduction = (int)(getMaxMP() * SATTACK_MP_REDUCTION);
        setCurrMP(getCurrMP() - _mpReduction);

        final int _damage = (int)(calcDamage() * DAMAGE_MULTIPLIER_SATTACK);
        final int _damageDone = enemy.defend(_damage);
        return _damageDone;
    }

    /**
     * Se a entidade (player ou Boss) já não estiverem defendendo
     * ativa o modo de defesa que durará MAX_DEFEND_DURATION
     * @see entities.Entity#MAX_DEFEND_DURATION
     * @see entities.Entity#DEFEND_MULTIPLIER
     */
    public void setDefend() {
        if(isDefending == false) {
            currDefenseMultiplier += DEFEND_MULTIPLIER;
            isDefending = true;
            defendDuration = MAX_DEFEND_DURATION;
        }
    }

    /**
     * Executa atualização de estatísticas e dados da entidade 
     * (Player ou Boss) a cada turno
     */
    public void updateEndTurn() {
        if(defendDuration > 0) {
            --defendDuration;
        } 
        else {
            if(isDefending == true) {
                isDefending = false;
                currDefenseMultiplier -= DEFEND_MULTIPLIER;
            }
        }

        healHP( (int)(maxHP * HEAL_DPercentage) );
        recoverMP( (int)(maxMP * RCVR_DPercentage) );
    }

    // --------------------------- GETTERS --------------------------- //
    public String getName() { return name; }
    public boolean getIsDead() { return isDead; }

    public int getDefendDuration() { return defendDuration; }
    public int getMaxHP() { return maxHP; }
    public int getMaxMP() { return maxMP; }
    public int getCurrHP() { return currHP; }
    public int getCurrMP() { return currMP; }
    public int getBaseDefense() { return baseDefense; }
    public int getBaseDamage() { return baseDamage; }
    public int getCurrDamage() { return currDamage; }
    public int getCurrDefense() { return currDefense; }
    public int getCurrRecRateHP() { return currRecRateHP; }
    public int getBaseRecRateHP() { return baseRecRateHP; }
    public int getCurrRecRateMP() { return currRecRateMP; }
    public int getBaseRecRateMP() { return baseRecRateMP; }

    public double getBaseCritChance() { return baseCritChance; }
    public double getCurrCritChance() { return currCritChance; }
    public double getBaseAccuracy() { return baseAccuracy; }
    public double getCurrAccuracy() { return currAccuracy; }
    public double getBaseCritMultiplier() { return baseCritMultiplier; }
    public double getCurrCritMultiplier() { return currCritMultiplier; }
    public double getBaseDefenseMultiplier() { return baseDefenseMultiplier; }
    public double getCurrDefenseMultiplier() { return currDefenseMultiplier; }

    public TextureId getTextureId() { return textureId; }
    public Bar getHealthBar() { return healthBar; }
    public Bar getManaBar() { return manaBar; }
    public Statbox getStatbox() { return statbox; }
    

    // --------------------------- SETTERS --------------------------- //
    protected void setName(String name) throws EmptyStringException, MaxStringSizeException { 
        if(name.length() > MAX_NAME_SIZE) 
            throw new MaxStringSizeException("Max string size is " + MAX_NAME_SIZE);
        else if(Text.stringIsEmpty(name))
            throw new EmptyStringException("Name cannot be null");

        this.name = name;
    }

    protected void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    protected void setDefendDuration(int defendDuration) {
        this.defendDuration = defendDuration;
    }

    protected void setMaxHP(int maxHP) { 
        this.maxHP = maxHP; 
    }

    protected void setMaxMP(int maxMP) { 
        this.maxMP = maxMP; 
    }

    protected void setCurrHP(int hp) throws NumberOverflowException {
        if(currHP < 0 || currHP > maxHP)
            throw new NumberOverflowException(
                getMessageNumberOverflow("HP", maxHP)
            );

        currHP = hp;
    }

    protected void setCurrMP(int mp) throws NumberOverflowException {
        if(currMP < 0 || currMP > maxMP)
            throw new NumberOverflowException(
                getMessageNumberOverflow("MP", maxMP)
            );

        this.currMP = mp;
    }

    protected void setBaseDefense(int baseDefense) { 
        this.baseDefense = baseDefense; 
    }

    protected void setBaseDamage(int baseDamage) { 
        this.baseDamage = baseDamage; 
    }

    public void setCurrDamage(int currDamage) {
        this.currDamage = currDamage;
    }

    public void setCurrDefense(int currDefense) {
        this.currDefense = currDefense;
    }

    protected void setBaseCritChance(double baseCritChance) {
        if(baseCritChance > MAX_BASE_CRIT) this.baseCritChance = MAX_BASE_CRIT;
        else if(baseCritChance < 0.0) this.baseCritChance = 0.0;
        else this.baseCritChance = baseCritChance;
    }

    public void setCurrCritChance(double currCritChance) {
        this.currCritChance = currCritChance;
    }

    protected void setBaseAccuracy(double baseAccuracy) {
        if(baseAccuracy > MAX_BASE_ACCUR) this.baseAccuracy = MAX_BASE_ACCUR;
        else if(baseAccuracy < 0.0) this.baseAccuracy = 0.0;
        else this.baseAccuracy = baseAccuracy;
    }
    
    public void setCurrAccuracy(double currAccuracy) {
        this.currAccuracy = currAccuracy;
    }

    protected void setCurrRecRateHP(int recRate) {
        this.currRecRateHP = recRate;
    }

    public void setBaseRecRateHP(int baseRecRateHP) {
        this.baseRecRateHP = baseRecRateHP;
    }

    protected void setCurrRecRateMP(int recRate) {
        this.currRecRateMP = recRate;
    }

    public void setBaseRecRateMP(int baseRecRateMP) {
        this.baseRecRateMP = baseRecRateMP;
    }

    protected void setBaseCritMultiplier(double critValue) {
        if(critValue > MAX_CRIT_MULTIPLIER) this.baseCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(critValue < 0.0) this.baseCritMultiplier = 0.0;
        else this.baseCritMultiplier = critValue;
    }

    protected void setCurrCritMultiplier(double critValue) {
        if(critValue > MAX_CRIT_MULTIPLIER) this.currCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(critValue < 0.0) this.currCritMultiplier = 0.0;
        else this.currCritMultiplier = critValue;
    }

    public void setBaseDefenseMultiplier(double baseDefenseMultiplier) {
        this.baseDefenseMultiplier = baseDefenseMultiplier;
    }

    public void setCurrDefenseMultiplier(double currDefenseMultiplier) {
        this.currDefenseMultiplier = currDefenseMultiplier;
    }

    public void setTextureId(TextureId textureId) {
        this.textureId = textureId;
    }

    public void setHealthBar(Bar healthBar) {
        this.healthBar = healthBar;
    }

    public void setManaBar(Bar manaBar) {
        this.manaBar = manaBar;
    }

    public void setStatbox(Statbox statbox) {
        this.statbox = statbox;
    }

    // --------------------------- STATIC RELATED --------------------------- //
    public static int getCountEntities() { return countEntities; }


    // --------------------------- PRIVATE --------------------------- //
    private static String getMessageNumberOverflow(String attribute, int maxvalue) {
        return attribute + " cannot be greater then " + maxvalue + " or lower then 0";
    }

    /** Reseta todos os booleanos para seus valores padrões */
    protected void resetBooleans() {
        isDead = false;
        isDefending = false;
    }

    /** Zera todas as estatísticas */
    protected void resetToZero() {
        maxHP = maxMP = 0;
        currHP = currMP = 0;
        baseDamage = baseDefense = 0;
        currDefense = currDamage = 0;
        currRecRateHP = currRecRateMP = 0;
        baseRecRateHP = baseRecRateMP = 0;
        baseAccuracy = baseCritMultiplier = baseCritChance = 0.0;
        currAccuracy = currCritMultiplier = currCritChance = 0.0;
        currDefenseMultiplier = baseDefenseMultiplier = 0.0;
        defendDuration = 0;
        resetBooleans();
    }

    /** Reseta todas as estatísticas para seus valores base */
    protected void resetToBase() {
        currHP = maxHP;
        currMP = maxMP;
        currDamage = baseDamage;
        currDefense = baseDefense;
        currCritChance = baseCritChance;
        currCritMultiplier = baseCritMultiplier;
        currAccuracy = baseAccuracy;
        currRecRateHP = baseRecRateHP;
        currRecRateMP = baseRecRateMP;
        currDefenseMultiplier = baseDefenseMultiplier;
        resetBooleans();
    }
}