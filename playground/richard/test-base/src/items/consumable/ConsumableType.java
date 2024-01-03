package items.consumable;

public enum ConsumableType {
    POTION("Potion"), 
    FOOD("Food"), 
    POISON("Venon");

    private final String consumableTypeName;

    private ConsumableType(String consumableTypeName) {
        this.consumableTypeName = consumableTypeName;
    }

    @Override
    public String toString() { return this.consumableTypeName; }
}
