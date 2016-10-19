package GUI;

import Players.Player;

import javax.swing.*;

/**
 * Created by Draga on 19/10/2016.
 */
public class ActivateButtons {
    private final JButton[] controlButtons;
    private final JButton viewTurnButton;

    public ActivateButtons(JButton[] controlButtons, JButton viewTurnButton) {
        this.controlButtons = controlButtons;
        this.viewTurnButton = viewTurnButton;
    }

    void setActiveButtons(Player playerUp) {
        if (playerUp.isUser()){
            this.viewTurnButton.setEnabled(false);
            for (JButton button :
                    controlButtons) {
                button.setEnabled(true);
            }
        }
        else {
            this.viewTurnButton.setEnabled(true);
            for (JButton button :
                    controlButtons) {
                button.setEnabled(false);
            }
        }
    }
}
