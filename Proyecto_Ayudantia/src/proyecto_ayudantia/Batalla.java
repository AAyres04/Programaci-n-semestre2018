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
        randomLuchadores(numeroRandom(1,6));
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
            for (int i = 0; i < this.luchadoresActivos.getLuchadores().size(); i++){
                this.luchadoresActivos.getLuchadores().get(i).setAtk(this.luchadoresActivos.getLuchadores().get(i).getAtk()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setDef(this.luchadoresActivos.getLuchadores().get(i).getDef()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setSpd(this.luchadoresActivos.getLuchadores().get(i).getSpd()*valor);
                this.luchadoresActivos.getLuchadores().get(i).setHp(this.luchadoresActivos.getLuchadores().get(i).getHp()*valor);
            }
        } else if (valor < 0){
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
        ordenBatalla.mostrarElementos("Luchadores");
        System.out.println(this.monstruoBatalla);
        System.out.println("BATTLE START!");
        int i = 0;
        do{
            System.out.println("~~~~~~~Turno " + (i+1) + "~~~~~~~");
            interaccionEntidades(ordenBatalla);
            System.out.println("~~~~~~~Fin del ataque~~~~~~~" + "\n");
            i++;
            if (this.monstruoBatalla.getHp() <= 0){
                System.out.println("LOS LUCHADORES GANAN!");
                System.out.println("OBJETO DROPEADO:" );
                System.out.println(this.monstruoBatalla.asignarDrop());
            }
            if (checkHpLuchadores(ordenBatalla)){
                System.out.println("LOS LUCHADORES PIERDEN");
                break;
            }
        }while (this.monstruoBatalla.getHp() > 0);
        ordenBatalla.mostrarElementos("Luchadores");
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
    
    public void ataqueLuchador(Luchador atacante){
        double dmg;
        
        if(compararFaccionLuchador(atacante).equals("Ventaja")){
            dmg = atacante.getAtk()*1.5 - this.monstruoBatalla.getDef();
            if (dmg < 0) dmg = 0;
            this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
            System.out.println("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño. Es supereficaz!");
        }else if(compararFaccionLuchador(atacante).equals("Desventaja")){
            dmg = atacante.getAtk()*0.75 - this.monstruoBatalla.getDef();
            if (dmg < 0) dmg = 0;
            this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
            System.out.println("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño.  No es muy eficaz.");
        }else if(compararFaccionLuchador(atacante).equals("Neutral")){
            dmg = atacante.getAtk() - this.monstruoBatalla.getDef();
            if (dmg < 0) dmg = 0;
            this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
            System.out.println("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño.");
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
            
            if(compararFaccionMonstruo(luchadores.getLuchadores().get(i)).equals("Ventaja")){
                dmg = this.monstruoBatalla.getAtk()*1.5 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. Es supereficaz!" );
            } else if (compararFaccionMonstruo(luchadores.getLuchadores().get(i)).equals("Desventaja")) {
                dmg = this.monstruoBatalla.getAtk()*0.75 - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño. No es muy eficaz." );
            } else if (compararFaccionMonstruo(luchadores.getLuchadores().get(i)).equals("Neutral")){
                dmg = this.monstruoBatalla.getAtk() - luchadores.getLuchadores().get(i).getDef();
                if (dmg < 0) dmg = 0;
                luchadores.getLuchadores().get(i).setHp(luchadores.getLuchadores().get(i).getHp() - dmg);
                System.out.println("El monstruo atacó a " + luchadores.getLuchadores().get(i).getNombre() + ", con " + dmg + " de daño." );
            }
            
            if (luchadores.getLuchadores().get(i).getHp() < 0){
                luchadores.getLuchadores().get(i).setHp(0);
            }
        }
    }
    
    public String compararFaccionMonstruo(Luchador luchador){
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Fuego")) return "Ventaja";
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Planta")) return "Desventaja";
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Agua")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Fuego")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Planta")) return "Ventaja";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Agua")) return "Desventaja";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Desventaja";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Ventaja";
        return "Neutral";
    }
    
    public String compararFaccionLuchador(Luchador luchador){
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Fuego")) return "Desventaja";
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Planta")) return "Ventaja";
        if (this.monstruoBatalla.getFaccion().equals("Agua") && luchador.getFaccion().equals("Agua")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Fuego")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Planta")) return "Desventaja";
        if (this.monstruoBatalla.getFaccion().equals("Fuego") && luchador.getFaccion().equals("Agua")) return "Ventaja";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Ventaja";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Neutral";
        if (this.monstruoBatalla.getFaccion().equals("Planta") && luchador.getFaccion().equals("Fuego")) return "Desventaja";
        return "Neutral";
    }
    
    public void interaccionEntidades(InventarioLuchadores luchadores){
        boolean ataqueRealizado = false;
        int i = 0;
        
        while (i < luchadores.cantidadLuchadores()){
            if (this.monstruoBatalla.getSpd() > luchadores.getLuchadores().get(i).getSpd()  && !ataqueRealizado){
                //si el luchador tiene la misma velocidad que el monstruo, se le dara prioridad al jugador
                if (this.monstruoBatalla.getHp() > 0){   
                    ataqueMonstruo(luchadores);
                } else {
                    break;
                }
                ataqueRealizado = true;
                if(checkHpLuchadores(luchadores)){
                    break;
                }
            }else{
                System.out.println("===========================");
                Luchador atacante = luchadores.getLuchadores().get(i);
                if (atacante.getHp() <= 0){
                    System.out.println(atacante.getNombre() + " esta muerto. No puede atacar.");
                    i = i+1;
                }else{
                    ataqueLuchador(atacante);
                    if (this.monstruoBatalla.getHp() <= 0){
                        this.monstruoBatalla.setHp(0);
                        break;
                    }
                    i = i +1;
                }
            }   
        }
        
        if (!ataqueRealizado && this.monstruoBatalla.getHp() > 0){
            ataqueMonstruo(luchadores);
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
