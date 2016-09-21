package Players;

import Cards.Card;
import Trumps.Trump;

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

    public abstract String chooseCategory(Card lastPlayedCard);

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

    public void setPlayableCards(Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card:
                currentHand) {
            if (card.canPlayOn(lastPlayedCard, currentCategory)){
                playableCards.add(card);
            }

        }
        this.playableCards = playableCards;
    }

    public ArrayList<Card> getPlayableCards() {
        return playableCards;
    }

    public abstract Card playFirstCard(int cardChoice);


}
