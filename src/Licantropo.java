import java.util.ArrayList;
import java.util.HashSet;

public class Licantropo extends Personaje{
    private int puntosRabia;

    public Licantropo(int puntosRabia) {
        this.puntosRabia = puntosRabia;
    }


    @Override
    public void añadirEsbirro(Esbirro esbirro) {
        if (!(esbirro instanceof Humano)){
            super.addListaEsbirros(esbirro);
        }
    }

    @Override
    public void eliminarEsbirro() {
    }

    @Override
    public int calcularAtaque() {
        return 0;
    }

    /*@Override
    public int calcularAtaque() {
        int Ataque;
        Ataque = getAtaque() + getHabilidadEspecial().getValorAtaque();
        if (getHabilidadEspecial().getRabiaMinima() < puntosRabia) {
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

    }*/
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

    public int getPuntosRabia() {
        return puntosRabia;
    }

    public void setPuntosRabia(int puntosRabia) {
        this.puntosRabia = puntosRabia;
    }
}
