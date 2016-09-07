import java.util.ArrayList;
import java.util.Random;

/**Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
public class Game {
    final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    final int CARDS_TO_A_HAND = 8;
    int numPlayers;
    Player[] players;
    Player dealer;
    Deck superTrumpsDeck;


    Game(int numPlayers, String userName) {
        superTrumpsDeck = DeckBuilder.buildDeck();
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        players[0] = new Player(0, userName);
        for (int i=1; i < players.length; ++i){
            players[i] = new Player(i, BOTNAMES[i]);
        }
    }

    public String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        return dealer.getName();
    }

    public void dealInitialHands() {
        superTrumpsDeck.shuffle();
        ArrayList<Card> newHand = new ArrayList<Card>(CARDS_TO_A_HAND);
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
}
