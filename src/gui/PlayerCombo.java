package gui;

import cards.Card;
import game.Game;
import players.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class handles functionality for the user playing the Supertrumps Combo on one of their turns
 * Created by Draga on 22/10/2016.
 */
class PlayerCombo implements ActionListener {

  private final JPanel cardImgPanel;
  private final JTextPane logPane;
  private final JLabel playLabel;

  PlayerCombo(JPanel cardImgPanel, JTextPane gameLogPane, JLabel playCardLabel) {
    this.cardImgPanel = cardImgPanel;
    this.logPane = gameLogPane;
    this.playLabel = playCardLabel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Game game = Game.currentGame;
    Player user = game.getCurrentPlayer();
    String userName = user.getName();

    if (user.hasCombo()) {
      //Combo cannot be played on first turn
      if (!game.isFirstTurn()) {
        game.playCombo();
        JOptionPane.showMessageDialog(null, userName + " played the Combo!");

        //update details on screen
        Card lastPlayedCard = game.getLastPlayedCard();
        TurnUpdate.updateCard(playLabel, lastPlayedCard);
        TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
        new HandView(cardImgPanel).showCards();
      } else {
        JOptionPane.showMessageDialog(null, "You can't play the combo on the first turn.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "You do not have the combo.");
    }
  }
}
