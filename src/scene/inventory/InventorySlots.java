package scene.inventory;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import items.Inventory;
import scene.statbox.MouseOverInventoryBox;

public class InventorySlots {
    private Inventory inventory;
    private InventorySlot slots[];

    public InventorySlots(Inventory inventory, 
                          Jaylib.Rectangle rectangle, 
                          int borderSize, 
                          Raylib.Color backgroundColor, 
                          Raylib.Color borderColor){
        this.inventory = inventory;
        this.slots = new InventorySlot[Inventory.MAX_ITEMS + 2];
        
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            Jaylib.Rectangle curRec = new Jaylib.Rectangle(rectangle.x() + rectangle.width() * i, rectangle.y(), rectangle.width(), rectangle.height());
            this.slots[i] = new InventorySlot(curRec, borderSize, backgroundColor, borderColor);
        }
    }

    public void draw(){
        this.slots[0].draw(inventory.getEquippedWeapon());
        this.slots[1].draw(inventory.getEquippedArmor());
        for (int i = 2; i < Inventory.MAX_ITEMS + 2; i++){
            this.slots[i].draw(inventory.getItem(i));
        }

        drawMouseOver();
    }

    public void drawMouseOver(){
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            if (slots[i].isMouseOver()){
                switch (i){
                    case 0:
                        MouseOverInventoryBox.drawMouseOver(inventory.getEquippedWeapon());
                        break;
                    case 1:
                        MouseOverInventoryBox.drawMouseOver(inventory.getEquippedArmor());
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

    public int checkClickedIndex(){
        // Returns the index of a slot being clicked by the mouse. -1 if none are being clicked.
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            if (slots[i].isMousePressed()){
                System.out.println(i);
                return i;
            }
        }

        return -1;
    }
}
