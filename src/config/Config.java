package config;

import com.raylib.Jaylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Font;

/** Classe para armazenar todas as configurações de estilo das janelas e telas do jogo. */
public class Config {
    /**********************************************************************************/
    // GAME 
    /** Título do jogo */
    public static final String GAME_TITLE = "BOSSFIGHTER";
    /** Versão atual do jogo */
    public static final String VERSION = "1.0.0";

    /**********************************************************************************/
    // GAME WINDOW
    /** Tamanho do comprimento da janela do jogo */
    public static final int WINDOW_WIDTH = 600;
    /** Tamanho da altura da janela do jogo */
    public static final int WINDOW_HEIGHT = 400;
    /** Frames por segundo (taxa de atualização da tela) */
    public static final int FPS = 60; 

     /** Fonte padrão do jogo */
    public static final Font FONT_DEFAULT = Jaylib.GetFontDefault();

    /** espaço entre letras padrão (letter spacing) */
    public static final float FONT_DEFAULT_SPACING = 1.1f;

    /**********************************************************************************/
    // ENTITIES BAR
    /** Cor de preenchimento da barra de HP */
    public static final Color BAR_COLOR_FILLED_HP = Jaylib.DARKGREEN;
    /** Cor de preenchimento da barra de MP */
    public static final Color BAR_COLOR_FILLED_MP = Jaylib.DARKBLUE;
    /** Cor de fundo das barras de HP/MP */
    public static final Color BAR_COLOR_EMPTY = Jaylib.LIGHTGRAY;
    /** Cor de preenchimento da barra de HP quando baixa */
    public static final Color BAR_COLOR_LOW_HP = Jaylib.RED;
    /** Cor de preenchimento da barra de MP quando baixa */
    public static final Color BAR_COLOR_LOW_MP = Jaylib.DARKPURPLE;
    /** Cor de texto das barras de HP/MP */
    public static final Color BAR_COLOR_TEXT = Jaylib.WHITE;

    /** Comprimento da barra de HP */
    public static final float BAR_WIDTH_HP = (float)WINDOW_WIDTH / 6;
    /** Altura da barra de HP */
    public static final float BAR_HEIGHT_HP = (float)WINDOW_HEIGHT / 24;
    /** Comprimento da barra de MP */
    public static final float BAR_WIDTH_MP = BAR_WIDTH_HP;
    /** Altura da barra de MP */
    public static final float BAR_HEIGHT_MP = BAR_HEIGHT_HP;

    /** Tamanho das margens laterais da barra HP/MP */
    public static final float BAR_MARGIN = 4.0f;
    /** Posição geral y das barras HP/MP */
    public static final float BAR_POS_Y = (float)WINDOW_HEIGHT * 0.02f;
    
    /** Posição y da barra de HP */
    public static final float BAR_POS_Y_HP = BAR_POS_Y;
    /** Posição y da barra de MP */
    public static final float BAR_POS_Y_MP = BAR_POS_Y + BAR_HEIGHT_MP + BAR_MARGIN;

    /** Tamanho do texto das barras de HP/MP */
    public static final int BAR_TEXT_SIZE = WINDOW_HEIGHT / 24;
    
    /** Posição x das barras de HP/MP do jogador */
    public static final float BAR_POSX_PLAYER = ((float)WINDOW_WIDTH / 2) - (3 * BAR_WIDTH_HP / 2);
    /** Posição x das barras de HP/MP dos chefões */
    public static final float BAR_POSX_BOSS = ((float)WINDOW_WIDTH / 2) + (BAR_WIDTH_HP / 2);

    /**********************************************************************************/
    // MOUSE 
    /** Botão esquerdo do mouse */
    public static final int MOUSE_BUTTON_LEFT = Jaylib.MOUSE_BUTTON_LEFT;
    /** Botão direito do mouse */
    public static final int MOUSE_BUTTON_RIGHT = Jaylib.MOUSE_BUTTON_RIGHT;

    /**********************************************************************************/
    // INVENTORY
    /** Tamanho dos slots (espaços) do inventário */
    public static final float INVENTORY_SLOT_SIZE = (float)WINDOW_WIDTH / 12;
    // Left of screen
    /** posição x do inventário */
    public static final float INVENTORY_POS_X = 0.0f;
    // Bottom of screen
    /** Posição y do inventário */
    public static final float INVENTORY_POS_Y = (float)WINDOW_HEIGHT - INVENTORY_SLOT_SIZE;
    /** Tamanho das bordas laterais do inventário */
    public static final float INVENTORY_BORDER_SIZE = 4.0f;
    
    /** Cor de fundo dos slots do inventário */
    public static final Color INVENTORY_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593);
    /** Cor das bordas do inventário */
    public static final Color INVENTORY_COLOR_BORDER = Jaylib.GetColor(1631265023);

    /**********************************************************************************/
    // ITEMS INFOBOX 
    /** Texto para a estatística do dano do item */
    public static final String INFOBOX_TEXT_DMG    = "DMG: ";
    /** Texto para a estatística da defesa do item */
    public static final String INFOBOX_TEXT_DEF    = "DEF: ";
    /** Texto para a estatística da precisão do item */
    public static final String INFOBOX_TEXT_ACC    = "ACC: ";
    /** Texto para a estatística da chance de crítico do item */
    public static final String INFOBOX_TEXT_CRITC  = "CRT%: ";
    /** Texto para a estatística do multiplicado de crítico do item */
    public static final String INFOBOX_TEXT_CRITM  = "CRT*: ";
    /** Texto para a estatística de HP do item */
    public static final String INFOBOX_TEXT_HP     = "HP: ";
    /** Texto para a estatística de MP do item */
    public static final String INFOBOX_TEXT_MP     = "MP: ";
    
    /** Largura da caixa de informações do item */
    public static final float INFOBOX_WIDTH = (float)WINDOW_WIDTH / 2;
    /** Altura da caixa de informações do item */
    public static final float INFOBOX_HEIGHT = (float)WINDOW_HEIGHT / 3.2f;
    
    /** Tamanho máximo de carácteres em uma linha da caixa de informações do item */
    public static final int INFOBOX_WRAP_SIZE = 40;
    /** Tamanho da fonte da caixa de informações do item */
    public static final int INFOBOX_FONT_SIZE = WINDOW_HEIGHT / 26;
    /** Tamanho das bordas laterais da caixa de informações */
    public static final int INFOBOX_BORDER_SIZE = 4;
    /** Tamanho das margens da caixa de informações */
    public static final int INFOBOX_MARGIN = 2;
    
    //Jaylib.GetColor(-1772078593),
    /** Cor de fundo da caixa de informações */
    public static final Color INFOBOX_COLOR_BACKGROUND = Jaylib.ColorAlpha(Jaylib.BLACK, 0.9f);
    /** Cor das bordas da caixa de informações */
    public static final Color INFOBOX_COLOR_BORDER = Jaylib.GetColor(1631265023);
    /** Cor do texto da caixa de informações */
    public static final Color INFOBOX_COLOR_TEXT = Jaylib.WHITE;
    /** Cor da linha do separator da caixa de informações */
    public static final Color INFOBOX_COLOR_SEPARATOR = Jaylib.ColorAlpha(Jaylib.WHITE, 0.20f);

    /** Fonte do texto da caixa de informações */
    public static final Font  INFOBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) do texto da caixa de informações */
    public static float INFOBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    /**********************************************************************************/
    // STABOX
    /** Texto para a estatística de level do jogador */
    public static final String STATBOX_TEXT_LVL = "LVL: ";
    /** Texto para a estatística de experiência do jogador */
    public static final String STATBOX_TEXT_XP = "XP: ";
    /** Texto para a estatística de dano da entidade */
    public static final String STATBOX_TEXT_DMG = "DMG: ";
    /** Texto para a estatística de defesa da entidade */
    public static final String STATBOX_TEXT_DEF = "DEF: ";
    /** Texto para a estatística de multiplicador de defesa da entidade */
    public static final String STATBOX_TEXT_DEFMULT = "DEF*: ";
    /** Texto para a estatística de chance de crítico da entidade */
    public static final String STATBOX_TEXT_CRIT = "CRIT%: ";
    /** Texto para a estatística de multiplicador de crítico da entidade */
    public static final String STATBOX_TEXT_CRITMULT = "CRIT*: ";
    /** Texto para a estatística de precisão (probabilidade de acerto) da entidade */
    public static final String STATBOX_TEXT_ACC = "HIT%: ";
    
    /** Largura da caixa de estatística */
    public static final float STATBOX_WIDTH = (float)WINDOW_WIDTH / 6;
    /** Altura da caixa de estatística */
    public static final float STATBOX_HEIGHT = (float)WINDOW_HEIGHT / 3;
    /** Posição y da caixa de estatística geral */
    public static final float STATBOX_POSY = 0.0f;
    /** Posição x da caixa de estatística do jogador */
    public static final float STATBOX_PLAYER_POSX = 0.0f; // Top left corner
    /** Posição y da caixa de estatística do jogador */
    public static final float STATBOX_PLAYER_POSY = STATBOX_POSY;
    /** Posição x da caixa de estatística do chefão */
    public static final float STATBOX_BOSS_POSX = (float)WINDOW_WIDTH - STATBOX_WIDTH; // Top right corner
    /** Posição y da caixa de estatística do chefão */
    public static final float STATBOX_BOSS_POSY = STATBOX_POSY;
    
    /** Tamanho da fonte da caixa de estatística */
    public static final int STATBOX_FONT_SIZE = WINDOW_HEIGHT / 26;
    /** Tamanho das bordas laterais da caixa de estatísticas */
    public static final int STATBOX_BORDER_SIZE = 4;
    /** Tamanho das margens da caixa de estatística */
    public static final int STATBOX_MARGIN = 4;

    /** Cor de fundo da caixa de estatística */
    public static final Color STATBOX_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593);
    /** Cor das bordas da caixa de estatística */
    public static final Color STATBOX_COLOR_BORDER = Jaylib.GetColor(1631265023);
    /** Cor do texto da caixa de estatística */
    public static final Color STATBOX_COLOR_TEXT = Jaylib.WHITE;

    /** Fonte do texto da caixa de estatística */
    public static final Font STATBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) do texto da caixa de estatística */
    public static final float STATBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    /**********************************************************************************/
    // TEXTBOX
    /** Tamanho da fonte da caixa de texto */
    public static final int TEXTBOX_FONT_SIZE = WINDOW_HEIGHT / 20;
    /** Tamanho das bordas da caixa de texto */
    public static final int TEXTBOX_BORDER_SIZE = 4;
    
    /** Largura da caixa de texto */
    public static final float TEXTBOX_WIDTH = (float)WINDOW_WIDTH / 2;
    /** Altura da caixa de texto */
    public static final float TEXTBOX_HEIGHT = (float)WINDOW_HEIGHT / 10;
    /** Posição x da caixa de texto */    
    public static final float TEXTBOX_POSX = ((float)WINDOW_WIDTH / 2) - (TEXTBOX_WIDTH / 2);
    /** Posição y da caixa de texto */
    public static final float TEXTBOX_POSY = ((float)WINDOW_HEIGHT / 2) - (TEXTBOX_HEIGHT / 2);

    /** Cor do overlay (fundo que cobre toda a tela) da caixa de texto */
    public static final Color TEXTBOX_COLOR_BACKPANEL = Jaylib.ColorAlpha(Jaylib.RAYWHITE, 0.2f);
    /** Cor de fundo da caixa de texto */
    public static final Color TEXTBOX_COLOR_BACKGROUND = Jaylib.GetColor(-1772078593);
    /** Cor das bordas laterais da caixa de texto */
    public static final Color TEXTBOX_COLOR_BORDER = Jaylib.BLACK;//Jaylib.GetColor(1631265023),
    /** Cor do texto da caixa de texto */
    public static final Color TEXTBOX_COLOR_TEXT = Jaylib.WHITE;

    /** Texto para início de uma batalha Player x Boss */
    public static final String TEXTBOX_TEXT_BATTLE = "FIGHT!";
    /** Texto para quando Player/Boss errarem ataque */
    public static final String TEXTBOX_TEXT_MISS = "MISS!";
    /** Texto para quando Player é derrotado (game over) */
    public static final String TEXTBOX_TEXT_LOSE = "YOU DIED!";
    /** Texto para quando jogador vence o jogo */
    public static final String TEXTBOX_TEXT_WIN = "YOU WIN!";

    /** Fonte do texto da caixa de texto */
    public static final Font TEXTBOX_FONT = FONT_DEFAULT;
    /** Espaço entre letras (letter spacing) da caixa de texto */
    public static final float TEXTBOX_FONT_SPACING = FONT_DEFAULT_SPACING;

    /** Cor de fundo para quando o jogador erra ataque */
    public static final Color TEXTBOX_COLOR_PLAYER = Jaylib.DARKBLUE;
    /** Cor de fundo para quando o chefão erra ataque */
    public static final Color TEXTBOX_COLOR_BOSS = Jaylib.DARKPURPLE;

    /**********************************************************************************/
    // GAME SCENE 
    /** Tamanho do título do menu inicial */
    public static final int SCENE_FONT_SIZE_TITLE = WINDOW_HEIGHT / 10;
    /** Tamanho do título do menu de seleção de classes do jogador */
    public static final int SCENE_FONT_SIZE_SELECTION = WINDOW_HEIGHT / 12; 
    /** Posição y dos titulos dos menus */
    public static final int SCENE_TITLE_POSY = WINDOW_HEIGHT / 10;

    /** Texto para o título do menu de seleção de classes do jogador */
    public static final String SCENE_TEXT_SELECTION = "SELECT YOUR CLASS";
    
    /** Posição y dos sprites das classes do jogador */
    public static final float SCENE_SELECT_CLASS_POSY = (WINDOW_HEIGHT * 0.4f);
    // percentage of the screen height that the sprite will take up
    /** escala do sprite na tela de seleção de classes do jogador */
    public static final float SCENE_PLAYER_SPRITE_SIZE_SELECT = (WINDOW_HEIGHT * 0.3f); 
    /** escala do sprite na tela de batalha Player vs Boss */
    public static final float SCENE_PLAYER_SPRITE_SIZE_BATTLE = (WINDOW_HEIGHT * 0.25f);
    /** escala do sprite do chefão na tela de batalha Player x Boss */
    public static final float SCENE_BOSS_SPRITE_SIZE_BATTLE = (WINDOW_HEIGHT * 0.35f);

    /**  Posição x para iniciar o desenho dos sprites das classes de jogador */
    public static final float SCENE_SPRITES_DISTANCEX_START = 0.1f; 
    /** Distância (espaçamento) x entre os sprites das classes de jogador */
    public static final float SCENE_SPRITES_DISTANCEX_BETWEEN = 0.3f;

    // unsigned hexadecilmal for RGB value #969696
    /** Cor de fundo da tela de batalha Player x Boss */
    public static final Color SCENE_COLOR_BACKGROUND = Jaylib.GetColor(-1768515841); 
}
