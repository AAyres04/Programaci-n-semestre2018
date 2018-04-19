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
public class Luchador {
    private int atk;
    private int def;
    private int spd;
    private int hp;
    private int star;
    private String nombre;
    private String faccion;
    
    /* 
    * Si se desea probar los metodos en un Main, se recomienda usar el metodo
    * mostrarLuchador(), ya que muestra todo de manera ordenada.
    */
    
    public Luchador(){
        this.star = generarStar();
        this.atk = generarBase(20, 70, this.star);
        this.def = generarBase(5, 25, this.star);
        this.spd = generarBase(10, 100, this.star);
        this.hp = generarBase(200, 500, this.star);
        this.nombre = randomString(listaNombre());
        this.faccion = randomString(listaFaccion());
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
    
    private String[] listaFaccion(){
        String[] listaFaccion = {"Agua", "Tierra", "Fuego"};
        return listaFaccion;
    }
    
    private String randomString(String[] lista){//Random String a partir de una array
        return lista[numeroRandom(0, lista.length-1)];
    }
    
    public void mostrarLuchador(){ // reemplazo a "toString()"
        System.out.println("Nombre: " + this.nombre + "\n" +
                "Facción: " + this.faccion + "\n" +
                "ATK: " + this.atk + " (base: " + this.atk/this.star + ")" + "\n" +
                "DEF: " + this.def + " (base: " + this.def/this.star + ")" + "\n" +
                "SPD: " + this.spd + " (base: " + this.spd/this.star + ")" + "\n" +
                "HP: " + this.hp + " (base: " + this.hp/this.star + ")" + "\n" +
                "Estrella: " + this.star);
    }
    
    public void mostrarAtk(){
        System.out.println(this.atk);
    }
    
    public void mostrarDef(){
        System.out.println(this.def);
    }
    
    public void mostrarSpd(){
        System.out.println(this.spd);
    }
    
    public void mostrarHp(){
        System.out.println(this.hp);
    }
    
    public void mostrarStar(){
        System.out.println(this.star);
    }
    
    public void mostrarNombre(){
        System.out.println(this.nombre);
    }
    
    public void mostrarFaccion(){
        System.out.println(this.faccion);
    }
    
    //TAREA N 2 - Todo lo que esta debajo fue añadido para la tarea 2
    
    public int getAtk(){
        return this.atk;
    }
    
    public void setAtk(int atk){
        this.atk = atk;
    }
    
    public int getDef(){
        return this.def;
    }
    
    public void setDef(int def){
        this.def = def;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    public void setHp(int hp){
        this.hp = hp;
    }
    
    public int getSpd(){
        return this.spd;
    }
    
    public void setSpd(int spd){
        this.spd = spd;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getFaccion(){
        return this.faccion;
    }
    
    
}