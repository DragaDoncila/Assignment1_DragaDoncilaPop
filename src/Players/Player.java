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

    public abstract String chooseCategory();

    public void addCard(Card drawnCard){
        currentHand.add(drawnCard);
    }

    public Card getCard(int cardChoice) {
        return currentHand.get(cardChoice);
    }

    public abstract Card playCard(int cardChoice);

    public boolean hasPlayableCards() {
        if ((playableCards.size() != 0)) return true;
        else return false;
    }

    public boolean hasCombo() {
        boolean hasCombo = false;
        int countComboCards = 0;
        for (Card card :
                currentHand) {
            if (card.isComboCard()){
                ++countComboCards;
            }
        }
        if (countComboCards == 2) {
            hasCombo = true;
        }
        return hasCombo;
    }

    public Card playCombo() {
        int magnetiteIndex = -1;
        int geophysIndex = -1;

        for (int i = 0; i < currentHand.size(); i++) {
            Card currentCard = currentHand.get(i);
            if (currentCard.getTitle().equals("Magnetite")){
                magnetiteIndex = i;
            }
            else if (currentCard.getTitle().equals("The Geophysicist")){
                geophysIndex = i;
            }
        }
        //TODO: TRY EXCEPT
        currentHand.remove(geophysIndex);
        return currentHand.remove(magnetiteIndex);
    }


    public enum PlayerTypes{USER, BOT};
    int id;

    PlayerTypes type;
    ArrayList<Card> currentHand;
    ArrayList<Card> playableCards;
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
