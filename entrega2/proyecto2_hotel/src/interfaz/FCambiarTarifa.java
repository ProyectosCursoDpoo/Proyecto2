package interfaz;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FCambiarTarifa extends JFrame implements ActionListener{
    Color fondo = new Color(28, 35, 46);
    FPrincipal principal;
    Administrador admin;
    JComboBox<String> tipo_in;
    JTextField fechas_in;
    JTextField precio_in;


    public FCambiarTarifa(FPrincipal fPrincipal, Administrador admin){
        this.principal = fPrincipal;
        this.admin=admin;
        inicializar();
    }

    public void inicializar(){
        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(fondo);

        String[] tipos = {"Estandar", "Suite", "Suite_Doble"};
        //tipo
        JLabel tipo = new JLabel("Tipo:");
        tipo.setFont(tipo.getFont().deriveFont(20f));
        tipo.setForeground(fondo);
        tipo.setHorizontalAlignment(JLabel.CENTER);
        tipo_in = new JComboBox<>(tipos);

        JLabel fechas = new JLabel("Rango Fechas: (0101-0601)");
        fechas.setFont(fechas.getFont().deriveFont(20f));
        fechas.setForeground(fondo);
        fechas.setHorizontalAlignment(JLabel.CENTER);
        fechas_in = new JTextField();

        JLabel precio = new JLabel("Nuevo Precio:");
        precio.setFont(precio.getFont().deriveFont(20f));
        precio.setForeground(fondo);
        precio.setHorizontalAlignment(JLabel.CENTER);
        precio_in = new JTextField();

        JPanel grilla = new JPanel(new GridLayout(3, 2, 20, 30));
        grilla.setBackground(Color.LIGHT_GRAY);
        grilla.add(tipo);
        grilla.add(tipo_in);
        grilla.add(fechas);
        grilla.add(fechas_in);
        grilla.add(precio);
        grilla.add(precio_in);

        panel1.add(grilla, BorderLayout.CENTER);

        Component spacer = Box.createHorizontalStrut(400);
        Component spacer2 = Box.createHorizontalStrut(400);
        Component spacer3 = Box.createVerticalStrut(100);
        Component spacer4 = Box.createVerticalStrut(200);

        panel1.add(spacer, BorderLayout.WEST);
        panel1.add(spacer2, BorderLayout.EAST);
        panel1.add(spacer4, BorderLayout.NORTH);
        panel1.add(spacer3, BorderLayout.SOUTH);
        

        // Configura el contenedor principal
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.CENTER);

        JButton botonSalida = new JButton("Enviar");
        botonSalida.setFont(botonSalida.getFont().deriveFont(30f));
        botonSalida.setBackground(Color.GREEN);
        botonSalida.setForeground(fondo);
        botonSalida.setPreferredSize(new Dimension(200, 80));
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Enviar");
        this.add(botonSalida, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Cambiar Tarifa");
        titulo.setFont(titulo.getFont().deriveFont(32f));
        titulo.setBackground(fondo);
        titulo.setForeground(fondo);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        this.setBackground(fondo);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width-400, screenSize.height-200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Enviar")) {
            enviar();
            this.dispose();

        }
    }

    public void enviar(){
        
        int text_tipo_in;
        if (tipo_in.getSelectedItem().equals("Estandar")){
            text_tipo_in=1;
        }
        else if (tipo_in.getSelectedItem().equals("Suite")){
            text_tipo_in=2;
        }
        else{
            text_tipo_in=3;
        }

        String text_fechas_in=fechas_in.getText();
        int text_precio_in=Integer.parseInt(precio_in.getText());

        admin.cambiarTarifa(text_tipo_in, text_fechas_in, text_precio_in,this.principal.hotel.tarifasEstandar,this.principal.hotel.tarifasSuite,this.principal.hotel.tarifasSuite2);

    }
    
}
