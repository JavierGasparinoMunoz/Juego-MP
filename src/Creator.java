import java.util.ArrayList;
import java.util.HashSet;

public abstract class Creator {
    public abstract Personaje crearPersonaje(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro);
}
