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
    private String resultados;
    
    public Batalla(){
        
        this.luchadoresActivos = new InventarioLuchadores();
        this.monstruoBatalla = new Monstruo();
        this.resultados = "";
    }
    
    public InventarioLuchadores getLuchadoresActivos() {
        return luchadoresActivos;
    }

    public Monstruo getMonstruoBatalla() {
        return monstruoBatalla;
    }
    
    public void setLuchadoresActivos(InventarioLuchadores luchadoresActivos) {
        this.luchadoresActivos = luchadoresActivos;
    }

    public void setMonstruoBatalla(Monstruo monstruoBatalla) {
        this.monstruoBatalla = monstruoBatalla;
    }
    
    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getResultados() {
        return resultados;
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
        this.resultados += "\n" + ("El valor del juego de dados es: " + (d1.getResult() - d2.getResult()) + "\n" +
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
        this.resultados += "\n" +("STATS ANTES DE LA BATALLA");
        ordenBatalla.mostrarElementos("Luchadores");
        this.resultados += "\n" +(this.monstruoBatalla);
        this.resultados += "\n" +("BATTLE START!");
        int i = 0;
        int random = numeroRandom(3,6);
        int contadorFuria = random;
        do{
            this.resultados += "\n" +("~~~~~~~Turno " + (i+1) + "~~~~~~~");
            interaccionEntidades(ordenBatalla, random, contadorFuria);
            this.resultados += "\n" +("~~~~~~~Fin del ataque~~~~~~~" + "\n");
            i++;
            contadorFuria--;
            if (contadorFuria < 0) contadorFuria = random;
            if (this.monstruoBatalla.getHp() <= 0){
                this.resultados += "\n" +("LOS LUCHADORES GANAN!");
                this.resultados += "\n" +("OBJETO DROPEADO:" );
                this.resultados += "\n" +(this.monstruoBatalla.asignarDrop());
            }
            if (checkHpLuchadores(ordenBatalla)){
                this.resultados += "\n" +("LOS LUCHADORES PIERDEN");
                break;
            }
        }while (this.monstruoBatalla.getHp() > 0);
        this.resultados += "\n" + ordenBatalla.mostrarElementos("Luchadores");
        this.resultados += "\n" +(this.monstruoBatalla);
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
        
        switch (compararFaccionLuchador(atacante)) {
            case "Ventaja":
                dmg = atacante.getAtk()*1.5 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                this.resultados += "\n" +("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño. Es supereficaz!");
                break;
            case "Desventaja":
                dmg = atacante.getAtk()*0.75 - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                this.resultados += "\n" +("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño.  No es muy eficaz.");
                break;
            case "Neutral":
                dmg = atacante.getAtk() - this.monstruoBatalla.getDef();
                if (dmg < 0) dmg = 0;
                this.monstruoBatalla.setHp(this.monstruoBatalla.getHp() - dmg);
                this.resultados += "\n" +("El luchador " + atacante.getNombre() + " atacó al monstruo, con " + dmg + " de daño.");
                break;
            default:
                break;
        }
                    
    }
    
    public void ataqueMonstruo(InventarioLuchadores luchadores){
        double dmg;
        int random = numeroRandom(0,luchadores.getLuchadores().size()-1);

       
        this.resultados += "\n" +("===========================");
            
        if (luchadores.getLuchadores().get(random).getHp() <= 0){
            this.resultados += "\n" +("El monstruo intento atacar a " + luchadores.getLuchadores().get(random).getNombre() + " pero esta muerto!");
            
        }else{
            
            switch (compararFaccionMonstruo(luchadores.getLuchadores().get(random))) {
                case "Ventaja":
                    dmg = this.monstruoBatalla.getAtk()*1.5 - luchadores.getLuchadores().get(random).getDef();
                    if (dmg < 0) dmg = 0;
                    luchadores.getLuchadores().get(random).setHp(luchadores.getLuchadores().get(random).getHp() - dmg);
                    this.resultados += "\n" +("El monstruo atacó a " + luchadores.getLuchadores().get(random).getNombre() + ", con " + dmg + " de daño. Es supereficaz!" );
                    break;
                case "Desventaja":
                    dmg = this.monstruoBatalla.getAtk()*0.75 - luchadores.getLuchadores().get(random).getDef();
                    if (dmg < 0) dmg = 0;
                    luchadores.getLuchadores().get(random).setHp(luchadores.getLuchadores().get(random).getHp() - dmg);
                    this.resultados += "\n" +("El monstruo atacó a " + luchadores.getLuchadores().get(random).getNombre() + ", con " + dmg + " de daño. No es muy eficaz." );
                    break;
                case "Neutral":
                    dmg = this.monstruoBatalla.getAtk() - luchadores.getLuchadores().get(random).getDef();
                    if (dmg < 0) dmg = 0;
                    luchadores.getLuchadores().get(random).setHp(luchadores.getLuchadores().get(random).getHp() - dmg);
                    this.resultados += "\n" +("El monstruo atacó a " + luchadores.getLuchadores().get(random).getNombre() + ", con " + dmg + " de daño." );
                    break;
                default:
                    break;
            }
            
        if (luchadores.getLuchadores().get(random).getHp() < 0){
            luchadores.getLuchadores().get(random).setHp(0);
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
    
    public void interaccionEntidades(InventarioLuchadores luchadores, int random, int contadorFuria){
        boolean ataqueRealizado = false;
        int i = 0;
        
        if (contadorFuria > 0){
            this.resultados += "\n" +("##################################" + "\n" +
                            "Quedan " + contadorFuria + " turno(s) para el Ataque especial del monstruo!" + "\n" +
                            "(El monstruo atacará " + random + " veces entre todos los luchadores)." + "\n" +
                            "##################################");
        } else {
            this.resultados += "\n" +("##################################" + "\n" +
                    "El monstruo atacara con furia!" + "\n" +
                    "##################################");
        }
        
        
        while (i < luchadores.cantidadLuchadores()){
            if (this.monstruoBatalla.getSpd() > luchadores.getLuchadores().get(i).getSpd()  && !ataqueRealizado){
                //si el luchador tiene la misma velocidad que el monstruo, se le dara prioridad al jugador
                if (this.monstruoBatalla.getHp() > 0 && contadorFuria > 0){   
                    ataqueMonstruo(luchadores);
                    
                }else if(this.monstruoBatalla.getHp() > 0 && contadorFuria == 0){
                    for (int j = 0; j < random ; j++){
                        ataqueMonstruo(luchadores);
                    }
                } else {
                    break;
                }
                ataqueRealizado = true;
                if(checkHpLuchadores(luchadores)){
                    break;
                }
            }else{
                this.resultados += "\n" +("===========================");
                Luchador atacante = luchadores.getLuchadores().get(i);
                if (atacante.getHp() <= 0){
                    this.resultados += "\n" +(atacante.getNombre() + " esta muerto. No puede atacar.");
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
        
        if (!ataqueRealizado && this.monstruoBatalla.getHp() > 0 && contadorFuria > 0){
            ataqueMonstruo(luchadores);
        } else {
            for (int j = 0; j < random ; j++){
                ataqueMonstruo(luchadores);
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
