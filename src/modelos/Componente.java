/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Graphics;

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
     */
    public String codigoInterior=null;
    /**
     * La posicion en x del componente respecto al panel.
     */
    int x=0;
    /**
     * La posicion en y del componente respecto al panel.
     */
    int y=0;
    /**
     * Dibuja el componente en las coordenadas x y y del panel
     * @param g 
     */
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
}
