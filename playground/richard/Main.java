import tools.Print;
import game.Game;

public class Main {
    public static void main(String[] args) {
        Print.line("hello");

        Game game = new Game();

        game.loop();
    }
}