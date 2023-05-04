package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import logica.*;

import javax.swing.*;

public class Fcotizacion extends JFrame implements ActionListener {
    private Frecep frecep;
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Recepcionista recepcionista;
    private Hotel hotel;
    public JTextField fechaLlegado;
    public JTextField fechaSalida;

    public Fcotizacion(Frecep frecep, Hotel hotel) {
        super("Cotizacion");
        this.hotel = hotel;
        this.frecep = frecep;
        inicializar();
    }

    public void inicializar() {
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

        JButton bOcupacion = new JButton("Ver Ocupacion");
        bOcupacion.setFont(new Font("Georgia", Font.BOLD, 40));
        bOcupacion.setBackground(fondo);
        bOcupacion.setForeground(Color.white);
        bOcupacion.addActionListener(this);
        bOcupacion.setActionCommand("ocupacion");

        JButton bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        panel.add(bEnviar);
        panel.add(bOcupacion);
        panel.add(bVolver);

        add(panel, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Dar Cotizacion");
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
            String llegada = fechaLlegado.getText();
            String salida = fechaSalida.getText();
            if (llegada.equals("") || salida.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                recepcionista = new Recepcionista();
                HashMap<Integer, Huesped> huespedes = hotel.getHuespedes();
                HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
                HashMap<String, Integer> tarifasEstandar = hotel.getTarifasEstandar();
                HashMap<String, Integer> tarifasSuite = hotel.getTarifasSuite();
                HashMap<String, Integer> tarifasSuite2 = hotel.getTarifasSuite2();

                recepcionista.darCotizacion(llegada, salida, huespedes, habitaciones, tarifasEstandar, tarifasSuite,
                        tarifasSuite2, recepcionista);
            }
        } else if (comando.equals("ocupacion")) {
            recepcionista = new Recepcionista();
        } else if (comando.equals("volver")) {
            frecep.setVisible(true);
            this.dispose();
        }
    }

}
