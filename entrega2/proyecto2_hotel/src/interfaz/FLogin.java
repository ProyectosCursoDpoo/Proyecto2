package interfaz;

import logica.Hotel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FLogin extends JPanel {
    
    private FPrincipal principal;
    private JPanel loginPanel;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaTextField;
    private JButton loginButton;

    public FLogin(FPrincipal fPrincipal){
        this.principal = fPrincipal;
        inicializar();
    }

    public void inicializar(){
        setLayout(new GridLayout(3, 2));
        JLabel usuarioLabel = new JLabel("Usuario: ");
        usuarioTextField = new JTextField();
        JLabel contrasenaLabel = new JLabel("Contrasena: ");
        contrasenaTextField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String usuario = usuarioTextField.getText();
                String contrasena = new String(contrasenaTextField.getPassword());
                principal.login(usuario, contrasena);
            }
        });
        
        add(usuarioLabel);
        add(usuarioTextField);
        add(contrasenaLabel);
        add(contrasenaTextField);
        add(new JLabel());
        add(loginButton);

    }



}
