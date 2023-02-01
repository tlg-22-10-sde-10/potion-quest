public abstract class Location {
    private String name;
    private String[] items;
    private String[] exits;
    public Location(String name, String[] items, String[] exits) {
        setName(name);
        setItems(items);
        setExits(exits);
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
