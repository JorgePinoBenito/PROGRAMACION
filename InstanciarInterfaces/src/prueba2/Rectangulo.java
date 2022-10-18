package prueba2;

public class Rectangulo extends Figura{
	
	private double base=0.0;
	private double altura=0.0;

	public Rectangulo(int nlados, String tipo, double base, double altura) {
		super(nlados, tipo);
		this.base = base;
		this.altura = altura;
	}
	
	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	double area() {
		double area = base*altura;
		return area;
	}

}
