import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Monster {
    private String name;
    private Map<String, Integer> stats;
    private int health;
    private int id;

    public Monster() {
    }

    public Monster(String name, Map<String, Integer> stats) {
        setName(name);
        setStats(stats);
    }


    //read monster.json, creates map of monster from the file
    public static Map<String, Monster> monsterJsonParser() throws IOException {
        Map<String, Monster> monstersMap;
        try (InputStream inputStream = Monster.class.getClassLoader().getResourceAsStream("monster.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Monster> monsters = objectMapper.readValue(inputStream, new TypeReference<List<Monster>>() {
            });
            monstersMap = monsters
                    .stream()
                    .collect(Collectors.toMap(Monster::getName, Function.identity()));

        }
        return monstersMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
