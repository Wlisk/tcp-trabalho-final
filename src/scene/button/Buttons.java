package scene.button;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib.Color;

import entities.player.ClassType;
import java.util.HashMap;
import static config.Config.WINDOW_WIDTH;
import static config.Config.WINDOW_HEIGHT;

/** Classe \"estática\" para armazena os botões do jogo */
public class Buttons {
    private static final int 
        MENU_BUTTON_WIDTH = WINDOW_WIDTH / 4, 
        MENU_BUTTON_HEIGHT = WINDOW_HEIGHT / 8,
        MENU_BUTTON_FONT_SIZE = WINDOW_HEIGHT / 20;

    private static final float 
        BUTTON_PLAY_POS_Y = WINDOW_HEIGHT * 0.5f, 
        BUTTON_EXIT_POS_Y = WINDOW_HEIGHT * 0.75f,
        BUTTON_CLASS_SEL_POS_Y = WINDOW_HEIGHT * 0.8f;

    private static final float BUTTON_CENTER_X = (WINDOW_WIDTH / 2) - (MENU_BUTTON_WIDTH / 2);

    private static final Color
        // RBG + Alpha unsigned hex values converted to integers
        COLOR_MOUSEOVER = Jaylib.GetColor(-225094913), 
        COLOR_BUTTON = Jaylib.GetColor(-1772078593),
        COLOR_TEXT = Jaylib.WHITE;

    //*************************** PLAY BUTTON ***************************//
    private static final Rectangle buttonPlayRectangle = new Rectangle(
        BUTTON_CENTER_X, 
        BUTTON_PLAY_POS_Y, 
        (float)MENU_BUTTON_WIDTH, 
        (float)MENU_BUTTON_HEIGHT
    );

    /** 
     * Cria o botão \"PLAY\"
     * @return (Button) o botão
     */
    public static final Button newPlayButton() { 
        return new Button(
            buttonPlayRectangle, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER, 
            "PLAY", 
            MENU_BUTTON_FONT_SIZE, 
            COLOR_TEXT
        ); 
    }

    //*************************** EXIT BUTTON ***************************//
    private static final Rectangle buttonExitRectangle = new Rectangle(
        BUTTON_CENTER_X, 
        BUTTON_EXIT_POS_Y, 
        (float)MENU_BUTTON_WIDTH, 
        (float)MENU_BUTTON_HEIGHT
    );

    /** 
     * Cria o botão \"EXIT\"
     * @return (Button) o botão
     */
    public static final Button newExitButton() { 
        return new Button(
            buttonExitRectangle, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER,  
            "EXIT", 
            MENU_BUTTON_FONT_SIZE, 
            COLOR_TEXT
        ); 
    }

    //*************************** PLAYER CLASS BUTTONS ***************************//
    private static final float BUTTONS_START_DISTANCEX = 0.1f;
    private static final float BUTTONS_BETWEEN_DISTANCEX = 0.3f;

    /** 
     * Cria os botões para selecionar a classe do jogador
     * @return (HashMap) a lista dos botões como hash
     */
    public static final HashMap<ClassType, Button> newClassButtons() {
        final HashMap<ClassType, Button> _buttons = new HashMap<ClassType, Button>();
        float _distanceX = BUTTONS_START_DISTANCEX;

        for(ClassType _classType: ClassType.values()) {
            final Rectangle _buttonClassSelRectangle = new Rectangle(
                WINDOW_WIDTH * _distanceX, 
                BUTTON_CLASS_SEL_POS_Y, 
                (float)MENU_BUTTON_WIDTH, 
                (float)MENU_BUTTON_HEIGHT
            );

            final Button _button = new Button(
                _buttonClassSelRectangle, 
                COLOR_BUTTON,COLOR_MOUSEOVER, 
                _classType.getTypeName(),
                MENU_BUTTON_FONT_SIZE, 
                COLOR_TEXT
            );

            _buttons.put(_classType, _button);

            _distanceX += BUTTONS_BETWEEN_DISTANCEX;
        }
        return _buttons;
    }

    //*************************** FIGHT BUTTONS ***************************//
    private static final int 
        ACTION_BUTTON_WIDTH = WINDOW_WIDTH / 6, 
        ACTION_BUTTON_HEIGHT = WINDOW_HEIGHT / 10,
        ACTION_BUTTON_FONT_SIZE = WINDOW_HEIGHT / 20;

    private static final float 
        ACTION_BUTTONS_POS_Y = 0.6f * WINDOW_HEIGHT,                    
        ATTACK_BUTTON_POS_X = 0.05f * WINDOW_WIDTH,               
        SPECIAL_BUTTON_POS_X = 0.22f * WINDOW_WIDTH,                   
        DEFEND_BUTTON_POS_X = 0.39f * WINDOW_WIDTH;

    private static final Rectangle 
        ATTACK_BUTTON_REC = new Rectangle(
            ATTACK_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y, 
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT
        ),
        SPECIAL_BUTTON_REC = new Rectangle(
            SPECIAL_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y,
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT
        ),
        DEFEND_BUTTON_REC = new Rectangle(
            DEFEND_BUTTON_POS_X, 
            ACTION_BUTTONS_POS_Y,
            (float)ACTION_BUTTON_WIDTH, 
            (float)ACTION_BUTTON_HEIGHT
        );

    /** 
     * Cria o botão de ataque do jogador
     * @return (Button) o botão de ataque
     */
    public static final Button newAttackButton() { 
        return new Button(
            ATTACK_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER,  
            "ATTACK", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT
        ); 
    }

    /** 
     * Cria o botão de ataque especial do jogador
     * @return (Button) o botão de ataque especial
     */
    public static final Button newSpecialButton() { 
        return new Button(
            SPECIAL_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER, 
            "SPECIAL", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT
        ); 
    }

    /** 
     * Cria o botão de defesa do jogador
     * @return (Button) o botão de defesa
     */
    public static final Button newDefendButton() { 
        return new Button(
            DEFEND_BUTTON_REC, 
            COLOR_BUTTON, 
            COLOR_MOUSEOVER, 
            "DEFEND", 
            ACTION_BUTTON_FONT_SIZE, 
            COLOR_TEXT
        ); 
    }
}
