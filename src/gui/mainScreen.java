package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Draga on 7/10/2016.
 */
public class mainScreen extends JFrame{
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
        super("Mineral Supertrumps");
        this.setContentPane(mainPanel);
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
                mainPanel.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        mainScreen newFrame = new mainScreen();
        newFrame.setBounds(100, 100, 650, 485);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);


    }

}
