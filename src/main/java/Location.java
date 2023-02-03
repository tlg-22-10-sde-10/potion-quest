import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Location {
    private String name;
    private String[] items;
    private String[] exits;
    public Location(String name, String[] items, String[] exits) {
        setName(name);
        setItems(items);
        setExits(exits);
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
    public abstract void locationDescription();

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
