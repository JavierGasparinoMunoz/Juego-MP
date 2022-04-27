import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre;
    private Set<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private Set<Armadura> conjuntoArmaduras, armadurasActivas = new HashSet<Armadura>();
    private Set<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private ArrayList<Modificador> listaDebilidades,listaFortalezas = new ArrayList<>();
    private int cantidadOro,valorPoder,salud,ataque,defensa;
    private HabilidadEspecial habilidadEspecial;

    public Personaje() {

    }

    public abstract void habilidadEspecial();
    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();


}
