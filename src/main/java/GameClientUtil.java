import java.awt.*;
import java.awt.image.BufferedImage;

public class GameClientUtil {
    private static int width = 130;
    private static int height = 15;
    private static int fontSize = 15;
    private static int x_offset =10;
    private static int y_offset = 12;

    private static String titleGame = "POTION QUEST";

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printGameLogo(){
        BufferedImage bufferedImage = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        Graphics logoGraphics = bufferedImage.getGraphics();
        logoGraphics.setFont(new Font("Helvetica", Font.ITALIC, fontSize));

        Graphics2D logo = (Graphics2D) logoGraphics;
        logo.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        logoGraphics.drawString(titleGame, x_offset, y_offset);


        for (int y = 0; y < height; y++) {
            StringBuilder logoStringBuilder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                logoStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "L");
            }
            if (logoStringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_PURPLE + logoStringBuilder + ANSI_RESET);
        }
    }

    public static void gameStartMessage() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println();
        System.out.println(ANSI_CYAN + "Welcome to Potion Quest");
        System.out.println();
        System.out.println("Potion Quest is a text-based RPG game. Go on an adventure to get the potion to cure your sister!");
        Thread.sleep(2000);
        System.out.println("During this quest you will be faced with many choices and obstacles. Choose wisely....");
        Thread.sleep(1000);
        System.out.println("Your life and your sister's life DEPENDS on it!");
        Thread.sleep(2000);
        System.out.println("Complete the quest by traveling to the next village over and bring back the potion to cure your sister's illness." + ANSI_RESET);
    }

    public static void availableCommands(){
        System.out.println(ANSI_PURPLE + "======================================================================================");
        System.out.println("Available commands: go [direction], get [item], use [item], help, quit");
        System.out.println("======================================================================================" + ANSI_RESET);
    }

    public static void playerHelpCall() {
        System.out.println(ANSI_GREEN + "You have asked for help. Here are some suggestions:");
        System.out.println();
        System.out.println("You can interact with items, by typing get [item] or use [item].");
        System.out.println("You can move from your location to another location by typing go [direction].");
        System.out.println("Examples: go north or use rope" + ANSI_RESET);
    }

    public static void gameExitMessage() {
        System.out.println("Thank you for playing Potion Quest, have a nice day!");
    }
}
