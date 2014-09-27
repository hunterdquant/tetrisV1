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
        { {0,0,1,2}, {0,0,0,1}, {2,0,1,2}, {0,1,1,1} },
        { {0,0,1,1}, {1,2,0,1}, {0,0,1,1}, {1,2,0,1} },
        { {1,1,0,0}, {0,1,1,2}, {1,1,0,0}, {0,1,1,2} },
        { {0,1,2,2}, {0,1,0,0}, {0,0,1,2}, {1,1,0,1} },
        { {1,0,1,2}, {1,0,1,1}, {0,1,1,2}, {0,0,1,0} },
        { {0,1,0,1}, {0,1,0,1}, {0,1,0,1}, {0,1,0,1} },  
        { {0,1,2,3}, {0,0,0,0}, {0,1,2,3}, {0,0,0,0} }
    };
    private int [][][] yRotations = {
        { {0,1,0,0}, {0,1,2,2}, {0,1,1,1}, {0,0,1,2} },
        { {0,1,1,2}, {0,0,1,1}, {0,1,1,2}, {0,0,1,1} },
        { {0,1,1,2}, {0,0,1,1}, {0,1,1,2}, {0,0,1,1} },
        { {0,0,0,1}, {0,0,1,2}, {0,1,1,1}, {0,1,2,2} },
        { {0,1,1,1}, {0,1,1,2}, {0,0,1,0}, {0,1,1,2} },
        { {0,0,1,1}, {0,0,1,1}, {0,0,1,1}, {0,0,1,1} },
        { {0,0,0,0}, {0,1,2,3}, {0,0,0,0}, {0,1,2,3} }
    };

    //Initializes the JPanel   
    public void initialize() {

        //Set up window properties
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(Color.GREEN);
    }

    //Draws square cell to screen
    public void drawCell(int x, int y) {
        
        //Sets cell to occupied
        gameBoard[x][y] = 1;
    }

    //Erases cell at x and y
    public void eraseCell(int x, int y) {
        
        //Sets cell to empty
        gameBoard[x][y] = 0;
    }

    //Draws a token to the screen
    public void drawToken(int x, int y, int [] xArray, int [] yArray) {
    
        for (int i = 0; i < 4; i++) {

            drawCell(x+xArray[i], y+yArray[i]);
        }
    }

    public boolean isPosValid(int x, int y, int tokNum, int rotNum) {
        
        //Arrays for checking valid positions
        int [] xArray = xRotations[tokNum][rotNum];
        int [] yArray = yRotations[tokNum][rotNum];
        
        for (int i = 0; i < 4; i++) {
           
            //Positions to check 
            int xIndex = x+xArray[i];
            int yIndex = y+yArray[i];
            
            //Check range
            if (xIndex < 0) return false;
            if (xIndex >= 10) return false;
            if (yIndex < 0) return false;
            if (yIndex >= 20) return false;
            
            //Check to see if space is occupied
            if (gameBoard[xIndex][yIndex] == 1) return false;
        }

        return true;
    }

    public void testTokens() {

        try {Thread.sleep(1000);} catch (Exception ignore) {}
        
        //Must be initialize out of scope of the while loop
        int x, y, tokNum, rotNum;

        while (true) {

            //Position to be placed
            x = (int) (10*Math.random());
            y = (int) (20*Math.random());

            //Token number/rotation
            tokNum = (int) (7*Math.random());
            rotNum = (int) (4*Math.random());

            if (isPosValid(x, y, tokNum, rotNum)) break;
        }

        drawToken(x, y, xRotations[tokNum][rotNum], yRotations[tokNum][rotNum]);
        repaint();
    }

    //Draws a red cell with a black border
    public void paint(Graphics g) {

        super.paint(g);
        
        //Fill the board with black if 0, red if 1
        for(int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[0].length; y++) {
                
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

