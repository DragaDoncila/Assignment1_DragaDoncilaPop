package GUI;

import Cards.Card;
import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Draga on 19/10/2016.
 */
public class HandView {
    private final JPanel cardContainer;

    public HandView(JPanel cardContainer) {
        this.cardContainer = cardContainer;
    }

    void showCards() {
        emptyContainer();
        Game game = Game.currentGame;
        ArrayList<Card> userHand = game.getPlayers()[0].getCurrentHand();
        for (Card card :
                userHand) {
            //make a new card (JPanel) in cardContainer with a label
            JPanel newCard = new JPanel();
            //find the image associated with the card and display it as an icon for the label
            ImageIcon cardImg = new ImageIcon("src/GUI/images/cards/"+ card.getFileName());
            JLabel imgLabel = new JLabel(cardImg);

            newCard.add(imgLabel);
            cardContainer.add(newCard, card.getTitle());

        }
    }

    private void emptyContainer() {
        try{
            for (Component component :
                    cardContainer.getComponents()) {
                cardContainer.remove(component);
            }
        }catch (NullPointerException noComponent){
            noComponent.printStackTrace();
        }
    }
}
