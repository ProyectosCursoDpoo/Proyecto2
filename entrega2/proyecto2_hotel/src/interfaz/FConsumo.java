package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logica.Empleado;
import logica.Staff;


public class FConsumo extends JFrame implements ActionListener {

    private JTextField numeroReservaTextField;
    private JTextArea facturaTextArea;
    private FStaff fStaff;

    public FConsumo(FStaff fStaff) {
        super("Buscar Factura");
        this.fStaff = fStaff;
        inicializar();}

    public void inicializar() {
        // Crear el JLabel y el JTextField para el número de reserva
        JLabel numeroReservaLabel = new JLabel("Número de reserva:");
        numeroReservaTextField = new JTextField(20);

        // Crear el botón de "Buscar"
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(this);

        // Crear el JTextArea para mostrar la factura
        facturaTextArea = new JTextArea(20, 40);
        facturaTextArea.setEditable(false);

        // Agregar los componentes al JFrame
        JPanel panel = new JPanel();
        panel.add(numeroReservaLabel);
        panel.add(numeroReservaTextField);
        panel.add(buscarButton);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(facturaTextArea), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Obtener el número de reserva ingresado por el usuario
        String tnumeroReserva = numeroReservaTextField.getText();
        int numeroReserva= Integer.parseInt(tnumeroReserva);

        // Buscar la factura correspondiente al número de reserva
        Staff staff = new Staff();
        StringBuilder factura = staff.mostrarFacturaPorReserva(fStaff.principal.hotel.consumos, numeroReserva);

        // Mostrar la factura al usuario
        facturaTextArea.setText(factura.toString());
    }

}
