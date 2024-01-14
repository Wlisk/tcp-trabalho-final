package items.armor;

import entities.player.ClassType;
import entities.player.ClassTypeUtil;
import exceptions.UnknownTypeException;
import scene.TextureId;
import utils.Randomic;

/**
 * Classe com propriedades e métodos estáticos para 
 * criação e armazenamento das armaduras do jogo.
 * @see items.weapon.Armor
 * @see scene.TextureId
 */
public final class Armors {
    /** Índice (int) da armadura inicial do jogador (qualquer classe) */
    public static final int INDEX_INITIAL_ARMOR = 0;

    private static final Armor[] ARMORS_MAGE = {
        new Armor(
            ArmorType.ROBE, 
            TextureId.ARMOR_LIGHT_1,
            "Tattered Robe", 
            20, 
            //20, 
            //100, 
            "It has seen better days"
        )
    };

    private static final Armor[] ARMORS_WARRIOR = {
        new Armor(
            ArmorType.PLATE, 
            TextureId.ARMOR_HEAVY_1,
            "Rusty Plate Armor", 
            50, 
            //100, 
            //20,
            "Be aware that any punch can scraps it"
        )
    };

    private static final Armor[] ARMORS_ARCHER = {
        new Armor(
            ArmorType.LEATHER, 
            TextureId.ARMOR_MEDIUM_1,
            "Old Leather Armor", 
            35, 
            //60, 
            //60,
            "Sew with rats leather"
        )
    };

    /**
     * Retorna a armadura da classe mago, dado a posição da armadura na 
     * lista de armaduras do mago
     * @param index posição (int) da armadura do mago na lista
     * @return (Armor) a armadura encontrada, ou null caso contrário
     */
    public static Armor getArmorMage(int index) {
        if(index < 0 || index >= ARMORS_MAGE.length) return null;
        return ARMORS_MAGE[index];
    }

    /**
     * Retorna a armadura da classe guerreiro, dado a posição da armadura 
     * na lista de armaduras do guerreito
     * @param index posição (int) da armadura do guerreiro na lista
     * @return (Armor) a armadura encontrada, ou null caso contrário
     */
    public static Armor getArmorWarrior(int index) {
        if(index < 0 || index >= ARMORS_WARRIOR.length) return null;
        return ARMORS_WARRIOR[index];
    }

    /**
     * Retorna a armadura da classe arqueiro, dado a posição da armadura 
     * na lista de armaduras doarqueiro
     * @param index posição (int) da armadura do arqueiro na lista
     * @return (Armor) a armadura encontrada, ou null caso contrário
     */
    public static Armor getArmorArcher(int index) {
        if(index < 0 || index >= ARMORS_ARCHER.length) return null;
        return ARMORS_ARCHER[index];
    }

    /**
     * Retorna uma armadura randomicamente baseado na classe do jogador passada
     * @param classType a classe ao qual se deseja a armadura
     * @return (Armor) a armadura da classe randomicamente escolhida
     * @throws UnknownTypeException tipo de classe passado desconhecido
     * @see entities.player.ClassType
     * @see exceptions.UnknownTypeException
     * @see entities.player.ClassTypeUtil#ERR_TYPE_MESSAGE
     */
    public static Armor getRandomByClass(ClassType classType) throws UnknownTypeException {
        switch(classType) {
            case MAGE: {
                final int _i = Randomic.between(0, ARMORS_MAGE.length - 1);
                return ARMORS_MAGE[_i];
            }    

            case WARRIOR: {
                final int _i = Randomic.between(0, ARMORS_WARRIOR.length - 1);
                return ARMORS_WARRIOR[_i];
            }

            case ARCHER: {
                final int _i = Randomic.between(0, ARMORS_ARCHER.length - 1);
                return ARMORS_ARCHER[_i];
            }

            default:
                throw new UnknownTypeException(ClassTypeUtil.ERR_TYPE_MESSAGE);
        }
    }
}
