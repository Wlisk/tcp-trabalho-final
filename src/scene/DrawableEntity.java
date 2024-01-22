package scene;

import com.raylib.Jaylib.Rectangle;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Vector2;

import static com.raylib.Raylib.MeasureTextEx;
import static com.raylib.Raylib.DrawText;
import static com.raylib.Raylib.GetFontDefault;
import static com.raylib.Raylib.DrawRectangleRec;

import config.Config;
import entities.Entity;

/** 
 * Classe proxy para desenhar as entidades Player e Boss.
 * <p>
 * Essa classe age como um proxy (um intermediador), assim alterações no código de desenho 
 * não precisam estar nessas classes e portanto as alteações nelas são mínimas ou nulas.
 * @see entities.Entity
 * @see entities.player.Player 
 * @see entities.boss.Boss 
 */
public class DrawableEntity extends Entity {
    private Rectangle recBarHP, recBarMP;

    //@param imageSrc o caminho para a textura da imagem 
    /**
     * Construtor da classe DrawableEntity, precisa dos parâmetros para 
     * poder repassá-los para a super classe
     * @param name o nome da entidade
     * @see entities.Entity
     */
    public DrawableEntity(String name/*, String imageSrc*/) {
        super(name/*, imageSrc*/);

        recBarHP = new Rectangle(0.0f, Config.BAR_POS_Y_HP, Config.BAR_WIDTH_HP, Config.BAR_HEIGHT_HP);
        recBarMP = new Rectangle(0.0f, Config.BAR_POS_Y_MP, Config.BAR_WIDTH_MP, Config.BAR_HEIGHT_MP);
    }

    /**
     * Desenha a barra de HP da entidade na tela
     * @param posX a posição x onde irá desenhar
     * @see #drawBar(Rectangle, Color, int, int)
     */
    public void drawHP(float posX) {
        final Color _fillColor = 
            isLowHP() ? Config.BAR_COLOR_LOW_HP : Config.BAR_COLOR_FILLED_HP;

        recBarHP.x(posX);
        drawBar(recBarHP, _fillColor, getCurrHP(), getMaxHP());
    }

    /**
     * Desenha a barra de MP da entidade na tela
     * @param posX a posição x onde irá desenhar
     * @see #drawBar(Rectangle, Color, int, int)
     */
    public void drawMP(float posX) {
        final Color _fillColor = 
            canSuper() ? Config.BAR_COLOR_FILLED_MP : Config.BAR_COLOR_LOW_MP; 

        recBarMP.x(posX);
        drawBar(recBarMP, _fillColor, getCurrMP(), getMaxMP());
    }

    /**
     * Computa e desenha a barra (HP/MP/etc.) na tela
     * @param rect retângulo da posição e tamanho da barra
     * @param fillColor cor de preenchimento da barra
     * @param currVal o valor corrente/atual para preenchimento da barra
     * @param maxVal o valor máximo para preenchimento da barra 
     * @see com.raylib.Jaylib.Rectangle
     * @see com.raylib.Raylib.Color
     */
    private void drawBar(Rectangle rect, Color fillColor, int currVal, int maxVal) {
        DrawRectangleRec(rect, Config.BAR_COLOR_EMPTY);

        // normalize values
        final int _currValTemp = (currVal < 0) ? 0 : currVal;
        final int _maxVal = (maxVal == 0) ? 1 : Math.abs(maxVal);

        final int _currVal = (_currValTemp > _maxVal) ? _maxVal : _currValTemp;
        final float _ratio = (float)_currVal / (float)_maxVal;

        final String _text = Integer.toString(_currVal) + "/" + Integer.toString(_maxVal);

        final Vector2 _textVec = MeasureTextEx(
            GetFontDefault(), _text, Config.BAR_TEXT_SIZE, 0
        );

        final float 
            _centerBarX = rect.width() / 2, 
            _centerBarY = rect.height() / 2, 
            _centerTextX = _textVec.x() / 2, 
            _centerTextY = _textVec.y() / 2;

        final float 
            _textMiddleBarX = _centerBarX - _centerTextX,
            _textMiddleBarY = _centerBarY - _centerTextY;

        final float 
            _centerX = rect.x() + _textMiddleBarX, 
            _centerY = rect.y() + _textMiddleBarY;
        
        final float _oldWitdh = rect.width();
        rect.width( rect.width() * _ratio );

        // Draws the filled in rectangle on top of the empty one
        DrawRectangleRec(rect, fillColor);
        rect.width(_oldWitdh);

        DrawText(
            _text, 
            (int)_centerX, (int)_centerY, 
            Config.BAR_TEXT_SIZE, 
            Config.BAR_COLOR_TEXT
        );
    }
}
