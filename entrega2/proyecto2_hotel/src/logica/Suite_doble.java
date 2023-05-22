package logica;

import java.util.*;

public class Suite_doble extends Habitacion {
    private int numero;
    private String ubicacion;
    private int capacidad;
    private boolean vista;
    private boolean balcon;
    private boolean cocina;
    private ArrayList<Cama> camas = new ArrayList<Cama>();
    private String estado;
    private HashMap<String, Integer> precio = new HashMap<String, Integer>();

    private int m2;
    private boolean aireAcondicionado;
    private boolean calefaccion;
    private boolean tv;
    private boolean cafetera;
    private boolean ropaCama;
    private boolean plancha;
    private boolean secador;
    private int voltaje;
    private boolean usba;
    private boolean usbc;
    private boolean desayuno;

    public Suite_doble(int numero, String ubicacion, int capacidad, boolean vista, boolean balcon, boolean cocina,
            ArrayList<Cama> camas, HashMap<String, Integer> precio, String estado, int m2, boolean aireAcondicionado, 
            boolean calefaccion, boolean tv, boolean cafetera, boolean ropaCama, boolean plancha, boolean secador, 
            int voltaje, boolean usba, boolean usbc, boolean desayuno) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.vista = vista;
        this.balcon = balcon;
        this.cocina = cocina;
        this.camas = camas;
        this.estado = estado;
        this.precio = precio;

        this.m2 = m2;
        this.aireAcondicionado = aireAcondicionado;
        this.calefaccion = calefaccion;
        this.tv = tv;
        this.cafetera = cafetera;
        this.ropaCama = ropaCama;
        this.plancha = plancha;
        this.secador = secador;
        this.voltaje = voltaje;
        this.usba = usba;
        this.usbc = usbc;
        this.desayuno = desayuno;
    }
    

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

    public int getM2() {
        return m2;
    }


    public void setM2(int m2) {
        this.m2 = m2;
    }


    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }


    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }


    public boolean isCalefaccion() {
        return calefaccion;
    }


    public void setCalefaccion(boolean calefaccion) {
        this.calefaccion = calefaccion;
    }


    public boolean isTv() {
        return tv;
    }


    public void setTv(boolean tv) {
        this.tv = tv;
    }


    public boolean isCafetera() {
        return cafetera;
    }


    public void setCafetera(boolean cafetera) {
        this.cafetera = cafetera;
    }


    public boolean isRopaCama() {
        return ropaCama;
    }


    public void setRopaCama(boolean ropaCama) {
        this.ropaCama = ropaCama;
    }


    public boolean isPlancha() {
        return plancha;
    }


    public void setPlancha(boolean plancha) {
        this.plancha = plancha;
    }


    public boolean isSecador() {
        return secador;
    }


    public void setSecador(boolean secador) {
        this.secador = secador;
    }


    public int getVoltaje() {
        return voltaje;
    }


    public void setVoltaje(int voltaje) {
        this.voltaje = voltaje;
    }


    public boolean isUsba() {
        return usba;
    }


    public void setUsba(boolean usba) {
        this.usba = usba;
    }


    public boolean isUsbc() {
        return usbc;
    }


    public void setUsbc(boolean usbc) {
        this.usbc = usbc;
    }


    public boolean isDesayuno() {
        return desayuno;
    }


    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
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

    public int getPrecioAhora(HashMap<String,Integer>precios, String fecha) {
        // LocalDate currentDate = LocalDate.now();
        // String mes=String.valueOf(currentDate).substring(5,7);
        // String dia=String.valueOf(currentDate).substring(8);
        // String fecha_now=String.valueOf(Integer.parseInt(mes+dia));

        int precio_por_fecha=0;
        if (precios.containsKey(fecha)){
            precio_por_fecha = precios.get(fecha);
        }
        return precio_por_fecha;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(HashMap<String, Integer> precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena += getNumero() + ";";
        cadena += getUbicacion() + ";";
        cadena += getCapacidad() + ";";
        cadena += "3;";
        int contadorcamas=1;
        for(Cama k: getCamas()){
            cadena+=k.getTamanio()+ "-";
            cadena+=k.getCapacidad();
            if (contadorcamas!=getCamas().size()){
                cadena+="/";
            }
            contadorcamas++;
        }
        cadena += ";"; 
       cadena += isVista() + ";";
       cadena += isBalcon() + ";";
       cadena += isCocina() + ";";
        cadena += getEstado() + ";";
        cadena += getM2() + ";";
        cadena += isAireAcondicionado() + ";";
        cadena+= isCalefaccion()+ ";";
        cadena+= isTv()+ ";";
        cadena+= isCafetera()+ ";";
        cadena+= isRopaCama()+ ";";
        cadena+= isPlancha()+ ";";
        cadena+= isSecador()+ ";";
        cadena+= getVoltaje()+ ";";
        cadena+= isUsba()+ ";";
        cadena+= isUsbc()+ ";";
        cadena+= isDesayuno()+ "\n";

        
    
       return cadena;
    }

}
