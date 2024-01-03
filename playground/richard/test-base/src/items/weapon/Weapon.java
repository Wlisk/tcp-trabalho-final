package items.weapon;

import items.Item;
import items.ItemType;

public final class Weapon extends Item {
    private WeaponType weaponType;

    public Weapon(
        WeaponType weaponType, String name, 
        int boostDamage, int boostDefense,
        double boostAccuracy, double boostCritChance, double boostCritMultiplier
    ) 
    {
        super(ItemType.WEAPON, name);
        this.weaponType = weaponType;

        this.boostDamage = boostDamage;
        this.boostDefense = boostDefense;
        this.boostAccuracy = boostAccuracy;
        this.boostCritChance = boostCritChance;
        this.boostCritMultiplier = boostCritMultiplier;
    }

    public WeaponType getWeaponType() { return this.weaponType; }

}
