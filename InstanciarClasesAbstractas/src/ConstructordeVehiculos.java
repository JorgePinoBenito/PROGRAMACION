
public class ConstructordeVehiculos {

	private ConstructordeVehiculos() {}

	public static Vehiculo create(String type)
	{
		switch (type)
		{
		case "coche":
			return new Coche();
		case "moto":
			return new Moto();
		default:
			throw new UnsupportedOperationException();
		}
	}
}


