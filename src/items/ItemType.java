package items;

/** 
 * Enum para todos os tipos de itens existentes no jogo. 
 * @see java.lang.Enum
 */
public enum ItemType {
    /** itens do tipo armas */
    WEAPON("Weapon"), 
    /** itens do tipo armadura ou vestíveis */
    ARMOR("Armor"), 
    /** itens do tipo consumíveis, buff ou uso */
    CONSUMABLE("Consumable");

    private final String itemTypeName;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o nome do tipo
     * @param itemTypeName o nome (string) do tipo
     */
    private ItemType(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    /**
     * Retorna a string para o tipo de item corrente/utilizado
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() { return this.itemTypeName; }
}
