package items.consumable;

import items.Item;
import items.ItemType;
import scene.TextureId;

/** 
 * Classe para os itens do tipo conumível do jogo 
 * @see items.Item
 * @see items.ItemType
 */
public final class Consumable extends Item {
    private ConsumableType consumableType;
    //private int turnsDuration;

    // * @param boostDamage a quantidade de aumento de dano
    // * @param turnsDuration o número de turnos que o consumível dura
    /**
     * Construtor do item comsumível
     * @param consumableType o tipo de consumível utilizado (poção, alimento, veneno)
     * @param textureId o enum para obter o src da textura do consumível
     * @param name o nome do consumível
     * @param boostHP a quantidade de aumento de HP
     * @param boostMP a quantidade de aumento de MP
     * @param description a descrição do consumível
     * @see items.consumable.ConsumableType
     * @see scene.TextureId
     */
    public Consumable(
        ConsumableType consumableType, 
        TextureId textureId, 
        String name, 
        int boostHP, 
        int boostMP, 
        /*int boostDamage,
        int turnsDuration,*/ 
        String description
    ) 
    {
        super(ItemType.CONSUMABLE, textureId, name, description);
        this.consumableType = consumableType;

        this.boostHP = boostHP;
        this.boostMP = boostMP;
        //this.boostDamage = boostDamage;
        //this.turnsDuration = turnsDuration;
    }

    /** 
     * Retorna o tipo (ConsumableType) do consumível 
     * @return (ConsumableType) tipo do consúmivel
     */
    public ConsumableType getConsumableType() { return consumableType; }
    ///* Retorna o número (int) de duração (turnos totais) do consumível */
    //public int getTurnsDuration() { return turnsDuration; }
}
