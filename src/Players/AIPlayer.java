package Players;

import Cards.Card;
import Trumps.Trump;

/**Class handles behaviours for bots involved in MineralSupertrumpts games.
 * Created by Draga on 17/09/2016.
 */
public class AIPlayer extends Player{
    private final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};

    public AIPlayer(int id) {
        this.type = PlayerTypes.BOT;
        this.id = id;
        this.name = BOTNAMES[id-1];

    }


    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        return null;
    }
}
