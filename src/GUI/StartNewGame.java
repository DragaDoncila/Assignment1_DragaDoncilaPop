package GUI;

import Game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.PaintEvent;

/**
 * Created by Draga on 12/10/2016.
 */
public class StartNewGame implements ActionListener {
    private final JPanel parentContainer;

    public StartNewGame(JPanel mainCard) {
        this.parentContainer = mainCard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startNewGame();
    }

    private void startNewGame() {
        //get valid number of players
        int numPlayers = getValidNumPlayers();
        //create new game

        //select a dealer

        //deal cards

        //show new card in MST frame with the setup completed and information displayed.
    }

    private int getValidNumPlayers() {
        boolean isValid = false;
        int numPlayers = 0;
        String numPlayerStr = JOptionPane.showInputDialog(parentContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);
        while (!isValid) {
            if (numPlayerStr != null) {
                try {
                    numPlayers = Integer.parseInt(numPlayerStr);
                    if (!Game.isValidNumPlayers(numPlayers)) {
                        JOptionPane.showMessageDialog(parentContainer, "That is not a valid number of players", "Error Message", JOptionPane.ERROR_MESSAGE);
                        numPlayerStr = JOptionPane.showInputDialog(parentContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);

                    }
                    else {
                        isValid = true;
                    }
                } catch (NumberFormatException formatError) {
                    JOptionPane.showMessageDialog(parentContainer, "That is not a valid number", "Error Message", JOptionPane.ERROR_MESSAGE);
                    numPlayerStr = JOptionPane.showInputDialog(parentContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);
                }
            }
            else {
                //cancel option
                isValid = true;
            }
        }
        return numPlayers;
    }
}
