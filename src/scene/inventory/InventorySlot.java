package scene.inventory;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import items.Item;

public class InventorySlot {
    private Jaylib.Rectangle rectangle, innerRec;
    private Raylib.Color backgroundColor, borderColor;

    public InventorySlot(Jaylib.Rectangle rectangle, 
                         int borderSize, 
                         Raylib.Color backgroundColor, 
                         Raylib.Color borderColor){
        this.rectangle = rectangle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
    }

    public void draw(Item item){
        Jaylib.DrawRectangleRec(rectangle, borderColor);
        Jaylib.DrawRectangleRec(innerRec, backgroundColor);
        
        // TODO: draw item sprite
    }


}
