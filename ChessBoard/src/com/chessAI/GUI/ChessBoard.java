package com.chessAI.GUI;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {

    public void paint(Graphics gp)
    {
        gp.setColor(Color.white);
        gp.fillRect(10, 10, 400, 400);
        gp.setColor(Color.BLACK);
        for(int x=10; x<=370; x+=100)
        {

            for(int y=10; y<=370; y+=100)
            {
                gp.fillRect(x, y, 50, 50);
            }
        }


       for(int x=60; x<=370; x+=100)
        {

            for(int y=60; y<=370; y+=100)
            {
                gp.fillRect(x, y, 50, 50);
            }
        }

    }

    public static void main(String[] args){
        JFrame jf = new JFrame();
        jf.setSize(600,600);
        jf.setTitle("Chess Board");
        jf.getContentPane().add(new ChessBoard());
        jf.setLocationRelativeTo(null);
        jf.setBackground(Color.gray);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }


}
