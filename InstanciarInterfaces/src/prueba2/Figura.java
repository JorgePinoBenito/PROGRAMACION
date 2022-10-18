package prueba2;

public abstract class Figura {

	private int nlados;
	private String tipo;

	public Figura()
	{

	}

	public Figura(int nlados, String tipo) {
		this.nlados = nlados;
		this.tipo = tipo;
	}

	public int getNlados() {
		return nlados;
	}

	public void setNlados(int nlados) {
		this.nlados = nlados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	abstract double area();
}
