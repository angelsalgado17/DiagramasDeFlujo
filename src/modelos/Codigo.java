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
public class Codigo implements Componente {

    Conector abajo;
    Conector arriba;
    /**
     * Color normal del rectangulo.
     */
    Color color;
    /**
     * Color que adquiere el rectangulo cuando esta seleccionado.
     */
    Color colorSeleccion;
    private boolean selected;
    /**
     * La posicion en x del componente respecto al panel.
     */
    int x=0;
    /**
     * La posicion en y del componente respecto al panel.
     */
    int y=0;
    /**
     * El ancho del rectangulo.
     */
    int ancho;
    /**
     * El alto del rectangulo.
     */
    int alto;
    /**
     * Contiene la direccion del siguiente componente, es decir, el que esta 
     * conectado abajo de el y que se ejecutaria despues de este componente.
    */
    public Componente siguiente;
    /**
     * La direccion del componente anterior, es decir, el que esta conectado 
     * arriba de el y se ejecuto antes que este componente.
     */
    public Componente anterior;
    
    private String codigoInterior;
    public Codigo(int x, int y){
        this.x=x;
        this.y=y;
        color= Color.GREEN;
        colorSeleccion=Color.BLUE;  
        alto=80;
        ancho=(int)(1.618*alto);
        arriba= new Conector(ancho/2, -30, 5, Color.BLACK);
        abajo= new Conector(ancho/2, alto+30,5, Color.BLACK);
    }
    @Override
    public void dibujar(Graphics g) {
        arriba.dibujar(g, this);
        abajo.dibujar(g, this);
        if(selected)
            g.setColor(colorSeleccion);
        else g.setColor(color);
        
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawString((codigoInterior==null)?"": codigoInterior, x, y);
        g.drawLine(x+arriba.x, y+arriba.y, x+ancho/2, y);
        g.drawLine(x+abajo.x, y+ abajo.y, x+ancho/2, y+alto);
    }

    @Override
    public String generarCodigo() {
        // aqui se va a hacer todo el parseo y ese pedo del JAVACC y asi
        //por mientras supondre que el usuario ya metio el codigo en lenguaje C
        return codigoInterior;
    }

    @Override
    public Componente getComponenteFinal() {
        if(siguiente==null)return null;
        Componente aux=siguiente;
        while(aux.getSiguiente()!=null){
            aux=aux.getSiguiente();
        }
        return aux;
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
        return siguiente;
    }

    @Override
    public Componente getAnterior() {
        return anterior;
    }

    @Override
    public void setSiguiente(Componente c) {
        siguiente=c;
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

    @Override
    public String getCodigoInterior() {
        return codigoInterior;
    }

    @Override
    public void setCodigoInterior(String codigo) {
        codigoInterior=codigo;
    }
    
}
