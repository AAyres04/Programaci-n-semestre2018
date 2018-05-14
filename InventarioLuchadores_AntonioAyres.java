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
public class InventarioLuchadores extends Inventario{
    
    private ArrayList<Luchador> luchadores;
    
    public InventarioLuchadores(){
        super();
        this.luchadores = this.elementos;
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
            System.out.println("Nombre: " + aux.get(i).getNombre());
            System.out.println("Facción: " + aux.get(i).getFaccion());
            System.out.println("Estrella: " + aux.get(i).getStar());
        }
    }
    
    public int cantidadLuchadores(){
        return this.luchadores.size();
    }
}
