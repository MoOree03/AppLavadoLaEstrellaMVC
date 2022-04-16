/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoslaestrella;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Moreno
 */
public class Modelo {

    private int diaC;
    private int mesC;
    private int periodoC;
    private int diaC1;
    private int mesC1;
    private int periodoC1;
    private String buscarC;
    private String funcionarioC;
    ArrayList<Map> registroArray = new ArrayList<Map>();
    private final double TARIFAAUTOMOVIL = 0.1;
    private final double TARIFACAMIONETA = 0.2;
    private final double LAVADOBASICO = 100;
    private final double LAVADOESPECIAL = 150;
    private final double DESBASICO = 50;
    private final double DESAVANZADO = 100;
    private final double COMBO1 = 150;
    private final double COMBO2 = 200;
    private final double COMBO3 = 250;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">   
    public double getTARIFAAUTOMOVIL() {
        return TARIFAAUTOMOVIL;
    }

    public double getTARIFACAMIONETA() {
        return TARIFACAMIONETA;
    }

    public double getLAVADOBASICO() {
        return LAVADOBASICO;
    }

    public double getLAVADOESPECIAL() {
        return LAVADOESPECIAL;
    }

    public double getDESBASICO() {
        return DESBASICO;
    }

    public double getDESAVANZADO() {
        return DESAVANZADO;
    }

    public double getCOMBO1() {
        return COMBO1;
    }

    public double getCOMBO2() {
        return COMBO2;
    }

    public double getCOMBO3() {
        return COMBO3;
    }

    public int getDiaC() {
        return diaC;
    }

    public void setDiaC(int diaC) {
        this.diaC = diaC;
    }

    public int getMesC() {
        return mesC;
    }

    public void setMesC(int mesC) {
        this.mesC = mesC;
    }

    public int getPeriodoC() {
        return periodoC;
    }

    public void setPeriodoC(int periodoC) {
        this.periodoC = periodoC;
    }

    public int getDiaC1() {
        return diaC1;
    }

    public void setDiaC1(int diaC1) {
        this.diaC1 = diaC1;
    }

    public int getMesC1() {
        return mesC1;
    }

    public void setMesC1(int mesC1) {
        this.mesC1 = mesC1;
    }

    public int getPeriodoC1() {
        return periodoC1;
    }

    public void setPeriodoC1(int periodoC1) {
        this.periodoC1 = periodoC1;
    }

    public String getBuscarC() {
        return buscarC;
    }

    public void setBuscarC(String buscarC) {
        this.buscarC = buscarC;
    }

    public String getFuncionarioC() {
        return funcionarioC;
    }

    public void setFuncionarioC(String funcionarioC) {
        this.funcionarioC = funcionarioC;
    }

    public ArrayList<Map> getRegistroArray() {
        return registroArray;
    }

    // </editor-fold>
    public double calcularPrecio(String tipo, String servicio) {
        double tarifa = 0.0;
        double servicioF = 0.0;
        switch (tipo) {
            case "Automovil":
                tarifa = this.TARIFAAUTOMOVIL;
                break;
            case "Camioneta":
                tarifa = this.TARIFACAMIONETA;
                break;
        }
        switch (servicio) {
            case "LavadoBasico":
                servicioF = this.LAVADOBASICO;
                break;
            case "LavadoEspecial":
                servicioF = this.LAVADOESPECIAL;
                break;
            case "DesBasica":
                servicioF = this.DESBASICO;
                break;
            case "DesAvanzado":
                servicioF = this.DESAVANZADO;
                break;
            case "Combo1":
                servicioF = this.COMBO1;
                break;
            case "Combo2":
                servicioF = this.COMBO2;
                break;
            case "Combo3":
                servicioF = this.COMBO3;
                break;

        }
        return (servicioF * tarifa) + servicioF;
    }

    public void añadirRegistro(Map reg) {
        this.registroArray.add(reg);
    }

    public void mostrar() {
        this.registroArray.forEach((e) -> System.out.println(e));
    }

    public void reporteTipoServicio(String servicio) {
        mostrar();
        char tempStr;
        int count = 0;
        for (Map i : this.registroArray) {
            tempStr = i.get("Servicio").toString().trim().charAt(0);
            if (tempStr == servicio.trim().charAt(0)) {
                count++;
            }
        }
        JOptionPane.showMessageDialog(null, "El servicio: " + servicio + "\n Fue realizado " + count + " veces");
    }

    public void reporteFuncionario(String funcionario) {
        mostrar();
        int count = 0;
        for (Map i : this.registroArray) {
            if (i.get("Funcionario") == funcionario) {
                count++;
            }
        }
        JOptionPane.showMessageDialog(null, "El funcionario: " + funcionario + "\n realizo " + count + " servicios");
    }

    public void reporteRangoFecha(String fechaIni, String fechaFin) throws ParseException {
        mostrar();
        int count = 0;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date feIni = formato.parse(fechaIni);
        Date feFin = formato.parse(fechaFin);
        for (Map i : this.registroArray) {
            Date feOri = formato.parse(i.get("Fecha").toString());
            if (feOri.before(feFin) && feOri.after(feIni)) {
                count++;
            }
        }
        JOptionPane.showMessageDialog(null, "Se han realizado: " + count + "\n servicios desde " + fechaIni + " hasta " + fechaFin);
    }

}
