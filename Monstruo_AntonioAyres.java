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
public class Monstruo extends Entidad{
    
    private InventarioObjetos dropsPosibles;

    public InventarioObjetos getDropsPosibles() {
        return dropsPosibles;
    }

    public void setDropsPosibles(InventarioObjetos dropsPosibles) {
        this.dropsPosibles = dropsPosibles;
    }
    
    public Monstruo(){
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.spd = 0;
        this.faccion = "";
        this.dropsPosibles = new InventarioObjetos();
        crearMosntruo();
        crearObjetosDropeables();
    }
    
    private void crearMosntruo(){
        this.atk = generarBase(1000, 1500);
        this.hp = generarBase(3500, 4000);
        this.def = generarBase(5, 25);
        this.spd = generarBase(10, 100);
        this.faccion = randomString(listaFaccion());
    }
    
    private void crearObjetosDropeables(){
        int i = 0;
        do{
            ObjetoEquipable aux = new ObjetoEquipable();
            // Esto es para almacenar 3 armas de 1, 3 y 5 estrellas respectivamente, en la coleccion de objetos.
            if (aux.getStar() == (i+(i+1))){
                this.dropsPosibles.getObjetos().add(aux);
                i = i+1;
            }
        }while (i<3);
    }
    
    public ObjetoEquipable asignarDrop(){
        int numRandom = numeroRandom(0,100);
        if(numRandom > 0 && numRandom <= 60){
            return this.dropsPosibles.getObjetos().get(0);//Siempre estara almacenado un objeto de una estrella
        } else if (numRandom > 60 && numRandom <=90){
            return this.dropsPosibles.getObjetos().get(1);//Siempre estara almacenado un objeto de tres estrellas
        } else {
            return this.dropsPosibles.getObjetos().get(2);//Siempre estara almacenado un objeto de cinco estrellas
        }
    }
    
    @Override
    public String toString(){
        return "-------=Monstruo=--------"  + "\n" + 
                "Facción: " + this.faccion + "\n" +
                "ATK: " + this.atk + "\n" +
                "DEF: " + this.def + "\n" +
                "SPD: " + this.spd + "\n" +
                "HP: " + this.hp + "\n";
    }
}
