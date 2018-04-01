/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import java.util.Random;

/**
 *
 * @author arturoayres
 */
public class ObjetoEquipable {
    private String nombreObjeto;
    private int star; 
    private int multiplicador; //Stat del objeto
    
    public ObjetoEquipable(){
        this.star = generarStar();
        this.nombreObjeto = randomString(listaNombre());
        this.multiplicador = generarBase(1, 9, this.star); 
    }
    
    public String getNombreObjeto(){
        return this.nombreObjeto;
    }
    
    public int getStar(){
        return this.star;
    }
    
    public int getMultiplicador(){
        return this.multiplicador;
    }
    
    private int numeroRandom(int rangoIni, int rangoFin){
        Random random = new Random();
        int numero;
        do{
            numero = random.nextInt(rangoFin+1);
        }while(numero<rangoIni);
        return numero;
    }
    
    private int generarBase(int rangoIni, int rangoFin, int star){
        return numeroRandom(rangoIni, rangoFin)*star;
    }
    
    private int generarStar(){
        int numRandom = numeroRandom(0,100);
        if (numRandom <= 20){
            return 1;
        } else if (numRandom > 20 && numRandom <= 40){
            return 2;
        } else if (numRandom > 40 && numRandom <= 60){
            return 3;
        } else if (numRandom > 60 && numRandom <= 75){
            return 4;
        } else if (numRandom > 75 && numRandom <= 85){
            return 5;
        } else if (numRandom > 85 && numRandom <= 90){
            return 6;
        } else if (numRandom > 90 && numRandom <= 94){
            return 7;
        } else if (numRandom > 94 && numRandom <= 97){
            return 8;
        } else if (numRandom > 97 && numRandom <= 99){
            return 9;
        } else {
            return 10;
        }
    }
    
    private String[] listaNombre(){
        String[] listaNombre = {"Muramasa", "Tempestad", "Kanesada", "Murakumo"};
        return listaNombre;
    }
    
    private String randomString(String[] lista){//Random String a partir de una array
        return lista[numeroRandom(0, lista.length-1)];
    }
    
    public void mostrarObjeto(){
        System.out.println("Nombre del Objeto: " + this.nombreObjeto + "\n" +
                "Stat: " + this.multiplicador + " (base: " + this.multiplicador/this.star + ")" + "\n" +
                "Estrellas: " + this.star);
    }
}
