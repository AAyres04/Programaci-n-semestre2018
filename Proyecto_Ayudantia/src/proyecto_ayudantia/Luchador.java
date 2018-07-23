/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import java.util.Random;

/**
 *
 * @author zeiruos
 */
public class Luchador extends Entidad{
    private int star;
    private String nombre;
    private ObjetoEquipable objetoLuchador;
    
    /* 
    * Si se desea probar los metodos en un Main, se recomienda usar el metodo
    * mostrarLuchador(), ya que muestra todo de manera ordenada.
    */
    
    public Luchador(){
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.spd = 0;
        this.faccion = "";
        this.nombre = "";
        crearLuchador();
    }
    
    private void crearLuchador(){
        this.star = generarStar();
        this.atk = generarBase(20, 70)*this.star;
        this.def = generarBase(5, 25)*this.star;
        this.spd = generarBase(10, 100)*this.star;
        this.hp = generarBase(200, 500)*this.star;
        this.nombre = randomString(listaNombre());
        this.faccion = randomString(listaFaccion());
    }
    
    private int generarStar(){
        int numRandom = numeroRandom(0,100);
        if (numRandom <= 40){
            return 1;
        } else if (numRandom > 40 && numRandom <=70){
            return 2;
        } else if (numRandom > 70 && numRandom <=85){
            return 3;
        } else if (numRandom > 85 && numRandom <=95){
            return 4;
        } else {
            return 5;
        }
    }
    
    private String[] listaNombre(){
        String[] listaNombre = {"Java", "Python", "JavaScript", "Ruby", "C++",
                                "Binary", "C", "ASL", "Bash", "Scratch", ".NET",
                                "Perl", "C#", "GO", "MatLab"};
        return listaNombre;
    }
    
    public double getStar(){
        return this.star;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void equiparObjeto(){
        this.objetoLuchador = new ObjetoEquipable();
        this.objetoLuchador.mejorarEstadistica(this.objetoLuchador.elegirEstadistica(), this);
    }
    
    @Override
    public String toString(){
        return "Nombre: " + this.nombre + "\n" +
                "Facción: " + this.faccion + "\n" +
                "ATK: " + this.atk + "\n" +
                "DEF: " + this.def +  "\n" +
                "SPD: " + this.spd + "\n" +
                "HP: " + this.hp + "\n" +
                "Estrella: " + this.star +"\n";
    }
    
}