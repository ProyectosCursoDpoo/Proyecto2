package logica;

import java.io.*;
import java.util.*;
import java.time.*;
//import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Recepcionista extends Empleado {
    private String usuario;
    private String contrasena;
    private String nombre;
    public Random random = new Random();

    public Recepcionista(String usuario, String contrasena, String nombre) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public HashMap<Integer, reserva> iniciarReserva(HashMap<Integer, Huesped> huespedes,
            HashMap<Integer, reserva> reservas,
            HashMap<Integer, Habitacion> habitaciones, Empleado empleado, HashMap<String, Integer> tarifasEstandar,
            HashMap<String, Integer> tarifasSuite, HashMap<String, Integer> tarifasSuiteDoble,
            HashMap<Integer, Grupo> grupos) {
        // creo arraylist de apoyo
        ArrayList<Huesped> huespedes_reserva = new ArrayList<Huesped>();
        ArrayList<Habitacion> habitaciones_reserva = new ArrayList<Habitacion>();
        int tarifa_reserva = 0;
        int habitaciones_disponibles = 0;
        // pregunto por los huespedes
        System.out.println("Vamos a empezar con agregar la informacion de los huespedes \n Empecemos: ");
        do {
            System.out.println("Ingrese la informacion del huesped: ");
            int id = Integer.parseInt(input("Ingrese el id del huesped"));
            if (huespedes.containsKey(id)) {
                System.out.println("El huesped ya existe");
                huespedes_reserva.add(huespedes.get(id));
            } else {
                System.out.println("El huesped no existe");
                String nombre_huesped = input("Ingrese el nombre del huesped");
                String correo = input("Ingrese el correo del huesped");
                String celular = input("Ingrese el celular del huesped");
                String fecha = input("Ingrese la fecha de nacimiento del huesped");
                Huesped nuevo_huesped = new Huesped(nombre_huesped, id, correo, celular, fecha);
                huespedes.put(id, nuevo_huesped);
                huespedes_reserva.add(nuevo_huesped);
            }
        } while (input("Desea agregar otro huesped? (S/N)").equals("S"));

        // Fecha de reserva
        String fecha_realizada = ZonedDateTime.now(ZoneId.of("America/Bogota"))
                .format(DateTimeFormatter.ofPattern("MM.dd.yyy"));
        String fecha_final = input(
                "Ingresa hasta que dia deseas tu reserva, (Recuerda ingresarla en el formato MM.dd.yyy): ");
        String rango_fecha = fecha_realizada.substring(0, 5).replace(".", "") + "-"
                + fecha_final.substring(0, 5).replace(".", "");
        String inicial = fecha_realizada.substring(0, 5).replace(".", "");
        String f_final = fecha_final.substring(0, 5).replace(".", "");

        // pregunto la habitacion que quiere
        System.out.println("Ahora te presentaremos la informacion de las habitaciones para que escojas: ");
        Object mensaje;
        do {
            for (Object k : habitaciones.keySet()) {
                Habitacion habitacion = habitaciones.get(k);
                if (habitacion instanceof Estandar) {
                    Estandar habiEstandar = (Estandar) habitacion;
                    if (habiEstandar.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiEstandar.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiEstandar.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiEstandar.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                } else if (habitacion instanceof Suite) {
                    Suite habiSuite = (Suite) habitacion;
                    if (habiSuite.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiSuite.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiSuite.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiSuite.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                } else if (habitacion instanceof Suite_doble) {
                    Suite_doble habiSuite2 = (Suite_doble) habitacion;
                    if (habiSuite2.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiSuite2.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiSuite2.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiSuite2.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                }
            }
            if (habitaciones_disponibles > 0) {
                int numero_habitacion = Integer
                        .parseInt(input("Ingresa el numero de la habitacion que sea de tu interes: "));
                habitaciones_reserva.add(habitaciones.get(numero_habitacion));
                // Numero de la reserva
                Habitacion habitacion_elegida = habitaciones.get(numero_habitacion);
                if (habitacion_elegida instanceof Estandar) {
                    Estandar habitacion = (Estandar) habitacion_elegida;
                    System.out.println("Seleccionaste una Estandar \n");
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_reserva += habitacion.getPrecioAhora(tarifasEstandar, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }
                } else if (habitacion_elegida instanceof Suite) {
                    System.out.println("Seleccionaste una suite \n");
                    Suite habitacion = (Suite) habitacion_elegida;
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_reserva += habitacion.getPrecioAhora(tarifasSuite, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }

                } else if (habitacion_elegida instanceof Suite_doble) {
                    Suite_doble habitacion = (Suite_doble) habitacion_elegida;
                    System.out.println("Seleccionaste una Suite Doble \n");
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_reserva += habitacion.getPrecioAhora(tarifasSuiteDoble, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }
                }
                mensaje = input("Deseas Elegir otra habitacion? (S/N)");
            } else {
                System.out.println("Lo sentimos no tenemos habitaciones disponibles en este momento");
                mensaje = "N";
            }
        } while (mensaje.equals("S"));
        if (habitaciones_disponibles > 0) {
            int numero_reserva = habitaciones_reserva.get(0).getNumero();
            // Se crea el grupo de la reserva
            int id = 0;
            do {
                id = random.nextInt(101);
            } while (grupos.containsKey(id));
            Grupo grupo_reserva = new Grupo(huespedes_reserva, habitaciones_reserva, id);

            reserva reserva = new reserva(numero_reserva, grupo_reserva, tarifa_reserva, fecha_realizada, rango_fecha,
                    empleado);
            reservas.put(numero_reserva, reserva);
            System.out.println("Reserva creada con exito!");
            return reservas;
        }
        return reservas;
    }

    public void darCotizacion(HashMap<Integer, Huesped> huespedes, HashMap<Integer, Habitacion> habitaciones,
            HashMap<String, Integer> tarifasEstandar, HashMap<String, Integer> tarifasSuite,
            HashMap<String, Integer> tarifasSuiteDoble) {
        System.out.println(
                "A Continuacion te pedire informacion sobre las habitaciones de tu interes, y los dias de tu estadia para sacar el precio de cotizacion. \n");
        // Fecha de reserva
        String fecha_realizada = input(
                "Ingresa hasta que desde que dias deseas tu reserva, (Recuerda ingresarla en el formato MM.dd.yyy): ");
        String fecha_final = input(
                "Ingresa hasta que dia deseas tu reserva, (Recuerda ingresarla en el formato MM.dd.yyy): ");
        String rango_fecha = fecha_realizada + "-" + fecha_final;
        String inicial = fecha_realizada.substring(0, 5).replace(".", "");
        String f_final = fecha_final.substring(0, 5).replace(".", "");
        int habitaciones_disponibles = 0;
        // Desplegar info habitaciones
        int tarifa_cotizacion = 0;
        System.out.println("Ahora te presentaremos la informacion de las habitaciones para que escojas: ");
        Object mensaje;
        do {
            for (Object k : habitaciones.keySet()) {
                Habitacion habitacion = habitaciones.get(k);
                if (habitacion instanceof Estandar) {
                    Estandar habiEstandar = (Estandar) habitacion;
                    if (habiEstandar.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiEstandar.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiEstandar.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiEstandar.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                } else if (habitacion instanceof Suite) {
                    Suite habiSuite = (Suite) habitacion;
                    if (habiSuite.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiSuite.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiSuite.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiSuite.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                } else if (habitacion instanceof Suite_doble) {
                    Suite_doble habiSuite2 = (Suite_doble) habitacion;
                    if (habiSuite2.getEstado().equals("DISPONIBLE")) {
                        habitaciones_disponibles += 1;
                        System.out.println("Habitacion #" + k + ": \n ");
                        System.out.println("Ubicacion: " + habiSuite2.getUbicacion() + "\n");
                        System.out.println("Capacidad: " + habiSuite2.getCapacidad() + "\n");
                        System.out.println("Camas: \n");
                        ArrayList<Cama> camas = habiSuite2.getCamas();
                        for (Cama cama : camas) {
                            System.out.println("\tCapacidad: " + cama.getCapacidad() + "\n");
                            System.out.println("\tTamaño: " + cama.getTamanio() + "\n");

                        }
                        System.out.println("\n");
                    }
                }
            }
            if (habitaciones_disponibles > 0) {
                int numero_habitacion = Integer
                        .parseInt(input("Ingresa el numero de la habitacion que sea de tu interes: "));
                // Numero de la reserva
                Habitacion habitacion_elegida = habitaciones.get(numero_habitacion);
                if (habitacion_elegida instanceof Estandar) {
                    Estandar habitacion = (Estandar) habitacion_elegida;
                    System.out.println("Seleccionaste una Estandar \n");
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_cotizacion += habitacion.getPrecioAhora(tarifasEstandar, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }
                } else if (habitacion_elegida instanceof Suite) {
                    System.out.println("Seleccionaste una suite \n");
                    Suite habitacion = (Suite) habitacion_elegida;
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_cotizacion += habitacion.getPrecioAhora(tarifasSuite, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }

                } else if (habitacion_elegida instanceof Suite_doble) {
                    Suite_doble habitacion = (Suite_doble) habitacion_elegida;
                    System.out.println("Seleccionaste una Suite Doble \n");
                    int fecha_ini = Integer.parseInt(inicial);
                    int fecha_fin = Integer.parseInt(f_final);

                    while (fecha_ini != fecha_fin) {
                        if (fecha_ini % 100 == 32) {
                            fecha_ini = (fecha_ini - 31) + 100;
                        }
                        tarifa_cotizacion += habitacion.getPrecioAhora(tarifasSuiteDoble, String.valueOf(fecha_ini));
                        fecha_ini++;
                    }
                }
                mensaje = input("Deseas Elegir otra habitacion? (S/N)");
            } else {
                System.out.println("Lo sentimos no tenemos habitaciones disponibles en este momento");
                mensaje = "N";
            }

        } while (mensaje.equals("S"));

        if (habitaciones_disponibles > 0) {
            System.out.format(
                    "\n El precio en el que saldria la reserva seria: %d pesos colombianos, en este rango de fecha %s \n ",
                    tarifa_cotizacion, rango_fecha);
        }
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
                        "../proyecto1/entrega3/proyecto1_hotel/data/facturas/reserva"
                                + String.valueOf(numero_reserva) + ".txt")))) {

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
