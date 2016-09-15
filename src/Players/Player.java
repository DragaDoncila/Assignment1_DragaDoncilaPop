package Players;

import Cards.Card;
import Trumps.Trump;

import java.util.ArrayList;

/**Class describes current attributes of a player as well as their methods for interacting with the game
 * Created by Draga on 6/09/2016.
 */
public class Player {
    private int id;

    private ArrayList<Card> currentHand;
    private String name;

    public String getName() {
        return name;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public void setCurrentHand(ArrayList<Card> currentHand) {
        this.currentHand = currentHand;
    }

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        ArrayList<Card> playableCards = getPlayableCards(countRounds, lastPlayedCard, currentCategory);

        return playableCards.get(0);
    }

    private ArrayList<Card> getPlayableCards(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card:
                currentHand) {
            if (countRounds == 1) {
                playableCards.add(card);
            }
            else if (card.canPlayOn(countRounds, lastPlayedCard, currentCategory)){
                playableCards.add(card);
            }

        }
        return playableCards;
    }
}
