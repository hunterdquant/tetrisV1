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
    
    //Rotations for each Token
    //USE: [Token][Rotation]
    private int [][][] xRotations = {
        { {0, 0, 1, 2}, {0, 0, 0, 1}, {2, 0, 1, 2}, {0, 1, 1, 1} },
        { {0, 0, 1, 1}, {1, 2, 0, 1}, {0, 0, 1, 1}, {1, 2, 0, 1} }
    };
    private int [][][] yRotations = {
        { {0, 1, 0, 0}, {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2} },
        { {0, 1, 1, 2}, {0, 0, 1, 1}, {0, 1, 1, 2}, {0, 0, 1, 1} }
    };

    //Initializes the JPanel   
    public void initialize() {

        //Set up window properties
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(Color.GREEN);
        
        drawToken(0, 0, xRotations[0][0], yRotations[0][0]);
        
        drawToken(0, 5, xRotations[0][1], yRotations[0][1]);

        drawToken(0, 10, xRotations[0][2], yRotations[0][2]);
        
        drawToken(0, 15, xRotations[0][3], yRotations[0][3]);

        drawToken(5, 0, xRotations[1][0], yRotations[1][0]);
        
        drawToken(5, 5, xRotations[1][1], yRotations[1][1]);

        drawToken(5, 10, xRotations[1][2], yRotations[1][2]);

        drawToken(5, 15, xRotations[1][3], yRotations[1][3]);
    }

    //Draws square cell to screen
    public void drawCell(int x, int y) {

        gameBoard[x][y] = 1;
    }

    //Erases cell at x and y
    public void eraseCell(int x, int y) {

        gameBoard[x][y] = 0;
    }

    //Draws a token to the screen
    public void drawToken(int x, int y, int [] xArray, int [] yArray) {
    
        for (int i = 0; i < 4; i++) {

            drawCell(x+xArray[i], y+yArray[i]);
        }
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

