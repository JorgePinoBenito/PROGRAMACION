package principal;

import java.util.ArrayList;

public class Factory {
	
	private Factory() {}

    public static Jugador create(Posicion posicion)
    {
    	ArrayList<Equipacion>equipaciongeneral=new ArrayList<Equipacion>();
    	equipaciongeneral.add(new Pantalon());
    	equipaciongeneral.add(new Camiseta());
    	equipaciongeneral.add(new Zapatillas());
    	
    	ArrayList<Equipacion>equipacionportero=new ArrayList<Equipacion>();
    	equipacionportero.add(new Pantalon());
    	equipacionportero.add(new Camiseta());
    	equipacionportero.add(new Zapatillas());
    	equipacionportero.add(new Guantes());
    	
        switch (posicion)
        {
            case PORTERO:
                return new Jugador(null, 1, Posicion.PORTERO, equipacionportero);
                
            case DEFENSA:
                return new Jugador(null, 2, Posicion.DEFENSA, equipaciongeneral);
                
            case CENTROCAMPISTA:
                return new Jugador(null, 3, Posicion.CENTROCAMPISTA, equipaciongeneral);
                
            case DELANTERO:
                return new Jugador(null, 4, Posicion.DELANTERO, equipaciongeneral);
            default:
                throw new UnsupportedOperationException();
        }
    }

}
