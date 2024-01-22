package items;

import scene.TextureId;

/** Classe base para os itens do jogo */
public abstract class Item {
    private static final int BASE_IBOOST = 0;
    private static final double BASE_DBOOST = 0.0f;

    private final String name;
    private final ItemType itemType;
    private final String description;
    private final TextureId textureId;

    
    /** o buff/debuff de HP do item */
    protected int boostHP;
    /** o buff/debuff de MP do item */
    protected int boostMP;
    /** o buff/debuff de defesa do item */
    protected int boostDefense;
    /** o buff/debuff de dano do item */
    protected int boostDamage;
    
    /** o buff/debuff de precissão do item */
    protected double boostAccuracy; 
    /** o buff/debuff de chance de crítico do item */
    protected double boostCritChance;
    /** o buff/debuff de multiplicador de crítico do item */
    protected double boostCritMultiplier;

    /**
     * Initialize the item with base information
     * @param itemType o tipo de item (se é armadura, arma ou consumível)
     * @param textureId o enum para obter o src da textura
     * @param name o nome do item
     * @param description a descrição (ou história) do item
     */
    public Item(
        ItemType itemType, 
        TextureId textureId, 
        String name, 
        String description
    ) 
    {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.textureId = textureId;

        boostHP = boostMP = boostDamage = boostDefense = BASE_IBOOST;
        boostAccuracy = boostCritChance = boostCritMultiplier = BASE_DBOOST;
    }

    /**
     * Retorna o nome (String) do item
     * @return (String) o nome
     */
    public String getName() { return this.name; }

    /** 
     * Retorna o tipo (Itemtype) do item  
     * @return (ItemType) o tipo do item (consumable, weapon, armor)
     */
    public ItemType getItemType(){  return this.itemType; }

    /** 
     * Retorna a descrição (String) do item
     * @return (String) a descrição do item
     */
    public String getDescription() { return this.description; }

    /** 
     * Retorna o enum do src da textura (textureId)
     * @return (TextureId) o id de textura
     */
    public TextureId getTextureId() { return this.textureId; }

    /** 
     * Retorna o valor de boost (int) do HP
     * @return (int) o buff/debuff de HP
     */
    public int getBoostHP() { return boostHP; }

    /** 
     * Retorna o valor de boost (int) do MP
     * @return (int) o buff/debuff de MP
     */
    public int getBoostMP() { return boostMP; }

    /** 
     * Retorna o valor de boost (int) da defesa
     * @return (int) o buff/debuff de defesa
     */
    public int getBoostDefense() { return boostDefense; }

    /** 
     * Retorna o valor de boost (int) do dano
     * @return (int) o buff/debuff de dano
     */
    public int getBoostDamage() { return boostDamage; }

    /** 
     * Retorna o valor de boost (int) da precisão
     * @return (int) o buff/debuff de precisão
     */
    public double getBoostAccuracy() { return boostAccuracy; }

    /** 
     * Retorna o valor de boost (int) da chance de crítico
     * @return (int) o buff/debuff de chance de crítico
     */
    public double getBoostCritChance() { return boostCritChance; }

    /** 
     * Retorna o valor de boost (int) do multiplicador de crítico
     * @return (int) o buff/debuff de multiplicador de crítico
     */
    public double getBoostCritMultiplier() { return boostCritMultiplier; }
}
