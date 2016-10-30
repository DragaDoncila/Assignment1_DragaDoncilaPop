package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class moves forward through next JPanels in instructions CardLayout
 *
 * <p>Created by Draga on 8/10/2016.
 */
class NextInstructions implements ActionListener {
  private CardLayout instructionsLayout;
  private JPanel parentPanel;

  NextInstructions(JPanel instructionsCard) {
    parentPanel = instructionsCard;
    instructionsLayout = (CardLayout) instructionsCard.getLayout();
  }

  /**
   * Displays the next card in the Instructions Card Layout container
   *
   * @param e the click event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    instructionsLayout.next(parentPanel);
  }
}
