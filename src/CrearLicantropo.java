import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CrearLicantropo extends Creator implements Serializable {
    Scanner sc = new Scanner(System.in);
    @Override
    public Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro) {
        int rabia;
        do {
            System.out.println("Introduzca los puntos de rabia del Licántropo, entre 0 y 3");
            rabia = sc.nextInt();
        } while (rabia < 0 || rabia > 3);
        Licantropo l = new Licantropo(nombre,listaArmas,armasActivas,listaArmaduras,listaEsbirros,cantidadOro,rabia);
        return l;
    }
}
