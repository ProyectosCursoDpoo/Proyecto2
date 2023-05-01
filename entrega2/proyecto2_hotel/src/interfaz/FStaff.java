package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.io.*;

public class FStaff extends JPanel implements ActionListener{
    FPrincipal principal;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JPanel servicioPanel;
    private CardLayout opcServicioLayout;
    
    private FServicio pRegistrarServicio;
    private JPanel pMostrarFactura;
    private JButton servicioBoton;
    private JButton facturaBoton;
    private JButton volverBoton;
    private JPanel opcionesPanel;

    public FStaff(FPrincipal fPrincipal){
        //super(new GridLayout(2,1));
        this.principal = fPrincipal;
        inicializar();
    }

    public void inicializar(){
        Color fondo = new Color(28, 35, 46);
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        JButton boton1 = new JButton("Registrar servicio");
        boton1.setFont(boton1.getFont().deriveFont(18f)); // aumenta el tamaño de la fuente
        boton1.setHorizontalAlignment(JLabel.CENTER); // centra el texto
        boton1.addActionListener(this);
        boton1.setActionCommand("registrarServicio");
        ImageIcon icon1 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/servicio.png");
        ImageIcon icon1Scaled = new ImageIcon(icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // escala la imagen
        JLabel image1 = new JLabel(icon1Scaled);
        panel1.add(boton1, BorderLayout.SOUTH);
        panel1.add(image1, BorderLayout.CENTER);

        // Crea el panel para el segundo cuadrado
        JPanel panel2 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel2.setBackground(fondo);

        JButton boton2 = new JButton("Consultar consumo por reserva");
        boton2.setFont(boton2.getFont().deriveFont(18f)); // aumenta el tamaño de la fuente
        boton2.setHorizontalAlignment(JLabel.CENTER); // centra el texto
        boton2.addActionListener(this);
        boton2.setActionCommand("consultarConsumo");
        ImageIcon icon2 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/factura.png");
        ImageIcon icon2Scaled = new ImageIcon(icon2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // escala la imagen
        JLabel image2 = new JLabel(icon2Scaled);
        panel2.add(boton2, BorderLayout.SOUTH);
        panel2.add(image2, BorderLayout.CENTER);

        // Crea un panel para los cuadrados y añade los paneles a él
        JPanel cuadrados = new JPanel(new GridLayout(1, 2, 10, 10)); // espacio entre los cuadrados de 10 píxeles
        cuadrados.setBackground(fondo);
        cuadrados.add(panel1);
        cuadrados.add(panel2);

        // Configura el contenedor principal
        setLayout(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        add(cuadrados, BorderLayout.CENTER);
        JButton botonSalida = new JButton("Salir");
        botonSalida.setFont(botonSalida.getFont().deriveFont(18f)); // aumenta el tamaño de la fuente
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Salir");
        add(botonSalida, BorderLayout.SOUTH);
        JLabel titulo = new JLabel("Staff");
        titulo.setFont(titulo.getFont().deriveFont(24f)); // aumenta el tamaño de la fuente
        titulo.setHorizontalAlignment(JLabel.CENTER); // centra el texto
        add(titulo, BorderLayout.NORTH);
        setBackground(fondo); // color blanco de fondo
        
    }



    public void actionPerformed(ActionEvent pEvento){
        String comando = pEvento.getActionCommand();
        if (comando.equals("registrarServicio")){
            FServicio ventanaServicio = new FServicio(this);
            ventanaServicio.setVisible(true);
        }
        else if (comando.equals("consultarConsumo")){
            cardLayout.show(cardsPanel, "mostrarFactura");
        }
        else if (comando.equals("Salir")){
            cardLayout.first(cardsPanel);
        }
    }

    private class FServicio extends JFrame{
        private CardLayout cardLayout;
        private JPanel cardPanel;
        private JPanel restaurantePanel;
        private JPanel spaPanel;
        private JPanel guiaPanel;
        private JButton restauranteBoton;
        private JButton spaBoton;
        private JButton guiaBoton;
        private JPanel opcionesPanel;

        public FServicio(FStaff fStaff) {
            super("Mi Ventana Principal");
            setSize(fStaff.getHeight(),fStaff.getWidth());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el CardLayout y el panel que contendrá las diferentes tarjetas
            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);

            // Crear los paneles de las diferentes tarjetas
            restaurantePanel = new JPanel();
            panelRestaurante(restaurantePanel, fStaff);
            spaPanel = new JPanel();
            spaPanel.add(new JLabel("Aquí se agregarían las opciones para spa"));
            guiaPanel = new JPanel();
            guiaPanel.add(new JLabel("Aquí se agregarían las opciones para guía turística"));

            // Agregar los paneles de las diferentes tarjetas al panel principal
            cardPanel.add(restaurantePanel, "restaurante");
            cardPanel.add(spaPanel, "spa");
            cardPanel.add(guiaPanel, "guia");

            // Crear el panel con las opciones que cambiarán las tarjetas
            restauranteBoton = new JButton("Restaurante");
            spaBoton = new JButton("Spa");
            guiaBoton = new JButton("Guia Turística");
            opcionesPanel = new JPanel();
            opcionesPanel.add(restauranteBoton);
            opcionesPanel.add(spaBoton);
            opcionesPanel.add(guiaBoton);

            // Agregar un ActionListener a los botones para cambiar las tarjetas
            restauranteBoton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "restaurante");
                }
            });
            spaBoton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "spa");
                }
            });
            guiaBoton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "guia");
                }
            });

            // Agregar los paneles al panel principal
            add(opcionesPanel, BorderLayout.NORTH);
            add(cardPanel, BorderLayout.CENTER);

            setVisible(true);
        }

        private JRadioButton l1;
        private JRadioButton l2;
        private int lugar = 1;

        private void panelRestaurante(JPanel pR, FStaff fStaff){
            

            setLayout(new FlowLayout());

            JLabel opcLugar = new JLabel("Donde desea tomar su servicio: ");
            add(opcLugar);

            JRadioButton l1 = new JRadioButton("Restaurante", true);
            JRadioButton l2 = new JRadioButton("Habitacion");

            ButtonGroup grupoBotones = new ButtonGroup();
            grupoBotones.add(l1);
            grupoBotones.add(l2);

            add(l1);
            add(l2);

            l1.addActionListener(fStaff);
            l2.addActionListener(fStaff);
            
            System.out.println(lugar);
            String [][] menu = fStaff.principal.hotel.mostrarMenu(lugar);
            System.out.println(menu);
            DefaultListModel<String> model = new DefaultListModel<>(); // Crear el modelo de lista
            for (String[] plato : menu) {
                String nombre = plato[0];
                String precio = plato[1];
                String item = nombre + " - $" + precio;
                model.addElement(item); // Agregar el elemento al modelo de lista
                }
            JList<String> lista = new JList<>(model);
            add(lista);
        }

        public void actionPerformed( ActionEvent pEvento )
        {   
            if (l1.isSelected())
                lugar = 1;
            else if (l2.isSelected()) 
                lugar = 2;
        }  

    }
}


