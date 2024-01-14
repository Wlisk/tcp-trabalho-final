package entities.player;

/** 
 * Enum para os tipos de classe do jogador.
 * <p>
 * Além disso, armazena o caminho das imagens de cada tipo de classe.
 * @see java.lang.Enum
 */
public enum ClassType {
    /** Classe do tipo mago */
    MAGE(
        "Mage", 
        0, 
        "resources/sprites/classes/mage.png",
        "resources/sprites/sheet/wizard-sheet.png"
    ), 
    /** Classe do tipo guerreiro */
    WARRIOR(
        "Warrior", 
        1, 
        "resources/sprites/classes/warrior.png",
        "resources/sprites/sheet/warrior-sheet.png"
    ), 
    /** Classe do tipo arqueiro */
    ARCHER(
        "Archer", 
        2, 
        "resources/sprites/classes/ranger.png",
        "resources/sprites/sheet/ranger-sheet.png"
    );

    private final String classTypeName;
    private final int index;
    private final String imageSrc;
    private final String spriteSheetSrc;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o nome do tipo
     * @param itemTypeName o nome (string) do tipo
     */
    private ClassType(String classTypeName, int index, String imageSrc, String spriteSheetSrc) {
        this.classTypeName = classTypeName;
        this.index = index;
        this.imageSrc = imageSrc;
        this.spriteSheetSrc = spriteSheetSrc;
    }

    /*static {
        System.out.println("$DIR" + System.getProperty("user.dir"));
    }*/

    public String getTypeName() { return this.classTypeName; }
    public int getIndex() { return this.index; }
    public String getImageSrc() { return this.imageSrc; }
    public String getSpriteSheetSrc() { return this.spriteSheetSrc; }

    private static final int size;
    static {
        size = values().length;
    }
    public static int size() { return size; }

    public static String[] getStrings() {
        final String[] strings = new String[size];

        int i = 0;
        for(ClassType classType: values()) {
            strings[i++] = classType.toString();
        }

        return strings;
    }
}
