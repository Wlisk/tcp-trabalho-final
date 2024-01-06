package scene.bars;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Vector2;

import entities.Entity;

public class Bar {
    private Rectangle rectangle;
    private int curVal, maxVal;
    private Color filledColor, emptyColor, textColor; // Color of the filled in part
    private int textSize;

    public Bar(Rectangle rectangle, int maxVal, int curVal, Color filledColor, Color emptyColor, int textSize, Color textColor){
        this.rectangle = rectangle;
        this.maxVal = maxVal;
        this.curVal = curVal;
        this.filledColor = filledColor;
        this.emptyColor = emptyColor;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    public void setMaxVal(int maxVal){
        this.maxVal = maxVal;
    }

    public void setCurVal(int curVal){
        this.curVal = curVal;
    }

    public int getMaxVal(){
        return maxVal;
    }

    public int getCurVal(){
        return curVal;
    }

    public void updateHP(Entity entity){
        curVal = entity.getCurrHP();
        maxVal = entity.getMaxHP();
    }

    public void updateMP(Entity entity){
        curVal = entity.getCurrMP();
        maxVal = entity.getMaxMP();
    }

    public void draw(){
        Jaylib.DrawRectangleRec(rectangle, emptyColor);
        
        float ratio = 1;
        if (maxVal != 0){ // Division by 0 prevention
            ratio = (float)curVal / (float)maxVal;
        }

        Rectangle filledRec = new Rectangle(rectangle.x(), rectangle.y(), (float) rectangle.width() * ratio, rectangle.height()); // Draws the filled in rectangle on top of the empty one
        Jaylib.DrawRectangleRec(filledRec, filledColor);

        String text = Integer.toString(curVal) + "/" + Integer.toString(maxVal);
        Vector2 textVec = Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), text, this.textSize, 0);
        float centerX = rectangle.x() + (rectangle.width() / 2 - textVec.x() / 2);
        float centerY = rectangle.y() + (rectangle.height() / 2 - textVec.y() / 2);
        Jaylib.DrawText(text, (int) centerX, (int) centerY, textSize, textColor);
    }
}
