/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autoslaestrella;

/**
 *
 * @author Miguel Moreno
 */
public class AutosLaEstrella {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modelo mod = new Modelo();
        Vista vis = new Vista();
        
        Controlador ctrl = new Controlador(vis,mod);
        ctrl.start();
        vis.setVisible(true);
    }
    
}
