import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GameClient {
    // flag to allow user to quickly exit the game
    public boolean quitGame = false;
    private static long elapsedTime = 0L;
    private static long startTime = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException, IOException {
        Location.locationJsonParser();
        Item.itemJsonParser();
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        GameClient gameClient = new GameClient();
        // Welcome the user to the game
        GameClientUtil.gameStartMessage();
        GameClientUtil.askPlayerIfTheyWantToStartGame();

        GameClientUtil.startingVillageMessage();

        //starts timer thread, will print out the Game
        (new Thread(new Timer())).start();
        // game runs below:
        do {
            GameClientUtil.availableCommands();
            String userInput = input.nextLine();
            UserInputParser.handleUserInput(userInput, gameClient);
        }
        while (!gameClient.isQuitGame() && Game.getGameInstance().getPlayer().getHealth() > 0);
        GameClientUtil.endGameSequence();
    }

    public boolean isQuitGame() {
        return quitGame;
    }

    public void setQuitGame(boolean quitGame) {
        this.quitGame = quitGame;
    }
}
