/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojanalogico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Jach
 */
public class RelojAnalogico extends JFrame {

    
    public RelojAnalogico(){
        this.setBounds(0,0, 500, 500);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
       
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RelojAnalogico ra = new RelojAnalogico();
                ra.setLocationRelativeTo(null);
                ra.setVisible(true);
                ra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs); //To change body of generated methods, choose Tools | Templates.
        ciruclo(grphcs);
    }
    
    public void ciruclo(Graphics g){
        g.setColor(Color.WHITE);
        int x= this.getWidth()/4;
        int y= this.getHeight()/4;
        g.fillOval(x,y,2*x,2*y);
    }
    
    
}
