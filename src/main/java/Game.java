import java.io.IOException;
import java.util.*;

public class Game {
    private static Game gameInstance = null;
    private Player player;
    private Monster monster;

    //private int time
    private Direction direction;
    private InteractionVerb interactionVerb;
    private Noun noun;
    private Item item;
    private Map<String, Item> items;
    private Map<String, Location> locations;
    private Map<String, Monster> monsters;

    private Game(Player player, Map<String, Monster> monsters, Map<String, Item> items, Map<String, Location> locations) {
        setPlayer(player);
        setMonsters(monsters);
        setItems(items);
        setLocations(locations);
    }

    public static Game createGameInstance() throws IOException {
        if (gameInstance == null) {
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
            Map<String, Item> mapOfAllItems = Item.itemJsonParser();
            Map<String, Monster> mapOfAllMonsters = Monster.monsterJsonParser();


            Location startingVillage = mapOfAllLocations.get("Starting Village");
            List<Item> itemsToAddStartingVillage = new ArrayList<>();
            Item trinket = mapOfAllItems.get("Trinket");
            Item coin = mapOfAllItems.get("Bread");
            itemsToAddStartingVillage.add(trinket);
            itemsToAddStartingVillage.add(coin);
            startingVillage.setItems(itemsToAddStartingVillage);

            Location forest = mapOfAllLocations.get("Forest");
            Item rope1 = mapOfAllItems.get("Rope");
            List<Item> itemsToAddForest = new ArrayList<>();
            itemsToAddForest.add(rope1);
            forest.setItems(itemsToAddForest);
            Monster wolf = mapOfAllMonsters.get("Wolf");


            Location mountainPass = mapOfAllLocations.get("Mountain Pass");
            Item rope2 = mapOfAllItems.get("Rope");
            List<Item> itemsToAddMountains = new ArrayList<>();
            itemsToAddMountains.add(rope2);
            mountainPass.setItems(itemsToAddMountains);

            Location riverNorth = mapOfAllLocations.get("River North");
            Item fish = mapOfAllItems.get("Fish");
            List<Item> itemsToAddRiverNorth = new ArrayList<>();
            itemsToAddRiverNorth.add(fish);
            riverNorth.setItems(itemsToAddRiverNorth);

            Location riverSouth = mapOfAllLocations.get("River South");
            Item waterBottle = mapOfAllItems.get("Ale");
            List<Item> itemsToAddRiverSouth = new ArrayList<>();
            itemsToAddRiverSouth.add(waterBottle);
            riverSouth.setItems(itemsToAddRiverSouth);

            Location village2 = mapOfAllLocations.get("Village2");
            Item potion = mapOfAllItems.get("Potion");
            Item sword = mapOfAllItems.get("Sword");
            List<Item> itemsToAddVillage2 = new ArrayList<>();
            itemsToAddVillage2.add(potion);
            itemsToAddVillage2.add(sword);
            village2.setItems(itemsToAddVillage2);

            Player cindy = new Player("Cindy", playerHealth, new ArrayList<>(5), playerStats, startingVillage);

            Map<String, Integer> monsterStats = Map.of(
                    "Health", 20,
                    "Strength", 10,
                    "Defense", 5
            );

            System.out.println(wolf.getStats());

            startingVillage.addAdjacentLocation("NORTH", forest);
            startingVillage.addAdjacentLocation("SOUTH", mountainPass);

            forest.addAdjacentLocation("SOUTH", startingVillage);
            forest.addAdjacentLocation("EAST", riverNorth);

            mountainPass.addAdjacentLocation("NORTH", startingVillage);
            mountainPass.addAdjacentLocation("EAST", riverSouth);

            riverNorth.addAdjacentLocation("SOUTH", village2);
            riverNorth.addAdjacentLocation("WEST", forest);

            riverSouth.addAdjacentLocation("WEST", mountainPass);
            riverSouth.addAdjacentLocation("NORTH", village2);

            village2.addAdjacentLocation("NORTH", riverNorth);
            village2.addAdjacentLocation("SOUTH", riverSouth);

            gameInstance = new Game(cindy, mapOfAllMonsters, mapOfAllItems, mapOfAllLocations);
        }
        return gameInstance;
    }

    public static void checkWin(List<Item> inventory, Location location, GameClient gameClient) throws IOException {
        Map<String, Item> mapOfAllItems = Item.itemJsonParser();
        if (inventory.contains(mapOfAllItems.get("Potion"))) {
            System.out.println("You have the potion");
            if (location.getName().equalsIgnoreCase("Starting Village")) {
                System.out.println("You are back in the starting village");
                gameClient.setQuitGame(true);
            }
        }
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }

    public Map<String, Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(Map<String, Monster> monsters) {
        this.monsters = monsters;
    }

    public static void destroyGameInstance() {
        gameInstance = null;
    }

    public static Game getGameInstance() {
        return gameInstance;
    }

    public Item getItem() {
        return item;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
