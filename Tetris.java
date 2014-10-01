//======================================================\\
// Author: Hunter Quant
// Email: hunterdquant@gmail.com
// GitHub: https://www.github.com/hunterdquant
// Description: Tetris class
//======================================================\\

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tetris extends JPanel implements KeyListener {
    
    

    boolean gameOver = false;
    private boolean right = false;
    private boolean left = false;
    private boolean down = false;
    private boolean space = false;

    private int score = 0;
    private int lines = 0;
    private int level = 0;

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


    JLabel scoreLabel = new JLabel("SCORE: 0");
    JLabel levelLabel = new JLabel("LEVEL: 0");

    //Initializes the JPanel   
    public void initialize() {

        //Set up window properties
        this.setPreferredSize(new java.awt.Dimension(240, 510));
        this.setBackground(Color.BLUE);

        setLayout(null);

        scoreLabel.setForeground(Color.white);
        scoreLabel.setBounds(10, 475, 200, 30);
        add(scoreLabel);

        levelLabel.setForeground(Color.white);
        levelLabel.setBounds(170, 475, 200, 30);
        add(levelLabel);
        
        repaint();
    }

    //Draws square cell to screen
    public void drawCell(int x, int y) {
        
        //Sets cell to occupied
        gameBoard[x][y] = 1;
    }

    //Draws a token to the screen
    public void drawToken(int x, int y, int [] xArray, int [] yArray) {
    
        for (int i = 0; i < 4; i++) {

            drawCell(x+xArray[i], y+yArray[i]);
        }
    }

    //Erases cell at x and y
    public void eraseCell(int x, int y) {
        
        //Sets cell to empty
        gameBoard[x][y] = 0;
    }

    public void eraseToken(int x, int y, int [] xArray, int [] yArray) {
        
        for (int i = 0; i < 4; i++) {

            eraseCell(x+xArray[i], y+yArray[i]);
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

    public void scoreTally(int [] complete) {
    
        int points = 10;
        for (int i = 0; i < complete.length; i++) {
            
            if (complete[i] == 1) {
                
                lines += 1;
                score += points;
                points *= 2;
            }
        }

        level = lines/3;
        if (level > 30) {
            
            lines = 0;
            level = 0;
        }

        scoreLabel.setText("SCORE: " + score);
        levelLabel.setText("LEVEL: " + level);
    }

    public void gameOverMessage() {
        
        JLabel gameOver = new JLabel("NOOB");
        
        gameOver.setForeground(Color.white);
        gameOver.setBounds(100, 475, 200, 30);
        add(gameOver);
        repaint();
    }

    public void checkForComplete() {
    
        int [] complete = new int[20];
        
        //rows
        for (int y = 0; y < 20; y++) {
        
            int cell = 0;

            //Columns
            for (int x = 0; x < 10; x++) {

                if (gameBoard[x][y] == 1) cell++;
                if (cell == 10) complete[y] = 1;
            }
        }

        clearRow(complete);
        shiftDown(complete);
        scoreTally(complete);
    }

    public void clearRow(int [] complete) {
        for (int blink = 0; blink < 5; blink++) {
            for (int y = 0; y < complete.length; y++) {

                if (complete[y] == 1) {

                    for (int x = 0; x < 10; x++) {

                        gameBoard[x][y] = 1 - gameBoard[x][y];
                    }
                }
            }

            repaint();
            try { Thread.sleep(100); } catch (Exception ignore) {}

        }
    }

    public void shiftDown(int [] complete) {
    
        for (int i = 0; i < complete.length; i++) {
        
            if (complete[i] == 1) {
            
                for (int y = i; y >= 1; y--) {
                    
                    for (int x = 0; x < 10; x++) gameBoard[x][y] = gameBoard[x][y-1];
                }
            }
        }
    }

    public void fallingToken() {

        int x = 5;
        int y = 0;
        int tokNum, rotNum;

        tokNum = (int) (7*Math.random());
        rotNum = (int) (4*Math.random());

        //Get the data for the token
        int [] xArray = xRotations[tokNum][rotNum];
        int [] yArray = yRotations[tokNum][rotNum];
        
        if (!isPosValid(x, y, tokNum, rotNum)) {
            
            gameOver = true;
            drawToken(x, y, xArray, yArray);
            repaint();

            return;
        }

        //Draw and repaint
        drawToken(x, y, xArray, yArray);
        repaint();
        
        int frame = 0;
        boolean hitFloor = false;
        
        //While you haven't hit the floor
        while (!hitFloor) {
        
            try{Thread.sleep(50); } catch (Exception ignore) {}

            //Erase the token
            eraseToken(x, y, xArray, yArray);
            repaint();

            if (left && isPosValid(x-1, y, tokNum, rotNum)) x -= 1;
            if (right && isPosValid(x+1, y, tokNum, rotNum)) x += 1;
            if (down && isPosValid(x, y+1, tokNum, rotNum)) y += 1;
            if (space && isPosValid(x, y, tokNum, (rotNum+1)%4)) {
                
                rotNum = (rotNum+1) % 4;
                xArray = xRotations[tokNum][rotNum];
                yArray = yRotations[tokNum][rotNum];

                space = false;
            }
            
            int n = 31 - level;
            if (frame % n == 0) y += 1;
            if (!isPosValid(x, y, tokNum, rotNum)) { 
                
                y -= 1; 
                hitFloor = true; 
            }
            
            //Re-draw
            drawToken(x, y, xArray, yArray);
            repaint();

            frame++;
        }
    }
    
    //Key pressed
    public void keyPressed (KeyEvent event) {
    
        if (event.getKeyCode() == 37) left = true;
        if (event.getKeyCode() == 39) right = true;
        if (event.getKeyCode() == 40) down = true;
        if (event.getKeyCode() == 32) space = true;
    }

    //Key release
    public void keyReleased (KeyEvent event) {
    
        if (event.getKeyCode() == 37) left = false;
        if (event.getKeyCode() == 39) right = false;
        if (event.getKeyCode() == 40) down = false;
        if (event.getKeyCode() == 32) space = false;
    }


    public void keyTyped (KeyEvent event) {
    
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


