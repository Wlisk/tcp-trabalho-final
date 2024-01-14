package items.armor;

import items.Item;
import items.ItemType;
import scene.TextureId;

/** 
 * Classe para os itens do tipo armadura do jogo 
 * @see items.Item
 * @see items.ItemType
 */
public final class Armor extends Item {
    private ArmorType armorType;

    // * @param boostHP a quantidade de aumento de hp
    // * @param boostMP a quantidade de aumento de mp
    /**
     * Construtor do item armadura
     * @param armorType o tipo de armadura utilizada (metálica, couro ou pano)
     * @param textureId o enum para obter o src da textura da armadura
     * @param name o nome da armadura
     * @param boostDefense a quantidade de aumento de defesa
     * @param description a descrição da armadura
     * @see items.armor.ArmorType
     * @see scene.TextureId
     */
    public Armor(
        ArmorType armorType, 
        TextureId textureId, 
        String name, 
        int boostDefense, 
        /*int boostHP, int boostMP,*/ 
        String description
    ) 
    {
        super(ItemType.ARMOR, textureId, name, description);
        this.armorType = armorType;

        this.boostDefense = boostDefense;
        //this.boostHP = boostHP;
        //this.boostMP = boostMP;
    }

    /** Retorna o tipo (ArmorType) da armadura  */
    public ArmorType getArmorType() { return armorType; }
}
