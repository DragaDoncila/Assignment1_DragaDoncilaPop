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

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card:
             currentHand) {
//            CHANGE TO 1!!!
            if (countRounds == 0) {
                playableCards.add(card);
            }
            else if (card.canPlayOn(countRounds, lastPlayedCard, currentCategory)){
                playableCards.add(card);
            }

        }
        for (Card card:
             playableCards) {
            System.out.println(card.getTitle());
        }

        return playableCards.get(0);
    }
}
