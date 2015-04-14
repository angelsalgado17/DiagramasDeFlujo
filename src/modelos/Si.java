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

    public Si(int x, int y) {
        super(x, y);
        arriba= new Conector(ancho/2, -30, 5, Color.BLACK);
        abajo= new Conector(ancho/2, alto+60,5, Color.BLACK);//este va a ser variable
        conectoresInternos= new Conector[2];
        conectoresInternos[0]= new Conector(ancho/2, alto+30, 5, Color.BLACK);//El conector de si
        conectoresInternos[1]= new Conector(ancho+30,alto/2, 5, Color.BLACK); //El conector de no, tambien sera variable
        componentesInternos= new Componente[2];
        
    }

    @Override
    public void dibujar(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAncho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizaConectores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
