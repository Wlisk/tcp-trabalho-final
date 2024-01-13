package items;

import java.util.ArrayList;

import items.armor.Armor;
import items.consumable.Consumable;
import items.consumable.Consumables;
import items.weapon.Weapon;

/** Gerencia o inventário do jogador, podendo-se adicinar, remover e usar itens do inventário. */
public final class Inventory {
    /** Número máximo de items permitidos no inventário */
    public static final int MAX_ITEMS = 5;

    private Weapon equipedWeapon;
    private Armor equipedArmor;
    private final ArrayList<Item> items = new ArrayList<Item>(MAX_ITEMS);

    /** Inicializa o inventário com 2 consumíveis, uma poção de MP e outra de HP */
    public Inventory() { 
        equipedArmor = null;
        equipedWeapon = null;
        items.add( (Item)Consumables.getConsumablePotion(Consumables.INDEX_POTION_HP) );
        items.add( (Item)Consumables.getConsumablePotion(Consumables.INDEX_POTION_MP) );
    }

    /**
     * Adiciona items ao inventário (ou tenta)
     * @item o item a ser adicionado
     * @return (Item) o item que foi adicionado ou null em caso de inventário cheio
     */
    public Item add(Item item) {
        if(isFull()) return null;
        items.add(item);
        return item;
    }

    /**
     * Remove items do inventário (ou tenta)
     * @param index o índice do item no inventário
     * @return (Item) o item que foi removido ou null caso contrário
     */
    public Item remove(int index) {
        return items.remove(index);
    }

    /**
     * Equipa items no jogador (ou tenta)
     * @param index o índice do item no inventário
     * @return (Item) o item que foi equipado ou null caso contrário
     */
    public Item equip(int index) {
        final Item _selectedItem = getItem(index);
        if(_selectedItem == null) return null;

        final ItemType _itemType = _selectedItem.getItemType();

        if(_itemType == ItemType.ARMOR) {
            remove(index); // remove from inventory the selected armor
            add(equipedArmor);
            equipedArmor = (Armor)_selectedItem;
            
        }

        else if(_itemType == ItemType.WEAPON) {
            remove(index); // remove from inventory the selected weapon
            add(equipedWeapon);
            equipedWeapon = (Weapon)_selectedItem;
        }

        else return null;

        return _selectedItem;
    }

    /**
     * Usa ou Consome itens do inventário (ou tenta)
     * @param index o índice do item no inventário
     * @return (Consumable) o item que foi consumido/usado no inventário ou null caso contrário
     */
    public Consumable use(int index) {
        Item _selectedItem = getItem(index);
        if(_selectedItem == null) return null;

        if(_selectedItem.getItemType() != ItemType.CONSUMABLE) return null;

        remove(index);
        return (Consumable)_selectedItem;
    }

    /**
     * Retorna o item na posicão especificada sem removê-lo do inventário
     * @param index o índice do item no inventário
     * @return (Item) o item buscado ou null caso não encontrado na posição indicada
     */
    public Item getItem(int index) {
        if(index < 0 || index >= items.size()) return null;
        return items.get(index);
    }

    /**
     * Verifica se o inventário está cheio
     * @return (boolean) se o inventário está cheio ou não
     */
    public boolean isFull() { return items.size() >= MAX_ITEMS; }

    /** Retorna o número (int) de itens atualmente no inventário */
    public int size() { return items.size(); }

    /** 
     * Retorna a armadura equipada 
     * @return (Armor) a armadura ou null se nenhuma equipada
     */
    public Armor getEquippedArmor() { return equipedArmor; }

    /** 
     * Retorna a arma equipada
     * @return (Weapon) a arma ou null se nenhuma equipada
     */
    public Weapon getEquippedWeapon() { return equipedWeapon; }

    /**
     * Equipa uma armadura
     * @param armor a armadura a ser equipada, pode ser null
     */
    public void equipArmor(Armor armor) {
        this.equipedArmor = armor;
    }

    /**
     * Equipa uma arma
     * @param weapon a arma a ser equipada, pode ser null
     */
    public void equipWeapon(Weapon weapon) {
        this.equipedWeapon = weapon;
    }
}
