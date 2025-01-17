package assignment6_000901300;

/**
 * The main class to test the implemented classes in the fantasy world.
 * Author: Devang Bhojane, 000901300
 */
public class Main {
    public static void main(String[] args) {

        // Inhabitant
        Inhabitant inhabitant1 = new Inhabitant("John Doe", 5, 6, 3, 8);
        System.out.println(inhabitant1.toString());

        // Werewolf
        Werewolf werewolf1 = new Werewolf("Werewolf1", 7, 8, 5, 10, "Blue Moon");
        System.out.println(werewolf1.toString());

        // Fairy
        Fairy fairy1 = new Fairy("Fairy1");
        System.out.println(fairy1.toString());

        // Human
        Werewolf werewolfFriend = new Werewolf("Friendly Werewolf");
        Human human1 = new Human("Human1", 6, 7, 4, 9, "Healer", werewolfFriend);
        System.out.println(human1.toString());
    }
}
