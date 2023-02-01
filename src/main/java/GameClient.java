import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        boolean quitGame = false;

        // Welcome the user to the game
        // WelcomeMessage()

        // Prompt the user for input about starting the game
        System.out.println("Would you like to start Potion Quest?\n");

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
            System.out.println(Game.getGameInstance());
        } else  {
            System.out.println("Why would you start up a game if you don't want to play?");
            System.exit(0);
        }

        // game runs below:
        while(!quitGame) {
            userInput = input.nextLine();
            // The user can type 'quit' at any time to exit the game
            if(userInput.equalsIgnoreCase("quit")) {
                quitGame = true;
                // destroy game, display exit message
                Game.destroyGameInstance();
                System.out.println(Game.getGameInstance());
                // ExitMessage()
            }
            if(userInput.equalsIgnoreCase("help")) {
                // display help message
                // HelpMessage()
            }


        }
    }
}
