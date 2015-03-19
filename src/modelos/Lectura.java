
package modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class Lectura implements Componente{
    private Conector abajo;
    private Conector arriba;
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
    private int x=0;
    /**
     * La posicion en y del componente respecto al panel.
     */
    private int y=0;
    /**
     * El ancho del rectangulo.
     */
    private int ancho;
    /**
     * El alto del rectangulo.
     */
    private int alto;
    /**
     * Describe los pixeles de diferencia entre un rectangulo normal y el 
     * romboide que se dibujara, entre mayor sea su valor mas inclinado 
     * sera.
     * 
     */
    private int inclinacion;
    /**
     * Contiene la direccion del siguiente componente, es decir, el que esta 
     * conectado abajo de el y que se ejecutaria despues de este componente.
    */
    private Componente siguiente;
    /**
     * La direccion del componente anterior, es decir, el que esta conectado 
     * arriba de el y se ejecuto antes que este componente.
     */
    private Componente anterior;
    
    private String codigoInterior;

    public Lectura(int x, int y){
        this.x=x;
        this.y=y;
        color=Color.YELLOW;
        colorSeleccion=Color.BLUE;
        alto=80;
        ancho=(int)(1.618*80);
        arriba= new Conector(ancho/2, -30, 5, Color.BLACK);
        abajo= new Conector(ancho/2, alto+30,5, Color.BLACK);
        inclinacion=15;
    }
    /**
     * Aqui va lo que el usuario escribio como codigo, me imagino solamente una 
     * lista de variables separadas por comas, que son las que se leeran.
     * @return el codigo que el usuario escribio.
     */
    @Override
    public String getCodigoInterior() {
        return codigoInterior;
    }
    /**
     * Aqui va lo que el usuario escribio como codigo, me imagino solamente una 
     * lista de variables separadas por comas, que son las que se leeran.
     * @param codigo El codigo a asignarle.
     */
    @Override
    public void setCodigoInterior(String codigo) {
        codigoInterior=codigo;
    }
    
    @Override
    public void dibujar(Graphics g) {
        arriba.dibujar(g, this);
        abajo.dibujar(g, this);
        g.drawLine(x+arriba.x,y+ arriba.y, x+ancho/2, y);
        g.drawLine(x+abajo.x, y+abajo.y, x+ancho/2, y+alto);
        if(selected)
            g.setColor(colorSeleccion);
        else g.setColor(color);
        int px[]=new int[4];
        int py[]=new int[4];
        px[0]=x+inclinacion;
        py[0]=y;
        px[1]=x+ancho;
        py[1]=y;
        px[2]=x+ancho-inclinacion;
        py[2]=y+alto;
        px[3]=x;
        py[3]=y+alto;
        g.fillPolygon(px, py, 4);
        
    }
    
    @Override
    public String generarCodigo() {
        //aqui aun no se como cambiaremos de lista de variables a puros scanf leyento esas variables
        //creo que hay que hacer un objeto variable, y guardarlo en el inicio o algo asi
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}