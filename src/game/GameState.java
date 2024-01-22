package game;

/** 
 * Enum para os estados do jogo.
 * <p>
 * Além disso, armazena o delay entre troca de estados.
 * @see java.lang.Enum
 */
public enum GameState {
    /** Estado: menu inicial */
    MAIN_MENU(0), 
    /** Estado: menu de selação de classe do jogador */
    SELECTING_CLASS(0), 
    /** Estado: inicio de uma batalha Player x Boss */
    BATTLE_START(1.5), 
    /** Estado: inicio dos turnos Player x Boss */
    TURN_START(0.5), 
    /** Estado: jogador escolhe ação */
    TURN_PLAYER_CHOOSE(0.2), 
    /** Estado: jogador escolheu ação */
    TURN_PLAYER_CHOSEN(1),
    /** Estado: chefão escolhe ação */ 
    TURN_ENEMY_CHOOSE(0), 
    /** Estado: chefão escolheu ação */
    TURN_ENEMY_CHOSEN(1), 
    /** Estado: fim do turno */
    TURN_END(0), 
    /** Estado: fim da batalha Player x Boss */
    BATTLE_END(1), 
    /** Estado: fim do jogo */
    GAME_END(5);

    // Transition delay is the minimum delay before the 
    // logic will progress after entering a state.
    private final double transitionDelay; 

    /**
     * Construtor interno do Enum para setar valores para o tipo de estado
     * @param transitionDelay delay de troca entre estados
     */
    GameState(double transitionDelay) {
        this.transitionDelay = transitionDelay;
    }

    /**
     * Retorna o delay de troca entre estados
     * @return (double) o delay de troca
     */
    public double getDelay() {
        return this.transitionDelay;
    }

    /**
     * Retorna os textos dos estados que representam menus
     * @return (String) o texto do menu
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch(this) {
            case MAIN_MENU: return "Main Menu";
            case SELECTING_CLASS: return "Class Selection";
            default: return "";
        }
    }
}
