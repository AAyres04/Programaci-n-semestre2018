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
public class InventarioObjetos {
    
    private ArrayList<ObjetoEquipable> objetos;
    
    public InventarioObjetos(){
        this.objetos = new ArrayList<>();
    }
    
    public ArrayList<ObjetoEquipable> getObjetos(){
        return this.objetos;
    }
    
    public void setObjetos(ArrayList<ObjetoEquipable> luchadores){
        this.objetos = luchadores;
    }
    
    public void addObjeto(ObjetoEquipable objeto){
        if (cantidadObjetos() < 10){
            this.objetos.add(objeto);
        } else {
            System.out.println("Maximo alcanzado");
        }
    }
    
    public void rmObjeto(int posicion){
        this.objetos.remove(posicion);
    }
    
    public ArrayList<ObjetoEquipable> filtrarObjetos(int opcion, int consulta){
        ArrayList auxObjetos;
        System.out.println("=====Ordenando por Rango=====");
        auxObjetos = filtrarEstrella(consulta);
        return auxObjetos;
    }
    
    private ArrayList<ObjetoEquipable> filtrarEstrella(int star){
        ArrayList<ObjetoEquipable> aux = new ArrayList<>();
        for (int i = 0; i < cantidadObjetos(); i++){
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
    
    public void mostrarObjetos(){
        for (int i = 0; i < cantidadObjetos(); i++){
            System.out.println("------------------Objeto N°" + i+1 + "------------------");
            this.objetos.get(i).mostrarNombre();
            this.objetos.get(i).mostrarMultiplicador();
            this.objetos.get(i).mostrarStar();
        }
    }
    
    public void elegirObjeto(int index){
        String aviso = "Objeto fuera de rango";
        if (validarIndex(index)){
            System.out.println("------------------Objeto N°" + index+1 + "------------------");
            this.objetos.get(index).mostrarObjeto();
        } else {
            System.out.println(aviso);
        }
    }
    
    private boolean validarIndex(int index){ //en caso de usarlo en otro método
        return index > 0 && index < cantidadObjetos();
    }
    
    public int cantidadObjetos(){
        return this.objetos.size();
    }
    
    
}
