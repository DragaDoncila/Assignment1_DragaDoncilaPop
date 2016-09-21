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

    }


    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        return null;
    }

    @Override
    public String chooseCategory(Card lastPlayedCard) {
        int trumpChoice = new Random().nextInt(trumpCats.length);
        return trumpCats[trumpChoice];
    }

    @Override
    public Card playFirstCard(int cardChoice) {
        cardChoice = new Random().nextInt(currentHand.size());
        return currentHand.remove(cardChoice);
    }
}
