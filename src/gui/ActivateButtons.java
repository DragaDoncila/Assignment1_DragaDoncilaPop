package gui;

import players.Player;

import javax.swing.*;

/**
 * Class checks the current player of a Mineral Supertrumps game and activates the correct buttons
 * as necessary
 *
 * Created by Draga on 19/10/2016.
 */
class ActivateButtons {
  private final JButton[] controlButtons;
  private final JButton viewTurnButton;

  ActivateButtons(JButton[] controlButtons, JButton viewTurnButton) {
    this.controlButtons = controlButtons;
    this.viewTurnButton = viewTurnButton;
  }

  /**
   * Checks the type of player (user/robot) who is up and enables/disables buttons as appropriate for that
   * player's turn
   *
   * @param playerUp the player in current game who is up to play
   */
  void setActiveButtons(Player playerUp) {
    if (playerUp.isUser()) {
      this.viewTurnButton.setEnabled(false);
      for (JButton button : controlButtons) {
        button.setEnabled(true);
      }
    } else {
      this.viewTurnButton.setEnabled(true);
      for (JButton button : controlButtons) {
        button.setEnabled(false);
      }
    }
  }
}
