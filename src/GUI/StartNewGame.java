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
    private final JLabel infoLabel;
    private final JButton viewTurnButton;
    private JButton[] controlButtons;

    public StartNewGame(MineralSupertrumps mineralSupertrumps) {
        this.mainContainer = mineralSupertrumps.parentContainer;
        this.playerContainer = mineralSupertrumps.playerPanel;
        this.cardContainer = mineralSupertrumps.cardImgPanel;
        this.parentLayout = (CardLayout) mainContainer.getLayout();
        this.usernameField = mineralSupertrumps.usernameField;
        this.infoLabel = mineralSupertrumps.playCardLabel;
        this.viewTurnButton = mineralSupertrumps.viewTurnButton;
        controlButtons = new JButton[]{mineralSupertrumps.playCardButton, mineralSupertrumps.passTurnButton, mineralSupertrumps.playComboButton};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startNewGame();
    }

    private void startNewGame() {
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
            showCards(newGame);
            Player playerUp = newGame.getCurrentPlayer();
            setActiveButtons(playerUp);
            infoLabel.setText("Let's go! It's " + playerUp.getName() + "'s turn");

            parentLayout.show(mainContainer, "playCard");

        }
        else {
            JOptionPane.showMessageDialog(mainContainer, "You must enter a username (not blank) to play!", "Error Message", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setActiveButtons(Player playerUp) {
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

    private void showCards(Game newGame) {
        ArrayList<Card> userHand = newGame.getPlayers()[0].getCurrentHand();
        for (Card card :
                userHand) {
            //make a new card (JPanel) in cardContainer with a label
            JPanel newCard = new JPanel();
            //find the image associated with the card and display it as an icon for the label
            ImageIcon cardImg = new ImageIcon("src/GUI/images/cards/"+ card.getFileName());
            JLabel imgLabel = new JLabel(cardImg);

            newCard.add(imgLabel);
            cardContainer.add(newCard, card.getTitle());

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
