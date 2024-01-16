package entities;

import utils.Randomic;
import utils.Text;
import exceptions.EmptyStringException;
import exceptions.MaxStringSizeException;
import scene.TextureId;
import scene.bars.Bar;
import scene.statbox.Statbox;
import utils.Number;

/**
 * Classe base para Player e Boss.
 * @see entities.player.Player
 * @see entities.boss.Boss
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
    /** Percentual de HP que entidades regeneram por turno */
    private static final double HEAL_DPercentage = Number.dPercentage(5);
    /** Percentual de MP que entidades regeneram por turno */
    private static final double RCVR_DPercentage = Number.dPercentage(10);
    private static final double SATTACK_MP_REDUCTION = Number.dPercentage(50);
    private static final double DAMAGE_MULTIPLIER_SATTACK = Number.dPercentage(150);
    private static final double MINIMUM_DAMAGE_DPERCENTAGE = Number.dPercentage(10);

    // base statistics
    private int maxHP;
    private int maxMP;
    private int baseDamage;
    private int baseDefense;
    private double baseRecRateHP;
    private double baseRecRateMP;
    private double baseCritChance;
    private double baseCritMultiplier;
    private double baseAccuracy;
    private double baseDefenseMultiplier;

    // current statistics
    private int currHP;
    private int currMP; 
    private int currDamage;
    private int currDefense;
    private double currRecRateHP;
    private double currRecRateMP;
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
     * @param name o nome da entidade 
     */
    public Entity(String name) {
        resetToZero();
        this.name = name;
        baseRecRateHP = HEAL_DPercentage;
        currRecRateHP = HEAL_DPercentage;
        baseRecRateMP = RCVR_DPercentage;
        currRecRateMP = RCVR_DPercentage;
        ++countEntities;
    }

    /**
     * Recupera o HP da entidade (Player ou Boss) por um valor passado como argumento
     * @return (int) o nobo valor de mp da entidade
     */
    protected int healHP(int _amount) {
        if(_amount > 0) {
            final int _hpWithHeal = currHP + _amount;
            currHP = (_hpWithHeal > maxHP) ? maxHP : _hpWithHeal;
        }

        return currHP;
    }


    /**
     * Recupera o MP da entidade (Player ou Boss) por um valor passado como argumento
     * @return (int) o nobo valor de mp da entidade
     */
    protected int recoverMP(int _amount) {
        if(_amount > 0) {
            final int _mpWithRecover = currMP + _amount;
            currMP = (_mpWithRecover > maxMP) ? maxMP : _mpWithRecover;
        }
        
        return currMP;
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
     * O dano não pode ser desviado ou perdido
     * @param enemy a entidade a ser atacada com o especial
     * @return (int) o dano causado na entidade
     */
    public int attackSuper(Entity enemy) {
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

        int _healHP = (int) (getCurrRecRateHP() * getMaxHP());
        int _healMP = (int) (getCurrRecRateMP() * getMaxMP());

        healHP(_healHP);
        recoverMP(_healMP);
    }

    // --------------------------- OTHERS --------------------------- //
    /**
     * Retorna a quantidade de entidades instânciadas até o momento
     * @return (int) a quantidade de entidades
     */
    public static int getCountEntities() { return countEntities; }

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
    public void resetToBase() {
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

    // --------------------------- GETTERS --------------------------- //
    /**
     * Retorna o nome da entidade
     * @return (String) o nome
     */
    public String getName() { return name; }

    /**
     * Retorna se a entidade está morta ou não
     * @return (boolean) se entidade morta
     */
    public boolean getIsDead() { return isDead; }

    /**
     * Retorna a duração de defesa restante
     * @return (int) duração restante
     */
    public int getDefendDuration() { return defendDuration; }

    /**
     * Retorna o HP máximo da entidade
     * @return (int) o HP máximo
     */
    public int getMaxHP() { return maxHP; }

    /**
     * Retorna o MP máximo da entidade
     * @return (int) o MP máximo
     */
    public int getMaxMP() { return maxMP; }

    /**
     * Retorna o HP corrente/atual da entidade
     * @return (int) o HP atual
     */
    public int getCurrHP() { return currHP; }

    /**
     * Retorna o MP corrente/atual da entidade
     * @return (int) o MP atual
     */
    public int getCurrMP() { return currMP; }

    /**
     * Retorna a defesa base da entidade
     * @return (int) a defesa base
     */
    public int getBaseDefense() { return baseDefense; }

    /**
     * Retorna o dano base da entidade
     * @return (int) o dano base
     */
    public int getBaseDamage() { return baseDamage; }

    /**
     * Retorna o dano corrente/atual da entidade
     * @return (int) o dano atual
     */
    public int getCurrDamage() { return currDamage; }

    /**
     * Retorna a defesa corrente/atual da entidade
     * @return (int) a defesa atual
     */
    public int getCurrDefense() { return currDefense; }

    /**
     * Retorna a taxa corrente/atual de recuperação de HP da entidade
     * @return (double) a recuperação de HP atual
     */
    public double getCurrRecRateHP() { return currRecRateHP; }

    /**
     * Retorna a taxa base de recuperação de HP da entidade
     * @return (double) a recuperação de HP base
     */
    public double getBaseRecRateHP() { return baseRecRateHP; }

    /**
     * Retorna a taxa corrente/atual de recuperação de MP da entidade
     * @return (double) a recuperação de MP atual
     */
    public double getCurrRecRateMP() { return currRecRateMP; }

    /**
     * Retorna a taxa base de recuperação de MP da entidade
     * @return (double) a recuperação de MP base
     */
    public double getBaseRecRateMP() { return baseRecRateMP; }

    /**
     * Retorna a chance de crítico base da entidade
     * @return (double) a chance de crítico base
     */
    public double getBaseCritChance() { return baseCritChance; }

    /**
     * Retorna a chance de crítico corrente/atual da entidade
     * @return (double) a chance de crítico atual
     */
    public double getCurrCritChance() { return currCritChance; }

    /**
     * Retorna a precisão base da entidade
     * @return (double) a precisão base
     */
    public double getBaseAccuracy() { return baseAccuracy; }

    /**
     * Retorna a precisão corrente/atual da entidade
     * @return (double) a precisão atual
     */
    public double getCurrAccuracy() { return currAccuracy; }

    /**
     * Retorna o multiplicador de crítico base da entidade
     * @return (double) o multiplicador de crítico base
     */
    public double getBaseCritMultiplier() { return baseCritMultiplier; }

    /**
     * Retorna o multiplicador de crítico corrente/atual da entidade
     * @return (double) o multiplicador de crítico atual
     */
    public double getCurrCritMultiplier() { return currCritMultiplier; }

    /**
     * Retorna o mulitiplicador de defesa base da entidade
     * @return (double) o mulitiplicador de defesa base
     */
    public double getBaseDefenseMultiplier() { return baseDefenseMultiplier; }

    /**
     * Retorna o mulitiplicador de defesa corrente/atual da entidade
     * @return (double) o mulitiplicador de defesa atual
     */
    public double getCurrDefenseMultiplier() { return currDefenseMultiplier; }

    /**
     * Retorna o enum ID de textura da entidade
     * @return (TextureId) o enum ID de textura
     * @see scene.TextureId
     */
    public TextureId getTextureId() { return textureId; }

    /**
     * Retorna o gerenciador de desenho da barra de HP da entidade
     * @return (Bar) a barra de HP
     * @see scene.bars.Bar
     */
    public Bar getHealthBar() { return healthBar; }

    /**
     * Retorna o gerenciador de desenho da barra de MP da entidade
     * @return (Bar) a barra de MP
     * @see scene.bars.Bar
     */
    public Bar getManaBar() { return manaBar; }

    /**
     * Retorna o gerenciador de desenho das estatísticas da entidade
     * @return (Statbox) a caixa de estatísticas
     * @see scene.statbox.Statbox
     */
    public Statbox getStatbox() { return statbox; }
    

    // --------------------------- SETTERS --------------------------- //
    /**
     * Seta o nome da entidade (Player ou Boss)
     * @param name o nome a ser setado
     * @throws EmptyStringException erro em caso string vazia ou nula
     * @throws MaxStringSizeException erro em caso ultrapassou máximo número de caracteres
     * @see exceptions.EmptyStringException
     * @see exceptions.MaxStringSizeException
     */
    public void setName(String name) throws EmptyStringException, MaxStringSizeException { 
        if(name.length() > MAX_NAME_SIZE) 
            throw new MaxStringSizeException("Max string size is " + MAX_NAME_SIZE);
        else if(Text.stringIsEmpty(name))
            throw new EmptyStringException("Name cannot be null");
        this.name = name;
    }

    /**
     * Seta o HP máximo da entidade
     * @param maxHP o HP máximo a ser setado
     */
    protected void setMaxHP(int maxHP) { 
        this.maxHP = maxHP; 
    }

    /**
     * Seta o MP máximo da entidade
     * @param maxMP o MP máximo a ser setado
     */
    protected void setMaxMP(int maxMP) { 
        this.maxMP = maxMP; 
    }

    /**
     * Seta o HP atual da entidade, limita-se ao valores mínimos e máximos de HP
     * @param hp o HP atual a ser setado
     */
    protected void setCurrHP(int hp) {
        if(hp < 0) currHP = 0;
        else if(hp > maxHP) currHP = maxHP;
        else currHP = hp;
    }

    /**
     * Seta o MP atual da entidade, limita-se ao valores mínimos e máximos de MP
     * @param mp o MP atual a ser setado
     */
    protected void setCurrMP(int mp) {
        if(mp < 0) currMP = 0;
        else if(mp > maxHP) currMP = maxMP;
        else currMP = mp;
    }

    /**
     * Seta a defesa base da entidade 
     * @param baseDefense a defesa base a ser setada
     */
    protected void setBaseDefense(int baseDefense) { 
        this.baseDefense = baseDefense; 
    }

    /**
     * Seta o dano base da entidade
     * @param baseDamage o dano base a ser setado
     */
    protected void setBaseDamage(int baseDamage) { 
        this.baseDamage = baseDamage; 
    }

    /**
     * Seta o dano corrente/atual da entidade
     * @param currDamage o dano atual a ser setado
     */
    protected void setCurrDamage(int currDamage) {
        this.currDamage = currDamage;
    }

    /**
     * Seta a defesa corrente/atual da entidade
     * @param currDefense a defesa atual a ser setada
     */
    protected void setCurrDefense(int currDefense) {
        this.currDefense = currDefense;
    }

    /**
     * Seta a chance de crítico base da entidade
     * @param baseCritChance a chance de crítico base a ser setada 
     */
    protected void setBaseCritChance(double baseCritChance) {
        if(baseCritChance > MAX_BASE_CRIT) this.baseCritChance = MAX_BASE_CRIT;
        else if(baseCritChance < 0.0) this.baseCritChance = 0.0;
        else this.baseCritChance = baseCritChance;
    }

    /**
     * Seta a chance de crítico corrente/atual da entidade
     * @param currCritChance a chance de crítico atual a ser setada
     */
    protected void setCurrCritChance(double currCritChance) {
        this.currCritChance = currCritChance;
    }

    /**
     * Seta a precisão base da entidade 
     * @param baseAccuracy a precisão base a ser setada
     */
    protected void setBaseAccuracy(double baseAccuracy) {
        if(baseAccuracy > MAX_BASE_ACCUR) this.baseAccuracy = MAX_BASE_ACCUR;
        else if(baseAccuracy < 0.0) this.baseAccuracy = 0.0;
        else this.baseAccuracy = baseAccuracy;
    }
    
    /**
     * Seta a precisão corrente/atual da entidade
     * @param currAccuracy a precisão atual a ser setada
     */
    protected void setCurrAccuracy(double currAccuracy) {
        this.currAccuracy = currAccuracy;
    }

    /**
     * Seta a taxa de recuperação de HP corrente/atual da entidade
     * @param recRate a taxa de recuperação atual a ser setada 
     */
    protected void setCurrRecRateHP(double recRate) {
        this.currRecRateHP = recRate;
    }

    /**
     * Seta a taxa de recuperação de MP corrente/atual da entidade
     * @param recRate a taxa de recuperação atual a ser setada 
     */
    protected void setCurrRecRateMP(double recRate) {
        this.currRecRateMP = recRate;
    }

    /**
     * Seta a taxa de recuperação de HP base da entidade
     * @param baseRecRate a taxa de recuperação base a ser setada 
     */
    protected void setBaseRecRateHP(double baseRecRate) {
        this.baseRecRateHP = baseRecRate;
    }

    /**
     * Seta a taxa de recuperação de MP base da entidade
     * @param baseRecRate a taxa de recuperação base a ser setada 
     */
    protected void setBaseRecRateMP(double baseRecRate) {
        this.baseRecRateMP = baseRecRate;
    }

    /**
     * Seta o multiplicador de crítico base da entidade
     * @param multiplier o multiplicador de crítico base a ser setado
     */
    protected void setBaseCritMultiplier(double multiplier) {
        if(multiplier > MAX_CRIT_MULTIPLIER) this.baseCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(multiplier < 0.0) this.baseCritMultiplier = 0.0;
        else this.baseCritMultiplier = multiplier;
    }

    /**
     * Seta o multiplicador de crítico corrente/atual da entidade
     * @param multiplier o multiplicador de crítico atual a ser setado
     */
    protected void setCurrCritMultiplier(double multiplier) {
        if(multiplier > MAX_CRIT_MULTIPLIER) this.currCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(multiplier < 0.0) this.currCritMultiplier = 0.0;
        else this.currCritMultiplier = multiplier;
    }

    /**
     * Seta o multiplicador de defesa base da entidade
     * @param multiplier o multiplicador de defesa base a ser setado
     */
    protected void setBaseDefenseMultiplier(double multiplier) {
        this.baseDefenseMultiplier = multiplier;
    }

    /**
     * Seta o multiplicador de defesa corrente/atual da entidade
     * @param multiplier o multiplicador de defesa atual a ser setado
     */
    protected void setCurrDefenseMultiplier(double multiplier) {
        this.currDefenseMultiplier = multiplier;
    }

    /**
     * Seta o enum ID de textura da entidade
     * @param textureId o enum ID da textura
     * @see scene.TextureId
     */
    public void setTextureId(TextureId textureId) {
        this.textureId = textureId;
    }

    /**
     * Seta o gerenciador de desenho da barra de HP da entidade
     * @param healthBar a barra de HP
     * @see scene.bars.Bar
     */
    public void setHealthBar(Bar healthBar) {
        this.healthBar = healthBar;
    }

    /**
     * Seta o gerenciador de desenho da barra de MP da entidade
     * @param manaBar a barra de HP
     * @see scene.bars.Bar
     */
    public void setManaBar(Bar manaBar) {
        this.manaBar = manaBar;
    }

    /**
     * Retorna o gerenciador de desenho das estatísticas da entidade
     * @return (Statbox) a caixa de estatísticas
     * @see scene.statbox.Statbox
     */
    public void setStatbox(Statbox statbox) {
        this.statbox = statbox;
    }
}