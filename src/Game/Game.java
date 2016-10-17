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
 * Class handles the attributes and associated methods & logic required for the playing of a Mineral
 * Supertrumps game. Created by Draga on 6/09/2016.
 */
public class Game {
  static final String INSTRUCTIONS1 =
      "1. This is a game for 3-5 people. The game begins with 8 cards being randomly \n"
          + "dealt to each player by a randomly assigned dealer.\n";
  static final String INSTRUCTIONS2 =
      "2. The player to the left of the dealer goes first by placing a card on the table. If the card is a \n"
          + "mineral card, the player must choose the trump category (Hardness, Economic Value, Cleavage,\n"
          + " Crustal Abundance or Specific Gravity) they wish to play. The card will then be played, and its \n"
          + "relevant trump value displayed.\n";
  static final String INSTRUCTIONS3 =
      "3. The next player to the left takes the next turn. This player must play a mineral card that has a \n"
          + "higher value in the trump category than the card played by the previous player. The game continues with\n"
          + " the next player to the left, and so on.\n";
  static final String INSTRUCTIONS4 =
      "4. If a player does not have any mineral cards that are of higher value for the specific trump category\n"
          + " being played, then the player must pass and picks up one card from the remaining deck. The player then\n"
          + " cannot play again until all but one player has passed, or until another player throws a supertrump \n"
          + "card to change the trump category, as described next. A player is allowed to pass even if they still \n"
          + "hold cards that could be played.\n";
  static final String INSTRUCTIONS5 =
      "5. If the player has a supertrump card (The Miner, The Geologist, The Geophysicist, The Petrologist, \n"
          + "The Mineralogist, The Gemologist), then they may play this card at any of their turns. By placing a \n"
          + "supertrump card, the player changes the trump category according to the instructions on the supertrump \n"
          + "card. At this stage, any other player who had passed on the previous round is now able to play again. \n"
          + "If a player happens to hold both The Geophysicist card and the Magnetite card in their hand, then that \n"
          + "player can place both cards together to win the round.\n";
  static final String INSTRUCTIONS6 =
      "6. The game continues with players taking turns to play cards until all but one player has passed. The \n"
          + "last player then gets to lead out the next round and chooses the trump category to be played.\n";
  static final String INSTRUCTIONS7 =
      "7. The winner of the game is the first player to lose all of their cards. The game continues until all \n"
          + "but one player has lost their cards.\n";
  static final String INSTRUCTIONS8 =
      "8. Trump Sequences:\n"
          + "\tHardness: relates to Moh’s hardness scale of minerals from 1 to 10. Where a range of values is \n"
          + "presented, the highest value should be used. \n";
  static final String INSTRUCTIONS9 =
      "Specific Gravity: in grams per cubic centimeter. Where a range of values is presented, the highest \n"
          + "value should be used. \n";
  static final String INSTRUCTIONS10 =
      "Cleavage: refers to the number of cleavage planes and how well the planes are typically expressed in \n"
          + "the crystal. For example, “1 perfect, 2 poor” means the mineral has 1 perfect cleavage plane, and 2 \n"
          + "poor cleavage planes. \n"
          + "The order of ranking from lowest to highest is: \n"
          + "[none - poor/none - 1 poor  - 2 poor  - 1 good  - 1 good, 1 poor - 2 good - 3 good - 1 perfect - \n"
          + "1 perfect, 1 good - 1 perfect, 2 good - 2 perfect, 1 good - 3 perfect - 4 perfect - 6 perfect]\n";
  static final String INSTRUCTIONS11 =
      "Crustal Abundance is ranked from lowest to highest as: \n"
          + "[ultratrace - trace - low - moderate - high - very high]\n";
  static final String INSTRUCTIONS12 =
      "Economic value: is ranked from lowest to highest as: \n"
          + "[trivial - low - moderate - high - very high - I’m rich!]\n";
  static final int MIN_PLAYERS = 3;
  static final int MAX_PLAYERS = 5;

  static Game currentGame;

  private int numPlayers;
  private Player[] players;
  private Player dealer;
  private Player currentPlayer;

  private Deck superTrumpsDeck;
  private Trump.TrumpCategories currentCategory;
  private Card lastPlayedCard;

  private int numPasses;
  private String roundWinner;
  private ArrayList<Player> winners;

  private Player lastUserToPlay;
  private boolean comboWasPlayed;

  /**
   * Builds a Game object with the passed number of players and username. Initializes player array
   * and relevant fields
   *
   * @param numPlayers the number of players in the game
   * @param userName the name of the human player
   */
  public Game(int numPlayers, String userName) {
    currentGame = this;
    superTrumpsDeck = DeckBuilder.buildDeckFromPlist();
    this.numPlayers = numPlayers;

    players = new Player[numPlayers];
    HumanPlayer user = new HumanPlayer(0, userName);
    players[0] = user;
    for (int i = 1; i < players.length; ++i) {
      players[i] = new AIPlayer(i);
    }

    winners = new ArrayList<>();
    numPasses = 0;
  }

  public Player[] getPlayers() {
    return players;
  }

  Player getLastUserToPlay() {
    return lastUserToPlay;
  }

  Card getLastPlayedCard() {
    return lastPlayedCard;
  }

  Trump.TrumpCategories getCurrentCategory() {
    return currentCategory;
  }

  Player getCurrentPlayer() {
    return currentPlayer;
  }

  String getRoundWinner() {
    return roundWinner;
  }

  ArrayList<Player> getWinners() {
    return winners;
  }

  private void incrementNumPasses() {
    ++this.numPasses;
  }

  /**
   * Returns which player is currrently up for a turn
   *
   * @return player who is up next
   */
  Player getNextPlayer() {
    int currentPlayerID = Arrays.asList(players).indexOf(currentPlayer);
    //if the id of the current player is last in the list
    if (currentPlayerID == players.length - 1) {
      //start at the beginning again
      currentPlayer = players[0];
    }
    //otherwise, next player is next index in list
    else {
      currentPlayer = players[currentPlayerID + 1];
    }
    return currentPlayer;
  }

  boolean userIsUp() {
    return currentPlayer.getType() == Player.PlayerTypes.USER;
  }

  boolean hasRoundWinner() {
    return roundWinner != null;
  }

  boolean comboWasPlayed() {
    return comboWasPlayed;
  }

  public static boolean isValidNumPlayers(int numPlayers) {
    return (numPlayers >= MIN_PLAYERS && numPlayers <= MAX_PLAYERS);
  }

  /**
   * Returns true if no card has been played (and therefore it's the beginning of the game),
   * otherwise false.
   *
   * @return boolean of comparison
   */
  boolean isFirstTurn() {
    return this.lastPlayedCard == null;
  }

  /**
   * Returns true if all but one person currently playing have skipped or the combo was played,
   * otherwise false
   *
   * @return isNewRound boolean of comparison
   */
  boolean isNewRound() {
    boolean isNewRound;
    //if the number of people who have passed (excepting winners) is one less than the number of players
    if (this.numPasses == (this.numPlayers - 1 - winners.size()) || comboWasPlayed) {
      isNewRound = true;
      comboWasPlayed = false;
    } else {
      isNewRound = false;
    }
    return isNewRound;
  }

  /**
   * Returns true if any player has won the game, otherwise false
   *
   * @return isWon boolean of comparison
   */
  boolean isWon() {
    boolean isWon = false;
    for (Player player : players) {
      //if any player has no cards left
      if (player.getCurrentHand().size() == 0) {
        isWon = true;
      }
    }
    return isWon;
  }

  /**
   * Returns true if the card a user has chosen is a playable card, otherwise false
   *
   * @param userChoice the card choice of the user
   * @return isPlayable true if the card can be played
   */
  boolean isPlayable(Card userChoice) {
    boolean isPlayable = false;
    String userCardTitle = userChoice.getTitle();
    for (Card card : currentPlayer.getPlayableCards()) {
      //if the card is also in playable cards
      if (card.getTitle().equals(userCardTitle)) {
        isPlayable = true;
      }
    }
    return isPlayable;
  }

  /**
   * Returns true if all but one player in the game have no cards left, otherwise false
   *
   * @return isOver result of comparison
   */
  boolean isOver() {
    int numWinners = 0;
    for (Player player : players) {
      int currentHandSize = player.getCurrentHand().size();
      if (currentHandSize == 0) {
        ++numWinners;
      }
    }
    return numWinners == numPlayers - 1;
  }

  /**
   * Returns true if the passed player has won the game and was added to winners, otherwise false
   *
   * @param playerUp the player whose hand will be checked
   * @return boolean of comparison
   */
  boolean hasWon(Player playerUp) {
    int currentHandSize = playerUp.getCurrentHand().size();
    if (currentHandSize == 0) {
      winners.add(playerUp);
      return true;
    } else {
      return false;
    }
  }

  /** Completely skips player's turn- used when that user is out of the round */
  void skipPlayer() {
    currentPlayer = getNextPlayer();
  }

  void resetNumPasses() {
    this.numPasses = 0;
  }

  /** Allows all previously passed players back in the game */
  void setAllPlayersIn() {
    for (Player player : players) {
      player.setIsOut(false);
    }
  }

  /**
   * Selects a random dealer from the number of players and also assigns them to current player
   *
   * @return string the name of the dealer
   */
  public String selectDealer() {
    Random rand = new Random();
    int dealerID = rand.nextInt(numPlayers);
    this.dealer = players[dealerID];

    this.currentPlayer = dealer;
    return dealer.getName();
  }

  /**
   * Sets the dealer for the game to the passed player.
   *
   * @param player the player who will deal
   * @return the player's name
   */
  String selectDealer(Player player) {
    this.dealer = player;
    return player.getName();
  }

  /** Deals cards from the deck to each player based on the number of cards required in the hand */
  public void dealInitialHands() {
    superTrumpsDeck.shuffle();
    ArrayList<Card> newHand;
    for (Player player : players) {
      int CARDS_TO_A_HAND = 8;
      newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
      player.setCurrentHand(newHand);
    }

    //Used for hard dealing Combo to players
    //ArrayList<Card> userHand = new ArrayList<>();
    //userHand.add(superTrumpsDeck.getMagnetite());
    //userHand.add(superTrumpsDeck.getGeophys());
    //userHand.addAll(superTrumpsDeck.dealHand(CARDS_TO_A_HAND));
    //players[0].setCurrentHand(userHand);
    //
    //ArrayList<Card> newHand;
    //for (int i = 1; i < players.length; i++) {
    //  newHand = superTrumpsDeck.dealHand(CARDS_TO_A_HAND);
    //  players[i].setCurrentHand(newHand);
    //}

  }

  /**
   * Takes a card choice and trump category choice and sets the appropriate attributes for the first
   * turn
   *
   * @param cardChoice the card to play
   * @param trumpChoiceStr the trump category that will lead out the round
   */
  void playFirstTurn(Card cardChoice, String trumpChoiceStr) {
    //Used in case of mineral card or the Geologist
    this.lastPlayedCard = cardChoice;
    this.lastUserToPlay = currentPlayer;
    setCurrentCategory(trumpChoiceStr);
    currentPlayer = getNextPlayer();
  }

  /**
   * Takes a boolean to decide whether it's the first turn of the game or just a new round, gets the
   * relevant attributes from the player and sets them
   *
   * @param isStartOfGame true when it's the very first turn of the game
   */
  void playFirstTurn(boolean isStartOfGame) {
    //AI Play First Turn
    this.lastPlayedCard = currentPlayer.playFirstCard(0, isStartOfGame);
    this.lastUserToPlay = currentPlayer;
    setCurrentCategory(currentPlayer.chooseCategory());
    currentPlayer = getNextPlayer();
  }

  /**
   * Gets a playable card choice (and potentially a trump category) from the current player and sets
   * the appropriate attributes
   */
  void playTurn() {
    //For AI functionality
    if (currentPlayer.hasCombo()) {
      this.lastPlayedCard = currentPlayer.playCombo();
      comboWasPlayed = true;
    } else {
      this.lastPlayedCard = currentPlayer.playCard(0);
      this.lastUserToPlay = currentPlayer;
      //if it's a trump card the trump category also needs changing
      if (lastPlayedCard.isGeologist()) {
        setCurrentCategory(currentPlayer.chooseCategory());
      } else if (lastPlayedCard.isTrump()) {
        setCurrentCategory(lastPlayedCard.getInfo());
      }
      currentPlayer = getNextPlayer();
    }
  }

  /**
   * Plays the current card on top of the last played card and, if it's a trump, also changes the
   * trump category
   *
   * @param chosenCard the card chosen by the player
   */
  void playTurn(Card chosenCard) {
    //    For all cards which don't require a trump category from user
    this.lastPlayedCard = chosenCard;
    this.lastUserToPlay = currentPlayer;
    //If Supertrump card was chosen, category is reset
    if (chosenCard.isTrump()) {
      setCurrentCategory(lastPlayedCard.getInfo());
    }
    currentPlayer = getNextPlayer();
  }

  /**
   * Takes a card and trump choice from the user and sets the appropriate attributes
   *
   * @param chosenCard the card chosen by the player
   * @param trumpStr the trump category chosen by the player
   */
  void playTurn(Card chosenCard, String trumpStr) {
    this.lastPlayedCard = chosenCard;
    this.lastUserToPlay = currentPlayer;
    setCurrentCategory(trumpStr);
    currentPlayer = getNextPlayer();
  }

  /**
   * Sets the appropriate attributes for a player's choosing to pass and checks for a round winner
   */
  void pass() {
    //draw a card if the deck is not empty
    int numCardsLeft = superTrumpsDeck.getCards().size();
    if (numCardsLeft != 0) {
      Card drawnCard = superTrumpsDeck.drawCard();
      currentPlayer.addCard(drawnCard);
    }
    //set the player out of the current round
    currentPlayer.setIsOut(true);
    incrementNumPasses();
    currentPlayer = getNextPlayer();
    //if passing resulted in a new round, get that player's name and make them the current player
    if (isNewRound()) {
      roundWinner = lastUserToPlay.getName();
      currentPlayer = lastUserToPlay;
    }
  }

  /**
   * Plays the combo from the player's hand and sets the attribute to start a new round
   *
   * @return the player who played the combo
   */
  Player playCombo() {
    this.lastPlayedCard = currentPlayer.playCombo();
    comboWasPlayed = true;
    return currentPlayer;
  }

  /**
   * Sets the current trump category based on the passed string
   *
   * @param currentCategory category string
   */
  private void setCurrentCategory(String currentCategory) {
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
}
