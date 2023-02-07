import java.util.Map;

public class Monster {
    private String name;
    private Map<String, Integer> stats;

    public Monster(String name, Map<String, Integer> stats) {
        setName(name);
        setStats(stats);
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
}
