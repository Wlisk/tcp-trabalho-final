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

public class Button 
implements IDrawable, IMouseOverable, IClickable 
{
    private Rectangle rectangle;
    private Color buttonColor;
    private Color mouseOverColor;
    private String text;
    private float textSize;
    private Color textColor;

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

    public boolean isMouseOver() {
        Vector2 mousePoint = GetMousePosition();    
        return CheckCollisionPointRec(mousePoint, rectangle);
    }

    public boolean isMousePressed() {
        return isMouseOver() && IsMouseButtonDown(MOUSE_BUTTON_LEFT);
    }
    
    public Rectangle getRectangle() { return this.rectangle; }
    public Color getButtonColor() { return this.buttonColor; }
    public Color getMouseOverColor() { return this.mouseOverColor; }

    public String getText() { return this.text; }
    public float getTextSize() { return this.textSize; }
    public Color getTextColor() { return this.textColor; }
    
    public void draw() {
        Color _currColor = this.isMouseOver() ? mouseOverColor : buttonColor;

        Jaylib.DrawRectangleRec(rectangle, _currColor);

        Raylib.Vector2 _windowTextSize = Jaylib.MeasureTextEx(
            Jaylib.GetFontDefault(), text, textSize, 0
        );

        float _middleXPos = (rectangle.width() / 2 ) - (_windowTextSize.x() / 2);
        float _middleYPos = (rectangle.height() / 2) - (_windowTextSize.y() / 2);

        float _centerX = rectangle.x() + _middleXPos;
        float _centerY = rectangle.y() + _middleYPos;

        Jaylib.DrawText(text, (int)_centerX, (int)_centerY, (int)textSize, textColor);
    }
}
