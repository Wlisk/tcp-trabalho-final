package entities.player;

public enum ClassType {
    MAGE("Mage", 0, "resources/sprites/classes/mage.png"), 
    WARRIOR("Warrior", 1, "resources/sprites/classes/warrior.png"), 
    ARCHER("Archer", 2, "resources/sprites/classes/ranger.png");

    private final String classTypeName;
    private final int index;
    private final String imageSrc;

    private ClassType(String classTypeName, int index, String imageSrc) {
        this.classTypeName = classTypeName;
        this.index = index;
        this.imageSrc = imageSrc;
    }

    static {
        System.out.println("$DIR" + System.getProperty("user.dir"));
    }

    public String getTypeName() { return this.classTypeName; }
    public int getIndex() { return this.index; }
    public String getImageSrc() { return this.imageSrc; }

    private static final int size;
    static {
        size = values().length;
    }

    public static String[] getStrings() {
        final String[] strings = new String[size];

        int i = 0;
        for(ClassType classType: values()) {
            strings[i] = classType.toString();
            ++i;
        }

        return strings;
    }

    public static int size() { return size; }
}
