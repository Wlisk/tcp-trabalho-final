import com.raylib.Jaylib;

import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import game.Game;

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
