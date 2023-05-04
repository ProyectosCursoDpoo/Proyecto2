package logica;

import java.io.*;
import java.util.*;

import interfaz.Fhabitaciones;

public class Recepcionista extends Empleado {
    private String usuario;
    private String contrasena;
    private String nombre;
    public Random random = new Random();
    public HashMap<String, Integer> tarifasEstandar = new HashMap<>();
    public HashMap<String, Integer> tarifasSuite = new HashMap<>();
    public HashMap<String, Integer> tarifasSuite2 = new HashMap<>();
    private int tarifaReserva;

    // Recepcionista viejo
    /*
     * public Recepcionista(String usuario, String contrasena, String nombre) {
     * this.usuario = usuario;
     * this.contrasena = contrasena;
     * this.nombre = nombre;
     * }
     */

    // Recepcionista nuevo
    public Recepcionista() {
        super();
    }

    public HashMap<Integer, reserva> iniciarReserva(HashMap<Integer, Huesped> huespedes,
            HashMap<Integer, reserva> reservas,
            HashMap<Integer, Habitacion> habitaciones, Empleado empleado, HashMap<String, Integer> tarifasEstandar,
            HashMap<String, Integer> tarifasSuite, HashMap<String, Integer> tarifasSuiteDoble,
            HashMap<Integer, Grupo> grupos, ArrayList<Habitacion> habitacionesRegistradas,
            ArrayList<Huesped> huespedesRegistrados, String inicial, String f_final) {

        int rangoI = Integer.parseInt(inicial.substring(0, 5).replace(".", ""));
        int rangoF = Integer.parseInt(f_final.substring(0, 5).replace(".", ""));
        String rangoIni = String.valueOf(rangoI);
        String rangoFini = String.valueOf(rangoF);
        String rango_fecha = rangoIni + "-" + rangoFini;
        String f_inicial = inicial.substring(0, 5).replace(".", "");
        String finalf = f_final.substring(0, 5).replace(".", "");
        for (Habitacion habitacion : habitacionesRegistradas) {
            if (habitacion instanceof Estandar) {
                Estandar estandar = (Estandar) habitacion;
                int fecha_ini = Integer.parseInt(f_inicial);
                int fecha_fin = Integer.parseInt(finalf);

                while (fecha_ini != fecha_fin) {
                    if (fecha_ini % 100 == 32) {
                        fecha_ini = (fecha_ini - 31) + 100;
                    }
                    tarifaReserva += estandar.getPrecioAhora(tarifasEstandar, String.valueOf(fecha_ini));
                    fecha_ini++;
                }
            } else if (habitacion instanceof Suite) {
                Suite suite = (Suite) habitacion;
                int fecha_ini = Integer.parseInt(f_inicial);
                int fecha_fin = Integer.parseInt(finalf);

                while (fecha_ini != fecha_fin) {
                    if (fecha_ini % 100 == 32) {
                        fecha_ini = (fecha_ini - 31) + 100;
                    }
                    tarifaReserva += suite.getPrecioAhora(tarifasSuite, String.valueOf(fecha_ini));
                    fecha_ini++;
                }
            } else if (habitacion instanceof Suite_doble) {
                Suite_doble suite_doble = (Suite_doble) habitacion;
                int fecha_ini = Integer.parseInt(f_inicial);
                int fecha_fin = Integer.parseInt(finalf);

                while (fecha_ini != fecha_fin) {
                    if (fecha_ini % 100 == 32) {
                        fecha_ini = (fecha_ini - 31) + 100;
                    }
                    tarifaReserva += suite_doble.getPrecioAhora(tarifasSuiteDoble, String.valueOf(fecha_ini));
                    fecha_ini++;
                }
            }
        }

        int numero_reserva = habitacionesRegistradas.get(0).getNumero();
        int id = 0;
        do {
            id = random.nextInt(101);
        } while (grupos.containsKey(id));
        Grupo grupo_reserva = new Grupo(huespedesRegistrados, habitacionesRegistradas, id);

        int fecha_registro = Integer.parseInt(f_inicial);
        String fecha_inicio = String.valueOf(fecha_registro);
        reserva reserva = new reserva(numero_reserva, grupo_reserva, tarifaReserva,
                fecha_inicio, rango_fecha,
                empleado);
        reservas.put(numero_reserva, reserva);
        return reservas;
    }

    public HashMap<Integer, Habitacion> habitaciones_disponibles(HashMap<Integer, Habitacion> habitaciones) {
        HashMap<Integer, Habitacion> habitaciones_disponibles = new HashMap<Integer, Habitacion>();
        for (Integer k : habitaciones.keySet()) {
            Habitacion habitacion = habitaciones.get(k);
            if (habitacion instanceof Estandar) {
                Estandar habiEstandar = (Estandar) habitacion;
                if (habiEstandar.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles.put(k, habiEstandar);

                }
            } else if (habitacion instanceof Suite) {
                Suite habiSuite = (Suite) habitacion;
                if (habiSuite.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles.put(k, habiSuite);

                }
            } else if (habitacion instanceof Suite_doble) {
                Suite_doble habiSuite2 = (Suite_doble) habitacion;
                if (habiSuite2.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles.put(k, habiSuite2);

                }
            }
        }

        return habitaciones_disponibles;
    }

    public void darCotizacion(String fechaInicio, String fechaFin, HashMap<Integer, Huesped> huespedes,
            HashMap<Integer, Habitacion> habitaciones,
            HashMap<String, Integer> tarifasEstandar, HashMap<String, Integer> tarifasSuite,
            HashMap<String, Integer> tarifasSuiteDoble, Recepcionista recepcionista) {

        this.tarifasEstandar = tarifasEstandar;
        this.tarifasSuite = tarifasSuite;
        this.tarifasSuite2 = tarifasSuiteDoble;
        int habitaciones_disponibles = 0;
        HashMap<Integer, Habitacion> info_habitaciones_disponibles = new HashMap<Integer, Habitacion>();
        for (Integer k : habitaciones.keySet()) {
            Habitacion habitacion = habitaciones.get(k);
            if (habitacion instanceof Estandar) {
                Estandar habiEstandar = (Estandar) habitacion;
                if (habiEstandar.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles += 1;
                    info_habitaciones_disponibles.put(k, habiEstandar);

                }
            } else if (habitacion instanceof Suite) {
                Suite habiSuite = (Suite) habitacion;
                if (habiSuite.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles += 1;
                    info_habitaciones_disponibles.put(k, habiSuite);

                }
            } else if (habitacion instanceof Suite_doble) {
                Suite_doble habiSuite2 = (Suite_doble) habitacion;
                if (habiSuite2.getEstado().equals("DISPONIBLE")) {
                    habitaciones_disponibles += 1;
                    info_habitaciones_disponibles.put(k, habiSuite2);

                }
            }
        }
        if (habitaciones_disponibles > 0) {
            Fhabitaciones ventaHabitaciones = new Fhabitaciones(info_habitaciones_disponibles, fechaInicio, fechaFin,
                    recepcionista);
            ventaHabitaciones.setVisible(true);
        } else {
            System.out.println("Lo sentimos no tenemos habitaciones disponibles en este momento");
            Fhabitaciones ventaHabitaciones = new Fhabitaciones(info_habitaciones_disponibles, fechaInicio, fechaFin,
                    recepcionista);
            ventaHabitaciones.setVisible(true);

        }

    }

    public int getTarifa_cotizacion(int nHabitacion, HashMap<Integer, Habitacion> habitaciones, String inicial,
            String finalf) {
        int tarifa_cotizacion = 0;
        String f_inicial = inicial.substring(0, 5).replace(".", "");
        String f_final = finalf.substring(0, 5).replace(".", "");
        Habitacion habitacion_elegida = habitaciones.get(nHabitacion);
        if (habitacion_elegida instanceof Estandar) {
            Estandar habitacion = (Estandar) habitacion_elegida;
            int fecha_ini = Integer.parseInt(f_inicial);
            int fecha_fin = Integer.parseInt(f_final);

            while (fecha_ini != fecha_fin) {
                if (fecha_ini % 100 == 32) {
                    fecha_ini = (fecha_ini - 31) + 100;
                }
                tarifa_cotizacion += habitacion.getPrecioAhora(tarifasEstandar,
                        String.valueOf(fecha_ini));
                fecha_ini++;
            }
        } else if (habitacion_elegida instanceof Suite) {
            System.out.println("Seleccionaste una suite \n");
            Suite habitacion = (Suite) habitacion_elegida;
            int fecha_ini = Integer.parseInt(f_inicial);
            int fecha_fin = Integer.parseInt(f_final);

            while (fecha_ini != fecha_fin) {
                if (fecha_ini % 100 == 32) {
                    fecha_ini = (fecha_ini - 31) + 100;
                }
                tarifa_cotizacion += habitacion.getPrecioAhora(tarifasSuite,
                        String.valueOf(fecha_ini));
                fecha_ini++;
            }

        } else if (habitacion_elegida instanceof Suite_doble) {
            Suite_doble habitacion = (Suite_doble) habitacion_elegida;
            System.out.println("Seleccionaste una Suite Doble \n");
            int fecha_ini = Integer.parseInt(f_inicial);
            int fecha_fin = Integer.parseInt(f_final);

            while (fecha_ini != fecha_fin) {
                if (fecha_ini % 100 == 32) {
                    fecha_ini = (fecha_ini - 31) + 100;
                }
                tarifa_cotizacion += habitacion.getPrecioAhora(tarifasSuite2,
                        String.valueOf(fecha_ini));
                fecha_ini++;
            }
        }
        return tarifa_cotizacion;
    }

    public HashMap<Integer, reserva> cancelarReserva(Integer numero_reserva, HashMap<Integer, reserva> reservas) {
        reserva reserva = reservas.get(numero_reserva);
        Grupo grupo = reserva.getGrupo();
        ArrayList<Habitacion> habitaciones = grupo.getHabitaciones();
        // ArrayList<Huesped> huespedes = grupo.getHuespedes();

        for (Habitacion habitacion : habitaciones) {
            habitacion.setEstado("DISPONIBLE");
        }

        reservas.remove(numero_reserva);
        System.out.println("Reserva Acabada!");
        return reservas;
    }

    public void guardarFactura(Integer numero_reserva, String factura) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../proyecto2_hotel/data/facturas/reserva" + String.valueOf(numero_reserva) + ".txt")))) {

            bw.write(factura);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public HashMap<Integer, Consumo> borrarConsumos(Integer numero_reserva, HashMap<Integer, reserva> reservas,
            HashMap<Integer, Consumo> consumos) {

        if (reservas.containsKey(numero_reserva)) {
            System.out.println(reservas);
            reserva reserva = reservas.get(numero_reserva);
            ArrayList<Consumo> consumos_reserva = reserva.getConsumos();

            for (Consumo consumo : consumos_reserva) {
                int id = consumo.getId();
                consumos.remove(id);
                System.out.println("Coincide");
            }
        }
        return consumos;
    }

    public HashMap<Integer, reserva> registrarSalida(Integer numero_reserva, HashMap<Integer, reserva> reservas,
            HashMap<Integer, Consumo> consumos) {
        System.out.println(
                "A continuacion te mostrate tu factura para que hagas el respectivo pago y poder registrar tu factura: ");
        // reserva reserva = reservas.get(numero_reserva);
        String factura = generarFactura(numero_reserva, reservas, consumos);
        System.out.println("--------Factura--------");
        System.out.println(factura);
        System.out.println("-----------------------");

        // int saldo = Integer.parseInt(input("Ingresa el saldo correspondiente a tarifa
        // final: "));
        System.out.println("Gracias por visitar nuestro hotel!!!");
        guardarFactura(numero_reserva, factura);
        return cancelarReserva(numero_reserva, reservas);

    }

    public String generarFactura(Integer numero_reserva, HashMap<Integer, reserva> reservas,
            HashMap<Integer, Consumo> consumos) {
        // HashMap<Integer, Consumo> consumos
        reserva reserva = reservas.get(numero_reserva);
        double total = reserva.getTarifaReserva();
        String factura = "";
        Grupo grupo = reserva.getGrupo();
        ArrayList<Habitacion> habitaciones_usadas = grupo.getHabitaciones();
        ArrayList<Huesped> huespedes_registrados = grupo.getHuespedes();
        double saldo_pendiente = reserva.getSaldoPendiente();
        total += saldo_pendiente;

        factura += "Servicios utilizados: \n \t---Las habitaciones que utilizaste en tu reserva son:--- \n";
        for (Habitacion habitacion : habitaciones_usadas) {
            if (habitacion instanceof Estandar) {
                Estandar habiEstandar = (Estandar) habitacion;
                factura += ("Habitacion #" + habiEstandar.getNumero() + ": \n ");
                factura += ("Ubicacion: " + habiEstandar.getUbicacion() + "\n");
                factura += ("Capacidad: " + habiEstandar.getCapacidad() + "\n");
                factura += ("Camas: \n");
                ArrayList<Cama> camas = habiEstandar.getCamas();
                for (Cama cama : camas) {
                    factura += ("\tCapacidad: " + cama.getCapacidad() + "\n");
                    factura += ("\tTamaño: " + cama.getTamanio() + "\n");
                }
                factura += ("\n");
            } else if (habitacion instanceof Suite) {
                Suite habiSuite = (Suite) habitacion;
                factura += ("Habitacion #" + habiSuite.getNumero() + ": \n ");
                factura += ("Ubicacion: " + habiSuite.getUbicacion() + "\n");
                factura += ("Capacidad: " + habiSuite.getCapacidad() + "\n");
                factura += ("Camas: \n");
                ArrayList<Cama> camas = habiSuite.getCamas();
                for (Cama cama : camas) {
                    factura += ("\tCapacidad: " + cama.getCapacidad() + "\n");
                    factura += ("\tTamaño: " + cama.getTamanio() + "\n");
                }
                factura += ("\n");
            } else if (habitacion instanceof Suite_doble) {
                Suite_doble habiSuite2 = (Suite_doble) habitacion;
                factura += ("Habitacion #" + habiSuite2.getNumero() + ": \n ");
                factura += ("Ubicacion: " + habiSuite2.getUbicacion() + "\n");
                factura += ("Capacidad: " + habiSuite2.getCapacidad() + "\n");
                factura += ("Camas: \n");
                ArrayList<Cama> camas = habiSuite2.getCamas();
                for (Cama cama : camas) {
                    factura += ("\tCapacidad: " + cama.getCapacidad() + "\n");
                    factura += ("\tTamaño: " + cama.getTamanio() + "\n");
                }
                factura += ("\n");
            }
        }
        factura += "\t ---Los huespedes hospedados fueron:--- \n";
        for (Huesped huesped : huespedes_registrados) {
            factura += String.format("Nombre del huesped: %s \n", huesped.getNombre());
            factura += String.format("Correo del huesped: %s \n", huesped.getCorreo());
            factura += String.format("Celular del huesped: %s \n", huesped.getCelular());
            factura += String.format("Identificacion del huesped: %s \n", huesped.getIdentificacion());
            factura += "\n";

        }
        factura += "\t ---Los consumos adicionales son:--- \n";
        for (Object k : consumos.keySet()) {
            Consumo consumo = consumos.get(k);
            if (consumo.getReserva().getNumeroReserva() == reserva.getNumeroReserva()) {
                factura += String.format("Nombre del servicio adicional que contrato: %s \n", consumo.getNombre());
                factura += String.format("Precio del servicio adicional que contrato: %d \n", consumo.getPrecioIndv());
                factura += "\n";
                if (!consumo.estado) {
                    total += consumo.getPrecioIndv();
                }
            }
        }

        factura += String.format("El precio total de la factura es: %.1f pesos colombianos \n", total);
        factura += "Gracias por reservar con nostros! \n";
        System.out.println(factura);
        return factura;

    }

    /**
     * @return String return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

}
