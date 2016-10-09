package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 8/10/2016.
 */
public class BackInstructions implements ActionListener {
    JPanel parentPanel;
    CardLayout instructionsLayout;

    public BackInstructions(JPanel instructionsCard) {
        parentPanel = instructionsCard;
        instructionsLayout = (CardLayout) instructionsCard.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        instructionsLayout.previous(parentPanel);
    }
}
