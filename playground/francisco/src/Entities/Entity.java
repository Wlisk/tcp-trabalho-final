package Entities;

import Utils.Utils;

public class Entity {
    protected int maxHp;
    protected int hp;
    protected int maxMp;
    protected int mp; 
    protected int baseDamage;
    protected int baseDefence;
    protected double baseCritChance;
    protected double baseAccuracy;
    protected boolean alive;
    protected int defending; // Number of turns entity is defending for; Will be decremented every turn.
    protected String name;
    
    public int attack(Entity attacked){
        // Function for an entity attacking another. Returns damage if hit, -1 if attack missed
        boolean hit = this.baseAccuracy >= Utils.randomRange(0, 1);
        boolean crit = this.baseCritChance >= Utils.randomRange(0, 1);
        
        if (hit){
            int dmg = this.calcDamage(attacked, crit);
            attacked.take_damage(dmg);
            return dmg;
        }
        return -1; // Special value for missed attack
    };
    
    public void take_damage(int dmg){
        this.hp -= dmg;
        if (this.hp <= 0){ // If health runs out, entity dies
            this.alive = false;
        }
    }

    protected int calcDamage(Entity attacked, boolean crit){
        int dmg = this.baseDamage;
        int dmgReduction = attacked.baseDefence;

        if (crit){ // Crit deals 150% damage
            dmg = (int)(this.baseDamage * 1.5);
        }

        if (attacked.isDefending()) { // If attacked entity is defending, their defence is doubled
            dmgReduction -= attacked.baseDefence;
        }
            
        if (dmg - dmgReduction < dmg * 0.2){ // Defence damage reduction caps at 80%
            return (int) (dmg * 0.2);
        } else {
            return dmg - dmgReduction;
        }
    }
    
    public int superAttack(Entity attacked){
        // TODO
        return 0;
    }

    public void defend(int turns) {
        this.defending = turns;
    }

    public boolean isDefending(){
        return this.defending > 0;
    }   

    public void updateDefend() {
        // Lowers the number of turns a creature is defending for. Is called every turn
        if (this.isDefending()) {
            this.defending -= 1;
        }
    }

    public void healHp(int amount){
        if (this.hp + amount <= maxHp){
            this.hp += amount;
        } else {
            this.hp = this.maxHp;
        }
    }

    public void recoverMp(int amount){
        if (this.mp + amount <= maxMp){
            this.mp += amount;
        } else {
            this.mp = this.maxMp;
        }
    }

    // ------------ GETTERS AND SETTERS ------------ //
    
    public int getMaxHp(){
        return this.maxHp;
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public int getMaxMp(){
        return this.maxMp;
    }

    public void setMaxMp(int maxMp){
        this.maxMp = maxMp;
    }

    public int getHp(){
        return this.hp;
    }

    public void setHp(int hp){
        if (hp > 0 && hp <= maxHp){
            this.hp =  hp;
        }
    }

    public int getMp(){
        return this.mp;
    }

    public void setMp(int mp){
        if (mp > 0 && mp <= maxMp){
            this.mp =  mp;
        }
    }

    public int getBaseDef(){
        return this.baseDefence;
    }

    public void setBaseDef(int baseDefence){
        this.baseDefence = baseDefence;
    }

    public int getBaseDmg(){
        return this.baseDamage;
    }

    public void setBaseDmg(int baseDamage){
        this.baseDamage = baseDamage;
    }

    public double getBaseCritChance(){
        return this.baseCritChance;
    }

    public void setBaseCritChance(double baseCritChance){
        this.baseCritChance = baseCritChance;
    }

    public double getBaseAccuracy(){
        return this.baseAccuracy;
    }

    public void setBaseAccuracy(double baseAccuracy){
        this.baseAccuracy = baseAccuracy;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public int getDefending(){
        return this.defending;
    }

    public void setDefending(int defending){
        this.defending = defending;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}