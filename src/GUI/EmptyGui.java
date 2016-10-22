package GUI;

import javax.swing.*;
import java.awt.*;

/** Created by Draga on 22/10/2016. */
public class EmptyGui {
  public EmptyGui(JPanel playerContainer, JTextPane logTextPane, JLabel playCardLabel) {

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
