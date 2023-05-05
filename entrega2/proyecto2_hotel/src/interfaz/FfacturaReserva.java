package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logica.*;

public class FfacturaReserva extends JFrame implements ActionListener {
    Recepcionista recepcionista;
    Staff staff;
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JButton bVolver;
    JButton bPagar;
    JPanel panelFactura;
    JTextArea areaTexto;
    JScrollPane scrollPane;
    private int id;
    private Hotel hotel;

    public FfacturaReserva(Hotel hotel, int id) {
        super("Factura Reserva");
        this.hotel = hotel;
        this.id = id;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        this.panelFactura = new JPanel();
        panelFactura.setBackground(fondo);

        JLabel factura = new JLabel("Factura");
        factura.setFont(new java.awt.Font("Georgia", 1, 40));
        factura.setForeground(Color.white);
        panelFactura.add(factura);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBackground(fondo);

        this.bVolver = new JButton("Volver");
        bVolver.setFont(new java.awt.Font("Georgia", 1, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        this.bPagar = new JButton("Pagar");
        bPagar.setFont(new java.awt.Font("Georgia", 1, 40));
        bPagar.setBackground(fondo);
        bPagar.setForeground(Color.white);
        bPagar.addActionListener(this);
        bPagar.setActionCommand("pagar");

        panelBotones.add(bVolver);
        panelBotones.add(bPagar);

        factura();

        panelFactura.add(factura);

        add(panelFactura, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);

    }

    public void factura() {
        recepcionista = new Recepcionista();
        staff = new Staff();
        HashMap<Integer, reserva> reservas = hotel.getReservas();
        HashMap<Integer, Consumo> consumos = hotel.getConsumos();
        HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
        String info = recepcionista.registrarSalida(id, reservas, staff, consumos, habitaciones, this.hotel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 400);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText(info);
        areaTexto.setBounds(20, 50, 300, 200);

        scrollPane.setViewportView(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("volver")) {
            this.dispose();
        } else if (comando.equals("pagar")) {
            JOptionPane.showMessageDialog(this, "¿Desea pagar la factura?", "Pagar", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, "Factura pagada");
            hotel.logOut();
            this.dispose();
        }
    }

}
