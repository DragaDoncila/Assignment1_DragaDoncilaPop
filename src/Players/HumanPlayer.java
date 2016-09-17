package Players;

import Cards.Card;
import Trumps.Trump;

import java.util.ArrayList;

/**Class describes current attributes of a player as well as their methods for interacting with the game
 * Created by Draga on 6/09/2016.
 */
public class HumanPlayer extends Player {
    /*
    TODO:Turn this into an abstract class with abstract method chooseCardToPlay. Descend AIPlayer and UserPlayer. UserPlayer gets playable cards, and is
    asked to select a card. if card in playable cards, play it.
    AI HumanPlayer for now returns a random card from playable cards. Eventually will turn into AggressiveAIPlayer, PassiveAIPlayer, etc.
    */

    public HumanPlayer(int id, String name) {
        this.id = id;
        this.name = name;

    }

    @Override
    public Card chooseCardToPlay(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        ArrayList<Card> playableCards = getPlayableCards(countRounds, lastPlayedCard, currentCategory);

        return playableCards.get(0);
    }
}
