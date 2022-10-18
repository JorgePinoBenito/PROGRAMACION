package principal;

public enum Posicion {
	
	PORTERO("Portero",1), DEFENSA("Defensa",2),
	CENTROCAMPISTA("Centrocampista",3), DELANTERO("Delantero",4);

	private String posicion;
	private int dorsal;
	
	private Posicion(String posicion, int dorsal) {
		this.posicion = posicion;
		this.dorsal = dorsal;
	}

	public String getPosicion() {
		return posicion;
	}

	public int getDorsal() {
		return dorsal;
	}

}
