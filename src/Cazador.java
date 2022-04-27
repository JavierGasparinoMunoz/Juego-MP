import java.util.ArrayList;
import java.util.HashSet;

public class Cazador extends Personaje{
    private int puntosVoluntad;

    public Cazador() {
    }

    public Cazador(int puntosVoluntad) {
        this.puntosVoluntad = puntosVoluntad;
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
        Ataque = getAtaque() + getHabilidadEspecial().getValorAtaque() + getPuntosVoluntad();

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

    public int getPuntosVoluntad() {
        return puntosVoluntad;
    }

    public void setPuntosVoluntad(int puntosVoluntad) {
        this.puntosVoluntad = puntosVoluntad;
    }
}
