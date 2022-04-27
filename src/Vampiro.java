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
    public void habilidadEspecial() {
    }

    @Override
    public void añadirEsbirro() {
    }

    @Override
    public void eliminarEsbirro() {
    }

    @Override
    public int calcularAtaque() {
        int Ataque;
        Ataque = getAtaque() + getHabilidadEspecial().getValorAtaque();

        if (getPuntosSangre() > 4) {
            Random r = new Random();
            int ranNum = r.nextInt(2) + 1;
            puntosSangre -= ranNum;
            Ataque += 2;
        }

        HashSet<Arma> setArmas = getArmasActivas();
        HashSet<Armadura> setArmadura = getArmadurasActivas();
        ArrayList<Modificador> listDebilidades = getListaDebilidadesActivas();
        ArrayList<Modificador> listFortalezas = getListaFortalezasActivas();

        for(Arma a : setArmas){
            Ataque += a.getModAtaque();
        }

        for(Armadura b : setArmadura){
            Ataque += b.getModAtaque();
        }

        for(Modificador m : listDebilidades){
            Ataque += m.getValor();
        }

        for(Modificador n : listFortalezas){
            Ataque += n.getValor();
        }

        return Ataque;

    }
    @Override
    public int calcularDefensa() {
        int Defensa;
        Defensa = getDefensa() + getHabilidadEspecial().getValorDesfensa();

        HashSet<Arma> setArmas = getArmasActivas();
        HashSet<Armadura> setArmadura = getArmadurasActivas();
        ArrayList<Modificador> listDebilidades = getListaDebilidadesActivas();
        ArrayList<Modificador> listFortalezas = getListaFortalezasActivas();

        for (Arma a : setArmas) {
            Defensa += a.getModDefensa();
        }

        for (Armadura b : setArmadura) {
            Defensa += b.getModDefensa();
        }

        for(Modificador m : listDebilidades){
            Defensa += m.getValor();
        }

        for(Modificador n : listFortalezas){
            Defensa += n.getValor();
        }

        return Defensa;
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
