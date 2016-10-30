package gui;

import cards.Card;
import game.Game;
import players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class handles functionality and updates required for the user clicking "play card" in a game of
 * Mineral Supertrumps
 *
 * Created by Draga on 20/10/2016.
 */
class PlayHumanTurn implements ActionListener {
  private final JPanel cards;
  private final JLabel cardLabel;
  private final JTextPane logPane;
  private final JPanel cardContainer;
  private final JButton viewTurnButton;
  private final JButton[] controlButtons;
  private final JPanel parentContainer;

  PlayHumanTurn(MineralSupertrumps mineralSupertrumps) {
    this.cards = mineralSupertrumps.cardImgPanel;
    this.cardLabel = mineralSupertrumps.playCardLabel;
    this.logPane = mineralSupertrumps.gameLogPane;
    this.cardContainer = mineralSupertrumps.cardImgPanel;
    this.viewTurnButton = mineralSupertrumps.viewTurnButton;
    this.controlButtons = mineralSupertrumps.playerControlButtons;
    this.parentContainer = mineralSupertrumps.parentContainer;
  }

  /**
   * Method queries game object for its current state and displays/acts upon the GUI accordingly as
   * a result of human playing their turn
   *
   * @param e the click event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Game game = Game.currentGame;
    Player user = game.getCurrentPlayer();
    String userName = user.getName();

    ArrayList<Player> winners = game.getWinners();
    if (!winners.contains(user)) {
      if (!user.isOut()) {
        Card potentialCard = getCurrentCard();
        int potentialCardIndex = user.getCardIndex(potentialCard);
        if (game.isFirstTurn()) {
          if (!potentialCard.isTrump()) {
            String chosenCategory = getCategoryChoice();
            Card chosenCard = user.playCard(potentialCardIndex);
            game.playFirstTurn(chosenCard, chosenCategory);
            //update turn details
            Card lastPlayedCard = game.getLastPlayedCard();
            TurnUpdate.updateCard(cardLabel, lastPlayedCard);
            TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
            //update hand view
            new HandView(cardContainer).showCards();
          }
          //else (card is supertrump and it's the first turn)
          else {
            JOptionPane.showMessageDialog(null, "You cannot play a Supertrump on the first turn.");
          }
        } else if (game.isNewRound()) {
          game.setAllPlayersIn();
          game.resetNumPasses();

          Card chosenCard = user.playCard(potentialCardIndex);
          if (!chosenCard.isTrump() || chosenCard.isGeologist()) {
            String chosenCategory = getCategoryChoice();
            game.playTurn(chosenCard, chosenCategory);
          }
          //else (card is supertrump but not geologist)
          else {
            game.playTurn(chosenCard);
          }
          //update turn details
          Card lastPlayedCard = game.getLastPlayedCard();
          TurnUpdate.updateCard(cardLabel, lastPlayedCard);
          TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());

          //update hand view
          new HandView(cardContainer).showCards();
        }
        //else (normal turn)
        else {
          user.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
          if (user.hasPlayableCards()) {
            if (game.isPlayable(potentialCard)) {
              Card chosenCard = user.playCard(potentialCardIndex);
              if (chosenCard.isGeologist()) {
                String chosenCategory = getCategoryChoice();
                game.playTurn(chosenCard, chosenCategory);
              }
              //else (card does not require category choice)
              else {
                game.playTurn(chosenCard);
              }
              //update turn details
              Card lastPlayedCard = game.getLastPlayedCard();
              TurnUpdate.updateCard(cardLabel, lastPlayedCard);
              TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());

              //update hand view
              new HandView(cardContainer).showCards();
            }
            //else (chosen card not playable)
            else {
              JOptionPane.showMessageDialog(
                  null, potentialCard.getTitle() + " can't play on that card.");
            }
          }
        }
        //if player has won (as a result of this turn)
        if (game.hasWon(user)) {
          JOptionPane.showMessageDialog(null, userName + " has won!");
          if (game.isOver()) {
            JOptionPane.showMessageDialog(
                null, "That's the end of the game! Thank you for playing.");
            new GoToMain(parentContainer).showMainScreen();
            return;
          }
        }
        //if trump was played
        Card lastPlayed = game.getLastPlayedCard();
        if (lastPlayed != null && lastPlayed.isTrump()) {
          TurnUpdate.updateLog(logPane, "Supertrump Played! All players in");
          game.resetNumPasses();
          game.setAllPlayersIn();
        }
      }
    }
    //update log for next player's turn
    TurnUpdate.updateLog(logPane, "############################");
    TurnUpdate.updateLog(
        logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
    new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
  }

  /**
   * Prompts the user using a combo box dialog and returns the stripped category string
   *
   * @return categoryString the stripped string
   */
  private String getCategoryChoice() {
    String[] choices = {
      "Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"
    };

    String categoryChoice =
        (String)
            JOptionPane.showInputDialog(
                null,
                "Choose your trump category: ",
                "Trump Category",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]);

    return categoryChoice.replaceAll("\\s+", "").toLowerCase();
  }

  /**
   * Checks which card was being displayed when the user selects play card and grabs the appropriate
   * object from Game
   *
   * @return currentCard the card active when user clicked play card
   */
  private Card getCurrentCard() {
    String currentCardTitle = "";
    Component[] allCards = cards.getComponents();
    for (Component component : allCards) {
      //if we've got the jpanel that's currently showing
      if (component.isVisible()) {
        JPanel card = (JPanel) component;
        currentCardTitle = card.getName();
      }
    }
    //get the Card object from the user's hand and return
    Card currentCard = Game.currentGame.getCurrentPlayer().getCardByTitle(currentCardTitle);
    return currentCard;
  }
}
