package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import logica.*;
import logica.Recepcionista;

import java.awt.*;

public class FelegirFechas extends JFrame implements ActionListener {
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JTextField fechaLlegado;
    public JTextField fechaSalida;
    // private Freserva freserva;
    private Hotel hotel;
    private Recepcionista recepcionista;
    private ArrayList<Huesped> huespedesRegistrados;
    private ArrayList<Habitacion> habitacionesRegistradas;

    public FelegirFechas(Hotel hotel,
            ArrayList<Huesped> huespedes, Freserva freserva, ArrayList<Habitacion> habitaciones) {
        super("Elegir Fechas");
        this.hotel = hotel;
        // this.frecep = frecep;
        // this.freserva = freserva;
        this.huespedesRegistrados = huespedes;
        this.habitacionesRegistradas = habitaciones;
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBackground(fondo);

        JPanel info = new JPanel(new GridLayout(3, 3, 10, 10));
        JLabel labelLlegada = new JLabel("Fecha de llegada");

        labelLlegada.setFont(new Font("Georgia", Font.BOLD, 40));
        labelLlegada.setBackground(fondo);
        labelLlegada.setForeground(Color.black);
        labelLlegada.setHorizontalAlignment(JLabel.CENTER);

        fechaLlegado = new JTextField();
        fechaLlegado.setFont(new Font("Georgia", Font.BOLD, 40));
        fechaLlegado.setBackground(fondo);
        fechaLlegado.setForeground(Color.white);
        fechaLlegado.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelSalida = new JLabel("Fecha de salida");
        labelSalida.setFont(new Font("Georgia", Font.BOLD, 40));
        labelSalida.setBackground(fondo);
        labelSalida.setForeground(Color.BLACK);
        labelSalida.setHorizontalAlignment(JLabel.CENTER);
        fechaSalida = new JTextField();
        fechaSalida.setFont(new Font("Georgia", Font.BOLD, 40));
        fechaSalida.setBackground(fondo);
        fechaSalida.setForeground(Color.white);
        fechaSalida.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelInportante = new JLabel("Formato de fecha: mm/dd/aaaa Ej: 01/01/2021");
        labelInportante.setFont(new Font("Georgia", Font.BOLD, 40));
        labelInportante.setSize(getWidth(), 30);
        labelInportante.setBackground(fondo);
        labelInportante.setForeground(Color.BLACK);
        labelInportante.setHorizontalAlignment(JLabel.CENTER);

        info.add(labelLlegada);
        info.add(labelSalida);
        info.add(fechaLlegado);
        info.add(fechaSalida);
        info.add(labelInportante);

        add(info, BorderLayout.CENTER);

        JButton bEnviar = new JButton("Enviar");
        bEnviar.setFont(new Font("Georgia", Font.BOLD, 40));
        bEnviar.setBackground(fondo);
        bEnviar.setForeground(Color.white);
        bEnviar.addActionListener(this);
        bEnviar.setActionCommand("enviar");

        JButton bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        panel.add(bEnviar);
        panel.add(bVolver);

        add(panel, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Elegir Fechas Reservacion");
        titulo.setBackground(fondo);
        titulo.setForeground(Color.BLACK);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 60));
        add(titulo, BorderLayout.NORTH);

        setBackground(fondo);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("enviar")) {
            String fechaLlegada = fechaLlegado.getText();
            String fechaSalida_a = fechaSalida.getText();
            if (fechaLlegada.equals("") || fechaSalida_a.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                recepcionista = new Recepcionista();
                HashMap<Integer, Huesped> huespedes = hotel.getHuespedes();
                HashMap<Integer, reserva> reservas = hotel.getReservas();
                HashMap<String, Integer> tarifasEstandar = hotel.getTarifasEstandar();
                HashMap<String, Integer> tarifasSuite = hotel.getTarifasSuite();
                HashMap<String, Integer> tarifasSuite2 = hotel.getTarifasSuite2();
                HashMap<Integer, Grupo> grupos = hotel.getGrupos();
                HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();

                recepcionista.iniciarReserva(huespedes, reservas, habitaciones,
                        recepcionista, tarifasEstandar,
                        tarifasSuite,
                        tarifasSuite2, grupos, habitacionesRegistradas, huespedesRegistrados, fechaLlegada,
                        fechaSalida_a, hotel);
                // hotel.setReservas(reservasA);
                // System.out.println(reservasA);
                JOptionPane.showMessageDialog(null, "Reserva creada con exito", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } else if (comando.equals("volver")) {
            dispose();
        }
    }

}
