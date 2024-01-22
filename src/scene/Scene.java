package scene;

import com.raylib.Jaylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;

import config.Config;
import game.GameState;
import entities.boss.Boss;
import entities.player.Player;
import game.Game;
import entities.player.ClassType;
import scene.button.Button;
import entities.boss.StateType;

import static config.Config.WINDOW_WIDTH;
import static config.Config.WINDOW_HEIGHT;
import static config.Config.FPS;

/**
 * Classe para gerenciar e desenhar o jogo na tela 
 */
public class Scene {
    private static final Jaylib.Vector2 
        PLAYER_BATTLE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.25f, WINDOW_HEIGHT * 0.30f),
        BOSS_BATTLE_POS = new Jaylib.Vector2(WINDOW_WIDTH * 0.55f, WINDOW_HEIGHT * 0.15f);

    private static final Jaylib.Vector2 originVector = new Jaylib.Vector2(0f, 0f);

    /** 
     * Calcula a posição dos sprites das classes do jogador possíveis de se selecionar 
     * e retorna elas como uma lista de posições 
     * @return (Vector2) lista de posições dos sprites das classes do jogador
     * @see com.raylib.Jaylib.Vector2
     */
    private static Jaylib.Vector2[] getSpritesPos() {
        float _distanceX = Config.SCENE_SPRITES_DISTANCEX_START;
        final Jaylib.Vector2[] _spritesPos = new Jaylib.Vector2[ClassType.size()];

        for(final ClassType _classType: ClassType.values()) {
            _spritesPos[_classType.getIndex()] = new Jaylib.Vector2(
                WINDOW_WIDTH * _distanceX, 
                Config.SCENE_SELECT_CLASS_POSY
            );
            _distanceX += Config.SCENE_SPRITES_DISTANCEX_BETWEEN;
        }
        return _spritesPos;
    }

    private static final Jaylib.Vector2[] SPRITE_POS = getSpritesPos();
    //private HashMap<TextureId, Jaylib.Texture> textures;

    private String gameTitle;
    private final Game game;

    /**
     * Contrutor de Scene, inicializando a classe com dados da classe Game, 
     * para que Scene sempre seja atualizando correspondendo a lógica do jogo
     * @param game onde a lógica do jogo acontece
     * @see game.Game
     */
    public Scene(final Game game) {
        gameTitle = null;
        this.game = game;
        //textures = new HashMap<TextureId, Jaylib.Texture>();
    }

    /** 
     * Inicializa a janela de desenho do jogo com o título dado 
     * @param gameTitle o título da janela do jogo
     */
    public void initializeWindow(final String gameTitle) {
        Jaylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, gameTitle);
        Jaylib.SetTargetFPS(FPS);
        this.gameTitle = gameTitle;
    }

    /*public void loadTextures() {
        for (final TextureId txId: TextureId.values()) {
            textures.put(txId, Jaylib.LoadTexture(txId.getPath()));
        }
    }*/

    /** Descarrega todas as texturas que foram carregadas na memória */
    public void unloadTextures() {
        /*for(final HashMap.Entry<TextureId, Jaylib.Texture> entry : textures.entrySet()) {
            Jaylib.UnloadTexture(entry.getValue());
        }*/
        for (final TextureId _textureId: TextureId.values()) {
            _textureId.unloadTexture();
        }
    }

    /** Desenha os botões de ação do Player */
    private void drawBattleButtons() {
        game.getAttackButton().draw();
        game.getSpecialButton().draw();
        game.getDefendButton().draw();
    }

    /** Desenha o menu inicial do jogo */
    private void drawMainMenu() {
        final int _textMeasure = Jaylib.MeasureText(gameTitle, Config.SCENE_FONT_SIZE_TITLE);
        final int _textCenterPosX = (WINDOW_WIDTH / 2) - (_textMeasure / 2); 

        Jaylib.DrawText(
            gameTitle, 
            _textCenterPosX, 
            Config.SCENE_TITLE_POSY, 
            Config.SCENE_FONT_SIZE_TITLE, 
            Jaylib.RED
        );
        game.getPlayButton().draw();
        game.getExitButton().draw();
    }

    /** Desenha o menu de seleção de classes do jogador com os botões de seleção */
    private void drawClassSelections() {
        final int _textMeasure = Jaylib.MeasureText(
            Config.SCENE_TEXT_SELECTION, 
            Config.SCENE_FONT_SIZE_SELECTION
        );
        final int _textCenterPosX = (WINDOW_WIDTH / 2) - (_textMeasure / 2); 
        Jaylib.DrawText(
            Config.SCENE_TEXT_SELECTION, 
            _textCenterPosX, 
            Config.SCENE_TITLE_POSY, 
            Config.SCENE_FONT_SIZE_SELECTION, 
            Jaylib.RED
        );

        final Texture _textureClass1 = TextureId.CLASS_1.getTexture(); 
        final float _scaleClass1 = Config.SCENE_PLAYER_SPRITE_SIZE_SELECT / _textureClass1.height();
        Jaylib.DrawTextureEx(
            _textureClass1,
            SPRITE_POS[0], 
            0f, 
            _scaleClass1,
            Jaylib.WHITE
        );
        
        final Texture _textureClass2 = TextureId.CLASS_2.getTexture(); 
        final float _scaleClass2 = Config.SCENE_PLAYER_SPRITE_SIZE_SELECT / _textureClass2.height();
        Jaylib.DrawTextureEx(
            _textureClass2,
            SPRITE_POS[1], 
            0f, 
            _scaleClass2,
            Jaylib.WHITE
        );

        final Texture _textureClass3 = TextureId.CLASS_3.getTexture(); 
        final float _scaleClass3 = Config.SCENE_PLAYER_SPRITE_SIZE_SELECT / _textureClass3.height();
        Jaylib.DrawTextureEx(
            _textureClass3,
            SPRITE_POS[2], 
            0f, 
            _scaleClass3,
            Jaylib.WHITE
        );

        for(final Button _button: game.getClassButtons().values()) {
            _button.draw();
        }
    }

    /** Desenha as barras de HP e MP do Player e do Boss */
    private void drawBars() {
        game.getPlayer().drawHP(Config.BAR_POSX_PLAYER);
        game.getPlayer().drawMP(Config.BAR_POSX_PLAYER);
        game.getCurrBoss().drawHP(Config.BAR_POSX_BOSS);
        game.getCurrBoss().drawMP(Config.BAR_POSX_BOSS);
    }

    /**
     * Retorna a cor do jogador, esta que depende se ele está se defendendo ou não
     * @return (Color) a cor do jogador
     */
    private Color getPlayerColor() {
        final int _defendDuration = game.getPlayer().getDefendDuration();
        return (_defendDuration > 0) ? Jaylib.BLUE : Jaylib.WHITE;
    }

    /** Desenha o jogador na tela (a classe do jogador escolhida) */
    private void drawPlayer() {
        final Texture _texturePlayer = game.getPlayer().getTextureId().getTexture();

        // Make sure texture doesn't get distorted if texture's aspect ratio isn't 1:1
        final float _aspectRatio = (float)_texturePlayer.height() / (float)_texturePlayer.width(); 

        final Rectangle _rectSource = new Jaylib.Rectangle(
            0f, 0f, _texturePlayer.width(), _texturePlayer.height()
        );
        
        final Rectangle _rectDest = new Jaylib.Rectangle(
            PLAYER_BATTLE_POS.x(),
            PLAYER_BATTLE_POS.y(),
            Config.SCENE_PLAYER_SPRITE_SIZE_BATTLE,
            Config.SCENE_PLAYER_SPRITE_SIZE_BATTLE * _aspectRatio
        );

        Jaylib.DrawTexturePro(
            _texturePlayer, 
            _rectSource, _rectDest, 
            originVector, 0f, 
            getPlayerColor()
        );
    }

    /**
     * Retorna a cor do chefão dependo de seu estado
     * @return (Color) a cor do chefão 
     */
    private Color getBossColor() {
        final StateType _stateType = game.getCurrBoss().getCurrState();
        final int _defendDuration = game.getCurrBoss().getDefendDuration();

        switch(_stateType) {
            case BERSERK: return Jaylib.RED;
            case WEAK: return Jaylib.GREEN;
            default: 
                return (_defendDuration > 0) ? Jaylib.BLUE : Jaylib.WHITE;
        }
    }

    /** Desenha o chefão atual na tela */
    private void drawBoss() {
        final Boss _boss = game.getCurrBoss();
        final Texture _textureBoss = _boss.getTextureId().getTexture();

        // Make sure texture doesn't get distorted if texture's aspect ratio isn't 1:1
        final float _aspectRatio = (float)_textureBoss.height() / (float)_textureBoss.width(); 

        final Rectangle _rectSource = new Jaylib.Rectangle(
            0f, 0f, _textureBoss.width(), _textureBoss.height()
        );

        final Rectangle _rectDest = new Jaylib.Rectangle(
            BOSS_BATTLE_POS.x(),
            BOSS_BATTLE_POS.y(),
            Config.SCENE_BOSS_SPRITE_SIZE_BATTLE,
            Config.SCENE_BOSS_SPRITE_SIZE_BATTLE * _aspectRatio
        );

        Jaylib.DrawTexturePro(
            _textureBoss, 
            _rectSource, _rectDest, 
            originVector, 0f, 
            getBossColor()
        );
    }

    /** Desenha a tela de batalha entre o jogador e o chefão */
    private void drawBattle() {
        drawPlayer();
        drawBoss();

        game.getPlayer().getStatbox().draw();
        game.getCurrBoss().getStatbox().draw();

        drawBars();
        game.getPlayer().getInventory().draw();
    }

    /**
     * Desenha a janela do jogo dado o estado atual do jogo
     * @param gameState o estado do jogo atual 
     * @param player o jogador 
     * @param currBoss o chefão atual 
     */
    public void drawWindow(final GameState gameState, final Player player, final Boss currBoss) {
        Jaylib.BeginDrawing();
        Jaylib.ClearBackground(Config.SCENE_COLOR_BACKGROUND);

        switch (gameState) {
            case MAIN_MENU:
                drawMainMenu();
                break;

            case SELECTING_CLASS:
                drawClassSelections();
                break;

            case BATTLE_START:
            case TURN_START:
                drawBattle();
                break;

            case TURN_PLAYER_CHOOSE:
                drawBattleButtons();
                drawBattle();
                break;

            case TURN_PLAYER_CHOSEN:
            case TURN_ENEMY_CHOOSE:
            case TURN_ENEMY_CHOSEN:
            case TURN_END:
            case BATTLE_END:
                drawBattle();
                break;

            case GAME_END:
            default: break;
        }
        game.getAlertTextBox().draw();
        Jaylib.EndDrawing();
    }
}
