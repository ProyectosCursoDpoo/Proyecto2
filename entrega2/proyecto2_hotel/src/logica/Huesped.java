package logica;

public class Huesped {
    public String nombre;
    public int identificacion;
    public String correo;
    public String celular;
    public String fechaNacimiento;
    
    public Huesped(String nombre, int identificacion, String correo, String celular, String fechaNacimiento) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCelular() {
        return celular;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    @Override
    public String toString() {
        String cadena = "";
        cadena += getNombre() + ";";
        cadena += getIdentificacion() + ";";
        cadena += getCorreo() + ";";
        cadena += getCelular() + ";";
        cadena += getFechaNacimiento();
        cadena += "\n";
        return cadena;
    }
}
