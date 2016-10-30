package gui;

import game.Game;
import players.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class checks whether the previous Mineral Supertrumps turn requires dialogs for the user, and
 * displays them if so Created by Draga on 21/10/2016.
 */
class AlertChecks {

  private final JPanel cardContainer;
  private final JTextPane logPane;
  private final JButton[] controlButtons;
  private final JButton viewTurnButton;

  /**
   * Assigns the relevant GUI elements required to fields
   *
   * @param gui the object containing all elements that may need updating
   */
  AlertChecks(MineralSupertrumps gui) {
    this.cardContainer = gui.cardImgPanel;
    this.logPane = gui.gameLogPane;
    this.controlButtons = gui.playerControlButtons;
    this.viewTurnButton = gui.viewTurnButton;
  }

  /**
   * Method performs various checks about a current game's state and displays an alert
   * message/updates relevant labels if required
   */
  void checkUserAlerts() {
    String alertMessage = "";
    Game game = Game.currentGame;
    Player user = game.getCurrentPlayer();
    String userName = user.getName();
    ArrayList<Player> winners = game.getWinners();

    //if user has already won
    if (winners.contains(user)) {
      TurnUpdate.updateLog(logPane, userName + " has already won!");
      game.skipPlayer();
      new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
      TurnUpdate.updateLog(logPane, "############################");
      TurnUpdate.updateLog(
          logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
      //if it's the beginning of a round
    } else if (game.isNewRound()) {
      alertMessage = userName + " won the round!\n";
      //if the user has already passed this round
    } else if (user.isOut()) {
      alertMessage = "You are out of the round.\n";
      game.skipPlayer();
      TurnUpdate.updateLog(logPane, userName + " is out for the round.");
      new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
      TurnUpdate.updateLog(logPane, "############################");
      TurnUpdate.updateLog(
          logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
    } else if (!game.isFirstTurn()) {
      //if the user does not have any playable cards for this turn
      user.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
      if (!user.hasPlayableCards()) {
        alertMessage = "You have no playable cards and have therefore been passed.\n";
        game.pass();
        new HandView(cardContainer).showCards();
        new ActivateButtons(controlButtons, viewTurnButton)
            .setActiveButtons(game.getCurrentPlayer());
        TurnUpdate.updateLog(logPane, "############################");
        TurnUpdate.updateLog(
            logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
      }
    }
    //only alert if there is a message to show and the game isn't over
    if (!alertMessage.equals("") && !game.isOver()) {
      JOptionPane.showMessageDialog(null, alertMessage);
    }
  }
}
