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

/**Class handles the attributes and associated methods required for the playing of a Mineral Supertrumps game.
 * Created by Draga on 6/09/2016.
 */
class Game {

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




    int incrementCountRounds() {
        ++this.countRounds;
        return countRounds;
    }


    Game(int numPlayers, String userName) {
        superTrumpsDeck = DeckBuilder.buildDeck();
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        this.user = new HumanPlayer(0, userName);
        players[0] = this.user;
        for (int i=1; i < players.length; ++i){
            players[i] = new AIPlayer(i);
        }
        countRounds = 0;
        this.lastPlayedCard = superTrumpsDeck.getCard(0);

    }

    String selectDealer() {
        Random rand = new Random();
        int dealerID = rand.nextInt(numPlayers);
        this.dealer = players[dealerID];
        this.currentPlayer = players[dealerID];
        return dealer.getName();
    }

    void dealInitialHands() {
        superTrumpsDeck.shuffle();
        ArrayList<Card> newHand;
        for (Player player:
             players) {
             newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
             player.setCurrentHand(newHand);

        }
//        for (Cards.Card card:
//             newHand) {
//            System.out.println(card.getTitle());
//        }
    }

    Player[] getPlayers() {
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

    boolean checkIfWon() {
        boolean isWon = false;
        for (Player player:
             players) {
            if(player.getCurrentHand().size() == 0){
                isWon = true;
            }
        }
        return isWon;
    }

    boolean isPlayable(Card userChoice){
        boolean isPlayable = false;
        String userCardTitle = userChoice.getTitle();
        for (Card card :
                currentPlayer.getPlayableCards()) {
            if (card.getTitle().equals(userCardTitle)){
                isPlayable = true;
            }
        }
        return isPlayable;
    }

//    Card playTurn() {
//        currentPlayer.setPlayableCards(this.getCountRounds(), this.getLastPlayedCard(), this.currentCategory);
//
//        currentPlayer.chooseCardToPlay();
//    }

    private int getCountRounds() {
        return countRounds;
    }

    private Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    boolean userIsUp(){
        if (currentPlayer.getType() == Player.PlayerTypes.USER){
            return true;
        }
        else {
            return false;
        }
    }

    public String selectDealer(Player allPlayer) {
        this.dealer = allPlayer;
        return allPlayer.getName();
    }

    public void playFirstTurn(Card cardChoice, String trumpChoiceStr) {
        this.lastPlayedCard = cardChoice;
        this.setCurrentCategory(trumpChoiceStr);
        incrementCountRounds();
    }

    public void setCurrentCategory(String currentCategory) {
        switch (currentCategory){
            case "Cleavage":
                this.currentCategory = Trump.TrumpCategories.CLEAVAGE;
                break;
            case "Crustal Abundance":
                this.currentCategory = Trump.TrumpCategories.CRUSTAL_ABUNDANCE;
                break;
            case "Economic Value":
                this.currentCategory = Trump.TrumpCategories.ECONOMIC_VALUE;
                break;
            case "Hardness":
                this.currentCategory = Trump.TrumpCategories.HARDNESS;
                break;
            case "Specific Gravity":
                this.currentCategory = Trump.TrumpCategories.SPECIFIC_GRAVITY;
                break;
        }
    }

    public void playFirstTurn() {

    }
}
