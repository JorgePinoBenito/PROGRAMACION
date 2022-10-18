
import java.util.Scanner;

public class Ejercicio1primeraclase 
{

public static void main(String[] args) 
{

Calculadora n1 = new Calculadora();
Calculadora n2 = new Calculadora();
Calculadora sumar = new Calculadora();


Scanner lector1 = new Scanner(System.in);
System.out.print("Introduce un numero: ");
int num1 = lector1.nextInt();
n1.set_x(num1);


Scanner lector2 = new Scanner(System.in);
System.out.print("Introduce un numero: ");
int num2 = lector2.nextInt();
n2.set_y(num2);

int resultado=sumar.suma(n1,n2);
System.out.println("La suma de los dos numeros introducidos es: " + resultado);  

lector1.close();
lector2.close();

}

}
class Calculadora
{
   
   private int x;
   private int y;

   public Calculadora() 
   {
      super();
      this.x = 0;
      this.y = 0;
   }
   public Calculadora(int x, int y) 
   {
      super();
      this.x = x;
      this.y = y;
   }
   public void set_x(int x) 
   {
      this.x = x;
   }
   public int get_x() 
   {
   return this.x;
   }
   public void set_y(int y)
   {
   this.y = y;
   }
   public int get_y()
   {
   return this.y;
   }
   public int suma(Calculadora n1, Calculadora n2)
   {
   int suma;
   suma=(n1.get_x()+n2.get_y());
   return suma;
   }

}

