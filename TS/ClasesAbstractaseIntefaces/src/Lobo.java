
public class Lobo extends Canino implements Animal {

	public Lobo(int patas) 
	{ 
		super(patas); 
	}


	@Override
	public void sonido() {
		System.out.println("auuu");
	}

	@Override
	void cazar() {
		System.out.println("Los lobos cazan");
	}

}
