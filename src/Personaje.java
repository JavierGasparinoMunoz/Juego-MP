import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre;
    private Set<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private Set<Armadura> conjuntoArmaduras, armadurasActivas = new HashSet<Armadura>();
    private Set<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private ArrayList<Modificador> listaDebilidades,listaFortalezas,listaDebilidadesActivas,listaFortalezasActivas = new ArrayList<>();
    private int cantidadOro,valorPoder,salud,ataque,defensa;
    private HabilidadEspecial habilidadEspecial;

    public Personaje() {

    }

    public abstract void habilidadEspecial();
    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();

    public ArrayList<Modificador> getListaDebilidades() {
        return listaDebilidades;
    }

    public void setListaDebilidades(ArrayList<Modificador> listaDebilidades) {
        this.listaDebilidades = listaDebilidades;
    }

    public ArrayList<Modificador> getListaFortalezas() {
        return listaFortalezas;
    }

    public void setListaFortalezas(ArrayList<Modificador> listaFortalezas) {
        this.listaFortalezas = listaFortalezas;
    }

    public ArrayList<Modificador> getListaDebilidadesActivas() {
        return listaDebilidadesActivas;
    }

    public void setListaDebilidadesActivas(ArrayList<Modificador> listaDebilidadesActivas) {
        this.listaDebilidadesActivas = listaDebilidadesActivas;
    }

    public ArrayList<Modificador> getListaFortalezasActivas() {
        return listaFortalezasActivas;
    }

    public void setListaFortalezasActivas(ArrayList<Modificador> listaFortalezasActivas) {
        this.listaFortalezasActivas = listaFortalezasActivas;
    }

}
