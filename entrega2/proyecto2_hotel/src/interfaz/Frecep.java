package interfaz;

import javax.swing.*;
import java.awt.*;
//import java.io.*;

public class Frecep extends JFrame {

    public JLabel titulo;
    public JPanel panel;

    public Frecep() {

        this.setTitle("Motel los pps");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1220, 800);
        this.panel = new JPanel(getLayout());
        this.panel.setBackground(new Color(28, 35, 46));

        this.setLayout(new BorderLayout());

        this.add(this.panel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Frecep();
    }
}
