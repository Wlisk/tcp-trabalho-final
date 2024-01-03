package entities;

import utils.exceptions.MaxStringSizeException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NumberOverflowException;
import utils.Text;
import utils.Randomic;

// TODO: setters must be completed with guard statements
// - 'if' must be put to guard against values below 0 or 0.0

public class Entity {
    public static final int MAX_NAME_SIZE = 32;
    public static final int ATTACK_MISSED = 0;
    public static final double MAX_BASE_CRIT = 1.0;
    public static final double MAX_BASE_ACCUR = 1.0;
    public static final double MAX_CRIT_MULTIPLIER = 10.0;
    private static int countEntities = 0;

    private String name;
    private boolean isDead;
    private boolean isDefending;

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
    

    // TODO: variables yet to create and impement
    // Sprite sprite;
    // Vector2D position; 
    // ArrayList<Super> supers;
  

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
        resetBooleans();
    }

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

    public Entity(String name) {
        resetToZero();
        this.name = name;
        ++countEntities;
    }

    private int computeDefense() {
        int _multipliedDefense = 0;

        if(this.isDefending == true) 
            _multipliedDefense = (int)((double)currDefense * currDefenseMultiplier);
        
        return (_multipliedDefense < currDefense) ? currDefense : _multipliedDefense;
    }

    // defends from a damage value, possibily reducing it to zero
    public int defend(int damage) {
        int _defenseValue = computeDefense() - damage;

        // here, instead of missing the attack, we could
        // have a reduce in damage of 80%~90%
        if(_defenseValue >= 0) return ATTACK_MISSED;

        int _damageTaken = -_defenseValue;
        takeDamage(_damageTaken);

        return _damageTaken;
    }
    
    // pure damage taken, do nothing else
    private void takeDamage(int damage) {
        int _hpWithLoss = currHP - damage;

        if (_hpWithLoss <= 0) { 
            currHP = 0;
            isDead = true;
        }

        currHP = _hpWithLoss;
    }

    protected int calcDamage() {
        boolean _hasCrit = baseCritChance >= Randomic.between(0.0, MAX_BASE_CRIT);

        int _damage = baseDamage;

        if (_hasCrit) { 
            _damage += (int)(baseDamage * currCritMultiplier);
        }
            
        return _damage;
    }
    
    public int attack(Entity enemy) {
        boolean _haveHit = this.baseAccuracy >= Randomic.between(0.0, MAX_BASE_ACCUR);

        if(!_haveHit) return ATTACK_MISSED;

        int _damage = calcDamage();
        int _damageDone = enemy.defend(_damage);
        return _damageDone;
    }
    
    public int attackSuper(Entity enemy)/*, Super super */ {
        // TODO
        return attack(enemy);
    }
    /* 
    public int defendSuper(Super super)
    */

    protected int healHP(int amount) {
        if(amount >= 0) {
            int _hpWithHeal = currHP + amount;

            currHP = (_hpWithHeal > maxHP) ? maxHP : _hpWithHeal;
        }

        return currHP;
    }

    protected int recoverMP(int amount) {
        if(amount >= 0) {
            int _mpWithRecover = currMP + amount;

            currMP = (_mpWithRecover > maxMP) ? maxMP : _mpWithRecover;
        }

        return currMP;
    }

    // --------------------------- GETTERS --------------------------- //
    public String getName() { return name; }
    public boolean getIsDead() { return isDead; }
    public boolean getIsDefending() { return isDefending; }
    
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
    

    // --------------------------- SETTERS --------------------------- //
    protected void setName(String name) throws EmptyStringException, MaxStringSizeException { 
        if (name.length() > MAX_NAME_SIZE) 
            throw new MaxStringSizeException("Max string size is " + MAX_NAME_SIZE);
        else if (Text.stringIsEmpty(name))
            throw new EmptyStringException("Name cannot be null");

        this.name = name;
    }

    protected void setIsDefending(boolean isDefending) {
        this.isDefending = isDefending;
    }

    protected void setMaxHP(int maxHP) { 
        this.maxHP = maxHP; 
    }

    protected void setMaxMP(int maxMP) { 
        this.maxMP = maxMP; 
    }

    protected void setCurrHP(int hp) throws NumberOverflowException {
        if (currHP < 0 || currHP > maxHP)
            throw new NumberOverflowException(
                getMessageNumberOverflow("HP", maxHP)
            );

        this.currHP = hp;
    }

    protected void setCurrMP(int mp) throws NumberOverflowException {
        if (currMP < 0 || currMP > maxMP)
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
        if(critValue > MAX_CRIT_MULTIPLIER) this.currCritMultiplier = critValue;
        else if(critValue < 0.0) this.currCritMultiplier = 0.0;
        else this.currCritMultiplier = critValue;
    }

    public void setBaseDefenseMultiplier(double baseDefenseMultiplier) {
        this.baseDefenseMultiplier = baseDefenseMultiplier;
    }

    public void setCurrDefenseMultiplier(double currDefenseMultiplier) {
        this.currDefenseMultiplier = currDefenseMultiplier;
    }


    // --------------------------- STATIC RELATED --------------------------- //
    public static int getCountEntities() { return countEntities; }


    // --------------------------- PRIVATE --------------------------- //
    private static String getMessageNumberOverflow(String attribute, int maxvalue) {
        return attribute + " cannot be greater then " + maxvalue + " or lower then 0";
    }

    private void resetBooleans() {
        isDead = false;
        isDefending = false;
    }
}