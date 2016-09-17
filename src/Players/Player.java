package Players;

import Cards.Card;
import Trumps.Trump;

import java.util.ArrayList;

/**
 * Created by Draga on 17/09/2016.
 */
public abstract class Player {
    int id;

    ArrayList<Card> currentHand;
    String name;

    public String getName() {
        return name;
    }

    public void setCurrentHand(ArrayList<Card> currentHand) {
        this.currentHand = currentHand;
    }

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    ArrayList<Card> getPlayableCards(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
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

    public abstract Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory);

}
