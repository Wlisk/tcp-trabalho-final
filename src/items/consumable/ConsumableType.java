package items.consumable;

/** 
 * Enum para os tipos de consumíveis/usáveis existentes no jogo. 
 * @see java.lang.Enum
 */
public enum ConsumableType {
    /** consumíveis do tipo poção */
    POTION("Potion"), 
    /** consumíveis do tipo comida */
    FOOD("Food"), 
    /** consumíveis do tipo veneno (aplicados em armas) */
    POISON("Venon");

    private final String consumableTypeName;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o nome do tipo
     * @param consumableTypeName o nome (string) do tipo
     */
    private ConsumableType(String consumableTypeName) {
        this.consumableTypeName = consumableTypeName;
    }

    /**
     * Retorna a string para o tipo de consumível/usável corrente/utilizado
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() { return this.consumableTypeName; }
}
