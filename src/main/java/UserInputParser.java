import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * UserInputParser accepts user input from the keyboard and handles situations from command combinations.
 */
public class UserInputParser {
    private final ArrayList<String> movementVerbs = new ArrayList<>() {
        {
            add("go");
            add("move");
        }
    };
    private final ArrayList<String> interactionVerbs = new ArrayList<>() {
        {
            add("take");
            add("get");
            add("drop");
            add("use");
            add("talk");
            add("look");
        }
    };

    //need to update to consume JSON items instead
    private final ArrayList<String> nouns = new ArrayList<>() {
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
            add("attack");
            add("fish");
        }
    };
    private final ArrayList<String> monsters = new ArrayList<>() {
        {
            add("wolf");
            add("bear");
        }
    };
    private final ArrayList<String> directions = new ArrayList<>() {
        {
            add("north");
            add("south");
            add("east");
            add("west");
        }
    };

    /**
     * parseCommand takes a list of Strings called wordList and parses it based on a verb and direction/noun combination.
     * If 'go' or 'move' are the first word in the list, parseCommand expects a direction. Otherwise, it will check the
     * list of interactions verbs with the list of nouns.
     *
     * @param wordlist  - a List of String from the user input.
     */
    public static void parseCommand(List<String> wordlist) {
        UserInputParser inputParser = new UserInputParser();
        Player player = Game.getGameInstance().getPlayer();
        Location location = Game.getGameInstance().getPlayer().getCurrentLocation();
        String firstArgumentOfUserInput;
        String secondArgumentOfUserInput;
        firstArgumentOfUserInput = wordlist.get(0).toLowerCase();
        secondArgumentOfUserInput = wordlist.get(1).toLowerCase();

        if (wordlist.size() > 2) {
            System.out.println("Only 2 word commands allowed.");
        } else if (inputParser.getMovementVerbs().contains(firstArgumentOfUserInput)) {
            if (inputParser.getDirections().contains(secondArgumentOfUserInput)) {
                // movement and direction are valid, move player
                updatePlayerLocation(secondArgumentOfUserInput, player, location);
            } else {
                System.out.println("Invalid direction. Try 'North', 'East', 'South', or 'West' after 'Go' or 'Move'");
            }
        } else if(inputParser.getInteractionVerbs().contains(firstArgumentOfUserInput)) {
            if(inputParser.getDirections().contains(secondArgumentOfUserInput)) {
                System.out.println("Invalid command. Please pair 'go' and 'move' with directions only.");
            } else if(inputParser.getNouns().contains(secondArgumentOfUserInput)) {
                // interact with nouns here
                Item item = player.convertInputNounToTargetObject(secondArgumentOfUserInput);
                if (firstArgumentOfUserInput.equalsIgnoreCase("take")
                || firstArgumentOfUserInput.equalsIgnoreCase("get"))
                {
                    player.takeItem(item);
                }
                if (firstArgumentOfUserInput.equalsIgnoreCase("drop")) {
                    player.dropItem(item);
                }
                if (firstArgumentOfUserInput.equalsIgnoreCase("look")) {
                    player.lookAtItem(item);
                }
            } else {
                System.out.println("Invalid noun.");
            }
        } else if(firstArgumentOfUserInput.equalsIgnoreCase("attack")) {
            if(inputParser.getMonsters().contains(secondArgumentOfUserInput)) {
                handleCombatEncounter();
            } else {
                System.out.println("Invalid command, please pair 'attack' with a monster name.");
            }
        } else {
            System.out.println("Invalid interaction verb. For interacting with items, try 'take', 'grab', 'drop', or " +
                    "'use'. For NPCs try 'talk'");
        }
    }

    public static void updatePlayerLocation(String secondArgumentOfUserInput, Player player, Location location) {
        Direction direction;
        switch (secondArgumentOfUserInput) {
            case "north":
                direction = Direction.NORTH;
                player.move(direction);
                break;
            case "south":
                direction = Direction.SOUTH;
                player.move(direction);
                break;
            case "east":
                direction = Direction.EAST;
                player.move(direction);
                break;
            case "west":
                direction = Direction.WEST;
                player.move(direction);
                break;
            default:
                System.out.println("Not a valid direction.");
        }
    }

    private static void handleCombatEncounter() {
        Player player = Game.getGameInstance().getPlayer();
        int playerHealth = player.getHealth();
        Monster monster = Game.getGameInstance().getMonster();
        int monsterHealth = monster.getStats().get("Health");
        while(monsterHealth > 0) {
            int playerAttack = Combat.playerAttack(player);
            int playerDefend = Combat.playerDefend(player);
            int monsterAttack = Combat.monsterAttack(monster);
            int monsterDefend = Combat.monsterDefend(monster);
            int monsterDamageTaken = Combat.monsterTakeDamage(playerAttack, monsterDefend);
            int playerDamageTaken = Combat.playerTakeDamage(playerDefend, monsterAttack);
            System.out.println("You did " + monsterDamageTaken + " damage to the " + monster.getName());
            monsterHealth -= monsterDamageTaken;
            System.out.println(monster.getName() + " health:\t[" + monsterHealth + "/20]");
            playerHealth -= playerDamageTaken;
            System.out.println("You took " + playerDamageTaken + " damage.\nYour current hp is " + playerHealth);
        }
        System.out.println("You slayed the " + monster.getName());
    }

    private static List<String> trimUserInput(String userInput) {
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
                    Arrays.asList(Game.getGameInstance().getPlayer().getInventory()
                            .stream().map(p -> p.getName()).collect(Collectors.toList())));
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

    public ArrayList<String> getMovementVerbs() {
        return movementVerbs;
    }

    public ArrayList<String> getInteractionVerbs() {
        return interactionVerbs;
    }

    public ArrayList<String> getNouns() {
        return nouns;
    }

    public ArrayList<String> getMonsters() {
        return monsters;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }
}
