package GUI;

import Cards.Card;
import Game.Game;
import Players.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Draga on 18/10/2016.
 */
public class PlayRobotTurn implements ActionListener {
    private final JLabel categoryLabel;
    private final JPanel parentContainer;
    private final JButton viewTurnButton;
    private final JTextPane logTextPane;
    private final JButton[] controlButtons;

    public PlayRobotTurn(MineralSupertrumps mineralSupertrumps) {
        this.categoryLabel = mineralSupertrumps.playCardLabel;
        this.parentContainer = mineralSupertrumps.parentContainer;
        this.viewTurnButton = mineralSupertrumps.viewTurnButton;
        this.logTextPane = mineralSupertrumps.gameLogPane;
        this.controlButtons = mineralSupertrumps.playerControlButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = Game.currentGame;
        Player playerUp = game.getCurrentPlayer();
        String playerName = playerUp.getName();
        //if the player hasn't won
        ArrayList<Player> winners = game.getWinners();
        if (!winners.contains(playerUp)) {
            //if the player is not out
            if (!playerUp.isOut()) {
                //if it's the first turn of the game
                if (game.isFirstTurn()) {
                    //playFirstTurn()
                    game.playFirstTurn(true);
                    //update card image
                    Card lastPlayedCard = game.getLastPlayedCard();
                    new TurnUpdate().updateCard(categoryLabel, lastPlayedCard);
                    new TurnUpdate().updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());
                }
                //else if it is a new round
                else if (game.isNewRound()) {
                    //alert player won round
                    JOptionPane.showMessageDialog(null, playerName + " won the round!");
                    game.setAllPlayersIn();
                    game.resetNumPasses();
                    //game.playFirstTurn()
                    game.playFirstTurn(false);
                    //set last played card image
                    Card lastPlayedCard = game.getLastPlayedCard();
                    new TurnUpdate().updateCard(categoryLabel, lastPlayedCard);
                    new TurnUpdate().updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());
                }
                //else if the player has playable cards
                else {
                    playerUp.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
                    if (playerUp.hasPlayableCards()) {
                        //game.playTurn()
                        game.playTurn();
                        //######if Combo?????
                        //update details
                        Card lastPlayedCard = game.getLastPlayedCard();
                        new TurnUpdate().updateCard(categoryLabel, lastPlayedCard);
                        new TurnUpdate().updateLog(logTextPane, playerName + " played " + lastPlayedCard.getTitle());

                    }
                    //else (not new round, no playable cards)
                    else {
                        //game.pass()
                        game.pass();
                        //TODO: set player label to red
                        //update card label: player passed OR player played
                        new TurnUpdate().updateLog(logTextPane, playerName + " chose to pass and is out for the round");
                    }
                }
                //if player won (as a result of playing this turn)
                if (game.hasWon(playerUp)) {
                    //alert: player won
                    JOptionPane.showMessageDialog(null, playerName + " has won!");
                    //if game over:
                    if (game.isOver()) {
                        //alert
                        JOptionPane.showMessageDialog(null, "That's the end of the game! Thank you for playing.");
                        //back to main
                        new GoToMain(parentContainer);
                    }
                }
                Card lastPlayed = game.getLastPlayedCard();
                if (lastPlayed.isTrump()){
                    new TurnUpdate().updateLog(logTextPane, "Supertrump Played! All players in");
                    game.resetNumPasses();
                    game.setAllPlayersIn();
                }
            }
            //else (player is out)
            else {
                //skip
                game.skipPlayer();
                //update card label
                new TurnUpdate().updateLog(logTextPane, playerName + " is out for the round.");
            }
        }
        //else (player has already won)
        else {
            //skip
            game.skipPlayer();
            //update card label: player has already won
            new TurnUpdate().updateLog(logTextPane, playerName + " has already won!");
        }
        new TurnUpdate().updateLog(logTextPane, "############################");
        new TurnUpdate().updateLog(logTextPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());

    }
}
