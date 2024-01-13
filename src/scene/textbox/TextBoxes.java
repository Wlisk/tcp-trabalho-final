package scene.textbox;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import game.Game;

public class TextBoxes {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;
    
    private static final int
        FONT_SIZE = WINDOW_HEIGHT / 20,
        BORDER_SIZE = 4;

    private static final float
        BOX_WIDTH = WINDOW_WIDTH / 2,
        BOX_HEIGHT = WINDOW_HEIGHT / 10;

    private static final float
        BOX_POS_X = WINDOW_WIDTH / 2 - BOX_WIDTH / 2,
        BOX_POS_Y = WINDOW_HEIGHT / 2 - BOX_HEIGHT / 2;

    private static final Raylib.Color
        COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        COLOR_BORDER = Jaylib.GetColor(1631265023),
        COLOR_TEXT = Jaylib.WHITE;


    private static final Jaylib.Rectangle
        ALERT_TEXTBOX_REC = new Jaylib.Rectangle(BOX_POS_X, BOX_POS_Y, BOX_WIDTH, BOX_HEIGHT);

    
    public static final TextBox
        ALERT_TEXTBOX = new TextBox(ALERT_TEXTBOX_REC, FONT_SIZE, BORDER_SIZE, COLOR_TEXT, COLOR_BACKGROUND, COLOR_BORDER);

    public static final String
        BATTLE_START = "FIGHT!",
        ATTACK_MISSED = "MISS!",
        GAME_OVER_LOSS = "YOU DIED!",
        GAME_OVER_WIN = "YOU WIN!";

    

}
