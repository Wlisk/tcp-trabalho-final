package scene;

import java.util.ArrayList;
import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.float16;

//import static com.raylib.Jaylib.Texture;
//import static com.raylib.Jaylib.Vector2;
/*import static com.raylib.Raylib.DrawText;
import static com.raylib.Raylib.DrawTextureEx;
import static com.raylib.Raylib.LoadTexture;
import static com.raylib.Raylib.MeasureText;
import static com.raylib.Raylib.UnloadTexture;*/
import static com.raylib.Jaylib.LoadTexture;
import static com.raylib.Jaylib.UnloadTexture;
import static com.raylib.Jaylib.DrawTextureEx;
import static com.raylib.Jaylib.DrawTextureV;
import static com.raylib.Jaylib.DrawText;
import static com.raylib.Jaylib.MeasureText;

import game.GameState;
import entities.boss.Boss;
import entities.player.Player;
import game.Game;
import scene.bars.Bars;
import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import exceptions.UnknownTypeException;
import scene.button.Button;
import scene.button.Buttons;
import scene.textbox.TextBoxes;
import scene.statbox.Statboxes;
import scene.inventory.InventorySlotInst;

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

    private static final Vector2[] SPRITE_POS = getSpritesPos();
    
    private final ArrayList<Texture> texturesClassSel = 
        new ArrayList<Texture>( ClassType.size() );
    private final ArrayList<Texture> texturesClassSprite = 
        new ArrayList<Texture>( ClassType.size() );
    private final ArrayList<Texture> texturesBosses = 
        new ArrayList<Texture>( 1/*Bosses.quantity()*/ );

    private String gameTitle;
    private final Game game;

    public Scene(Game game) {
        gameTitle = null;
        this.game = game;
    }

    public void initializeWindow(String gameTitle) {
        Jaylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, gameTitle);
        Jaylib.SetTargetFPS(FPS);
        this.gameTitle = gameTitle;
    }

    public void loadTextures() {
        for(final ClassType _classType: ClassType.values()) {
            texturesClassSel.add(
                LoadTexture(_classType.getImageSrc())
            );
            texturesClassSprite.add(
                LoadTexture(_classType.getSpriteSheetSrc())
            );
        }

        Boss[] _bosses = Bosses.getList();
        for(int i = 0; i < 1/*Bosses.quantity()*/; ++i) {
            texturesBosses.add(
                LoadTexture(_bosses[i].getImageSrc())
            );
        }
    }

    public void unloadTextures() {
        for(final Texture _textureClassSel: texturesClassSel) {
            UnloadTexture(_textureClassSel);
        }

        for(final Texture _textureSprite: texturesClassSprite) {
            UnloadTexture(_textureSprite);
        }

        for(final Texture _textureBoss: texturesBosses) {
            UnloadTexture(_textureBoss);
        }
    }

    public void drawWindow(GameState gameState, Player player, Boss currBoss) {
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
                drawBattleStart();
                break;
            case TURN_START:
                drawTurnStart(player, currBoss);
                drawBattle();
                break;

            default: break;
        }

        TextBoxes.ALERT_TEXTBOX.draw();

        Jaylib.EndDrawing();
    }

    private void drawMainMenu() {
        final int _textCenterPosX = 
            (WINDOW_WIDTH / 2) - (MeasureText(gameTitle, FONT_SIZE_TITLE) / 2); 

        DrawText(
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
            (WINDOW_WIDTH / 2) - (MeasureText(SELECTION_TEXT, FONT_SIZE_SELECTION) / 2); 
        
        DrawText(
            SELECTION_TEXT, 
            _textCenterPosX, 
            TITLE_TEXT_POS_Y, 
            FONT_SIZE_SELECTION, 
            Jaylib.RED
        );

        for(final ClassType _classType: ClassType.values()) {
            DrawTextureEx(
             texturesClassSel.get( _classType.getIndex() ), 
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

    private void drawBattleStart() {

    }

    private void drawTurnStart(Player player, Boss currBoss) {
        Statboxes.PLAYER_STATBOX.draw();
        Statboxes.BOSS_STATBOX.draw();
        drawBars(player, currBoss);
        InventorySlotInst.INVENTORY_SLOTS.draw();
    }

    private void drawBars(Player player, Boss currBoss) {
        Bars.PLAYER_HEALTHBAR.drawHP();
        Bars.PLAYER_MANABAR.drawMP();
        Bars.BOSS_HEALTHBAR.drawHP();
        Bars.BOSS_MANABAR.drawMP();
    }

    private static Vector2[] getSpritesPos() {
        float _distanceX = SPRITES_START_DISTANCEX;
        final Vector2[] _spritesPos = new Vector2[ClassType.size()];

        for(final ClassType _classType: ClassType.values()) {
            _spritesPos[_classType.getIndex()] = new Vector2(
                WINDOW_WIDTH * _distanceX, 
                SELECT_CLASS_POS_Y
            );

            _distanceX += SPRITES_BETWEEN_DISTANCEX;
        }

        return _spritesPos;
    }

    public void drawBattle() {
        final Player _player = game.getPlayer();
        final Boss _boss = game.getCurrBoss();

        //Jaylib.DrawTexture(null, 15, 15, Jaylib.WHITE);
    }

    public static void main(String[] args) throws UnknownTypeException {
        Jaylib.InitWindow(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, "test");
        Jaylib.SetTargetFPS(Game.FPS);

        final Player _player = new Player("Test", ClassType.MAGE);
        final Boss _boss = Bosses.getNextBoss();

        final Texture _textureBoss = LoadTexture(_boss.getImageSrc());
        final Texture _texturePlayer = LoadTexture(_player.getClassType().getImageSrc());

        final float _baselineY = Game.WINDOW_HEIGHT * 0.75f;

        //final float _posY = _baselineY;
        final float _borderDistanceX = 15.0f;

        final float _bossScale = _boss.getImageScale();
        final float _bossHeightScaled = _textureBoss.height() * _bossScale;
        final float _bossWidthScaled = _textureBoss.width() * _bossScale;
        final float _bossPosX = Game.WINDOW_WIDTH - (_borderDistanceX + _bossWidthScaled);
        final float _bossPosY = _baselineY - _bossHeightScaled;

        final float _playerScale = 3.0f;
        final float _playerHeightScaled = _texturePlayer.height() *  _playerScale;
        final float _playerPosY = _baselineY - _playerHeightScaled;

        _player.pos(_borderDistanceX, _playerPosY);
        _boss.pos(_bossPosX, _bossPosY);

        while (!Jaylib.WindowShouldClose()) {
            Jaylib.BeginDrawing();
            Jaylib.ClearBackground(Jaylib.BLACK);

            //DrawTextureV(_texturePlayer, _player.pos(), Jaylib.WHITE);
            DrawTextureEx(_texturePlayer, _player.pos(), 0.0f, _playerScale, Jaylib.WHITE);
            DrawTextureEx(_textureBoss, _boss.pos(), 0.0f, _bossScale , Jaylib.WHITE);

            Jaylib.EndDrawing();
        }

        Jaylib.UnloadTexture(_textureBoss);
        Jaylib.UnloadTexture(_texturePlayer);
        Jaylib.CloseWindow();
    }
}
