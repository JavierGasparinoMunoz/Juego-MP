import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Vampiro extends Personaje implements Serializable {

    private int puntosSangre;
    private int edad;

    public Vampiro(String nombre, ArrayList<Arma> listaArmas, HashSet<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro, int puntosSangre, int edad) {
        super(nombre, listaArmas, armasActivas, listaArmaduras, listaEsbirros, cantidadOro);
        this.puntosSangre = puntosSangre;
        this.edad = edad;
    }

    @Override
    public void añadirEsbirro(Esbirro esbirro) {
        if (!(esbirro instanceof Humano)){
            super.addListaEsbirros(esbirro);
        }
    }

    public int getPuntosSangre() {
        return puntosSangre;
    }

    public void setPuntosSangre(int puntosSangre) {
        this.puntosSangre = puntosSangre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
