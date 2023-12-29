package Scene;

import Utils.GameState;
import Utils.TextureIndex;

import com.raylib.Jaylib;
import com.raylib.Raylib;

import java.util.ArrayList;


public class Scene {
    // ------ Constants ------
    private final int WINDOW_WIDTH = 1200, WINDOW_HEIGHT = 800, FPS = 60;

    // Text constants and strings
    private final int FONT_SIZE_TITLE = WINDOW_HEIGHT / 10, FONT_SIZE_SELECTION = WINDOW_HEIGHT / 12, FONT_SIZE_BUTTONS = WINDOW_HEIGHT / 20;
    private final int TITLE_TEXT_POS_Y = WINDOW_HEIGHT / 10;
    private final String GAME_TITLE = "BOSSFIGHTER", 
                         PLAY_TEXT = "PLAY", 
                         EXIT_TEXT = "EXIT", 
                         CLASS1_TEXT = "WARRIOR",
                         CLASS2_TEXT = "RANGER", 
                         CLASS3_TEXT = "MAGE",
                         SELECTION_TEXT = "SELECT YOUR CLASS";
    
    // Sprite related constants
    private final int NUM_SPRITES = 3;
    private final int SIZE_TEXTURE = 32; // Size of texture files is 32x32
    private final float SELECT_SPRITE_SCALE = (WINDOW_HEIGHT / SIZE_TEXTURE) * 0.3f;
    private final float SELECT_CLASS_POS_Y = (WINDOW_HEIGHT * 0.4f);
    private final Jaylib.Vector2 SELECT_CLASS1_SPRITE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.1f, SELECT_CLASS_POS_Y), 
                                 SELECT_CLASS2_SPRITE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.4f, SELECT_CLASS_POS_Y), 
                                 SELECT_CLASS3_SPRITE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.7f, SELECT_CLASS_POS_Y);
    

    // Buttons and related constants
    private final int BUTTON_WIDTH = WINDOW_WIDTH / 4, 
                      BUTTON_HEIGHT = WINDOW_HEIGHT / 8;
    private final float PLAY_BUTTON_POS_Y = WINDOW_HEIGHT * 0.5f, 
                        EXIT_BUTTON_POS_Y = WINDOW_HEIGHT * 0.75f;
    private final float SELECT_CLASS_BUTTON_POS_Y = WINDOW_HEIGHT * 0.8f;

    private final Jaylib.Rectangle PLAY_BUTTON_REC = new Jaylib.Rectangle((float)WINDOW_WIDTH / 2 - (float) BUTTON_WIDTH / 2, PLAY_BUTTON_POS_Y, (float)BUTTON_WIDTH, (float) BUTTON_HEIGHT);
    private final Button PLAY_BUTTON = new Button(PLAY_BUTTON_REC, Jaylib.GRAY, Jaylib.LIGHTGRAY, PLAY_TEXT, FONT_SIZE_BUTTONS, Jaylib.RED);

    private final Jaylib.Rectangle EXIT_BUTTON_REC = new Jaylib.Rectangle((float)WINDOW_WIDTH / 2 - (float) BUTTON_WIDTH / 2, EXIT_BUTTON_POS_Y, (float) BUTTON_WIDTH, (float) BUTTON_HEIGHT);
    private final Button EXIT_BUTTON = new Button(EXIT_BUTTON_REC, Jaylib.GRAY, Jaylib.LIGHTGRAY, EXIT_TEXT, FONT_SIZE_BUTTONS, Jaylib.RED);

    private final Jaylib.Rectangle SELECT_CLASS1_REC = new Jaylib.Rectangle((float)WINDOW_WIDTH * 0.1f, SELECT_CLASS_BUTTON_POS_Y, (float) BUTTON_WIDTH, (float) BUTTON_HEIGHT),
                                   SELECT_CLASS2_REC = new Jaylib.Rectangle((float)WINDOW_WIDTH * 0.4f, SELECT_CLASS_BUTTON_POS_Y, (float) BUTTON_WIDTH, (float) BUTTON_HEIGHT),
                                   SELECT_CLASS3_REC = new Jaylib.Rectangle((float)WINDOW_WIDTH * 0.7f, SELECT_CLASS_BUTTON_POS_Y, (float) BUTTON_WIDTH, (float) BUTTON_HEIGHT);

    private final Button SELECT_CLASS1_BUTTON = new Button(SELECT_CLASS1_REC, Jaylib.GRAY, Jaylib.LIGHTGRAY, CLASS1_TEXT, FONT_SIZE_BUTTONS, Jaylib.RED),
                         SELECT_CLASS2_BUTTON = new Button(SELECT_CLASS2_REC, Jaylib.GRAY, Jaylib.LIGHTGRAY, CLASS2_TEXT, FONT_SIZE_BUTTONS, Jaylib.RED),
                         SELECT_CLASS3_BUTTON = new Button(SELECT_CLASS3_REC, Jaylib.GRAY, Jaylib.LIGHTGRAY, CLASS3_TEXT, FONT_SIZE_BUTTONS, Jaylib.RED);
    

    // ------ End of constants ------
    private ArrayList<Jaylib.Texture> textures;

    public Scene(){

        this.textures = new ArrayList<Jaylib.Texture>(NUM_SPRITES);
    }

    public void initializeWindow(){
        Jaylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        Jaylib.SetTargetFPS(FPS);
    }

    public void loadTextures(){
        for (int i = 0; i < NUM_SPRITES; i++){
            textures.add(null);
        }
        textures.set(TextureIndex.CLASS1_SPRITE.ordinal(), Jaylib.LoadTexture("Sprites/warrior.png"));
        textures.set(TextureIndex.CLASS2_SPRITE.ordinal(), Jaylib.LoadTexture("Sprites/ranger.png"));
        textures.set(TextureIndex.CLASS3_SPRITE.ordinal(), Jaylib.LoadTexture("Sprites/mage.png"));
    }

    public void unloadTextures(){
        for (int i = 0; i < NUM_SPRITES; i++){
            Jaylib.UnloadTexture(textures.get(i));
        }
    }

    public void drawWindow(GameState gameState){
        Jaylib.BeginDrawing();
        Jaylib.ClearBackground(Jaylib.BLACK);

        switch (gameState){
            case GameState.MAIN_MENU:
                drawMainMenu();
                break;

            case GameState.SELECTING_CLASS:
                drawClassSelection();
                break;

            case GameState.TURN_START:
                break;
        }

        Jaylib.EndDrawing();
    }

    public void drawMainMenu(){
        int centerText = WINDOW_WIDTH / 2 - Jaylib.MeasureText(GAME_TITLE, FONT_SIZE_TITLE) / 2; // Calculating X coordinate for text to be horizontally centered
        
        Jaylib.DrawText(GAME_TITLE, centerText, TITLE_TEXT_POS_Y, FONT_SIZE_TITLE, Jaylib.RED);

        drawButton(PLAY_BUTTON);
        drawButton(EXIT_BUTTON);
    }

    public void drawClassSelection(){
        int centerText = WINDOW_WIDTH / 2 - Jaylib.MeasureText(SELECTION_TEXT, FONT_SIZE_SELECTION) / 2; // Coordinate X for text to be horizontally centered
        
        Jaylib.DrawText(SELECTION_TEXT, centerText, TITLE_TEXT_POS_Y, FONT_SIZE_SELECTION, Jaylib.RED);

        Jaylib.DrawTextureEx(textures.get(TextureIndex.CLASS1_SPRITE.ordinal()), SELECT_CLASS1_SPRITE_POS, 0f, SELECT_SPRITE_SCALE, Jaylib.WHITE);
        Jaylib.DrawTextureEx(textures.get(TextureIndex.CLASS2_SPRITE.ordinal()), SELECT_CLASS2_SPRITE_POS, 0f, SELECT_SPRITE_SCALE, Jaylib.WHITE);
        Jaylib.DrawTextureEx(textures.get(TextureIndex.CLASS3_SPRITE.ordinal()), SELECT_CLASS3_SPRITE_POS, 0f, SELECT_SPRITE_SCALE, Jaylib.WHITE);

        drawButton(SELECT_CLASS1_BUTTON);
        drawButton(SELECT_CLASS2_BUTTON);
        drawButton(SELECT_CLASS3_BUTTON);
    }

    public void drawButton(Button button){
        if (button.isMouseOver()){
            Jaylib.DrawRectangleRec(button.getRectangle(), button.getMouseOverColor());
        } else {
            Jaylib.DrawRectangleRec(button.getRectangle(), button.getButtonColor());
        }

        Raylib.Vector2 size = Jaylib.MeasureTextEx(Jaylib.GetFontDefault(), button.getText(), button.getTextSize(), 0);
        float centerX = button.getRectangle().x() + (button.getRectangle().width() / 2 - size.x() / 2);
        float centerY = button.getRectangle().y() + (button.getRectangle().height() / 2 - size.y() / 2);

        Jaylib.DrawText(button.getText(), (int) centerX, (int) centerY, (int)button.getTextSize(), button.getTextColor());
    }

    public Button getPlayButton(){
        return PLAY_BUTTON;
    }

    public Button getExitButton(){
        return EXIT_BUTTON;
    }

    public Button getClass1SelectButton(){
        return SELECT_CLASS1_BUTTON;
    }

    public Button getClass2SelectButton(){
        return SELECT_CLASS2_BUTTON;
    }

    public Button getClass3SelectButton(){
        return SELECT_CLASS3_BUTTON;
    }

    
}
