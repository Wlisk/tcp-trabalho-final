package scene;

import com.raylib.Raylib.Texture;

/** 
 * Enum para as imagens/texturas do jogo.
 * <p>
 * Além disso, armazena o caminho das imagens/texturas.
 * @see java.lang.Enum
 */
public enum TextureId { 
    // Texture ids to reference texture files stored in the Scene class
    /** Textura para a classe do jogador: mago */
    CLASS_1("resources/sprites/classes/mage.png"),
    /** Textura para a classe do jogador: guerreiro */
    CLASS_2("resources/sprites/classes/warrior.png"),
    /** Textura para a classe do jogador: arqueiro */
    CLASS_3("resources/sprites/classes/ranger.png"), 

    /** Textura para o boss do jogo 1o */
    BOSS_1("resources/sprites/bosses/boss1.png"),
    /** Textura para o boss do jogo 2o */
    BOSS_2("resources/sprites/bosses/boss2.png"), 
    /** Textura para o boss do jogo 3o */
    BOSS_3("resources/sprites/bosses/boss3.png"), 
    /** Textura para o boss do jogo 4o */
    BOSS_4("resources/sprites/bosses/boss4.png"), 
    /** Textura para o boss do jogo 5o */
    BOSS_5("resources/sprites/bosses/boss5.png"), 

    /** Textura para o item consumível do jogo: poção de HP grande */
    CONSUMABLE_RED_BIG("resources/sprites/items/consumables/redbig.png"), 
    /** Textura para o item consumível do jogo: poção de HP pequena */
    CONSUMABLE_RED_SMALL("resources/sprites/items/consumables/redsmall.png"),
    /** Textura para o item consumível do jogo: poção de HP normal */ 
    CONSUMABLE_RED_VIAL("resources/sprites/items/consumables/redvial.png"), 
    /** Textura para o item consumível do jogo: poção de MP grande */
    CONSUMABLE_BLUE_BIG("resources/sprites/items/consumables/bluebig.png"), 
    /** Textura para o item consumível do jogo: poção de MP pequena */
    CONSUMABLE_BLUE_SMALL("resources/sprites/items/consumables/bluesmall.png"), 
    /** Textura para o item consumível do jogo: poção de MP normal */
    CONSUMABLE_BLUE_VIAL("resources/sprites/items/consumables/bluevial.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_GREEN_BIG("resources/sprites/items/consumables/greenbig.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_GREEN_SMALL("resources/sprites/items/consumables/greensmall.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_GREEN_VIAL("resources/sprites/items/consumables/greenvial.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_ORANGE_BIG("resources/sprites/items/consumables/orangebig.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_ORANGE_SMALL("resources/sprites/items/consumables/orangesmall.png"), 
    /** Textura para o item consumível do jogo: poção  */
    CONSUMABLE_ORANGE_VIAL("resources/sprites/items/consumables/orangevial.png"),

    /** Textura para o item consumível do jogo: alimento banana */
    CONSUMABLE_BANANA("resources/sprites/items/consumables/banana.png"),
    /** Textura para o item consumível do jogo: alimento pão */
    CONSUMABLE_BREAD("resources/sprites/items/consumables/bread.png"),
    /** Textura para o item consumível do jogo: alimento queijo */
    CONSUMABLE_CHEESE("resources/sprites/items/consumables/cheese.png"),
    /** Textura para o item consumível do jogo: alimento uvas */
    CONSUMABLE_GRAPES("resources/sprites/items/consumables/grapes.png"),
    /** Textura para o item consumível do jogo: alimento pizza */
    CONSUMABLE_PIZZA("resources/sprites/items/consumables/pizza.png"),
    /** Textura para o item consumível do jogo: alimento bife */
    CONSUMABLE_STEAK("resources/sprites/items/consumables/steak.png"),

    /** Textura para o item arma do jogo da classe guerreiro: espada 1 */
    WEAPON_SWORD_1("resources/sprites/items/weapons/sword1.png"), 
    /** Textura para o item arma do jogo da classe guerreiro: espada 2 */
    WEAPON_SWORD_2("resources/sprites/items/weapons/sword2.png"), 
    /** Textura para o item arma do jogo da classe guerreiro: espada 3 */
    WEAPON_SWORD_3("resources/sprites/items/weapons/sword3.png"),
    /** Textura para o item arma do jogo da classe guerreiro: espada 4 */
    WEAPON_SWORD_4("resources/sprites/items/weapons/sword4.png"),

    /** Textura para o item arma do jogo da classe arqueiro: arco 1 */
    WEAPON_BOW_1("resources/sprites/items/weapons/bow1.png"), 
    /** Textura para o item arma do jogo da classe arqueiro: arco 2 */
    WEAPON_BOW_2("resources/sprites/items/weapons/bow2.png"), 
    /** Textura para o item arma do jogo da classe arqueiro: arco 3 */
    WEAPON_BOW_3("resources/sprites/items/weapons/bow3.png"), 
    /** Textura para o item arma do jogo da classe arqueiro: arco 4 */
    WEAPON_BOW_4("resources/sprites/items/weapons/bow4.png"),

    /** Textura para o item arma do jogo da classe mago: cajado 1 */
    WEAPON_STAFF_1("resources/sprites/items/weapons/staff1.png"), 
    /** Textura para o item arma do jogo da classe mago: cajado 2 */
    WEAPON_STAFF_2("resources/sprites/items/weapons/staff2.png"), 
    /** Textura para o item arma do jogo da classe mago: cajado 3 */
    WEAPON_STAFF_3("resources/sprites/items/weapons/staff3.png"), 
    /** Textura para o item arma do jogo da classe mago: cajado 4 */
    WEAPON_STAFF_4("resources/sprites/items/weapons/staff4.png"),

    /** Textura para o item armadura do jogo da classe mago: pano 1 */
    ARMOR_LIGHT_1("resources/sprites/items/armor/robe1.png"), 
    /** Textura para o item armadura do jogo da classe mago: pano 2 */
    ARMOR_LIGHT_2("resources/sprites/items/armor/robe2.png"), 
    /** Textura para o item armadura do jogo da classe mago: pano 3 */
    ARMOR_LIGHT_3("resources/sprites/items/armor/robe3.png"),

    /** Textura para o item armadura do jogo da classe arqueiro: couro 1 */
    ARMOR_MEDIUM_1("resources/sprites/items/armor/chain1.png"), 
    /** Textura para o item armadura do jogo da classe arqueiro: couro 2 */
    ARMOR_MEDIUM_2("resources/sprites/items/armor/chain2.png"), 
    /** Textura para o item armadura do jogo da classe arqueiro: couro 3 */
    ARMOR_MEDIUM_3("resources/sprites/items/armor/chain3.png"),

    /** Textura para o item armadura do jogo da classe guerreiro: metal 1 */
    ARMOR_HEAVY_1("resources/sprites/items/armor/heavy1.png"), 
    /** Textura para o item armadura do jogo da classe guerreiro: metal 2 */
    ARMOR_HEAVY_2("resources/sprites/items/armor/heavy2.png"), 
    /** Textura para o item armadura do jogo da classe guerreiro: metal 3 */
    ARMOR_HEAVY_3("resources/sprites/items/armor/heavy3.png");

    private final String path;
    private final TextureLoader textureLoader;

    /** 
     * Construtor interno do Enum para setar valores para o tipo, 
     * neste caso, o caminho da imagem/textura (desde a raiz do projeto)
     * @param path o caminho da imagem
     */
    private TextureId(String path) {
        this.path = path;
        textureLoader = new TextureLoader();
    }

    /**
     * Retorna o caminho (desde a raiz do projeto) do tipo corrente/utilizado
     * @return (String) o caminho da imagem
     */
    public String getPath() { return path; }

    /**
     * Retorna da memória a textura, e ou carrega na memória caso já não esteja  
     * @return (Texture) a textura carregada na memória ou null caso não consiga carregar a textura
     */
    public Texture getTexture() { return textureLoader.getTexture(path); }

    /**
     * Descarrega a textura da memória
     * @return (boolean) retorna true se foi descarregada ou false se não há nada a descarregar
     */
    public boolean unloadTexture() { return textureLoader.unloadTexture(); }
}