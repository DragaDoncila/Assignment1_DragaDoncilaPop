package players;

import cards.Card;
import trumps.Trump;

import java.util.ArrayList;

/**
 * Abstract class outlays behaviours of Players for MineralSupertrumps games. Created by Draga on
 * 17/09/2016.
 */
public abstract class Player {

  /**
   * Returns the card with the passed title in the user's hand. Does not remove it from the hand.
   *
   * @param passedCardTitle the string title of the card to return
   * @return chosenCard the card in the hand whose title matches the passed title
   */
  public Card getCardByTitle(String passedCardTitle) {
    Card chosenCard = null;
    for (Card card : currentHand) {
      String cardTitle = card.getTitle();
      if (cardTitle.equals(passedCardTitle)) {
        chosenCard = card;
      }
    }
    return chosenCard;
  }

  public int getCardIndex(Card potentialCard) {
    int cardIndex = -1;
    String potentialCardTitle = potentialCard.getTitle();
    for (int i = 0; i < currentHand.size(); i++) {
      String cardTitle = currentHand.get(i).getTitle();
      if (cardTitle.equals(potentialCardTitle)) {
        cardIndex = i;
      }
    }
    return cardIndex;
  }

  public enum PlayerTypes {
    USER,
    BOT
  }

  int id;

  PlayerTypes type;
  ArrayList<Card> currentHand;
  ArrayList<Card> playableCards;
  String name;
  boolean isOut;

  public PlayerTypes getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public Card getCard(int cardChoice) {
    return currentHand.get(cardChoice);
  }

  public ArrayList<Card> getPlayableCards() {
    return playableCards;
  }

  public ArrayList<Card> getCurrentHand() {
    return currentHand;
  }

  public void setCurrentHand(ArrayList<Card> currentHand) {
    this.currentHand = currentHand;
  }

  public void setIsOut(boolean isOut) {
    this.isOut = isOut;
  }

  public boolean isOut() {
    return isOut;
  }

  /**
   * Builds a variable array and adds cards to it if the card can play on the previously played card
   * and trump category
   *
   * @param lastPlayedCard the card played last in game
   * @param currentCategory the current category in game
   */
  public void setPlayableCards(Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
    ArrayList<Card> playableCards = new ArrayList<>();
    for (Card card : currentHand) {
      if ((lastPlayedCard == null || currentCategory == null)
          || card.canPlayOn(lastPlayedCard, currentCategory)) {
        playableCards.add(card);
      }
    }
    this.playableCards = playableCards;
  }

  public boolean hasPlayableCards() {
    return (playableCards.size() != 0);
  }

  /**
   * Iterates through user's hand and returns true if they have the combo
   *
   * @return hasCombo true if they have the combo
   */
  public boolean hasCombo() {
    boolean hasCombo = false;
    int countComboCards = 0;
    for (Card card : currentHand) {
      if (card.isComboCard()) {
        ++countComboCards;
      }
    }
    if (countComboCards == 2) {
      hasCombo = true;
    }
    return hasCombo;
  }

  public void addCard(Card drawnCard) {
    currentHand.add(drawnCard);
  }

  /**
   * Removes both combo cards from the hand and returns magnetite for playing
   *
   * @return magnetite
   */
  public Card playCombo() {
    int magnetiteIndex = -1;
    int geophysIndex = -1;

    for (int i = 0; i < currentHand.size(); i++) {
      Card currentCard = currentHand.get(i);
      if (currentCard.getTitle().equals("Magnetite")) {
        magnetiteIndex = i;
      } else if (currentCard.getTitle().equals("The Geophysicist")) {
        geophysIndex = i;
      }
    }
    currentHand.remove(geophysIndex);
    return currentHand.remove(magnetiteIndex);
  }

  /**
   * Chooses a trump category to be set for the game
   *
   * @return trumpCategory
   */
  public abstract String chooseCategory();

  /**
   * Returns true if this player is the user
   *
   * @return boolean
   */
  public abstract boolean isUser();

  /**
   * Returns the chosen card
   *
   * @param cardChoice the chosen card
   * @return the card
   */
  public abstract Card playCard(int cardChoice);

  /**
   * Checks whether it's the first turn of the game and returns an appropriate card
   *
   * @param cardChoice the chosen card
   * @param b true if it's the first turn of the game
   * @return the chosen card
   */
  public abstract Card playFirstCard(int cardChoice, boolean b);
}
