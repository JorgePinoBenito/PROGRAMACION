
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1segundaclase 
{

public static void main(String[] args) 
{
    
ArrayList<Character> miarray = new ArrayList<Character>();

while(true)
{
Scanner lector1 = new Scanner(System.in);
System.out.println("Introduce un numero entero, introduce x para salir");
char num2 = lector1.next().charAt(0);
  
   if (num2 == 'x') 
   {
    System.out.println ("Saliendo...");
    break;
   } 
   else if (num2 == 'X')
   {
    System.out.println ("Saliendo...");
    break; 
   }
   else 
   {
   miarray.add(num2);
   }
 
}

mostrar(miarray);
   
}

public static void mostrar(ArrayList<Character> array)
{
    //Bucle for.
    for (int i=0;i<array.size();i++)
      {
          System.out.print(array.get(i)+",");
      } 
}
}
