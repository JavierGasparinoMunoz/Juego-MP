import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre;
    private HashSet<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private HashSet<Armadura> conjuntoArmaduras, armadurasActivas = new HashSet<Armadura>();
    private HashSet<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private ArrayList<Modificador> listaDebilidades,listaFortalezas,listaDebilidadesActivas,listaFortalezasActivas = new ArrayList<>();
    private int cantidadOro,valorPoder,salud,ataque,defensa;
    private HabilidadEspecial habilidadEspecial;

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

    public HashSet<Armadura> getArmadurasActivas() {
        return armadurasActivas;
    }

    public HashSet<Esbirro> getConjuntoEsbirros() {
        return conjuntoEsbirros;
    }

    public int getCantidadOro() {
        return cantidadOro;
    }

    public int getValorPoder() {
        return valorPoder;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public HabilidadEspecial getHabilidadEspecial() {
        return habilidadEspecial;
    }

    public Personaje() {

    }

    public abstract void habilidadEspecial();
    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();
    public abstract int calcularAtaque();
    public abstract int calcularDefensa();

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
