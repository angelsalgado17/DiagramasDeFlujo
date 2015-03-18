/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelos.Diagrama;
import vista.PanelDiagrama;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class OyentePanel extends MouseAdapter {
    Diagrama diagrama;
    PanelDiagrama panel;
    /**
     * El punto donde se presiono el boton del mouse.
     */
    Point p1;
    /**
     * El punto donde se solto el boton del mouse.
     */
    Point p2;
    /**
     * Indica si habia uno o mas componentes seleccionados en el primer clic,
     * si es asi, se moveran todos, si no, se seleccionaran mas.
     */
    private boolean selecciono;
    private int ux, uy;
    public OyentePanel(Diagrama d, PanelDiagrama pane){
        diagrama=d;
        panel=pane;
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e); 
        p1=e.getPoint();
        
        System.out.println("selecciono " + selecciono );
        
        if(selecciono){ //primero pregunta si ya hay algo seleccionado y si se esta dando clic a ese algo
            selecciono=diagrama.confirmaSeleccion(p1, p1);
        }
        if(!selecciono){ //si no habia nada seleccionado, empieza a buscar con las coordenadas actuales
            selecciono= diagrama.seleccionar(p1, p1);
        }
        
        if(!selecciono){
            panel.setSeleccionado(true);
            panel.setP1(p1);
            panel.setP2(p1);
            System.out.println("Se seleccionaran varios componentes");
        }else{ //si va a arrastrar un elemento
            ux=p1.x;
            uy=p1.y;
            System.out.println("Se van a arrastrar uno o varios componentes");
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(selecciono){ //si va a arrastrar un elemento
            System.out.println("Arrastrando... ");
            Point act=e.getPoint();
            diagrama.trasladarSeleccionados(act.x - ux, act.y - uy);
            ux=act.x;
            uy=act.y;
        }else{ //si va a seleccionar varios elementos
            System.out.println("Seleccionando");
            p2=e.getPoint();
            //System.out.println("p2: " + p2);
            diagrama.seleccionar(p1, p2); 
            //panel.setSeleccionado(true);
            //panel.setP1(p1);
            panel.setP2(p2);
        }
        panel.repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); 
        p2=e.getPoint();
        selecciono=diagrama.seleccionar(p1, p2);  //para verificar si se seleccionaron todos al final        
        
        panel.setSeleccionado(false);
        System.out.println("Solto");
        panel.repaint();
    }
}
