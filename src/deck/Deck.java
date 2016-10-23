package deck;

import cards.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class holds all fields and methods necessary to use this Deck to play a game of Mineral
 * Supertrumps Created by Draga on 6/09/2016.
 */
public class Deck {

  private ArrayList<Card> cards;

  /** Constructor initializes the cards array as empty variable array */
  Deck() {
    cards = new ArrayList<>();
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * Adds the passed Card object to the cards of the deck
   *
   * @param newCard Card object to add to deck
   */
  void addCard(Card newCard) {
    cards.add(newCard);
  }

  /** Shuffles the deck before play */
  public void shuffle() {
    Collections.shuffle(cards);
  }

  /**
   * Removes the passed number of cards from the top of the deck and adds them to a hand
   *
   * @param cards_to_a_hand the number of cards to deal
   * @return hand the array of dealt cards
   */
  public ArrayList<Card> dealHand(int cards_to_a_hand) {
    ArrayList<Card> hand = new ArrayList<>(cards_to_a_hand);
    for (int i = 0; i < cards_to_a_hand; ++i) {
      hand.add(cards.remove(i));
    }
    return hand;
  }

  /**
   * Return the card on the top of the deck
   *
   * @return card the card on top of the deck
   */
  public Card drawCard() {
    return cards.remove(0);
  }

  //Method used for testing Deck build
  //    public void displayDeck(){
  //        for (Card card : cards) {
  //            System.out.println(card.getTitle());
  //        }
  //    }

//  Method used for testing combo
      public Card getMagnetite() {
          int index = -1;
          for (int i = 0; i < cards.size(); i++) {
              Card card = cards.get(i);
              if (card.getTitle().equals("Magnetite")){
                  index = i;
              }
          }
          return cards.remove(index);
      }

//  Method used for testing combo
      public Card getGeophys() {
          int index = -1;
          for (int i = 0; i < cards.size(); i++) {
              if (cards.get(i).getTitle().equals("The Geophysicist")){
                  index = i;
              }
          }
          return cards.remove(index);
      }
}
