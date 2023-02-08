import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private String name;
    private int health;
    private List<Item> inventory;
    private Map<String, Integer> stats;
    private Location currentLocation;

    public Player() {

    }

    public Player(String name, int health, Location startingLocation) {
        this.name = name;
        this.health = health;
        setCurrentLocation(startingLocation);
    }

    public Player(String name, int health, Location startingLocation, List<Item> inventory) {
        this.name = name;
        this.health = health;
        this.currentLocation = startingLocation;
        this.inventory = inventory;
    }

    public Player(String name, int health, List<Item> inventory, Map<String, Integer> stats, Location startingLocation) {
        this.name = name;
        setHealth(health);
        setInventory(inventory);
        setStats(stats);
        setCurrentLocation(startingLocation);
    }

    public Item convertInputNounToTargetObject(String noun) {
        List<Item> itemsInThisLocation = getCurrentLocation().getItems();
        List<Item> itemsInPlayerInventory = getInventory();
        List<Item> allItems = new ArrayList<>();
        if (itemsInPlayerInventory.equals(null)) {
            allItems.addAll(itemsInThisLocation);
        }
        if (itemsInThisLocation.equals(null)) {
            allItems.addAll(itemsInPlayerInventory);
        } else {
            allItems.addAll(itemsInThisLocation);
            allItems.addAll(itemsInPlayerInventory);
        }
        for (Item item : allItems) {
            if (item.getName().toLowerCase().equals(noun)) {
                System.out.println(item.getName());
                return item;
            }
        }
        return null;
    }

    public static void lookAtItem(Item itemYouAreLookingAt) {
        try {
            System.out.println(itemYouAreLookingAt.getDescription());
        } catch (NullPointerException e) {
            System.out.println("Not a valid item to look at!");
        }
    }

    private static void transferOb(Item targetItem, List<Item> objectToRemove, List<Item> objectToAdd) {
        objectToRemove.remove(targetItem);
        objectToAdd.add(targetItem);
    }

    public String takeItem(Item targetItem) {
        String display = "";
        List<Item> itemsInThisLocation = getCurrentLocation().getItems();
        Boolean isTargetItemHere = itemsInThisLocation.contains(targetItem);
        try {
            if (targetItem.getName().equals("")) {
                display = "nameless object"; // if no object specified
            } else if (isTargetItemHere == false) {
                display = "There is no " + targetItem + " here!";
            } else {
                transferOb(targetItem, getCurrentLocation().getItems(), getInventory());
                display = targetItem.getName() + " taken!";
            }
        } catch (NullPointerException e) {
            System.out.println("Item not found!");
        }
        return display;
    }

    public String dropItem(Item targetItem) {
        String display = "";
        Boolean isTargetItemHere = getInventory().contains(targetItem);
        try {
            if (targetItem.getName().equals("")) {
                display = "Which object would you like to drop?"; // if no object specified
            } else if (isTargetItemHere == false) {
                display = "That is not in your inventory!";
            } else {
                transferOb(targetItem, getInventory(), getCurrentLocation().getItems());
                display = targetItem.getName() + " dropped.";
            }
        } catch (NullPointerException e) {
            System.out.println("This item is not valid!");
        }
        return display;
    }

    public void move(Direction direction) {
        System.out.println(this.currentLocation);
        Location targetLocation = getCurrentLocation().getAdjacentLocation(direction);
        if (targetLocation != null) {
            setCurrentLocation(targetLocation);
            System.out.println("You moved to the " + targetLocation.getName());
            System.out.println(description());
        } else {
            System.out.println("Cannot move in that direction.");
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String description() {
        return "Name: " + this.name + "\nHealth: " + health + "\n" + currentLocation.description() + "\nInventory " +
                Arrays.asList(inventory);
    }

    @Override
    public String toString() {
        StringBuilder playerInformation = new StringBuilder();
        playerInformation.append("Current Location: ")
                .append(getCurrentLocation().getName())
                .append("\tLocation items: ")
                .append(Arrays.asList(inventory))
                .append("\tHealth: ")
                .append(getHealth())
                .append(" \\ 100")
                .append("\tInventory: ")
                .append(Arrays.asList(getInventory()))
                .append("\tPossible exits: ");
//                .append(Arrays.toString(getCurrentLocation().getExits()));
        return playerInformation.toString();
    }
}
