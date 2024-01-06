package scene.statbox;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import entities.player.Player;

public class PlayerStatBox {
    private static final String 
        LVL_TEXT = "LVL: ",
        XP_TEXT = "XP: ",
        DMG_TEXT = "DMG: ",
        DEF_TEXT = "DEF: ",
        DEFMULT_TEXT = "DEF*: ",
        CRIT_TEXT = "CRIT%: ",
        CRITMULT_TEXT = "CRIT*: ",
        ACC_TEXT = "HIT%: ";

    private static final int
        MARGIN = 2;

    private Jaylib.Rectangle rectangle, innerRec;
    private Raylib.Color textColor, backgroundColor, borderColor;

    public PlayerStatBox(Jaylib.Rectangle rectangle, 
                         int borderSize, 
                         Raylib.Color textColor, 
                         Raylib.Color backgroundColor, 
                         Raylib.Color borderColor){
        this.rectangle = rectangle;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
    }

    public void draw(Player player){
        String textArr[] = {LVL_TEXT + Integer.toString(player.getLevel()),
                         XP_TEXT + Integer.toString(player.getExp()) + "/" + Integer.toString(player.getExpToLevel()),
                         DMG_TEXT + Integer.toString(player.getCurrDamage()),
                         CRIT_TEXT + Integer.toString((int)player.getCurrCritChance() * 100) + "%",
                         CRITMULT_TEXT + Integer.toString((int)player.getCurrCritMultiplier() * 100) + "%",
                         ACC_TEXT + Integer.toString((int)player.getCurrAccuracy() * 100) + "%",
                         DEF_TEXT + Integer.toString(player.getCurrDefense()),
                         DEFMULT_TEXT + Integer.toString((int)player.getCurrDefenseMultiplier() * 100) + "%"
                         };
        
        int fontSize = (int) ((innerRec.height() * 0.9 + (MARGIN * textArr.length)) / (textArr.length)); // Reasonable estimate for font size to fit all information

        Jaylib.DrawRectangleRec(rectangle, borderColor);
        Jaylib.DrawRectangleRec(innerRec, backgroundColor);

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)fontSize, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i], 
                            (int)(innerRec.x() + MARGIN), 
                            (int)(MARGIN + spacing * i), 
                            fontSize, 
                            textColor);
        }
    }
}
