import java.io.IOException;
import java.util.Scanner;

public class GameClient {
    // flag to allow user to quickly exit the game
    private boolean quitGame = false;

   public static void main(String[] args) throws InterruptedException, IOException {
       Location.locationJsonParser();
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        GameClient gameClient = new GameClient();
        // Welcome the user to the game
        GameClientUtil.gameStartMessage();
        GameClientUtil.askPlayerIfTheyWantToStartGame();
        GameClientUtil.startingVillageMessage();

        // game runs below:
        while(!gameClient.isQuitGame()) {
            GameClientUtil.availableCommands();
            String userInput = input.nextLine();
            UserInputParser.handleUserInput(userInput, gameClient);
        }
    }

    public boolean isQuitGame() {
        return quitGame;
    }

    public void setQuitGame(boolean quitGame) {
        this.quitGame = quitGame;
    }
}
