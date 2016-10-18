package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 18/10/2016.
 */
public class PlayRobotTurn implements ActionListener {
    public PlayRobotTurn(MineralSupertrumps mineralSupertrumps) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if the player hasn't won
            //if the player is not out
                //if it is a new round:
                    //game.playFirstTurn()
                    //set last played card image
                //else if the player has playable cards
                    //game.playTurn()
                    //set last played card image
                //else (not new round, no playable cards)
                    //game.pass()
                //update card label: player passed OR player played
                //if player won
                    //alert: player won
                    //if game over:
                        //alert
                        //back to main
            //else (player is out)
                //pass
                //set player label to red
        //else (player has already won)
                //update card label: player has already won
    }
}
