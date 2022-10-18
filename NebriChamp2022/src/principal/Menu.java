package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	void printMenu() {

		boolean rep = true;
		boolean cond = true;
		int choice = 0;

		do {
			System.out.println("\n ¿Qué desea hacer? \n\n"
					+ "1) Mostrar equipos de la liga \n"
					+ "2) Crear nuevo equipo\n"
					+ "3) Salir"
					+ "\n\nOpc:");

			do {
				try {
					choice = sc.nextInt();		
					if (choice<=0 || choice>3) {
						throw new InputMismatchException(); 
					}
					cond = false; 
				}
				catch(InputMismatchException e){
					System.out.println("\nDebe ser un entero entre 1 y 3");
					sc.nextLine();
				}
				if (choice==1)
				{
					OpcionesdeEquipo.mostrarequiposliga();
				}
				else if(choice==2)
				{
					OpcionesdeEquipo.crearnuevoequipo();
				}
				else if(choice==3)
				{
					rep = false;
					System.out.println("Saliendo del programa...");
					System.exit(0);
				}
				else
				{
					System.out.println("Opcion no contemplada");
					System.exit(0);
				}
			}while(cond);
		}while(rep); // while repite si lo de dentro es true. También se puede poner while(rep==true);
	}

}
