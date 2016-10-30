package gui;

import game.Game;
import players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Prepares the GUI screens and populates containers/Game elements to set up a new game of Mineral
 * Supertrumps Created by Draga on 12/10/2016.
 */
class StartNewGame implements ActionListener {
  private final JPanel mainContainer;
  private final JTextField usernameField;
  private final CardLayout parentLayout;
  private final JPanel playerContainer;
  private final JPanel cardContainer;
  private final JButton viewTurnButton;
  private final JTextPane logTextPane;
  private final JLabel playCardLabel;
  private JButton[] controlButtons;

  StartNewGame(MineralSupertrumps mineralSupertrumps) {
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

    String userName = usernameField.getText();
    String strippedName = userName.replaceAll("\\s+", "");
    //if the user has entered a valid username
    if (strippedName.length() >= 1) {
      int numPlayers = getValidNumPlayers();
      //0 is returned if user chooses to cancel
      if (numPlayers != 0) {
        Game newGame = new Game(numPlayers, userName);
        String dealerName = newGame.selectDealer();
        newGame.dealInitialHands();

        //populate containers with appropriate information
        showPlayers(newGame);
        new HandView(cardContainer).showCards();
        newGame.getNextPlayer();

        Player playerUp = newGame.getCurrentPlayer();
        new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(playerUp);

        String startingText =
            dealerName
                + " has dealt the cards!\nLet's go! It's "
                + playerUp.getName()
                + "'s turn\n";
        logTextPane.setText(startingText);

        //once populated, display the card
        parentLayout.show(mainContainer, "playCard");
      }

    }
    //user did not enter a valid username
    else {
      JOptionPane.showMessageDialog(
          mainContainer,
          "You must enter a username (not blank) to play!",
          "Error Message",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Populates the player container with a number of icons equal to the number of players in the
   * current game
   *
   * @param newGame the current game just initialised
   */
  private void showPlayers(Game newGame) {
    int countPics = 0;

    for (Player player : newGame.getPlayers()) {
      //add the image icon to the player's label as well as their name
      Icon playerPic = new ImageIcon("src/GUI/images/playerIcon" + countPics + ".png");
      JLabel playerLabel = new JLabel(playerPic);
      playerLabel.setText(player.getName());

      //align the label below the image
      playerLabel.setHorizontalTextPosition(JLabel.CENTER);
      playerLabel.setVerticalTextPosition(JLabel.BOTTOM);

      playerContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
      playerContainer.add(playerLabel);

      playerContainer.revalidate();
      playerContainer.repaint();

      //used to concatenate the file name
      ++countPics;
    }
  }

  /**
   * Prompts user for a valid number of players between 3 and 5 and does not return it until that is
   * the case, or 0 is entered for cancel
   *
   * @return numPlayers the chosen number of players
   */
  private int getValidNumPlayers() {
    boolean isValid = false;
    int numPlayers = 0;
    String numPlayerStr =
        JOptionPane.showInputDialog(
            mainContainer,
            "How many players (3-5) would you like?",
            "Number of Players",
            JOptionPane.QUESTION_MESSAGE);
    while (!isValid) {
      if (numPlayerStr != null) {
        try {
          numPlayers = Integer.parseInt(numPlayerStr);
          if (!Game.isValidNumPlayers(numPlayers)) {
            JOptionPane.showMessageDialog(
                mainContainer,
                "That is not a valid number of players",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
            numPlayerStr =
                JOptionPane.showInputDialog(
                    mainContainer,
                    "How many players (3-5) would you like?",
                    "Number of Players",
                    JOptionPane.QUESTION_MESSAGE);

          } else {
            isValid = true;
          }
        } catch (NumberFormatException formatError) {
          JOptionPane.showMessageDialog(
              mainContainer,
              "That is not a valid number",
              "Error Message",
              JOptionPane.ERROR_MESSAGE);
          numPlayerStr =
              JOptionPane.showInputDialog(
                  mainContainer,
                  "How many players (3-5) would you like?",
                  "Number of Players",
                  JOptionPane.QUESTION_MESSAGE);
        }
      } else {
        //user chose to cancel, this is a valid choice (just not a valid number of players)
        isValid = true;
      }
    }
    return numPlayers;
  }
}
