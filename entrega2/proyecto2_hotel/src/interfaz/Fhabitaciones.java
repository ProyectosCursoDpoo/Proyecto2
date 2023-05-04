package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import logica.*;

import javax.swing.*;

public class Fhabitaciones extends JFrame implements ActionListener {
    ArrayList<String> habitacionesOcupadas = new ArrayList<String>();
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Recepcionista recepcionista;
    private HashMap<Integer, Habitacion> habitaciones;
    private JPanel panel;
    private JTextField habitacionText;
    private Set<Integer> habitacionesPosibles;
    private String fechaInicio;
    private String fechaFin;

    public Fhabitaciones(HashMap<Integer, Habitacion> habitaciones, String fechaInicio, String fechaFin,
            Recepcionista recepcionista) {
        super("Habitaciones");
        this.habitaciones = habitaciones;
        this.habitacionesPosibles = habitaciones.keySet();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.recepcionista = recepcionista;

        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        if (this.habitaciones.size() > 0) {
            JLabel titulo = new JLabel("Informacion De Habitaciones:");
            titulo.setFont(new Font("Georgia", Font.BOLD, 40));
            titulo.setBackground(fondo);
            titulo.setForeground(Color.BLACK);
            titulo.setHorizontalAlignment(JLabel.CENTER);
            add(titulo, BorderLayout.NORTH);
            this.panel = new JPanel(new GridLayout(1, 8, 10, 10));
            panel.setBackground(fondo);

            infoHabitacion(this.habitaciones);

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

            JButton bFinalizar = new JButton("Dar Cotizacion");
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
                } else {
                    if (habitacionesOcupadas.contains(habitacion)) {
                        JOptionPane.showMessageDialog(this, "Esa habitacion ya la elejiste", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        habitacionesOcupadas.add(habitacion);
                        JOptionPane.showMessageDialog(this, "Habitacion " + habitacion + " elegida", "Exito",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else if (comando.equals("Dar Cotizacion")) {
            if (habitacionesOcupadas.size() == 0) {
                JOptionPane.showMessageDialog(this, "No has elegido ninguna habitacion", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int total = 0;
                for (String h : habitacionesOcupadas) {
                    int numero = Integer.parseInt(h);
                    total += recepcionista.getTarifa_cotizacion(numero, habitaciones, fechaInicio, fechaFin);
                }

                JOptionPane.showMessageDialog(this, "El total de la cotizacion es: " + total, "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.habitacionesOcupadas = new ArrayList<String>();
            }
        } else if (comando.equals("Volver")) {
            this.dispose();
        }
    }

}
