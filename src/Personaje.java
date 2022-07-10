import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Personaje implements Serializable {
    private String nombre;
    private ArrayList<Arma> listaArmas = new ArrayList<>();
    private ArrayList<Arma> armasActivas = new ArrayList<>();
    private ArrayList<Armadura> listaArmaduras = new ArrayList<>();
    private ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
    private int cantidadOro;

    public Personaje(String nombre, ArrayList<Arma> listaArmas, ArrayList<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro) {
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

    public ArrayList<Arma>  getArmasActivas() {
        return armasActivas;
    }

    public void addArmasActivas(Arma arma) {
        armasActivas.add(arma);
    }

    public void removeArmasActivas(Arma arma) {
        armasActivas.remove(arma);
    }

    public void addListaArmas(Arma arma) {
        listaArmas.add(arma);
    }

    public void removeListaArmas(Arma arma) {
        listaArmas.remove(arma);
    }

    public ArrayList<Armadura> getListaArmaduras() {
        return listaArmaduras;
    }

    public void addListaArmaduras(Armadura armadura) {listaArmaduras.add(armadura); }

    public void removeListaArmaduras(Armadura armadura) {
        listaArmaduras.remove(armadura);
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

    public abstract void añadirEsbirro(Esbirro esbirro);

    protected void addListaEsbirros(Esbirro esbirro) {listaEsbirros.add(esbirro); }
}
