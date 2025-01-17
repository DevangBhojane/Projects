package assignment6_000901300;

/**
 * Represents a human in the fantasy world, extending the Inhabitant class.
 * Author: Devang Bhojane, 000901300
 */
public class Human extends Inhabitant{
    private String role;
    private Werewolf werewolfFriend;
    private int magicRating;

    /**
     * Constructor to initialize a human with specific attributes.
     *
     * @param name          The name of the human.
     * @param strength      The strength attribute of the human.
     * @param agility       The agility attribute of the human.
     * @param armour        The armour attribute of the human.
     * @param healthRating  The health rating of the human.
     * @param role          The role of the human.
     * @param werewolfFriend The werewolf friend of the human.
     */
    public Human(String name, int strength, int agility, int armour, int healthRating, String role, Werewolf werewolfFriend) {
        super(name, strength, agility, armour, healthRating);
        this.role = role;
        this.werewolfFriend = werewolfFriend;
        this.magicRating = (int) (Math.random() * 11);
    }

    /**
     * Constructor to initialize a human with a name and werewolf friend.
     *
     * @param name The name of the human.
     * @param werewolfFriend The werewolf friend of the human.
     */
    public Human(String name, Werewolf werewolfFriend) {
        super(name);
        this.werewolfFriend = werewolfFriend;
        this.magicRating = (int) (Math.random() * 11);
    }


    /**
     * Gets the role of the human.
     *
     * @return The role of the human.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the magic rating of the human, ensuring it is within the range 0 to 10.
     *
     * @param magicRating The magic rating to set.
     */
    public void setMagicRating(int magicRating) {
        this.magicRating = Math.max(0, Math.min(10, magicRating));
    }

    /**
     * Uses the human's magic, decrementing the magic rating.
     */
    public void useMagic() {
        if (magicRating > 0) {
            // Perform magic action
            magicRating--;
        } else {
            System.out.println("Error: Magic rating is 0 or less. Cannot perform magic.");
        }
    }

    /**
     * Returns a string representation of the human.
     *
     * @return A formatted string representing the human attributes.
     */
    public String toString() {
        return super.toString() + String.format(", Role: %s, Magic Rating: %d", role, magicRating);
    }
}
