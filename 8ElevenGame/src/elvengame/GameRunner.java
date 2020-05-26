package elvengame;

/**
 * Runs GUI or ConsolaUI
 */
public class GameRunner {
    
    public static void main(String[] args) {
        ConsolaUI game = new ConsolaUI();
        //GUI game = new GUI();
        game.start();
    }
}
