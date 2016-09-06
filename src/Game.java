import java.util.ArrayList;
import java.util.Random;

/**Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
public class Game {
    final String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    int numPlayers;
    Player[] players;
    Player dealer;

    Game(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        for (int i=0; i < players.length; ++i){
            players[i] = new Player(i, BOTNAMES[i]);
        }
    }

    public String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        return dealer.getName();
    }
}
