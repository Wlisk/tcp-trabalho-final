package entities.boss;

import exceptions.NumberOverflowException;
import scene.TextureId;

/**
 * Classe com propriedades e métodos estáticos para 
 * criação e armazenamento dos bosses do jogo.
 * @see entities.boss.Boss
 * @see exceptions.NumberOverflowException
 * @see scene.TextureId
 */
public final class Bosses {
    private static int bossNextCounter = 0;
    private static NumberOverflowException err = null;

    /**
     * Seta e configura os chefões do jogo
     * <p>
     * Com a instância de {@link entities.boss.Boss Boss} pode gerar erro, 
     * usa-se try-catch para captura o erro e salva-lo em variável estática externa 
     * @return (Boss[]) os bosses do jogo
     * @see entities.boss.Boss
     */
    private static Boss[] createBosses() {
        Boss[] _bosses = null;

        try {
            final Boss[] _tempBosses = {
                new Boss( 
                    "Aragdhar", 
                    TextureId.BOSS_1,
                    500,
                    600,
                    200,
                    150,
                    20,
                    0.1,
                    1.25,
                    0.8,
                    1,
                    0.2, 
                    0.5,
                    0.2,
                    0.2,
                    2, 2,
                    "Born at the Hell of Valathur, its protruding fangs drips venon that poison its enemies to death. Is a nightmare that no one wants to confront."
                ),
                new Boss(
                    "Irmitz", 
                    TextureId.BOSS_2,
                    1000,
                    1000,
                    250,
                    250,
                    30,
                    0.15,
                    1.25,
                    0.80,
                    1,
                    0.25,
                    0.6,
                    0.3,
                    0.2,
                    2, 2,
                    "The fire itself, burns anyone who gets close. Be catious, cause his temperament is fiery."
                ),
                new Boss(
                    "Bolrvots", 
                    TextureId.BOSS_3,
                    1500,
                    1400,
                    300,
                    350,
                    40,
                    0.15,
                    1.30,
                    0.8,
                    1,
                    0.3,
                    0.7,
                    0.4,
                    0.2,
                    2, 2,
                    "An dark elven that has fallen to the dark side. It has betrayed its people and so was condened to eternity."
                ),
                new Boss(
                    "Mirax", 
                    TextureId.BOSS_4,
                    2000,
                    2000,
                    500,
                    400,
                    60,
                    0.30,
                    1.35,
                    0.85,
                    1,
                    0.35, 
                    0.8,
                    0.45,
                    0.2,
                    2, 2,
                    "Dreams and Ilusions becomes reality and its power are the nightmares themselves. One blink one kill, \"No one shall sleep before me!\""
                ),
                new Boss(
                    "Oslav", 
                    TextureId.BOSS_5,
                    2500,
                    2500,
                    1000,
                    500,
                    100,
                    0.35,
                    1.5,
                    0.9,
                    1,
                    0.9,
                    0.45,
                    0.5,
                    0.2, 
                    2, 2, 
                    "They killed his loved one, so now he will make de world burn"
                )
            };

            _bosses = _tempBosses;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _bosses;
    }

    private static Boss[] bossList = createBosses();
    
    /**
     * Retorna a exceção gerada na criação dos chefões
     * @return (NumberOverflowException) a exceção
     * @see exceptions.NumberOverflowException
     */
    public static NumberOverflowException getException() { return err; }

    /**
     * Retorna o número de chefões criados
     * @return (int) o número de chefões
     */
    public static int quantity() { return bossList.length; }

    /**
     * Retorna o índice do chefão corrente/atual 
     * @return (int) o índice do chefão atual
     */
    public static int getCurrIndex() { return bossNextCounter; }

    /**
     * Retorna a lista de chefões criados
     * @return (Boss[]) a lista de chefões
     */
    public static Boss[] getList() { return bossList; }
    
    /**
     * Retorna o próximo chefão da lista, iniciando pelo primeiro da lista
     * @return (Boss) o próximo chefão ou null se não existir próximo
     */
    public static Boss getNextBoss() { 
        if(bossNextCounter >= bossList.length) 
            return null;
        
        final Boss _boss = bossList[bossNextCounter++];
        _boss.resetToBase();

        return _boss;
    }

    /** Reseta os chefões e o contador de próximo chefão */
    public static void resetBossNextCounter() { 
        bossList = createBosses();
        bossNextCounter = 0; 
    }
}
