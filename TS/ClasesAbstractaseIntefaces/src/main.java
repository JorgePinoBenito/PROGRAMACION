import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		ArrayList<Canino>animales1=new ArrayList<Canino>();
		ArrayList<Animal>animales2=new ArrayList<Animal>();
		
		Perro perro=new Perro(4);
		Lobo lobo=new Lobo(4);
	
		System.out.println("El perro tiene " + perro.getNpatas() + " patas");
		
		System.out.println("El lobo tiene " + lobo.getNpatas() + " patas");
		
		animales1.add(perro);
		animales1.add(lobo);
		animales2.add(perro);
		animales2.add(lobo);
		
		for(Canino animal:animales1)
		{
			animal.cazar();
			animal.dormir();
		}
		for(Animal animal:animales2)
		{
			animal.sonido();
		}
		
	}

}
