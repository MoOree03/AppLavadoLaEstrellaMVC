/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoslaestrella;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Moreno
 */
public class Controlador {

    private Vista view;
    private Modelo model;

    public Controlador(Vista view, Modelo model) {
        this.view = view;
        this.model = model;
        this.view.btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConsultaactionPerformed(e);
            }
        });
        this.view.btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelarActionPerformed(e);
            }
        });
        this.view.btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRegistroActionPerformed(e);
            }
        });
        this.view.cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });
        this.view.cmbLavado.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                cmbLavadoItemStateChanged(evt);
            }
        });
        this.view.cmbDesinfeccion.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                cmbDesItemStateChanged(evt);
            }
        });
        this.view.cmbCombos.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                cmbComboItemStateChanged(evt);
            }
        });
    }

    public void start() {
        view.setTitle("Autos La Estrella");
        view.setLocationRelativeTo(null);
        inicio();
    }

    public void btnRegistroActionPerformed(ActionEvent e) {
        if (validarRegistro()) {
            Map<String, String> registroDic = new HashMap<String, String>();
            String fecha = view.cmbDia.getSelectedItem() + "/" + view.cmbMes.getSelectedItem() + "/" + view.cmbPeriodo.getSelectedItem();
            registroDic.put("Fecha", fecha);
            registroDic.put("Funcionario", view.cmbFuncionario1.getSelectedItem().toString());
            registroDic.put("TipoVehiculo", view.cmbTipoVehiculo.getSelectedItem().toString());
            registroDic.put("Servicio", selectedServiceItem());
            registroDic.put("Precio", String.valueOf(model.calcularPrecio(view.cmbTipoVehiculo.getSelectedItem().toString(), selectedServiceItem())));
            model.añadirRegistro(registroDic);
            JOptionPane.showMessageDialog(null, "Se ha registrado el servicio!");
            reinicioRegistro();
            inicioCmbRegistro();
        }

    }

    public void btnConsultaactionPerformed(ActionEvent e) {
        if (view.cmbBuscar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la busqueda!");
            return;
        }
        model.setBuscarC(view.cmbBuscar.getSelectedItem().toString());
        if (validarConsulta()) {       
            if (view.cmbFuncionario.isVisible()) {
                model.setFuncionarioC(view.cmbFuncionario.getSelectedItem().toString());
                model.reporteFuncionario((model.getFuncionarioC()));
            }
            if (view.cmbTipoServicio.isVisible()) {
                model.reporteTipoServicio(view.cmbTipoServicio.getSelectedItem().toString());
            }
            if (view.lbFecha.isVisible()) {
                String fechaIni = view.cmbDia1.getSelectedItem().toString() + "/" + view.cmbMes1.getSelectedItem().toString() + "/" + view.cmbPeriodo1.getSelectedItem().toString();
                String fechaFin = view.cmbDia2.getSelectedItem().toString() + "/" + view.cmbMes2.getSelectedItem().toString() + "/" + view.cmbPeriodo2.getSelectedItem().toString();
                model.setDiaC(Integer.parseInt(view.cmbDia1.getSelectedItem().toString()));
                model.setMesC(Integer.parseInt(view.cmbMes1.getSelectedItem().toString()));
                model.setPeriodoC(Integer.parseInt(view.cmbPeriodo1.getSelectedItem().toString()));
                model.setDiaC1(Integer.parseInt(view.cmbDia2.getSelectedItem().toString()));
                model.setMesC1(Integer.parseInt(view.cmbMes2.getSelectedItem().toString()));
                model.setPeriodoC1(Integer.parseInt(view.cmbPeriodo2.getSelectedItem().toString()));
                try {
                    model.reporteRangoFecha(fechaIni, fechaFin);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        reinicioConsulta();
    }

    public void inicio() {
        view.lbHasta.setVisible(false);
        view.lbFuncionario1.setVisible(false);
        view.cmbFuncionario.setVisible(false);
        view.lbTipoServicio.setVisible(false);
        view.cmbDia1.setVisible(false);
        view.cmbMes1.setVisible(false);
        view.cmbPeriodo1.setVisible(false);
        view.cmbDia2.setVisible(false);
        view.cmbMes2.setVisible(false);
        view.cmbPeriodo2.setVisible(false);
        view.cmbTipoServicio.setVisible(false);
        view.lbFecha.setVisible(false);

    }

    public void inicioCmbRegistro() {
        view.cmbDesinfeccion.setEnabled(true);
        view.cmbCombos.setEnabled(true);
        view.cmbLavado.setEnabled(true);
    }

    public void reinicioRegistro() {
        view.cmbDesinfeccion.setSelectedIndex(-1);
        view.cmbLavado.setSelectedIndex(-1);
        view.cmbCombos.setSelectedIndex(-1);
        view.cmbDia.setSelectedIndex(-1);
        view.cmbMes.setSelectedIndex(-1);
        view.cmbPeriodo.setSelectedIndex(-1);
        view.cmbFuncionario1.setSelectedIndex(-1);
        view.cmbTipoVehiculo.setSelectedIndex(-1);
    }

    public void reinicioConsulta() {
        view.cmbFuncionario.setSelectedIndex(-1);
        view.cmbTipoServicio.setSelectedIndex(-1);
        view.cmbDia1.setSelectedIndex(-1);
        view.cmbMes1.setSelectedIndex(-1);
        view.cmbPeriodo1.setSelectedIndex(-1);
        view.cmbDia2.setSelectedIndex(-1);
        view.cmbMes2.setSelectedIndex(-1);
        view.cmbPeriodo2.setSelectedIndex(-1);
    }

    public void cmbBuscarItemStateChanged(ItemEvent e) {
        inicio();
        if (view.cmbBuscar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la busqueda!");
            return;
        }
        String seleccion = view.cmbBuscar.getSelectedItem().toString();
        switch (seleccion) {
            case ("Tipo Servicio"):
                view.cmbTipoServicio.setVisible(true);
                view.lbTipoServicio.setVisible(true);
                break;
            case ("Funcionario"):
                view.cmbFuncionario.setVisible(true);
                view.lbFuncionario1.setVisible(true);
                break;
            case ("Fechas"):
                view.lbHasta.setVisible(true);
                view.lbFecha.setVisible(true);
                view.cmbDia1.setVisible(true);
                view.cmbMes1.setVisible(true);
                view.cmbPeriodo1.setVisible(true);
                view.cmbDia2.setVisible(true);
                view.cmbMes2.setVisible(true);
                view.cmbPeriodo2.setVisible(true);
                view.lbFecha.setVisible(true);
                break;
        }
    }

    public void cmbLavadoItemStateChanged(ItemEvent e) {
        inicioCmbRegistro();
        view.cmbDesinfeccion.setEnabled(false);
        view.cmbCombos.setEnabled(false);
    }

    public void cmbDesItemStateChanged(ItemEvent e) {
        inicioCmbRegistro();
        view.cmbLavado.setEnabled(false);
        view.cmbCombos.setEnabled(false);
    }

    public void cmbComboItemStateChanged(ItemEvent e) {
        inicioCmbRegistro();
        view.cmbDesinfeccion.setEnabled(false);
        view.cmbLavado.setEnabled(false);
    }

    public void btnCancelarActionPerformed(ActionEvent e) {
        view.cmbDesinfeccion.setSelectedIndex(-1);
        view.cmbLavado.setSelectedIndex(-1);
        view.cmbCombos.setSelectedIndex(-1);
        inicioCmbRegistro();
    }

    public boolean validarRegistro() {
        if (view.cmbDia.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el dia!");
            return false;
        }
        if (view.cmbMes.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el mes!");
            return false;
        }
        if (view.cmbPeriodo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el año!");
            return false;
        }
        if (view.cmbFuncionario1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el funcionario!");
            return false;
        }
        if (view.cmbTipoVehiculo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo del vehiculo!");
            return false;
        }

        if (view.cmbLavado.getSelectedIndex() == -1 && view.cmbDesinfeccion.getSelectedIndex() == -1 && view.cmbCombos.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el servicio!");
            return false;
        }
        return true;
    }

    public boolean validarConsulta() {
                
        switch (model.getBuscarC()) {
            case "Tipo Servicio":
                if (view.cmbTipoServicio.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de servicio!");
                    return false;
                }
                break;
            case "Funcionario":
                if (view.cmbFuncionario.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el funcionario!");
                    return false;
                }
                break;
            case "Fechas":
                if (view.cmbDia1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el dia de inicio del rango!");
                    return false;
                }
                if (view.cmbMes1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el mes de inicio del rango!");
                    return false;
                }
                if (view.cmbPeriodo1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el año de inicio del rango!");
                    return false;
                }
                if (view.cmbDia2.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el dia de fin del rango!");
                    return false;
                }
                if (view.cmbMes2.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el mes de fin del rango!");
                    return false;
                }
                if (view.cmbPeriodo2.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el año de fin del rango!");
                    return false;
                }
                break;
        }
        return true;
    }

    public String selectedServiceItem() {
        String tempService = "";
        if (view.cmbDesinfeccion.getSelectedIndex() == -1 && view.cmbCombos.getSelectedIndex() == -1) {
            tempService = view.cmbLavado.getSelectedItem().toString();
        } else if (view.cmbDesinfeccion.getSelectedIndex() == -1 && view.cmbLavado.getSelectedIndex() == -1) {
            tempService = view.cmbCombos.getSelectedItem().toString();
        } else if (view.cmbLavado.getSelectedIndex() == -1 && view.cmbCombos.getSelectedIndex() == -1) {
            tempService = view.cmbDesinfeccion.getSelectedItem().toString();
        }
        return tempService;
    }
}
