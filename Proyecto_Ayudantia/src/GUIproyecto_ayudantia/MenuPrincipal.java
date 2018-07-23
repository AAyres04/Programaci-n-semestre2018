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
public class MenuPrincipal extends JFrame implements ActionListener {

    JButton batalla;
    JButton summon;
    TextArea infoSummon;
    JTextField numeroSummons;
    JLabel lnumeroSummons;
    InventarioLuchadores luchadoresInvocados;
    
    public MenuPrincipal(String title){
        super(title);
        
        initComponents();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        elementsSize();
        
        addWindow();
        
        this.infoSummon.setEditable(false);
        
        this.batalla.addActionListener(this);
        this.summon.addActionListener(this);
    }
    
    private void initComponents(){
        this.batalla = new JButton("Batalla!");
        this.summon = new JButton("Invocar luchadores");
        this.infoSummon = new TextArea();
        this.numeroSummons = new JTextField();
        this.lnumeroSummons = new JLabel("Cantidad de luchadores que desea invocar (entre 1 al 6)");
        
        this.luchadoresInvocados = new InventarioLuchadores();
    }
    
    private void elementsSize(){
        this.setSize(800,600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.batalla.setBounds(100,300,100,50);
        this.summon.setBounds(80, 200, 150, 50);
        this.infoSummon.setBounds(380, 100, 400, 400);
        this.numeroSummons.setBounds(50, 150, 200, 30);
        this.lnumeroSummons.setBounds(20, 100, 350, 30);
    }
    
    private void addWindow(){
        this.add(this.batalla);
        this.add(this.infoSummon);
        this.add(this.summon);
        this.add(this.numeroSummons);
        this.add(this.lnumeroSummons);
        this.add(new JLabel());
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.summon){
            int numeroIngresado;
            if(inputIsInt()){
                
                numeroIngresado = Integer.parseInt(this.numeroSummons.getText());
                if(numeroIngresado > 0 && numeroIngresado <6){
                    for(int i=0; i<this.luchadoresInvocados.cantidadElementos();i++){
                        this.luchadoresInvocados.rmLuchador(i);
                    }
            
                    for(int i = 0; i<numeroIngresado; i++){
                        this.luchadoresInvocados.addLuchador(new Luchador());
                    }
                    this.infoSummon.setText(this.luchadoresInvocados.mostrarElementos("Luchador"));
                }else if(numeroIngresado >= 6){
                    JOptionPane.showMessageDialog(this, "El numero ingresado es mayor al limite especificado (1 a 6).");
                }else if(numeroIngresado <= 0){
                    JOptionPane.showMessageDialog(this, "El numero ingresado es menor al limite especificado (1 a 6).");
                }
                
            }
            
            
            
            
        } else if (ae.getSource() == this.batalla){
            if(this.luchadoresInvocados.cantidadLuchadores() > 1){
                VentanaBatalla batalla = new VentanaBatalla("Resultados", this.luchadoresInvocados);
                batalla.setVisible(true);
                this.setVisible(false);
            }
        }
    }
    
    private boolean inputIsInt(){
        //condiciones que gatillan la excepción: Que en el textfield se coloque un valor que no sea numerico
        //medidas paliativas de la excepción: Cubre todos los intentos de igresar un caracter que no sea numerico.
        //justificacion del tipo de la excepcion usada: En la documentacion de la clase Integer, aparece dicha excepción ("throws").
        
        try{
            Integer.parseInt(this.numeroSummons.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingreso un valor que no es numerico. Vuelva a intentarlo" + "\n" + e);
            return false;
        }
        return true;
    }
}
