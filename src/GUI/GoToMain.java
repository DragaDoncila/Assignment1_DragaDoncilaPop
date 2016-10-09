package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 8/10/2016.
 */
public class GoToMain implements ActionListener {

    private final JPanel parentContainer;
    private final CardLayout layout;

    public GoToMain(JPanel parentContainer) {
        this.parentContainer = parentContainer;
        this.layout = (CardLayout) parentContainer.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        layout.show(parentContainer, "mainCard");
    }
}
