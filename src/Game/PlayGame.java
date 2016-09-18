package Game;

import Cards.Card;
import Players.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**Class handles playing a MineralSupertrump game from start to finish
 * Created by Draga on 6/09/2016.
 */
public class PlayGame {
    private final static String MENU = "Mineral Supertrumps Menu\n" +
            "(P)lay game\n" +
            "(I)nstructions\n" +
            "(Q)uit";
    private final static int MIN_PLAYERS = 3;
    private final static int MAX_PLAYERS = 5;
    private final static String[] validTrumps = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};


    public static void main(String[] args) {
        System.out.println("Welcome to Mineral Supertrumps!");
        Scanner input = new Scanner(System.in);
        System.out.printf("What is your name? >>> ");
//        TODO: error check non blank username
        String userName = input.nextLine();
        System.out.printf(MENU);
        String userChoice = input.nextLine().toUpperCase();
        while (!userChoice.equals("Q")) {
            if (userChoice.equals("I")) {
                System.out.println("Instructions");
            } else {
                Game superTrumpsGame = startnewgame(userName);
                System.out.println("Ready to play, " + userName);
                Player playerUp = superTrumpsGame.getNextPlayer();
                System.out.println("Let's go! It's " + playerUp.getName() + "'s turn.");
                if (superTrumpsGame.userIsUp()){
                    playUserFirstTurn(superTrumpsGame, playerUp);
                }
                else {
                    superTrumpsGame.playFirstTurn();
                }

                while (superTrumpsGame.checkIfWon() == false){
                    System.out.println("Round: " + superTrumpsGame.incrementCountRounds());
//                    Card cardChoice = superTrumpsGame.playTurn();
                    break;
                }

            }
            System.out.printf(MENU);
            userChoice = input.nextLine().toUpperCase();
        }
    }

    static void playUserFirstTurn(Game superTrumpsGame, Player playerUp) {
        Card cardChoice;
        cardChoice = getUserCardChoice(playerUp);
        System.out.println("You've chosen to play : " + cardChoice.getTitle());
        int trumpChoiceNum = getValidTrumpChoice();
        String trumpChoiceStr = validTrumps[trumpChoiceNum];
        superTrumpsGame.playFirstTurn(cardChoice, trumpChoiceStr);
    }

    protected static int getValidTrumpChoice() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your trump choice?");
        for (int i = 0; i < validTrumps.length; i++) {
            System.out.println("<" + i + ">" + validTrumps[i]);
        }
        int userChoice = getValidNumInRange(validTrumps.length-1);
        return userChoice;
    }

    protected static Card getUserCardChoice(Player playerUp) {
        Card userCardChoice;
        Scanner keyboard = new Scanner(System.in);
            System.out.println("Choose a card to play: ");
            displayCardChoices(playerUp);
            int userChoice = getValidNumInRange(playerUp.getCurrentHand().size());
            userCardChoice = playerUp.getCurrentHand().get(userChoice);
        return userCardChoice;
    }

    private static int getValidNumInRange(int max) {
        boolean validChoice = false;
        Scanner input = new Scanner(System.in);
        System.out.printf("Your choice >>> ");
        String userChoice = input.nextLine();
        int num = -1;
        while (!validChoice) {
            try {
                num = Integer.parseInt(userChoice);
                if (num < 0 || num > max){
                    System.out.println("That is not a valid choice.");
                    System.out.printf("Your choice >>> ");
                    userChoice = input.nextLine();
                }
                else{
                    validChoice = true;
                }
            }
            catch (NumberFormatException error){
                System.out.println("That is not a valid choice.");
                System.out.printf("Your choice >>> ");
                userChoice = input.nextLine();
            }
        }
        return num;
    }

    protected static void displayCardChoices(Player playerUp) {
        int i = 0;
        for (Card card:
                playerUp.getCurrentHand()) {
            System.out.println("<" + i + ">" + card.getTitle());
            ++i;
        }
    }

    private static Game startnewgame(String userName) {
        int numPlayers = getValidNumPlayers();
        Game superTrumpsGame = new Game(numPlayers, userName);
        System.out.println("Let's see who's playing today!");
        Player[] allPlayers = superTrumpsGame.getPlayers();
        for (Player player:
             allPlayers) {
            System.out.println(player.getName());
        }
        String dealerName = superTrumpsGame.selectDealer();
        System.out.println("Dealer has been selected! " + dealerName + " is dealing today.");
        superTrumpsGame.dealInitialHands();
        ArrayList<Card> userHand = superTrumpsGame.getPlayers()[0].getCurrentHand();
        System.out.println(userName + "! Your hand has been dealt!");
//      TODO: Move this to a method of deck or hand.
        for (Card card:
             userHand) {
            System.out.println(card.getTitle());
        }
        return superTrumpsGame;
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

