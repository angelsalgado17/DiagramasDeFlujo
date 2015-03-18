/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class Diagrama {
    /**
     * Contiene todos los componentes que se encuentran en pantalla, si bien 
     * aqui aparecen en un orden, no se garantiza que esten en orden de 
     * ejecucion, si se requiere este orden, se debe iterar sobre los elementos
     * como una lista enlazada.
     */
    private ArrayList<Componente> componentes;
    private Inicio compInicial;
    public boolean haySeleccionado;
    public Diagrama(){
        componentes= new ArrayList<>();
    }
    /**
     * Establece como seleccionados a todos los componentes que esten en el area
     * formada por el rectangulo cuya esquina superior derecha es p1 y la 
     * esquina inferior izquierda es p2.
     * @param p1 El punto de la esquina superior izquierda del area a seleccionar
     * @param p2 El punto de la ezquina inferior derecha del area a seleccionar
     * @return true si selecciono alguno de los componentes, falso si no.
     */
    public boolean seleccionar(Point p1, Point p2){
        haySeleccionado=false;
        for (Componente componente : componentes) {
            if(componente.estaEnArea(p1, p2)){
                componente.setSelected(true);
                haySeleccionado=true;
            }else componente.setSelected(false);
        }
        return haySeleccionado;
    }
    /**
     * Confirma que se este dando clic a uno de los componentes que previamente 
     * han sido seleccionados sin alterar la seleccion actual.
     * @param p1
     * @param p2
     * @return 
     */
    public boolean confirmaSeleccion(Point p1, Point p2){
        for (Componente componente : componentes) {
            if(componente.isSelected() && componente.estaEnArea(p1, p2)){
                return true;
            }
        }
        return false;
    }
    /**
     * Agrega un componente al diagrama.
     * @param comp El componente a agregar al diagrama.
     * @return false si se agrega un componente Inicio y este ya existe, o si se agrega otro componente de fin, se devuelve true en los demas casos.
     */
    public boolean add(Componente comp){
        if(comp instanceof Inicio){ //si se le envia un Inicio y ya existe un inicio, no lo agrega y devuelve false
            if(compInicial==null){
                compInicial= (Inicio)comp;
            }else return false;
        }
        componentes.add(comp);
        return true;
    }
    public void dibuja(Graphics g){
        for (Componente componente : componentes) {
            componente.dibujar(g);
        }
    }
    public void trasladarSeleccionados(int dx, int dy){
        for (Componente componente : componentes) {
            if(componente.isSelected()){
                componente.traslada(dx, dy);
            }
        }
    }
}
