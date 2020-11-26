import javax.swing.*;
import java.awt.*;
import cs101.sosgame.SOS;
import java.awt.event.*;

/**
 * sets up the panel
 * adds labels
 */
public class SOSGUIPanel extends JPanel
{
    // Properties
    SOS             game;
    JPanel          panel;
    JLabel          player1;
    JLabel          player2;
    JRadioButton    s;
    JRadioButton    o;
    ButtonGroup     buttonGroup;
    int             player1Score;
    int             player2Score;

    // Strings to add to the label
    String          player1ScoreString;
    String          player2ScoreString;

    MyRadioListener radioListener;
    char            choice;
    MyMouseListener mouseListener;
    int             turn;
    String          name1;
    String          name2;

    // Constructors
    public SOSGUIPanel( SOS game, String name1, String name2)
    {
        this.game    = game;
        this.name1   = name1;
        this.name2   = name2;
        player1Score = 0;
        player2Score = 0;
        choice       = 'S';

        turn         = game.getTurn();

        setLayout(new FlowLayout());

        // Setting up the grid on the GUIPanel
        panel = new SOSCanvas( game );
        add( panel );
        mouseListener = new MyMouseListener();
        panel.addMouseListener( mouseListener);

        panel.setBackground(Color.red);
        panel.setLayout(new FlowLayout());


        // player 1 label to add to the panel
        player1ScoreString = "" + player1Score;
        player1 = new JLabel( name1 + ": " + player1ScoreString);
        player1.setOpaque( true);
        if ( turn == 1 )
            player1.setBackground( Color.cyan);
        else
            player1.setBackground( Color.white);
        add( this.player1);



        // Setting up the radio buttons on the GUIPanel
        s = new JRadioButton( "S");
        o = new JRadioButton( "O");
        buttonGroup = new ButtonGroup();
        buttonGroup.add( s);
        buttonGroup.add( o);
        add( s);
        add( o);
        radioListener = new MyRadioListener();
        s.addActionListener( radioListener);
        o.addActionListener( radioListener);

        // Setting up the player2 label on the GUIPanel
        player2ScoreString = "" + player2Score;
        player2 = new JLabel( name2 + ": " + player2ScoreString);
        player2.setOpaque( true);
        if ( turn == 2 )
            player2.setBackground( Color.cyan);
        else
            player2.setBackground( Color.white);
        add( player2);

        //********************************//

        // setting panel size
        setPreferredSize( new Dimension( 400, 400));
    }

    // Inner-class to represent the mouse listener event
    private class MyMouseListener extends MouseAdapter
    {
        public void mousePressed( MouseEvent event)
        {
            Point point = event.getPoint();

            int MAX = 250;
            int row =  point.y / (( MAX / game.getDimension()) + 1);
            int col =  point.x / (( MAX / game.getDimension()) + 1);

            game.play( choice, row, col);

            // score updating in the labels
            //strings required
            player1Score = game.getPlayerScore1();
            player1ScoreString = "" + player1Score;
            player1.setText( name1 + ": " + player1ScoreString);
            player2Score = game.getPlayerScore2();
            player2ScoreString = "" + player2Score;
            player2.setText( name2 + ": " + player2ScoreString);


            //********************************//
            //setting up turns//

            // Change Turn
            turn = game.getTurn();
            if ( turn == 1 )
            {
                player1.setBackground( Color.cyan);
                player2.setBackground( Color.white);
            }
            else
            {
                player1.setBackground( Color.white);
                player2.setBackground( Color.cyan);
            }

            //********************************//

            panel.repaint();

            if ( game.isGameOver() )
            {
                if ( player1Score > player2Score )
                {
                    JOptionPane.showMessageDialog( panel, name1 + " is the winner!");
                }
                else if ( player1Score < player2Score )
                {
                    JOptionPane.showMessageDialog( panel, name2 + " is the winner!");
                }
                else
                {
                    JOptionPane.showMessageDialog( panel, "Game tied!");
                }
            }
        }
    }

    // Inner-class to represent the selection of Radio Button
    private class MyRadioListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e)
        {
            Object source = e.getSource();

            if ( source == s )
            {
                choice = 's';
            }
            else
            {
                choice = 'o';
            }
        }
    }
}
