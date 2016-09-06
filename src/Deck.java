import java.util.ArrayList;

/**Class handles a deck of cards to be used in Mineral Supertrumps
 * Created by Draga on 6/09/2016.
 */
public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();

    public void addCard(Card newCard){
        cards.add(newCard);
    }

    public void displayDeck(){
        for (Card card : cards) {
            System.out.println(card.getTitle());
        }
    }
}
