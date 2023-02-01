public class Game {
    private static Game gameInstance = null;
    //private Location currentLocation;
    //private Location previousLocation;
    //private int time
    private Directions directions;
    private Verbs verbs;
    private Nouns nouns;

    private Game() {
    }

    public static void createGameInstance() {
        if(gameInstance == null) {
            gameInstance = new Game();
        }
    }

    public static void destroyGameInstance() {
        gameInstance = null;
    }

    public static Game getGameInstance() {
        return gameInstance;
    }
}
