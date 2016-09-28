package Game;

import Cards.Card;
import Deck.Deck;
import Deck.DeckBuilder;
import Players.AIPlayer;
import Players.HumanPlayer;
import Players.Player;
import Trumps.Trump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
public class Game {
    final static String INSTRUCTIONS1 =
            "1. This is a game for 3-5 people. The game begins with 8 cards being randomly \n" +
                    "dealt to each player by a randomly assigned dealer.\n";
    final static String INSTRUCTIONS2 =
            "2. The player to the left of the dealer goes first by placing a card on the table. If the card is a \n" +
                    "mineral card, the player must choose the trump category (Hardness, Economic Value, Cleavage,\n" +
                    " Crustal Abundance or Specific Gravity) they wish to play. The card will then be played, and its \n" +
                    "relevant trump value displayed.\n";
    final static String INSTRUCTIONS3 =
            "3. The next player to the left takes the next turn. This player must play a mineral card that has a \n" +
                    "higher value in the trump category than the card played by the previous player. The game continues with\n" +
                    " the next player to the left, and so on.\n";
    final static String INSTRUCTIONS4 =
            "4. If a player does not have any mineral cards that are of higher value for the specific trump category\n" +
                    " being played, then the player must pass and picks up one card from the remaining deck. The player then\n" +
                    " cannot play again until all but one player has passed, or until another player throws a supertrump \n" +
                    "card to change the trump category, as described next. A player is allowed to pass even if they still \n" +
                    "hold cards that could be played.\n";
    final static String INSTRUCTIONS5 =
            "5. If the player has a supertrump card (The Miner, The Geologist, The Geophysicist, The Petrologist, \n" +
                    "The Mineralogist, The Gemologist), then they may play this card at any of their turns. By placing a \n" +
                    "supertrump card, the player changes the trump category according to the instructions on the supertrump \n" +
                    "card. At this stage, any other player who had passed on the previous round is now able to play again. \n" +
                    "If a player happens to hold both The Geophysicist card and the Magnetite card in their hand, then that \n" +
                    "player can place both cards together to win the round.\n";
    final static String INSTRUCTIONS6 =
            "6. The game continues with players taking turns to play cards until all but one player has passed. The \n" +
                    "last player then gets to lead out the next round and chooses the trump category to be played.\n";
    final static String INSTRUCTIONS7 =
            "7. The winner of the game is the first player to lose all of their cards. The game continues until all \n" +
                    "but one player has lost their cards.\n";
    final static String INSTRUCTIONS8 =
            "8. Trump Sequences:\n" +
                    "\tHardness: relates to Moh’s hardness scale of minerals from 1 to 10. Where a range of values is \n" +
                    "presented, the highest value should be used. \n";
    final static String INSTRUCTIONS9 =
            "Specific Gravity: in grams per cubic centimeter. Where a range of values is presented, the highest \n" +
                    "value should be used. \n";
    final static String INSTRUCTIONS10 =
            "Cleavage: refers to the number of cleavage planes and how well the planes are typically expressed in \n" +
                    "the crystal. For example, “1 perfect, 2 poor” means the mineral has 1 perfect cleavage plane, and 2 \n" +
                    "poor cleavage planes. \n" +
                    "The order of ranking from lowest to highest is: \n" +
                    "[none - poor/none - 1 poor  - 2 poor  - 1 good  - 1 good, 1 poor - 2 good - 3 good - 1 perfect - \n" +
                    "1 perfect, 1 good - 1 perfect, 2 good - 2 perfect, 1 good - 3 perfect - 4 perfect - 6 perfect]\n";
    final static String INSTRUCTIONS11 =
            "Crustal Abundance is ranked from lowest to highest as: \n" +
                    "[ultratrace - trace - low - moderate - high - very high]\n";
    final static String INSTRUCTIONS12 =
            "Economic value: is ranked from lowest to highest as: \n" +
                    "[trivial - low - moderate - high - very high - I’m rich!]\n";
    public final static int MIN_PLAYERS = 3;
    public final static int MAX_PLAYERS = 5;

    private final int CARDS_TO_A_HAND = 8;
    private int numPlayers;
    private Player[] players;
    private Player dealer;
    private HumanPlayer user;
    private Player currentPlayer;
    private Deck superTrumpsDeck;
    private Trump.TrumpCategories currentCategory;
    private Card lastPlayedCard;
    private int countRounds;
    private int numPasses;
    private boolean isNewRound;
    private boolean hasUserPlayed;
    private String roundWinner;


//    void incrementCountRounds() {
//        if (this.numPasses == this.numPlayers - 1){
//            ++this.countRounds;
//            resetNumPasses();
//        }
//    }

    public int getNumPasses() {
        return numPasses;
    }

    public void incrementNumPasses() {
        ++this.numPasses;
    }

    public boolean isNewRound() {
        if (this.numPasses == this.numPlayers - 1) {
            ++this.countRounds;
            isNewRound = true;
        } else {
            isNewRound = false;
        }
        return isNewRound;
    }


    public boolean isFirstTurn() {
        if (this.lastPlayedCard == null) {
            return true;
        } else {
            return false;
        }
    }

    public Game(int numPlayers, String userName) {
        superTrumpsDeck = DeckBuilder.buildDeck();
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        this.user = new HumanPlayer(0, userName);
        players[0] = this.user;
        for (int i = 1; i < players.length; ++i) {
            players[i] = new AIPlayer(i);
        }
        countRounds = 0;
        numPasses = 0;

    }

    String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        this.currentPlayer = getNextPlayer();
        return dealer.getName();
    }

    void dealInitialHands() {
        superTrumpsDeck.shuffle();
        ArrayList<Card> newHand;
        for (Player player :
                players) {
            newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
            player.setCurrentHand(newHand);

        }
//        for (Cards.Card card:
//             newHand) {
//            System.out.println(card.getTitle());
//        }
    }

    public Player[] getPlayers() {
        return players;
    }

    Player getNextPlayer() {
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

    boolean isWon() {
        boolean isWon = false;
        for (Player player :
                players) {
            if (player.getCurrentHand().size() == 0) {
                isWon = true;
            }
        }
        return isWon;
    }

    boolean isPlayable(Card userChoice) {
        boolean isPlayable = false;
        String userCardTitle = userChoice.getTitle();
        for (Card card :
                currentPlayer.getPlayableCards()) {
            if (card.getTitle().equals(userCardTitle)) {
                isPlayable = true;
            }
        }
        return isPlayable;
    }

    private int getCountRounds() {
        return countRounds;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

//    public void setLastPlayedCard(int cardIndex) {
//        this.lastPlayedCard = currentPlayer.getCurrentHand().get(cardIndex);
//    }


    boolean userIsUp() {
        if (currentPlayer.getType() == Player.PlayerTypes.USER) {
            return true;
        } else {
            return false;
        }
    }

    public String selectDealer(Player allPlayer) {
        this.dealer = allPlayer;
        return allPlayer.getName();
    }

    public void playFirstTurn(Card cardChoice, String trumpChoiceStr) {
//Used in case of mineral card OR The Geologist Trump
//        setLastPlayedCard(cardChoice);
        /*Need to:
        * set last played card to the chosen card
        * set current trump category to the chosen category
        * increment rounds
        * increment current player
        * return current trump value? or just get it in play game..*/
        this.lastPlayedCard = cardChoice;
        setCurrentCategory(trumpChoiceStr);
        currentPlayer = getNextPlayer();
    }

    public void playFirstTurn() {
//        AI Play First Turn
        this.lastPlayedCard = currentPlayer.playFirstCard(0);
        setCurrentCategory(currentPlayer.chooseCategory());
        currentPlayer = getNextPlayer();

    }

    public void playFirstTurn(Card chosenCard) {
        //Trump play first turn functionality
        /*Need to:
        * set last played card to the chosen card
        * set current trump category based on card effect
        * increment rounds
        * increment current player
        * return current trump cat? or just get it in play game..*/
        this.lastPlayedCard = chosenCard;
        setCurrentCategory(lastPlayedCard.getInfo());
        hasUserPlayed = true;
        currentPlayer = getNextPlayer();
    }

    public Trump.TrumpCategories getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(String currentCategory) {
        switch (currentCategory) {
            case "cleavage":
                this.currentCategory = Trump.TrumpCategories.CLEAVAGE;
                break;
            case "crustalabundance":
                this.currentCategory = Trump.TrumpCategories.CRUSTAL_ABUNDANCE;
                break;
            case "economicvalue":
                this.currentCategory = Trump.TrumpCategories.ECONOMIC_VALUE;
                break;
            case "hardness":
                this.currentCategory = Trump.TrumpCategories.HARDNESS;
                break;
            case "specificgravity":
                this.currentCategory = Trump.TrumpCategories.SPECIFIC_GRAVITY;
                break;
        }
    }

    public void playTurn() {
//        For AI only
        this.lastPlayedCard = currentPlayer.playCard(0);
        if (lastPlayedCard.isGeologist()) {
            setCurrentCategory(currentPlayer.chooseCategory());
        } else if (lastPlayedCard.isTrump()) {
            setCurrentCategory(lastPlayedCard.getInfo());
        }
        currentPlayer = getNextPlayer();
    }

    public void playTurn(Card chosenCard) {
        //    For all but Geologist cards.
        this.lastPlayedCard = chosenCard;
        //If Supertrump card was chosen, category is reset
        if (chosenCard.isTrump()) {
            setCurrentCategory(lastPlayedCard.getInfo());
        }
        currentPlayer = getNextPlayer();

    }

    public void playTurn(Card chosenCard, String trumpStr) {
//        For Geologist playing
        this.lastPlayedCard = chosenCard;
        setCurrentCategory(trumpStr);
        currentPlayer = getNextPlayer();
    }

    public boolean playableCardChosen(Card chosenCard) {
        return chosenCard.canPlayOn(lastPlayedCard, currentCategory);
    }


    public void pass() {
        int numCardsLeft = superTrumpsDeck.getCards().size();
        if (numCardsLeft != 0) {
            Card drawnCard = superTrumpsDeck.drawCard();
            currentPlayer.addCard(drawnCard);
        }
        currentPlayer.setIsOut(true);
        incrementNumPasses();
        currentPlayer = getNextPlayer();
        if (isNewRound()) {
            roundWinner = currentPlayer.getName();
        }
    }

    public void setAllPlayersIn() {
        for (Player player :
                players) {
            player.setIsOut(false);
        }
    }

    public void resetNumPasses() {
        this.numPasses = 0;
        this.isNewRound = false;
    }

    public Player playCombo() {
        this.lastPlayedCard = currentPlayer.playCombo();
        ++this.countRounds;
        isNewRound = true;
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean hasUserPlayed() {
        return this.hasUserPlayed;
    }

    public boolean hasRoundWinner() {
        if (roundWinner != null) {
            return true;
        } else {
            return false;
        }
    }

    public void resetUserPlayed() {
        this.hasUserPlayed = false;
    }

    public static boolean isValidNumPlayers(int numPlayers) {
        return (numPlayers >= MIN_PLAYERS && numPlayers <= MAX_PLAYERS);
    }

    public String getRoundWinner() {
        return roundWinner;
    }

    public void skipPlayer(Player playerUp) {
        currentPlayer = getNextPlayer();
    }

    public void setRoundWinner(Player roundWinner) {
        this.roundWinner = roundWinner.getName();
    }
}
