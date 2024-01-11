package scene;

public enum TextureId { // Texture ids to reference texture files stored in the Scene class
    CLASS_1("resources/sprites/classes/mage.png"),
    CLASS_2("resources/sprites/classes/warrior.png"),
    CLASS_3("resources/sprites/classes/ranger.png"), 
    BOSS_1("resources/sprites/bosses/boss1.png"),
    BOSS_2("resources/sprites/bosses/boss2.png"), 
    BOSS_3("resources/sprites/bosses/boss3.png"), 
    BOSS_4("resources/sprites/bosses/boss4.png"), 
    BOSS_5("resources/sprites/bosses/boss5.png"), 
    CONSUMABLE_RED_BIG("resources/sprites/items/consumables/redbig.png"), 
    CONSUMABLE_RED_SMALL("resources/sprites/items/consumables/redsmall.png"), 
    CONSUMABLE_RED_VIAL("resources/sprites/items/consumables/redvial.png"), 
    CONSUMABLE_BLUE_BIG("resources/sprites/items/consumables/bluebig.png"), 
    CONSUMABLE_BLUE_SMALL("resources/sprites/items/consumables/bluesmall.png"), 
    CONSUMABLE_BLUE_VIAL("resources/sprites/items/consumables/bluevial.png"), 
    CONSUMABLE_GREEN_BIG("resources/sprites/items/consumables/greenbig.png"), 
    CONSUMABLE_GREEN_SMALL("resources/sprites/items/consumables/greensmall.png"), 
    CONSUMABLE_GREEN_VIAL("resources/sprites/items/consumables/greenvial.png"), 
    CONSUMABLE_ORANGE_BIG("resources/sprites/items/consumables/orangebig.png"), 
    CONSUMABLE_ORANGE_SMALL("resources/sprites/items/consumables/orangesmall.png"), 
    CONSUMABLE_ORANGE_VIAL("resources/sprites/items/consumables/orangevial.png"),
    CONSUMABLE_BANANA("resources/sprites/items/consumables/banana.png"),
    CONSUMABLE_BREAD("resources/sprites/items/consumables/bread.png"),
    CONSUMABLE_CHEESE("resources/sprites/items/consumables/cheese.png"),
    CONSUMABLE_GRAPES("resources/sprites/items/consumables/grapes.png"),
    CONSUMABLE_PIZZA("resources/sprites/items/consumables/pizza.png"),
    CONSUMABLE_STEAK("resources/sprites/items/consumables/steak.png"),
    WEAPON_SWORD_1("resources/sprites/items/weapons/sword1.png"), 
    WEAPON_SWORD_2("resources/sprites/items/weapons/sword2.png"), 
    WEAPON_SWORD_3("resources/sprites/items/weapons/sword3.png"), 
    WEAPON_SWORD_4("resources/sprites/items/weapons/sword4.png"),
    WEAPON_BOW_1("resources/sprites/items/weapons/bow1.png"), 
    WEAPON_BOW_2("resources/sprites/items/weapons/bow2.png"), 
    WEAPON_BOW_3("resources/sprites/items/weapons/bow3.png"), 
    WEAPON_BOW_4("resources/sprites/items/weapons/bow4.png"),
    WEAPON_STAFF_1("resources/sprites/items/weapons/staff1.png"), 
    WEAPON_STAFF_2("resources/sprites/items/weapons/staff2.png"), 
    WEAPON_STAFF_3("resources/sprites/items/weapons/staff3.png"), 
    WEAPON_STAFF_4("resources/sprites/items/weapons/staff4.png"),
    ARMOR_LIGHT_1("resources/sprites/items/armor/robe1.png"), 
    ARMOR_LIGHT_2("resources/sprites/items/armor/robe2.png"), 
    ARMOR_LIGHT_3("resources/sprites/items/armor/robe3.png"),
    ARMOR_MEDIUM_1("resources/sprites/items/armor/chain1.png"), 
    ARMOR_MEDIUM_2("resources/sprites/items/armor/chain2.png"), 
    ARMOR_MEDIUM_3("resources/sprites/items/armor/chain3.png"),
    ARMOR_HEAVY_1("resources/sprites/items/armor/heavy1.png"), 
    ARMOR_HEAVY_2("resources/sprites/items/armor/heavy2.png"), 
    ARMOR_HEAVY_3("resources/sprites/items/armor/heavy3.png");

    private final String path;
    private TextureId(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}