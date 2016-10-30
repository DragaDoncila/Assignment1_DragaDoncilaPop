package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class returns game to main menu as a result of the Main Menu button being clicked Created by
 * Draga on 8/10/2016.
 */
class GoToMain implements ActionListener {

  private final JPanel parentContainer;
  private final CardLayout layout;

  GoToMain(JPanel parentContainer) {
    this.parentContainer = parentContainer;
    this.layout = (CardLayout) parentContainer.getLayout();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    showMainScreen();
  }

  void showMainScreen() {
    layout.show(parentContainer, "mainCard");
  }
}
