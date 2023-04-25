package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import logica.Hotel;

public class App {

	Hotel hotel = new Hotel();

	public void mostrarMenu() {
		System.out.println("1.) Login");
		System.out.println("2.) Salir ");
	}

	public void ejecutarAplicacion() {

		mostrarMenu();
		hotel.cargarInformacion();

		int opcion = Integer.parseInt(input("\nSeleccione una opcion"));
		while (true) {
			if (opcion == 1) {
				ejecutarOpcion1();
			} else if (opcion == 2) {
				System.out.println("Saliendo de la app...");
				System.out.print("\033[H\033[2J");
				System.out.flush();
				break;
			} else {
				System.out.println("Seleccione una opcion valida, por favor");
				System.out.print("\033[H\033[2J");
				System.out.flush();

			}
			System.out.print("\033[H\033[2J");
			mostrarMenu();
			opcion = Integer.parseInt(input("\nSeleccione una opcion"));
			System.out.flush();
		}
	}

	private void ejecutarOpcion1() {
		String usuario = input("Ingresa usuario");
		String contrasena = input("Ingresa contrasena");
		hotel.login(usuario, contrasena);
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

	public static void main(String[] args) {
		{
			App consola = new App();
			consola.ejecutarAplicacion();
		}
	}
}
