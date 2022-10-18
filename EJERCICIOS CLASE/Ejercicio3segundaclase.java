
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3segundaclase 
{
    public static void main(String[] args) 
    {
        String palabra="";
        String palabrarev="";
        String palabrasinespacios="";
        ArrayList<String> palabrasinvocales = new ArrayList<String>(); 
        char [] arr;

        Scanner lector1 = new Scanner(System.in);
        System.out.print("Introduce una palabra: ");
        palabra = lector1.nextLine();
        palabrasinvocales.add(palabra);
        arr=palabra.toCharArray();

        for (int i=arr.length-1;i>-1;i--)
        {
            palabrarev=palabrarev+arr[i];
        }

        System.out.println("Tu palabra al reves es: " + palabrarev);
        palabrasinespacios=(palabra.replace(" ",""));
        System.out.println("Tu palabra tiene: " + palabrasinespacios.length() + " letras");

        for(int i=0; i<palabrasinvocales.size(); ++i) 
        {
            palabrasinvocales.set(i, palabrasinvocales.get(i).replaceAll("(?iu)[aeiouáéíóúü]", ""));
        }

        System.out.print("Tu palabra sin vocales es: " );
        
        for(String letras:palabrasinvocales) 
        {
            System.out.print(letras);
        }

        lector1.close();

    }
}
