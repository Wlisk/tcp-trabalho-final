package scene.box;

import items.consumable.Consumable;
import items.weapon.Weapon;
import items.armor.Armor;
import items.Item;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Jaylib.Vector2;

import config.Config;
import static config.Config.WINDOW_WIDTH;

/** Classe para mostrar caixas de informações de items no inventário do jogador */
public class Infobox {
    private static final int textSpacingY = (int)Jaylib.MeasureTextEx(
        Config.INFOBOX_FONT, 
        Config.INFOBOX_TEXT_DMG,  // any text to compute spacing
        (float)Config.INFOBOX_FONT_SIZE, 
        0.0f
    ).y();

    // border rectangle
    private final Rectangle rectOuterBox = new Jaylib.Rectangle(
        0.0f, 0.0f,
        Config.INFOBOX_WIDTH, 
        Config.INFOBOX_HEIGHT
    );

    // content rectangle
    private final Rectangle rectInnerBox = new Jaylib.Rectangle(
        0.0f, 0.0f,
        Config.INFOBOX_WIDTH - Config.INFOBOX_BORDER_SIZE * 2,
        Config.INFOBOX_HEIGHT - Config.INFOBOX_BORDER_SIZE * 2
    );

    private static final int SEPARATOR_POINT_START = 0;
    private static final int SEPARATOR_POINT_END = 1;

    private final Vector2[] separatorLinePoints = new Vector2[]{
        new Vector2(0.0f, 0.0f),
        new Vector2(0.0f, 0.0f)
    };

    /** Calcula a posição do retangulo da borda */
    private void computeOuterRect() {
        Raylib.Vector2 mousePos = Jaylib.GetMousePosition();

        if(mousePos.x() <= (WINDOW_WIDTH - Config.INFOBOX_WIDTH)) {
            rectOuterBox.x(mousePos.x());
        } 
        else rectOuterBox.x(mousePos.x() - Config.INFOBOX_WIDTH);

        rectOuterBox.y(mousePos.y() - Config.INFOBOX_HEIGHT);
    }

    /** Calcula a posição do retangulo interno onde as informações erão mostradas */
    private void computeInnerRect() {
        rectInnerBox.x(rectOuterBox.x() + Config.INFOBOX_BORDER_SIZE);
        rectInnerBox.y(rectOuterBox.y() + Config.INFOBOX_BORDER_SIZE);
    }

    /** Calcula a posição da linha que separa o nome do item das demais informações */
    private void computeSeparatorPos(float startY) {
        separatorLinePoints[SEPARATOR_POINT_START].x(
            rectInnerBox.x()
        );
        separatorLinePoints[SEPARATOR_POINT_END].x(
            (rectInnerBox.x() + Config.INFOBOX_WIDTH) - (Config.INFOBOX_BORDER_SIZE * 2)
        );

        final float _y = startY + textSpacingY + Config.INFOBOX_MARGIN;

        separatorLinePoints[SEPARATOR_POINT_START].y(_y);
        separatorLinePoints[SEPARATOR_POINT_END].y(_y);
    }

    /** 
     * Desenha a caixa de informações do item (só os retangulos)
     * @param plusSizeY fator de aumento da altura da caixa
     */
    private void drawBox(int plusSizeY) {
        final float _oldOuterHeight = rectOuterBox.height();
        final float _oldInnerHeight = rectInnerBox.height();

        rectOuterBox.height(_oldOuterHeight + plusSizeY);
        rectInnerBox.height(_oldInnerHeight + plusSizeY);

        //Jaylib.DrawRectangleRec(rectOuterBox, Config.INFOBOX_COLOR_BORDER);
        Jaylib.DrawRectangleLinesEx(
            rectOuterBox, 
            Config.INFOBOX_BORDER_SIZE, 
            Config.INFOBOX_COLOR_BORDER
        );
        Jaylib.DrawRectangleRec(rectInnerBox, Config.INFOBOX_COLOR_BACKGROUND);

        rectOuterBox.height(_oldOuterHeight);
        rectInnerBox.height(_oldInnerHeight);
    }

    /** 
     * Desenha a caixa de informações do item dependendo da posição do mouse 
     * @param item o item a ser mostrado as informações 
     * @see items.Item
     */
    public void drawMouseOver(Item item) {
        if(item == null) return;

        String[] _textLines = null;
        String _itemType = null;

        switch(item.getItemType()) {
            case WEAPON: 
                _textLines = ((Weapon) item).getTextLines();
                _itemType = ((Weapon) item).getType().toString();
                break;
                
            case ARMOR: 
                _textLines = ((Armor) item).getTextLines();
                _itemType = ((Armor) item).getType().toString();
                break;

            case CONSUMABLE: 
                _textLines = ((Consumable) item).getTextLines();
                _itemType = ((Consumable) item).getType().toString();
                break;

            default: return;
        }

        computeOuterRect();
        computeInnerRect();

        final int _startPosX = (int)(rectInnerBox.x() + Config.INFOBOX_MARGIN);
        final int _startPosY = (int)(rectInnerBox.y() + Config.INFOBOX_MARGIN);

        computeSeparatorPos(_startPosY);

        final String _description = _textLines[_textLines.length - 1];
        final int _numLineBreaks = _description.split("\n").length;
        final int _plusSizeY = (_numLineBreaks > 1) ? 
            Config.INFOBOX_FONT_SIZE * _numLineBreaks : 0;

        Raylib.BeginBlendMode(Raylib.BLEND_ALPHA);
        drawBox(_plusSizeY);

        Jaylib.DrawText(
            item.getName() + "  ( " + _itemType + " )", 
            _startPosX, 
            _startPosY,
            Config.INFOBOX_FONT_SIZE,
            Config.INFOBOX_COLOR_TEXT
        );

        Jaylib.DrawLine(
            (int)separatorLinePoints[SEPARATOR_POINT_START].x(), 
            (int)separatorLinePoints[SEPARATOR_POINT_START].y(), 
            (int)separatorLinePoints[SEPARATOR_POINT_END].x(), 
            (int)separatorLinePoints[SEPARATOR_POINT_END].y(), 
            Config.INFOBOX_COLOR_SEPARATOR
        );

        int i = 0;
        final int _verticalSpacing = 2;
        final int _posY = (int)separatorLinePoints[SEPARATOR_POINT_END].y() 
            + Config.INFOBOX_MARGIN
            + _verticalSpacing;

        for(final String _textLine: _textLines) {
            Jaylib.DrawTextEx(
                Config.INFOBOX_FONT, 
                _textLine,
                new Vector2(
                    _startPosX,
                    _posY + (textSpacingY * i++)
                ),
                Config.INFOBOX_FONT_SIZE,
                Config.INFOBOX_FONT_SPACING, 
                Config.INFOBOX_COLOR_TEXT
            );
        }
        Raylib.EndBlendMode();
    }
}
    

    

    
