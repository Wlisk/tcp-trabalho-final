package scene.sprite;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import entities.player.ClassType;
import game.Game;
import interfaces.IDrawable;

/**
 * Class to draw sprites (animated images) on the screen and manage them.
 * <p>
 * Notice that it's not intended to be instantiated.
 * 
 * @see com.raylib.Jaylib
 */
public class Sprite implements IDrawable {
    private static int FPS_SPEED = 0;

    /** Number of spritesheet frames shown by second */
    int frameSpeed;
    int fpsCounter;
    int numSprites;
    int currSpriteIndex;

    private Jaylib.Vector2 position;
    private Jaylib.Rectangle frameRectangle;

    private Jaylib.Rectangle sourceRectangle;
    private Jaylib.Rectangle drawRectangle;
    private Jaylib.Texture texture;

    /**
     * 
     * @param texture
     * @param size
     * @param drawRectangle
     * @param numSprites
     */
    public Sprite(
            Jaylib.Texture texture,
            Jaylib.Vector2 position,
            Jaylib.Rectangle frameRectangle,
            int numSprites) {
        this.texture = texture;
        this.position = position;
        this.numSprites = numSprites;

        this.sourceRectangle = new Jaylib.Rectangle(0, 0, position.x(), position.y());
        this.frameRectangle = frameRectangle;

        fpsCounter = 0;
        frameSpeed = FPS_SPEED;
        currSpriteIndex = 0;
    }

    public void draw() {
        Jaylib.Vector2 origin = new Jaylib.Vector2(drawRectangle.width() / 2, drawRectangle.height() / 2);
        Jaylib.DrawTexturePro(texture, sourceRectangle, drawRectangle, origin, 0, Jaylib.WHITE);
        // DrawTexturePro draws part of a texture (selected by sourceRectangle) on
        // drawRectangle
        // This is for drawing individual sprites from spritesheets
    }

    public int nextSprite() {
        return currSpriteIndex++;
    }

    public Jaylib.Vector2 getPosition() {
        return position;
    }

    public Jaylib.Rectangle getDrawRectangle() {
        return drawRectangle;
    }

    public static void main(String[] args) {
        Jaylib.InitWindow(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, "test");
        Jaylib.SetTargetFPS(Game.FPS);

        final String archerSheet = ClassType.ARCHER.getSpriteSheetSrc();
        final Jaylib.Texture textureSheet = Jaylib.LoadTexture(archerSheet);
        final int textureSheetWidth = textureSheet.width();

        final float posYStart = 96.0f;
        final float posYEnd = 127.0f;
        final int numSprites = 6;
        final float spriteHeight = Math.abs(posYEnd - posYStart);
        final float spriteWidth = (textureSheetWidth / 10);

        float currXPos = 0.0f;
        int fpsCounter = 0;
        int frameSpeed = 5;
        int currentFrame = 0;

        final float scale = 5.0f;

        Raylib.Vector2 drawPosition = new Jaylib.Vector2(15.0f, 15.0f);
        Jaylib.Rectangle spriteRectangle = new Jaylib.Rectangle(
            0.0f, posYStart, 
            spriteWidth, spriteHeight
        );
        Jaylib.Rectangle rectangleScaleAndPos = new Jaylib.Rectangle(
            drawPosition.x(), drawPosition.y(), 
            spriteWidth*scale, spriteHeight*scale
        );

        final int spriteFrameRate = (int)(Game.FPS / frameSpeed);
        final Jaylib.Vector2 VECTOR_NULL = new Jaylib.Vector2(0.0f, 0.0f);
        
        while (!Jaylib.WindowShouldClose()) 
        {
            fpsCounter++;

            if (fpsCounter >= spriteFrameRate) {
                fpsCounter = 0;
                ++currentFrame;
                currXPos += spriteWidth;

                if (currentFrame >= numSprites) {
                    currentFrame = 0;
                    currXPos = 0;
                }
                
                spriteRectangle.x(currXPos);
            }

            Jaylib.BeginDrawing();
            Jaylib.ClearBackground(Jaylib.BLACK);

            // Draw part of the texture
            //Jaylib.DrawTextureRec(textureSheet, spriteRectangle, drawPosition, Jaylib.WHITE); 
            Jaylib.DrawTexturePro(
                textureSheet, 
                spriteRectangle, rectangleScaleAndPos, 
                VECTOR_NULL, 0.0f, 
                Jaylib.WHITE
            );

            Jaylib.EndDrawing();
        }

        System.out.println("sprite width: " + spriteWidth);

        Jaylib.UnloadTexture(textureSheet);
        Jaylib.CloseWindow();
    }
}
