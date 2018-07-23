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
public class Inventario {
    
    protected int cantidadElementos;
    protected ArrayList elementos;
    
    public Inventario(){
        this.elementos = new ArrayList();
    }
    
    protected void elegirElemento(int index, String tipoElemento){
        String aviso = "Elemento fuera de rango";
        if (validarIndex(index)){
            System.out.println("------------------" + tipoElemento +" N°" + (index+1) + "------------------");
            System.out.println(this.elementos.get(index));
        } else {
            System.out.println(aviso);
        }
    }
    
    public String mostrarElementos(String tipoElemento){
        String elemento ="";
        for (int i = 0; i < cantidadElementos(); i++){
            elemento += ("--------------" + tipoElemento +" N°" + (i+1) + "--------------" + "\n" + this.elementos.get(i));
        }
        return elemento;
    }
    
    private boolean validarIndex(int index){ //en caso de usarlo en otro método
        return index > 0 && index < cantidadElementos();
    }
        
    public int cantidadElementos(){
        return this.elementos.size();
    }

}
