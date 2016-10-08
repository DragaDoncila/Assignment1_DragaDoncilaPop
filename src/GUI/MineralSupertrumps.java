package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 8/10/2016.
 */
public class MineralSupertrumps {
    private JPanel parentContainer;
    private JPanel mainCard;
    private JPanel titleLabel;
    private JPanel usernamePanel;
    private JPanel buttonPanel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JButton playButton;
    private JButton quitButton;
    private JButton instructionsButton;
    private JPanel instructionsCard;
    private JPanel instructions1;

    public MineralSupertrumps() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quitOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Confirm", JOptionPane.YES_NO_OPTION);
                if (quitOption == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout myLayout = (CardLayout) (parentContainer.getLayout());
                myLayout.show(parentContainer, "instructionsCard");
            }
        });
    }

    public static void main(String[] args) {
        JFrame newFrame = new JFrame("Mineral Supertrumps");
        newFrame.setBounds(100, 100, 650, 485);
        newFrame.setContentPane(new MineralSupertrumps().parentContainer);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }
}
