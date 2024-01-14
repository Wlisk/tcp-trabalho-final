package scene.inventory;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import interfaces.IMouseOverable;

public class InventorySlotUI implements IMouseOverable {
    private Jaylib.Rectangle rectangle, innerRec;
    private Raylib.Color backgroundColor, borderColor;

    public InventorySlotUI(Jaylib.Rectangle rectangle, 
                         int borderSize, 
                         Raylib.Color backgroundColor, 
                         Raylib.Color borderColor){
        this.rectangle = rectangle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
    }

    public void draw(Jaylib.Texture texture){ // Texture provided, draw inventory as well as item sprite
        draw();
        Jaylib.Rectangle sourceRec = new Jaylib.Rectangle(0f, 0f, texture.width(), texture.height());
        Jaylib.Vector2 origin = new Jaylib.Vector2(0f, 0f);
        Jaylib.DrawTexturePro(texture, sourceRec, innerRec, origin, 0, Jaylib.WHITE);
    }

    public void draw(){ // No texture provided, draw inventory slot itself
        Jaylib.DrawRectangleRec(rectangle, borderColor);
        Jaylib.DrawRectangleRec(innerRec, backgroundColor);
    }

    public boolean isMouseOver(){
        Raylib.Vector2 mousePos = Jaylib.GetMousePosition();
        return Jaylib.CheckCollisionPointRec(mousePos, rectangle);
    }

    public boolean isMouseLeftPressed() {
        return isMouseOver() && Jaylib.IsMouseButtonDown(Jaylib.MOUSE_BUTTON_LEFT);
    }

    public boolean isMouseRightPressed() {
        return isMouseOver() && Jaylib.IsMouseButtonDown(Jaylib.MOUSE_BUTTON_RIGHT);
    }

    public Jaylib.Rectangle getRec(){
        return this.rectangle;
    }
}
