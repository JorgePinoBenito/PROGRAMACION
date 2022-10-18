package prueba2;

public class Cuadrado extends Figura {
	
	private double lado=0.0;

	public Cuadrado(int nlados, String tipo, double lado) {
		super(nlados, tipo);
		this.lado = lado;
	}

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	double area() {
		double area =  lado*lado;
		return area;
	}

}
