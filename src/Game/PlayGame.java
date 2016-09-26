package Game;

import Cards.Card;
import Players.Player;
import Trumps.Trump;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handles playing a MineralSupertrump game from start to finish
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
    private final static int MIN_PLAYERS = 3;
    private final static int MAX_PLAYERS = 5;
    private final static String[] validTrumpChoices = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};


    public static void main(String[] args) {
        System.out.println("Welcome to Mineral Supertrumps!");
        Scanner input = new Scanner(System.in);
        System.out.printf("What is your name? >>> ");
//        TODO: error check non blank username
        String userName = input.nextLine();
        System.out.println();
        System.out.printf(MAIN_MENU);
        String userChoice = input.nextLine().toUpperCase();
        while (!userChoice.equals("Q")) {
            if (userChoice.equals("I")) {
                System.out.println("Instructions");
            } else {
                Game superTrumpsGame = startnewgame(userName);
                System.out.println("Ready to play, " + userName + "?");
                waitForUser();
                Player playerUp = superTrumpsGame.getNextPlayer();
                while (!superTrumpsGame.isWon()) {
                    System.out.println("Let's go! It's " + playerUp.getName() + "'s turn\n");
                    while (superTrumpsGame.userIsUp()) {
                        //WHAT DO BEFORE CARD HAS BEEN PLAYED?!?
                        playerUp.setPlayableCards(superTrumpsGame.getLastPlayedCard(), superTrumpsGame.getCurrentCategory());
                        if (playerUp.hasPlayableCards()) {
                            userChoice = getUserTurnChoice();
                            switch (userChoice) {
                                case "V":
                                    viewHandDetails(playerUp.getCurrentHand(), superTrumpsGame);
                                    break;
                                case "P":
                                    superTrumpsGame.pass();
                                    System.out.println("Player passed.");
                                    waitForUser();
                                    playerUp = superTrumpsGame.getNextPlayer();
                                    break;
                                case "C":
                                    if (playerUp.hasCombo()) {
                                        superTrumpsGame.playCombo();
                                    } else {
                                        System.out.println("\nYou do not hold the combo in your hand.");
                                        waitForUser();
                                    }
                                    break;
                                case "S":
                                    if (superTrumpsGame.isNewRound()) {
                                        userPlayFirstTurn(superTrumpsGame, playerUp);

                                    } else {
                                        userPlayTurn(superTrumpsGame, playerUp);
                                    }
                                    displayTurnResults(superTrumpsGame, playerUp);
                                    playerUp = superTrumpsGame.getNextPlayer();
                                    break;
                                default:
                                    System.out.println("\nInvalid choice.\n");
                            }
                        } else {
                            System.out.println("No cards in your hand can play on " + superTrumpsGame.getLastPlayedCard().getTitle() + ".");
                            System.out.println("You must pass.");
                            waitForUser();
                            superTrumpsGame.pass();
                            playerUp = superTrumpsGame.getNextPlayer();
                        }
                    }
                    //                    ROBOTS PLAY BELOW
                    if (superTrumpsGame.isNewRound()) {
                        superTrumpsGame.playFirstTurn();
                        displayTurnResults(superTrumpsGame, playerUp);
                    } else {
                        playerUp.setPlayableCards(superTrumpsGame.getLastPlayedCard(), superTrumpsGame.getCurrentCategory());
                        if (playerUp.hasPlayableCards()) {
                            superTrumpsGame.playTurn();
                            displayTurnResults(superTrumpsGame, playerUp);
                        } else {
                            superTrumpsGame.pass();
                            System.out.println("Player passed.");
                            waitForUser();
                        }
                    }
                    playerUp = superTrumpsGame.getNextPlayer();
                }
                System.out.println("YOU WON!");
            }

        }
        System.out.printf(MAIN_MENU);
        userChoice = input.nextLine().toUpperCase();
    }

    private static void waitForUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("###########################");
        System.out.println("Enter to continue >>>");
        input.nextLine();
    }

    private static void viewHandDetails(ArrayList<Card> currentHand, Game superTrumpsGame) {
        System.out.println();
        for (Card card :
                currentHand) {
            System.out.println(card);
            Trump.TrumpCategories currentCategory = superTrumpsGame.getCurrentCategory();
            System.out.println(String.format("%-30s", "Current Category: ") + currentCategory);
            if (superTrumpsGame.getLastPlayedCard() != null) {
                System.out.println(String.format("%-30s", "Last Played Value: ") + superTrumpsGame.getLastPlayedCard().getTrumpVal(currentCategory));
            }
            System.out.println("------------------------------------------");
            waitForUser();
        }
    }

    static void userPlayTurn(Game superTrumpsGame, Player playerUp) {
        int cardChoice = getUserCardChoice(playerUp);
//            We get the card, but do not play it until it is checked as playable
        Card chosenCard = playerUp.getCard(cardChoice);
        boolean validCard = false;
        while (!validCard) {
            if (superTrumpsGame.isPlayable(chosenCard)) {
//                    If the card is actually playable, we play it and remove it from the hand
                chosenCard = playerUp.playCard(cardChoice);
                if (chosenCard.isGeologist()) {
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

    static void displayTurnResults(Game superTrumpsGame, Player playerUp) {
        System.out.println("Turn Complete! Let's see what happened...");
        waitForUser();
        System.out.println(playerUp.getName() + " played " + superTrumpsGame.getLastPlayedCard().getTitle());
        System.out.println("Current trump category is: " + superTrumpsGame.getCurrentCategory() + "\n");
        System.out.println("Current trump value is: " + superTrumpsGame.getLastPlayedCard().getTrumpVal(superTrumpsGame.getCurrentCategory()));


    }

    static void userPlayFirstTurn(Game superTrumpsGame, Player playerUp) {
        //TODO: Add functionality for user choosing a supertrumps card.
        int cardChoice = getUserCardChoice(playerUp);
        Card chosenCard = playerUp.playFirstCard(cardChoice);
        if (chosenCard.isMineral() || chosenCard.getTitle().equals("The Geologist")) {
            String trumpChoice = getTrumpStr();
            superTrumpsGame.playFirstTurn(chosenCard, trumpChoice);
        } else {
            superTrumpsGame.playFirstTurn(chosenCard);
        }
//        System.out.println("You've chosen to play : " + cardChoice.getTitle());
    }

    private static int getValidTrumpChoice() {
        //TODO: Refactor to validate through game.
        System.out.println("What is your trump choice?");
        for (int i = 0; i < validTrumpChoices.length; i++) {
            System.out.println("<" + i + ">" + validTrumpChoices[i]);
        }
        return getValidNumInRange(validTrumpChoices.length - 1);
    }

    private static int getUserCardChoice(Player playerUp) {
        //TODO: Refactor to validate through game.
        System.out.println("Choose the number of your desired card: ");
        displayCardChoices(playerUp);
        return getValidNumInRange(playerUp.getCurrentHand().size());
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
                if (num < 0 || num > max) {
                    System.out.println("That is not a valid choice. Please enter number in range.");
                    System.out.printf("Your choice >>> ");
                    userChoice = input.nextLine();
                } else {
                    validChoice = true;
                }
            } catch (NumberFormatException error) {
                System.out.println("That is not a valid choice. Please enter valid number.");
                System.out.printf("Your choice >>> ");
                userChoice = input.nextLine();
            }
        }
        return num;
    }

    private static void displayCardChoices(Player playerUp) {
        int i = 0;
        for (Card card :
                playerUp.getCurrentHand()) {
            System.out.println("<" + i + ">" + card.getTitle());
            ++i;
        }
    }

    private static Game startnewgame(String userName) {
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
        System.out.println();
        System.out.println("Selecting dealer...");
        waitForUser();
        String dealerName = superTrumpsGame.selectDealer();
        System.out.println(dealerName + " is dealing today.\n");
        superTrumpsGame.dealInitialHands();
        ArrayList<Card> userHand = superTrumpsGame.getPlayers()[0].getCurrentHand();
        System.out.println("Dealing hands...");
        waitForUser();
//      TODO: Move this to a method of deck or hand.
        System.out.println("Your hand:");
        System.out.println("---------------------------");
        for (Card card :
                userHand) {
            System.out.println(card.getTitle());
        }
        System.out.println();
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


    private static String getTrumpStr() {
        int trumpChoiceNum = getValidTrumpChoice();
        String trumpStr = validTrumpChoices[trumpChoiceNum];
        trumpStr = trumpStr.replaceAll("\\s+", "").toLowerCase();
        return trumpStr;
    }

    private static String getUserTurnChoice() {
        String userTurnChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(TURN_MENU);
        System.out.printf("What do you wish to do? >>>");
        userTurnChoice = input.nextLine().toUpperCase();
        return userTurnChoice;
    }
}

