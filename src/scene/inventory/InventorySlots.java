package scene.inventory;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Jaylib.Rectangle;

import items.Inventory;
import scene.statbox.MouseOverInventoryBox;

public class InventorySlots {
    private Rectangle rectangle;
    private Inventory inventory;
    private InventorySlot slots[];

    public InventorySlots(Inventory inventory, 
                          Jaylib.Rectangle rectangle, 
                          int borderSize, 
                          Raylib.Color backgroundColor, 
                          Raylib.Color borderColor){
        this.inventory = inventory;
        this.rectangle = rectangle;
        this.slots = new InventorySlot[Inventory.MAX_ITEMS + 2];
        
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            Jaylib.Rectangle curRec = new Jaylib.Rectangle(rectangle.x() + rectangle.width() * i, rectangle.y(), rectangle.width(), rectangle.height());
            this.slots[i] = new InventorySlot(curRec, borderSize, backgroundColor, borderColor);
        }
    }

    public void draw(){
        this.slots[0].draw(inventory.getEquipedWeapon());
        this.slots[1].draw(inventory.getEquipedArmor());
        for (int i = 2; i < Inventory.MAX_ITEMS + 2; i++){
            this.slots[i].draw(inventory.getItem(i));
        }

        drawMouseOver();
    }

    public void drawMouseOver(){
        Raylib.Vector2 mousePos = Jaylib.GetMousePosition();

        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            Jaylib.Rectangle curRec = new Jaylib.Rectangle(rectangle.x() + rectangle.width() * i, rectangle.y(), rectangle.width(), rectangle.height());
            if (Jaylib.CheckCollisionPointRec(mousePos, curRec)){
                switch (i){
                    case 0:
                        MouseOverInventoryBox.drawMouseOver(inventory.getEquipedWeapon());
                        break;
                    case 1:
                        MouseOverInventoryBox.drawMouseOver(inventory.getEquipedArmor());
                        break;
                    default:
                        MouseOverInventoryBox.drawMouseOver(inventory.getItem(i - 2));
                }
            }
        }
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
}
