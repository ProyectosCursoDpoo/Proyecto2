package logica;

public abstract class Empleado {
    public String usuario;
    private String contrasena;

    

    public String getUsuario(){
        return this.usuario;
    }

    /**
     * @return String return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
