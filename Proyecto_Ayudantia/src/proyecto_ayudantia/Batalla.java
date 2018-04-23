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
public class Batalla {
    private InventarioLuchadores luchadoresActivos;
    private Monstruo monstruoBatalla;
    
    public Batalla(){
        /*
        *Como aun no se crea un menu como tal, no se esta utilizando ninguna lista de luchadores activa,
        *por lo qu se usara una lista de luchadores temporal. En cuanto se cree el menu, se aplicara que estos
        *sean elegidos aleatoriamente.
        *
        *this.luchadoresActivos = luchadoresAleatorios(luchadores);
        */
        
        this.luchadoresActivos = new InventarioLuchadores();
        randomLuchadores(numeroRandom(1,5));
        this.monstruoBatalla = new Monstruo();
        
    }
    
    private int numeroRandom(int rangoIni, int rangoFin){
        Random random = new Random();
        int numero;
        do{
            numero = random.nextInt(rangoFin+1);
        }while(numero<rangoIni);
        return numero;
    }
    
    public void randomLuchadores(int cantidad){
        if (cantidad > 6){
            cantidad = 6;
        }
        for (int i = 0; i < cantidad; i++ ){
            this.luchadoresActivos.addLuchador(new Luchador());
        }
    }
    
    public int valorJuegoDados(){
        Dado d1 = new Dado();
        Dado d2 = new Dado();
        d1.setCantidadCaras(8);
        d2.setCantidadCaras(6);
        d1.arrojarDado();
        d2.arrojarDado();
        System.out.println("El valor del juego de dados es: " + (d1.getResult() - d2.getResult()) + "\n" +
                "Dado1 = " + d1.getResult() + " Dado2 = "  + d2.getResult());
        return (d1.getResult() - d2.getResult());
    }
    
    public void buffInicial(int valor){
        if (valor > 0){
            System.out.println("pase por aqui 1");
            for (int i = 0; i < this.luchadoresActivos.getLuchadores().size(); i++){
                this.luchadoresActivos.getLuchadores().get(i).setAtk(this.luchadoresActivos.getLuchadores().get(i).getAtk()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setDef(this.luchadoresActivos.getLuchadores().get(i).getDef()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setSpd(this.luchadoresActivos.getLuchadores().get(i).getSpd()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setHp(this.luchadoresActivos.getLuchadores().get(i).getHp()*valor);
            }
        } else if (valor < 0){
            System.out.println("pase por aqui 2");
            this.monstruoBatalla.setAtk(this.monstruoBatalla.getAtk()*(-valor));
            this.monstruoBatalla.setDef(this.monstruoBatalla.getDef()*(-valor));
            this.monstruoBatalla.setSpd(this.monstruoBatalla.getSpd()*(-valor));
            this.monstruoBatalla.setHp(this.monstruoBatalla.getHp()*(-valor));
        } else {
            //Nothing
        }
    }
    
    public void batallar(){
        buffInicial(valorJuegoDados());
        InventarioLuchadores ordenBatalla = ordenBatalla();
        System.out.println("STATS ANTES DE LA BATALLA");
        ordenBatalla.mostrarLuchadores();
        System.out.println(this.monstruoBatalla);
        System.out.println("BATTLE START!");
        do{
            System.out.println("~~~~~~~Turno de los luchadores!~~~~~~~");
            ataqueLuchadores(ordenBatalla);
            System.out.println("~~~~~~~Fin del ataque~~~~~~~" + "\n");
            if (this.monstruoBatalla.getHp() <= 0){
                System.out.println("LOS LUCHADORES GANAN!");
                System.out.println("OBJETO DROPEADO:" );
                this.monstruoBatalla.asignarDrop().mostrarObjeto();
                break;
            }
            System.out.println("~~~~~~~Turno del monstruo!~~~~~~~");
            ataqueMonstruo(ordenBatalla);
            System.out.println("~~~~~~~Fin del ataque~~~~~~~" + "\n");
            if (checkHpLuchadores(ordenBatalla)){
                System.out.println("LOS LUCHADORES PIERDEN");
                break;
            }
        }while (this.monstruoBatalla.getHp() > 0);
        ordenBatalla.mostrarLuchadores();
        System.out.println(this.monstruoBatalla);
    }
    
    public InventarioLuchadores ordenBatalla(){
        InventarioLuchadores ordenBatalla;
        ordenBatalla = this.luchadoresActivos;
        for(int i = 0; i < ordenBatalla.cantidadLuchadores(); i++){
            for (int j = 0; j < ordenBatalla.cantidadLuchadores(); j++){
                if(ordenBatalla.getLuchadores().get(i).getSpd() > ordenBatalla.getLuchadores().get(j).getSpd()){
                    Luchador aux = ordenBatalla.getLuchadores().get(i);
                    ordenBatalla.getLuchadores().set(i, ordenBatalla.getLuchadores().get(j));
                    ordenBatalla.getLuchadores().set(j, aux);
                }
            }
        }
        return ordenBatalla;
    }
    
    public void ataqueLuchadores(InventarioLuchadores luchadores){
        double dmg;
        for (int i = 0; i < luchadores.cantidadLuchadores(); i++){
            System.out.println("===========================");
            
            if (luchadores.getLuchadores().get(i).getHp() <= 0){
                System.out.println(luchadores.getLuchadores().get(i).getNombre() + " esta muerto. No puede atacar.");
                continue;
            }
            
            
            if(this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*0.75 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño.  No es muy eficaz.");
                
            } else if (this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*1.5 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño. Es supereficaz!");
            
            } else if (this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = luchadores.getLuchadores().get(i).getAtk() - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño.");
            
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = luchadores.getLuchadores().get(i).getAtk() - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño.");
            
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*0.75 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño. No es muy eficaz.");
                
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*1.5 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño. Es supereficaz!");
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*1.5 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño. Es supereficaz!");
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = luchadores.getLuchadores().get(i).getAtk() - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño.");
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = luchadores.getLuchadores().get(i).getAtk()*0.75 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - (luchadores.getLuchadores().get(i).getAtk()*0.75 - this.monstruoBatalla.getDef()));
                System.out.println("El luchador " + luchadores.getLuchadores().get(i).getNombre() + " atacó al monstruo, con " + dmg + " de daño. No es muy eficaz.");

            }
            
            if (this.monstruoBatalla.getHp() <= 0){
                this.monstruoBatalla.setHp(0);
                break;
            }
        }
        
        
    }
    
    public void ataqueMonstruo(InventarioLuchadores luchadores){
        double dmg;
        for (int i = 0; i < luchadores.cantidadLuchadores(); i++){
            System.out.println("===========================");
            
            if (luchadores.getLuchadores().get(i).getHp() <= 0){
                System.out.println("El monstruo intento atacar a " + luchadores.getLuchadores().get(i).getNombre() + " pero esta muerto!");
                continue;
            }
            
            if(this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = this.monstruoBatalla.getAtk()*1.5 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. Es supereficaz!" );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = this.monstruoBatalla.getAtk()*0.75 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. No es muy eficaz." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Agua") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = this.monstruoBatalla.getAtk() - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = this.monstruoBatalla.getAtk() - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = this.monstruoBatalla.getAtk()*1.5 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. Es supereficaz!" );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = this.monstruoBatalla.getAtk()*0.75 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño.  No es muy eficaz." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Fuego")){
                dmg = this.monstruoBatalla.getAtk()*0.75 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. No es muy eficaz." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Planta")){
                dmg = this.monstruoBatalla.getAtk() - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño." );
                
            } else if (this.monstruoBatalla.getFaccion().equals("Planta") && luchadores.getLuchadores().get(i).getFaccion().equals("Agua")){
                dmg = this.monstruoBatalla.getAtk()*1.5 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. Es supereficaz!" );
            }
            
            if (luchadores.getLuchadores().get(i).getHp() < 0){
                luchadores.getLuchadores().get(i).setHp(0);
            }
        }
    }
    
    public boolean checkHpLuchadores(InventarioLuchadores luchadores){
        for (int i = 0; i < luchadores.cantidadLuchadores(); i++){
            if (luchadores.getLuchadores().get(i).getHp() <= 0){

            } else {
                return false;
            }
        }
        return true;
    }
}
