import com.raylib.Jaylib;

import config.Config;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import game.Game;

/**
 * Classe principal onde o jogo irá rodar
 */
public class Main {
    /**
     * Erros gerados por erros de dev
     * @throws NumberOverflowException caso um número fora dos limites especificados for passado
     * @throws UnknownTypeException caso um tipo errado for passado
     */
    public static void main(String args[])
    throws NumberOverflowException, UnknownTypeException
    {
        if(args.length > 0) {
            String help = args[0];

            if(help.equals("--help") || help.equals("-h")) {
                System.out.println(Config.GAME_TITLE);
                System.out.println("Version: " + Config.VERSION);
            }
            return;
        }

        Game _game = new Game();

        _game.sceneLoad();
        _game.gameLoop();
        _game.sceneUnload();

        Jaylib.CloseWindow();
    }
}
