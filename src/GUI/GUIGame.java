package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Draga on 7/10/2016.
 */
public class GUIGame extends JFrame {

    JLabel headLabel;
    JTextField username;
    JLabel infoLabel;
    JButton play, instructions, quit;

    GUIGame(){
        //Generals
        super("Mineral Supertrumps");
        setBounds(300, 300, 502, 382);

        Dimension smallSep = new Dimension(0,8);
        Dimension medSep = new Dimension(0,20);
        Dimension largeSep = new Dimension(0,30);

//        FlowLayout newLayout = new FlowLayout();
//        GridLayout newLayout = new GridLayout(0,1,0,50);
//        newLayout.setAlignment(FlowLayout.CENTER);
        BoxLayout newLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(newLayout);
        add(Box.createRigidArea(medSep));

        //Heading
        headLabel = new JLabel("Welcome to Mineral Supertrumps");
        Font headFont = new Font("Arial", Font.PLAIN, 28);
        headLabel.setFont(headFont);
        headLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(headLabel);

        add(Box.createRigidArea(medSep));

        //UsernameField
        username = new JTextField("Username must be at least one character", 40);
        username.setMaximumSize(new Dimension(300, 25));
        Font labelFont = new Font("Arial", Font.ITALIC, 11);
        username.setFont(labelFont);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(username);

        add(Box.createRigidArea(smallSep));

        //InfoLabel
        infoLabel = new JLabel("Enter username to play");
        Font infoFont = labelFont.deriveFont(Font.PLAIN, 14);
        infoLabel.setFont(infoFont);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(infoLabel);

        add(Box.createRigidArea(largeSep));

        //Buttons
        Dimension buttonSize = new Dimension(120, 40);
        play = new JButton("Play");
        play.setMaximumSize(buttonSize);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(play);
        add(Box.createRigidArea(smallSep));

        instructions = new JButton("Instructions");
        instructions.setMaximumSize(buttonSize);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(instructions);
        add(Box.createRigidArea(smallSep));

        quit = new JButton("Quit");
        quit.setMaximumSize(buttonSize);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quit);
        add(Box.createRigidArea(smallSep));


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GUIGame myGame = new GUIGame();
    }

}
