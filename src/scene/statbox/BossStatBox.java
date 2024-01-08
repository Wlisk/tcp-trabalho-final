package scene.statbox;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import entities.boss.Boss;

public class BossStatBox {
    private static final String 
        XP_TEXT = "XP: ",
        DMG_TEXT = "DMG: ",
        CRIT_TEXT = "CRIT%: ",
        CRITMULT_TEXT = "CRIT*: ",
        ACC_TEXT = "HIT%: ",
        DEF_TEXT = "DEF: ",
        DEFMULT_TEXT = "DEF*: ";
        

    private static final int
        MARGIN = 2;

    private Jaylib.Rectangle rectangle, innerRec;
    private Raylib.Color textColor, backgroundColor, borderColor;
    private Boss boss;
    private int fontSize;

    public BossStatBox(Jaylib.Rectangle rectangle, 
                       int fontSize,
                       int borderSize, 
                       Raylib.Color textColor, 
                       Raylib.Color backgroundColor, 
                       Raylib.Color borderColor,
                       Boss boss){
        this.rectangle = rectangle;
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
    }

    public void draw(){
        String textArr[] = {XP_TEXT + Integer.toString(boss.getExpReward()),
                            DMG_TEXT + Integer.toString(boss.getCurrDamage()),
                            CRIT_TEXT + Integer.toString((int)boss.getCurrCritChance() * 100) + "%",
                            CRITMULT_TEXT + Integer.toString((int)boss.getCurrCritMultiplier() * 100) + "%",
                            ACC_TEXT + Integer.toString((int)boss.getCurrAccuracy() * 100) + "%",
                            DEF_TEXT + Integer.toString(boss.getCurrDefense()),
                            DEFMULT_TEXT + Integer.toString((int)boss.getCurrDefenseMultiplier() * 100) + "%"
                            };
        
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

    public void setBoss(Boss boss){
        this.boss = boss;
    }
}
