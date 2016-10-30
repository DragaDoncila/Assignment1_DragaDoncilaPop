package gui;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class handles functionality for the player choosing to pass a turn in Mineral Supertrumps
 *
 * <p>Created by Draga on 22/10/2016.
 */
class PlayerPass implements ActionListener {
  private final JPanel cardImgPanel;
  private final JTextPane logPane;
  private final JButton[] controlButtons;
  private final JButton viewTurnButton;

  PlayerPass(
      JPanel cardImgPanel,
      JTextPane gameLogPane,
      JButton[] playerControlButtons,
      JButton viewTurnButton) {
    this.cardImgPanel = cardImgPanel;
    this.logPane = gameLogPane;
    this.controlButtons = playerControlButtons;
    this.viewTurnButton = viewTurnButton;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Game game = Game.currentGame;
    TurnUpdate.updateLog(
        logPane, game.getCurrentPlayer().getName() + " chose to pass and is out for the round.");
    game.pass();

    //update the hand view as user may have drawn a card
    new HandView(cardImgPanel).showCards();
    new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
    TurnUpdate.updateLog(logPane, "############################");
    TurnUpdate.updateLog(
        logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
  }
}
