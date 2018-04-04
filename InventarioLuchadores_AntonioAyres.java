/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import java.util.ArrayList;

/**
 *
 * @author arturoayres
 */
public class InventarioLuchadores {
    private ArrayList<Luchador> luchadores;
    
    public InventarioLuchadores(){
        this.luchadores = new ArrayList<>();
    }
    
    public ArrayList<Luchador> getLuchadores(){
        return this.luchadores;
    }
    
    public void setLuchadores(ArrayList<Luchador> luchadores){
        this.luchadores = luchadores;
    }
    
    public void addLuchador(Luchador luchador){
        if (cantidadLuchadores() < 25){
            this.luchadores.add(luchador);
        } else {
            System.out.println("Maximo alcanzado");
        }
    }
    
    public void rmLuchador(int posicion){
        this.luchadores.remove(posicion);
    }
    
    public ArrayList<Luchador> filtrarLuchadores(int opcion, String consulta){
        ArrayList auxLuchadores = new ArrayList<>();
        switch(opcion){
            case 1:
                System.out.println("=====Ordenando por nombre=====");
                auxLuchadores = filtrarNombre(consulta);
                break;
            case 2:
                System.out.println("=====Ordenando por facción=====");
                auxLuchadores = filtrarFaccion(consulta);
                break;
        }
        return auxLuchadores;
    }
    
    private ArrayList<Luchador> filtrarNombre(String nombre){
        ArrayList<Luchador> aux = new ArrayList<>();
        for (int i = 0; i < cantidadLuchadores(); i++){
            if (this.luchadores.get(i).getNombre().equals(nombre)){
                aux.add(this.luchadores.get(i));
            }
        }
        return aux;
    }
    
    private ArrayList<Luchador> filtrarFaccion(String faccion){
        ArrayList<Luchador> aux = new ArrayList<>();
        for (int i = 0; i < cantidadLuchadores(); i++){
            if (this.luchadores.get(i).getFaccion().equals(faccion)){
                aux.add(this.luchadores.get(i));
            }
        }
        return aux;
    }
    
    public void mostrarFiltrado(ArrayList<Luchador> aux){
        for (int i = 0; i< aux.size(); i++){
            System.out.println("-----------------");
            aux.get(i).mostrarNombre();
            aux.get(i).mostrarFaccion();
            aux.get(i).mostrarStar();
        }
    }
    
    public void mostrarLuchadores(){
        for (int i = 0; i < cantidadLuchadores(); i++){
            System.out.println("------------------Luchador N°" + i + "------------------");
            this.luchadores.get(i).mostrarNombre();
            this.luchadores.get(i).mostrarFaccion();
            this.luchadores.get(i).mostrarStar();
        }
    }
    
    public void elegirLuchador(int index){
        String aviso = "Luchador fuera de rango";
        if (validarIndex(index)){
            System.out.println("------------------Luchador N°" + index + "------------------");
            this.luchadores.get(index).mostrarLuchador();
        } else {
            System.out.println(aviso);
        }
    }
    
    private boolean validarIndex(int index){ //en caso de usarlo en otro método
        return index > 0 && index < this.luchadores.size();
    }
    
    public int cantidadLuchadores(){
        return this.luchadores.size();
    }
}
