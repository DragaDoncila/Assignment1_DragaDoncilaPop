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
        ArrayList<Card> hand = new ArrayList<>(cards_to_a_hand);
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

    public Card drawCard() {
//        Return the card on the top of the deck
        return cards.remove(0);
    }

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
