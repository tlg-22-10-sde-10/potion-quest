import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private static String name;
    private static int health;
    private static List<Item> inventory;
    private static Map<String, Integer> stats;
    private static Location currentLocation;

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

    private void transferOb(Item itemToInventory, List<Item> takeObjectFromLocation, List<Item> toInventory) {
        takeObjectFromLocation.remove(itemToInventory);
        toInventory.add(itemToInventory);
    }

    public String takeItem(Item targetItem) {
        String display = "";
        List<Item> itemsInThisLocation = this.getCurrentLocation().getItems();
        Boolean isTargetItemHere = itemsInThisLocation.contains("targetItem");
//        List<Item> item = itemsInThisLocation.stream().filter(u ->
//                u.getName().equals(targetItem)).collect(Collectors.toList());
        if (targetItem.equals("")) {
            display = "nameless object"; // if no object specified
        }
        if (isTargetItemHere == null) {
            display = "There is no " + targetItem + " here!";
        } else {
            transferOb(targetItem, this.getCurrentLocation().getItems(), this.getInventory());
            display = targetItem.getName() + " taken!";
        }
        return display;
    }

    public String dropOb(Item targetItem) {
        String display = "";
        List<Item> itemsInThisLocation = this.getCurrentLocation().getItems();
        Boolean isTargetItemHere = itemsInThisLocation.contains(targetItem);
        if (targetItem.equals("")) {
            display = "Which object would you like to drop?"; // if no object specified
        } else if (targetItem == null) {
            display = "That is not in your inventory!";
        } else {
            transferOb(targetItem, this.getCurrentLocation().getItems(), this.getInventory());
            display = targetItem.getName() + " dropped.";
        }
        return display;
    }

    public static void move(Direction direction) {
        Location targetLocation = currentLocation.getAdjacentLocation(direction);
        if (targetLocation != null) {
            currentLocation = targetLocation;
            System.out.println("You moved to the " + targetLocation.getName());
            System.out.println(description());
        }
        else {
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
        return inventory;
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
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public static String description() {
        return "Name: " + name + "\nHealth: " + health + "\n" + currentLocation.description() + "\nInventory " +
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
