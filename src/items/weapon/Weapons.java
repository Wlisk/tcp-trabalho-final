package items.weapon;

import entities.player.ClassType;
import entities.player.ClassTypeUtil;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import scene.TextureId;
import utils.Randomic;

/**
 * Classe com propriedades e métodos estáticos para 
 * criação e armazenamento das armas do jogo.
 * @see items.weapon.Weapon
 * @see exceptions.NumberOverflowException
 * @see scene.TextureId
 */
public final class Weapons {
    /** Índice inicial das armas base do jogador */
    public static final int INDEX_INITIAL_WEAPON = 0;
    // variable used to get the exception from the static methods
    private static NumberOverflowException err = null;

    /**
     * Tenta criar e instanciar todas as armas da classe mago
     * @return (Weapon[]) armas para a classe mago
     */
    private static Weapon[] createWeaponsMage() {
        Weapon[] _weapons = null;

        try {
            final Weapon[] _tempWeapons = {
                new Weapon(
                    WeaponType.STAFF, 
                    TextureId.WEAPON_STAFF_1,
                    "Rotting Staff", 
                    50, 
                    0, 
                    0.05, 
                    0.05, 
                    0.1,
                    "Made from a rotting magical tree, sometimes it backfires."
                ), 
        
                new Weapon(
                    WeaponType.STAFF, 
                    TextureId.WEAPON_STAFF_2,
                    "Eldritch Staff", 
                    200, 
                    30, 
                    0.15, 
                    0.2, 
                    1.5, 
                    "Made from a branch of the oldest tree in the realm."
                )
            };

            _weapons = _tempWeapons;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _weapons;
    }

    /**
     * Tenta criar e instanciar todas as armas da classe guerreiro
     * @return (Weapon[]) armas para a classe guerreiro
     */
    private static Weapon[] createWeaponsWarrior() {
        Weapon[] _weapons = null;

        try {
            final Weapon[] _tempWeapons = {
                new Weapon(
                    WeaponType.SWORD, 
                    TextureId.WEAPON_SWORD_1,
                    "Rusty Sword", 
                    40, 
                    30,  
                    0.05, 
                    0.05, 
                    0.1,
                    "Its rustiness is a double edged sword."
                ),
        
                new Weapon(
                    WeaponType.SWORD, 
                    TextureId.WEAPON_SWORD_2,
                    "Hero of Salvation Sword", 
                    180, 
                    100,  
                    0.15, 
                    0.3, 
                    1.0, 
                    "Made to protect the realm against the invaders."
                )
            };

            _weapons = _tempWeapons;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _weapons;
    }

    /**
     * Tenta criar e instanciar todas as armas da classe arqueiro
     * @return (Weapon[]) armas para a classe arqueiro
     */
    private static Weapon[] createWeaponsArcher() {
        Weapon[] _weapons = null;

        try {
            final Weapon[] _tempWeapons = {
                new Weapon(
                    WeaponType.BOW, 
                    TextureId.WEAPON_BOW_1,
                    "Old Bow", 
                    45, 
                    0, 
                    0.05, 
                    0.05, 
                    0.1, 
                    "This one is... well... be careful."
                ),
        
                new Weapon(
                    WeaponType.BOW, 
                    TextureId.WEAPON_BOW_2,
                    "Chaos Bow", 
                    190, 
                    50, 
                    0.45, 
                    0.3, 
                    1.2, 
                    "None shall pass."
                )
            };

            _weapons = _tempWeapons;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _weapons;
    }

    // weapons bank
    private static final Weapon[] WEAPONS_MAGE = createWeaponsMage();
    private static final Weapon[] WEAPONS_WARRIOR = createWeaponsWarrior();
    private static final Weapon[] WEAPONS_ARCHER = createWeaponsArcher();

    /**
     * Retorna a arma da classe mago, dado a posição da arma na lista de armas do mago
     * @param index posição (int) da arma do mago na lista
     * @return (Weapon) a arma encontrada, ou null caso contrário
     */
    public static Weapon getWeaponMage(int index) {
        if(index < 0 || index >= WEAPONS_MAGE.length) return null;
        return WEAPONS_MAGE[index];
    }

    /**
     * Retorna a arma da classe guerreiro, dado a posição da arma na lista de armas do guerreiro
     * @param index posição (int) da arma do guerreiro na lista
     * @return (Weapon) a arma encontrada, ou null caso contrário
     */
    public static Weapon getWeaponWarrior(int index) {
        if(index < 0 || index >= WEAPONS_WARRIOR.length) return null;
        return WEAPONS_WARRIOR[index];
    }

    /**
     * Retorna a arma da classe arqueiro, dado a posição da arma na lista de armas do arqueiro
     * @param index posição (int) da arma do arqueiro na lista
     * @return (Weapon) a arma encontrada, ou null caso contrário
     */
    public static Weapon getWeaponArcher(int index) {
        if(index < 0 || index >= WEAPONS_ARCHER.length) return null;
        return WEAPONS_ARCHER[index];
    }

    /**
     * Retorna a exceção gerada dentro dos métodos estáticos
     * @return (NumberOverflowException) a excessão gerada
     * @see exceptions.NumberOverflowException
     */
    public static NumberOverflowException getException() { return err; }

    /**
     * Retorna uma arma randomicamente baseado na classe do jogador passada
     * @param classType a classe ao qual se deseja a arma
     * @return (Weapon) a arma da classe randomicamente escolhida
     * @throws UnknownTypeException tipo de classe passado desconhecido
     * @see entities.player.ClassType
     * @see exceptions.UnknownTypeException
     * @see entities.player.ClassTypeUtil#ERR_TYPE_MESSAGE
     */
    public static Weapon getRandomByClass(ClassType classType) throws UnknownTypeException {
        switch(classType) {
            case MAGE: {
                final int _i = Randomic.between(0, WEAPONS_MAGE.length - 1);
                return WEAPONS_MAGE[_i];
            }    

            case WARRIOR: {
                final int _i = Randomic.between(0, WEAPONS_WARRIOR.length - 1);
                return WEAPONS_WARRIOR[_i];
            }

            case ARCHER: {
                final int _i = Randomic.between(0, WEAPONS_ARCHER.length - 1);
                return WEAPONS_ARCHER[_i];
            }

            default:
                throw new UnknownTypeException(ClassTypeUtil.ERR_TYPE_MESSAGE);
        }
    }
}
