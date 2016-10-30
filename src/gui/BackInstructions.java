package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class moves back through previous JPanels in instructions CardLayout
 *
 * Created by Draga on 8/10/2016.
 */
class BackInstructions implements ActionListener {
  private JPanel parentPanel;
  private CardLayout instructionsLayout;

  BackInstructions(JPanel instructionsCard) {
    parentPanel = instructionsCard;
    instructionsLayout = (CardLayout) instructionsCard.getLayout();
  }

  /**
   * Method responds to Back button pushed from instructions, moves back through the instructions
   * cards
   *
   * @param e the click event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    instructionsLayout.previous(parentPanel);
  }
}
