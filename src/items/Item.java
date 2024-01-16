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

    protected int 
        boostHP,
        boostMP,
        boostDefense,
        boostDamage;
    
    protected double 
        boostAccuracy, 
        boostCritChance,
        boostCritMultiplier;

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

    /** Retorna o nome (String) do item */
    public String getName() { return this.name; }
    /** Retorna o tipo (Itemtype) do item  */
    public ItemType getItemType(){  return this.itemType; }
    /** Retorna a descrição (String) do item */
    public String getDescription() { return this.description; }
    /** Retorna o enum do src da textura (textureId)  */
    public TextureId getTextureId() { return this.textureId; }

    /** Retorna o valor de boost (int) do hp */
    public int getBoostHP() { return boostHP; }
    /** Retorna o valor de boost (int) do mp */
    public int getBoostMP() { return boostMP; }
    /** Retorna o valor de boost (int) da defesa */
    public int getBoostDefense() { return boostDefense; }
    /** Retorna o valor de boost (int) do dano */
    public int getBoostDamage() { return boostDamage; }
    /** Retorna o valor de boost (double) da precisão */
    public double getBoostAccuracy() { return boostAccuracy; }
    /** Retorna o valor de boost (double) da chance de crítico */
    public double getBoostCritChance() { return boostCritChance; }
    /** Retorna o valor de boost (double) do multiplicador de crítico */
    public double getBoostCritMultiplier() { return boostCritMultiplier; }
}
