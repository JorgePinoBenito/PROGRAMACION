package prueba;

public class Dog implements Animales{

	private String nombre="";
	
	public Dog(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public void makesound() {
		System.out.println("Guau");
		
	}

}
