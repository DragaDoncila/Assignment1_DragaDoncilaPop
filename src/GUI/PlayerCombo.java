package GUI;

import Cards.Card;
import Game.Game;
import Players.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by Draga on 22/10/2016. */
public class PlayerCombo implements ActionListener {

  private final JPanel cardImgPanel;
  private final JTextPane logPane;
  private final JButton[] controlButtons;
  private final JButton viewTurnButton;
  private final JLabel playLabel;

  public PlayerCombo(
          JPanel cardImgPanel,
          JTextPane gameLogPane,
          JLabel playCardLabel, JButton[] playerControlButtons,
          JButton viewTurnButton) {
    this.cardImgPanel = cardImgPanel;
    this.logPane = gameLogPane;
    this.controlButtons = playerControlButtons;
    this.viewTurnButton = viewTurnButton;
    this.playLabel = playCardLabel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Game game = Game.currentGame;
    Player user = game.getCurrentPlayer();
    String userName = user.getName();

    if (user.hasCombo()){
      if (!game.isFirstTurn()){
        game.playCombo();
        JOptionPane.showMessageDialog(null, userName + " played the Combo!");
        //update details
        Card lastPlayedCard = game.getLastPlayedCard();
        TurnUpdate.updateCard(playLabel, lastPlayedCard);
        TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
      }
      else {
        JOptionPane.showMessageDialog(null, "You can't play the combo on the first turn.");

      }
    }
    else {
      JOptionPane.showMessageDialog(null, "You do not have the combo.");

    }
  }
}
