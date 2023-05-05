package interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class FOcupacion extends JFrame implements ActionListener {
    Color fondo = new Color(28, 35, 46);

    public FOcupacion() {
        inicializar();
    }

    public void inicializar() {
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(fondo);

        JLabel[][] ocupaciones = new JLabel[30][12];
        ArrayList<Color> colores = new ArrayList<Color>();
        colores.add(Color.RED);
        colores.add(Color.ORANGE);
        colores.add(Color.GREEN);

        JPanel grilla = new JPanel(new GridLayout(12, 30));
        grilla.setBackground(fondo);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 12; j++) {
                ocupaciones[i][j] = new JLabel(String.valueOf(i + 1) + "-" + String.valueOf(j + 1));
                ocupaciones[i][j].setFont(ocupaciones[i][j].getFont().deriveFont(9f));
                ocupaciones[i][j].setHorizontalAlignment(JLabel.CENTER);
                ocupaciones[i][j].setVerticalAlignment(JLabel.CENTER);

                JPanel panelito = new JPanel(new FlowLayout(FlowLayout.CENTER));
                int randomIndex = (int) (Math.random() * colores.size());
                panelito.setBackground(colores.get(randomIndex));

                panelito.add(ocupaciones[i][j]);
                grilla.add(panelito);
            }
        }

        panel1.add(grilla, BorderLayout.CENTER);

        // create a panel in the right side
        JPanel panelpro = new JPanel(new BorderLayout());
        panelpro.setBackground(fondo);
        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        panel2.setBackground(fondo);
        // add 3 labels
        JLabel label1 = new JLabel("Rojo: Muy Ocupado");
        label1.setFont(label1.getFont().deriveFont(18f));
        label1.setForeground(Color.RED);
        label1.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(label1);

        JLabel label2 = new JLabel("Naranja: Poco Ocupado");
        label2.setFont(label2.getFont().deriveFont(18f));
        label2.setForeground(Color.ORANGE);
        label2.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(label2);

        JLabel label3 = new JLabel("Verde: Más Disponible");
        label3.setFont(label3.getFont().deriveFont(18f));
        label3.setForeground(Color.GREEN);
        label3.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(label3);

        panelpro.add(panel2, BorderLayout.CENTER);
        panel1.add(panelpro, BorderLayout.EAST);

        // spacers
        Component spacer = Box.createVerticalStrut(270);
        Component spacer2 = Box.createHorizontalStrut(150);

        // vvertical spacer
        Component spacer3 = Box.createVerticalStrut(100);
        Component spacer4 = Box.createVerticalStrut(100);
        Component spacer5 = Box.createVerticalStrut(270);

        panel1.add(spacer3, BorderLayout.NORTH);
        panel1.add(spacer4, BorderLayout.SOUTH);
        panel1.add(spacer2, BorderLayout.WEST);

        panelpro.add(spacer, BorderLayout.NORTH);
        panelpro.add(spacer5, BorderLayout.SOUTH);

        Component spacer6 = Box.createHorizontalStrut(150);
        panelpro.add(spacer6, BorderLayout.WEST);

        Component spacer7 = Box.createHorizontalStrut(150);
        panelpro.add(spacer7, BorderLayout.EAST);

        this.setLayout(new BorderLayout()); // espacio entre los componentes de 10 píxeles
        this.add(panel1, BorderLayout.CENTER);

        JButton botonSalida = new JButton("Volver");
        botonSalida.setFont(botonSalida.getFont().deriveFont(35f));
        botonSalida.setBackground(Color.LIGHT_GRAY);
        botonSalida.setForeground(fondo);
        botonSalida.setPreferredSize(new Dimension(200, 80));
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Salir");
        this.add(botonSalida, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Mapa de Ocupación en tiempo real");
        titulo.setFont(titulo.getFont().deriveFont(40f));
        titulo.setForeground(fondo);
        titulo.setHorizontalAlignment(JLabel.CENTER); // centra el texto
        this.add(titulo, BorderLayout.NORTH);

        this.setBackground(fondo); // color blanco de fondo

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // establece el tamaño del marco como las dimensiones de la pantalla
        setSize(screenSize.width - 50, screenSize.height - 50);
        // establece la ubicación del marco en la esquina superior izquierda de la
        // pantalla
        setResizable(false);
        // close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Salir")) {
            this.dispose();
        }
    }

}
