package entities.player;

public enum ClassType {
    MAGE("Mage", 0, "src/resources/sprites/classes/mage.png"), 
    WARRIOR("Warrior", 1, "src/resources/sprites/classes/warrior.png"), 
    ARCHER("Archer", 2, "src/resources/sprites/classes/ranger.png");

    private final String classTypeName;
    private final int index;
    private final String imageSrc;

    private ClassType(String classTypeName, int index, String imageSrc) {
        this.classTypeName = classTypeName;
        this.index = index;
        this.imageSrc = imageSrc;
    }

    public String getTypeName() { return this.classTypeName; }
    public int getIndex() { return this.index; }
    public String getImageSrc() { return this.imageSrc; }


    public static final int size;
    static {
        size = values().length;
    }
    
    public static int getNumClasses() {
        return size;
    }

    public static String[] getStrings() {
        final int numClasses = getNumClasses();
        final String[] strings = new String[numClasses];

        int i = 0;
        for(ClassType classType: values()) {
            strings[i] = classType.toString();
            ++i;
        }

        return strings;
    }
}
