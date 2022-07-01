import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Vampiro extends Personaje{

    private int puntosSangre;
    private int edad;

    public Vampiro(){
    }

    public Vampiro(int puntosSangre, int edad) {
        this.puntosSangre = puntosSangre;
        this.edad = edad;
    }

    @Override
    public void añadirEsbirro() {
    }

    @Override
    public void eliminarEsbirro() {
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
