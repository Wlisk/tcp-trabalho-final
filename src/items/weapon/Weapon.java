package items.weapon;

import items.Item;
import items.ItemType;
import scene.TextureId;

public final class Weapon extends Item {
    private WeaponType weaponType;

    public Weapon(
        WeaponType weaponType, TextureId textureId, String name, 
        int boostDamage, int boostDefense,
        double boostAccuracy, double boostCritChance, double boostCritMultiplier, String description
    ) 
    {
        super(ItemType.WEAPON, textureId, name, description);
        this.weaponType = weaponType;

        this.boostDamage = boostDamage;
        this.boostDefense = boostDefense;
        this.boostAccuracy = boostAccuracy;
        this.boostCritChance = boostCritChance;
        this.boostCritMultiplier = boostCritMultiplier;
    }

    public WeaponType getWeaponType() { return this.weaponType; }

}
