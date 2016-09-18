package Players;

import Cards.Card;
import Trumps.Trump;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**Abstract class outlays behaviours of Players for MineralSupertrumps games.
 * Created by Draga on 17/09/2016.
 */
public abstract class Player {

    public PlayerTypes getType() {
        return type;
    }

    public StringBuilder getCurrentHandTitles() {
        StringBuilder titlesStr = new StringBuilder();
        for (Card card:
             currentHand) {
            titlesStr.append(card.getTitle() + "\n");
        }
        return titlesStr;
    }

    public enum PlayerTypes{USER, BOT};
    int id;

    PlayerTypes type;
    ArrayList<Card> currentHand;
    private ArrayList<Card> playableCards;
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

    public void setPlayableCards(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
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
        this.playableCards = playableCards;
    }

    public ArrayList<Card> getPlayableCards() {
        return playableCards;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
