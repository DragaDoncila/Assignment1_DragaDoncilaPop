package Game;

import Cards.Card;
import Players.Player;

/**Testing a hard coded game object
 * Created by Draga on 17/09/2016.
 */
public class TestGame {
    private final static String[] validTrumps = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};

    public static void main(String[] args) {
        Game newGame = new Game(5, "Jane");
        System.out.println("Playing today:");
        Player[] allPlayers = newGame.getPlayers();
        for (Player player :
                allPlayers) {
            System.out.println(player.getName());
        }
        System.out.println();
        System.out.println("#####################");
        System.out.println();
        System.out.println("Dealer is: " + newGame.selectDealer(allPlayers[allPlayers.length -1]));
        newGame.dealInitialHands();
        for (Player pl : allPlayers) {
            System.out.println(pl.getName() + "'s hand: ");
            System.out.println("#######################");
            for (Card c :
                    pl.getCurrentHand()) {
                System.out.println(c.getTitle());
            }
            System.out.println("#################################################");
        }
        Player playerUp = newGame.getNextPlayer();
        System.out.println("Next up: " + playerUp.getName());
        if (newGame.userIsUp()){
            PlayGame.userPlayFirstTurn(newGame, playerUp);
            PlayGame.displayTurnResults(newGame, playerUp);
        }
    }
}
