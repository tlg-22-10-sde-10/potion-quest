import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private static Game gameInstance = null;
    private Player player;
    private Monster monster;
    private Location currentLocation;
    //private int time
    private Direction direction;
    private InteractionVerb interactionVerb;
    private Noun noun;

    private Game(Player player, Monster wolf) {
        setPlayer(player);
        setMonster(wolf);
    }

    public static Game createGameInstance() throws IOException {
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
            Map<String, Location> mapOfAllLocations = Location.locationJsonParser();

            Location startingVillage = mapOfAllLocations.get("Starting Village");
            Location forest = mapOfAllLocations.get("Forest");
            Location mountainPass = mapOfAllLocations.get("Mountain Pass");
            Location riverNorth = mapOfAllLocations.get("River North");
            Location riverSouth = mapOfAllLocations.get("River South");
            Location village2 = mapOfAllLocations.get("Village2");

           // Player joe = new Player("Joe", 100, startingVillage);
            Player bryce = new Player("Bryce", 100, new ArrayList<>(5), playerStats, startingVillage);
            Map<String, Integer> monsterStats = Map.of(
                    "Health", 20,
                    "Strength", 10,
                    "Defense", 5
            );
            Monster wolf = new Monster("Wolf", monsterStats);

            System.out.println(startingVillage.description());

            startingVillage.addAdjacentLocation("NORTH", forest);
            startingVillage.addAdjacentLocation("SOUTH", mountainPass);

            forest.addAdjacentLocation("SOUTH", startingVillage);
            forest.addAdjacentLocation("EAST", riverNorth);

            mountainPass.addAdjacentLocation("NORTH", startingVillage);
            mountainPass.addAdjacentLocation("EAST", riverSouth);

            riverNorth.addAdjacentLocation("EAST", village2);
            riverNorth.addAdjacentLocation("WEST", forest);

            riverSouth.addAdjacentLocation("WEST", mountainPass);
            riverSouth.addAdjacentLocation("EAST", village2);

            village2.addAdjacentLocation("NORTH", riverNorth);
            village2.addAdjacentLocation("SOUTH", riverSouth);

            gameInstance = new Game(bryce, wolf);
        }
        return gameInstance;
    }

    public static void destroyGameInstance() {
        gameInstance = null;
    }

    public static Game getGameInstance() {
        return gameInstance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster wolf) {
        this.monster = wolf;
    }
}
