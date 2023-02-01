public class StartingVillage extends Location {

    public StartingVillage() {
        super("Starting Village", new String[] {"Trinket", "Bag"}, new String[] {"Forest, Mountain Path"});
    }

    @Override
    public void locationDescription() {
        StringBuilder description = new StringBuilder();
        description.append("A quaint village near a dense forest. A few homes line the outskirts of town" +
                " with the village tavern in the middle.")
                .append("You see two possible paths to the East; a forest and a mountain path.");
        System.out.println(description.toString());

    }
}
