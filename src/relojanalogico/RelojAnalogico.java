/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojanalogico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import sun.java2d.loops.DrawLine;

/**
 *
 * @author Jach
 */
public class RelojAnalogico extends JFrame {

    public RelojAnalogico() {
        this.setBounds(0, 0, 500, 500);
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
        movimientoSegundero(grphcs);
    }

    public void ciruclo(Graphics g) {
        g.setColor(Color.WHITE);
        int x = this.getWidth() / 4;
        int y = this.getHeight() / 4;
        g.fillOval(x, y, 2 * x, 2 * y);

    }

    public void movimientoSegundero(final Graphics g) {
        for (int i = 0; i < 360; i += 6) {
            
            try{
                dibujaSegundero(g, i, Color.BLACK);
                Thread.sleep(100);
                dibujaSegundero(g, i, Color.WHITE);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void dibujaSegundero(Graphics g, int angulo, Color color) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 125);
        g.setColor(color);
        g.drawLine(origen.x, origen.y, destino.x, destino.y);
    }

    public Point getSegundoPunto(int x1, int y1, int angulo, int distancia) {
        Point p = new Point();
        double anguloRadianes = (Math.PI * angulo) / 180;
        double x2 = distancia * Math.cos(anguloRadianes);
        double y2 = distancia * Math.sin(anguloRadianes);
        p.x = (int) Math.round(x2) + x1;
        p.y = (int) Math.round(y2) + y1;
        return p;
    }

}
