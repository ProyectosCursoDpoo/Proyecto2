package interfaz;

import logica.Hotel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 
public class FPrincipal extends JFrame {

    private Hotel hotel = new Hotel();
    private JPanel pRecept;
    private JPanel pAdmin;
    private FStaff pStaff;

    // CardLayout para cambiar entre los paneles
    private CardLayout cardLayout;
    private JPanel contentP;

    public FPrincipal(){
        super("Sistema de hotel");
        hotel.cargarInformacion();
    try
        {
            Hotel hotel = new Hotel( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el estado inicial " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //pRecept = new FRecepcionista(this);
    //pAdmin = new FAdmin(this);
    pRecept = new JPanel();
    pAdmin = new JPanel();
    pStaff = new FStaff(this);

    cardLayout = new CardLayout();
    contentP = new JPanel(cardLayout);
    contentP.add(new FLogin(this), "login");
    contentP.add(pRecept, "Recepcionista");
    contentP.add(pAdmin, "Administrador");
    contentP.add(pStaff, "Staff");

    setContentPane(contentP);
    setSize(500, 500);
    setVisible(true);


    }

    public void login(String usuario, String contrasena) {


       if (hotel.contrasena(usuario, contrasena)){
        if(usuario.contains("Recept"))  {
            cardLayout.show(contentP, "Recepcionista");
        } else if (usuario.contains("Admin")) {
            cardLayout.show(contentP, "Administrador");
        } else if (usuario.contains("Staff")) {
            cardLayout.show(contentP, "Staff");
        } 
        }
        else {
            // Mostrar mensaje de error si el usuario no existe o no tiene un rol válido
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
		new FPrincipal();
        
	}
}

