import java.util.Map;

public class Game {
    private static Game gameInstance = null;
    private Player player;
    //private Location currentLocation;
    //private Location previousLocation;
    //private int time
    private Directions directions;
    private Verbs verbs;
    private Nouns nouns;

    private Game(Player player) {
        setPlayer(player);
    }

    public static Game createGameInstance() {
        if(gameInstance == null) {
            int playerHealth = 100;
            String[] inventory = new String[5];
            Map<String, Integer> playerStats = Map.of(
                    "Strength", 10,
                    "Defense", 10,
                    "Agility", 10,
                    "Intelligence", 10,
                    "Charisma", 10,
                    "Luck", 10);
            Location startingVillage = new StartingVillage();
            Player player = new Player(playerHealth, inventory, playerStats, startingVillage);
            gameInstance = new Game(player);
        }
        return gameInstance;
    }

    public static void destroyGameInstance() {
        gameInstance = null;
    }

    public Game getGameInstance() {
        return gameInstance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
