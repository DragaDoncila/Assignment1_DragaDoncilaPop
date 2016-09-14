import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
public class Game {
    enum TrumpCategories{HARDNESS, SPECIFIC_GRAVITY, CLEAVAGE, CRUSTAL_ABUNDANCE, ECONOMIC_VALUE}

    final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    final int CARDS_TO_A_HAND = 8;
    int numPlayers;
    Player[] players;
    Player dealer;
    Player currentPlayer;
    Deck superTrumpsDeck;
    Card lastPlayedCard;



    public int incrementCountRounds() {
        ++this.countRounds;
        return countRounds;
    }

    int countRounds;


    Game(int numPlayers, String userName) {
        superTrumpsDeck = DeckBuilder.buildDeck();
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        players[0] = new Player(0, userName);
        for (int i=1; i < players.length; ++i){
            players[i] = new Player(i, BOTNAMES[i-1]);
        }
        countRounds = 0;
    }

    public String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        this.currentPlayer = players[dealerID];
        return dealer.getName();
    }

    public void dealInitialHands() {
        superTrumpsDeck.shuffle();
        ArrayList<Card> newHand;
        for (Player player:
             players) {
             newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
             player.currentHand = newHand;

        }
//        for (Card card:
//             newHand) {
//            System.out.println(card.getTitle());
//        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getNextPlayer() {
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

    public boolean checkIfWon() {
        boolean isWon = false;
        for (Player player:
             players) {
            if(player.getCurrentHand().size() == 0){
                isWon = true;
            }
        }
        return isWon;
    }

//    public void playTurn() {
//        currentPlayer.chooseCardToPlay(this.getCountRounds(), this.getLastPlayedCard());
//    }

    public int getCountRounds() {
        return countRounds;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }
}
