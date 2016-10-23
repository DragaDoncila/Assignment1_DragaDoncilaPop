package gui;

import game.Game;
import players.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Draga on 21/10/2016.
 */
public class AlertChecks {

    private final JPanel cardContainer;
    private final JTextPane logPane;
    private final JButton[] controlButtons;
    private final JButton viewTurnButton;

    public AlertChecks(MineralSupertrumps gui) {
        this.cardContainer = gui.cardImgPanel;
        this.logPane = gui.gameLogPane;
        this.controlButtons = gui.playerControlButtons;
        this.viewTurnButton = gui.viewTurnButton;
    }

    public void checkUserAlerts() {
        String alertMessage = "";
        Game game = Game.currentGame;
        Player user = game.getCurrentPlayer();
        String userName = user.getName();
        ArrayList<Player> winners = game.getWinners();


        if (winners.contains(user)){
            //if user has won we do not concatenate to the previous messages, just replace them
            TurnUpdate.updateLog(logPane, userName + " has already won!");
            game.skipPlayer();
            new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
            TurnUpdate.updateLog(logPane, "############################");
            TurnUpdate.updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        }
        else if (game.isNewRound()){
            alertMessage = userName + " won the round!\n";
        }
        else if (user.isOut()){
            alertMessage = "You are out of the round.\n";
            //skip
            game.skipPlayer();
            TurnUpdate.updateLog(logPane, userName + " is out for the round.");
            new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
            TurnUpdate.updateLog(logPane, "############################");
            TurnUpdate.updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        }
        else if (!game.isFirstTurn()){
            user.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
            if (!user.hasPlayableCards()){
                alertMessage = "You have no playable cards and have therefore been passed.\n";
                game.pass();
                new HandView(cardContainer).showCards();
                new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
                TurnUpdate.updateLog(logPane, "############################");
                TurnUpdate.updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
            }
        }
        //only alert if there is a message to show and the game isn't over
        if (! alertMessage.equals("") && !game.isOver()){
            JOptionPane.showMessageDialog(null, alertMessage);
        }
    }
}
