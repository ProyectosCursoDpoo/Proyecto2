package interfaz;

import logica.Hotel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FLogin extends JPanel {

        private FPrincipal principal;

        public FLogin(FPrincipal fPrincipal) {
                this.principal = fPrincipal;
                inicializar();
        }

        public void inicializar() {
                setLayout(new BorderLayout());
                Color fondo = new Color(28, 35, 46);

                JLabel titulo = new JLabel("Hotel los pps");
                titulo.setFont(new Font("Georgia", Font.BOLD, 60)); // aumenta el tamaño de la fuente
                titulo.setHorizontalAlignment(JLabel.CENTER); // centra el texto
                titulo.setForeground(Color.WHITE); // establece el color del texto

                // Panel central con bloque y elementos de login
                JPanel centerPanel = new JPanel();
                centerPanel.setBackground(fondo);
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weighty = 0.1;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.HORIZONTAL; // establecer relleno horizontal
                gbc.insets = new Insets(10, 10, 10, 10);

                // Panel de usuario
                JPanel usuarioPanel = new JPanel();
                usuarioPanel.setLayout(new BoxLayout(usuarioPanel, BoxLayout.X_AXIS));
                ImageIcon icon1 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/usuario.png");
                usuarioPanel.add(new JLabel(
                                new ImageIcon(icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
                usuarioPanel.setBackground(fondo);
                usuarioPanel.add(Box.createRigidArea(new Dimension(50, 0))); // agregar espacio entre los elementos
                JTextField usuarioTextField = new JTextField(5);
                usuarioPanel.add(usuarioTextField);
                centerPanel.add(usuarioPanel, gbc);

                // Panel de contraseña
                gbc.gridy++;
                gbc.weighty = 0.1;
                JPanel contrasenaPanel = new JPanel();
                contrasenaPanel.setLayout(new BoxLayout(contrasenaPanel, BoxLayout.X_AXIS));
                ImageIcon icon2 = new ImageIcon("../Proyecto2/entrega2/proyecto2_hotel/data/contrasena.png");
                contrasenaPanel.add(new JLabel(
                                new ImageIcon(icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
                contrasenaPanel.setBackground(fondo);
                contrasenaPanel.add(Box.createRigidArea(new Dimension(50, 0))); // agregar espacio entre los elementos
                JPasswordField contrasenaTextField = new JPasswordField(10);
                contrasenaPanel.add(contrasenaTextField);
                centerPanel.add(contrasenaPanel, gbc);

                // Botón de login
                gbc.gridy++;
                gbc.weightx = 1.0; // establecer el peso para el botón de inicio de sesión
                gbc.weighty = 0.5;
                JButton loginButton = new JButton("Login");
                loginButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String usuario = usuarioTextField.getText();
                                String contrasena = new String(contrasenaTextField.getPassword());
                                principal.login(usuario, contrasena);
                        }
                });
                centerPanel.add(loginButton, gbc);

                add(centerPanel, BorderLayout.CENTER);

                // Panel inferior con espacio vacío para alinear el botón de inicio de sesión en
                // el centro
                JPanel bottomPanel = new JPanel();
                bottomPanel.setBackground(fondo);
                bottomPanel.setPreferredSize(new Dimension(0, 250));
                add(bottomPanel, BorderLayout.SOUTH);

                // Panel izquierdo con espacio vacío
                JPanel leftPanel = new JPanel();
                leftPanel.setBackground(new Color(28, 35, 46));
                leftPanel.setPreferredSize(new Dimension(400, 0));
                add(leftPanel, BorderLayout.WEST);

                // Panel derecho con espacio vacío
                JPanel rightPanel = new JPanel();
                rightPanel.setBackground(new Color(28, 35, 46));
                rightPanel.setPreferredSize(new Dimension(400, 0));
                add(rightPanel, BorderLayout.EAST);

                // Panel superior con espacio vacío
                JPanel topPanel = new JPanel();
                topPanel.setLayout(new BorderLayout());
                topPanel.setBackground(new Color(28, 35, 46));
                topPanel.add(titulo, BorderLayout.SOUTH);
                topPanel.setPreferredSize(new Dimension(0, 250));
                add(topPanel, BorderLayout.NORTH);

        }

}
