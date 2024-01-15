package scene;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib.Texture;

public class Sprite {
    private static final float ZEROF = 0.0f;

    float imageScale = 1.0f;
    final Rectangle dimension = new Rectangle(ZEROF, ZEROF, ZEROF, ZEROF); 
    final Vector2 position = new Vector2(ZEROF, ZEROF);
    final String imageSrc;
    Texture texture;

    public Sprite(String imageSrc) {
        this.imageSrc = imageSrc;
        texture = null;
    }

    public Sprite(String imageSrc, float x, float y, float scale, float width, float height) {
        imageScale = scale;
        setDimension(x, y, width, height);
        texture = null;
        this.imageSrc = imageSrc;
    }

    private void setVector(float x, float y) {
        position.x(x);
        position.y(y);
    }

    private void setDimension(float x, float y, float width, float height) {
        dimension.x(x);
        dimension.y(y);
        dimension.width(width);
        dimension.height(height);
        setVector(x, y);
    }

    public Vector2 pos() { return position; }
    public void pos(float x, float y) {
        position.x(x);
        position.y(y);
    }
    public float x() { return position.x(); }
    public float y() { return position.y(); }
    public void x(float x) { position.x(x); }
    public void y(float y) { position.y(y); }

    public Texture loadTexture() {
        texture =  Jaylib.LoadTexture(imageSrc);
        return texture;
    }
    public void unloadTexture() {
        Jaylib.UnloadTexture(texture);
    }
}
