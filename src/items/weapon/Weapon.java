package items.weapon;

import config.Config;
import exceptions.NumberOverflowException;
import items.Item;
import items.ItemType;
import scene.TextureId;
import utils.Number;
import utils.Text;

/** 
 * Classe para os itens do tipo arma do jogo 
 * @see items.Item
 * @see items.ItemType
 */
public final class Weapon extends Item {
    private WeaponType weaponType;
    private final String[] textLines;

    /**
     * Construtor do item arma
     * @param weaponType o tipo de arma utilizada (espada, arco, cajado)
     * @param textureId o enum para obter o src da textura da arma
     * @param name o nome da arma
     * @param boostDamage a quantidade de aumento de dano
     * @param boostDefense a quantidade de aumento de defesa
     * @param boostAccuracy a quantidade de aumento de precisão
     * @param boostCritChance a quantidade de aumento de chance de crítico
     * @param boostCritMultiplier a quantidade de aumento de multiplicador de crítico
     * @param description a descrição da arma
     * @see items.weapon.WeaponType
     * @see scene.TextureId
     */
    public Weapon(
        WeaponType weaponType, 
        TextureId textureId, 
        String name, 
        int boostDamage, 
        int boostDefense,
        double boostAccuracy, 
        double boostCritChance, 
        double boostCritMultiplier, 
        String description
    ) throws NumberOverflowException
    {
        super(ItemType.WEAPON, textureId, name, description);
        this.weaponType = weaponType;

        Number.limitTo(Number.MIN_DPERCENTAGE, Number.MAX_DPERCENTAGE, boostAccuracy);
        Number.limitTo(Number.MIN_DPERCENTAGE, Number.MAX_DPERCENTAGE, boostCritChance);

        this.boostDamage = boostDamage;
        this.boostDefense = boostDefense;
        this.boostAccuracy = boostAccuracy;
        this.boostCritChance = boostCritChance;
        this.boostCritMultiplier = boostCritMultiplier;

        textLines = createTextLines();
    }

    /**
     * Cria a lista de informações do item para a sua caixa de informações (infobox)
     * @return (String[]) a lista de informações do item
     */
    private String[] createTextLines() {  
        return new String[]{
            Config.INFOBOX_TEXT_DMG + Integer.toString(getBoostDamage()),
            Config.INFOBOX_TEXT_CRITC + Integer.toString((int)(getBoostCritChance() * 100)) + "%",
            Config.INFOBOX_TEXT_CRITM + Integer.toString((int)(getBoostCritMultiplier() * 100)) + "%",
            Config.INFOBOX_TEXT_ACC + Integer.toString((int)(getBoostAccuracy() * 100)) + "%",
            Config.INFOBOX_TEXT_DEF + Integer.toString(getBoostDefense()),
            Text.wrap(getDescription(), Config.INFOBOX_WRAP_SIZE)
        };
        }

    /** Retorna o tipo (WeaponType) da arma  */
    public WeaponType getType() { return this.weaponType; }

    /**
     * Retorna a lista de informações  do item para a sua caixa de informações (infobox)
     * @return (String[]) a lista de informações do item
     */
    public String[] getTextLines() { return textLines; }
}
