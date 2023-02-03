import java.util.ArrayList;
import java.util.Map;

public class Game {
    private static Game gameInstance = null;
    private Player player;
    //private Location currentLocation;
    //private Location previousLocation;
    //private int time
    private Direction direction;
    private InteractionVerb interactionVerb;
    private Noun noun;

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
            ArrayList<Location> woodsAdjacent = new ArrayList<>();
            ArrayList<Location> villageAdjacent = new ArrayList<>();
            ArrayList<Location> mountainAdjacent = new ArrayList<>();
            ArrayList<Location> riverNorthAdjacent = new ArrayList<>();
            ArrayList<Location> riverSouthAdjacent = new ArrayList<>();
            Location village = new Location("Village", villageAdjacent);
            Location woods = new Location("Woods", woodsAdjacent);
            Location mountains = new Location("Mountains", mountainAdjacent);
            Location riverNorth = new Location("Woods River", riverNorthAdjacent);
            Location riverSouth = new Location("Mountains River", riverSouthAdjacent);

            village.addAdjacentLocation(Direction.NORTH, woods);
            village.addAdjacentLocation(Direction.SOUTH, mountains);
            woods.addAdjacentLocation(Direction.SOUTH, village);
            woods.addAdjacentLocation(Direction.EAST, riverNorth);
            mountains.addAdjacentLocation(Direction.NORTH, village);
            mountains.addAdjacentLocation(Direction.EAST, riverSouth);
            riverNorth.addAdjacentLocation(Direction.WEST, woods);
            riverSouth.addAdjacentLocation(Direction.WEST, mountains);
            Player joe = new Player("Joe", 100, village);

            String[] villageItems = {"Trinket", "Backpack"};
            village.setItems(villageItems);

            String[] woodsItems = {"Rope", "Dagger"};
            woods.setItems(woodsItems);

            String[] mountainItems = {"Rope", "Bread"};
            mountains.setItems(mountainItems);

            String[] riverNorthItems = {"No items available"};
            riverNorth.setItems(riverNorthItems);

            String[] riverSouthItems = {"No items available"};
            riverSouth.setItems(riverSouthItems);

            gameInstance = new Game(joe);
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
