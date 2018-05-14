/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import java.util.ArrayList;

/**
 *
 * @author tonio
 */
public class InventarioObjetos extends Inventario {
    
    private ArrayList<ObjetoEquipable> objetos;

    public InventarioObjetos(){
        super();
        this.objetos = this.elementos;
    }
    
    public ArrayList<ObjetoEquipable> getObjetos(){
        return this.objetos;
    }
    
    public void setObjetos(ArrayList<ObjetoEquipable> objetos){
        this.objetos = objetos;
    }
    
    public void addObjeto(ObjetoEquipable objeto){
        if (cantidadElementos() < 10){
            this.objetos.add(objeto);
        } else {
            System.out.println("Maximo alcanzado");
        }
    }
    
    public void rmObjeto(int posicion){//1
        this.objetos.remove(posicion);
    }
    
    public ArrayList<ObjetoEquipable> filtrarObjetos(int opcion, int consulta){
        ArrayList<ObjetoEquipable> auxObjetos;
        System.out.println("=====Ordenando por Rango=====");
        auxObjetos = filtrarEstrella(consulta);
        return auxObjetos;
    }
    
    private ArrayList<ObjetoEquipable> filtrarEstrella(int star){
        ArrayList<ObjetoEquipable> aux = new ArrayList<>();
        for (int i = 0; i < cantidadElementos(); i++){
            if (this.objetos.get(i).getStar() == star){
                aux.add(this.objetos.get(i));
            }
        }
        return aux;
    }
    
    public void mostrarFiltrado(ArrayList<ObjetoEquipable> aux){
        for (int i = 0; i< aux.size(); i++){
            System.out.println("-----------------");
            System.out.println(aux.get(i).getStar());
        }
    }
    
}
