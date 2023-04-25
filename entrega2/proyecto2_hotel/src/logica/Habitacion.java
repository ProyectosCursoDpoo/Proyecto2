package logica;

import java.util.*;

public abstract class Habitacion {
    private int numero;
    private String ubicacion;
    private int capacidad;
    private boolean vista;
    private boolean balcon;
    private boolean cocina;
    private ArrayList<Cama> camas = new ArrayList<Cama>();
    private String estado;
    private HashMap<String, Integer> precio = new HashMap<String, Integer>();

    /**
     * @return int return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return String return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return int return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return boolean return the vista
     */
    public boolean isVista() {
        return vista;
    }

    /**
     * @param vista the vista to set
     */
    public void setVista(boolean vista) {
        this.vista = vista;
    }

    /**
     * @return boolean return the balcon
     */
    public boolean isBalcon() {
        return balcon;
    }

    /**
     * @param balcon the balcon to set
     */
    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    /**
     * @return boolean return the cocina
     */
    public boolean isCocina() {
        return cocina;
    }

    /**
     * @param cocina the cocina to set
     */
    public void setCocina(boolean cocina) {
        this.cocina = cocina;
    }

    /**
     * @return ArrayList<Cama> return the camas
     */
    public ArrayList<Cama> getCamas() {
        return camas;
    }

    /**
     * @param camas the camas to set
     */
    public void setCamas(ArrayList<Cama> camas) {
        this.camas = camas;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return HashMap<String, Integer> return the precio
     */
    public HashMap<String, Integer> getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(HashMap<String, Integer> precio) {
        this.precio = precio;
    }

}
