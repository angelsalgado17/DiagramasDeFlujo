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
public class Conector extends Point{
    public Color color;
    public int radio;
    /**
     * Crea el punto negro que conecta un componente con otro
     * @param x la coordenada en x respecto al componente
     * @param y la coordenada en y respecto al componente
     * @param radio el radio del punto
     * @param c el color del punto
     */
    public Conector(int x, int y, int radio, Color c){
        super(x,y);
        this.radio=radio;
        color=c;
    }
    /**
     * Dibuja el conector perteneciente al componente indicado.
     * @param g El contexto grafico en el cual dibujar.
     * @param comp El componente que contiene este conector.
     */
    public void dibujar(Graphics g, Componente comp){
        g.setColor(color);
        g.fillOval(comp.getX() + this.x-radio, comp.getY()+y-radio, radio*2, radio*2);
    }
}
