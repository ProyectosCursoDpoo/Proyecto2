package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import logica.*;

import javax.swing.*;

public class FconfirmacionRegistro extends JFrame implements ActionListener {

    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Frecep frecep;
    private Freserva freserva;
    private Recepcionista recepcionista;
    private Hotel hotel;
    private ArrayList<Huesped> huespedesRegistrados;

    public FconfirmacionRegistro(Frecep frecep, Hotel hotel,
            ArrayList<Huesped> huespedes, Freserva freserva) {
        super("Confirmacion de registro");
        this.hotel = hotel;
        this.frecep = frecep;
        this.freserva = freserva;
        this.huespedesRegistrados = huespedes;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(fondo);

        JLabel lMensaje = new JLabel("HUESPED REGISTRADO CON EXITO!");
        lMensaje.setFont(new Font("Georgia", Font.BOLD, 60));
        lMensaje.setBackground(fondo);
        lMensaje.setForeground(Color.white);
        lMensaje.setHorizontalAlignment(JLabel.CENTER);

        panel.add(lMensaje);
        add(panel, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBackground(fondo);

        JButton bContinuar = new JButton("Continuar");
        bContinuar.setFont(new Font("Georgia", Font.BOLD, 40));
        bContinuar.setBackground(fondo);
        bContinuar.setForeground(Color.white);
        bContinuar.addActionListener(this);
        bContinuar.setActionCommand("continuar");
        bContinuar.setHorizontalAlignment(JLabel.CENTER);

        JButton bAgregarOtro = new JButton("Agregar otro huesped");
        bAgregarOtro.setFont(new Font("Georgia", Font.BOLD, 40));
        bAgregarOtro.setBackground(fondo);
        bAgregarOtro.setForeground(Color.white);
        bAgregarOtro.addActionListener(this);
        bAgregarOtro.setActionCommand("agregar");
        bAgregarOtro.setHorizontalAlignment(JLabel.CENTER);

        panelBotones.add(bContinuar);
        panelBotones.add(bAgregarOtro);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("continuar")) {
            recepcionista = new Recepcionista();
            HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
            HashMap<Integer, Habitacion> habitacionesDis = recepcionista.habitaciones_disponibles(habitaciones);
            FagregarHabitaciones ventanaAgrHabitac = new FagregarHabitaciones(frecep, hotel, huespedesRegistrados,
                    freserva, habitacionesDis);
            this.dispose();
            freserva.dispose();
            ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
            new Freserva(frecep, hotel, huespedes);
            ventanaAgrHabitac.setVisible(true);
        } else if (comando.equals("agregar")) {
            this.dispose();
            freserva.dispose();
            new Freserva(frecep, hotel, huespedesRegistrados);

        }
    }

}
