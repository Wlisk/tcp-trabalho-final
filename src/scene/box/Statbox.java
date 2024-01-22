package scene.box;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Jaylib.Vector2;

import config.Config;
import entities.boss.Boss;
import entities.Entity;
import entities.player.Player;
import interfaces.IDrawable;

/** 
 * Classe para mostrar caixas de estatísticas do jogador e dos chefões 
 * @see entities.player.Player
 * @see entities.boss.Boss 
 * @see interfaces.IDrawable
 */
public class Statbox implements IDrawable {
    // border rectangle
    private final Rectangle rectOuterBox = new Jaylib.Rectangle(
        0.0f,
        0.0f, 
        Config.STATBOX_WIDTH, 
        Config.STATBOX_HEIGHT
    ); 

    // content rectangle
    private final Rectangle rectInnerBox = new Jaylib.Rectangle(
        Config.STATBOX_BORDER_SIZE, 
        Config.STATBOX_BORDER_SIZE, 
        Config.STATBOX_WIDTH - (Config.STATBOX_BORDER_SIZE * 2), 
        Config.STATBOX_HEIGHT - (Config.STATBOX_BORDER_SIZE * 2)
    );

    private String[] statList = null;
    private final Entity entity;

    /**
     * Construtor do Statbox, inicializando qual a sua entidade (Player ou Boss)
     * e quais informações vai mostrar 
     * @param entity a entidade
     * @see entities.Entity 
     */
    public Statbox(Entity entity) {
        this.entity = entity;

        if(entity instanceof Player) {
            statList = ((Player)entity).getStatboxText();

            rectOuterBox.x(Config.STATBOX_PLAYER_POSX);
            rectOuterBox.y(Config.STATBOX_PLAYER_POSY);

            rectInnerBox.x(rectInnerBox.x() + rectOuterBox.x());
            rectInnerBox.y(rectInnerBox.y() + rectOuterBox.y());
        }
        else {
            statList = ((Boss)entity).getStatboxText();

            rectOuterBox.x(Config.STATBOX_BOSS_POSX);
            rectOuterBox.y(Config.STATBOX_BOSS_POSY);

            rectInnerBox.x(rectInnerBox.x() + rectOuterBox.x());
            rectInnerBox.y(rectInnerBox.y() + rectOuterBox.y());
        }
    }

    /** Atualiza as estatísticas da entidade para refletir seus possíveis novos valores */
    private void updateStatList() {
        statList = (entity instanceof Player) ? 
            ((Player)entity).getStatboxText() : ((Boss)entity).getStatboxText();
    }

    /**
     * Desenha a caixa de estatísticas com as informações da entidade (player ou Boss)
     * @see interfaces.IDrawable
     */
    @Override 
    public void draw() {       
        Jaylib.DrawRectangleRec(rectOuterBox, Config.STATBOX_COLOR_BORDER);
        Jaylib.DrawRectangleRec(rectInnerBox, Config.STATBOX_COLOR_BACKGROUND);

        final int _textOffsetX = (int)rectInnerBox.x() + Config.STATBOX_MARGIN;

        final int _textSpacingY = (int)Raylib.MeasureTextEx(
            Config.STATBOX_FONT, 
            Config.STATBOX_TEXT_DMG,  // any text to compute spacing
            (float)Config.STATBOX_FONT_SIZE, 
            0.0f
        ).y();

        int i = 0;
        for(final String _text: statList){
            Jaylib.DrawTextEx(
                Config.STATBOX_FONT, 
                _text, 
                new Vector2(
                    _textOffsetX, 
                    (Config.STATBOX_MARGIN + (_textSpacingY) * i++) 
                ),
                Config.STATBOX_FONT_SIZE, 
                Config.STATBOX_FONT_SPACING, 
                Config.STATBOX_COLOR_TEXT 
            );
        }
        updateStatList();
    }
}
