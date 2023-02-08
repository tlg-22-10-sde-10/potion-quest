import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Location {
    private String name;
    private List<Item> items;
    private Map<String, Location> exits;
    private String description;
    private List<Direction> availableDirections;

    public List<Direction> getAvailableDirections() {
        return availableDirections;
    }

    public void setAvailableDirections(List<Direction> availableDirections) {
        this.availableDirections = availableDirections;
    }

    public Location() {
    }

    public Location(String name, List<Location> adjacentLocations) {
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
        this.exits = new HashMap<>();
    }

    public Location(String name, List<Item> items, Map<String, Location> exits) {
        this.name = name;
        this.items = items;
        this.exits = exits;
    }

    //method will iterate through printing out each location on file.
    //need to continue researching the creation of each location and accessing individual elements
    public static Map<String, Location> locationJsonParser() throws IOException {
//        File file = new File("src/main/resources/locations.json");
        Map<String, Location> locationMap;
        try (InputStream inputStream = Location.class.getClassLoader().getResourceAsStream("locations.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Location> locations = objectMapper.readValue(inputStream, new TypeReference<List<Location>>() {
            });
            locationMap = locations
                    .stream()
                    .collect(Collectors.toMap(Location::getName, Function.identity()));
        }
        return locationMap;
    }

    public void addAdjacentLocation(String direction, Location location) {
        exits.put(direction, location);
    }

    public Location getAdjacentLocation(Direction direction) {
        switch (direction) {
            case NORTH:
                return exits.get("NORTH");
            case SOUTH:
                return exits.get("SOUTH");
            case EAST:
                return exits.get("EAST");
            case WEST:
                return exits.get("WEST");
            default:
                return null;
        }
    }

    public List<String> displayAdjacentLocations() {
        List<Location> adjacentLocations = new ArrayList<>();
        List<String> adjacentLocationNames = new ArrayList<>();
        String name;
        for (Location adjacentLocation : exits.values()) {
            adjacentLocations.add(adjacentLocation);
            name = adjacentLocation.getName();
            adjacentLocationNames.add(name);
        }
        return adjacentLocationNames;
    }

    public List<String> displayExits() {
        List<String> exits = new ArrayList<>();
        for (String direction : this.getExits().keySet()) {
            exits.add(direction);
        }
        return exits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, Location> getExits() {
        return exits;
    }

    public void setExits(Map<String, Location> exits) {
        this.exits = exits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description() {
        return "You are now in the " + getName()
                + "\n" + getDescription()
                + "\n" + "Directions are: " + displayExits()
                + "\n" + "Adjacent to your location is the: " + displayAdjacentLocations()
                + "\n" + "You can find these items here: " + getItems()
                .stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        // System.out.println("Television.equals");
        boolean result = false;
        if (obj instanceof Location) {
            Location other = (Location) obj;
            result = Objects.equals(this.getName(), other.getName());
        }
        return result;
    }

}
