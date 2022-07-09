import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CrearCazador extends Creator implements Serializable {

    @Override
    public Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, ArrayList<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro, Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca los puntos de Voluntad del Cazador (0 - 3)");
        int puntosVoluntad = sc.nextInt();
        while(puntosVoluntad < 0 || puntosVoluntad > 3) {
            System.out.println("Introduzca los puntos de Voluntad del cazador, solo entre 0 y 3");
            puntosVoluntad = Integer.parseInt(sc.nextLine());
        }
        Cazador c = new Cazador(nombre, listaArmas, armasActivas, listaArmaduras, listaEsbirros, cantidadOro, puntosVoluntad);
        return c;
    }
}
