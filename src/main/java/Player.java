import java.util.Arrays;
import java.util.Map;

public class Player {
    private static String name;
    private static int health;
    private static String[] inventory;
    private static Map<String, Integer> stats;

    private static Location currentLocation;

    public Player(String name, int health, Location startingLocation) {
        this.name = name;
        this.health = health;
        this.currentLocation = startingLocation;
    }

    public Player(int health, String[] inventory, Map<String, Integer> stats, Location currentLocation) {
        setHealth(health);
        setInventory(inventory);
        setStats(stats);
        setCurrentLocation(currentLocation);
    }

    public Player() {

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

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
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
        return "Name: " + name + "\nHealth: " + health + "\n" + currentLocation.description();
    }

    public String toString() {
        StringBuilder playerInformation = new StringBuilder();
        playerInformation.append("Current Location: ")
                .append(getCurrentLocation().getName())
                .append("\tLocation items: ")
                .append(Arrays.toString(getCurrentLocation().getItems()))
                .append("\tHealth: ")
                .append(getHealth())
                .append(" \\ 100")
                .append("\tInventory: ")
                .append(Arrays.toString(getInventory()))
                .append("\tPossible exits: ")
                .append(Arrays.toString(getCurrentLocation().getExits()));
        return playerInformation.toString();
    }
}
