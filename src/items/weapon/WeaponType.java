package items.weapon;

/** 
 * Enum para os tipos de armas existentes no jogo. 
 * @see java.lang.Enum
 */
public enum WeaponType {
    /** armas do tipo espada */
    SWORD("Sword"), 
    /** armas do tipo arco (e flecha) */
    BOW("Bow"), 
    /** armas do tipo cajado/varinha */
    STAFF("Staff");

    private final String weaponTypeName;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o nome do tipo
     * @param weaponTypeName o nome (string) do tipo
     */
    private WeaponType(String weaponTypeName) {
        this.weaponTypeName = weaponTypeName;
    }

    /**
     * Retorna a string para o tipo de arma corrente/utilizado
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() { return this.weaponTypeName; }
}
