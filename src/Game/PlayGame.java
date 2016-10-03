package Game;

import Cards.Card;
import Players.Player;
import Trumps.Trump;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handles input/output for playing a game of Mineral Supertrumps in command line
 * Created by Draga on 6/09/2016.
 */
public class PlayGame {

    private final static String MAIN_MENU = "Main Menu\n" +
            "(P)lay game\n" +
            "(I)nstructions\n" +
            "(Q)uit\n" +
            "   >>> ";
    private final static String TURN_MENU = "Turn Menu\n" +
            "(V)iew hand details\n" +
            "(S)elect card\n" +
            "(C)ombo\n" +
            "(P)ass";

    private final static String[] validTrumpStrings = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};
    private final static int BACK_VALUE = -1;
    private static final String BACK_STRING = "";

    public static void main(String[] args) {
        System.out.println("Welcome to Mineral Supertrumps!");
        Scanner input = new Scanner(System.in);
        String userName = getNonBlankUserName();

        System.out.println();
        System.out.printf(MAIN_MENU);
        String userChoice = input.nextLine().toUpperCase();
        while (!userChoice.equals("Q")) {
            switch (userChoice) {
                case "I":
                    printInstructions();
                    break;
                case "P":
                    Game superTrumpsGame = startNewGame(userName);
                    System.out.println("Ready to play, " + userName + "?");
                    waitForUser();

                    boolean isNewRound;
                    while (!superTrumpsGame.isOver()) {
                        Player playerUp = superTrumpsGame.getCurrentPlayer();
                        ArrayList<Player> gameWinners = superTrumpsGame.getWinners();
                        //if the current player has already won they are skipped
                        if (gameWinners.contains(playerUp)) {
                            superTrumpsGame.skipPlayer();
                        } else {
                            //If a supertrump was played everybody is back in
                            checkForSuperTrump(superTrumpsGame);
                            isNewRound = checkForNewRound(superTrumpsGame);
                            if (superTrumpsGame.userIsUp()) {
                                //Human player functionality
                                System.out.println("Let's go! It's your turn\n");
                                while (playerUp.isUser()) {
                                    //if the player hadn't previously passed
                                    if (!playerUp.isOut()) {
                                        if (!isNewRound) {
                                            playerUp.setPlayableCards(superTrumpsGame.getLastPlayedCard(), superTrumpsGame.getCurrentCategory());
                                        }
                                        if (playerUp.hasPlayableCards() || isNewRound) {
                                            userChoice = getUserTurnChoice();
                                            switch (userChoice) {
                                                case "V":
                                                    viewCardDetails(playerUp, superTrumpsGame);
                                                    break;
                                                case "P":
                                                    superTrumpsGame.pass();
                                                    System.out.println(playerUp.getName() + " passed. They're out for the round.");
                                                    waitForUser();
                                                    break;
                                                case "C":
                                                    if (playerUp.hasCombo()) {
                                                        if (!superTrumpsGame.isFirstTurn()) {
                                                            playerUp = superTrumpsGame.playCombo();
                                                            System.out.println("COMBO PLAYED! " + playerUp.getName() + " plays again!");
                                                            isNewRound = checkForNewRound(superTrumpsGame);
                                                        } else {
                                                            System.out.println("\nCan't do that on the first turn.");
                                                        }
                                                    } else {
                                                        System.out.println("\nYou do not hold the combo in your hand.");
                                                        waitForUser();
                                                    }
                                                    break;
                                                case "S":
                                                    //If it's a new round or the first turn
                                                    if (isNewRound || superTrumpsGame.isFirstTurn()) {
                                                        userPlayFirstTurn(superTrumpsGame, playerUp);
                                                    } else {
                                                        userPlayTurn(superTrumpsGame, playerUp);
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("\nInvalid choice.\n");
                                            }
                                            //player has no playable cards
                                        } else {
                                            System.out.println("No cards in your hand are playable.");
                                            System.out.println("You must pass. You are out for the round.");
                                            waitForUser();
                                            superTrumpsGame.pass();
                                        }
                                        //player had passed for the round
                                    } else {
                                        System.out.println("You are out for the round");
                                        superTrumpsGame.skipPlayer();
                                        waitForUser();
                                    }
                                    playerUp = superTrumpsGame.getCurrentPlayer();
                                }
                                //user is not up
                            } else {
                                //Robot player functionality
                                System.out.println("Let's go! It's " + playerUp.getName() + "'s turn\n");
                                if (!playerUp.isOut()) {
                                    if (superTrumpsGame.isFirstTurn()) {
                                        superTrumpsGame.playFirstTurn(true);
                                        displayTurnResults(superTrumpsGame, playerUp);
                                    } else if (isNewRound) {
                                        superTrumpsGame.playFirstTurn(false);
                                        displayTurnResults(superTrumpsGame, playerUp);
                                        //it's not a new round or the first turn
                                    } else {
                                        playerUp.setPlayableCards(superTrumpsGame.getLastPlayedCard(), superTrumpsGame.getCurrentCategory());
                                        if (playerUp.hasPlayableCards()) {
                                            superTrumpsGame.playTurn();
                                            if (superTrumpsGame.comboWasPlayed()) {
                                                System.out.println("COMBO PLAYED! " + playerUp.getName() + " is going again.");
                                            } else {
                                                displayTurnResults(superTrumpsGame, playerUp);
                                            }
                                            //the player has no playable cards
                                        } else {
                                            superTrumpsGame.pass();
                                            System.out.println(playerUp.getName() + " passed. They're out for the round.");
                                            waitForUser();
                                        }
                                    }
                                    //the player had already passed
                                } else {
                                    System.out.println(playerUp.getName() + " is out for the round. ");
                                    superTrumpsGame.skipPlayer();
                                    waitForUser();
                                }
                            }
                            Player lastToPlay = superTrumpsGame.getLastUserToPlay();
                            //if somebody has won alert the user
                            if (superTrumpsGame.hasWon(lastToPlay)) {
                                System.out.println("That was the last card in " + superTrumpsGame.getLastUserToPlay().getName() + "'s hand!");
                                System.out.println(lastToPlay.getName().toUpperCase() + " WON!\n");
                                waitForUser();
                            }
                        }
                    }
                    //game is over
                    System.out.println("That's the end of the game. Thanks for playing!\n");
                    break;
                default:
                    System.out.println("Invalid menu choice.");
                    break;
            }

            System.out.printf(MAIN_MENU);
            userChoice = input.nextLine().toUpperCase();
        }
        System.out.println("Thank you for playing Mineral Supertrumps. Goodbye " + userName);
    }

    /**
     * Gets an input from the user and reprompts until that input is not a blank string.
     *
     * @return the input
     */
    private static String getNonBlankUserName() {
        Scanner input = new Scanner(System.in);
        System.out.printf("What is your name? >>> ");
        String userName = input.nextLine();
        while (userName.length() < 1 || userName.trim().length() < 1) {
            System.out.println("That is not a valid username. Please enter at least one character. ");
            System.out.printf("What is your name? >>> ");
            userName = input.nextLine();
        }
        return userName;
    }

    /**
     * Gets a number from the user in the range of allowed number of players and doesn't return it until it's valid
     *
     * @return validated number of players
     */
    private static int getValidNumPlayers() {
        Scanner input = new Scanner(System.in);
        boolean isValidNum = false;
        int numPlayers = 0;
        System.out.println("This is a game for " + Game.MIN_PLAYERS + " to " + Game.MAX_PLAYERS + " people.");
        System.out.printf("Please enter the number of players today>>> ");
        String userEntry = input.nextLine();
        while (!isValidNum) {
            try {
                numPlayers = Integer.parseInt(userEntry);
                if (!Game.isValidNumPlayers(numPlayers)) {
                    System.out.println("That is not a valid number of players.");
                    System.out.println("This is a game for " + Game.MIN_PLAYERS + " to " + Game.MAX_PLAYERS + " people.");
                    System.out.printf("Please enter the number of players today>>> ");
                    userEntry = input.nextLine();
                } else {
                    isValidNum = true;
                }

            } catch (NumberFormatException error) {
                System.out.println("That is not a valid number of players.");
                System.out.println("This is a game for " + Game.MIN_PLAYERS + " to " + Game.MAX_PLAYERS + " people.");
                System.out.printf("Please enter the number of players today>>> ");
                userEntry = input.nextLine();
            }
        }
        return numPlayers;
    }

    /**
     * Establishes and prepares a Mineral Supertrumps game with a number of players, a dealer and dealt hands.
     *
     * @param userName the name of the human player
     * @return the prepared game object
     */
    private static Game startNewGame(String userName) {
        int numPlayers = getValidNumPlayers();
        System.out.println("\nSetting up game for " + numPlayers + " players.\n");
        Game superTrumpsGame = new Game(numPlayers, userName);

        System.out.println("Let's welcome the party!");
        waitForUser();
        Player[] allPlayers = superTrumpsGame.getPlayers();
        int i = 1;
        for (Player player :
                allPlayers) {
            System.out.println("Player " + i + ": " + player.getName());
            ++i;
        }

        //Get a dealer
        System.out.println();
        System.out.println("Selecting dealer...");
        waitForUser();
        String dealerName = superTrumpsGame.selectDealer();
        System.out.println(dealerName + " is dealing today.\n");

        //Deal and show the hand
        superTrumpsGame.dealInitialHands();
        ArrayList<Card> userHand = superTrumpsGame.getPlayers()[0].getCurrentHand();
        System.out.println("Dealing hands...");
        waitForUser();
        System.out.println("Your hand:");
        System.out.println("---------------------------");
        for (Card card :
                userHand) {
            System.out.println(card.getTitle());
        }
        System.out.println();

        superTrumpsGame.getNextPlayer();
        return superTrumpsGame;
    }

    /**
     * Get a valid turn choice from the user
     *
     * @return the user's choice
     */
    private static String getUserTurnChoice() {
        String userTurnChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(TURN_MENU);
        System.out.printf("What do you wish to do? >>>");
        userTurnChoice = input.nextLine().toUpperCase();
        return userTurnChoice;
    }

    /**
     * Gets a trump choice from the user and only returns the associated string if the user makes a valid choice
     *
     * @return trump category
     */
    private static int getValidTrumpChoice() {
        System.out.println("What is your trump choice?");
        for (int i = 0; i < validTrumpStrings.length; i++) {
            System.out.println("<" + i + ">" + validTrumpStrings[i]);
        }
        System.out.println("(B)ack");
        return getValidNumInRange(validTrumpStrings.length - 1);
    }

    /**
     * Gets a card choice from the user and only returns it if it's a valid choice
     *
     * @param playerUp the current player
     * @return the chosen card
     */
    private static int getUserCardChoice(Player playerUp) {
        System.out.println("Choose the number of your desired card: ");
        displayCardChoices(playerUp);
        return getValidNumInRange(playerUp.getCurrentHand().size() - 1);
    }

    /**
     * Gets a valid trump choice from the user if they do not wish to go back.
     *
     * @return the trump string or back
     */
    private static String getTrumpStr() {
        int trumpChoiceNum = getValidTrumpChoice();
        if (trumpChoiceNum != BACK_VALUE) {
            String trumpStr = validTrumpStrings[trumpChoiceNum];
            trumpStr = trumpStr.replaceAll("\\s+", "").toLowerCase();
            return trumpStr;
        }
        return BACK_STRING;
    }

    /**
     * Gets a valid card choice and trump category from the user and plays it
     *
     * @param superTrumpsGame the current game
     * @param playerUp        the current player
     */
    static void userPlayFirstTurn(Game superTrumpsGame, Player playerUp) {
        int cardChoice = getUserCardChoice(playerUp);
        if (cardChoice != BACK_VALUE) {
            Card potentialCard = playerUp.getCard(cardChoice);
            while (potentialCard.isTrump() && superTrumpsGame.isFirstTurn()) {
                System.out.println("Cannot play Supertrump on first turn.\n");
                cardChoice = getUserCardChoice(playerUp);
                if (cardChoice != BACK_VALUE) {
                    potentialCard = playerUp.getCard(cardChoice);
                }
                //user wants to go back to main menu
                else {
                    return;
                }
            }
            Card chosenCard = playerUp.getCard(cardChoice);
            System.out.println("You chose " + chosenCard.getTitle() + "\n");
            String trumpChoice = getTrumpStr();
            if (!trumpChoice.equals(BACK_STRING)) {
                chosenCard = playerUp.playCard(cardChoice);
                superTrumpsGame.playFirstTurn(chosenCard, trumpChoice);
                displayTurnResults(superTrumpsGame, playerUp);
            }
            //if user wants to go back, function ends here and returns
        }
    }

    /**
     * Get a valid card choice from the user that is playable on the last played card, get a trump category if required,
     * and play the turn
     *
     * @param superTrumpsGame the current game
     * @param playerUp        the current player
     */
    static void userPlayTurn(Game superTrumpsGame, Player playerUp) {
        int cardChoice = getUserCardChoice(playerUp);
        //We get the card, but do not play it until it is checked as playable
        if (cardChoice != BACK_VALUE) {
            Card chosenCard = playerUp.getCard(cardChoice);
            boolean validCard = false;
            while (!validCard) {
                if (superTrumpsGame.isPlayable(chosenCard)) {
                    //If the card is actually playable, we play it and remove it from the hand
                    chosenCard = playerUp.playCard(cardChoice);
                    System.out.println("You chose " + chosenCard.getTitle() + "\n");
                    if (chosenCard.isGeologist()) {
                        String trumpChoice = getTrumpStr();
                        if (!trumpChoice.equals(BACK_STRING)) {
                            superTrumpsGame.playTurn(chosenCard, trumpChoice);
                            validCard = true;
                        }
                    }
                    //Card chosen is playable and is not the Geologist
                    else {
                        superTrumpsGame.playTurn(chosenCard);
                        validCard = true;
                    }
                    displayTurnResults(superTrumpsGame, playerUp);
                }
                // User has playable cards but has chosen unplayable
                else {
                    System.out.println("That card cannot play on " + superTrumpsGame.getLastPlayedCard().getTitle());
                    cardChoice = getUserCardChoice(playerUp);
                    chosenCard = playerUp.getCard(cardChoice);
                }
            }
        }
    }

    /**
     * Displays the details of the last played card and its relevant trump value
     *
     * @param superTrumpsGame the current game
     * @param playerUp        the current player
     */
    static void displayTurnResults(Game superTrumpsGame, Player playerUp) {
        System.out.println("Turn Complete! Let's see what happened...");
        waitForUser();
        System.out.println(playerUp.getName() + " played " + superTrumpsGame.getLastPlayedCard().getTitle());
        System.out.println("Current trump category is: " + superTrumpsGame.getCurrentCategory());
        System.out.println("Current trump value is: " + superTrumpsGame.getLastPlayedCard().getTrumpVal(superTrumpsGame.getCurrentCategory()) + "\n");

    }

    /**
     * Checks whether it's a new round in the game and displays the relevant messages to the user
     *
     * @param superTrumpsGame the current game
     * @return is new round
     */
    private static boolean checkForNewRound(Game superTrumpsGame) {
        if (superTrumpsGame.isNewRound()) {
            superTrumpsGame.setAllPlayersIn();
            superTrumpsGame.resetNumPasses();
            System.out.println("Starting out new round...");
            if (superTrumpsGame.hasRoundWinner()) {
                System.out.println("Last round won by " + superTrumpsGame.getRoundWinner());
            }
            waitForUser();
            return true;
        } else {

            return false;
        }
    }

    /**
     * Returns true if a supertrump has been played, and sets all the players back into the round
     *
     * @param superTrumpsGame the current game
     */
    private static void checkForSuperTrump(Game superTrumpsGame) {
        if (superTrumpsGame.getLastPlayedCard() != null && superTrumpsGame.getLastPlayedCard().isTrump()) {
            superTrumpsGame.setAllPlayersIn();
            superTrumpsGame.resetNumPasses();
            System.out.println("Supertrump was played, all players back in.");
            waitForUser();
        }
    }

    /**
     * Displays the card titles in the user's current hand with an associated index
     *
     * @param playerUp the current player
     */
    private static void displayCardChoices(Player playerUp) {
        int i = 0;
        for (Card card :
                playerUp.getCurrentHand()) {
            System.out.println("<" + i + ">" + card.getTitle());
            ++i;
        }
        System.out.println("(B)ack");
    }

    /**
     * Gets a card choice from the user and, if valid, displays the details of that card.
     *
     * @param playerUp        the current player
     * @param superTrumpsGame the current game
     */
    private static void viewCardDetails(Player playerUp, Game superTrumpsGame) {
        System.out.println();
        int cardChoice = getUserCardChoice(playerUp);
        //if the user hasn't chosen to return to menu
        if (cardChoice != BACK_VALUE) {
            Card card = playerUp.getCard(cardChoice);
            System.out.println(card);

            //display details of the last played card so user can compare
            Trump.TrumpCategories currentCategory = superTrumpsGame.getCurrentCategory();
            System.out.println(String.format("%-30s", "Current Category: ") + currentCategory);
            if (superTrumpsGame.getLastPlayedCard() != null) {
                System.out.println(String.format("%-30s", "Last Played Value: ") + superTrumpsGame.getLastPlayedCard().getTrumpVal(currentCategory));
            }
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Displays the instructions step by step, waiting for the user after each point
     */
    private static void printInstructions() {
        System.out.println(Game.INSTRUCTIONS1);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS2);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS3);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS4);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS5);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS6);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS7);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS8);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS9);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS10);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS11);
        waitForUser();
        System.out.println(Game.INSTRUCTIONS12);
        waitForUser();

    }

    /**
     * Waits for the user to press enter before the program continues
     */
    private static void waitForUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("###########################");
        System.out.println("Enter to continue >>>");
        input.nextLine();
    }

    /**
     * Gets a number from the user and only returns it if it's within the specified range
     *
     * @param max the maximum boundary
     * @return the number
     */
    private static int getValidNumInRange(int max) {
        boolean validChoice = false;
        Scanner input = new Scanner(System.in);
        System.out.printf("Your choice >>> ");
        String userChoice = input.nextLine().toUpperCase();
        int num = BACK_VALUE;
        while (!validChoice) {
            try {
                num = Integer.parseInt(userChoice);
                if (num < 0 || num > max) {
                    System.out.println("That is not a valid choice. Please enter number in range.");
                    System.out.printf("Your choice >>> ");
                    userChoice = input.nextLine().toUpperCase();
                } else {
                    validChoice = true;
                }
            } catch (NumberFormatException error) {
                if (userChoice.equals("B")) {
                    return BACK_VALUE;
                } else {
                    System.out.println("That is not a valid choice. Please enter valid number or B to return to turn menu.");
                    System.out.printf("Your choice >>> ");
                    userChoice = input.nextLine().toUpperCase();
                }
            }
        }
        return num;
    }

}

