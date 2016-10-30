package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Class moves forward through next JPanels in instructions CardLayout
 * Created by Draga on 8/10/2016.
 */
class NextInstructions implements ActionListener {
    private CardLayout instructionsLayout;
    private JPanel parentPanel;

    NextInstructions(JPanel instructionsCard){
        parentPanel = instructionsCard;
        instructionsLayout = (CardLayout) instructionsCard.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        instructionsLayout.next(parentPanel);

    }
}
