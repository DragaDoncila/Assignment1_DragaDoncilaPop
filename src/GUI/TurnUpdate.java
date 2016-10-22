package GUI;

import Cards.Card;
import Game.Game;

import javax.swing.*;

/**
 * Created by Draga on 20/10/2016.
 */
public class TurnUpdate {

    public static void updateCard(JLabel categoryLabel, Card lastPlayedCard) {
        Game game = Game.currentGame;
        ImageIcon cardImage = new ImageIcon("src/GUI/images/cards/"+ lastPlayedCard.getFileName());
        categoryLabel.setIcon(cardImage);
        String detailString = game.getCurrentCategory().toString();
        categoryLabel.setText(MineralSupertrumps.CATEGORY_STRING + detailString);
    }

    public static void updateLog(JTextPane logTextPane, String message) {
        String currentText = logTextPane.getText();
        String newText = currentText + "\n" + message + "\n";
        logTextPane.setText(newText);
    }
}
