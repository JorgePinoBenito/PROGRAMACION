package prueba;

public class Main {

	public static void main(String[] args) {
		Animales [] animales = new Animales[2];

		animales[0] = new Dog("Jorge");
		animales[1] = new Cat("Katy");

		for (Animales animal:animales) {
			animal.makesound();
		}


	}

}
