package interfaz;

import javax.swing.*;

import logica.Administrador;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class FAdmin extends JPanel implements ActionListener{
    FPrincipal principal;
    Administrador admin;

    public FAdmin(FPrincipal fPrincipal){
        this.principal = fPrincipal;
        this.admin = new Administrador();
        inicializar();
    }

    public void inicializar(){
        Color fondo = new Color(28, 35, 46);
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        JButton boton1 = new JButton("Cargar Habitaciones");
        //boton1.setBackground(fondo);
        boton1.setForeground(fondo);
        boton1.setFont(boton1.getFont().deriveFont(18f));
        boton1.setPreferredSize(new Dimension(50, 10));
        boton1.setHorizontalAlignment(JLabel.CENTER);
        boton1.addActionListener(this);
        boton1.setActionCommand("Cargar Habitaciones");

        JButton boton2 = new JButton("Cargar Tarifas");
        //boton2.setBackground(fondo);
        boton2.setForeground(fondo);
        boton2.setFont(boton1.getFont().deriveFont(18f));
        boton2.setPreferredSize(new Dimension(50, 10));
        boton2.setHorizontalAlignment(JLabel.CENTER);
        boton2.addActionListener(this);
        boton2.setActionCommand("Cargar Tarifas");

        JButton boton3 = new JButton("Cargar Menú");
        //boton3.setBackground(fondo);
        boton3.setForeground(fondo);
        boton3.setFont(boton1.getFont().deriveFont(18f));
        boton3.setPreferredSize(new Dimension(50, 10));
        boton3.setHorizontalAlignment(JLabel.CENTER);
        boton3.addActionListener(this);
        boton3.setActionCommand("Cargar Menú");

        JButton boton4 = new JButton("Crear Habitación");
        //boton4.setBackground(fondo);
        boton4.setForeground(fondo);
        boton4.setFont(boton1.getFont().deriveFont(18f));
        boton4.setPreferredSize(new Dimension(50, 10));
        boton4.setHorizontalAlignment(JLabel.CENTER);
        boton4.addActionListener(this);
        boton4.setActionCommand("Crear Habitación");

        JButton boton5 = new JButton("Cambiar Tarifa");
        //boton5.setBackground(fondo);
        boton5.setForeground(fondo);
        boton5.setFont(boton1.getFont().deriveFont(18f));
        boton5.setPreferredSize(new Dimension(50, 10));
        boton5.setHorizontalAlignment(JLabel.CENTER);
        boton5.addActionListener(this);
        boton5.setActionCommand("Cambiar Tarifa");

        JButton boton6 = new JButton("Configurar Plato");
        //boton6.setBackground(fondo);
        boton6.setForeground(fondo);
        boton6.setFont(boton1.getFont().deriveFont(18f));
        boton6.setPreferredSize(new Dimension(50, 10));
        boton6.setHorizontalAlignment(JLabel.CENTER);
        boton6.addActionListener(this);
        boton6.setActionCommand("Configurar Plato");

        JPanel grilla = new JPanel(new GridLayout(2, 3, 100, 150));
        grilla.setBackground(fondo);
        grilla.add(boton1);
        grilla.add(boton2);
        grilla.add(boton3);
        grilla.add(boton4);
        grilla.add(boton5);
        grilla.add(boton6);

        panel1.add(grilla, BorderLayout.CENTER);

        Component spacer = Box.createHorizontalStrut(150);
        Component spacer2 = Box.createHorizontalStrut(150);

        panel1.add(spacer, BorderLayout.WEST);
        panel1.add(spacer2, BorderLayout.EAST);
        
    

        
        // Configura el contenedor principal
        this.setLayout(new BorderLayout(60, 60)); // espacio entre los componentes de 10 píxeles
        this.add(panel1, BorderLayout.CENTER);

        JButton botonSalida = new JButton("Log Out");
        botonSalida.setFont(botonSalida.getFont().deriveFont(18f));
        botonSalida.setBackground(Color.RED);
        botonSalida.setForeground(Color.WHITE);
        botonSalida.setPreferredSize(new Dimension(200, 80));
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Salir");
        this.add(botonSalida, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Administrador");
        titulo.setFont(titulo.getFont().deriveFont(24f));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER); // centra el texto
        this.add(titulo, BorderLayout.NORTH);

        this.setBackground(fondo); // color blanco de fondo
        
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //Administrador admin= new Administrador();
        String comando = e.getActionCommand();
        if (comando.equals("Cargar Habitaciones")){
            
            File archivoHabitaciones = new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/habitaciones2.txt");
            this.admin.cargarHabitaciones(archivoHabitaciones, this.principal.hotel.tarifasEstandar, this.principal.hotel.tarifasSuite, this.principal.hotel.tarifasSuite2, this.principal.hotel.habitaciones);
            JOptionPane.showMessageDialog(this, "Habitaciones Cargadas Exitosamente", "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (comando.equals("Cargar Tarifas")){
            File archivoTarifaEstandar = new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/tarifa.txt");
                File archivoTarifaSuite = new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/tarifa2.txt");
                File archivoTarifaSuite2 = new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/tarifa3.txt");
            this.admin.cargarTarifa(archivoTarifaEstandar, this.principal.hotel.tarifasEstandar);
            this.admin.cargarTarifa(archivoTarifaSuite, this.principal.hotel.tarifasSuite);
            this.admin.cargarTarifa(archivoTarifaSuite2, this.principal.hotel.tarifasSuite2);
            JOptionPane.showMessageDialog(this, "Tarifas Cargadas Exitosamente", "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        else if (comando.equals("Cargar Menú")){
            File archivoMenu = new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/menu.txt");
            this.admin.cargarMenu(archivoMenu, this.principal.hotel.platos);
            JOptionPane.showMessageDialog(this, "Menú Cargado Exitosamente", "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }

        else if (comando.equals("Crear Habitación")){
            new FCrearHabitacion(this.principal, this.admin);
        }
        else if (comando.equals("Cambiar Tarifa")){
            new FCambiarTarifa(this.principal, this.admin);
            
        } 
        else if (comando.equals("Configurar Plato")){
            new FCrearPlato(this.principal, this.admin);
            
        }
        else if (comando.equals("Salir")){
            this.setVisible(false);
            this.principal.hotel.logOut();
        }
    }

    
}
