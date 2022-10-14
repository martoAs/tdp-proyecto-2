package juego;
import javax.swing.*;
import consumibles.Consumible;
import java.awt.*;
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

    //Controla las colisiones
    public void efecto(Criatura criatura) {
        if (ocupada == true){
            criatura.setEstaViva(false);
        }

        if(consumible != null) {
            consumible.afectarJugador(criatura);
            consumible = null;
        }
    }

    public int getXenTablero(){
        return xenTablero;
    }

    public int getYenTablero(){
        return yenTablero;
    }

    public boolean estaOcupada() {
        return ocupada;
    }


    //Establece en la celda la imagen que recibe (si esta en modo psicodelico llama al metodo setColorFondo) y actualiza el atributo ocupada
    public void setOcupada(String img){
        ocupada = true;
        if(img.contentEquals("cuerpoP") || img.contentEquals("cabezaP")) setColorFondo();
        else{
            //elige el fondo clarito o oscuro dependiendo de la posicion para simular tablero de ajedrez
            if(xenTablero%2 != yenTablero%2) setImagen("/images/"+img+"1.jpg");
            else setImagen("/images/"+img+"2.jpg");

        }
    }

    //Establece en la celda la imagen del fondo correspondiente, agregando el consumible si es necesario y actualiza el atributo ocupada
    public void desocupar(){
        if(consumible!= null) {
            //elige el fondo clarito o oscuro dependiendo de la posicion para simular tablero de ajedrez
            if(xenTablero%2 != yenTablero%2)
                setImagen(consumible.getSkin1());
            else
                setImagen(consumible.getSkin2());
        }
        else{
            //elige el fondo clarito o oscuro dependiendo de la posicion para simular tablero de ajedrez
            if(xenTablero%2 != yenTablero%2) setImagen("/images/celda1.jpg");
            else setImagen("/images/celda2.jpg");

        }
        ocupada = false;
    }

    //Metodo privado para dado el path de la imagen ponerla como icono de la Jlabel
    private void setImagen(String img)  {
        imagen = img;
        ImageIcon ic = new ImageIcon("src"+img);
        Image image = ic.getImage();

        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);

        this.setIcon(ic);

        this.repaint();
    }

    //En el modo psicodelico, cada celda es de un color generado random
    private void setColorFondo(){
        Random random = new Random();
        int r =  random.nextInt(255);
        int g =  random.nextInt(255);
        int b =  random.nextInt(255);
        this.setIcon(null);
        this.setBackground(new Color(r,g,b));
        this.setOpaque(true);

    }


    public Consumible getConsumible() {
        return consumible;
    }

    //Agrega un consumible a la celda
    public void setConsumible(Consumible consumible) {
        this.consumible = consumible;
        if(consumible!=null) {
        	if(xenTablero%2 != yenTablero%2) 
            	setImagen(consumible.getSkin1());
            else 
            	setImagen(consumible.getSkin2());
        }
        
    }
    
    public void sacarConsumible(){
        consumible = null;
    }


    

}
