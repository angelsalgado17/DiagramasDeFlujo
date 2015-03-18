/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public interface Componente {
    /**
     * Contiene la direccion del siguiente componente, es decir, el que esta 
     * conectado abajo de el y que se ejecutaria despues de este componente.
    */
    public Componente siguiente=null;
    /**
     * Contiene la direccion del componente anterior, es decir, el que esta 
     * conectado arriba de este, y que se ejecuto justo antes que este.
     */
    public Componente anterior=null;
    /**
     * Contiene el codigo que el usuario escribio.
     * el codigo depende de el tipo de componente que sea
     * @return El codigo que el usuario escribio
     */
    public String getCodigoInterior();
    /**
     * Asigna un codigo escrito por el usuario.
     * @param codigo 
     */
    public void setCodigoInterior(String codigo);
    
    public void  dibujar(Graphics g);
    /**
     * Convierte el componente en su respectivo codigo en C. 
     * @return 
     */
    public String generarCodigo();
    /**
     * 
     * @return 
     */
    public Componente getComponenteFinal();
    @Override
    public String toString();
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Componente getSiguiente();
    public Componente getAnterior();
    public void setSiguiente(Componente c);
    public void setAnterior(Componente c);
    public boolean isSelected();
    public void setSelected(boolean s);
    public boolean estaEnArea(Point a, Point b);
    public void traslada(int dx, int dy);
}
