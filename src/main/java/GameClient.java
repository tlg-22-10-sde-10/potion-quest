import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws InterruptedException {
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        // flag to allow user to quickly exit the game
        boolean quitGame = false;

        // Welcome the user to the game
        GameClientUtil.gameStartMessage();
        // Prompt the user for input about starting the game
        System.out.println("\nWould you like to start Potion Quest?\n");

        // Capture input, if yes, create the game Singleton
        String userInput = input.nextLine();
        while(!userInput.equalsIgnoreCase("yes") &&
                !userInput.equalsIgnoreCase("y") &&
        !userInput.equalsIgnoreCase("no") &&
        !userInput.equalsIgnoreCase("n")) {
            System.out.println("Please enter 'yes' or 'no'");
            userInput = input.nextLine();
        }
        if(userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            // create new Game here
            Game.createGameInstance();
            GameClientUtil.printGameLogo();
        } else  {
            System.out.println("Why would you start up a game if you don't want to play?");
            System.exit(0);
        }

        // game runs below:
        while(!quitGame) {
            GameClientUtil.availableCommands();
            userInput = input.nextLine();
            if(userInput.equalsIgnoreCase("quit")) {
                quitGame = true;
            }
            UserInputParser.RunCommand(userInput);
        }
    }
}
