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
import java.util.Calendar;
import javax.swing.JFrame;

/**
 *
 * @author Jach
 */
public class RelojAnalogico extends JFrame {

    boolean condicionSegundero = true;

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
        fullMarcas(grphcs);

        Thread hiloMinuto = new Thread(() -> {
            movimientoMinutero();
        });
        Thread hiloSegundo = new Thread(() -> {
            movimientoSegundero();
        });
        Thread hiloHora = new Thread(() -> {
            movimientoHora();
        });

        hiloHora.start();
        hiloMinuto.start();
        hiloSegundo.start();

    }

    public void ciruclo(Graphics g) {
        g.setColor(Color.WHITE);
        int x = this.getWidth() / 4;
        int y = this.getHeight() / 4;
        g.fillOval(x, y, 2 * x, 2 * y);
    }

    public void fullMarcas(Graphics g) {
        for (int i = 0; i < 360; i += 6) {
            marcaSegundero(g, i);
        }
        for (int i = 0; i < 360; i += 30) {
            marcaHora(g, i);
        }
        fullMarcaNumeros(g);

    }

    public void fullMarcaNumeros(Graphics g) {
        marcaNumero(g, 0, "3");
        marcaNumero(g, 30, "4");
        marcaNumero(g, 60, "5");
        marcaNumero(g, 90, "6");
        marcaNumero(g, 120, "7");
        marcaNumero(g, 150, "8");
        marcaNumero(g, 180, "9");
        marcaNumero(g, 210, "10");
        marcaNumero(g, 240, "11");
        marcaNumero(g, 270, "12");
        marcaNumero(g, 300, "1");
        marcaNumero(g, 330, "2");
    }

    public void marcaNumero(Graphics g, int angulo, String hora) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 140);
        g.setColor(Color.BLACK);
        g.drawString(hora, destino.x, destino.y);
    }

    public void marcaHora(Graphics g, int angulo) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 125);
        origen = getSegundoPunto(origen.x, origen.y, angulo, 110);
        g.setColor(Color.BLACK);
        g.drawLine(origen.x, origen.y, destino.x, destino.y);

    }

    public void marcaSegundero(Graphics g, int angulo) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 125);
        origen = getSegundoPunto(origen.x, origen.y, angulo, 120);
        g.setColor(Color.BLACK);
        g.drawLine(origen.x, origen.y, destino.x, destino.y);

    }

    public void movimientoSegundero() {
        Graphics g = this.getGraphics();
        while (condicionSegundero) {
            try {
                Calendar s = Calendar.getInstance();
                int seg = s.get(Calendar.SECOND);
                dibujaSegundero(g, (seg * 6) - 90, Color.RED);
                Thread.sleep(1000);
                dibujaSegundero(g, (seg * 6) - 90, Color.WHITE);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void movimientoMinutero() {
        Graphics g = this.getGraphics();
        while (condicionSegundero) {
            try {
                Calendar m = Calendar.getInstance();
                int min = m.get(Calendar.MINUTE);
                dibujaMinutero(g, (min * 6) - 90, Color.BLACK);
                Thread.sleep(1000);
                dibujaMinutero(g, (min * 6) - 90, Color.WHITE);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void movimientoHora() {
        Graphics g = this.getGraphics();
        while (condicionSegundero) {
            try {
                Calendar h = Calendar.getInstance();
                int hour = h.get(Calendar.HOUR);
                dibujaHora(g, (hour * 30)-90 , Color.BLACK);
                System.out.println(hour);
                Thread.sleep(1000);
                dibujaHora(g, (hour * 30)-90 , Color.WHITE);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void dibujaHora(Graphics g, int angulo, Color color) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 40);
        g.setColor(color);
        g.drawLine(origen.x, origen.y, destino.x, destino.y);

    }

    public void dibujaMinutero(Graphics g, int angulo, Color color) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 80);
        g.setColor(color);
        g.drawLine(origen.x, origen.y, destino.x, destino.y);

    }

    public void dibujaSegundero(Graphics g, int angulo, Color color) {
        Point origen = new Point();
        origen.x = 250;
        origen.y = 250;
        Point destino;
        destino = getSegundoPunto(origen.x, origen.y, angulo, 100);
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
