package scene.sprite;


import com.raylib.Jaylib;

public class Sprite {
    private Jaylib.Vector2 size;
    private Jaylib.Rectangle sourceRectangle;
    private Jaylib.Rectangle drawRectangle;
    private Jaylib.Texture texture;    

    public Sprite(Jaylib.Texture texture, Jaylib.Vector2 size, Jaylib.Rectangle drawRectangle){
        this.size = size;
        this.sourceRectangle = new Jaylib.Rectangle(0, 0, size.x(), size.y());
        this.drawRectangle = drawRectangle;
        this.texture = texture;
    }

    public void draw(){
        Jaylib.Vector2 origin = new Jaylib.Vector2(drawRectangle.width()/2, drawRectangle.height()/2);
        Jaylib.DrawTexturePro(texture, sourceRectangle, drawRectangle, origin, 0, Jaylib.WHITE);
        // DrawTexturePro draws part of a texture (selected by sourceRectangle) on drawRectangle
        // This is for drawing individual sprites from spritesheets
    }

    public void nextSprite(){

    }

    public Jaylib.Vector2 getSize(){
        return size;
    }

    public Jaylib.Rectangle getDrawRectangle(){
        return drawRectangle;
    }
}
