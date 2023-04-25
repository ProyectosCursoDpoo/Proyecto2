package interfaz;

import javax.swing.*;
import java.awt.*;
//import java.io.*;

public class Frecep extends JFrame {

    public Frecep() {

        this.setTitle("Motel los pps");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1220, 800);

        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Frecep();
    }
}
