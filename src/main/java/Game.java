import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game {
    private static Game gameInstance = null;
    private Player player;
    private Location currentLocation;
    //private int time
    private Direction direction;
    private InteractionVerb interactionVerb;
    private Noun noun;
    private Item item;

    private Game(Player player) {
        setPlayer(player);
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
            List<Item> startingVillageItems = startingVillage.getItems();
            Item trinket = new Item("trinket", "a small locket");
            Item bag = new Item("bag", "a small bag that can take 5 items");
            List<Item> listOfItems = new ArrayList<>();
            listOfItems.add(trinket);
            listOfItems.add(bag);
            startingVillage.setItems(listOfItems);

            Location forest = mapOfAllLocations.get("Forest");
            Location mountainPass = mapOfAllLocations.get("Mountain Pass");
            Location riverNorth = mapOfAllLocations.get("River North");
            Location riverSouth = mapOfAllLocations.get("River South");
            Location village2 = mapOfAllLocations.get("Village2");

            Player joe = new Player("Joe", 100, startingVillage);
            joe.setInventory(listOfItems);

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

            gameInstance = new Game(joe);
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
}
