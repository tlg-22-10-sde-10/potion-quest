import java.util.Arrays;
import java.util.Map;

public class Player {
    private int health;
    private String[] inventory;
    private Map<String, Integer> stats;

    private Location currentLocation;

    public Player(int health, String[] inventory, Map<String, Integer> stats, Location currentLocation) {
        setHealth(health);
        setInventory(inventory);
        setStats(stats);
        setCurrentLocation(currentLocation);
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
