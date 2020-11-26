import cs101.sosgame.SOS;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * SOS game main frame that instantiates the two other classes
 * sets up the frame 
 */
public class SOSFrame extends JFrame
{
    public static void main( String[] args)
    {
        // Variables

        new JFrame("SOS game");
        JFrame   frame;
        SOS      game;
        SOSGUIPanel panel;

        JLabel   lab = new JLabel("SOS game");

        // Program Code

        game   = new SOS(5);
        panel  = new SOSGUIPanel( game, "player1", "player2");


        // Setting up the frame
        frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible( true);
    }
}