package juego;
import javax.imageio.ImageIO;
import javax.swing.*;
import consumibles.Consumible;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("serial")
public class Celda extends JLabel{

    protected String imagen;
    protected int size;
    protected boolean ocupada;
    protected int xenTablero;
    protected int yenTablero;
    
    protected Consumible consumible;

    public Celda( int tam, int x, int y){
        imagen = "";
        size = tam;
        xenTablero = x;
        yenTablero = y;

        ocupada = false;
        consumible = null;
    }

    private void setColorFondo(){
        Random random = new Random();
        int r =  random.nextInt(255);
        int g =  random.nextInt(255);
        int b =  random.nextInt(255);
        this.setIcon(null);
        this.setBackground(new Color(r,g,b));
        this.setOpaque(true);

    }
    public String getImagen() {
        return imagen;
    }

    public void setOcupada(String img){
        ocupada = true;
        if(img.contentEquals("cuerpoP") || img.contentEquals("cabezaP")) setColorFondo();
        else{
            if(xenTablero%2 != yenTablero%2) setImagen("/images/"+img+"1.jpg");
            else setImagen("/images/"+img+"2.jpg");

        }
    }

    public void desocupar(){
        if(consumible!= null) {
            if(xenTablero%2 != yenTablero%2)
                setImagen(consumible.getSkin1());
            else
                setImagen(consumible.getSkin2());
        }
        else{
            if(xenTablero%2 != yenTablero%2) setImagen("/images/celda1.jpg");
            else setImagen("/images/celda2.jpg");

        }
        ocupada = false;
    }

    private void setImagen(String img)  {
        imagen = img;
        ImageIcon ic = new ImageIcon("src"+img);
        Image image = ic.getImage();

        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);




        this.setIcon(ic);



        this.repaint();
    }




    public boolean estaOcupada() {
        return ocupada;
    }



    public Consumible getConsumible() {
        return consumible;
    }

    public void setConsumible(Consumible consumible) {
        this.consumible = consumible;
        if(xenTablero%2 != yenTablero%2) 
        	setImagen(consumible.getSkin1());
        else 
        	setImagen(consumible.getSkin2());
        
    }
    

    
    public int getXenTablero(){
        return xenTablero;
    }
    

    
    public int getYenTablero(){
        return yenTablero;
    }
    
    public void efecto(Criatura criatura) {
    	if (ocupada == true){
            criatura.setEstaViva(false);

        }

        if(consumible != null) {
            consumible.afectarJugador(criatura);
            consumible = null;
        }
    }
    
    public int getSizeCelda(){
    	return size;
    }

}
