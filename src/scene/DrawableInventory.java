package scene;

import com.raylib.Jaylib;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;
import com.raylib.Jaylib.Rectangle;

import config.Config;
import interfaces.IDrawable;
import items.Inventory;
import items.Item;
import scene.box.Infobox;

import static utils.Number.NOT_FOUND;

import java.util.HashMap;

/** 
 * Classe para desenhar o inventário do jogador na tela.
 * <p>
 * Ela atua como classe final do iventário do jogador, portanto herda
 * da classe Inventory, assim não modificando código na classe pai, 
 * pois sua intenção apenas é para desenho 
 * @see items.Inventory
 * @see entities.player.Player 
 * @see interfaces.IDrawable
 */
public final class DrawableInventory extends Inventory
implements IDrawable
{
    private static final int NUM_SLOTS = MAX_ITEMS + NUM_EQUIPABLE_SLOTS;
    private static final float DOUBLE_BORDER_SIZE = Config.INVENTORY_BORDER_SIZE * 2;
    private static final Vector2 vectorOrigin = new Jaylib.Vector2(0f, 0f);

    /**
     * Cria e configura os retangulos dos slots (espaços) dos itens
     * que vão formar o inventário, retornando a lista deles 
     * @return (Rectangle[]) a lista de retangulos dos slots
     */
    private static Rectangle[] initializeRectSlots() {
        final Rectangle[] _rectSlots = new Rectangle[NUM_SLOTS];
        final float _posY = Config.INVENTORY_POS_Y - Config.INVENTORY_BORDER_SIZE;
        float _posX = Config.INVENTORY_POS_X + Config.INVENTORY_BORDER_SIZE;

        for(int i = 0; i < NUM_SLOTS; ++i) {
            _rectSlots[i] = new Rectangle(
                _posX, _posY, 
                Config.INVENTORY_SLOT_SIZE, Config.INVENTORY_SLOT_SIZE
            );
            _posX += Config.INVENTORY_SLOT_SIZE + Config.INVENTORY_BORDER_SIZE;
        }
        return _rectSlots;
    }

    private final Rectangle rectInventory = new Rectangle(
        Config.INVENTORY_POS_X, 
        Config.INVENTORY_POS_Y - DOUBLE_BORDER_SIZE, 
        Config.INVENTORY_BORDER_SIZE + 
        (Config.INVENTORY_SLOT_SIZE + Config.INVENTORY_BORDER_SIZE) * NUM_SLOTS, 
        Config.INVENTORY_SLOT_SIZE + DOUBLE_BORDER_SIZE
    );

    private final Rectangle[] rectSlots = initializeRectSlots();
    private final Infobox infobox = new Infobox();
    private final HashMap<Rectangle, Item> mappedSlots = new HashMap<Rectangle, Item>();
    
    /** Contrutor de DrawableInventory inicializando valores padrão */
    public DrawableInventory() {
        super();
        refreshMapping();
    }

    /** 
     * Atualiza os itens no hashmap de retangulos e itens para 
     * os correntes/atuais do inventário
     */
    private void refreshMapping() {
        for(int i = 0; i < NUM_EQUIPABLE_SLOTS; ++i)
            mappedSlots.put(rectSlots[i], equippedList[i]);

        for(int i = NUM_EQUIPABLE_SLOTS; i < rectSlots.length; ++i) {
            final int _realIndex = i - NUM_EQUIPABLE_SLOTS;
            final Item _item = (_realIndex < items.size()) ? items.get(_realIndex) : null;
            mappedSlots.put(rectSlots[i], _item);
        }
    }

    /**
     * Verifica qual item foi clicado com o botão esquerdo do mouse
     * @return (int) o índice do item que foi clicado ou NOT_FOUND caso contrário
     * @see utils.Number#NOT_FOUND
     */
    public int checkLeftClickedIndex(){
        return getSlotMousePressed(Config.MOUSE_BUTTON_LEFT);
    }

    /**
     * Verifica qual item foi clicado com o botão direito do mouse
     * @return (int) o índice do item que foi clicado ou NOT_FOUND caso contrário
     * @see utils.Number#NOT_FOUND
     */
    public int checkRightClickedIndex(){
        return getSlotMousePressed(Config.MOUSE_BUTTON_RIGHT);
    }

    /**
     * Verifica qual item foi clicado com o botão especificado do mouse
     * @param mousebutton qual dos botões do mouse para verificar 
     *   (0 para o esquerdo e 1 para o direto)
     * @return (int) o índice do item que foi clicado ou NOT_FOUND caso contrário
     * @see utils.Number#NOT_FOUND
     */
    private int getSlotMousePressed(int mouseButton) {
        final int _slotMouseOver = getSlotMouseOver();

        if(_slotMouseOver == NOT_FOUND) return NOT_FOUND;

        return Jaylib.IsMouseButtonDown(mouseButton) ? _slotMouseOver : NOT_FOUND;
    }

    /**
     * Verifica qual item está sendo apontado pelo ponteiro do mouse
     * @return (int) o índice do item que está sendo apontado ou NOT_FOUND caso contrário
     * @see utils.Number#NOT_FOUND
     */
    private int getSlotMouseOver() {
        Vector2 _mousePos = Jaylib.GetMousePosition();

        if(_mousePos == null) return NOT_FOUND;

        for(int i = 0; i < NUM_SLOTS; ++i) {
            if(Jaylib.CheckCollisionPointRec(_mousePos, rectSlots[i])) 
                return i;
        }
        return NOT_FOUND;
    }

    /** Desenha a caixa de informações do item quando o mouse está por cima (apontando) do slot */
    public void drawMouseOver() {
        final int _indexSlotOver = getSlotMouseOver();
        final int _realIndex = _indexSlotOver - NUM_EQUIPABLE_SLOTS;

        if(_indexSlotOver == NOT_FOUND) return;

        else if(_indexSlotOver < NUM_EQUIPABLE_SLOTS)
            infobox.drawMouseOver(equippedList[_indexSlotOver]);

        else if(_realIndex < items.size())
                infobox.drawMouseOver(items.get(_realIndex));

        else return;
    }

    /** Desenha o retangulo por trás dos slots, tornando-o as bordas dos slots */
    private void drawInventoryBase() {
        Jaylib.DrawRectangleRec(rectInventory, Config.INVENTORY_COLOR_BORDER);
    }

    /** Desenha os slots (espaços) do inventário com ou sem os itens */
    private void drawInventorySlots() {
        mappedSlots.forEach((_rectSlot, _slotItem) -> {
            Jaylib.DrawRectangleRec(_rectSlot, Config.INVENTORY_COLOR_BACKGROUND);
            if(_slotItem != null) {
                final Texture _itemTexture = _slotItem.getTextureId().getTexture();

                final Rectangle _rectTextureDimension = new Jaylib.Rectangle(
                    vectorOrigin.x(), vectorOrigin.y(), 
                    _itemTexture.width(), _itemTexture.height()
                );

                Jaylib.DrawTexturePro(
                    _itemTexture, 
                    _rectTextureDimension, 
                    _rectSlot, 
                    vectorOrigin, 
                    0, 
                    Jaylib.WHITE
                );
            }
        });
    }

    /** Desenha todo o inventário do jogador com os slots com itens e ou vazios */
    @Override 
    public void draw() {
        refreshMapping();
        drawInventoryBase();
        drawInventorySlots();
        drawMouseOver();
    }
}
