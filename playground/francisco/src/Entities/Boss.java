package Entities;

public class Boss extends Entity{
    private int expReward;

    public Boss(int maxHp, int maxMp, int baseDamage, int baseDefence, double baseCritChance, double baseAccuracy, int expReward, String name){
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMp = maxMp;
        this.mp = maxMp;
        this.baseDamage = baseDamage;
        this.baseCritChance = baseCritChance;
        this.baseAccuracy = baseAccuracy;
        this.expReward = expReward;
        this.name = name;
        this.alive = true;
        this.defending = 0;
    }

    public int getExpReward(){
        return this.expReward;
    }

    public void setExpReward(int expReward){
        this.expReward = expReward;
    }
}
