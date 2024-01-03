package items;

public class Item {
    private String name;
    private ItemType itemType;

    protected int 
        boostHP,
        boostMP,
        boostDefense,
        boostDamage;
    
    protected double 
        boostAccuracy, 
        boostCritChance,
        boostCritMultiplier;

    public Item(ItemType itemType, String name) {
        this.itemType = itemType;
        this.name = name;

        boostHP = boostMP = 0;
        boostDamage = boostDefense = 0;
        boostAccuracy = boostCritChance = boostCritMultiplier = 0.0;
    }

    public String getName() { return this.name; }
    public ItemType getItemType(){  return this.itemType; }

    public int getBoostHP() { return boostHP; }
    public int getBoostMP() { return boostMP; }
    public int getBoostDefense() { return boostDefense; }
    public int getBoostDamage() { return boostDamage; }
    public double getBoostAccuracy() { return boostAccuracy; }
    public double getBoostCritChance() { return boostCritChance; }
    public double getBoostCritMultiplier() { return boostCritMultiplier; }
}
