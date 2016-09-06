import java.util.ArrayList;

/**Class describes current attributes of a player as well as their methods for interacting with the game
 * Created by Draga on 6/09/2016.
 */
public class Player {
    int id;
    ArrayList<Card> currentHand;
    private String name;

    public String getName() {
        return name;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;

    }
}
