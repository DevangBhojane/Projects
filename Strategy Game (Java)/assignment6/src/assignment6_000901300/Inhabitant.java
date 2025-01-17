package assignment6_000901300;

/**
 * Represents an inhabitant.
 * Author: Devang Bhojane, 000901300
 */
public class Inhabitant {
    private String name;
    private int strength;
    private int agility;
    private int armour;
    private int healthRating;
    private boolean alive;

    /**
     * Constructor to initialize an inhabitant with specific attributes.
     *
     * @param name          The name of the inhabitant.
     * @param strength      The strength attribute of the inhabitant.
     * @param agility       The agility attribute of the inhabitant.
     * @param armour        The armour attribute of the inhabitant.
     * @param healthRating  The health rating of the inhabitant.
     */
    public Inhabitant(String name, int strength, int agility, int armour, int healthRating) {
        this.name = name;
        this.strength = validateAttribute(strength);
        this.agility = validateAttribute(agility);
        this.armour = validateAttribute(armour);
        this.healthRating = validateAttribute(healthRating);
        this.alive = healthRating > 0;
    }

    /**
     * Constructor to initialize an inhabitant with a name only.
     * @param name The name of the inhabitant.
     */
    public Inhabitant(String name) {
        this(name, 0, 0, 0, 0);
    }


    /**
     * Gets the name of the inhabitant.
     * @return The name of the inhabitant.
     */
    public String getName() {
        return name;
    }


    /**
     * Checks if the inhabitant is alive.
     * @return True if the inhabitant is alive, false otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Calculates the attack value of the inhabitant.
     * @return The attack value.
     */
    public int attack() {
        if (!alive) {
            System.out.println("Error: Inhabitant is dead and cannot attack.");
            return 0;
        }
        return (strength + agility + healthRating) / 3;
    }

    /**
     * Handles the defense of the inhabitant when attacked.
     *
     * @param damage The amount of damage inflicted by an attacker.
     */
    public void defend(int damage) {
        if (!alive) {
            System.out.println("Error: Inhabitant is dead and cannot defend.");
            return;
        }

        int damageTaken = damage / armour;
        healthRating -= damageTaken < 1 ? 1 : damageTaken;

        if (healthRating <= 0) {
            alive = false;
        }
    }

    /**
     * Returns a string representation of the inhabitant.
     *
     * @return A formatted string representing the inhabitant's attributes.
     */
    public String toString() {
        return String.format("%s - %s - Health: %d, Strength: %d, Agility: %d, Armour: %d",
                getClass().getSimpleName(), name, healthRating, strength, agility, armour);
    }

    private int validateAttribute(int value) {
        return Math.max(0, Math.min(10, value));
    }
}
