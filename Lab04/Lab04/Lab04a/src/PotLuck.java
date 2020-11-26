/*
 * Author: Egemen Öztürk
 * Lab04a PotLuck
 */

//importing required methods

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PotLuck extends JFrame implements ActionListener {

    private final JPanel panel;
    JLabel    label;
    JButton   but;
    JButton   bomb1Button;
    JButton bomb2Button;
    int       prize = 0;
    int       bomb1 = 0;
    int       bomb2 = 0;
    int       count;
    final int ROW = 1;
    final int COLUMN = 1;

    //Main method
    public static void main(String[] args) {

            //Scanner scan = new Scanner(System.in);
            //ROW = scan.nextInt();
            //COLUMN = ROW;
            PotLuck frame = new PotLuck();
            frame.setVisible(true);
    }

    //building label, button, row, column






    //Constructor
    public PotLuck() {

        //set Method
        setTitle("Guess where I am?");
        setBounds(360, 180, 800, 600);
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(ROW, ROW, COLUMN, COLUMN));
        contentPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add Method panel

        panel = new JPanel();
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(ROW, COLUMN, ROW * 3 , COLUMN * 3));


        //Attempt counter, button builder
        count = 0;

        while (prize == bomb1 ||  prize == bomb2 ||bomb1 == bomb2){
            prize = (int)(Math.random()*(ROW*COLUMN));
            bomb1 = (int)(Math.random()*(ROW*COLUMN));
            bomb2 = (int)(Math.random()*(ROW*COLUMN));
        }
        System.out.println("The price is in the box " + (prize + 1) );
        System.out.println("The bomb1 is in the box " + (bomb1 + 1) );
        System.out.println("The bomb2 is in the box " + (bomb2 + 1) );

        for(int i = 0; i < (ROW * COLUMN); i++){
            if(prize == i){
                but = new JButton();
                but.addActionListener(this);
                panel.add(but );
            }
            else if  (bomb1 == i ){
                bomb1Button = new JButton();
                bomb1Button.addActionListener(this);
                panel.add(bomb1Button);
            }
            else if  (bomb2 == i ){
                bomb2Button = new JButton();
                bomb2Button.addActionListener(this);
                panel.add(bomb2Button);
            }
            else{
                JButton temp = new JButton();
                panel.add(temp);
                temp.addActionListener(this);
            }
        }

        //Won the price Method
        label = new JLabel("You clicked "+count+" times\r\n");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label, BorderLayout.NORTH);
    }

    //Action Performed Method,
    public void actionPerformed( ActionEvent e)
    {
        if ( e.getSource() != but && e.getSource() != bomb1Button && e.getSource() != bomb2Button){
            count ++;
            ((JButton) e.getSource()).setEnabled(false);
            label.setText("YOU CLICKED "+count+" times");
        }

        else if ( e.getSource() == but){
            label.setText("You got the price in " + (count + 1) + " attempts!" );
            for(int i = 0; i < (ROW * COLUMN); i++){
                if(panel.getComponent(i) != but){
                    panel.getComponent(i).setEnabled(false);

                }
            }
        }
        else {
            label.setText("Sorry! You are blown up at attempt " + (count + 1));
            for(int i = 0; i < (ROW*COLUMN); i++){
                if(panel.getComponent(i) != but || panel.getComponent(i) != bomb1Button || panel.getComponent(i) != bomb2Button){
                    panel.getComponent(i).setEnabled(false);

                }
            }
        }
    }
}