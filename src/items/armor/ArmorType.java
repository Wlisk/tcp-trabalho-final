package items.armor;

/** 
 * Enum para os tipos de armaduras/vestíveis existentes no jogo. 
 * @see java.lang.Enum
 */
public enum ArmorType {
    /** armaduras do tipo metálico */
    PLATE("Plate"), 
    /** armaduras do tipo couro */
    LEATHER("Leather"), 
    /** armaduras do tipo pano */
    ROBE("Robe");

    private final String armorTypeName;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o nome do tipo
     * @param armorTypeName o nome (string) do tipo
     */
    private ArmorType(String armorTypeName) {
        this.armorTypeName = armorTypeName;
    }

    /**
     * Retorna a string para o tipo de armadura corrente/utilizado
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() { return this.armorTypeName; }
}
