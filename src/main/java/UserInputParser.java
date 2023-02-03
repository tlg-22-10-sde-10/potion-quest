import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInputParser {

    public static void ParseCommand(List<String> wordlist) {
        String verb;
        String noun;

        Verbs[] commandVerbList = Verbs.values();
        Nouns[] commandNounList = Nouns.values();
        Directions[] commandDirectionList = Directions.values();

        List<Directions> directionsAsList = Arrays.asList(commandDirectionList);
        List<Verbs> verbsAsList = Arrays.asList(commandVerbList);
        List<Nouns> nounsAsList = Arrays.asList(commandNounList);

        verb = wordlist.get(0);
        noun = wordlist.get(1);
        if(wordlist.size() > 2) {
            System.out.println("Only 2 word commands allowed.");
        }
        // special case of if verb is 'go', it must be paired with a valid direction
        // handle that here
        else if (verb.equalsIgnoreCase("go")) {
            try {
                Directions validDirectionEnum = Directions.valueOf(noun.toUpperCase());
                if (directionsAsList.contains(validDirectionEnum)) {
                    // update player location
                    //TO-DO:

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input, please match go with a direction: North, East, South, or West");
            }
        } else {
            try {
                Verbs validVerbEnums = Verbs.valueOf(verb.toUpperCase());
                Nouns validNounEnums = Nouns.valueOf(noun.toUpperCase());
                if(verbsAsList.contains(validVerbEnums)) {
                    if(nounsAsList.contains(validNounEnums)) {
                        // interact here

                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid verb or noun. Error message - " + e);
            }
        }
    }


    public static List<String> WordList(String trimmedInput) {
        String delims = " \t,.:;?!\"'";
        List<String> strlist = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(trimmedInput, delims);
        String t;
        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strlist.add(t);
        }
        return strlist;
    }

    public static void RunCommand(String userInput) throws IOException {
        List<String> wordlist;
        if(userInput.equalsIgnoreCase("quit")) {
            // destroy game, display exit message
            Game.destroyGameInstance();
            // ExitMessage()
            GameClientUtil.gameExitMessage();
            System.exit(0);
        }
        if(userInput.equalsIgnoreCase("help")) {
            // display help message
            GameClientUtil.playerHelpCall();
        }
        if(userInput.equalsIgnoreCase("")) {
            System.out.println("You must enter a command!");
        }
        else {
            wordlist = WordList(userInput);
            if (wordlist.size() > 1) {
                wordlist.forEach((aStr) -> System.out.println(aStr));
                ParseCommand(wordlist);
            }
            else {
                System.out.println("Try entering commands in a [verb] [direction/noun] format such as" +
                        " [go] [north] or [take] [sword]");
            }
        }
    }
}
