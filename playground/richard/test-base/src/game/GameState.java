package game;

public enum GameState {
    MAIN_MENU, 
    SELECTING_CLASS, 
    BATTLE_START, 
    TURN_START, 
    TURN_PLAYER_CHOOSE, 
    TURN_PLAYER_CHOSEN, 
    TURN_ENEMY_CHOOSE, 
    TURN_ENEMY_CHOSEN, 
    TURN_END, 
    BATTLE_END, 
    GAME_END;

    @Override
    public String toString() {
        switch(this) {
            case MAIN_MENU: return "Main Menu";
            case SELECTING_CLASS: return "Class Selection";
            default: return "";
        }
    }
}
