/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Manuel Angel Muñoz S
 */
public class Si extends ComponenteContenedor{
    int romboX[];
    int romboY[];
    public Si(int x, int y) {
        super(x, y);
        arriba= new Conector(ancho/2, -30, 5, Color.BLACK);
        abajo= new Conector(ancho/2, alto+60,5, Color.BLACK);//este va a ser variable
        conectoresInternos= new Conector[2];
        conectoresInternos[0]= new Conector(ancho/2, alto+30, 5, Color.BLACK);//El conector de si
        conectoresInternos[1]= new Conector( ancho+30,alto/2, 5, Color.BLACK); //El conector de no, tambien sera variable
        componentesInternos= new Componente[2];
        romboX= new int[4];
        romboY= new int[4];
    }

    @Override
    public void dibujar(Graphics g) {
        if(selected)
            g.setColor(colorSeleccion);
        else g.setColor(color);
        romboX[0]=x + ancho/2;
        romboY[0]=y;
        romboX[1]=x + ancho;
        romboY[1]=y + alto /2;
        romboX[2]=x + ancho/2;
        romboY[2]=y + alto;
        romboX[3]=x;
        romboY[3]=y + alto /2;
        
        g.fillPolygon(romboX, romboY, 4);
        g.setColor(Color.BLACK);
        
        //conector si
        g.drawLine(romboX[2], romboY[2], x+conectoresInternos[0].x, y + conectoresInternos[0].y); //linea
        conectoresInternos[0].dibujar(g, this); //conector si
        g.drawString("Si", x+conectoresInternos[0].x+20, y+conectoresInternos[0].y);
        Componente aux=null;
        int xIni=0, yIni=0;
        if(componentesInternos[0]!=null){
            aux= componentesInternos[0].getComponenteFinal();
            xIni=aux.getX() + aux.getAbajo().x;
            yIni=aux.getY() + aux.getAbajo().y;
        }else{
            xIni=x+conectoresInternos[0].x;
            yIni=y+conectoresInternos[0].y;
        }
        g.drawLine(xIni, yIni, x+abajo.x, y+abajo.y); //linea del ultimo componente de si al conector abajo
        
        //conector no
        g.drawLine(romboX[1], romboY[1], x+conectoresInternos[1].x, y+ conectoresInternos[1].y); //rombo a conector no
        conectoresInternos[1].dibujar(g, this); //conector si
        g.drawString("No", x+ conectoresInternos[1].x+20, y+conectoresInternos[1].y);
        if(componentesInternos[1]!=null){
            aux= componentesInternos[1].getComponenteFinal();
            xIni=aux.getX() + aux.getAbajo().x;
            yIni=aux.getY() + aux.getAbajo().y;
        }else{
            xIni=x+conectoresInternos[1].x;
            yIni=y+conectoresInternos[1].y;
        }
        g.drawLine(xIni, yIni, xIni, y+abajo.y); //linea del ultimo componente de no al conector abajo, correcta
        //g.drawLine(x+abajo.x, y, x, y);
        
        g.drawLine(x+abajo.x, y+abajo.y, xIni, y+abajo.y);
        
        g.drawLine(x+arriba.x, y+arriba.y,romboX[0], romboY[0]);
        arriba.dibujar(g, this);
        abajo.dibujar(g, this);
    }

    @Override
    public String generarCodigo() {
        StringBuilder codigo= new StringBuilder();
        codigo.append("if(").append(codigoInterior).append("){\n");
        Componente aux= componentesInternos[0];
        String linea;
        while(aux!=null){
            linea=aux.generarCodigo();
            if(linea.length()>0){
                codigo.append("\t").append(linea).append("\n");
            }
        }
        aux= componentesInternos[1];
        codigo.append("} else {");
        while(aux!=null){
            linea=aux.generarCodigo();
            if(linea.length()>0){
                codigo.append("\t").append(linea);
            }
        }
        codigo.append("}\n");
        return codigo.toString();
    }

    @Override
    public int getAlto() {
        int a= getAlturaComponentesInt();
        return abajo.y=conectoresInternos[0].x + a +30 - arriba.y;
    }

    @Override
    public int getAncho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizaConectores() {
        int altoT=getAlturaComponentesInt();
        abajo.y=conectoresInternos[0].y + altoT +30;
        
    }
    private int getAlturaComponentesInt(){
        int altoT=0, aux=0;
        Componente comp=componentesInternos[0];
        while(comp!=null){
            altoT+=comp.getAlto();
            comp=comp.getSiguiente();
        }
        comp=componentesInternos[1];
        while(comp!=null){
            aux+=comp.getAlto();
            comp=comp.getSiguiente();
        }
        return Math.max(altoT, aux);
    }
}
