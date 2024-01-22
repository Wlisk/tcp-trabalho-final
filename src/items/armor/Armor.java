package items.armor;

import config.Config;
import items.Item;
import items.ItemType;
import scene.TextureId;
import utils.Text;

/** 
 * Classe para os itens do tipo armadura do jogo 
 * @see items.Item
 * @see items.ItemType
 */
public final class Armor extends Item {
    private ArmorType armorType;
    private final String[] textLines;

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
        textLines = createTextLines();
    }

    /**
     * Cria a lista de informações do item para a sua caixa de informações (infobox)
     * @return (String[]) a lista de informações do item
     */
    private String[] createTextLines() {
        return new String[]{
            Config.INFOBOX_TEXT_DEF + Integer.toString(getBoostDefense()),
            Text.wrap(getDescription(), Config.INFOBOX_WRAP_SIZE)
        };
    }

    /** Retorna o tipo (ArmorType) da armadura  */
    public ArmorType getType() { return armorType; }

    /**
     * Retorna a lista de informações  do item para a sua caixa de informações (infobox)
     * @return (String[]) a lista de informações do item
     */
    public String[] getTextLines() { return textLines; }
}
