/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class Fin implements Componente{
    Conector arriba;
    /**
     * Color normal del ovalo.
     */
    Color color;
    /**
     * Color que adquiere el ovalo cuando esta seleccionado.
     */
    Color colorSeleccion;
    private boolean selected;
    /**
     * La posicion en x del componente respecto al panel.
     */
    private int x=0;
    /**
     * La posicion en y del componente respecto al panel.
     */
    private int y=0;
    /**
     * El ancho del ovalo.
     */
    private int ancho;
    /**
     * El alto del ovalo.
     */
    private int alto;
    
    private Componente anterior=null;
    private String codigoInterior;
    public Fin(int x, int y){
        this.x=x;
        this.y=y;
        color=Color.CYAN;
        colorSeleccion=Color.BLUE;
        alto =80;
        ancho=(int)(alto*1.618);
        arriba= new Conector(ancho/2,-30,5,Color.BLACK);
    }
    
    @Override
    public String getCodigoInterior() {
        return codigoInterior;
    }

    @Override
    public void setCodigoInterior(String codigo) {
        codigoInterior=codigo;
    }

    @Override
    public void dibujar(Graphics g) {
        arriba.dibujar(g, this);
        if(selected)
            g.setColor(colorSeleccion);
        else g.setColor(color);
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawLine(x+ancho/2, y, x+arriba.x, y+arriba.y);
        g.drawString("Fin", x+ancho/3, y+alto/2);
    }

    @Override
    public String generarCodigo() {
        return "return 0"; //aqui  
    }

    @Override
    public Componente getComponenteFinal() {
        return null;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public Componente getSiguiente() {
        return null;
    }

    @Override
    public Componente getAnterior() {
        return anterior;
    }

    @Override
    public void setSiguiente(Componente c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAnterior(Componente c) {
        anterior=c;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean s) {
        selected=s;
    }

    @Override
    public boolean estaEnArea(Point a, Point b) {
        int minX,minY, maxX, maxY;
        minX=Math.min(a.x, b.x);
        minY=Math.min(a.y, b.y);
        maxX=Math.max(a.x, b.x);
        maxY=Math.max(a.y, b.y);
        
        if(minX<=(x+ancho) && minY<=(y+alto)){ //checa esquina superior izquierda
            if(maxX >= x && maxY>=y){
                return true;
            }
        }
        return false;
    }

    @Override
    public void traslada(int dx, int dy) {
        x+=dx;
        y+=dy;
    }
    
}
