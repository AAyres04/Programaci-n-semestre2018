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
public class Dado {
    private int cantidadCaras;
    private int result;
    
    public Dado(){
        cantidadCaras = 0;
        result = 0;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
    public void setCantidadCaras(int cantidadCaras){
        this.cantidadCaras = cantidadCaras;
    }

    public int getCantidadCaras() {
        return cantidadCaras;
    }

    public int getResult() {
        return result;
    }
    
    public void arrojarDado(){
        this.result = numeroRandom(1, this.cantidadCaras);
    }
    
    private int numeroRandom(int rangoIni, int rangoFin){
        Random random = new Random();
        int numero;
        do{
            numero = random.nextInt(rangoFin+1);
        }while(numero<rangoIni);
        return numero;
    }
}

