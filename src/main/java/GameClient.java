import java.io.IOException;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        // flag to allow user to quickly exit the game
        boolean quitGame = false;

        // Welcome the user to the game
        GameClientUtil.gameStartMessage();
        // Prompt the user for input about starting the game
        System.out.println("\nWould you like to start Potion Quest?\n");

        GameClientUtil.startGame();
        //print game logo / title screen
        GameClientUtil.printGameLogo();

        // game runs below:
        while(!quitGame) {
            GameClientUtil.availableCommands();
            String userInput = input.nextLine();
            if(userInput.equalsIgnoreCase("quit")) {
                quitGame = true;
            }
            UserInputParser.handleUserInput(userInput);
        }
    }
}
