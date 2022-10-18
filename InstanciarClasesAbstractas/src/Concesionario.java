import java.util.ArrayList;

public class Concesionario {

	public static void main(String[] args) {

		Vehiculo coche = ConstructordeVehiculos.create("coche");
		coche.setMarca("Megane");
		//coche.sonido();

		Vehiculo moto = ConstructordeVehiculos.create("moto");
		moto.setMarca("Kawasaki");
		//moto.sonido();

		ArrayList <Vehiculo> vehiculos=new ArrayList <Vehiculo>();
		vehiculos.add(coche);
		vehiculos.add(moto);

		for(Vehiculo vehiculo:vehiculos)
		{
			vehiculo.sonido();
			System.out.println(vehiculo.getMarca());
		}

		System.out.println("Tienes en tu concesionario " + vehiculos.size() + " vehiculos");

	}

}
