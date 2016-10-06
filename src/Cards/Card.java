package Cards;

import java.util.ArrayList;

import Trumps.Trump;

/**
 * Abstract class holds basic attributes and methods for Mineral Supertrumps cards. It is extended
 * by the two types of cards available- MineralCard and SupertrumpCard. Created by Draga on
 * 6/09/2016.
 */
public abstract class Card {

  private enum CardTypes {
    PLAY,
    TRUMP
  }

  private CardTypes cardType;
  private String title;

  /**
   * Constructor takes an array of attributes and parses this array into the relevant fields
   *
   * @param attributes a variable array of String attributes
   */
  Card(ArrayList<String> attributes) {
    String cardTypeStr = attributes.get(2);
    if (cardTypeStr.equals("play")) {
      this.cardType = CardTypes.PLAY;
    } else {
      this.cardType = CardTypes.TRUMP;
    }
    this.title = attributes.get(3);
  }

  public String getTitle() {
    return title;
  }

  public abstract String getInfo();

  /**
   * Returns the value of the passed trump category
   *
   * @param category the trump category in question
   * @return value the value of the specified category
   */
  public abstract String getTrumpVal(Trump.TrumpCategories category);

  /**
   * Returns true if the card is a trump card, otherwise false
   *
   * @return bool the result of comparison
   */
  public boolean isTrump() {
    return this.cardType == CardTypes.TRUMP;
  }

  /**
   * Returns true if the card is The Geophysicist or Magnetite, otherwise false
   *
   * @return bool the result of the comparison
   */
  public boolean isComboCard() {
    return (this.title.equals("The Geophysicist") || this.title.equals("Magnetite"));
  }

  /**
   * Returns true if the card is The Geologist, otherwise false;
   *
   * @return bool the result of the comparison
   */
  public abstract boolean isGeologist();

  /**
   * Returns the current card's ability to be played on top of the previously played card and
   * category.
   *
   * @param lastPlayedCard the card last played in the game
   * @param currentCategory the category currently in play
   * @return bool true if the card can play, otherwise false
   */
  public abstract boolean canPlayOn(Card lastPlayedCard, Trump.TrumpCategories currentCategory);

  @Override
  public String toString() {
    return String.format("%-30s", "Title: ")
        + title
        + "\n------------------------------------------\n";
  }
}
