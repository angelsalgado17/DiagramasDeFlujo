/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import modelos.Componente;
import modelos.Diagrama;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class PanelDiagrama extends JPanel{
    public Diagrama diagrama;
    /**
     * 
     */
    private Point p1;
    private Point p2;
    private boolean seleccionado;
    public PanelDiagrama(Diagrama d){
        diagrama=d;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        diagrama.dibuja(g);
        
        if(seleccionado){
            int minX,minY, maxX, maxY;
            minX=Math.min(p1.x, p2.x);
            minY=Math.min(p1.y, p2.y);
            maxX=Math.max(p1.x, p2.x);
            maxY=Math.max(p1.y, p2.y);
            g.setColor(Color.BLACK);
            g.drawRect(minX, minY, maxX-minX, maxY-minY);
        }
    }

    /**
     * @return the p1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(Point p1) {
        this.p1 = p1;
    }

    /**
     * @return the p2
     */
    public Point getP2() {
        return p2;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(Point p2) {
        this.p2 = p2;
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
