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
     * Construtor interno do Enum para setar valores para o tipo
     * @param classTypeName o nome (string) do tipo
     * @param index o índice (int) do tipo
     * @param imageSrc o caminho da imagem (desde a raíz do projeto) do tipo
     * @param spriteSheetSrc o caminho do spritesheet (desde a raíz do projeto) do tipo
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

    /**
     * Retorna o nome do tipo da classe corrente/utilizada
     * @return (String) o nome do tipo
     */
    public String getTypeName() { return this.classTypeName; }

    /**
     * Retorna o índice do tipo da classe corrente/utilizada
     * @return (int) o índice do tipo
     */
    public int getIndex() { return this.index; }

    /**
     * Retorna o caminho (desde a raíz do projeto) da imagem do tipo corrente/utilizado
     * @return (String) o caminho da imagem do tipo
     */
    public String getImageSrc() { return this.imageSrc; }

    /**
     * Retorna o caminho (desde a raíz do projeto) do spritesheet do tipo corrente/utilizado
     * @return (String) o caminho do sriptesheet do tipo
     */
    public String getSpriteSheetSrc() { return this.spriteSheetSrc; }

    // used this way so we lessen the overhead of calling 'values().length' inside 'size()'
    private static final int size;
    static {
        size = values().length;
    }
    /**
     * Retorna a quantidade de tipos
     * @return (int) a quatidade de tipos
     */
    public static int size() { return size; }

    /**
     * Retorna uma lista de strings com o nome dos tipos, 
     * no formato de criação do tipo, ou seja, todas strings maiúsculas
     * @return (String[]) lista de strings dos nomes dos tipos
     */
    public static String[] getStrings() {
        final String[] strings = new String[size];

        int i = 0;
        for(ClassType classType: values()) {
            strings[i++] = classType.toString();
        }
        return strings;
    }
}
