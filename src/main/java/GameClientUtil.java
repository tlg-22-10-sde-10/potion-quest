import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameClientUtil {

    private String welcomeMessage = null;
    private String playerHelpCallMessage = null;

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

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }


    public String getPlayerHelpCallMessage() {
        return playerHelpCallMessage;
    }

    public void setPlayerHelpCallMessage(String playerHelpCallMessage) {
        this.playerHelpCallMessage = playerHelpCallMessage;
    }

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

    public static void gameStartMessage() throws InterruptedException, IOException {
        Thread.sleep(2000);
        File file = new File("src/main/resources/messages.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GameClientUtil gameWelcomeMessage = objectMapper.readValue(file, GameClientUtil.class);
        System.out.println(ANSI_CYAN + gameWelcomeMessage.getWelcomeMessage() + ANSI_RESET);
        System.out.println();
        Thread.sleep(2000);
    }

    public static void availableCommands(){
        System.out.println(ANSI_PURPLE + "======================================================================================");
        System.out.println("Available commands: go [direction], get [item], use [item], help, quit");
        System.out.println("======================================================================================" + ANSI_RESET);
    }

    public static void playerHelpCall() throws IOException {

        File file = new File("src/main/resources/messages.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GameClientUtil gameHelpMessage = objectMapper.readValue(file, GameClientUtil.class);
        System.out.println(ANSI_GREEN + gameHelpMessage.getPlayerHelpCallMessage() + ANSI_RESET);
    }


    public static void gameExitMessage() {
        System.out.println("Thank you for playing Potion Quest, have a nice day!");
    }

}
