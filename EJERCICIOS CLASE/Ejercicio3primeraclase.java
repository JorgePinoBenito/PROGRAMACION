
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio3primeraclase 
{

public static void main(String[] args) 
{
    int a = 0;
    int b = 1;
    int c = 0;
    int n = 0;
    
    ArrayList<Integer> miarray = new ArrayList<Integer>();

    Scanner lector = new Scanner(System.in);
    System.out.print("Introduce un numero entero para mostrar los numeros de la Serie de Fibonacci que esten por debajo de el: ");
    n = lector.nextInt();

    for (int i=0;i<n;i++)
    {
        if(a>n)
        {
            break;
        }
        else
        {
            miarray.add(a);
        }
        c=a+b;
        a=b;
        b=c;
    }

/*
Imprime ArrayList de menor a mayor.
   for (int i=0;i<miarray.size();i++)
   {
    System.out.print(miarray.get(i) + "-");
   }
*/

//Imprime ArrayList de mayor a menor.
Collections.reverse(miarray);

System.out.print("Los numeros que estan por debajo del tuyo de la Serie de Fibonacci son: ");

for (int i=0;i<miarray.size();i++)
   {
    System.out.print(miarray.get(i) + "-");
   } 

} 
}