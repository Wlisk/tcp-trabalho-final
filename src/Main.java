import com.raylib.Jaylib;

import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import game.Game;

/**
 * Classe principal onde o jogo irá rodar
 */
public class Main {
    /**
     * @throws NumberOverflowException caso um número fora dos limites especificados for passado
     * @throws UnknownTypeException caso um tipo errado for passado
     */
    public static void main(String args[])
    throws NumberOverflowException, UnknownTypeException
    {
        Game _game = new Game();

        _game.sceneLoad();
        _game.gameLoop();
        _game.sceneUnload();

        Jaylib.CloseWindow();
    }
}
