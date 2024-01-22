package config;

import com.raylib.Jaylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Font;

public class Config {
    /**********************************************************************************/
    // GAME 
    /** Título do jogo */
    public static final String GAME_TITLE = "BOSSFIGHTER";
    /** Versão atual do jogo */
    public static final String VERSION = "1.0.0";

    /**********************************************************************************/
    // GAME WINDOW
    public static final int 
        /** Tamanho do comprimento da janela do jogo */
        WINDOW_WIDTH = 600, 
        /** Tamanho da altura da janela do jogo */
        WINDOW_HEIGHT = 400, 
        /** Frames por segundo (taxa de atualização da tela) */
        FPS = 60; 

    public static final Font
        /** Fonte padrão do jogo */
        FONT_DEFAULT = Jaylib.GetFontDefault();

    public static final float 
        /** espaço entre letras padrão (letter spacing) */
        FONT_DEFAULT_SPACING = 1.1f;

    /**********************************************************************************/
    // ENTITIES BAR
    public static final Color 
        /** Cor de preenchimento da barra de HP */
        BAR_COLOR_FILLED_HP = Jaylib.DARKGREEN,
        /** Cor de preenchimento da barra de MP */
        BAR_COLOR_FILLED_MP = Jaylib.DARKBLUE, 
        /** Cor de fundo das barras de HP/MP */
        BAR_COLOR_EMPTY = Jaylib.LIGHTGRAY,
        /** Cor de preenchimento da barra de HP quando baixa */
        BAR_COLOR_LOW_HP = Jaylib.RED,
        /** Cor de preenchimento da barra de MP quando baixa */
        BAR_COLOR_LOW_MP = Jaylib.DARKPURPLE, 
        /** Cor de texto das barras de HP/MP */
        BAR_COLOR_TEXT = Jaylib.WHITE;

    public static final float
        /** Comprimento da barra de HP */
        BAR_WIDTH_HP = (float)WINDOW_WIDTH / 6,
        /** Altura da barra de HP */
        BAR_HEIGHT_HP = (float)WINDOW_HEIGHT / 24,
        /** Comprimento da barra de MP */
        BAR_WIDTH_MP = BAR_WIDTH_HP,
        /** Altura da barra de MP */
        BAR_HEIGHT_MP = BAR_HEIGHT_HP;

    public static final float
        /** Tamanho das margens laterais da barra HP/MP */
        BAR_MARGIN = 4.0f, 
        /** Posição geral y das barras HP/MP */
        BAR_POS_Y = (float)WINDOW_HEIGHT * 0.02f;

    public static final float
        /** Posição y da barra de HP */
        BAR_POS_Y_HP = BAR_POS_Y, 
        /** Posição y da barra de MP */
        BAR_POS_Y_MP = BAR_POS_Y + BAR_HEIGHT_MP + BAR_MARGIN;

    /** Tamanho do texto das barras de HP/MP */
    public static final int BAR_TEXT_SIZE = WINDOW_HEIGHT / 24;

    public static final float
        /** Posição x das barras de HP/MP do jogador */
        BAR_POSX_PLAYER = ((float)WINDOW_WIDTH / 2) - (3 * BAR_WIDTH_HP / 2), 
        /** Posição x das barras de HP/MP dos chefões */
        BAR_POSX_BOSS = ((float)WINDOW_WIDTH / 2) + (BAR_WIDTH_HP / 2);

    /**********************************************************************************/
    // MOUSE 
    public static final int 
        /** Botão esquerdo do mouse */
        MOUSE_BUTTON_LEFT = Jaylib.MOUSE_BUTTON_LEFT,
        /** Botão direito do mouse */
        MOUSE_BUTTON_RIGHT = Jaylib.MOUSE_BUTTON_RIGHT;

    /**********************************************************************************/
    // INVENTORY
    public static final float
        /** Tamanho dos slots (espaços) do inventário */
        INVENTORY_SLOT_SIZE = (float)WINDOW_WIDTH / 12,
        // Left of screen
        /** posição x do inventário */
        INVENTORY_POS_X = 0.0f,
        // Bottom of screen
        /** Posição y do inventário */
        INVENTORY_POS_Y = (float)WINDOW_HEIGHT - INVENTORY_SLOT_SIZE, 
        /** Tamanho das bordas laterais do inventário */
        INVENTORY_BORDER_SIZE = 4.0f;

    public static final Color
        /** Cor de fundo dos slots do inventário */
        INVENTORY_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        /** Cor das bordas do inventário */
        INVENTORY_COLOR_BORDER = Jaylib.GetColor(1631265023);

    /**********************************************************************************/
    // ITEMS INFOBOX 
    public static final String
        /** Texto para a estatística do dano do item */
        INFOBOX_TEXT_DMG    = "DMG: ",
        /** Texto para a estatística da defesa do item */
        INFOBOX_TEXT_DEF    = "DEF: ",
        /** Texto para a estatística da precisão do item */
        INFOBOX_TEXT_ACC    = "ACC: ",
        /** Texto para a estatística da chance de crítico do item */
        INFOBOX_TEXT_CRITC  = "CRT%: ", 
        /** Texto para a estatística do multiplicado de crítico do item */
        INFOBOX_TEXT_CRITM  = "CRT*: ",
        /** Texto para a estatística de HP do item */
        INFOBOX_TEXT_HP     = "HP: ",
        /** Texto para a estatística de MP do item */
        INFOBOX_TEXT_MP     = "MP: ";
        
    public static final float
        /** Largura da caixa de informações do item */
        INFOBOX_WIDTH = (float)WINDOW_WIDTH / 2,
        /** Altura da caixa de informações do item */
        INFOBOX_HEIGHT = (float)WINDOW_HEIGHT / 3.2f;

    public static final int
        /** Tamanho máximo de carácteres em uma linha da caixa de informações do item */
        INFOBOX_WRAP_SIZE = 40,
        /** Tamanho da fonte da caixa de informações do item */
        INFOBOX_FONT_SIZE = WINDOW_HEIGHT / 26,
        /** Tamanho das bordas laterais da caixa de informações */
        INFOBOX_BORDER_SIZE = 4,
        /** Tamanho das margens da caixa de informações */
        INFOBOX_MARGIN = 2;

    public static final Color
        //Jaylib.GetColor(-1772078593),
        /** Cor de fundo da caixa de informações */
        INFOBOX_COLOR_BACKGROUND = Jaylib.ColorAlpha(Jaylib.BLACK, 0.9f),
        /** Cor das bordas da caixa de informações */
        INFOBOX_COLOR_BORDER = Jaylib.GetColor(1631265023),
        /** Cor do texto da caixa de informações */
        INFOBOX_COLOR_TEXT = Jaylib.WHITE,
        /** Cor da linha do separator da caixa de informações */
        INFOBOX_COLOR_SEPARATOR = Jaylib.ColorAlpha(Jaylib.WHITE, 0.20f);

    /** Fonte do texto da caixa de informações */
    public static final Font  INFOBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) do texto da caixa de informações */
    public static float INFOBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    /**********************************************************************************/
    // STABOX
    public static final String 
        /** Texto para a estatística de level do jogador */
        STATBOX_TEXT_LVL = "LVL: ",
        /** Texto para a estatística de experiência do jogador */
        STATBOX_TEXT_XP = "XP: ",
        /** Texto para a estatística de dano da entidade */
        STATBOX_TEXT_DMG = "DMG: ",
        /** Texto para a estatística de defesa da entidade */
        STATBOX_TEXT_DEF = "DEF: ",
        /** Texto para a estatística de multiplicador de defesa da entidade */
        STATBOX_TEXT_DEFMULT = "DEF*: ",
        /** Texto para a estatística de chance de crítico da entidade */
        STATBOX_TEXT_CRIT = "CRIT%: ",
        /** Texto para a estatística de multiplicador de crítico da entidade */
        STATBOX_TEXT_CRITMULT = "CRIT*: ",
        /** Texto para a estatística de precisão (probabilidade de acerto) da entidade */
        STATBOX_TEXT_ACC = "HIT%: ";

    public static final float
        /** Largura da caixa de estatística */
        STATBOX_WIDTH = (float)WINDOW_WIDTH / 6,
        /** Altura da caixa de estatística */
        STATBOX_HEIGHT = (float)WINDOW_HEIGHT / 3,
        /** Posição y da caixa de estatística geral */
        STATBOX_POSY = 0.0f,
        /** Posição x da caixa de estatística do jogador */
        STATBOX_PLAYER_POSX = 0.0f, // Top left corner
        /** Posição y da caixa de estatística do jogador */
        STATBOX_PLAYER_POSY = STATBOX_POSY, 
        /** Posição x da caixa de estatística do chefão */
        STATBOX_BOSS_POSX = (float)WINDOW_WIDTH - STATBOX_WIDTH, // Top right corner
        /** Posição y da caixa de estatística do chefão */
        STATBOX_BOSS_POSY = STATBOX_POSY;

    public static final int
        /** Tamanho da fonte da caixa de estatística */
        STATBOX_FONT_SIZE = WINDOW_HEIGHT / 26,
        /** Tamanho das bordas laterais da caixa de estatísticas */
        STATBOX_BORDER_SIZE = 4,
        /** Tamanho das margens da caixa de estatística */
        STATBOX_MARGIN = 4;

    public static final Color
        /** Cor de fundo da caixa de estatística */
        STATBOX_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        /** Cor das bordas da caixa de estatística */
        STATBOX_COLOR_BORDER = Jaylib.GetColor(1631265023),
        /** Cor do texto da caixa de estatística */
        STATBOX_COLOR_TEXT = Jaylib.WHITE;

    /** Fonte do texto da caixa de estatística */
    public static final Font STATBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) do texto da caixa de estatística */
    public static final float STATBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    /**********************************************************************************/
    // TEXTBOX
    public static final int
        /** Tamanho da fonte da caixa de texto */
        TEXTBOX_FONT_SIZE = WINDOW_HEIGHT / 20,
        /** Tamanho das bordas da caixa de texto */
        TEXTBOX_BORDER_SIZE = 4;

    public static final float
        /** Largura da caixa de texto */
        TEXTBOX_WIDTH = (float)WINDOW_WIDTH / 2,
        /** Altura da caixa de texto */
        TEXTBOX_HEIGHT = (float)WINDOW_HEIGHT / 10,
        /** Posição x da caixa de texto */    
        TEXTBOX_POSX = ((float)WINDOW_WIDTH / 2) - (TEXTBOX_WIDTH / 2),
        /** Posição y da caixa de texto */
        TEXTBOX_POSY = ((float)WINDOW_HEIGHT / 2) - (TEXTBOX_HEIGHT / 2);

    public static final Color
        /** Cor do overlay (fundo que cobre toda a tela) da caixa de texto */
        TEXTBOX_COLOR_BACKPANEL = Jaylib.ColorAlpha(Jaylib.RAYWHITE, 0.2f),
        /** Cor de fundo da caixa de texto */
        TEXTBOX_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593),
        /** Cor das bordas laterais da caixa de texto */
        TEXTBOX_COLOR_BORDER = Jaylib.BLACK,//Jaylib.GetColor(1631265023),
        /** Cor do texto da caixa de texto */
        TEXTBOX_COLOR_TEXT = Jaylib.WHITE;

    public static final String
        /** Texto para início de uma batalha Player x Boss */
        TEXTBOX_TEXT_BATTLE = "FIGHT!",
        /** Texto para quando Player/Boss errarem ataque */
        TEXTBOX_TEXT_MISS = "MISS!",
        /** Texto para quando Player é derrotado (game over) */
        TEXTBOX_TEXT_LOSE = "YOU DIED!",
        /** Texto para quando jogador vence o jogo */
        TEXTBOX_TEXT_WIN = "YOU WIN!";

    /** Fonte do texto da caixa de texto */
    public static final Font TEXTBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) da caixa de texto */
    public static final float TEXTBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    public static final Color 
        /** Cor de fundo para quando o jogador erra ataque */
        TEXTBOX_COLOR_PLAYER = Jaylib.DARKBLUE,
        /** Cor de fundo para quando o chefão erra ataque */
        TEXTBOX_COLOR_BOSS = Jaylib.DARKPURPLE;

    /**********************************************************************************/
    // GAME SCENE 
    public static final int 
        /** Tamanho do título do menu inicial */
        SCENE_FONT_SIZE_TITLE = WINDOW_HEIGHT / 10, 
        /** Tamanho do título do menu de seleção de classes do jogador */
        SCENE_FONT_SIZE_SELECTION = WINDOW_HEIGHT / 12, 
        /** Posição y dos titulos dos menus */
        SCENE_TITLE_POSY = WINDOW_HEIGHT / 10;

    /** Texto para o título do menu de seleção de classes do jogador */
    public static final String SCENE_TEXT_SELECTION = "SELECT YOUR CLASS";
    
    public static final float 
        /** Posição y dos sprites das classes do jogador */
        SCENE_SELECT_CLASS_POSY = (WINDOW_HEIGHT * 0.4f),
        // percentage of the screen height that the sprite will take up
        /** escala do sprite na tela de seleção de classes do jogador */
        SCENE_PLAYER_SPRITE_SIZE_SELECT = (WINDOW_HEIGHT * 0.3f), 
        /** escala do sprite na tela de batalha Player vs Boss */
        SCENE_PLAYER_SPRITE_SIZE_BATTLE = (WINDOW_HEIGHT * 0.25f),
        /** escala do sprite do chefão na tela de batalha Player x Boss */
        SCENE_BOSS_SPRITE_SIZE_BATTLE = (WINDOW_HEIGHT * 0.35f);

    public static final float 
        /**  Posição x para iniciar o desenho dos sprites das classes de jogador */
        SCENE_SPRITES_DISTANCEX_START = 0.1f, 
        /** Distância (espaçamento) x entre os sprites das classes de jogador */
        SCENE_SPRITES_DISTANCEX_BETWEEN = 0.3f;

    // unsigned hexadecilmal for RGB value #969696
    /** Cor de fundo da tela de batalha Player x Boss */
    public static final Color SCENE_COLOR_BACKGROUND = Jaylib.GetColor(-1768515841); 
}
