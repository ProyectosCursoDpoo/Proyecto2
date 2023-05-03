package interfaz;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FCrearPlato extends JFrame implements ActionListener{
    Color fondo = new Color(28, 35, 46);
    FPrincipal principal;
    Administrador admin;
    JComboBox<String> lista_in;
    JTextField plato_in;
    JTextField bebida_in;
    JTextField precio_in;
    JTextField hora_in;
    JTextField ubicacion_in;

    public FCrearPlato(FPrincipal fPrincipal, Administrador admin){
        this.principal = fPrincipal;
        this.admin=admin;
        inicializar();
    }

    public void inicializar(){
        
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        
        String[] listica=principal.hotel.platos.keySet().toArray(new String[0]);
        JLabel lista_platos = new JLabel("Plato a Cambiar:");
        lista_platos.setFont(lista_platos.getFont().deriveFont(20f));
        lista_platos.setForeground(fondo);
        lista_platos.setHorizontalAlignment(JLabel.CENTER);
        lista_in = new JComboBox<>(listica);

        JLabel plato = new JLabel("Nombre Nuevo Plato:");
        plato.setFont(plato.getFont().deriveFont(20f));
        plato.setForeground(fondo);
        plato.setHorizontalAlignment(JLabel.CENTER);
        plato_in = new JTextField();

        JLabel bebida = new JLabel("Nombre Nuevo Bebida:");
        bebida.setFont(bebida.getFont().deriveFont(20f));
        bebida.setForeground(fondo);
        bebida.setHorizontalAlignment(JLabel.CENTER);
        bebida_in = new JTextField();

        JLabel precio = new JLabel("Nuevo Precio:");
        precio.setFont(precio.getFont().deriveFont(20f));
        precio.setForeground(fondo);
        precio.setHorizontalAlignment(JLabel.CENTER);
        precio_in = new JTextField();

        JLabel hora = new JLabel("Nuevo Rango Hora:");
        hora.setFont(hora.getFont().deriveFont(20f));
        hora.setForeground(fondo);
        hora.setHorizontalAlignment(JLabel.CENTER);
        hora_in = new JTextField();

        JLabel ubicacion = new JLabel("Nueva Ubicacion:");
        ubicacion.setFont(ubicacion.getFont().deriveFont(20f));
        ubicacion.setForeground(fondo);
        ubicacion.setHorizontalAlignment(JLabel.CENTER);
        ubicacion_in = new JTextField();

        JLabel info = new JLabel("NOTA:");
        info.setFont(info.getFont().deriveFont(20f));
        info.setForeground(Color.RED);
        info.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel info2 = new JLabel("Lo que permaneza vacío no se modificara");
        info2.setFont(info2.getFont().deriveFont(14f));
        info2.setForeground(Color.RED);
        info2.setHorizontalAlignment(JLabel.CENTER);

        JPanel grilla = new JPanel(new GridLayout(7, 2, 20, 30));
        grilla.setBackground(Color.LIGHT_GRAY);
        grilla.add(lista_platos);
        grilla.add(lista_in);
        grilla.add(plato);
        grilla.add(plato_in);
        grilla.add(bebida);
        grilla.add(bebida_in);
        grilla.add(precio);
        grilla.add(precio_in);
        grilla.add(hora);
        grilla.add(hora_in);
        grilla.add(ubicacion);
        grilla.add(ubicacion_in);
        grilla.add(info);
        grilla.add(info2);
        
        panel1.add(grilla, BorderLayout.CENTER);

        Component spacer = Box.createHorizontalStrut(400);
        Component spacer2 = Box.createHorizontalStrut(400);
        Component spacer3 = Box.createVerticalStrut(100);
        Component spacer4 = Box.createVerticalStrut(100);

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

        JLabel titulo = new JLabel("Configurar Plato");
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

        String text_lista_in=lista_in.getSelectedItem().toString();
        String text_plato_in=plato_in.getText();
        String text_bebida_in=bebida_in.getText();
        String text_precio_in=precio_in.getText();
        String text_hora_in=hora_in.getText();
        String text_ubicacion_in=ubicacion_in.getText();
        admin.configurarPlato(text_lista_in, text_plato_in, text_bebida_in, text_precio_in, text_hora_in, text_ubicacion_in,this.principal.hotel.platos);
        

    }
    
}
