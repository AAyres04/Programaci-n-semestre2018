/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import java.util.Random;

/**
 *
 * @author tonio
 */
public class Monstruo {
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private String faccion; //AGREGADO DESPUES DEL MODELO UML, CAMBIOS REFLEJADOS EN AVANCE 5
    private ObjetoEquipable drop;
    
    public Monstruo(){
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.spd = 0;
        this.faccion = "";
        crearMosntruo();
    }
    
    private int numeroRandom(int rangoIni, int rangoFin){
        Random random = new Random();
        int numero;
        do{
            numero = random.nextInt(rangoFin+1);
        }while(numero<rangoIni);
        return numero;
    }
    
    private int generarBase(int rangoIni, int rangoFin){
        return numeroRandom(rangoIni, rangoFin);
    }
    
    public void crearMosntruo(){
        this.atk = generarBase(1000, 1500);
        this.hp = generarBase(3500, 4000);
        this.def = generarBase(5, 25);
        this.spd = generarBase(10, 100);
        this.faccion = randomString(listaFaccion());
    }
    
    public ObjetoEquipable crearObjetoDropeable(){
        this.drop = new ObjetoEquipable();
        System.out.println("---drop---");
        drop.mostrarObjeto();
        return drop;
    }
    
    private String[] listaFaccion(){
        String[] listaFaccion = {"Agua", "Tierra", "Fuego"};
        return listaFaccion;
    }
    
    private String randomString(String[] lista){//Random String a partir de una array
        return lista[numeroRandom(0, lista.length-1)];
    }
    
}
