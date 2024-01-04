import com.raylib.Jaylib;

import game.Game;
import utils.exceptions.NumberOverflowException;
import utils.exceptions.UnknownTypeException;

public class Main {
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
