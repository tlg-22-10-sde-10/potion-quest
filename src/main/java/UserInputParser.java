import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInputParser {

    public static void ParseCommand(List<String> wordlist) {
        String verb;
        String noun;

        List<String> commandVerbList = new ArrayList<>(Arrays.asList("get", "give", "drop", "talk", "attack", "inspect"));
        List<String> commandNounList = new ArrayList<>(Arrays.asList("trinket", "potion", "rope", "dagger"));

        if (wordlist.size() > 2) {
            System.out.println("Only 2 word commands allowed.");
        }
        else {
            verb = wordlist.get(0);
            noun = wordlist.get(1);
            if (!commandVerbList.contains(verb)) {
                System.out.println(verb + " is not a known verb!");
            }
            if (!commandNounList.contains(noun)) {
                System.out.println(noun + " is not a known noun!");
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

    public static void RunCommand(String inputStr) {
        List<String> wordlist;
//        String s = "ok";
        String lowStr = inputStr.trim().toLowerCase();

        if (lowStr.equals("q")) {
            System.exit(0);
        }
        if (lowStr.equals("")) {
//            s = "You must enter a command!";
            System.out.println("You must enter a command!");
        }
        else {
            wordlist = WordList(lowStr);
            if (wordlist.size() > 1) {
                wordlist.forEach((aStr) -> System.out.println(aStr));
                ParseCommand(wordlist);
            }
            else {
                System.out.println("You must enter at least 2 words!");
            }
        }
//        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in;
        String input;
        String output;

        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("> Enter a Command");
            input = in.readLine();
//            output = RunCommand(input);
            RunCommand(input);
//            System.out.println(output);
        } while (!"q".equals(input));
    }
}
