package interfaz;

import javax.swing.*;
import java.awt.*;
//import java.io.*;

public class Frecep extends JPanel {

    public JLabel titulo;
    public JPanel panelOpciones;

    public Frecep() {
        Color fondo = new Color(28, 35, 46);
        this.setSize(getWidth(), getHeight());
        this.setLayout(new BorderLayout());
        this.setBackground(fondo);

        this.titulo = new JLabel("RECEPCIONISTA");
        this.titulo.setFont(new Font("Font Awesome 5 Brands", ALLBITS, 15));
        this.titulo.setBackground(fondo);

        this.panelOpciones = new JPanel(new FlowLayout());
        this.panelOpciones.setBackground(fondo);

        this.add(titulo, BorderLayout.NORTH);
        this.add(panelOpciones, BorderLayout.CENTER);
    }

}
