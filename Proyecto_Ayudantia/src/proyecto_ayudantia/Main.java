/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ayudantia;

import GUIproyecto_ayudantia.*;

/**
 *
 * @author arturoayres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*InventarioLuchadores i = new InventarioLuchadores();
        System.out.println(i.cantidadLuchadores());
        i.elegirLuchador(0);
        for (int j = 0; j < 30 ; j++){
            i.addLuchador(new Luchador());
        }
        System.out.println(i.cantidadLuchadores());
        i.mostrarLuchadores();
        i.elegirLuchador(4);
        i.mostrarFiltrado(i.filtrarLuchadores(1, ".NET"));
        i.mostrarFiltrado(i.filtrarLuchadores(2, "Agua"));
        i.rmLuchador(3-1);
        i.mostrarLuchadores();
        i.addLuchador(new Luchador());
        i.elegirLuchador(i.getLuchadores().size()-1);
        
        ObjetoEquipable o = new ObjetoEquipable();
        System.out.println("-------------------------");
        o.mostrarObjeto();*/
        
        /*Batalla batoru = new Batalla();
        batoru.batallar();*/
        
        MenuPrincipal menu = new MenuPrincipal("GameTest");
        menu.setVisible(true);
        
    }
    
    /*public void equiparObjeto(){
        ObjetoEquipable o = new ObjetoEquipable();
        o.mejorarEstadistica(o.elegirEstadistica(), this);
    }*/
}
