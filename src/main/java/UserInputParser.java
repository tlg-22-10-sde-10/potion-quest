import java.io.IOException;
import java.util.*;

public class UserInputParser {

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
                add("open");
                add("grab");
                add("store");
                add("drop");
                add("trade");
                add("use");
                add("look");
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
                add("mountain path");
                add("starting village");
                add("potion village");
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
                // movement and direction are valid, move player

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
            System.out.println("Invalid interaction verb. Maybe try 'take', 'open', or 'use'.");
        }
    }


    public static List<String> convertUserInputToList(String trimmedInput) {
        String punctuation = " \t,.:;?!\"'";
        List<String> listOfStrings = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(trimmedInput, punctuation);
        String token;
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            listOfStrings.add(token);
        }
        return listOfStrings;
    }

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
        else {
            listOfTrimmedInput = convertUserInputToList(userInput);
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
