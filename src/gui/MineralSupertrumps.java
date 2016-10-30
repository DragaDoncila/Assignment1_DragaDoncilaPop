package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class handles the building and event listeners required for playing a game of Mineral Supertrumps
 * Created by Draga on 8/10/2016.
 */
public class MineralSupertrumps {
  static final String CATEGORY_STRING = "Current Category:\n";
  JPanel parentContainer;
  JPanel mainCard;
  JTextField usernameField;
  private JButton playButton;
  private JButton quitButton;
  private JButton instructionsButton;
  private JPanel instructionsCard;
  private JButton next1;
  private JButton back2;
  private JButton next2;
  private JButton back3;
  private JButton mainMenu;
  JPanel playerPanel;
  private JButton previousButton;
  private JButton nextButton;
  JButton playCardButton;
  JButton passTurnButton;
  JButton playComboButton;
  private JButton backToMainButton;
  JLabel playCardLabel;
  JPanel cardImgPanel;
  JButton viewTurnButton;
  JTextPane gameLogPane;
  JButton[] playerControlButtons = {playCardButton, playComboButton, passTurnButton};

  //Elements below have values assigned in Form, but are required here for compilation
  private JPanel titleLabel;
  private JPanel usernamePanel;
  private JPanel buttonPanel;
  private JLabel usernameLabel;
  private JPanel instructions1;
  private JPanel rulePanel1;
  private JPanel titlePanel;
  private JPanel imagePanel;
  private JPanel navPanel1;
  private JLabel ruleLabel;
  private JLabel cardLabel;
  private JPanel instructions2;
  private JPanel titlePanel2;
  private JLabel rule2;
  private JPanel rulePanel2;
  private JPanel imagePanel2;
  private JPanel navPanel2;
  private JLabel supertrump;
  private JPanel instructions3;
  private JPanel title3;
  private JLabel instructionsLabel;
  private JLabel instructionsLabel2;
  private JLabel instructionsLabel3;
  private JPanel navPanel3;
  private JPanel rulePanel3;
  private JPanel cardPanel3;
  private JLabel rule3;
  private JLabel goodLuck;
  private JPanel playCard;
  private JPanel initialPlayScreen;
  private JPanel navPanel;
  private JPanel playPanel;
  private JPanel handPanel;
  private JPanel gameLogPanel;
  private JScrollPane logScrollPane;

  private MineralSupertrumps() {

    quitButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int quitOption =
                JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to quit?",
                    "Quit Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (quitOption == JOptionPane.YES_OPTION) {
              System.exit(0);
            }
          }
        });

    //Instructions are handled with a Card Layout that switches through the instructions screens
    instructionsButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CardLayout myLayout = (CardLayout) (parentContainer.getLayout());
            myLayout.show(parentContainer, "instructionsCard");
          }
        });
    next1.addActionListener(new NextInstructions(instructionsCard));
    next2.addActionListener(new NextInstructions(instructionsCard));
    back2.addActionListener(new BackInstructions(instructionsCard));
    back3.addActionListener(new BackInstructions(instructionsCard));
    mainMenu.addActionListener(new GoToMain(parentContainer));

    playButton.addActionListener(new StartNewGame(this));

    //Functionality for user viewing their hand
    nextButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //go to the next card in the hand
            CardLayout cards = (CardLayout) cardImgPanel.getLayout();
            cards.next(cardImgPanel);
          }
        });
    previousButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //go to the previous card in the hand
            CardLayout cards = (CardLayout) cardImgPanel.getLayout();
            cards.previous(cardImgPanel);
          }
        });

    //Functionality for game continuation button
    viewTurnButton.addActionListener(new PlayRobotTurn(this));

    //Functionality for user's turn button
    playCardButton.addActionListener(new PlayHumanTurn(this));
    passTurnButton.addActionListener(
        new PlayerPass(cardImgPanel, gameLogPane, playerControlButtons, viewTurnButton));
    backToMainButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int quitOption =
                JOptionPane.showConfirmDialog(
                    null,
                    "Going back to main menu will end your game. \nAre you sure?",
                    "Quit Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (quitOption == JOptionPane.YES_OPTION) {
              new GoToMain(parentContainer).showMainScreen();
            }
          }
        });
    playComboButton.addActionListener(new PlayerCombo(cardImgPanel, gameLogPane, playCardLabel));
  }

  public static void main(String[] args) {
    JFrame newFrame = new JFrame("Mineral Supertrumps");
    //show full screen
    newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    newFrame.setContentPane(new MineralSupertrumps().parentContainer);
    newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    newFrame.setVisible(true);
  }
}
