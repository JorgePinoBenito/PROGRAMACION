public abstract class Vehiculo {

	private int nruedas;
	private String marca;
	private TipodeGiro tipodegiro;
	private String matricula;

	public Vehiculo() {
		this.nruedas = 0;
		this.marca = "";
		this.tipodegiro = null;
		this.matricula = "";
	}

	public Vehiculo(int nruedas, String marca, TipodeGiro tipodegiro, String matricula) {
		super();
		this.nruedas = nruedas;
		this.marca = marca;
		this.tipodegiro = tipodegiro;
		this.matricula = matricula;
	}
	
	public void setNruedas(int nruedas) {
		this.nruedas = nruedas;
	}
	
	public int getNruedas() {
		return nruedas;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setTipodegiro(TipodeGiro tipodegiro) {
		this.tipodegiro = tipodegiro;
	}

	public TipodeGiro getTipodegiro() {
		return tipodegiro;
	}
	
	public String quetipodegiro() {
		switch (getTipodegiro()) {
		case Unidireccional:
			return "Tu coche tiene un tipo de giro unidireccional";
		case Bidireccional:
			return "Tu coche tiene un tipo de giro bidireccional";
		}
		throw new RuntimeException("Ningun tipo de giro encontrado");
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	abstract void sonido();

}
