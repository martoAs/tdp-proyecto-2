package juego;

import java.util.LinkedList;

public class CriaturaGrafica {

    private LinkedList<String> skins;

    //Estados: 0 normal 1 psicodelico 2 redondito 3 futbol
    private int estado;


    public CriaturaGrafica(){
        //Normal
        estado = 0;
        skins = new LinkedList<>();
        skins.add("cabeza");
        skins.add("cuerpo");

        skins.add("cabezaP");
        skins.add("cuerpoP");

        skins.add("cabezaR");
        skins.add("cuerpoR");

        skins.add("cabezaF");
        skins.add("cuerpoF");

    }


    public String getImagenCuerpo(){
        return skins.get(estado*2+1);

    }

    public String getImagenCabeza(){

        return skins.get(estado*2);
    }

    public void cambiarEstado(int e){
        estado = e;
    }

}
