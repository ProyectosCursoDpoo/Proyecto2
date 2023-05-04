package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.io.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logica.*;

//TODO: MODIFICAR LAS RESERVAS PARA QUE SE GUARDEN

public class Frecep extends JPanel implements ActionListener {
    FPrincipal principal;
    public JLabel titulo;
    public JPanel panelOpciones;
    public Hotel hotel;
    public Recepcionista recepcionista;

    public Frecep(Hotel hotel, FPrincipal principal) {
        this.hotel = hotel;
        this.principal = principal;
        inicializar();
    }

    public void inicializar() {

        Color fondo = new Color(28, 35, 46);
        JPanel panel1 = new JPanel(new GridLayout(3, 3, 10, 10));
        panel1.setBackground(fondo);

        JButton bCoti = new JButton("Dar cotizacion");
        bCoti.setFont(bCoti.getFont().deriveFont(18f));
        bCoti.setPreferredSize(new Dimension(10, 10));
        bCoti.addActionListener(this);
        bCoti.setActionCommand("darcotizacion");
        panel1.add(bCoti);

        JButton bReser = new JButton("Iniciar reserva");
        bReser.setFont(bCoti.getFont().deriveFont(18f));
        bReser.setPreferredSize(new Dimension(10, 10));
        bReser.addActionListener(this);
        bReser.setActionCommand("iniciarreserva");
        panel1.add(bReser);

        JButton bsal = new JButton("Registrar Salida");
        bsal.setFont(bCoti.getFont().deriveFont(18f));
        bsal.setPreferredSize(new Dimension(10, 10));
        bsal.addActionListener(this);
        bsal.setActionCommand("registrarsalida");
        panel1.add(bsal);

        JButton bCan = new JButton("Cancelar Reserva");
        bCan.setFont(bCoti.getFont().deriveFont(18f));
        bCan.setPreferredSize(new Dimension(10, 10));
        bCan.addActionListener(this);
        bCan.setActionCommand("cancelarreserva");
        panel1.add(bCan);

        JPanel opciones = new JPanel(new GridLayout(1, 2, 10, 10));
        opciones.setBackground(fondo);
        opciones.add(panel1);

        // contenedor principal
        setLayout(new BorderLayout(10, 10));
        add(opciones, BorderLayout.CENTER);

        JButton bSalir = new JButton("Salir");
        bSalir.setFont(bSalir.getFont().deriveFont(18f));
        bSalir.setPreferredSize(new Dimension(50, 50));
        bSalir.addActionListener(this);
        bSalir.setActionCommand("salir");
        add(bSalir, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("RECEPCIONISTA");
        titulo.setFont(new Font("Font Awesome 5 Brands", ALLBITS, 30));
        titulo.setBackground(fondo);
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);
        setBackground(fondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("darcotizacion")) {
            System.out.println("Dar cotizacion");
            Fcotizacion ventanaCotizacion = new Fcotizacion(this, this.hotel);
            ventanaCotizacion.setVisible(true);
        } else if (comando.equals("iniciarreserva")) {
            ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
            Freserva ventanaReserva = new Freserva(this, this.hotel, huespedes);
            ventanaReserva.setVisible(true);
        } else if (comando.equals("registrarsalida")) {
            // TODO
        } else if (comando.equals("cancelarreserva")) {
            // TODO
        } else if (comando.equals("salir")) {
            principal.setVisible(true);
            this.setVisible(false);
        }
    }

}
