import java.util.Scanner;

/**Class handles playing a MineralSupertrump game from start to finish
 * Created by Draga on 6/09/2016.
 */
public class PlayGame {
    final static String MENU = "Mineral Supertrumps Menu\n" +
            "(P)lay game\n" +
            "(I)nstructions\n" +
            "(Q)uit";
    final static int MIN_PLAYERS = 3;
    final static int MAX_PLAYERS = 5;


    public static void main(String[] args) {
        System.out.println("Welcome to Mineral Supertrumps!");
        Scanner input = new Scanner(System.in);
        System.out.printf(MENU);
        String userChoice = input.nextLine().toUpperCase();
        while (!userChoice.equals("Q")) {
            if (userChoice.equals("I")) {
                System.out.println("Instructions");
            } else {
                startnewgame();

            }
            System.out.printf(MENU);
            userChoice = input.nextLine().toUpperCase();
        }
    }

    private static void startnewgame() {
        int numPlayers = getValidNumPlayers();
        Game superTrumpsGame = new Game(numPlayers);
        String dealerName = superTrumpsGame.selectDealer();
        System.out.println("Dealer has been selected! " + dealerName + " is dealing today.");
    }

    private static int getValidNumPlayers() {
        Scanner input = new Scanner(System.in);
        System.out.println("This is a game for " + MIN_PLAYERS + " to " + MAX_PLAYERS + " people.");
        System.out.printf("Please enter the number of players today>>> ");
        int numPlayers = input.nextInt();
        while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
            System.out.println("That is not a valid number of players.");

            System.out.println("This is a game for " + MIN_PLAYERS + " to " + MAX_PLAYERS + " people.");
            System.out.printf("Please enter the number of players today>>> ");
            numPlayers = input.nextInt();
        }
        return numPlayers;
    }


}

