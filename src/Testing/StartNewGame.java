package Testing;

import Game.Game;
import Players.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**Class tests the user story and associated function, startNewGame();
 * Created by Draga on 27/09/2016.
 */
public class StartNewGame {
    public static void main(String[] args) {
        System.out.println("Testing starting game with valid number of players...\n");

        System.out.println("Potential numbers of players: ");
        int[] potentialPlayerNum = {3, 6, 4, 1, 7, 0, 8, 2, 7, 5};
        System.out.println(Arrays.toString(potentialPlayerNum) + "\n");

        System.out.println("Valid number of players range between " + Game.MIN_PLAYERS + " and " + Game.MAX_PLAYERS);
        System.out.println("##################################");

        ArrayList<Game> myGames = new ArrayList<>();
        for (int num :
                potentialPlayerNum) {
            boolean isValid = Game.isValidNumPlayers(num);
            System.out.println(num + " is valid number of players: " + isValid);
            if (isValid){
                Game tempGame = new Game(num, "USER");
                myGames.add(tempGame);
            }

        }

        System.out.println();
        for (int i = 0; i < myGames.size(); i++) {
            System.out.println("Game: " + (i + 1));
            Game currentGame = myGames.get(i);
            for (Player player :
                    currentGame.getPlayers()) {
                System.out.printf(player.getName() + ", ");
            }
            System.out.println();
        }

//        prove that the same thing happens using startNewGame()!!!
    }
}
