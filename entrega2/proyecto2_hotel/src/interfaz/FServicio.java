package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;

import logica.Consumo;
import logica.Hotel;
import logica.Staff;

public class FServicio extends JFrame implements ActionListener {
    private FStaff fStaff;
    Color fondo = new Color(28, 35, 46);
    private int lugar = 1;
    private ArrayList<Integer> listaPedido;
    private boolean pago;
    private int numReserva, numServicio, cantGuia= 1, nPlato= 0;
    private JTextField cantGuiaField, numReservaField, textField = new JTextField(10);
    private JComboBox<String> ubicacionBox, pagoBox;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public FServicio(FStaff fStaff) {
        super("Registrar servicio");
        this.fStaff = fStaff;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width-400, screenSize.height-350);
        setLocationRelativeTo(fStaff);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(fondo);
        JButton boton1 = new JButton("Restaurante");
        boton1.setFont(new Font("Georgia", Font.BOLD, 25));
        boton1.setBackground(fondo);
        boton1.setForeground(Color.white);
        boton1.addActionListener(this);
        boton1.setActionCommand("restaurante");
        JButton boton2 = new JButton("Spa");
        boton2.setFont(new Font("Georgia", Font.BOLD, 25));
        boton2.setBackground(fondo);
        boton2.setForeground(Color.white);
        boton2.addActionListener(this);
        boton2.setActionCommand("spa");
        JButton boton3 = new JButton("Guia turística");
        boton3.setFont(new Font("Georgia", Font.BOLD, 25));
        boton3.setBackground(fondo);
        boton3.setForeground(Color.white);
        boton3.addActionListener(this);
        boton3.setActionCommand("guia");

        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);

        add(panel, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Elija el servicio a registrar: ");
        titulo.setBackground(fondo);
        titulo.setOpaque(true);
        titulo.setForeground(Color.white);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 40));
        add(titulo, BorderLayout.NORTH);

        setVisible(true);
    }

    private class PanelRestaurante extends JFrame implements ActionListener{
        private FServicio fServicio;

        public PanelRestaurante(FServicio fServicio){
            super("Registrar servicio de restaurante");
            this.fServicio = fServicio;
            inicializar();
        }

        public void inicializar() {
            setLayout(new BorderLayout());
            setSize(screenSize.width - 50, screenSize.height - 80);
            setLocationRelativeTo(fServicio.fStaff);
        
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBackground(fondo);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
            JLabel ubicacion = new JLabel("Ubicación:");
            ubicacion.setForeground(Color.white);
            ubicacion.setFont(new Font("Georgia", Font.BOLD, 20));
            ubicacionBox = new JComboBox<>(new String[]{"Restaurante", "Habitación"});
        
            JLabel numReservaLabel = new JLabel("Número de reserva:");
            numReservaLabel.setForeground(Color.white);
            numReservaLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            numReservaField = new JTextField(10);
        
            JLabel pagoLabel = new JLabel("Tipo de pago:");
            pagoLabel.setForeground(Color.white);
            pagoLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            pagoBox = new JComboBox<>(new String[]{"Inmediato", "Al final de la estadía"});
        
            JLabel pedidoLabel = new JLabel("Productos disponibles en el menú:");
            pedidoLabel.setForeground(Color.white);
            pedidoLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        
            String[][] menu = fStaff.principal.hotel.mostrarMenu(lugar);
            DefaultListModel<String> model = new DefaultListModel<>(); // Crear el modelo de lista
            int i = 0;
            for (String[] plato : menu) {
                String nombre = plato[0];
                String precio = plato[1];
                i++;
                String item = i + "-" + nombre + " - $" + precio;
                model.addElement(item); // Agregar el elemento al modelo de lista
            }
            JList<String> lista = new JList<>(model);
            JScrollPane scrollPane = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
            JButton addButton = new JButton("Agregar");
            addButton.addActionListener(this);
            addButton.setActionCommand("agregar");
            addButton.setForeground(Color.white);
            addButton.setFont(new Font("Georgia", Font.BOLD, 20));
            listaPedido = new ArrayList<>();
        
            lista.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        String plato = lista.getSelectedValue();
                        textField.setText(plato + " ."); // Mostrar el número de plato en el JTextField
                        String[] x = plato.split("-");
                        nPlato = Integer.parseInt(x[0]);
                    }
                }
            });
        
            panel.add(ubicacion);
            panel.add(ubicacionBox);
            panel.add(numReservaLabel);
            panel.add(numReservaField);
            panel.add(pagoLabel);
            panel.add(pagoBox);
            panel.add(pedidoLabel);
            panel.add(scrollPane);
            panel.add(textField);
            panel.add(addButton);
        
            add(panel, BorderLayout.CENTER);
        
            JButton terminar = new JButton("Terminar");
            terminar.setForeground(Color.white);
            terminar.setFont(new Font("Georgia", Font.BOLD, 20));
            terminar.addActionListener(this);
            terminar.setActionCommand("terminar");
            terminar.setBackground(Color.RED);
            add(terminar, BorderLayout.SOUTH);
        
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            Staff staff = new Staff();
            String comando = e.getActionCommand();
            if (nPlato == 0 & comando.equals("agregar")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un plato del menú", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (comando.equals("agregar")){
                listaPedido.add(nPlato);
                nPlato = 0;
                textField.setText("");
            } else if (comando.equals("terminar")){
                if (numReservaField.equals("") || numReservaField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Debe ingresar el número de reserva", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (listaPedido.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe agregar al menos un plato al pedido", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    terminar();
                    dispose();
                }
            }
        }
    }
 
    private class panelGuia extends JFrame implements ActionListener{
        private FServicio fServicio;

        public panelGuia(FServicio fServicio){
            super("Registrar servicio de guía turística");
            this.fServicio = fServicio;
            inicializar();
        }

        public void inicializar() {
            setLayout(new BorderLayout());
            setLocationRelativeTo(fServicio);
            setSize(screenSize.width - 50, screenSize.height - 80);
        
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBackground(fondo);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
            JLabel numReservaLabel = new JLabel("Número de reserva:");
            numReservaLabel.setForeground(Color.white);
            numReservaLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            numReservaField = new JTextField(10);
        
            JLabel pagoLabel = new JLabel("Tipo de pago:");
            pagoLabel.setForeground(Color.white);
            pagoLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            pagoBox = new JComboBox<>(new String[]{"Inmediato", "Al final de la estadía"});
        
            JLabel cantGuiaLabel = new JLabel("Cantidad de personas que tomarán el servicio:");
            cantGuiaLabel.setForeground(Color.white);
            cantGuiaLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            cantGuiaField = new JTextField(10);
        
            panel.add(numReservaLabel);
            panel.add(numReservaField);
            panel.add(pagoLabel);
            panel.add(pagoBox);
            panel.add(cantGuiaLabel);
            panel.add(cantGuiaField);
        
            add(panel, BorderLayout.CENTER);
        
            JButton terminar = new JButton("Terminar");
            terminar.setForeground(Color.white);
            terminar.setFont(new Font("Georgia", Font.BOLD, 20));
            terminar.addActionListener(this);
            terminar.setActionCommand("terminar");
            terminar.setBackground(Color.RED);
            add(terminar, BorderLayout.SOUTH);
        
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            String comando = e.getActionCommand();
            if (numReservaField.getText() == null){
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (cantGuiaField.getText() == null){
                JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad de personas que tomarán el servicio", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            if (comando.equals("terminar")){
                fServicio.terminar();
                dispose();
            }
        }
    }

    private class panelSpa extends JFrame implements ActionListener{
        private FServicio fServicio;

        public panelSpa(FServicio fServicio){
            super("Registrar servicio de spa");
            this.fServicio = fServicio;
            inicializar();
        }

        public void inicializar(){
            setLayout(new BorderLayout());
            setLocationRelativeTo(fServicio);
            setSize(screenSize.width - 50, screenSize.height - 80);
        
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBackground(fondo);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
            JLabel numReservaLabel = new JLabel("Número de reserva:");
            numReservaLabel.setForeground(Color.white);
            numReservaLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            numReservaField = new JTextField(10);
        
            JLabel pagoLabel = new JLabel("Tipo de pago:");
            pagoLabel.setForeground(Color.white);
            pagoLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            pagoBox = new JComboBox<>(new String[]{"Inmediato", "Al final de la estadía"});
        
            JLabel cantGuiaLabel = new JLabel("Cantidad de personas que tomarán el servicio:");
            cantGuiaLabel.setForeground(Color.white);
            cantGuiaLabel.setFont(new Font("Georgia", Font.BOLD, 20));
            cantGuiaField = new JTextField(10);
        
            panel.add(numReservaLabel);
            panel.add(numReservaField);
            panel.add(pagoLabel);
            panel.add(pagoBox);
            panel.add(cantGuiaLabel);
            panel.add(cantGuiaField);
        
            add(panel, BorderLayout.CENTER);
        
            JButton terminar = new JButton("Terminar");
            terminar.setForeground(Color.white);
            terminar.setFont(new Font("Georgia", Font.BOLD, 20));
            terminar.addActionListener(this);
            terminar.setActionCommand("terminar");
            terminar.setBackground(Color.RED);
            add(terminar, BorderLayout.SOUTH);
        
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            String comando = e.getActionCommand();
            if (comando.equals("terminar")){
                fServicio.terminar();
                dispose();
            }
        }
    }

    public void terminar(){
        Staff staff = new Staff();
        numReserva = Integer.parseInt(numReservaField.getText());
        if (pagoBox.getSelectedItem().equals("Inmediato")){
            pago = true;
            String factura = staff.facturaInmediata(fStaff.principal.hotel.reservas, fStaff.principal.hotel.platos, pago, fStaff.principal.hotel.consumos, listaPedido, numReserva, numServicio, cantGuia, lugar);
            JOptionPane.showMessageDialog(null, factura, "Factura", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            pago = false;
        }
        if(this.ubicacionBox != null && ubicacionBox.getSelectedItem().equals("Restaurante")){
            lugar = 1;
        }
        else{
            lugar = 2;
        }
        
        HashMap<Integer, Consumo> consumos_actualizados = staff.registrarServicio(fStaff.principal.hotel.reservas, fStaff.principal.hotel.platos, pago, fStaff.principal.hotel.consumos, listaPedido, numReserva, numServicio, cantGuia, lugar);
        fStaff.principal.hotel.consumos = consumos_actualizados;
        dispose();
    }

    public void actionPerformed( ActionEvent e ){
        String comando =  e.getActionCommand();
        if (comando.equals("terminar")){
            terminar();
        }
        else if (comando.equals("spa")){
            panelSpa pS = new panelSpa(this);
            pS.setSize(1000, 800);
            pS.setVisible(true);
            pS.setLocationRelativeTo(this);
            numServicio = 1;
        }
        else if (comando.equals("guia")){
            panelGuia pG = new panelGuia(this);
            pG.setSize(1000, 800);
            pG.setVisible(true);
            pG.setLocationRelativeTo(this);
            numServicio = 3;
        }
        else if (comando.equals("restaurante")){
            numServicio = 2;
            PanelRestaurante pR = new PanelRestaurante(this);
            pR.setSize(1000, 800);
            pR.setLocationRelativeTo(this);
            pR.setVisible(true);
        }
    } 

    private void panelSpa(JPanel pS, FStaff fStaff){

    }

}
