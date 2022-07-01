import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre;
    private HashSet<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private HashSet<Armadura> conjuntoArmaduras = new HashSet<Armadura>();
    private HashSet<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private int cantidadOro;

    public String getNombre() {
        return nombre;
    }

    public HashSet<Arma> getConjuntoArmas() {
        return conjuntoArmas;
    }

    public HashSet<Arma> getArmasActivas() {
        return armasActivas;
    }

    public HashSet<Armadura> getConjuntoArmaduras() {
        return conjuntoArmaduras;
    }


    public HashSet<Esbirro> getConjuntoEsbirros() {
        return conjuntoEsbirros;
    }

    public int getCantidadOro() {
        return cantidadOro;
    }

    public Personaje() {

    }

    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();


}
