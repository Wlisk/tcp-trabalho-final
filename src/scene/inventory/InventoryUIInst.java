package scene.inventory;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import game.Game;
import items.Inventory;

public class InventoryUIInst {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final float
        SLOT_SIDE = WINDOW_WIDTH / 12,
        SLOT_POS_X = 0, // Left of screen
        SLOT_POS_Y = WINDOW_HEIGHT - SLOT_SIDE; // Bottom of screen
    
    private static final int
        BORDER_SIZE = 4;

    private static final Raylib.Color
        COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        COLOR_BORDER = Jaylib.GetColor(1631265023);

    private static Jaylib.Rectangle REC = new Jaylib.Rectangle(SLOT_POS_X, SLOT_POS_Y, SLOT_SIDE, SLOT_SIDE);

    public static final InventoryUI newInventoryUI(Inventory inventory) { return new InventoryUI(inventory, REC, BORDER_SIZE, COLOR_BACKGROUND, COLOR_BORDER);}
}
