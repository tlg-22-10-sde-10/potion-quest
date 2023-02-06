import java.io.IOException;
import java.util.*;

/**
 * UserInputParser accepts user input from the keyboard and handles situations from command combinations.
 */
public class UserInputParser {

    /**
     * parseCommand takes a list of Strings called wordList and parses it based on a verb and direction/noun combination.
     * If 'go' or 'move' are the first word in the list, parseCommand expects a direction. Otherwise, it will check the
     * list of interactions verbs with the list of nouns.
     *
     * @param wordlist  - a List of String from the user input.
     */
    public static void parseCommand(List<String> wordlist) {
        String firstArgumentOfUserInput;
        String secondArgumentOfUserInput;
        final ArrayList<String> movementVerbs = new ArrayList<>() {
            {
                add("go");
                add("move");
            }
        };
        final ArrayList<String> interactionVerbs = new ArrayList<>() {
            {
                add("take");
                add("grab");
                add("drop");
                add("use");
                add("talk");
            }
        };
        final ArrayList<String> nouns = new ArrayList<>() {
            {
                add("door");
                add("trinket");
                add("rope");
                add("sword");
                add("potion");
                add("bread");
                add("ale");
                add("water");
                add("rabbit");
                add("river");
                add("forest");
                add("tavern");
                add("armor");
                add("hermit");
                add("shack");
                add("house");
                add("bag");
                add("torch");
            }
        };
        ArrayList<String> directions = new ArrayList<>() {
            {
                add("north");
                add("south");
                add("east");
                add("west");
            }
        };
        firstArgumentOfUserInput = wordlist.get(0).toLowerCase();
        secondArgumentOfUserInput = wordlist.get(1).toLowerCase();

        if (wordlist.size() > 2) {
            System.out.println("Only 2 word commands allowed.");
        } else if (movementVerbs.contains(firstArgumentOfUserInput)) {
            if (directions.contains(secondArgumentOfUserInput)) {
                Direction direction;
                // movement and direction are valid, move player
                switch (secondArgumentOfUserInput.toLowerCase()) {
                    case "north":
                        direction = Direction.NORTH;
                        Player.move(direction);
                        break;
                    case "south":
                        direction = Direction.SOUTH;
                        Player.move(direction);
                        break;
                    case "east":
                        direction = Direction.EAST;
                        Player.move(direction);
                        break;
                    case "west":
                        direction = Direction.WEST;
                        Player.move(direction);
                        break;
                    default:
                        System.out.println("Not a valid direction.");
                }
            } else {
                System.out.println("Invalid direction. Try 'North', 'East', 'South', or 'West' after 'Go' or 'Move'");
            }
        } else if(interactionVerbs.contains(firstArgumentOfUserInput)) {
            if(directions.contains(secondArgumentOfUserInput)) {
                System.out.println("Invalid command. Please pair 'go' and 'move' with directions only.");
            } else if(nouns.contains(secondArgumentOfUserInput)) {
                // interact with nouns here

            } else {
                System.out.println("Invalid noun.");
            }
        } else {
            System.out.println("Invalid interaction verb. For interacting with items, try 'take', 'grab', 'drop', or " +
                    "'use'. For NPCs try 'talk'");
        }
    }

    public static List<String> trimUserInput(String userInput) {
        String punctuation = " \t,.:;?!\"'";
        List<String> listOfStrings = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(userInput, punctuation);
        String token;
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            listOfStrings.add(token);
        }
        return listOfStrings;
    }

    /**
     * handleUserInput works direction with user input from the GameClient main method. If the user enters 'inventory',
     * 'quit' or 'help' a menu is shown. Any other one word command is not recognized. An empty String is also handled.
     * If the user enters in 2 Strings, the input is sent to trimUserInput to trim the input before sending it to
     * parseCommand.
     * @param userInput - input from the user
     * @param gameClient - instance of the GameClient
     * @throws IOException
     * @throws InterruptedException
     */
    public static void handleUserInput(String userInput, GameClient gameClient) throws IOException, InterruptedException {
        // Scanner object for accepting user keyboard input
        Scanner input = new Scanner(System.in);
        List<String> listOfTrimmedInput;
        if(userInput.equalsIgnoreCase("quit")) {
            System.out.println("Are you sure you want to quit?");
            userInput = input.next();
            boolean invalidInput = !userInput.equalsIgnoreCase("yes") &&
                    !userInput.equalsIgnoreCase("y") &&
                    !userInput.equalsIgnoreCase("no") &&
                    !userInput.equalsIgnoreCase("n");
            while (invalidInput) {
                System.out.println("Please enter yes or no.");
                userInput = input.next();
            }
            if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
                gameClient.setQuitGame(true);
                GameClientUtil.gameExitMessage();
                System.exit(0);
            } else {
                System.out.println("Exiting quit menu...");
                Thread.sleep(2000);
            }
        }
        if(userInput.equalsIgnoreCase("help")) {
            // display help message
            GameClientUtil.playerHelpCall();
        }
        if(userInput.equalsIgnoreCase("")) {
            System.out.println("You must enter a command!");
        }
        if(userInput.equalsIgnoreCase("inventory")) {
            // display player inventory
            System.out.println("Your current inventory: " +
                    Arrays.toString(Game.getGameInstance().getPlayer().getInventory()));
        }
        else {
            listOfTrimmedInput = trimUserInput(userInput);
            if (listOfTrimmedInput.size() > 1) {
                parseCommand(listOfTrimmedInput);
            }
            else {
                System.out.println("Try entering commands in a [verb] [direction/noun] format such as" +
                        " go north or take sword");
            }
        }
    }
}
