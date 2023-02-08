import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Item {
    private String name;
    private String description;

    public Item(){

    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Map<String, Item> itemJsonParser() throws IOException {
        Map<String, Item> itemsMap;
        try (InputStream inputStream = Item.class.getClassLoader().getResourceAsStream("item.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Item> items = objectMapper.readValue(inputStream, new TypeReference<List<Item>>() {
            });
            itemsMap = items
                    .stream()
                    .collect(Collectors.toMap(Item::getName, Function.identity()));

        }
        return itemsMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        // System.out.println("Television.equals");
        boolean result = false;
        if (obj instanceof Item) {
            Item other = (Item) obj;
            result = Objects.equals(this.getName(), other.getName());
        }
        return result;
    }
}
