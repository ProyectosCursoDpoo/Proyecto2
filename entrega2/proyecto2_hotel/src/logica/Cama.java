package logica;

public class Cama {
    private String tamanio;
    private int capacidad;

    public Cama(String tamanio, int capacidad) {
        this.tamanio = tamanio;
        this.capacidad = capacidad;
    }

    /**
     * @return int return the tamanio
     */
    public String getTamanio() {
        return tamanio;
    }

    /**
     * @param tamanio the tamanio to set
     */
    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
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

}
