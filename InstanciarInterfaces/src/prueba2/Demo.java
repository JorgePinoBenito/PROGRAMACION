package prueba2;

import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		ArrayList<Figura>figuras = new ArrayList<Figura>();
		
		
		
		Circulo circulo = new Circulo(0, "Circulo", 3.2);
		Cuadrado cuadrado = new Cuadrado(4, "Cuadrado", 3.1);
		Triangulo triangulo = new Triangulo(3, "Triangulo", 36.6, 10.3);
		Rectangulo rectangulo = new Rectangulo(4, "Rectangulo", 1.1, 5.5);
		
		figuras.add(circulo);
		figuras.add(cuadrado);
		figuras.add(triangulo);
		figuras.add(rectangulo);
		
		for(Figura figura:figuras)
		{
			System.out.println("El area del " + figura.getTipo() + " es: " + figura.area());
			System.out.println("Los lados de la figura " + figura.getTipo() + " son: " + figura.getNlados());
		}
		
	}

}
