package principal;

public class Equipo {

	private String nombre;
	private String colorequipacion1;
	private String colorequipacion2;
	private String estadio;
	private Jugador[]jugadores=new Jugador[4];
	
	public Equipo(String nombre, String colorequipacion1, String colorequipacion2, String estadio, Jugador[] jugadores) {
		this.nombre=nombre;
		this.colorequipacion1 = colorequipacion1;
		this.colorequipacion2 = colorequipacion2;
		this.estadio = estadio;
		this.jugadores = jugadores;
	}
	public String getColorequipacion1() {
		return colorequipacion1;
	}
	public void setColorequipacion1(String colorequipacion1) {
		this.colorequipacion1 = colorequipacion1;
	}
	public String getColorequipacion2() {
		return colorequipacion2;
	}
	public void setColorequipacion2(String colorequipacion2) {
		this.colorequipacion2 = colorequipacion2;
	}
	public String getEstadio() {
		return estadio;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	public Jugador[] getJugadores() {
		return jugadores;
	}
	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    
	

}
