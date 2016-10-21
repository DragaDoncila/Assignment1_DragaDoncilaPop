package GUI;

import Game.Game;
import Players.Player;

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
            alertMessage += userName + " has already won!\n";
            game.skipPlayer();
            new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
            new TurnUpdate().updateLog(logPane, "############################");
            new TurnUpdate().updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        }

        if (user.isOut()){
            alertMessage += "You are out of the round.\n";
            //skip
            game.skipPlayer();
            new TurnUpdate().updateLog(logPane, userName + " is out for the round.");
            new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
            new TurnUpdate().updateLog(logPane, "############################");
            new TurnUpdate().updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        }

        if (game.isNewRound()){
            alertMessage += userName + " won the round!\n";
        }

        else if (!game.isFirstTurn()){
            user.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
            if (!user.hasPlayableCards()){
                alertMessage += "You have no playable cards and must pass.\n";
                //pass
                game.pass();
                new HandView(cardContainer).showCards();
                new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
                new TurnUpdate().updateLog(logPane, "############################");
                new TurnUpdate().updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
            }
        }

        if (! alertMessage.equals("")){
            JOptionPane.showMessageDialog(null, alertMessage);
        }
    }
}
