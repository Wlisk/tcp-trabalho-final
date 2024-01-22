package interfaces;

import com.raylib.Raylib.Vector2;

/** Padronização de métodos para mouse sobre objetos na tela */
public interface IMouseOverable {
    /**  
     * Método para verificar se o mouse está sobre o objeto na tela
     * @return (Vector2) a posição do mouse em cima do objeto 
     */
    public Vector2 isMouseOver();
}
