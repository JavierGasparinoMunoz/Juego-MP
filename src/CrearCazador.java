import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CrearCazador extends Creator {
    Scanner sc = new Scanner(System.in);

    @Override
    public Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro) {
        System.out.println("Introduzca los puntos de Voluntad del Cazador (0 - 3)");
        int puntosVoluntad;
        do {
            System.out.println("Introduzca los puntos de Voluntad del cazador, solo entre 0 y 3");
            puntosVoluntad = sc.nextInt();
        } while (puntosVoluntad < 0 || puntosVoluntad > 3);
        sc.close();
        Cazador c = new Cazador(nombre, listaArmas, armasActivas, listaArmaduras, listaEsbirros, cantidadOro, puntosVoluntad);
        return c;
    }
}
