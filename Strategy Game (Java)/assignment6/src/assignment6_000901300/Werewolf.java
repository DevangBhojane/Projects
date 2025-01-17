package assignment6_000901300;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a werewolf, extending the Inhabitant class.
 * Author: Devang Bhojane, 000901300
 */
public class Werewolf extends Inhabitant {
    private String pack;
    private List<Fairy> allies;

    /**
     * Constructor to initialize a werewolf with specific attributes.
     *
     * @param name          The name of the werewolf.
     * @param strength      The strength attribute of the werewolf.
     * @param agility       The agility attribute of the werewolf.
     * @param armour        The armour attribute of the werewolf.
     * @param healthRating  The health rating of the werewolf.
     * @param pack          The pack to which the werewolf belongs.
     */
    public Werewolf(String name, int strength, int agility, int armour, int healthRating, String pack) {
        super(name, strength, agility, armour, healthRating);
        this.pack = pack;
        this.allies = new ArrayList<>();
    }

    /**
     * Constructor to initialize a werewolf with a name only.
     *
     * @param name The name of the werewolf.
     */
    public Werewolf(String name) {
        super(name);
        this.allies = new ArrayList<>();
    }

    /**
     * Gets the pack to which the werewolf belongs.
     *
     * @return The pack name.
     */
    public String getPack() {
        return pack;
    }


    /**
     * Adds a fairy ally to the werewolf's list of allies.
     *
     * @param ally The fairy ally to add.
     */
    public void addAlly(Fairy ally) {
        allies.add(ally);
    }

    /**
     * Removes a fairy ally from the werewolf's list of allies.
     *
     * @param ally The fairy ally to remove.
     */
    public void removeAlly(Fairy ally) {
        allies.remove(ally);
    }

    /**
     * Returns a string representation of the werewolf.
     *
     * @return A formatted string representing the werewolf's attributes.
     */
    public String toString() {
        return super.toString() + String.format(", Pack: %s", pack);
    }
}
