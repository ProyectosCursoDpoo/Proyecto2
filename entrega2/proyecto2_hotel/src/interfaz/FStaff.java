package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FStaff extends JPanel implements ActionListener {
    FPrincipal principal;

    public FStaff(FPrincipal fPrincipal) {
        // super(new GridLayout(2,1));
        this.principal = fPrincipal;
        inicializar();
    }

    public void inicializar() {
        Color fondo = new Color(28, 35, 46);
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        JButton boton1 = new JButton("Registrar servicio");
        boton1.setFont(boton1.getFont().deriveFont(18f)); 
        boton1.setHorizontalAlignment(JLabel.CENTER); 
        boton1.setPreferredSize(new Dimension(50, 50));
        boton1.addActionListener(this);
        boton1.setActionCommand("registrarServicio");
        ImageIcon icon1 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/servicio.png");
        ImageIcon icon1Scaled = new ImageIcon(icon1.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)); // escala
                                                                                                                 // la
                                                                                                                 // imagen
        JLabel image1 = new JLabel(icon1Scaled);
        panel1.add(boton1, BorderLayout.SOUTH);
        panel1.add(image1, BorderLayout.CENTER);

        // Crea el panel para el segundo cuadrado
        JPanel panel2 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel2.setBackground(fondo);

        JButton boton2 = new JButton("Consultar consumo por reserva");
        boton2.setFont(boton2.getFont().deriveFont(18f)); 
        boton2.setHorizontalAlignment(JLabel.CENTER); 
        boton2.setPreferredSize(new Dimension(50, 50));
        boton2.addActionListener(this);
        boton2.setActionCommand("consultarConsumo");
        ImageIcon icon2 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/factura.png");
        ImageIcon icon2Scaled = new ImageIcon(icon2.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)); // escala
                                                                                                                 // la
                                                                                                                 // imagen
        JLabel image2 = new JLabel(icon2Scaled);
        panel2.add(boton2, BorderLayout.SOUTH);
        panel2.add(image2, BorderLayout.CENTER);

        // Crea un panel para los cuadrados y añade los paneles a él
        JPanel cuadrados = new JPanel(new GridLayout(1, 2, 10, 10)); // espacio entre los cuadrados de 10 píxeles
        cuadrados.setBackground(fondo);
        cuadrados.add(panel1);
        cuadrados.add(panel2);

        // Configura el contenedor principal
        setLayout(new BorderLayout(10, 10)); 
        add(cuadrados, BorderLayout.CENTER);
        JButton botonSalida = new JButton("Salir");
        botonSalida.setFont(botonSalida.getFont().deriveFont(18f)); 
        botonSalida.setPreferredSize(new Dimension(50, 50));
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Salir");
        add(botonSalida, BorderLayout.SOUTH);
        JLabel titulo = new JLabel("Staff");
        titulo.setFont(new Font("Georgia", Font.BOLD, 50));        
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER); 
        add(titulo, BorderLayout.NORTH);
        setBackground(fondo);
        
    }

    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();
        if (comando.equals("registrarServicio")) {
            System.out.println("Registrar servicio");
            FServicio ventanaServicio = new FServicio(this);
            ventanaServicio.setVisible(true);
        } else if (comando.equals("consultarConsumo")) {
            FConsumo ventanaConsumo = new FConsumo(this);
            ventanaConsumo.setVisible(true);
        } else if (comando.equals("Salir")) {
            principal.setVisible(true);
            this.setVisible(false);   
            this.principal.hotel.logOut();
            }
    }

}
