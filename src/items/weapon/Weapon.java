package items.weapon;

import exceptions.NumberOverflowException;
import items.Item;
import items.ItemType;
import scene.TextureId;
import utils.Number;

/** 
 * Classe para os itens do tipo arma do jogo 
 * @see items.Item
 * @see items.ItemType
 */
public final class Weapon extends Item {
    private WeaponType weaponType;

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
     * @see items.armor.WeaponType
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
    }

    /** Retorna o tipo (WeaponType) da arma  */
    public WeaponType getWeaponType() { return this.weaponType; }
}
