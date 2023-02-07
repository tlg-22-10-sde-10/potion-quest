import java.util.Random;

public class Combat {
    private Player player = Game.getGameInstance().getPlayer();
    private Monster wolf = Game.getGameInstance().getMonster();

    public static int playerAttack(Player player) {
        Random rng = new Random();
        int playerStrength = player.getStats().get("Strength");
        return rng.nextInt(playerStrength);
    }

    public static int playerDefend(Player player) {
        Random rng = new Random();
        int playerDefense = player.getStats().get("Defense");
        return rng.nextInt(playerDefense);
    }

    public static int monsterAttack(Monster wolf) {
        Random rng = new Random();
        int wolfStrength = wolf.getStats().get("Strength");
        return rng.nextInt(wolfStrength);
    }

    public static int monsterDefend(Monster wolf) {
        Random rng = new Random();
        int wolfDefend = wolf.getStats().get("Defense");
        return rng.nextInt(wolfDefend);
    }

    public static int playerTakeDamage(int playerDefend,
                                       int monsterAttack) {
        int damage = 0;
        if(monsterAttack > playerDefend) {
            damage = monsterAttack;
        }
        return damage;
    }

    public static int monsterTakeDamage(int playerAttack, int monsterDefend) {
        int damage = 0;
        if(playerAttack > monsterDefend) {
            damage = playerAttack;
        }
        return damage;
    }
}
