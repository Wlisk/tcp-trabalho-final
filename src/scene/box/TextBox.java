package scene.box;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Rectangle;
import com.raylib.Jaylib.Vector2;

import config.Config;
import interfaces.IDrawable;

/** 
 * Classe para mostrar caixas de mensagens de texto na tela 
 * @see interfaces.IDrawable
 */
public class TextBox implements IDrawable {
    // overlay panel
    private final Rectangle rectBackPanel = new Jaylib.Rectangle(
        0.0f,
        0.0f,
        Config.WINDOW_WIDTH,
        Config.WINDOW_HEIGHT
    );

    // border rectangle 
    private final Rectangle rectOuterBox = new Jaylib.Rectangle(
        Config.TEXTBOX_POSX,
        Config.TEXTBOX_POSY,
        Config.TEXTBOX_WIDTH,
        Config.TEXTBOX_HEIGHT
    );

    // content rectangle 
    private final Rectangle rectInnerBox = new Jaylib.Rectangle(
        rectOuterBox.x() + Config.TEXTBOX_BORDER_SIZE, 
        rectOuterBox.y() + Config.TEXTBOX_BORDER_SIZE, 
        rectOuterBox.width() - (Config.TEXTBOX_BORDER_SIZE * 2), 
        rectOuterBox.height() - (Config.TEXTBOX_BORDER_SIZE * 2)
    );

    private String text;
    private int duration;
    private Color backgroundColor;
    private boolean showBackPanel = true;

    /** Desenha a caixa de mensagem e seu conteúdo */
    @Override
    public void draw() {
        if (duration-- > 0) {
            if(showBackPanel) 
                Jaylib.DrawRectangleRec(rectBackPanel, Config.TEXTBOX_COLOR_BACKPANEL);
            Jaylib.DrawRectangleRec(rectOuterBox, Config.TEXTBOX_COLOR_BORDER);
            Jaylib.DrawRectangleRec(rectInnerBox, backgroundColor);

            Raylib.Vector2 _windowTextSize = Jaylib.MeasureTextEx(
                Config.TEXTBOX_FONT, 
                text, 
                Config.TEXTBOX_FONT_SIZE, 
                Config.TEXTBOX_FONT_SPACING
            );

            final float _middleXPos = (rectOuterBox.width() / 2 ) - (_windowTextSize.x() / 2);
            final float _middleYPos = (rectOuterBox.height() / 2) - (_windowTextSize.y() / 2);

            final float _centerX = rectOuterBox.x() + _middleXPos;
            final float _centerY = rectOuterBox.y() + _middleYPos;
            final Vector2 _centerPos = new Vector2(_centerX, _centerY);

            Jaylib.DrawTextEx(
                Config.TEXTBOX_FONT, 
                text, 
                _centerPos, 
                Config.TEXTBOX_FONT_SIZE, 
                Config.TEXTBOX_FONT_SPACING, 
                Config.TEXTBOX_COLOR_TEXT
            );
        }
    }

    /**
     * Configura uma nova mensagem a ser mostrada com cor de fundo diferente sem overlay
     * @param text o nova mensagem 
     * @param duration a duração da mensagem na tela 
     * @param color a cor de fundo 
     */
    public void newMessage(String text, int duration, Color color) {
        this.text = text;
        this.duration = duration;
        backgroundColor = color;
        showBackPanel = false;
    }

    /**
     * Configura uma nova mensagem a ser mostrada com cor de fundo padrão com overlay
     * @param text o nova mensagem 
     * @param duration a duração da mensagem na tela 
     */
    public void newMessage(String text, int duration) {
        newMessage(text, duration, Config.TEXTBOX_COLOR_BACKGROUND);
        showBackPanel = true;
    }
}
