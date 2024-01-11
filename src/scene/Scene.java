package scene;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib.Texture;
import static com.raylib.Raylib.DrawText;
import static com.raylib.Raylib.DrawTextureEx;
import static com.raylib.Raylib.MeasureText;


import game.GameState;
import entities.boss.Boss;
import entities.player.Player;
import game.Game;
import scene.bars.Bars;
import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import scene.button.Button;
import scene.button.Buttons;
import scene.textbox.TextBoxes;
import scene.statbox.Statboxes;
import scene.inventory.InventorySlotInst;
import java.util.HashMap;
import scene.TextureId;

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
    
    private static final float SELECT_CLASS_POS_Y = (WINDOW_HEIGHT * 0.4f),
                               PLAYER_SELECT_SPRITE_SIZE = (WINDOW_HEIGHT * 0.3f), // percentage of the screen height that the sprite will take up
                               PLAYER_BATTLE_SPRITE_SIZE = (WINDOW_HEIGHT * 0.25f),
                               BOSS_BATTLE_SPRITE_SIZE = (WINDOW_HEIGHT * 0.35f);

    private static final Jaylib.Vector2 PLAYER_BATTLE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.25f, WINDOW_HEIGHT * 0.30f),
                                        BOSS_BATTLE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.55f, WINDOW_HEIGHT * 0.15f);

    
    private static final float SPRITES_START_DISTANCEX = 0.1f;
    private static final float SPRITES_BETWEEN_DISTANCEX = 0.3f;

    private static final Vector2[] SPRITE_POS = getSpritesPos();
    private HashMap<TextureId, Texture> textures;

    private String gameTitle;
    private final Game game;

    public Scene(Game game) {
        gameTitle = null;
        this.game = game;
        textures = new HashMap<TextureId, Texture>();
    }

    public void initializeWindow(String gameTitle) {
        Jaylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, gameTitle);
        Jaylib.SetTargetFPS(FPS);
        this.gameTitle = gameTitle;
    }

    public void loadTextures() {
        for (final TextureId txId: TextureId.values()){
            textures.put(txId, Jaylib.LoadTexture(txId.getPath()));
        }

    }

    public void unloadTextures() {
        for(final HashMap.Entry<TextureId, Texture> entry : textures.entrySet()){
            Jaylib.UnloadTexture(entry.getValue());
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
        
        DrawText(SELECTION_TEXT, 
                 _textCenterPosX, 
                 TITLE_TEXT_POS_Y, 
                 FONT_SIZE_SELECTION, 
                 Jaylib.RED);

        
        DrawTextureEx(textures.get(TextureId.CLASS_1),
                      SPRITE_POS[0], 
                      0f, 
                      (PLAYER_SELECT_SPRITE_SIZE / textures.get(TextureId.CLASS_1).height()),
                      Jaylib.WHITE);
        
        DrawTextureEx(textures.get(TextureId.CLASS_2),
                      SPRITE_POS[1], 
                      0f, 
                      (PLAYER_SELECT_SPRITE_SIZE / textures.get(TextureId.CLASS_2).height()),
                      Jaylib.WHITE);

        DrawTextureEx(textures.get(TextureId.CLASS_3),
                      SPRITE_POS[2], 
                      0f, 
                      (PLAYER_SELECT_SPRITE_SIZE / textures.get(TextureId.CLASS_3).height()),
                      Jaylib.WHITE);

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
        
        drawPlayer();
        drawBoss();
        InventorySlotInst.INVENTORY_SLOTS.draw(textures);
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

    private void drawPlayer(){
        final Jaylib.Texture tx = textures.get(game.getPlayer().getTextureId());

        final float aspectRatio = (float)tx.height()/(float)tx.width(); // Make sure texture doesn't get distorted if texture's aspect ratio isn't 1:1

        final Jaylib.Rectangle sourceRec = new Jaylib.Rectangle(0f, 0f, tx.width(), tx.height()),
                         destRec = new Jaylib.Rectangle(PLAYER_BATTLE_POS.x(),
                                                        PLAYER_BATTLE_POS.y(),
                                                        PLAYER_BATTLE_SPRITE_SIZE,
                                                        PLAYER_BATTLE_SPRITE_SIZE * aspectRatio);

        final Jaylib.Vector2 origin = new Vector2(0f, 0f);

        Jaylib.DrawTexturePro(tx, sourceRec, destRec, origin, 0f, Jaylib.WHITE);
    }

    private void drawBoss(){
        Boss boss = game.getCurrBoss();
        final Jaylib.Texture tx = textures.get(boss.getTextureId());

        final float aspectRatio = (float)tx.height()/(float)tx.width(); // Make sure texture doesn't get distorted if texture's aspect ratio isn't 1:1

        final Jaylib.Rectangle sourceRec = new Jaylib.Rectangle(0f, 0f, tx.width(), tx.height()),
                         destRec = new Jaylib.Rectangle(BOSS_BATTLE_POS.x(),
                                                        BOSS_BATTLE_POS.y(),
                                                        BOSS_BATTLE_SPRITE_SIZE,
                                                        BOSS_BATTLE_SPRITE_SIZE * aspectRatio);

        final Jaylib.Vector2 origin = new Vector2(0f, 0f);

        Jaylib.DrawTexturePro(tx, sourceRec, destRec, origin, 0f, getBossColor(boss));
    }

    private Raylib.Color getBossColor(Boss boss){
        switch (boss.getCurrState()){
            case BERSERK: return Jaylib.RED;
            case WEAK: return Jaylib.GREEN;
            default: return Jaylib.WHITE;
        }
    }

    public void drawBattle() {
        final Player _player = game.getPlayer();
        final Boss _boss = game.getCurrBoss();

        //Jaylib.DrawTexture(null, 15, 15, Jaylib.WHITE);
    }        
}
