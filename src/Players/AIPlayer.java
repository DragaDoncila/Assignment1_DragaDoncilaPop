package Players;

import Cards.Card;
import Trumps.Trump;

import java.util.Random;

/**Class handles behaviours for bots involved in MineralSupertrumpts games.
 * Created by Draga on 17/09/2016.
 */
public class AIPlayer extends Player{
    private final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    private final String[] trumpCats = {"hardness", "cleavage", "crustalabundance", "economicvalue", "specificgravity"};

    public AIPlayer(int id) {
        this.type = PlayerTypes.BOT;
        this.id = id;
        this.name = BOTNAMES[id-1];
        this.isOut = false;

    }


    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        return null;
    }

    @Override
    public String chooseCategory() {
        int trumpChoice = new Random().nextInt(trumpCats.length);
        return trumpCats[trumpChoice];
    }

    @Override
    public Card playCard(int cardChoice) {
        //Randomly select a playable card
        cardChoice = new Random().nextInt(playableCards.size());
        Card chosenCard = playableCards.get(cardChoice);
//        Remove the card from the hand, not just playable array before returning
        int handIndex = -1;
        for (int i = 0; i < currentHand.size(); i++) {
            Card currentCard = currentHand.get(i);
            if (currentCard.getTitle().equals(chosenCard.getTitle())){
                handIndex = i;
            }
        }
        currentHand.remove(handIndex);
        return playableCards.remove(cardChoice);
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public Card playFirstCard(int cardChoice, boolean isStartofGame) {
        cardChoice = new Random().nextInt(currentHand.size());
        Card potentialCard = currentHand.get(cardChoice);
        if (isStartofGame){
            while (potentialCard.isTrump()){
                cardChoice = new Random().nextInt(currentHand.size());
                potentialCard = currentHand.get(cardChoice);
            }
            return currentHand.remove(cardChoice);
        }
        else {
            return currentHand.remove(cardChoice);
        }
    }
}
