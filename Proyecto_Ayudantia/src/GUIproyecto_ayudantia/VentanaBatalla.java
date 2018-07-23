/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIproyecto_ayudantia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import proyecto_ayudantia.*;
/**
 *
 * @author tonio
 */
public class VentanaBatalla extends JFrame {
    
    TextArea resultados;
    Batalla batalla;
    
    public VentanaBatalla(String title, InventarioLuchadores luchadores){
        super(title);
        
        this.resultados = new TextArea();
        this.batalla = new Batalla();
        this.batalla.setLuchadoresActivos(luchadores);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setSize(800, 600);
        this.resultados.setBounds(100, 100, 600, 400);
        
        this.resultados.setEditable(false);
        this.add(this.resultados);
        this.add(new JLabel());
        
        this.batalla.batallar();
        this.resultados.setText(this.batalla.getResultados());
        
        
        
        
    }

    
}
