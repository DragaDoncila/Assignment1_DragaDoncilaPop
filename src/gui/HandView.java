package gui;

import cards.Card;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class iterates through user's hand and populates CardLayout container with multiple JPanels, each
 * representing a card Created by Draga on 19/10/2016.
 */
class HandView {
  private final JPanel cardContainer;

  HandView(JPanel cardContainer) {
    this.cardContainer = cardContainer;
  }

  /**
   * Iterates through the user's current hand and constructs the hand view, one panel with
   * appropriate image per card
   */
  void showCards() {
    emptyContainer();
    Game game = Game.currentGame;
    ArrayList<Card> userHand = game.getPlayers()[0].getCurrentHand();
    for (Card card : userHand) {
      //make a new card (JPanel) in cardContainer with a label
      JPanel newCard = new JPanel();
      //find the image associated with the card and display it as an icon for the label
      ImageIcon cardImg = new ImageIcon("src/GUI/images/cards/" + card.getFileName());
      JLabel imgLabel = new JLabel(cardImg);
      newCard.add(imgLabel);
      newCard.setName(card.getTitle());
      cardContainer.add(newCard);
    }
    cardContainer.revalidate();
    cardContainer.repaint();
  }

  private void emptyContainer() {
    try {
      for (Component component : cardContainer.getComponents()) {
        cardContainer.remove(component);
      }
    } catch (NullPointerException noComponent) {
      noComponent.printStackTrace();
    }
  }
}
