package Deck;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

/**Class handles a deck of cards to be used in Mineral Supertrumps
 * Created by Draga on 6/09/2016.
 */
public class Deck {

    private ArrayList<Card> cards;

    Deck(){
        cards =  new ArrayList<Card>();
    }

    public void addCard(Card newCard){
        cards.add(newCard);
    }

    public void displayDeck(){
        for (Card card : cards) {
            System.out.println(card.getTitle());
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<Card> dealHand(int cards_to_a_hand) {
        ArrayList<Card> hand = new ArrayList<Card>(cards_to_a_hand);
        for (int i = 0; i < cards_to_a_hand; ++i){
            hand.add(cards.remove(i));
        }
        return hand;
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
