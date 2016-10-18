package GUI;

import Cards.Card;
import Game.Game;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Draga on 18/10/2016.
 */
public class PlayRobotTurn implements ActionListener {
    private final JLabel infoLabel;

    public PlayRobotTurn(MineralSupertrumps mineralSupertrumps) {
        this.infoLabel = mineralSupertrumps.playCardLabel;
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
                    ImageIcon cardImage = new ImageIcon("src/GUI/images/cards/"+ lastPlayedCard.getFileName());
                    infoLabel.setIcon(cardImage);
                    //update card label
                    infoLabel.setText(playerName + " played " + lastPlayedCard.getTitle());
                }
                //else if it is a new round
                    //alert player won round
                    //game.playFirstTurn()
                    //set last played card image
                //else if the player has playable cards
                    //game.playTurn()
                    //######if Combo?????
                    //set last played card image
                //else (not new round, no playable cards)
                    //game.pass()
                    //set player label to red
                    //update card label: player passed OR player played

                //if player won (as a result of playing this turn)
                    //alert: player won
                    //if game over:
                        //alert
                        //back to main
            }
            //else (player is out)
            else {
                //skip
                game.skipPlayer();
                //update card label
                infoLabel.setText(playerName + " chose to pass.");
            }
        }
        //else (player has already won)
        else {
            //skip
            game.skipPlayer();
            //update card label: player has already won
            infoLabel.setText(playerName + " has already won!");
        }
    }
}
