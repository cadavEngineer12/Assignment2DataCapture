/**
 * Program that takes live data, for now just rain and apply a stat boost if the percent of rain is greater
 * than 50 percent
 * @author Charles, Angel, Ashton, Keiren
 */
public class Main {
    public static void main(String[] args) throws Exception {
        API data = new API();
        System.out.println(data.getRain() + "%" + " chance of Rain in Radford Virginia");
        Character character = new Character("Dr. Harden", 100, 15, 10);
        character.statBoost();
        System.out.println(character);
    }
}