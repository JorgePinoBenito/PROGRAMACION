package principal;

import java.io.FileWriter;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class OpcionesdeEquipo {

	private Scanner scan=new Scanner(System.in);

	private static ArrayList <Equipo> equipos = new ArrayList<Equipo>();

	static void mostrarequiposliga() {
		System.out.println("Los equipos de la liga son:");
		for(Equipo elem:equipos)
		{
			System.out.println(elem.getNombre());
		}
	}

	@SuppressWarnings("null")
	static void crearnuevoequipo() {

		Jugador portero, defensa, centrocampista, delantero;
		Jugador[] jugadores=new Jugador[4];
		Equipo equipo = null;

		portero = Factory.create(Posicion.PORTERO);

		defensa = Factory.create(Posicion.DEFENSA);

		centrocampista = Factory.create(Posicion.CENTROCAMPISTA);

		delantero = Factory.create(Posicion.DELANTERO);

		String name = JOptionPane.showInputDialog("Nombre del equipo:");
		equipo.setNombre(name);

		String coloreseq1 = JOptionPane.showInputDialog("Colores de la equipacion 1 del equipo:");
		equipo.setColorequipacion1(coloreseq1);

		String coloreseq2 = JOptionPane.showInputDialog("Colores de la equipacion 2 del equipo:");
		equipo.setColorequipacion2(coloreseq2);

		String estadio = JOptionPane.showInputDialog("Nombre del estadio del equipo:");
		equipo.setEstadio(estadio);

		String nombreportero = JOptionPane.showInputDialog("Nombre del portero:");
		portero.setNombre(nombreportero);
		jugadores[0] = portero;

		String nombredefensa = JOptionPane.showInputDialog("Nombre del defensa:");
		defensa.setNombre(nombredefensa);
		jugadores[1] = defensa;

		String nombrecentrocampista = JOptionPane.showInputDialog("Nombre del centrocampista:");
		centrocampista.setNombre(nombrecentrocampista);
		jugadores[2] = centrocampista;

		String nombredeldelantero = JOptionPane.showInputDialog("Nombre del delantero:");
		delantero.setNombre(nombredeldelantero);
		jugadores[3] = delantero;

		equipo.setJugadores(jugadores);
		
		equipos.add(equipo);



	}

	public void guardararchivo(String num) {
		FileWriter writer = null;
		PrintWriter pw = null;

		try
		{
			writer = new FileWriter ("Equipos.txt");
			pw=new PrintWriter(writer);
			pw.println(equipos);
		}
		catch(IOException a)
		{
			a.printStackTrace();		
		}
		finally{
			try{
				if( null != writer ){
					writer.close();
					pw.close();
				}
			}catch (IOException e2){
				e2.printStackTrace();
			}
		}
	}
}



