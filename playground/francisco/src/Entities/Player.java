package Entities;

import Items.*;
import Utils.PlayerClass;
import Utils.Utils;

public class Player extends Entity {
    // Constants for initializing a player of a certain class
    private final static int CLASS1_MAX_HP = 1000, CLASS1_MAX_MP = 200, CLASS1_BASE_DEF = 50, CLASS1_BASE_DMG = 85;
    private final static double CLASS1_BASE_ACC = 0.8;
    private final static String CLASS1_NAME = "Warrior";

    private final static int CLASS2_MAX_HP = 750, CLASS2_MAX_MP = 300, CLASS2_BASE_DEF = 30, CLASS2_BASE_DMG = 70;
    private final static double CLASS2_BASE_ACC = 0.9;
    private final static String CLASS2_NAME = "Ranger";

    private final static int CLASS3_MAX_HP = 500, CLASS3_MAX_MP = 500, CLASS3_BASE_DEF = 10, CLASS3_BASE_DMG = 100; 
    private final static double CLASS3_BASE_ACC = 0.8;
    private final static String CLASS3_NAME = "Mage";

    private final static double STARTING_CRIT_CHANCE = 0.2;
    private final static int STARTING_EXP_TO_LEVEL = 100;

    // Level up multipliers
    private final static double LUP_MULT_EXP_TO_LEVEL = 1.5, LUP_MULT_MAX_HP = 1.1, LUP_MULT_MAX_MP = 1.1, LUP_MULT_BASE_DAMAGE = 1.1, LUP_MULT_CRIT_CHANCE = 1.05, LUP_MULT_ACC = 1.02;

    // End of constants

    private int level;
    private int exp;
    private int expToLevel;
    private Inventory inventory;
    private PlayerClass playerClass;

    public Player(PlayerClass playerClass){
        // Constructor for player given a starting class
        if (playerClass == PlayerClass.CLASS1){
            this.maxHp = CLASS1_MAX_HP;
            this.hp = CLASS1_MAX_HP;
            this.maxMp = CLASS1_MAX_MP;
            this.mp = CLASS1_MAX_MP;
            this.baseDefence = CLASS1_BASE_DEF;
            this.baseDamage = CLASS1_BASE_DMG;
            this.baseAccuracy = CLASS1_BASE_ACC;
            this.name = CLASS1_NAME;
            this.inventory = new Inventory(PlayerClass.CLASS1);
        } else if (playerClass == PlayerClass.CLASS2){
            this.maxHp = CLASS2_MAX_HP;
            this.hp = CLASS2_MAX_HP;
            this.maxMp = CLASS2_MAX_MP;
            this.mp = CLASS2_MAX_MP;
            this.baseDefence = CLASS2_BASE_DEF;
            this.baseDamage = CLASS2_BASE_DMG;
            this.baseAccuracy = CLASS2_BASE_ACC;
            this.name = CLASS2_NAME;
            this.inventory = new Inventory(PlayerClass.CLASS2);
        } else {    
            this.maxHp = CLASS3_MAX_HP;
            this.hp = CLASS3_MAX_HP;
            this.maxMp = CLASS3_MAX_MP;
            this.mp = CLASS3_MAX_MP;
            this.baseDefence = CLASS3_BASE_DEF;
            this.baseDamage = CLASS3_BASE_DMG;
            this.baseAccuracy = CLASS3_BASE_ACC;
            this.name = CLASS3_NAME;
            this.inventory = new Inventory(PlayerClass.CLASS3);
        }

        this.baseCritChance = STARTING_CRIT_CHANCE;
        this.alive = true;
        this.level = 1;
        this.exp = 0;
        this.expToLevel = STARTING_EXP_TO_LEVEL;
        this.playerClass = playerClass; 
    }

    @Override
    public int attack(Entity attacked){
        // Function for a player attacking a boss. Returns damage if hit, -1 if attack missed
        boolean hit = this.baseAccuracy >= Utils.randomRange(0, 1);
        boolean crit = this.baseCritChance >= Utils.randomRange(0, 1);
        
        if (hit){
            int dmg = this.calcDamage(attacked, crit);
            attacked.take_damage(dmg);
            return dmg;
        }
        return -1; // Special value for missed attack
    };

    @Override
    protected int calcDamage(Entity attacked, boolean crit){
        int dmg = (int) (this.getBaseDmg() * this.inventory.getWeapon().getDamageMultiplier());
        int dmgReduction = attacked.getBaseDef() - attacked.getBaseDef();

        if (crit){ // Crit deals 150% damage
            dmg = (int)((this.getBaseDmg() * 1.5) * this.inventory.getWeapon().getDamageMultiplier());
        }

        if (attacked.isDefending()) { // If attacked entity is defending, their defence is doubled
            dmgReduction -= attacked.getBaseDef();
        }
            
        if (dmg - dmgReduction < dmg * 0.2){ // Defence damage reduction caps at 80%
            return (int) (dmg * 0.2);
        } else {
            return dmg - dmgReduction;
        }
    }

    @Override
    public int superAttack(Entity attacked){
        // TODO
        return 0;
    }

    public void receiveExp(int exp){
        this.exp += exp;
        if (this.exp >= this.expToLevel){
            this.levelUp();
        }
    }

    private void levelUp(){
        this.level += 1;
        this.exp -= expToLevel;
        this.expToLevel *= LUP_MULT_EXP_TO_LEVEL;
        this.maxHp *= LUP_MULT_MAX_HP;
        this.hp = this.maxHp;
        this.maxMp *= LUP_MULT_MAX_MP;
        this.mp = this.maxMp;
        this.baseDamage *= LUP_MULT_BASE_DAMAGE;
        this.baseCritChance *= LUP_MULT_CRIT_CHANCE;
        this.baseAccuracy *= LUP_MULT_ACC;
    }

    public void useConsumable(int index){
        Consumable consumable = this.inventory.getConsumable(index);
        if (consumable != null){
            this.inventory.removeConsumable(index);
            this.healHp(consumable.getHpBoost());
            this.recoverMp(consumable.getMpBoost());
        }
    }
    
    // ------------ GETTERS AND SETTERS ------------ //
    
    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getExp(){
        return this.exp;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public int getExpToLevel(){
        return this.expToLevel;
    }

    public void setExpToLevel(int expToLevel){
        this.expToLevel = expToLevel;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public PlayerClass getPlayerClass(){
        return this.playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass){
        this.playerClass = playerClass;
    }
}
