package entities.player;

import entities.Entity;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.Inventory;
import items.consumable.Consumable;
import scene.bars.Bars;
import scene.statbox.Statboxes;
import items.Item;
import items.ItemType;

public final class Player extends Entity {
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
    //private int temporaryDamageBoost;

    public Player(String name, ClassType classType) throws UnknownTypeException, NumberOverflowException {
        super(name);

        level = 1;
        exp = 0;
        expToLevel = calcExpToLevel(level);
        //temporaryDamageBoost = 0;
        inventory = new Inventory();

        this.classType = ClassTypeUtil.setClassType(classType);

        setMaxHP( ClassTypeUtil.getHP() );
        setMaxMP( ClassTypeUtil.getMP() );
        
        setBaseDamage( ClassTypeUtil.getDamage() );
        setBaseDefense( ClassTypeUtil.getDefense() );

        setBaseCritChance( ClassTypeUtil.getCritChance() );
        setBaseCritMultiplier( ClassTypeUtil.getCritMultiplier() );
        setBaseAccuracy( ClassTypeUtil.getAccuracy() );
        setBaseDefenseMultiplier( ClassTypeUtil.getDefenseMultiplier() );

        resetToBase();

        setTextureId(ClassTypeUtil.getTextureId());
        
        inventory.equipArmor( ClassTypeUtil.getInitialArmor() );
        inventory.equipWeapon( ClassTypeUtil.getInitialWeapon() );
        
        applyItemBuff(inventory.getEquippedArmor());
        applyItemBuff(inventory.getEquippedWeapon());

        setHealthBar(Bars.newPlayerHealthBar(this));
        setManaBar(Bars.newPlayerManaBar(this));
        setStatbox(Statboxes.newPlayerStatbox(this));
    }

    public void receiveExp(int gainedExp) throws NumberOverflowException {
        exp += gainedExp;
        while (exp >= expToLevel) {
            exp -= expToLevel;
            levelUp();
        } 
    }

    private int calcExpToLevel(int level) {
        int _expByLevel = level * STARTING_EXP_TO_LEVEL;
        int _halfPrevExp = (int)Math.floor(expToLevel * LUP_MULT_EXP_TO_LEVEL); 

        return _expByLevel + _halfPrevExp;
    }

    private int levelUp() throws NumberOverflowException {
        level += 1;
        expToLevel = calcExpToLevel(level);

        deapplyItemBuff(inventory.getEquippedArmor());
        deapplyItemBuff(inventory.getEquippedWeapon());

        setMaxHP( (int)(getMaxHP() * LUP_MULT_MAX_HP) );
        setCurrHP( getMaxHP() );

        setMaxMP( (int)(getMaxMP() * LUP_MULT_MAX_MP) );
        setCurrMP( getMaxMP() );
        
        setBaseDamage( (int)(getBaseDamage() * LUP_MULT_BASE_DAMAGE) );
        setCurrDamage(getBaseDamage());
        setBaseCritChance( getBaseCritChance() * LUP_MULT_CRIT_CHANCE );
        setCurrCritChance( getCurrCritChance() );
        setBaseAccuracy( getBaseAccuracy() * LUP_MULT_ACC );
        setCurrAccuracy( getCurrAccuracy() );

        applyItemBuff(inventory.getEquippedArmor());
        applyItemBuff(inventory.getEquippedWeapon());

        return level;
    }
    
    public void addItemInventory(Item item){
        this.inventory.add(item);
    }

    public Item getItemInventory(int index){
        return this.inventory.getItem(index);
    }

    public void addItemsInventory(Item[] items){
        for (final Item item: items){
            if (!inventory.isFull()){
                this.inventory.add(item);
            }
        }
    }

    public Consumable useConsumable(int index) {
        Consumable _consumable = inventory.use(index);

        if (_consumable != null) {
            healHP(_consumable.getBoostHP());
            recoverMP(_consumable.getBoostMP());
        }

        return _consumable;
    }

    public void equipInventory(int index) {
        Item _equipment = inventory.getItem(index);
        if(_equipment == null) return;

        if(_equipment.getItemType() == ItemType.ARMOR)
            deapplyItemBuff( inventory.getEquippedArmor() );

        else if(_equipment.getItemType() == ItemType.WEAPON)
            deapplyItemBuff( inventory.getEquippedWeapon() );

        inventory.equip(index);
        applyItemBuff(_equipment);
    }
    
    private void applyItemBuff(Item equipment) {
        final int _boostDefense = equipment.getBoostDefense();
        setCurrDefense( getCurrDefense() + _boostDefense);
        setCurrDamage( getCurrDamage() + equipment.getBoostDamage() );
        setCurrCritChance( getCurrCritChance() + equipment.getBoostCritChance() );
        setCurrCritMultiplier( getCurrCritMultiplier() + equipment.getBoostCritMultiplier() );
        setCurrAccuracy( getCurrAccuracy() + equipment.getBoostAccuracy() );
    }

    private void deapplyItemBuff(Item equipment) {
        final int _boostDefense = equipment.getBoostDefense();
        setCurrDefense( getCurrDefense() - _boostDefense);
        setCurrDamage( getCurrDamage() - equipment.getBoostDamage() );
        setCurrCritChance( getCurrCritChance() - equipment.getBoostCritChance() );
        setCurrCritMultiplier( getCurrCritMultiplier() - equipment.getBoostCritMultiplier() );
        setCurrAccuracy( getCurrAccuracy() - equipment.getBoostAccuracy() );
    }
    
    // ------------ GETTERS AND SETTERS ------------ //
    
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public int getExpToLevel() { return expToLevel; }
    public Inventory getInventory() { return inventory; }
    public ClassType getClassType() { return classType; }
}
