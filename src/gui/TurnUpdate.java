package gui;

import cards.Card;
import game.Game;

import javax.swing.*;

/**
 * Class handles the upating of relevant GUI elements as a result of somebody playing a turn of
 * Mineral Supertrumps Created by Draga on 20/10/2016.
 */
class TurnUpdate {

  /**
   * Updates the lastPlayedCard image and the associated current category label based on current
   * game state
   *
   * @param categoryLabel the label used to display the card image and category label
   * @param lastPlayedCard the Card last played in game
   */
  static void updateCard(JLabel categoryLabel, Card lastPlayedCard) {
    Game game = Game.currentGame;
    ImageIcon cardImage = new ImageIcon("src/GUI/images/cards/" + lastPlayedCard.getFileName());
    categoryLabel.setIcon(cardImage);
    String detailString = game.getCurrentCategory().toString();
    categoryLabel.setText(MineralSupertrumps.CATEGORY_STRING + detailString);
  }

  /**
   * Updates the game log with the appropriate passed message
   *
   * @param logTextPane the game log text pane
   * @param message the message to append to the game log
   */
  static void updateLog(JTextPane logTextPane, String message) {
    String currentText = logTextPane.getText();
    String newText = currentText + "\n" + message + "\n";
    logTextPane.setText(newText);
  }
}
