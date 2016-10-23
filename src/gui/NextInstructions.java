package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 8/10/2016.
 */
public class NextInstructions implements ActionListener {
    CardLayout instructionsLayout;
    JPanel parentPanel;

    NextInstructions(JPanel instructionsCard){
        parentPanel = instructionsCard;
        instructionsLayout = (CardLayout) instructionsCard.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        instructionsLayout.next(parentPanel);

    }
}
