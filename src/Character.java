import java.io.IOException;
import java.net.URISyntaxException;


/**
 * THis class is a basic character class that uses buffs and a debuff depending on the weather
 * @author Charles
 */
public class Character {
    static API data = new API();
    private String name;
    private int health;
    private int attack;
    private int defense;

    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * In the main Game, this will be in the update loop so as the weather changes, so will the stats
     * @throws IOException In case it fails to grab the IO to throw the error
     * @throws URISyntaxException In case it can not grab the URI
     */

    public void statBoost() throws IOException, URISyntaxException {
        if (data.getRain() > 50) {
            System.out.println("All Buffs and Debuffs has been applied....");
            setDefense(getDefense() + 10);
            setAttack(getAttack() + 5);
            setHealth(getHealth() - 10);
        }

        if (data.getTemperature() > 45) {
            System.out.println("All Buffs and Debuffs has been applied....");
            setDefense(getDefense() + 5);
            setAttack(getAttack() - 5);
            setHealth(getHealth() +5);

        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
