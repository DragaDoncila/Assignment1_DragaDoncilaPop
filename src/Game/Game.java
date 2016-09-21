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

    boolean isWon() {
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

    private int getCountRounds() {
        return countRounds;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void setLastPlayedCard(int cardIndex) {
        this.lastPlayedCard = currentPlayer.getCurrentHand().get(cardIndex);
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
        incrementCountRounds();
    }

    public void playFirstTurn() {
        this.lastPlayedCard = currentPlayer.playFirstCard(0);
        setCurrentCategory(currentPlayer.chooseCategory(lastPlayedCard));

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
        incrementCountRounds();
    }

    public Trump.TrumpCategories getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(String currentCategory) {
        switch (currentCategory){
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
}
