import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Location {
    private String name;
    private String[] items;
    private String[] exits;
    private Map<Direction, Location> potentialExitsAvailable = new HashMap<>();

    public Location(String name, String[] items, String[] exits) {
        this.name = name;
        this.items = items;
        this.exits = exits;
    }

    public Location(String name) {
        this.name = name;
        this.potentialExitsAvailable = new HashMap<>();
    }

    public Location(String name, List<Location> adjacentLocations) {
        this.name = name;
    }

    public void addAdjacentLocation(Direction direction, Location location) {
        potentialExitsAvailable.put(direction, location);
    }

    public Location getAdjacentLocation(Direction direction) {
        switch (direction) {
            case NORTH:
                return getNorthLocation();
            case SOUTH:
                return getSouthLocation();
            case EAST:
                return getEastLocation();
            case WEST:
                return getWestLocation();
            default:
                return null;
        }
    }

    private Location getNorthLocation() {
        return potentialExitsAvailable.get(Direction.NORTH);
    }

    private Location getSouthLocation() {
        return potentialExitsAvailable.get(Direction.SOUTH);
    }

    private Location getEastLocation() {
        return potentialExitsAvailable.get(Direction.EAST);
    }

    private Location getWestLocation() {
        return potentialExitsAvailable.get(Direction.WEST);
    }

    public List<Direction> displayExits() {
        List<Direction> exits = new ArrayList<>();
        for (Direction direction : potentialExitsAvailable.keySet()) {
            exits.add(direction);
        }
        return  exits;
    }

    public List<String> displayAdjacentLocations() {
        List<Location> adjacentLocations = new ArrayList<>();
        List<String> adjacentLocationNames = new ArrayList<>();
        String name;
        for (Location adjacentLocation : potentialExitsAvailable.values()) {
            adjacentLocations.add(adjacentLocation);
            name = adjacentLocation.getName();
            adjacentLocationNames.add(name);
        }
        return  adjacentLocationNames;
    }

    public String description() {
        potentialExitsAvailable.values().stream();
        return "You are in the " + name
                + "\n" + "Paths are: " + displayExits()
                + "\n" + "Adjacent to your location is: " + displayAdjacentLocations()
                + "\n" + "You can find these items here: " + Arrays.asList(items);
    }

    //method will iterate through printing out each location on file.
    //need to continue researching the creation of each location and accessing individual elements
    public static void locationJsonParser() throws IOException {
        File file = new File("src/main/resources/locations.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Location> locations = objectMapper.readValue(file, new TypeReference<List<Location>>() {
        });
        for(int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i).getName());
//            System.out.println(locations.get(i).getDescription());
//            System.out.println(locations.get(i).getItems());
//            System.out.println(locations.get(i).getExits());
//            System.out.println();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getExits() {
        return exits;
    }

    public void setExits(String[] exits) {
        this.exits = exits;
    }
}
