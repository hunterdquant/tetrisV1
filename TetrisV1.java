//======================================================\\
// Author: Hunter Quant
// Email: hunterdquant@gmail.com
// GitHub: https://www.github.com/hunterdquant
// Description: plays the game of Tetris
//======================================================\\

//Java graphics library
import javax.swing.*;

public class TetrisV1 {
  
    public static void main(String [] args) throws Exception{
        
        //Create new window
        JFrame window = new JFrame("TetrisV1");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Initialize Tetris object
        Tetris tetris = new Tetris();
        tetris.initialize();
        
        //Add Tetris object to the window
        window.add(tetris);
        window.pack();
        window.setVisible(true);

        for (int i = 0; i < 20; i++) tetris.testTokens();
    }
}

