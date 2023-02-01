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
            System.out.println(ANSI_PURPLE + logoStringBuilder);
        }
    }
}
