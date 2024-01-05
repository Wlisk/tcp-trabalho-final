package scene.statbox;

import game.Game;
import com.raylib.Jaylib;

public class Statboxes {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final float
        BOX_WIDTH = WINDOW_WIDTH / 6,
        BOX_HEIGHT = WINDOW_HEIGHT / 3,
        PLAYER_BOX_POS_X = 0,
        PLAYER_BOX_POS_Y = 0, // Top left corner
        BOSS_BOX_POS_X = WINDOW_WIDTH - BOX_WIDTH, // Top right corner
        BOSS_BOX_POS_Y = 0;
        

    private static final int
        BORDER_SIZE = 2;

    private static final Jaylib.Rectangle 
        PLAYER_STATBOX_REC = new Jaylib.Rectangle(PLAYER_BOX_POS_X, PLAYER_BOX_POS_Y, BOX_WIDTH, BOX_HEIGHT),
        BOSS_STATBOX_REC = new Jaylib.Rectangle(BOSS_BOX_POS_X, BOSS_BOX_POS_Y, BOX_WIDTH, BOX_HEIGHT);

    public static final PlayerStatBox PLAYER_STATBOX = new PlayerStatBox(PLAYER_STATBOX_REC, BORDER_SIZE, Jaylib.WHITE, Jaylib.BLACK, Jaylib.WHITE);
    public static final BossStatBox BOSS_STATBOX = new BossStatBox(BOSS_STATBOX_REC, BORDER_SIZE, Jaylib.WHITE, Jaylib.BLACK, Jaylib.WHITE);
}
