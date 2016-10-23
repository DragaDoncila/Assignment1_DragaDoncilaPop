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
 * Created by Draga on 12/10/2016.
 */
public class StartNewGame implements ActionListener {
    private final JPanel mainContainer;
    private final JTextField usernameField;
    private final CardLayout parentLayout;
    private final JPanel playerContainer;
    private final JPanel cardContainer;
    private final JButton viewTurnButton;
    private final JTextPane logTextPane;
    private final JLabel playCardLabel;
    private JButton[] controlButtons;

    public StartNewGame(MineralSupertrumps mineralSupertrumps) {
        this.mainContainer = mineralSupertrumps.parentContainer;
        this.playerContainer = mineralSupertrumps.playerPanel;
        this.cardContainer = mineralSupertrumps.cardImgPanel;
        this.parentLayout = (CardLayout) mainContainer.getLayout();
        this.usernameField = mineralSupertrumps.usernameField;
        this.logTextPane = mineralSupertrumps.gameLogPane;
        this.viewTurnButton = mineralSupertrumps.viewTurnButton;
        this.playCardLabel = mineralSupertrumps.playCardLabel;
        controlButtons = mineralSupertrumps.playerControlButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startNewGame();
    }

    private void startNewGame() {
        //empty existing containers
        new EmptyGui(playerContainer, logTextPane, playCardLabel);
        //check for a username
        String userName = usernameField.getText();
        String strippedName = userName.replaceAll("\\s+", "");
        if (strippedName.length() >= 1){
            //get valid number of players
            int numPlayers = getValidNumPlayers();
            //create new game
            Game newGame = new Game(numPlayers, userName);
            //select a dealer
            String dealerName = newGame.selectDealer();
            //deal cards
            newGame.dealInitialHands();
            //show new card in MST frame with the setup completed and information displayed
            showPlayers(newGame);
            new HandView(cardContainer).showCards();
            newGame.getNextPlayer();

            Player playerUp = newGame.getCurrentPlayer();
            new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(playerUp);
//            setActiveButtons(playerUp);

            String startingText = dealerName + " has dealt the cards!\nLet's go! It's " + playerUp.getName() + "'s turn\n";
            logTextPane.setText(startingText);

            parentLayout.show(mainContainer, "playCard");

        }
        else {
            JOptionPane.showMessageDialog(mainContainer, "You must enter a username (not blank) to play!", "Error Message", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void showPlayers(Game newGame) {
        int countPics = 0;
        for (Player player :
                newGame.getPlayers()) {
            Icon playerPic = new ImageIcon("src/GUI/images/playerIcon" + countPics + ".png");
            JLabel playerLabel = new JLabel(playerPic);
            playerLabel.setText(player.getName());

            playerLabel.setHorizontalTextPosition(JLabel.CENTER);
            playerLabel.setVerticalTextPosition(JLabel.BOTTOM);

            playerContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
            playerContainer.add(playerLabel);

            playerContainer.revalidate();
            playerContainer.repaint();
            ++countPics;
        }
    }

    private int getValidNumPlayers() {
        boolean isValid = false;
        int numPlayers = 0;
        String numPlayerStr = JOptionPane.showInputDialog(mainContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);
        while (!isValid) {
            if (numPlayerStr != null) {
                try {
                    numPlayers = Integer.parseInt(numPlayerStr);
                    if (!Game.isValidNumPlayers(numPlayers)) {
                        JOptionPane.showMessageDialog(mainContainer, "That is not a valid number of players", "Error Message", JOptionPane.ERROR_MESSAGE);
                        numPlayerStr = JOptionPane.showInputDialog(mainContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);

                    }
                    else {
                        isValid = true;
                    }
                } catch (NumberFormatException formatError) {
                    JOptionPane.showMessageDialog(mainContainer, "That is not a valid number", "Error Message", JOptionPane.ERROR_MESSAGE);
                    numPlayerStr = JOptionPane.showInputDialog(mainContainer, "How many players (3-5) would you like?", "Number of Players", JOptionPane.QUESTION_MESSAGE);
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
