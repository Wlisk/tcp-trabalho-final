package scene.inventory;

import java.util.HashMap;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import items.Inventory;
import scene.TextureId;
import scene.statbox.MouseOverInventoryBox;
import items.armor.Armor;
import items.weapon.Weapon;
import items.Item;

public class InventoryUI {
    private Inventory inventory;
    private InventorySlotUI slots[];
    private MouseOverInventoryBox mouseOverBox;

    public InventoryUI(Inventory inventory, 
                          Jaylib.Rectangle rectangle, 
                          int borderSize, 
                          Raylib.Color backgroundColor, 
                          Raylib.Color borderColor){
        this.inventory = inventory;
        this.slots = new InventorySlotUI[Inventory.MAX_ITEMS + 2];
        
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            Jaylib.Rectangle curRec = new Jaylib.Rectangle(rectangle.x() + rectangle.width() * i, rectangle.y(), rectangle.width(), rectangle.height());
            this.slots[i] = new InventorySlotUI(curRec, borderSize, backgroundColor, borderColor);
        }

        mouseOverBox = new MouseOverInventoryBox();
    }

    public void draw(HashMap<TextureId, Jaylib.Texture> textures){
        Armor armor = inventory.getEquippedArmor();
        Weapon weapon = inventory.getEquippedWeapon();

        this.slots[0].draw(textures.get(armor.getTextureId()));
        this.slots[1].draw(textures.get(weapon.getTextureId()));
        for (int i = 2; i < Inventory.MAX_ITEMS + 2; i++){
            Item item = inventory.getItem(i - 2);

            if (item != null){ // Making sure inventory slot isn't empty
                this.slots[i].draw(textures.get(item.getTextureId()));
            } else {
                this.slots[i].draw();
            }
            
        }

        drawMouseOver();
    }

    public void drawMouseOver(){
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            if (slots[i].isMouseOver()){
                switch (i){
                    case 0:
                        mouseOverBox.drawMouseOver(inventory.getEquippedArmor());
                        break;
                    case 1:
                        mouseOverBox.drawMouseOver(inventory.getEquippedWeapon());
                        break;
                    default:
                        mouseOverBox.drawMouseOver(inventory.getItem(i - 2));
                }
            }
        }
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public int checkLeftClickedIndex(){
        // Returns the index of a slot being clicked by the mouse. -1 if none are being clicked.
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            if (slots[i].isMouseLeftPressed()){
                return i;
            }
        }
        return -1;
    }

    public int checkRightClickedIndex(){
        // Returns the index of a slot being clicked by the mouse. -1 if none are being clicked.
        for (int i = 0; i < Inventory.MAX_ITEMS + 2; i++){
            if (slots[i].isMouseRightPressed()){
                return i;
            }
        }
        return -1;
    }
}
