
public class Perro extends Canino implements Animal {

	public Perro(int patas) 
	{ 
		super(patas); 
	}

	@Override
	public void sonido() {
		System.out.println("guau");
	}

	@Override
	void cazar() {
		System.out.println("Los perros no cazan");
	}
	
}
