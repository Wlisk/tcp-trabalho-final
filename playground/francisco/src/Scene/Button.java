package Scene;

import com.raylib.Raylib.Color;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib.Vector2;
import static com.raylib.Jaylib.GetMousePosition;
import static com.raylib.Jaylib.CheckCollisionPointRec;
import static com.raylib.Jaylib.IsMouseButtonDown;
import static com.raylib.Jaylib.MOUSE_BUTTON_LEFT;


public class Button {
    private Rectangle rectangle;
    private Color buttonColor;
    private Color mouseOverColor;
    private String text;
    private float textSize;
    private Color textColor;
    

    public Button(Rectangle rectangle, Color buttonColor, Color mouseOverColor, String text, int textSize, Color textColor){
        this.rectangle = rectangle;
        this.buttonColor = buttonColor;
        this.mouseOverColor = mouseOverColor;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    public boolean isMouseOver(){
        Vector2 mousePoint = GetMousePosition();    
        return CheckCollisionPointRec(mousePoint, rectangle);
    }

    public boolean isPressed(){
        if (isMouseOver() && IsMouseButtonDown(MOUSE_BUTTON_LEFT)){
            return true;
        }
        return false;
    }

    
    public Rectangle getRectangle(){
        return this.rectangle;
    }

    public Color getButtonColor(){
        return this.buttonColor;
    }

    public Color getMouseOverColor(){
        return this.mouseOverColor;
    }
    
    public String getText(){
        return this.text;
    }

    public float getTextSize(){
        return this.textSize;
    }

    public Color getTextColor(){
        return this.textColor;
    }    


}
