package prueba2;

public class Circulo extends Figura{
	
	private double radio=0.0;

	public Circulo(int nlados, String tipo, double radio) {
		super(nlados, tipo);
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	@Override
	double area() {
		double area =  Math.PI * Math.pow(radio,2);
		return area;
	}

}
