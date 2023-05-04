package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import logica.*;

import javax.swing.*;

public class FagregarHabitaciones extends JFrame implements ActionListener {

    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // private Frecep frecep;
    // private Freserva freserva;
    private Recepcionista recepcionista;
    private Hotel hotel;
    private ArrayList<Huesped> huespedesRegistrados;
    private ArrayList<Habitacion> habitacionesRegistradas;
    private HashMap<Integer, Habitacion> habitacionesDisponibles;
    private JPanel panel;
    private JTextField habitacionText;
    private Set<Integer> habitacionesPosibles;

    public FagregarHabitaciones(Frecep frecep, Hotel hotel,
            ArrayList<Huesped> huespedes, Freserva freserva, HashMap<Integer, Habitacion> habitaciones) {
        this.hotel = hotel;
        // this.frecep = frecep;
        // this.freserva = freserva;
        this.huespedesRegistrados = huespedes;
        this.habitacionesRegistradas = new ArrayList<>();
        this.habitacionesDisponibles = habitaciones;
        this.habitacionesPosibles = habitaciones.keySet();
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        if (this.habitacionesDisponibles.size() > 0) {
            JLabel titulo = new JLabel("Informacion De Habitaciones:");
            titulo.setFont(new Font("Georgia", Font.BOLD, 40));
            titulo.setBackground(fondo);
            titulo.setForeground(Color.BLACK);
            titulo.setHorizontalAlignment(JLabel.CENTER);
            add(titulo, BorderLayout.NORTH);
            this.panel = new JPanel(new GridLayout(1, 8, 10, 10));
            panel.setBackground(fondo);

            infoHabitacion(this.habitacionesDisponibles);

            add(panel, BorderLayout.CENTER);

            JPanel panel2 = new JPanel(new GridLayout(1, 3, 10, 10));
            panel2.setBackground(fondo);

            JLabel label = new JLabel("#Habitacion: ");
            label.setFont(new Font("Georgia", Font.BOLD, 40));
            label.setBackground(fondo);
            label.setForeground(Color.white);
            label.setHorizontalAlignment(JLabel.CENTER);

            this.habitacionText = new JTextField();
            habitacionText.setFont(new Font("Georgia", Font.BOLD, 40));
            habitacionText.setBackground(new Color(241, 234, 234));
            habitacionText.setForeground(Color.BLACK);
            habitacionText.setHorizontalAlignment(JLabel.CENTER);

            JButton bEnviar = new JButton("Elegir");
            bEnviar.setFont(new Font("Georgia", Font.BOLD, 40));
            bEnviar.setBackground(fondo);
            bEnviar.setForeground(Color.white);
            bEnviar.addActionListener(this);

            JButton bFinalizar = new JButton("Continuar");
            bFinalizar.setFont(new Font("Georgia", Font.BOLD, 40));
            bFinalizar.setBackground(fondo);
            bFinalizar.setForeground(Color.white);
            bFinalizar.addActionListener(this);

            JButton bVolver = new JButton("Volver");
            bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
            bVolver.setBackground(fondo);
            bVolver.setForeground(Color.white);
            bVolver.addActionListener(this);

            panel2.add(label);
            panel2.add(habitacionText);
            panel2.add(bEnviar);
            panel2.add(bFinalizar);
            panel2.add(bVolver);

            add(panel2, BorderLayout.SOUTH);
        }

        setVisible(true);
    }

    public void infoHabitacion(HashMap<Integer, Habitacion> habitaciones) {
        for (Object k : habitaciones.keySet()) {
            Habitacion habitacion = habitaciones.get(k);
            JPanel pHabitacion = new JPanel(new GridLayout(3, 1, 10, 10));
            pHabitacion.setBackground(fondo);
            pHabitacion.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            JLabel nlabel = new JLabel("Numero: " + habitacion.getNumero());
            nlabel.setFont(new Font("Georgia", Font.BOLD, 40));
            nlabel.setBackground(fondo);
            nlabel.setForeground(Color.white);
            nlabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel cLabel = new JLabel("Capacidad: " + habitacion.getCapacidad());
            cLabel.setFont(new Font("Georgia", Font.BOLD, 40));
            cLabel.setBackground(fondo);
            cLabel.setForeground(Color.white);
            cLabel.setHorizontalAlignment(JLabel.CENTER);

            pHabitacion.add(nlabel);
            pHabitacion.add(cLabel);

            if (habitacion instanceof Estandar) {
                JLabel tLabel = new JLabel("Tipo: Estandar");
                tLabel.setFont(new Font("Georgia", Font.BOLD, 40));
                tLabel.setBackground(fondo);
                tLabel.setForeground(Color.white);
                tLabel.setHorizontalAlignment(JLabel.CENTER);
                pHabitacion.add(tLabel);
            } else if (habitacion instanceof Suite) {
                JLabel tLabel = new JLabel("Tipo: Suite");
                tLabel.setFont(new Font("Georgia", Font.BOLD, 40));
                tLabel.setBackground(fondo);
                tLabel.setForeground(Color.white);
                tLabel.setHorizontalAlignment(JLabel.CENTER);
                pHabitacion.add(tLabel);
            } else if (habitacion instanceof Suite_doble) {
                JLabel tLabel = new JLabel("Tipo: Suite Doble");
                tLabel.setFont(new Font("Georgia", Font.BOLD, 40));
                tLabel.setBackground(fondo);
                tLabel.setForeground(Color.white);
                tLabel.setHorizontalAlignment(JLabel.CENTER);
                pHabitacion.add(tLabel);
            }

            this.panel.add(pHabitacion);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String habitacion = habitacionText.getText();
        if (comando.equals("Elegir")) {
            if (habitacion.equals("")) {
                JOptionPane.showMessageDialog(this, "No has ingresado ninguna habitacion", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (!habitacionesPosibles.contains(Integer.parseInt(habitacion))) {
                    JOptionPane.showMessageDialog(this, "Esa habitacion no esta entre las disponibles", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (habitacionesRegistradas
                        .contains(habitacionesDisponibles.get(Integer.parseInt(habitacion)))) {
                    JOptionPane.showMessageDialog(this, "Esa habitacion ya fue elegida", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Habitacion elegida", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    Habitacion habitacionEscogida = habitacionesDisponibles.get(Integer.parseInt(habitacion));
                    habitacionesRegistradas.add(habitacionEscogida);
                }
            }
        } else if (comando.equals("Continuar")) {
            if (habitacionesRegistradas.size() == 0) {
                JOptionPane.showMessageDialog(this, "No has elegido ninguna habitacion", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
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
                        tarifasSuite2, grupos, habitacionesRegistradas, huespedesRegistrados);
            }
        } else if (comando.equals("Volver")) {
            // Volver al menu principal
        }
    }
}