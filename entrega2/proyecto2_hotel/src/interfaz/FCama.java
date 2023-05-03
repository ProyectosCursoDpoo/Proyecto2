package interfaz;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FCama extends JFrame implements ActionListener{
    Color fondo = new Color(28, 35, 46);
    int contador = 0;
    ArrayList<Cama> camas_habitacion = new ArrayList<>();
    JTextField tamano_in;
    JTextField capacidad_in;
    JLabel contador_camas;
    FCrearHabitacion fCrearHabitacion;

    public FCama(FCrearHabitacion fCrearHabitacion){
        this.fCrearHabitacion = fCrearHabitacion;
        inicializar();
    }

    public void inicializar(){
        
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        JLabel tamano = new JLabel("Tamaño:");
        tamano.setFont(tamano.getFont().deriveFont(20f));
        tamano.setHorizontalAlignment(JLabel.CENTER);
        tamano.setForeground(fondo);
        tamano_in = new JTextField("");
        tamano_in.setPreferredSize(new Dimension(50, 30));
    

        JLabel capacidad = new JLabel("Capacidad:");
        capacidad.setFont(capacidad.getFont().deriveFont(20f));
        capacidad.setForeground(fondo);
        capacidad.setHorizontalAlignment(JLabel.CENTER);
        capacidad_in = new JTextField("");
        
        JPanel contador_agregar= new JPanel(new GridLayout(2, 1));
        contador_agregar.setBackground(Color.LIGHT_GRAY);
    

        contador_camas = new JLabel(String.valueOf("0"));
        contador_camas.setHorizontalAlignment(JLabel.CENTER);
        contador_camas.setFont(contador_camas.getFont().deriveFont(18f));
        contador_agregar.add(contador_camas);

        JButton agregar_otra = new JButton("Agregar");
        agregar_otra.setBackground(Color.GREEN);
        agregar_otra.setForeground(fondo);
        agregar_otra.setFont(agregar_otra.getFont().deriveFont(18f));
        agregar_otra.setSize(2000, 30);
        agregar_otra.addActionListener(this);
        agregar_otra.setActionCommand("Agregar");
        contador_agregar.add(agregar_otra);

        JButton guardar = new JButton("Guardar y Salir");
        guardar.setBackground(Color.RED);
        guardar.setForeground(fondo);
        guardar.setFont(guardar.getFont().deriveFont(18f));
        guardar.addActionListener(this);
        guardar.setActionCommand("Salir");
    
        JPanel grilla = new JPanel(new GridLayout(2, 3, 20, 30));
        grilla.setBackground(Color.LIGHT_GRAY);
        grilla.add(tamano);
        grilla.add(tamano_in);
        grilla.add(contador_agregar);
        grilla.add(capacidad);
        grilla.add(capacidad_in);
        grilla.add(guardar);
    
        panel1.add(grilla, BorderLayout.CENTER);

        Component spacer = Box.createHorizontalStrut(250);
        Component spacer2 = Box.createHorizontalStrut(250);
        Component spacer3 = Box.createVerticalStrut(100);
        Component spacer4 = Box.createVerticalStrut(100);

        panel1.add(spacer, BorderLayout.WEST);
        panel1.add(spacer2, BorderLayout.EAST);
        panel1.add(spacer3, BorderLayout.SOUTH);
        panel1.add(spacer4, BorderLayout.NORTH);
    
        // Configura el contenedor principal
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Agregar Camas");
        titulo.setFont(titulo.getFont().deriveFont(32f));
        titulo.setBackground(fondo);
        titulo.setForeground(fondo);
        titulo.setHorizontalAlignment(JLabel.CENTER); 
        this.add(titulo, BorderLayout.NORTH);

        this.setBackground(fondo); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width-800, screenSize.height-250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
    }

    public ArrayList<Cama> getCamas() {
        return this.camas_habitacion;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        if (comando.equals("Agregar")) {
            String tamanio= tamano_in.getText();
            int capacidad= Integer.parseInt(capacidad_in.getText());
            camas_habitacion.add(new Cama(tamanio, capacidad));
            this.contador_camas.setText(String.valueOf(camas_habitacion.size()));
            tamano_in.setText("");
            capacidad_in.setText("");
        } 
        else if (comando.equals("Salir")) {
            this.fCrearHabitacion.cambiarcontador();
            this.dispose();
        }
    }
    
}
