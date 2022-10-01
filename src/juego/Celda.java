package juego;
import javax.swing.*;

import consumibles.Consumible;

import java.awt.*;

public class Celda extends JLabel{

    protected String imagen;
    protected int tamanio;
    protected boolean ocupada;
    protected Consumible consumible;

    public Celda(String img, int tam){
        imagen = img;
        tamanio = tam;
        ImageIcon ic = new ImageIcon(Celda.class.getResource(imagen));
        Image image = ic.getImage();
        Image newimg = image.getScaledInstance(tam, tam,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);


        this.setIcon(ic);
        ocupada = false;
        consumible = null;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String img) {
        imagen = img;
        ImageIcon ic = new ImageIcon(Celda.class.getResource(imagen));
        Image image = ic.getImage();
        Image newimg = image.getScaledInstance(tamanio, tamanio,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);


        this.setIcon(ic);
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Consumible getConsumible() {
        return consumible;
    }

    public void setConsumible(Consumible consumible) {
        this.consumible = consumible;
    }

    public boolean tieneConsumible(){

        return consumible != null;
    }
}
