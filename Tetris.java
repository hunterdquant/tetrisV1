//======================================================\\
// Author: Hunter Quant
// Email: hunterdquant@gmail.com
// GitHub: https://www.github.com/hunterdquant
// Description: Tetris class
//======================================================\\

import javax.swing.*;
import java.awt.*;

public class Tetris extends JPanel {

    private int [][] gameBoard = new int[10][20];   

    //Initializes the JPanel   
    public void initialize() {

        //Set up window properties
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(Color.GREEN);
    }

    //Draws square cell to screen
    public void drawCell(Graphics g, int x, int y) {

        gameBoard[x][y] = 1;
    }

    //Erases cell at x and y
    public void eraseCell(Graphics g, int x, int y) {

        gameBoard[x][y] = 0;
    }

    //Draws a token to the screen
    public void drawToken(Graphics g, int x, int y) {

        drawCell(g, x+0, y+0);
        drawCell(g, x+0, y+1);
        drawCell(g, x+1, y+0);
        drawCell(g, x+2, y+0);
    }

    //Draws a red cell with a black border
    public void paint(Graphics g) {

        super.paint(g);
        
        //Fill the board with black if 0, red if 1
        for(int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                
                //Draw red
                if (gameBoard[x][y] == 1) {

                    g.setColor(Color.BLACK);
                    g.fillRect(x*24, y*24, 24, 24);
                    g.setColor(Color.RED);
                    g.fillRect(x*24+1, y*24+1, 22, 22);

                //Draw black
                } else {

                    g.setColor(Color.BLACK);
                    g.fillRect(x*24, y*24, 24, 24);
                }
            }
        }    
    }
}

