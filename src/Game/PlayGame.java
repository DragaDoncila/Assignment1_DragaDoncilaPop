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
    private final static String[] validTrumpChoices = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};


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
                    userPlayFirstTurn(superTrumpsGame, playerUp);
                }
                else {
                    superTrumpsGame.playFirstTurn();
                }
                displayTurnResults(superTrumpsGame, playerUp);
                while (!superTrumpsGame.isWon()){
                    playerUp = superTrumpsGame.getNextPlayer();
                    playerUp.setPlayableCards(superTrumpsGame.getLastPlayedCard(), superTrumpsGame.getCurrentCategory());
                    System.out.println("Let's go! It's " + playerUp.getName() + "'s turn.");
                    if (superTrumpsGame.userIsUp()){
                        userPlayTurn(superTrumpsGame, playerUp);
                        displayTurnResults(superTrumpsGame, playerUp);
                    }
                    else {
//                        AI plays their turn
                        if (playerUp.hasPlayableCards()){
                            System.out.println("Has playable");
                            superTrumpsGame.playTurn();
                            displayTurnResults(superTrumpsGame, playerUp);
                        }
                        else {
                            System.out.println("Has no playable");
                            superTrumpsGame.pass();
                            System.out.println(playerUp.getName() + " has passed.");
                        }
                    }
                }
                System.out.println("YOU WON!");

            }
            System.out.printf(MENU);
            userChoice = input.nextLine().toUpperCase();
        }
    }

    protected static void userPlayTurn(Game superTrumpsGame, Player playerUp) {
        int numPlayable = playerUp.getPlayableCards().size();
        if (numPlayable == 0){
            System.out.println("No cards in your hand can play on " + superTrumpsGame.getLastPlayedCard().getTitle() + ".");
            System.out.println("You must pass.");
            superTrumpsGame.pass();
        }
        else {
            int cardChoice = getUserCardChoice(playerUp);
//            We get the card, but do not play it until it is checked as playable
            Card chosenCard = playerUp.getCard(cardChoice);
            boolean validCard = false;
            while (!validCard) {
                if (superTrumpsGame.isPlayable(chosenCard)){
//                    If the card is actually playable, we play it and remove it from the hand
                    chosenCard = playerUp.playCard(cardChoice);
                    if (chosenCard.isGeologist()){
                        String trumpChoice = getTrumpStr();
                        superTrumpsGame.playTurn(chosenCard, trumpChoice);
                        validCard = true;
                    }
    //                Card chosen is playable and is not the Geologist
                    else {
                        superTrumpsGame.playTurn(chosenCard);
                        validCard = true;
                    }
                }
    //            User has playable cards but has chosen unplayable
                else {
                    System.out.println("That card cannot play on " + superTrumpsGame.getLastPlayedCard().getTitle());
                    cardChoice = getUserCardChoice(playerUp);
                    chosenCard = playerUp.getCard(cardChoice);
                }
            }
        }
        /*Get card choice from the user
        * while !valid
        * if game.playableCardChosen AND card isn't geologist:
        *           playTurn(card)
        *           isvalid
        *else if card is Geologist:
        *           get trump choice
        *           playTurn(card, trumpChoice)
        *           isvalid
        *else (it's not playable and it's not geologist)
        *           get card choice again
*/
    }

    protected static void displayTurnResults(Game superTrumpsGame, Player playerUp) {
        System.out.println("Turn Complete!");
        System.out.println(playerUp.getName() + " played " + superTrumpsGame.getLastPlayedCard().getTitle());
        System.out.println("Current trump category is: " + superTrumpsGame.getCurrentCategory());


    }

    static void userPlayFirstTurn(Game superTrumpsGame, Player playerUp) {
        //TODO: Add functionality for user choosing a supertrumps card.
        int cardChoice = getUserCardChoice(playerUp);
        Card chosenCard = playerUp.playFirstCard(cardChoice);
        if (chosenCard.isMineral() || chosenCard.getTitle().equals("The Geologist")){
            String trumpChoice = getTrumpStr();
            superTrumpsGame.playFirstTurn(chosenCard, trumpChoice);
        }
        else {
            superTrumpsGame.playFirstTurn(chosenCard);
        }
//        System.out.println("You've chosen to play : " + cardChoice.getTitle());
    }

    protected static int getValidTrumpChoice() {
        //TODO: Refactor to validate through game.
        Scanner input = new Scanner(System.in);
        System.out.println("What is your trump choice?");
        for (int i = 0; i < validTrumpChoices.length; i++) {
            System.out.println("<" + i + ">" + validTrumpChoices[i]);
        }
        int userChoice = getValidNumInRange(validTrumpChoices.length-1);
        return userChoice;
    }

    protected static int getUserCardChoice(Player playerUp) {
        //TODO: Refactor to validate through game.
        Scanner keyboard = new Scanner(System.in);
            System.out.println("Choose the number of your desired card: ");
            displayCardChoices(playerUp);
            int userChoice = getValidNumInRange(playerUp.getCurrentHand().size());
        return userChoice;
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
                    System.out.println("That is not a valid choice. Please enter number in range.");
                    System.out.printf("Your choice >>> ");
                    userChoice = input.nextLine();
                }
                else{
                    validChoice = true;
                }
            }
            catch (NumberFormatException error){
                System.out.println("That is not a valid choice. Please enter valid number.");
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


    public static String getTrumpStr() {
        int trumpChoiceNum = getValidTrumpChoice();
        String trumpStr = validTrumpChoices[trumpChoiceNum];
        trumpStr = trumpStr.replaceAll("\\s+", "").toLowerCase();
        return trumpStr;
    }
}

