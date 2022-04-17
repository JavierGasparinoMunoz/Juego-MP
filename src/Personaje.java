import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre, habilidadEspecial;
    private Set<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private Set<Armadura> conjuntoArmaduras, armadurasActivas = new HashSet<Armadura>();
    private Set<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private Set<Modificador> conjuntoDebilidades,conjuntoFortalezas = new HashSet<Modificador>();
    private int cantidadOro,valorPoder,salud,ataque,defensa;

    public Personaje() {
    }

    public abstract void habilidadEspecial();
    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();


}
