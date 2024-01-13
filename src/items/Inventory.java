package items;

import java.util.ArrayList;

import items.armor.Armor;
import items.consumable.Consumable;
import items.consumable.Consumables;
import items.weapon.Weapon;
import scene.inventory.InventoryUI;
import scene.inventory.InventoryUIInst;


public final class Inventory {
    public static final int MAX_ITEMS = 5;

    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private InventoryUI inventoryUI;
    private final ArrayList<Item> items = new ArrayList<Item>(MAX_ITEMS);

    public Inventory() { 
        items.add( (Item)Consumables.getConsumablePotion(Consumables.INDEX_POTION_HP) );
        items.add( (Item)Consumables.getConsumablePotion(Consumables.INDEX_POTION_MP) );
        this.inventoryUI = InventoryUIInst.newInventoryUI(this);
    }

    public Item add(Item item) {
        if(this.isFull()) return null;
        items.add(item);
        return item;
    }

    public Item remove(int index) {
        return items.remove(index);
    }

    public Item equip(int index) {
        Item _selectedItem = getItem(index);
        if(_selectedItem == null) return null;

        ItemType _itemType = _selectedItem.getItemType();

        if(_itemType == ItemType.ARMOR) {
            items.add(equippedArmor);
            equippedArmor = (Armor)_selectedItem;
        }

        else if(_itemType == ItemType.WEAPON) {
            items.add(equippedWeapon);
            equippedWeapon = (Weapon)_selectedItem;
        }

        else return null;

        remove(index);
        return _selectedItem;
    }

    public Consumable use(int index) {
        Item _selectedItem = getItem(index);
        if(_selectedItem == null) return null;

        if(_selectedItem.getItemType() != ItemType.CONSUMABLE) return null;

        remove(index);
        return (Consumable)_selectedItem;
    }

    public Item getItem(int index) {
        if (index < this.items.size()){
            return items.get(index);
        } 
        return null;
    }

    public int checkLeftClickedIndex(){ return inventoryUI.checkLeftClickedIndex(); } 
    public int checkRightClickedIndex(){ return inventoryUI.checkRightClickedIndex(); } 

    public boolean isFull() { return items.size() >= MAX_ITEMS; }
    public int size() { return items.size(); }

    public Armor getEquippedArmor() { return equippedArmor; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public InventoryUI getInventoryUI() { return inventoryUI; }

    public void setEquippedArmor(Armor equipedArmor) {
        this.equippedArmor = equipedArmor;
    }
    public void setEquippedWeapon(Weapon equipedWeapon) {
        this.equippedWeapon = equipedWeapon;
    }
}
