import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CrearVampiro extends Creator{

    @Override
    public Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro) {
        Scanner sc = new Scanner(System.in);
        int puntosSangre;
        int edad;
        boolean error = false;
        System.out.println("Introduce los puntos de sangre");
        puntosSangre = sc.nextInt();
        if (puntosSangre<0 || puntosSangre>10){
            do {
                System.out.println("Los puntos deben ser menor que 10");
                puntosSangre = sc.nextInt();
            } while (puntosSangre<0 || puntosSangre>10);
        }
        System.out.println("Introduce la edad");
        edad = 0;
        do {
            try {
                edad = sc.nextInt();
            } catch(NumberFormatException e){
                System.out.println("El valor debe ser numerico");
                error = true;
            }
            while (edad < 0) {
                System.out.println("La edad debe ser un numero mayor que 0");
                edad = sc.nextInt();
            }
        } while (error);
        Vampiro v = new Vampiro(nombre,listaArmas,armasActivas,listaArmaduras,listaEsbirros,cantidadOro,puntosSangre,edad);
        return v;
    }
}
