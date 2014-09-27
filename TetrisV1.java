//======================================================\\
// Author: Hunter Quant
// Email: hunterdquant@gmail.com
// GitHub: https://www.github.com/hunterdquant
// Description: plays the game of Tetris
//======================================================\\

import javax.swing.*;

public class TetrisV1 {
  
    public static void main(String [] args) {
        
        JFrame window = new JFrame("TetrisV1");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tetris tetris = new Tetris();
        tetris.initialize();

        window.add(tetris);
        window.pack();
        window.setVisible(true);
    }
}

