package Items;

import Utils.PlayerClass;

import java.util.ArrayList;

public class Inventory {
    private static final int maxItems = 5;

    private Weapon weapon;
    private Armor armor;
    private ArrayList<Consumable> consumables;

    public Inventory(PlayerClass playerClass){ 
        // Constructor for starting inventory of a class
        this.consumables = new ArrayList<Consumable>(maxItems);
        this.weapon = new Weapon(playerClass);
        this.armor = new Armor(playerClass);
    }

    public void addConsumable(Consumable consumable){
        if (this.consumables.size() < 5){
            this.consumables.add(consumable);
        }
    }

    public void removeConsumable(int index){
        if (this.consumables.get(index) != null){
            this.consumables.remove(index);
        }
    }

    public Consumable getConsumable(int index){
        return this.consumables.get(index);
    }

    public int getMaxItems(){
        return Inventory.maxItems;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public Armor getArmor(){
        return this.armor;
    }

    public void setArmor(Armor armor){
        this.armor = armor;
    }

    public ArrayList<Consumable> getConsumables(){
        return consumables;
    }

    public void setConsumables(ArrayList<Consumable> consumables){
        this.consumables = consumables;
    }
}
