package Items;

import Utils.ItemType;

public class Consumable extends Item{
    private int hpBoost;
    private int mpBoost;

    public Consumable(String name, int hpBoost, int mpBoost){
        this.name = name;
        this.itemType = ItemType.CONSUMABLE;
        this.hpBoost = hpBoost;
        this.mpBoost = mpBoost;
    }

    public int getHpBoost(){
        return this.hpBoost;
    }

    public void setHpBoost(int hpBoost){
        this.hpBoost = hpBoost;
    }

    public int getMpBoost(){
        return this.mpBoost;
    }

    public void setMpBoost(int mpBoost){
        this.mpBoost = mpBoost;
    }
}
