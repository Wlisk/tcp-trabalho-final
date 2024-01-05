package scene.button;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;

import entities.player.ClassType;
import entities.player.ClassTypeUtil;
import exceptions.UnknownTypeException;
import game.Game;

public class Buttons {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    public static final int 
        BUTTON_WIDTH = (int)(WINDOW_WIDTH / 4), 
        BUTTON_HEIGHT = (int)(WINDOW_HEIGHT / 8),
        FONT_SIZE_BUTTONS = (int)(WINDOW_HEIGHT / 20);

    private static final float 
        BUTTON_PLAY_POS_Y = WINDOW_HEIGHT * 0.5f, 
        BUTTON_EXIT_POS_Y = WINDOW_HEIGHT * 0.75f,
        BUTTON_CLASS_SEL_POS_Y = WINDOW_HEIGHT * 0.8f;

    private static final float CENTER_WIDTH = (WINDOW_WIDTH / 2) - (BUTTON_WIDTH / 2);


    //*************************** PLAY BUTTON ***************************//
    private static final Rectangle buttonPlayRectangle = new Rectangle(
        CENTER_WIDTH, 
        BUTTON_PLAY_POS_Y, 
        (float)BUTTON_WIDTH, 
        (float)BUTTON_HEIGHT
    );
    public static final Button PLAY_BUTTON = new Button(
        buttonPlayRectangle, 
        Jaylib.GRAY, 
        Jaylib.LIGHTGRAY, 
        "PLAY", 
        FONT_SIZE_BUTTONS, 
        Jaylib.RED
    );

    //*************************** EXIT BUTTON ***************************//
    private static final Rectangle buttonExitRectangle = new Rectangle(
        CENTER_WIDTH, 
        BUTTON_EXIT_POS_Y, 
        (float)BUTTON_WIDTH, 
        (float)BUTTON_HEIGHT
    );
    public static final Button EXIT_BUTTON = new Button(
        buttonExitRectangle, 
        Jaylib.GRAY, 
        Jaylib.LIGHTGRAY, 
        "EXIT", 
        FONT_SIZE_BUTTONS, 
        Jaylib.RED
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
                (float)BUTTON_WIDTH, 
                (float)BUTTON_HEIGHT
            );

            _buttons[i++] = new Button(
                _buttonClassSelRectangle, 
                Jaylib.GRAY, Jaylib.
                LIGHTGRAY, 
                _buttonText, 
                FONT_SIZE_BUTTONS, 
                Jaylib.RED
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
}
