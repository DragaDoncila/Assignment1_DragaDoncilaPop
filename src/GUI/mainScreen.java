package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 7/10/2016.
 */
public class mainScreen {
    private JPanel mainPanel;
    private JPanel welcomePanel;
    private JPanel menuPanel;
    private JPanel usernamePanel;
    private JLabel welcomeLabel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JButton playButton;
    private JButton instructionsButton;
    private JButton quitButton;

    public mainScreen() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quitOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Confirm", JOptionPane.YES_NO_OPTION);
                if (quitOption == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame newFrame = new JFrame("Mineral Supertrumps");
        newFrame.setContentPane(new mainScreen().mainPanel);
        newFrame.setBounds(100, 100, 650, 485);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);


    }

}
