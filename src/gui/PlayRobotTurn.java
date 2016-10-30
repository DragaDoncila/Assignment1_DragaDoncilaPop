package gui;

import cards.Card;
import game.Game;
import players.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class handles functionality and updates required for the user clicking "view turn" in a game of
 * Mineral Supertrumps Created by Draga on 18/10/2016.
 */
class PlayRobotTurn implements ActionListener {
  private final JLabel categoryLabel;
  private final JPanel parentContainer;
  private final JButton viewTurnButton;
  private final JTextPane logTextPane;
  private final JButton[] controlButtons;
  private final MineralSupertrumps gui;

  PlayRobotTurn(MineralSupertrumps mineralSupertrumps) {
    this.gui = mineralSupertrumps;
    this.categoryLabel = mineralSupertrumps.playCardLabel;
    this.parentContainer = mineralSupertrumps.parentContainer;
    this.viewTurnButton = mineralSupertrumps.viewTurnButton;
    this.logTextPane = mineralSupertrumps.gameLogPane;
    this.controlButtons = mineralSupertrumps.playerControlButtons;
  }

  /**
   * Method queries game object for its current state and displays/acts upon the GUI accordingly as
   * a result of robot playing a card
   *
   * @param e the click event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Game game = Game.currentGame;
    Player playerUp = game.getCurrentPlayer();
    String playerName = playerUp.getName();
    ArrayList<Player> winners = game.getWinners();
    if (!winners.contains(playerUp)) {
      if (!playerUp.isOut()) {
        if (game.isFirstTurn()) {
          game.playFirstTurn(true);
          //update turn details
          Card lastPlayedCard = game.getLastPlayedCard();
          TurnUpdate.updateCard(categoryLabel, lastPlayedCard);
          TurnUpdate.updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());
        }
        //else if it is a new round
        else if (game.isNewRound()) {
          JOptionPane.showMessageDialog(null, playerName + " won the round!");
          game.setAllPlayersIn();
          game.resetNumPasses();

          game.playFirstTurn(false);
          //update card details
          Card lastPlayedCard = game.getLastPlayedCard();
          TurnUpdate.updateCard(categoryLabel, lastPlayedCard);
          TurnUpdate.updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());
        }
        //else (is in but not new round)
        else {
          playerUp.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
          if (playerUp.hasPlayableCards()) {
            game.playTurn();

            if (game.comboWasPlayed()) {
              JOptionPane.showMessageDialog(null, playerName + " played the Combo!");
            }

            //update details
            Card lastPlayedCard = game.getLastPlayedCard();
            TurnUpdate.updateCard(categoryLabel, lastPlayedCard);
            TurnUpdate.updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());

          }
          //else (not new round, no playable cards)
          else {

            game.pass();
            TurnUpdate.updateLog(
                logTextPane, playerName + " chose to pass and is out for the round");
          }
        }
        //if player won (as a result of playing this turn)
        if (game.hasWon(playerUp)) {
          JOptionPane.showMessageDialog(null, playerName + " has won!");
          game.resetNumPasses();
          game.setAllPlayersIn();
          if (game.isOver()) {
            JOptionPane.showMessageDialog(
                null, "That's the end of the game! Thank you for playing.");
            //back to main
            new GoToMain(parentContainer).showMainScreen();
          }
        }
        Card lastPlayed = game.getLastPlayedCard();
        if (lastPlayed.isTrump()) {
          TurnUpdate.updateLog(logTextPane, "Supertrump Played! All players in");
          game.resetNumPasses();
          game.setAllPlayersIn();
        }
      }
      //else (player is out)
      else {
        game.skipPlayer();
        //update card label
        TurnUpdate.updateLog(logTextPane, playerName + " is out for the round.");
      }
    }
    //else (player has already won)
    else {
      game.skipPlayer();

      //update turn details
      TurnUpdate.updateLog(logTextPane, playerName + " has already won!");
      if (game.isNewRound()) {
        game.setAllPlayersIn();
        game.resetNumPasses();
      }
    }
    Player currentPlayer = game.getCurrentPlayer();

    //update for next player's turn
    TurnUpdate.updateLog(logTextPane, "############################");
    TurnUpdate.updateLog(logTextPane, "Let's Go! It's " + currentPlayer.getName() + "'s turn!");
    new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(currentPlayer);

    //check if any alerts need to be displayed to the user as a result of this turn
    if (currentPlayer.isUser()) {
      new AlertChecks(gui).checkUserAlerts();
    }
  }
}
