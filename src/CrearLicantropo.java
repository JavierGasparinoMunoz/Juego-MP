import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CrearLicantropo extends Creator implements Serializable {
    //Scanner sc = new Scanner(System.in);
    @Override
    public Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, ArrayList<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro,Scanner sc) {
        System.out.println("Introduzca un valor de rabia entre (0 - 3)");
        int rabia = sc.nextInt();
        while (rabia < 0 || rabia > 3) {
            System.out.println("Introduzca los puntos de rabia del Licántropo, solo entre 0 y 3");
            rabia = sc.nextInt();
        }
        Licantropo l = new Licantropo(nombre,listaArmas,armasActivas,listaArmaduras,listaEsbirros,cantidadOro,rabia);
        return l;
    }
}
