package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import logica.*;
import javax.swing.*;

public class FcancelarReserva extends JFrame implements ActionListener {
    JTextField tId;
    JButton bCancelar;
    JButton bVolver;
    Recepcionista recepcionista;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Color fondo = new Color(28, 35, 46);
    private Hotel hotel;

    public FcancelarReserva(Hotel hotel) {
        super("Cancelar Reserva");
        this.hotel = hotel;
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBackground(fondo);

        JLabel lId = new JLabel("Id Reserva: ");
        lId.setFont(new Font("Georgia", Font.BOLD, 40));
        lId.setBackground(fondo);
        lId.setForeground(Color.white);
        lId.setHorizontalAlignment(JLabel.CENTER);

        this.tId = new JTextField();
        tId.setFont(new Font("Georgia", Font.BOLD, 40));
        tId.setBackground(fondo);
        tId.setForeground(Color.white);
        tId.setHorizontalAlignment(JLabel.CENTER);

        panel.add(lId);
        panel.add(tId);

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));
        botones.setBackground(fondo);

        this.bCancelar = new JButton("Cancelar la reserva");
        bCancelar.setFont(new Font("Georgia", Font.BOLD, 40));
        bCancelar.setBackground(fondo);
        bCancelar.setForeground(Color.white);
        bCancelar.addActionListener(this);
        bCancelar.setActionCommand("cancelar");

        this.bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        botones.add(bCancelar);
        botones.add(bVolver);

        add(panel, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("cancelar")) {
            String id = tId.getText();
            HashMap<Integer, reserva> reservas = hotel.getReservas();
            recepcionista = new Recepcionista();
            // revisar que el Id si sea un numero y que exista en las reservas
            if (id.matches("[0-9]+") && reservas.containsKey(Integer.parseInt(id)) && !id.equals("")) {
                HashMap<Integer, Grupo> grupos = hotel.getGrupos();
                recepcionista.cancelarReserva(Integer.parseInt(id), reservas, grupos, this.hotel);
                JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente", "Reserva cancelada",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                hotel.logOut();
            } else {
                JOptionPane.showMessageDialog(null, "Id invalido", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (comando.equals("volver")) {
            dispose();
            String id = tId.getText();
            if (!id.equals("")) {
                hotel.logOut();
            }
        }
    }

}
