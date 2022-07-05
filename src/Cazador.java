import java.util.ArrayList;
import java.util.HashSet;

public class Cazador extends Personaje{
    private int puntosVoluntad;

    public Cazador(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro, int puntosVoluntad) {
        super(nombre, listaArmas, armasActivas, listaArmaduras, listaEsbirros, cantidadOro);
        this.puntosVoluntad = puntosVoluntad;
    }

    @Override
    public void añadirEsbirro(Esbirro esbirro) {super.addListaEsbirros(esbirro); }

    @Override
    public void eliminarEsbirro() {
    }

    public int getPuntosVoluntad() {
        return puntosVoluntad;
    }

    public void setPuntosVoluntad(int puntosVoluntad) {
        this.puntosVoluntad = puntosVoluntad;
    }
}
