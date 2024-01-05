package entities.player;

import entities.Entity;
import entities.boss.Boss;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.Inventory;
import items.consumable.Consumable;

public final class Player extends Entity {
    // Warrior: 1000HP, 200MP, 50DEF, 85DMG, 0.8ACC
    // Ranger: 750HP, 300MP, 30DEF, 70DMG, 0.9ACC
    // Mage: 500HP, 500MP, 10DEF, 100DMG, 0.8ACC
    private final static int STARTING_EXP_TO_LEVEL = 100;

    private final static double 
        LUP_MULT_EXP_TO_LEVEL = 0.5, 
        LUP_MULT_MAX_HP = 1.1, 
        LUP_MULT_MAX_MP = 1.1, 
        LUP_MULT_BASE_DAMAGE = 1.1, 
        LUP_MULT_CRIT_CHANCE = 1.05, 
        LUP_MULT_ACC = 1.02;

    private int level;
    private int exp;
    private int expToLevel = 0;
    private final Inventory inventory;
    private final ClassType classType;
    private int temporaryDamageBoost;

    public Player(String name, ClassType classType) throws UnknownTypeException {
        super(name);

        level = 1;
        exp = 0;
        expToLevel = calcExpToLevel(level);
        temporaryDamageBoost = 0;
        inventory = new Inventory();

        this.classType = ClassTypeUtil.setClassType(classType);

        setMaxHP( ClassTypeUtil.getHP() );
        setMaxMP( ClassTypeUtil.getMP() );
        
        setBaseDamage( ClassTypeUtil.getDamage() );
        setBaseDefense( ClassTypeUtil.getDefense() );

        setBaseCritChance( ClassTypeUtil.getCritChance() );
        setBaseCritMultiplier( ClassTypeUtil.getCritMultiplier() );
        setBaseAccuracy( ClassTypeUtil.getAccuracy() );

        inventory.setEquipedArmor( ClassTypeUtil.getInitialArmor() );
        inventory.setEquipedWeapon( ClassTypeUtil.getInitialWeapon() );
    }

    public int receiveExp(int gainedExp) throws NumberOverflowException {
        int _currExp = exp + gainedExp;
        if (_currExp >= expToLevel) {
            levelUp();
            int _overflowExp = expToLevel - _currExp;
            exp = _overflowExp;
        } 
        else exp = _currExp;
        return exp;
    }

    private int calcExpToLevel(int level) {
        int _expByLevel = level * STARTING_EXP_TO_LEVEL;
        int _halfPrevExp = (int)Math.floor(expToLevel * LUP_MULT_EXP_TO_LEVEL); 

        return _expByLevel + _halfPrevExp;
    }

    private int levelUp() throws NumberOverflowException {
        level += 1;
        exp = 0;
        expToLevel = calcExpToLevel(level);

        setMaxHP( (int)(getMaxHP() * LUP_MULT_MAX_HP) );
        setCurrHP( getMaxHP() );

        setMaxMP( (int)(getMaxMP() * LUP_MULT_MAX_MP) );
        setCurrMP( getMaxMP() );
        
        setBaseDamage( (int)(getBaseDamage() * LUP_MULT_BASE_DAMAGE) );
        setBaseCritChance( getBaseCritChance() * LUP_MULT_CRIT_CHANCE );
        setBaseAccuracy( getBaseAccuracy() * LUP_MULT_ACC );

        return level;
    }

    public Consumable useConsumable(int index) {
        Consumable _consumable = inventory.use(index);

        if (_consumable != null) {
            healHP(_consumable.getBoostHP());
            recoverMP(_consumable.getBoostMP());
            temporaryDamageBoost = _consumable.getBoostDamage();
        }
        return _consumable;
    }

    public int attack(Boss enemy) {
        setCurrDamage( getCurrDamage() + temporaryDamageBoost );
        int _damageDone = super.attack(enemy);

        setCurrDamage( getCurrDamage() - temporaryDamageBoost );
        temporaryDamageBoost = 0;

        return _damageDone;
    }
    
    // ------------ GETTERS AND SETTERS ------------ //
    
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public int getExpToLevel() { return expToLevel; }
    public Inventory getInventory() { return inventory; }
    public ClassType getClassType() { return classType; }
}
