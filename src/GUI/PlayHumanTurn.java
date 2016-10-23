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
 * Created by Draga on 20/10/2016.
 */
public class PlayHumanTurn implements ActionListener {
    private final JPanel cards;
    private final JLabel cardLabel;
    private final JTextPane logPane;
    private final JPanel cardContainer;
    private final JButton viewTurnButton;
    private final JButton[] controlButtons;
    private final JPanel parentContainer;
    private Card currentCard;

    public PlayHumanTurn(MineralSupertrumps mineralSupertrumps) {
        this.cards = mineralSupertrumps.cardImgPanel;
        this.cardLabel = mineralSupertrumps.playCardLabel;
        this.logPane = mineralSupertrumps.gameLogPane;
        this.cardContainer = mineralSupertrumps.cardImgPanel;
        this.viewTurnButton = mineralSupertrumps.viewTurnButton;
        this.controlButtons = mineralSupertrumps.playerControlButtons;
        this.parentContainer = mineralSupertrumps.parentContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = Game.currentGame;
        Player user = game.getCurrentPlayer();
        String userName = user.getName();
        //if user hasn't won
        ArrayList<Player> winners = game.getWinners();
        if (!winners.contains(user)) {
            //if user is not out
            if (!user.isOut()) {
                //get card chosen
                Card potentialCard = getCurrentCard();
                int potentialCardIndex = user.getCardIndex(potentialCard);
                //if very first turn
                if (game.isFirstTurn()) {
                    //if card is not supertrump
                    if (!potentialCard.isTrump()) {
                        //prompt for category
                        String chosenCategory = getCategoryChoice();
                        //user.play card
                        Card chosenCard = user.playCard(potentialCardIndex);
                        //playFirstTurn(card, category)
                        game.playFirstTurn(chosenCard, chosenCategory);
                        //change card label
                        Card lastPlayedCard = game.getLastPlayedCard();
                        TurnUpdate.updateCard(cardLabel, lastPlayedCard);
                        //update log
                        TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
                        //update hand
                        new HandView(cardContainer).showCards();
                    }
                    //else (card is supertrump)
                    else {
                        //alert no good
                        JOptionPane.showMessageDialog(null, "You cannot play a Supertrump on the first turn.");
                    }
                }
                //else if new round
                else if (game.isNewRound()) {
                    //reset players in
                    //reset passes
                    game.setAllPlayersIn();
                    game.resetNumPasses();
                    //user.play card (removes from user's hand)
                    Card chosenCard = user.playCard(potentialCardIndex);
                    //if card is mineral or is geologist
                    if (!chosenCard.isTrump() || chosenCard.isGeologist()) {
                        //prompt for category
                        String chosenCategory = getCategoryChoice();
                        //game.playTurn(card, category)
                        game.playTurn(chosenCard, chosenCategory);
                    }
                    //else (card is supertrump not geologist)
                    else {
                        //game.playTurn(card)
                        game.playTurn(chosenCard);
                    }
                    //change card label
                    Card lastPlayedCard = game.getLastPlayedCard();
                    TurnUpdate.updateCard(cardLabel, lastPlayedCard);
                    //update log
                    TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
                    //update hand
                    new HandView(cardContainer).showCards();
                }
                //else (normal turn)
                else {
                    //set playable cards
                    user.setPlayableCards(game.getLastPlayedCard(), game.getCurrentCategory());
                    //if has playable cards
                        if (user.hasPlayableCards()) {
                            //if chosen card is playable
                            if (game.isPlayable(potentialCard)){
                                //user/playCard
                                Card chosenCard = user.playCard(potentialCardIndex);
                                //if card is geologist
                                if (chosenCard.isGeologist()){
                                    //prompt for category
                                    String chosenCategory = getCategoryChoice();
                                    //playTurn (card, category)
                                    game.playTurn(chosenCard, chosenCategory);
                                }
                                //else
                                else {
                                    //just play the card
                                    game.playTurn(chosenCard);
                                }
                                //change card label
                                Card lastPlayedCard = game.getLastPlayedCard();
                                TurnUpdate.updateCard(cardLabel, lastPlayedCard);
                                //update log
                                TurnUpdate.updateLog(logPane, userName + " played " + lastPlayedCard.getTitle());
                                //update hand
                                new HandView(cardContainer).showCards();
                            }
                            //else (chosen card not playable)
                            else {
                                //alert as such
                                JOptionPane.showMessageDialog(null, potentialCard.getTitle() + " can't play on that card.");
                            }
                        }
                    //else (has no playable)
                }
                //if player has won (as a result of this turn)
                if (game.hasWon(user)) {
                    //alert user
                    JOptionPane.showMessageDialog(null, userName + " has won!");
                    //if game is over
                    if (game.isOver()) {
                        //alert user
                        JOptionPane.showMessageDialog(null, "That's the end of the game! Thank you for playing.");
                        //back to main
                        new GoToMain(parentContainer).showMainScreen();
                        return;
                    }
                }
                //if trump was played
                Card lastPlayed = game.getLastPlayedCard();
                if (lastPlayed != null && lastPlayed.isTrump()){
                    TurnUpdate.updateLog(logPane, "Supertrump Played! All players in");
                    game.resetNumPasses();
                    game.setAllPlayersIn();
                }
            }
            //else (user is out)
        }
        //update log with asterisks
        TurnUpdate.updateLog(logPane, "############################");
        TurnUpdate.updateLog(logPane, "Let's Go! It's " + game.getCurrentPlayer().getName() + "'s turn!");
        new ActivateButtons(controlButtons, viewTurnButton).setActiveButtons(game.getCurrentPlayer());
    }

    private String getCategoryChoice() {
        String[] choices = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};

        String categoryChoice = (String) JOptionPane.showInputDialog(null, "Choose your trump category: ",
                "Trump Category", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice

        return categoryChoice.replaceAll("\\s+", "").toLowerCase();
    }

    public Card getCurrentCard() {
        String currentCardTitle = "";
        Component[] allCards = cards.getComponents();
        for (Component component :
                allCards) {
            //if we've got the jpanel that's currently showing
            if (component.isVisible()){
                JPanel card = (JPanel) component;
                currentCardTitle = card.getName();
            }
        }
        currentCard = Game.currentGame.getCurrentPlayer().getCardByTitle(currentCardTitle);
        return currentCard;
    }
}
