package gui;

import javax.swing.*;
import java.awt.*;

/**Class clears any previous gameplay elements from the initial play screen, ready for a new game to commence
 * Created by Draga on 22/10/2016. */
class EmptyGui {
  EmptyGui(JPanel playerContainer, JTextPane logTextPane, JLabel playCardLabel) {

    for (Component component: playerContainer.getComponents()) {
        if (component != null){
          playerContainer.remove(component);
        }
    }

    logTextPane.setText("");
    playCardLabel.setText(MineralSupertrumps.CATEGORY_STRING);
    playCardLabel.setIcon(new ImageIcon("src/GUI/images/cards/Slide66.jpg"));
  }
}
