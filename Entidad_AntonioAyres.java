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
public class Entidad {
    
    protected double hp;
    protected double atk;
    protected double def;
    protected double spd;
    protected String faccion;
    
    public double getHp() {
        return hp;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }

    public double getSpd() {
        return spd;
    }

    public String getFaccion() {
        return faccion;
    }
    
    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }
    
    protected int numeroRandom(int rangoIni, int rangoFin){
        Random random = new Random();
        int numero;
        do{
            numero = random.nextInt(rangoFin+1);
        }while(numero<rangoIni);
        return numero;
    }
    
    protected int generarBase(int rangoIni, int rangoFin){
        return numeroRandom(rangoIni, rangoFin);
    }
    
    
    protected String[] listaFaccion(){
        String[] listaFaccion = {"Agua", "Planta", "Fuego"};
        return listaFaccion;
    }
    
    protected String randomString(String[] lista){//Random String a partir de una array
        return lista[numeroRandom(0, lista.length-1)];
    }
    
    
}
