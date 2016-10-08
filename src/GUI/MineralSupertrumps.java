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
    private JPanel rulePanel;
    private JPanel titlePanel;
    private JPanel imagePanel;
    private JPanel navPanel;
    private JLabel ruleLabel;
    private JLabel cardLabel;
    private JButton backButton;
    private JButton nextButton;
    private JPanel instructions2;
    private JPanel titlePanel1;
    private JLabel rule2;
    private JPanel rulePanel2;
    private JPanel imagePanel2;
    private JPanel navPanel2;
    private JLabel supertrump;
    private JButton back1;
    private JButton next1;

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
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout instructionsLayout = (CardLayout) (instructionsCard.getLayout());
                instructionsLayout.next(instructionsCard);
            }
        });
    }

    public static void main(String[] args) {
        JFrame newFrame = new JFrame("Mineral Supertrumps");
        newFrame.setBounds(100, 100, 850, 600);
        newFrame.setContentPane(new MineralSupertrumps().parentContainer);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }
}
