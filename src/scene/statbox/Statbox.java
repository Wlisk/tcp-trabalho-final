package scene.statbox;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import entities.boss.Boss;
import entities.Entity;
import entities.player.Player;


public class Statbox {
    private static final String 
        LVL_TEXT = "LVL: ",
        XP_TEXT = "XP: ",
        DMG_TEXT = "DMG: ",
        DEF_TEXT = "DEF: ",
        DEFMULT_TEXT = "DEF*: ",
        CRIT_TEXT = "CRIT%: ",
        CRITMULT_TEXT = "CRIT*: ",
        ACC_TEXT = "HIT%: ";

    private Jaylib.Rectangle rectangle, innerRec;
    private Raylib.Color textColor, backgroundColor, borderColor;
    private Entity entity;
    private int fontSize, margin;

    public Statbox(Jaylib.Rectangle rectangle, 
                       int fontSize,
                       int margin,
                       int borderSize, 
                       Raylib.Color textColor, 
                       Raylib.Color backgroundColor, 
                       Raylib.Color borderColor,
                       Entity entity){
        this.rectangle = rectangle;
        this.fontSize = fontSize;
        this.margin = margin;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.innerRec = new Jaylib.Rectangle(rectangle.x() + borderSize, rectangle.y() + borderSize, rectangle.width() - borderSize * 2, rectangle.height() - borderSize * 2);
        this.entity = entity;
    }
    
    private String[] getStrings(Entity entity){
        if (entity instanceof Player){
            return getStrings((Player) entity);
        } else {
            return getStrings((Boss) entity);
        }
    }

    private String[] getStrings(Player player){
        String textArr[] = {LVL_TEXT + Integer.toString(player.getLevel()),
                            XP_TEXT + Integer.toString(player.getExp()) + "/" + Integer.toString(player.getExpToLevel()),
                            DMG_TEXT + Integer.toString(player.getCurrDamage()),
                            CRIT_TEXT + Integer.toString((int)(player.getCurrCritChance() * 100)) + "%",
                            CRITMULT_TEXT + Integer.toString((int)(player.getCurrCritMultiplier() * 100)) + "%",
                            ACC_TEXT + Integer.toString((int)(player.getCurrAccuracy() * 100)) + "%",
                            DEF_TEXT + Integer.toString(player.getCurrDefense()),
                            DEFMULT_TEXT + Integer.toString((int)(player.getCurrDefenseMultiplier() * 100)) + "%" };

        return textArr;
    }

    private String[] getStrings(Boss boss){
        String textArr[] = {boss.getName(),
                            XP_TEXT + Integer.toString(boss.getExpReward()),
                            DMG_TEXT + Integer.toString(boss.getCurrDamage()),
                            CRIT_TEXT + Integer.toString((int)(boss.getCurrCritChance() * 100)) + "%",
                            CRITMULT_TEXT + Integer.toString((int)(boss.getCurrCritMultiplier() * 100)) + "%",
                            ACC_TEXT + Integer.toString((int)(boss.getCurrAccuracy() * 100)) + "%",
                            DEF_TEXT + Integer.toString(boss.getCurrDefense()),
                            DEFMULT_TEXT + Integer.toString((int)(boss.getCurrDefenseMultiplier() * 100)) + "%"
            };
        return textArr;
    }

    

    public void draw(){
        String textArr[] = getStrings(entity);
        
        Jaylib.DrawRectangleRec(rectangle, borderColor);
        Jaylib.DrawRectangleRec(innerRec, backgroundColor);

        int spacing = (int)Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), "!", (float)fontSize, 0f).y();

        for (int i = 0; i < textArr.length; i++){
            Jaylib.DrawText(textArr[i],
                            (int)(innerRec.x() + margin),
                            (int)(margin + spacing * i),
                            fontSize,
                            textColor);
        }
    }

    public void setEntity(Entity entity){
        this.entity = entity;
    }
}
