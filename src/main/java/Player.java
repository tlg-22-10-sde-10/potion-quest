import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private int health;
    private List<Item> inventory;
    private Map<String, Integer> stats;
    private Location currentLocation;

    public Player() {
    }

    public Player(String name, int health, List<Item> inventory, Map<String, Integer> stats, Location startingLocation) {
        setName(name);
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

    public static void talkToCharacters(Characters characterTalking){
        System.out.println(characterTalking.getDialogue());
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if(userChoice.equals("1")) {
            System.out.println(characterTalking.getResponses().get("1"));
        }
       else if(userChoice.equals("2")){
            System.out.println(characterTalking.getResponses().get("2"));
        }
       else if(userChoice.equals("3")){
            System.out.println(characterTalking.getResponses().get("3"));
        }
    }

    public void useHealingItem() {
        List<Item> itemsInPlayerInventory = getInventory();
        List<Item> toRemove = new ArrayList<Item>();
        List<Item> healingItems = new ArrayList<>();

        try {
            for (Item item : itemsInPlayerInventory) {
                if (item.getStatBuff() > 0) {
                    healingItems.add(item);
                }
            }
                if (healingItems.size() > 0) {
                    Item itemToUse = healingItems.get(0);
                    this.health = itemToUse.getStatBuff() + this.health;
                    System.out.println("You used " + itemToUse.getName()
                            + " to heal yourself by "
                            + itemToUse.getStatBuff()
                            + " points.");
                    toRemove.add(itemToUse);
                } else {
                    System.out.println("You don't have any healing items.");
                }
        } catch (NullPointerException e) {
            System.out.println("This item is not valid!");
        }
        itemsInPlayerInventory.removeAll(toRemove);
    }

    private static void transferOb(Item targetItem, List<Item> objectToRemove, List<Item> objectToAdd) {
        objectToRemove.remove(targetItem);
        objectToAdd.add(targetItem);
    }

    public String takeItem(Item targetItem) {
        String display = "";
        if (getInventory().size() == 5) {
            System.out.println("You are at max inventory size, you must drop an item to take this one.");
        } else if (getInventory().contains(targetItem)) {
            System.out.println("You already have this item.");
        } else if (!getCurrentLocation().getItems().contains(targetItem)) {
            System.out.println("There is no such item to be found here.");
        } else {
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
        }
        return display;
    }

    public String dropItem(Item targetItem) {
        String display = "";
        Boolean isTargetItemHere = getInventory().contains(targetItem);
        try {
            if (targetItem.getName().equals("")) {
                display = "Which object would you like to drop?"; // if no object specified
            } else if (!isTargetItemHere) {
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
        Location targetLocation = getCurrentLocation().getAdjacentLocation(direction);
        if (targetLocation != null) {
            setCurrentLocation(targetLocation);
            } else {
            System.out.println("Cannot move in that direction.");
        }
    }

    public int getHealth() {
        return this.health;
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
        return this.stats;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
