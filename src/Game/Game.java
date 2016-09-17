package Game;

import Cards.Card;
import Deck.Deck;
import Deck.DeckBuilder;
import Players.HumanPlayer;
import Players.Player;
import Trumps.Trump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
class Game {

    private final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    private final int CARDS_TO_A_HAND = 8;
    private int numPlayers;
    private Player[] players;
    private Player dealer;
    private Player currentPlayer;
    private Deck superTrumpsDeck;
    private Trump.TrumpCategories currentCategory;
    private Card lastPlayedCard;
    private int countRounds;




    int incrementCountRounds() {
        ++this.countRounds;
        return countRounds;
    }


    Game(int numPlayers, String userName) {
        superTrumpsDeck = DeckBuilder.buildDeck();
        this.numPlayers = numPlayers;
        players = new HumanPlayer[numPlayers];
        players[0] = new HumanPlayer(0, userName);
        for (int i=1; i < players.length; ++i){
            players[i] = new HumanPlayer(i, BOTNAMES[i-1]);
        }
        countRounds = 0;
        this.lastPlayedCard = superTrumpsDeck.getCard(0);

    }

    String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        this.currentPlayer = players[dealerID];
        return dealer.getName();
    }

    void dealInitialHands() {
        superTrumpsDeck.shuffle();
        ArrayList<Card> newHand;
        for (Player player:
             players) {
             newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
             player.setCurrentHand(newHand);

        }
//        for (Cards.Card card:
//             newHand) {
//            System.out.println(card.getTitle());
//        }
    }

    Player[] getPlayers() {
        return players;
    }

    Player getNextPlayer() {
        int currentPlayerID = Arrays.asList(players).indexOf(currentPlayer);
//        if the id of the current player is last in the list
        if (currentPlayerID == players.length - 1) {
//            start at the beginning again
            currentPlayer = players[0];
        }
//        otherwise, next player is the next in the list
        else {
            currentPlayer = players[currentPlayerID + 1];
        }
        return currentPlayer;
    }

    boolean checkIfWon() {
        boolean isWon = false;
        for (Player player:
             players) {
            if(player.getCurrentHand().size() == 0){
                isWon = true;
            }
        }
        return isWon;
    }

    void playTurn() {
//        this.currentCategory = Trump.TrumpCategories.CLEAVAGE;
//        System.out.println(lastPlayedCard.getTitle());
        currentPlayer.chooseCardToPlay(this.getCountRounds(), this.getLastPlayedCard(), this.currentCategory);
    }

    private int getCountRounds() {
        return countRounds;
    }

    private Card getLastPlayedCard() {
        return lastPlayedCard;
    }
}
