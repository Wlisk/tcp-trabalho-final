package scene.inventory;

import com.raylib.Jaylib;

import game.Game;

public class InventorySlotInst {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final float
        SLOT_SIDE = WINDOW_WIDTH / 12,
        SLOT_POS_X = 0, // Left of screen
        SLOT_POS_Y = WINDOW_HEIGHT - SLOT_SIDE; // Bottom of screen
    
    private static final int
        BORDER_SIZE = 2;

    private static Jaylib.Rectangle REC = new Jaylib.Rectangle(SLOT_POS_X, SLOT_POS_Y, SLOT_SIDE, SLOT_SIDE);

    public static final InventorySlots INVENTORY_SLOTS = new InventorySlots(null, REC, BORDER_SIZE, Jaylib.BLACK, Jaylib.WHITE);
}
