

public class Ejercicio4segundaclase 
{
    public static void main(String[] args)
    {

    Coche MiCoche1=new Coche();
    Coche MiCoche2=new Coche("Renault", "2093 GSW");
    Coche MiCoche3=new Coche("Ferrari", "2574 GWW", 32.3);

    MiCoche1.EncenderCoche();
    MiCoche2.EncenderCoche();
    MiCoche3.EncenderCoche();

    System.out.println("La marca de tu coche 1 es: " + MiCoche1.get_Marca());
    System.out.println("La matricula de tu coche 1 es: " + MiCoche1.get_Matricula());
    System.out.println("Los km recorridos de tu coche 1 son: " + MiCoche1.get_Km());

    System.out.println("--------------------------------------------------");

    System.out.println("La marca de tu coche 2 es: " + MiCoche2.get_Marca());
    System.out.println("La matricula de tu coche 2 es: " + MiCoche2.get_Matricula());
    System.out.println("Los km recorridos de tu coche 2 son: " + MiCoche2.get_Km());

    System.out.println("--------------------------------------------------");

    System.out.println("La marca de tu coche 3 es: " + MiCoche3.get_Marca());
    System.out.println("La matricula de tu coche 3 es: " + MiCoche3.get_Matricula());
    System.out.println("Los km recorridos de tu coche 3 son: " + MiCoche3.get_Km());

    }
}

class Coche
{
    //Atributos
    private String Marca="";
    private String Matricula ="";
    private double Km=0.0;

    //Metodos
    public Coche()
    {
        Marca = "";
        Matricula = "";
        Km= 0.0;
    }
    public Coche(String _Marca, String _Matricula)
    {
        Marca = _Marca;
        Matricula = _Matricula;
        Km= 0.0;
    }
    public Coche(String _Marca, String _Matricula, double _Km)
    {
        Marca = _Marca;
        Matricula = _Matricula;
        Km= _Km;
    }
    public void EncenderCoche()
    {
        System.out.println("Brrrmmm");
    }
    public void set_Marca(String Marca) 
    {
        this.Marca = Marca;
    }
    public String get_Marca() 
    {
        return this.Marca;
    }
    public void set_Matricula(String Matricula) 
    {
        this.Matricula = Matricula;
    }
    public String get_Matricula() 
    {
        return this.Matricula;
    }
    public void set_Km(double Km) 
    {
        this.Km = Km;
    }
    public double get_Km() 
    {
        return this.Km;
    }

}
    