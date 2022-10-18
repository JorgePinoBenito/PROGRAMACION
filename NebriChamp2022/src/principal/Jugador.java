package principal;

import java.util.ArrayList;

public class Jugador extends Equipacion{
	
	private String nombre;
	private int dorsal;
	private Posicion posicion;
	private ArrayList<Equipacion>equipacion;
	
	public Jugador(String nombre, int dorsal, Posicion posicion, ArrayList<Equipacion> equipacion) {
		super();
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.posicion = posicion;
		this.equipacion = equipacion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public  ArrayList<Equipacion> getEquipacion() {
		return equipacion;
	}

	public void setEquipacion(ArrayList<Equipacion> equipacion) {
		this.equipacion = equipacion;
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", dorsal=" + dorsal + ", posicion=" + posicion + ", equipacion="
				+ equipacion + "]";
	}
	
	

}
