package Deck;

import Cards.Card;

/**
 * Testing aspects of deck objects
 * Created by Draga on 21/09/2016.
 */
public class DeckTests {
    public static void main(String[] args) {
        Deck myDeck = DeckBuilder.buildDeckFromPlist();
        for (Card card :
                myDeck.getCards()) {
            if (card.isTrump()) {
                System.out.println(card.getInfo());
            }
        }
    }
}
