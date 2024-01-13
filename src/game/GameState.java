package game;

public enum GameState {
    MAIN_MENU(0), 
    SELECTING_CLASS(0), 
    BATTLE_START(2), 
    TURN_START(0.5), 
    TURN_PLAYER_CHOOSE(0.2), 
    TURN_PLAYER_CHOSEN(1), 
    TURN_ENEMY_CHOOSE(0), 
    TURN_ENEMY_CHOSEN(1), 
    TURN_END(0), 
    BATTLE_END(1), 
    GAME_END(5);

    private final double transitionDelay; // Transition delay is the minimum delay before the logic will progress after entering a state.

    GameState(double transitionDelay){
        this.transitionDelay = transitionDelay;
    }

    public double getDelay(){
        return this.transitionDelay;
    }

    @Override
    public String toString() {
        switch(this) {
            case MAIN_MENU: return "Main Menu";
            case SELECTING_CLASS: return "Class Selection";
            default: return "";
        }
    }
}
