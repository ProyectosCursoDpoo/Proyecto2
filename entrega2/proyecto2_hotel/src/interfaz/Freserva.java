package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import logica.*;

import javax.swing.*;

public class Freserva extends JFrame implements ActionListener {
    private Frecep frecep;
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // private Recepcionista recepcionista;
    private Hotel hotel;
    public ArrayList<Huesped> huespedes;
    JTextField tNombre;
    JTextField tIdentificacion;
    JTextField tCorreo;
    JTextField tTelefono;
    JTextField tFechaNacimiento;

    public Freserva(Frecep frecep, Hotel hotel, ArrayList<Huesped> huespedes) {
        super("Reserva");
        this.hotel = hotel;
        this.frecep = frecep;
        this.huespedes = huespedes;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBackground(fondo);

        JLabel lNombre = new JLabel("Nombre: ");
        lNombre.setFont(new Font("Georgia", Font.BOLD, 40));
        lNombre.setBackground(fondo);
        lNombre.setForeground(Color.white);
        lNombre.setHorizontalAlignment(JLabel.CENTER);

        this.tNombre = new JTextField();
        tNombre.setFont(new Font("Georgia", Font.BOLD, 40));
        tNombre.setBackground(fondo);
        tNombre.setForeground(Color.white);
        tNombre.setHorizontalAlignment(JLabel.CENTER);

        JLabel lIdentificacion = new JLabel("Identificacion: ");
        lIdentificacion.setFont(new Font("Georgia", Font.BOLD, 40));
        lIdentificacion.setBackground(fondo);
        lIdentificacion.setForeground(Color.white);
        lIdentificacion.setHorizontalAlignment(JLabel.CENTER);

        this.tIdentificacion = new JTextField();
        tIdentificacion.setFont(new Font("Georgia", Font.BOLD, 40));
        tIdentificacion.setBackground(fondo);
        tIdentificacion.setForeground(Color.white);
        tIdentificacion.setHorizontalAlignment(JLabel.CENTER);

        JLabel lCorreo = new JLabel("Correo: ");
        lCorreo.setFont(new Font("Georgia", Font.BOLD, 40));
        lCorreo.setBackground(fondo);
        lCorreo.setForeground(Color.white);
        lCorreo.setHorizontalAlignment(JLabel.CENTER);

        this.tCorreo = new JTextField();
        tCorreo.setFont(new Font("Georgia", Font.BOLD, 40));
        tCorreo.setBackground(fondo);
        tCorreo.setForeground(Color.white);
        tCorreo.setHorizontalAlignment(JLabel.CENTER);

        JLabel lTelefono = new JLabel("Telefono: ");
        lTelefono.setFont(new Font("Georgia", Font.BOLD, 40));
        lTelefono.setBackground(fondo);
        lTelefono.setForeground(Color.white);
        lTelefono.setHorizontalAlignment(JLabel.CENTER);

        this.tTelefono = new JTextField();
        tTelefono.setFont(new Font("Georgia", Font.BOLD, 40));
        tTelefono.setBackground(fondo);
        tTelefono.setForeground(Color.white);
        tTelefono.setHorizontalAlignment(JLabel.CENTER);

        JLabel lFechaNacimiento = new JLabel("Fecha de nacimiento: ");
        lFechaNacimiento.setFont(new Font("Georgia", Font.BOLD, 40));
        lFechaNacimiento.setBackground(fondo);
        lFechaNacimiento.setForeground(Color.white);
        lFechaNacimiento.setHorizontalAlignment(JLabel.CENTER);

        this.tFechaNacimiento = new JTextField();
        tFechaNacimiento.setFont(new Font("Georgia", Font.BOLD, 40));
        tFechaNacimiento.setBackground(fondo);
        tFechaNacimiento.setForeground(Color.white);
        tFechaNacimiento.setHorizontalAlignment(JLabel.CENTER);

        panel.add(lNombre);
        panel.add(tNombre);
        panel.add(lIdentificacion);
        panel.add(tIdentificacion);
        panel.add(lCorreo);
        panel.add(tCorreo);
        panel.add(lTelefono);
        panel.add(tTelefono);
        panel.add(lFechaNacimiento);
        panel.add(tFechaNacimiento);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBackground(fondo);

        JButton bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);

        JButton bRegistrar = new JButton("Registrar Huesped");
        bRegistrar.setFont(new Font("Georgia", Font.BOLD, 40));
        bRegistrar.setBackground(fondo);
        bRegistrar.setForeground(Color.white);
        bRegistrar.addActionListener(this);

        panelBotones.add(bVolver);
        panelBotones.add(bRegistrar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Volver")) {
            frecep.setVisible(true);
            this.dispose();
            hotel.logOut();
        } else if (comando.equals("Registrar Huesped")) {
            String nombre = tNombre.getText();
            String identificacion = tIdentificacion.getText();
            String correo = tCorreo.getText();
            String telefono = tTelefono.getText();
            String fechaNacimiento = tFechaNacimiento.getText();
            if (nombre.equals("") || identificacion.equals("") || correo.equals("") || telefono.equals("")
                    || fechaNacimiento.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                HashMap<String, String> datos = new HashMap<>();
                datos.put("nombre", nombre);
                datos.put("identificacion", identificacion);
                datos.put("correo", correo);
                datos.put("telefono", telefono);
                datos.put("fechaNacimiento", fechaNacimiento);
                Huesped huesped = new Huesped(nombre, Integer.parseInt(identificacion), correo, telefono,
                        fechaNacimiento);
                huespedes.add(huesped);
                FconfirmacionRegistro ventanaConf = new FconfirmacionRegistro(frecep, hotel, huespedes, this);
                ventanaConf.setVisible(true);
            }
        }
    }

}
