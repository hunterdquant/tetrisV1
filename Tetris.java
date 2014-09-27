//======================================================\\
// Author: Hunter Quant
// Email: hunterdquant@gmail.com
// GitHub: https://www.github.com/hunterdquant
// Description: Tetris class
//======================================================\\

import javax.swing.*;
import java.awt.*;

public class Tetris extends JPanel {
     
     //Initializes the JPanel   
     public void initialize() {
        
        //Set up window properties
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(Color.GREEN);
     }
     
     //Draws a red cell with a black border
     public void paint(Graphics g) {
        
         super.paint(g);
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, 24, 24);
         g.setColor(Color.RED);
         g.fillRect(1, 1, 22, 22);
     }
}

