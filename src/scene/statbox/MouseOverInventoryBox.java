package scene.statbox;

import game.Game;
import items.consumable.Consumable;
import items.weapon.Weapon;
import items.armor.Armor;
import items.Item;
import items.weapon.WeaponType;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import items.ItemType;

public class MouseOverInventoryBox {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final float
        BOX_WIDTH = WINDOW_WIDTH / 2,
        BOX_HEIGHT = WINDOW_HEIGHT / 3;

    private static final Raylib.Color
        BORDER_COLOR = Jaylib.WHITE,
        BACKGROUND_COLOR = Jaylib.BLACK,
        TEXT_COLOR = Jaylib.WHITE;

    private static final int
        FONT_SIZE = WINDOW_HEIGHT / 26,
        BORDER_SIZE = 4,
        MARGIN = 2;

    private static final String
        DMG_TEXT = "DMG: ",
        DEF_TEXT = "DEF: ",
        ACC_TEXT = "ACC: ",
        CRITCHANCE_TEXT = "CRT%: ", 
        CRITMULT_TEXT = "CRT*: ",
        HP_TEXT = "HP: ",
        MP_TEXT = "MP: ";

    private static Jaylib.Rectangle getOuterRectangle(){
        Raylib.Vector2 mousePos = Jaylib.GetMousePosition();
        Jaylib.Rectangle outerRec;
        if (mousePos.x() <= WINDOW_WIDTH - BOX_WIDTH){
            outerRec = new Jaylib.Rectangle(mousePos.x(), 
                                            mousePos.y() - BOX_HEIGHT, 
                                            BOX_WIDTH, 
                                            BOX_HEIGHT);
        } else {
            outerRec = new Jaylib.Rectangle(mousePos.x() - BOX_WIDTH, 
                                            mousePos.y() - BOX_HEIGHT, 
                                            BOX_WIDTH, 
                                            BOX_HEIGHT);
        }

        return outerRec;
    }

    private static Jaylib.Rectangle getInnerRectangle(Jaylib.Rectangle outerRec){
        return new Jaylib.Rectangle(outerRec.x() + BORDER_SIZE, outerRec.y() + BORDER_SIZE, BOX_WIDTH - BORDER_SIZE * 2, BOX_HEIGHT - BORDER_SIZE * 2);
    }

    private static void drawBox(){
        Jaylib.Rectangle outerRec = getOuterRectangle();
        Jaylib.Rectangle innerRec = getInnerRectangle(outerRec);

        Jaylib.DrawRectangleRec(outerRec, BORDER_COLOR);
        Jaylib.DrawRectangleRec(innerRec, BACKGROUND_COLOR);
    }

    public static void drawMouseOver(Item item){
        if (item == null){
            return;
        }

        switch (item.getItemType()){
            case ItemType.WEAPON:
                drawMouseOver((Weapon) item);
                break;
            case ItemType.ARMOR:
                drawMouseOver((Armor) item);
                break;

            case ItemType.CONSUMABLE:
                drawMouseOver((Consumable) item);
                break;
        }
        
    }
    private static void drawMouseOver(Weapon weapon){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {weapon.getName(),
            weapon.getWeaponType().toString(),
            DMG_TEXT + Integer.toString(weapon.getBoostDamage()),
            CRITCHANCE_TEXT + Integer.toString((int)weapon.getBoostCritChance() * 100) + "%",
            CRITMULT_TEXT + Integer.toString((int)weapon.getBoostCritMultiplier() * 100) + "%",
            ACC_TEXT + Integer.toString((int)weapon.getBoostAccuracy() * 100) + "%",
            DEF_TEXT + Integer.toString(weapon.getBoostDefense()),
            weapon.getDescription()
            };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            TEXT_COLOR);
        }
    }

    private static void drawMouseOver(Armor armor){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {armor.getName(),
            armor.getArmorType().toString(),
            DEF_TEXT + Integer.toString(armor.getBoostDefense()),
            HP_TEXT + Integer.toString(armor.getBoostHP()),
            MP_TEXT + Integer.toString(armor.getBoostMP()),
            armor.getDescription()
            };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            TEXT_COLOR);
        }
    }

    private static void drawMouseOver(Consumable consumable){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {consumable.getName(),
            consumable.getConsumableType().toString(),
            HP_TEXT + Integer.toString(consumable.getBoostHP()),
            MP_TEXT + Integer.toString(consumable.getBoostMP()),
            DMG_TEXT + Integer.toString(consumable.getBoostDamage()),
            consumable.getDescription()
            };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            TEXT_COLOR);
        }
    }
}
