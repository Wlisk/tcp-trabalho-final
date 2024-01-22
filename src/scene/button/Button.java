package scene.button;

import com.raylib.Raylib;
import com.raylib.Jaylib;
import com.raylib.Raylib.Color;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib.Vector2;
import static com.raylib.Jaylib.GetMousePosition;
import static com.raylib.Jaylib.CheckCollisionPointRec;
import static com.raylib.Jaylib.IsMouseButtonDown;
import static com.raylib.Jaylib.MOUSE_BUTTON_LEFT;

import interfaces.IClickable;
import interfaces.IDrawable;
import interfaces.IMouseOverable;
import utils.Objects;

/**
 * Classe para gerenciar e desenhar botões na tela/janela 
 * @see interfaces.IDrawable
 * @see interfaces.IMouseOverable 
 * @see interfaces.IClickable
 */
public class Button 
implements IDrawable, IMouseOverable, IClickable 
{
    private Rectangle rectangle;
    private Color buttonColor;
    private Color mouseOverColor;
    private String text;
    private float textSize;
    private Color textColor;

    /**
     * Construtor do Button, inicializando ele com parametros diversos
     * para configuração de estilos
     * @param rectangle a posicação e o tamanho do botão na tela 
     * @param buttonColor a cor do botão 
     * @param mouseOverColor a cor do botão com hover (mouse por cima)
     * @param text o texto do botão
     * @param textSize o tamanho do texto no botão
     * @param textColor a cor do texto no botão
     */
    public Button(
        Rectangle rectangle, 
        Color buttonColor, Color mouseOverColor, 
        String text, int textSize, Color textColor
    ) 
    {
        this.rectangle = rectangle;
        this.buttonColor = buttonColor;
        this.mouseOverColor = mouseOverColor;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    /**
     * Verifica se o mouse está por cima do botão e retorna a posição do mouse em cima dele
     * @return (Vector2) a posição do mouse em cima do botão ou null caso contrário
     * @see interfaces.IMouseOverable 
     */
    @Override
    public Vector2 isMouseOver() {
        Vector2 mousePoint = GetMousePosition();  
        return CheckCollisionPointRec(mousePoint, rectangle) ? mousePoint : null;
    }

    /** 
     * Verifica se o mouse está pressionando o botão com o botão do mouse especificado
     * @param mousebutton qual dos botões do mouse para verificar 
     * @return (boolean) se o mouve está precionado ou não
     * @see interfaces.IClickable 
     */
    @Override 
    public boolean isMousePressed(int mouseButton) {
        return Objects.toBool(isMouseOver()) && IsMouseButtonDown(mouseButton);
    }

    /**
     * Verifica se o mouse está pressionando o botão com o botão do mouse esquerdo
     * @return (boolean) se o mouve está precionado ou não
     */
    public boolean isMousePressed() {
        return isMousePressed(MOUSE_BUTTON_LEFT);
    }
    
    /** Desenha o botão na tela */
    @Override
    public void draw() {
        Color _currColor = Objects.toBool(this.isMouseOver()) ? mouseOverColor : buttonColor;

        Jaylib.DrawRectangleRec(rectangle, _currColor);

        Raylib.Vector2 _windowTextSize = Jaylib.MeasureTextEx(
            Jaylib.GetFontDefault(), text, textSize, 1
        );

        float _middleXPos = (rectangle.width() / 2 ) - (_windowTextSize.x() / 2);
        float _middleYPos = (rectangle.height() / 2) - (_windowTextSize.y() / 2);

        float _centerX = rectangle.x() + _middleXPos;
        float _centerY = rectangle.y() + _middleYPos;

        Jaylib.DrawText(text, (int)_centerX, (int)_centerY, (int)textSize, textColor);
    }
}
