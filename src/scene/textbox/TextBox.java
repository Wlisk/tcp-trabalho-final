package scene.textbox;

import com.raylib.Jaylib;
import com.raylib.Raylib;

public class TextBox {
    private Jaylib.Rectangle rectangle, innerRec;
    private int duration, fontSize;
    private String text;
    private Raylib.Color textColor, backgroundColor, borderColor;

    public TextBox(Jaylib.Rectangle rectangle, 
                   int fontSize, 
                   int borderSize, 
                   Raylib.Color textColor, 
                   Raylib.Color backgroundColor, 
                   Raylib.Color borderColor){
        this.rectangle = rectangle;
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
    }

    public void draw(){
        if (duration-- > 0){
            Jaylib.DrawRectangleRec(rectangle, borderColor);
            Jaylib.DrawRectangleRec(innerRec, backgroundColor);

            Raylib.Vector2 _windowTextSize = Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), text, fontSize, 0);
        

            float _middleXPos = (rectangle.width() / 2 ) - (_windowTextSize.x() / 2);
            float _middleYPos = (rectangle.height() / 2) - (_windowTextSize.y() / 2);

            float _centerX = rectangle.x() + _middleXPos;
            float _centerY = rectangle.y() + _middleYPos;
            Jaylib.DrawText(text, (int)_centerX, (int)_centerY, fontSize, textColor);
        }
    }

    public void newMessage(String text, int duration){
        this.text = text;
        this.duration = duration;
    }
}
