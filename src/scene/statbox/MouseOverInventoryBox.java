package scene.statbox;

import game.Game;
import items.consumable.Consumable;
import items.weapon.Weapon;
import items.armor.Armor;
import items.Item;

import com.raylib.Jaylib;
import com.raylib.Raylib;

public class MouseOverInventoryBox {
    private static final int 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT,
        WINDOW_WIDTH = Game.WINDOW_WIDTH;

    private static final float
        BOX_WIDTH = WINDOW_WIDTH / 2,
        BOX_HEIGHT = WINDOW_HEIGHT / 3;

    private static final Raylib.Color
        COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        COLOR_BORDER = Jaylib.GetColor(1631265023),
        COLOR_TEXT = Jaylib.WHITE;

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

    private Jaylib.Rectangle getOuterRectangle(){
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

    private Jaylib.Rectangle getInnerRectangle(Jaylib.Rectangle outerRec){
        return new Jaylib.Rectangle(outerRec.x() + BORDER_SIZE, outerRec.y() + BORDER_SIZE, BOX_WIDTH - BORDER_SIZE * 2, BOX_HEIGHT - BORDER_SIZE * 2);
    }

    private void drawBox(){
        Jaylib.Rectangle outerRec = getOuterRectangle();
        Jaylib.Rectangle innerRec = getInnerRectangle(outerRec);

        Jaylib.DrawRectangleRec(outerRec, COLOR_BORDER);
        Jaylib.DrawRectangleRec(innerRec, COLOR_BACKGROUND);
    }

    public void drawMouseOver(Item item){
        if (item == null){
            return;
        }

        switch (item.getItemType()){
            case WEAPON:
                drawMouseOver((Weapon) item);
                break;
            case ARMOR:
                drawMouseOver((Armor) item);
                break;

            case CONSUMABLE:
                drawMouseOver((Consumable) item);
                break;
        }
    }

    private void drawMouseOver(Weapon weapon){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {weapon.getName(),
            weapon.getWeaponType().toString(),
            DMG_TEXT + Integer.toString(weapon.getBoostDamage()),
            CRITCHANCE_TEXT + Integer.toString((int)(weapon.getBoostCritChance() * 100)) + "%",
            CRITMULT_TEXT + Integer.toString((int)(weapon.getBoostCritMultiplier() * 100)) + "%",
            ACC_TEXT + Integer.toString((int)(weapon.getBoostAccuracy() * 100)) + "%",
            DEF_TEXT + Integer.toString(weapon.getBoostDefense()),
            weapon.getDescription()
        };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            COLOR_TEXT);
        }
    }

    private void drawMouseOver(Armor armor){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {armor.getName(),
            armor.getArmorType().toString(),
            DEF_TEXT + Integer.toString(armor.getBoostDefense()),
            //HP_TEXT + Integer.toString(armor.getBoostHP()),
            //MP_TEXT + Integer.toString(armor.getBoostMP()),
            armor.getDescription()
        };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            COLOR_TEXT);
        }
    }

    private void drawMouseOver(Consumable consumable){
        drawBox();
        Jaylib.Rectangle innerRec = getInnerRectangle(getOuterRectangle());
        String textArr[] = {consumable.getName(),
            consumable.getConsumableType().toString(),
            HP_TEXT + Integer.toString(consumable.getBoostHP()),
            MP_TEXT + Integer.toString(consumable.getBoostMP()),
            consumable.getDescription()
        };

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)FONT_SIZE, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + MARGIN),
                            (int)(innerRec.y() + MARGIN + spacing * i),
                            FONT_SIZE,
                            COLOR_TEXT);
        }
    }
}
