package scene;

import com.raylib.Jaylib;
import java.util.ArrayList;

import game.GameState;
import game.Game;
import scene.button.Button;
import scene.button.Buttons;
import entities.player.ClassType;
import scene.textbox.TextBoxes;

public class Scene {
    private static final int 
        WINDOW_WIDTH = Game.WINDOW_WIDTH, 
        WINDOW_HEIGHT = Game.WINDOW_HEIGHT, 
        FPS = Game.FPS;

    private static final int 
        FONT_SIZE_TITLE = WINDOW_HEIGHT / 10, 
        FONT_SIZE_SELECTION = WINDOW_HEIGHT / 12, 
        TITLE_TEXT_POS_Y = WINDOW_HEIGHT / 10;

    private static final String SELECTION_TEXT = "SELECT YOUR CLASS";
    
    private static final int SIZE_TEXTURE = 32; // Size of texture files is 32x32
    private static final float SELECT_SPRITE_SCALE = (WINDOW_HEIGHT / SIZE_TEXTURE) * 0.3f;
    private static final float SELECT_CLASS_POS_Y = (WINDOW_HEIGHT * 0.4f);
    
    private static final float SPRITES_START_DISTANCEX = 0.1f;
    private static final float SPRITES_BETWEEN_DISTANCEX = 0.3f;

    private static final Jaylib.Vector2[] SPRITE_POS = getSpritesPos();
    
    private final ArrayList<Jaylib.Texture> textures = 
        new ArrayList<Jaylib.Texture>( ClassType.size() );

    private String gameTitle;

    public Scene() {
        gameTitle = null;
    }

    public void initializeWindow(String gameTitle) {
        Jaylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, gameTitle);
        Jaylib.SetTargetFPS(FPS);
        this.gameTitle = gameTitle;
    }

    public void loadTextures() {
        for(final ClassType _classType: ClassType.values()) {
            textures.add(
                Jaylib.LoadTexture(_classType.getImageSrc())
            );
        }
    }

    public void unloadTextures() {
        for(final Jaylib.Texture _texture: textures) {
            Jaylib.UnloadTexture(_texture);
        }
    }

    public void drawWindow(GameState gameState) {
        Jaylib.BeginDrawing();
        Jaylib.ClearBackground(Jaylib.BLACK);

        switch (gameState) {
            case MAIN_MENU:
                drawMainMenu();
                break;
            case SELECTING_CLASS:
                drawClassSelections();
                break;
            case BATTLE_START:
                break;
            case TURN_START:
                break;

            default: break;
        }
        TextBoxes.TEXTBOX.draw();

        Jaylib.EndDrawing();
    }

    private void drawMainMenu() {
        final int _textCenterPosX = 
            (WINDOW_WIDTH / 2) - (Jaylib.MeasureText(gameTitle, FONT_SIZE_TITLE) / 2); 

        Jaylib.DrawText(
            gameTitle, 
            _textCenterPosX, 
            TITLE_TEXT_POS_Y, 
            FONT_SIZE_TITLE, 
            Jaylib.RED
        );

        Buttons.PLAY_BUTTON.draw();
        Buttons.EXIT_BUTTON.draw();
    }

    private void drawClassSelections() {
        final int _textCenterPosX = 
            (WINDOW_WIDTH / 2) - (Jaylib.MeasureText(SELECTION_TEXT, FONT_SIZE_SELECTION) / 2); 
        
        Jaylib.DrawText(
            SELECTION_TEXT, 
            _textCenterPosX, 
            TITLE_TEXT_POS_Y, 
            FONT_SIZE_SELECTION, 
            Jaylib.RED
        );

        for(final ClassType _classType: ClassType.values()) {
            Jaylib.DrawTextureEx(
                textures.get( _classType.getIndex() ), 
                SPRITE_POS[_classType.getIndex()], 
                0f, 
                SELECT_SPRITE_SCALE, 
                Jaylib.WHITE
            );
        }

        for(final Button _button: Buttons.CLASS_BUTTONS) {
            _button.draw();
        }
    }

    private static Jaylib.Vector2[] getSpritesPos() {
        float _distanceX = SPRITES_START_DISTANCEX;
        final Jaylib.Vector2[] _spritesPos = new Jaylib.Vector2[ClassType.size()];

        for(final ClassType _classType: ClassType.values()) {
            _spritesPos[_classType.getIndex()] = new Jaylib.Vector2(
                WINDOW_WIDTH * _distanceX, 
                SELECT_CLASS_POS_Y
            );

            _distanceX += SPRITES_BETWEEN_DISTANCEX;
        }

        return _spritesPos;
    }
}
