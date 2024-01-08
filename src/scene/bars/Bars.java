package scene.bars;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;

import game.Game;

public class Bars {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final int 
        BAR_WIDTH = WINDOW_WIDTH / 6,
        BAR_HEIGHT = WINDOW_HEIGHT / 26,
        BAR_TEXT_SIZE = WINDOW_HEIGHT / 24,
        BAR_MARGIN = 4;
    
    private static final float 
        PLAYER_BARS_POS_X = WINDOW_WIDTH / 2 - 3 * BAR_WIDTH / 2,
        BOSS_BARS_POS_X = WINDOW_WIDTH / 2 + BAR_WIDTH / 2,
        BARS_POS_Y = WINDOW_HEIGHT * 0.05f;

    private static final Rectangle
        PLAYER_HEALTHBAR_REC = new Rectangle(PLAYER_BARS_POS_X, BARS_POS_Y, BAR_WIDTH, BAR_HEIGHT),
        PLAYER_MANABAR_REC = new Rectangle(PLAYER_BARS_POS_X, BARS_POS_Y + BAR_HEIGHT + BAR_MARGIN, BAR_WIDTH, BAR_HEIGHT),
        BOSS_HEALTHBAR_REC = new Rectangle(BOSS_BARS_POS_X, BARS_POS_Y, BAR_WIDTH, BAR_HEIGHT),
        BOSS_MANABAR_REC = new Rectangle(BOSS_BARS_POS_X, BARS_POS_Y + BAR_HEIGHT + BAR_MARGIN, BAR_WIDTH, BAR_HEIGHT);

    public static final Bar
        PLAYER_HEALTHBAR = new Bar(null, PLAYER_HEALTHBAR_REC, Jaylib.RED, Jaylib.LIGHTGRAY, BAR_TEXT_SIZE, Jaylib.WHITE),
        PLAYER_MANABAR = new Bar(null, PLAYER_MANABAR_REC, Jaylib.BLUE, Jaylib.LIGHTGRAY, BAR_TEXT_SIZE, Jaylib.WHITE),
        BOSS_HEALTHBAR = new Bar(null, BOSS_HEALTHBAR_REC, Jaylib.RED, Jaylib.LIGHTGRAY, BAR_TEXT_SIZE, Jaylib.WHITE),
        BOSS_MANABAR = new Bar(null, BOSS_MANABAR_REC, Jaylib.BLUE, Jaylib.LIGHTGRAY, BAR_TEXT_SIZE, Jaylib.WHITE);

        

}
