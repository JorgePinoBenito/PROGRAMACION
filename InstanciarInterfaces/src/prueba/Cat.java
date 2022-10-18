package prueba;

public class Cat implements Animales{

	private String nombre="";
	
	@Override
	public void makesound() {
		System.out.println("Miau");
	}
	public Cat(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
