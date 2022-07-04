import java.util.ArrayList;
import java.util.HashSet;

public abstract class Personaje {
    private String nombre;
    private ArrayList<Arma> listaArmas = new ArrayList<>();
    private HashSet<Arma> armasActivas = new HashSet<>();
    private ArrayList<Armadura> listaArmaduras = new ArrayList<>();
    private ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
    private int cantidadOro;

    public Personaje(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro) {
        this.nombre = nombre;
        this.listaArmas = listaArmas;
        this.armasActivas = armasActivas;
        this.listaArmaduras = listaArmaduras;
        this.listaEsbirros = listaEsbirros;
        this.cantidadOro = cantidadOro;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Arma> getListaArmas() {
        return listaArmas;
    }

    public void setListaArmas(ArrayList<Arma> listaArmas) {
        this.listaArmas = listaArmas;
    }

    public HashSet<Arma>  getArmasActivas() {
        return armasActivas;
    }

    public void setArmasActivas(HashSet<Arma>  armasActivas) {
        this.armasActivas = armasActivas;
    }

    public ArrayList<Armadura> getListaArmaduras() {
        return listaArmaduras;
    }

    public void setListaArmaduras(ArrayList<Armadura> listaArmaduras) {
        this.listaArmaduras = listaArmaduras;
    }

    public ArrayList<Esbirro> getListaEsbirros() {
        return listaEsbirros;
    }

    public void setListaEsbirros(ArrayList<Esbirro> listaEsbirros) {
        this.listaEsbirros = listaEsbirros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidadOro(int cantidadOro) {
        this.cantidadOro = cantidadOro;
    }

    public int getCantidadOro() {
        return cantidadOro;
    }

    public abstract void añadirEsbirro();

    public void eliminarEsbirro() { //HAY QUE IMPLEMENTARLO AQUI

    }


}
