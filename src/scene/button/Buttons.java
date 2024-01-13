package scene.button;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib;

import entities.player.ClassType;
import entities.player.ClassTypeUtil;
import exceptions.UnknownTypeException;
import game.Game;

public class Buttons {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    public static final int 
        MENU_BUTTON_WIDTH = (int)(WINDOW_WIDTH / 4), 
        MENU_BUTTON_HEIGHT = (int)(WINDOW_HEIGHT / 8),
        MENU_BUTTON_FONT_SIZE = (int)(WINDOW_HEIGHT / 20);

    private static final float 
        BUTTON_PLAY_POS_Y = WINDOW_HEIGHT * 0.5f, 
        BUTTON_EXIT_POS_Y = WINDOW_HEIGHT * 0.75f,
        BUTTON_CLASS_SEL_POS_Y = WINDOW_HEIGHT * 0.8f;

    private static final float BUTTON_CENTER_X = (WINDOW_WIDTH / 2) - (MENU_BUTTON_WIDTH / 2);

    private static final Raylib.Color
        COLOR_MOUSEOVER = Jaylib.GetColor(-225094913), // RBG + Alpha unsigned hex values converted to integers
        COLOR_BUTTON = Jaylib.GetColor(-1772078593),
        COLOR_TEXT = Jaylib.WHITE;


    //*************************** PLAY BUTTON ***************************//
    private static final Rectangle buttonPlayRectangle = new Rectangle(
        BUTTON_CENTER_X, 
        BUTTON_PLAY_POS_Y, 
        (float)MENU_BUTTON_WIDTH, 
        (float)MENU_BUTTON_HEIGHT
    );
    public static final Button PLAY_BUTTON = new Button(
        buttonPlayRectangle, 
        COLOR_BUTTON, 
        COLOR_MOUSEOVER, 
        "PLAY", 
        MENU_BUTTON_FONT_SIZE, 
        COLOR_TEXT
    );

    //*************************** EXIT BUTTON ***************************//
    private static final Rectangle buttonExitRectangle = new Rectangle(
        BUTTON_CENTER_X, 
        BUTTON_EXIT_POS_Y, 
        (float)MENU_BUTTON_WIDTH, 
        (float)MENU_BUTTON_HEIGHT
    );
    public static final Button EXIT_BUTTON = new Button(
        buttonExitRectangle, 
        COLOR_BUTTON, 
        COLOR_MOUSEOVER,  
        "EXIT", 
        MENU_BUTTON_FONT_SIZE, 
        COLOR_TEXT
    );

    //*************************** PLAYER CLASS BUTTONS ***************************//
    private static final float BUTTONS_START_DISTANCEX = 0.1f;
    private static final float BUTTONS_BETWEEN_DISTANCEX = 0.3f;

    private static Button[] createClassButtons() {
        final Button[] _buttons = new Button[ClassType.size()];
        float _distanceX = BUTTONS_START_DISTANCEX;

        int i = 0;
        for(String _buttonText: ClassType.getStrings()) {
            final Rectangle _buttonClassSelRectangle = new Rectangle(
                WINDOW_WIDTH * _distanceX, 
                BUTTON_CLASS_SEL_POS_Y, 
                (float)MENU_BUTTON_WIDTH, 
                (float)MENU_BUTTON_HEIGHT
            );

            _buttons[i++] = new Button(
                _buttonClassSelRectangle, 
                COLOR_BUTTON,
                COLOR_MOUSEOVER, 
                _buttonText, 
                MENU_BUTTON_FONT_SIZE, 
                COLOR_TEXT
            );

            _distanceX += BUTTONS_BETWEEN_DISTANCEX;
        }

        return _buttons;
    }

    public static final Button[] CLASS_BUTTONS = createClassButtons();

    public static Button getClassButton(ClassType classType) throws UnknownTypeException {
        switch(classType) {
            case MAGE: return CLASS_BUTTONS[ClassType.MAGE.getIndex()];
            case WARRIOR: return CLASS_BUTTONS[ClassType.WARRIOR.getIndex()];
            case ARCHER: return CLASS_BUTTONS[ClassType.ARCHER.getIndex()];
            default: 
                throw new UnknownTypeException(ClassTypeUtil.ERR_TYPE_MESSAGE);
        }
    }

    //*************************** FIGHT BUTTONS ***************************//
    public static final int 
        ACTION_BUTTON_WIDTH = (int)(WINDOW_WIDTH / 6), 
        ACTION_BUTTON_HEIGHT = (int)(WINDOW_HEIGHT / 10),
        ACTION_BUTTON_FONT_SIZE = (int)(WINDOW_HEIGHT / 20);

    private static final float ACTION_BUTTONS_POS_Y = 0.6f * WINDOW_HEIGHT,
                               ATTACK_BUTTON_POS_X = 0.05f * WINDOW_WIDTH,
                               SPECIAL_BUTTON_POS_X = 0.22f * WINDOW_WIDTH,
                               DEFEND_BUTTON_POS_X = 0.39f * WINDOW_WIDTH;

    private static final Rectangle 
        ATTACK_BUTTON_REC = new Rectangle(
            ATTACK_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y, 
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT),
        SPECIAL_BUTTON_REC = new Rectangle(
            SPECIAL_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y,
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT),
        DEFEND_BUTTON_REC = new Rectangle(
            DEFEND_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y,
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT);

    public static final Button 
        ATTACK_BUTTON = new Button(
            ATTACK_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER,  
            "ATTACK", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT),
        SPECIAL_BUTTON = new Button(
            SPECIAL_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER, 
            "SPECIAL", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT),
        DEFEND_BUTTON = new Button(
            DEFEND_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER, 
            "DEFEND", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT);
}
