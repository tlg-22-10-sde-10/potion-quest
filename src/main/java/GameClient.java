import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        Game potionQuest = null;
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        // flag to allow user to quickly exit the game
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
            System.out.println("Please enter '[y]es' or '[n]o'");
            userInput = input.nextLine();
        }
        if(userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            // create new Game here
            potionQuest.createGameInstance();
        } else  {
            System.out.println("Why would you start up a game if you don't want to play?");
            System.exit(0);
        }
        //print game logo / title screen
        GameClientUtil.printGameLogo();
        // game runs below:
        while(!quitGame) {
            potionQuest = Game.createGameInstance();
            System.out.println(potionQuest.getPlayer().toString());
            potionQuest.getPlayer().getCurrentLocation().locationDescription();
            userInput = input.nextLine();
            // The user can type 'quit' at any time to exit the game
            if(userInput.equalsIgnoreCase("quit")) {
                quitGame = true;
                // destroy game, display exit message
                potionQuest.destroyGameInstance();
                System.out.println(potionQuest.getGameInstance());
                // ExitMessage()
            }
            if(userInput.equalsIgnoreCase("help")) {
                // display help message
                // HelpMessage()
            }


        }
    }
}
