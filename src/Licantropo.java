import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Licantropo extends Personaje implements Serializable {
    private int puntosRabia;

    public Licantropo(String nombre, ArrayList<Arma> listaArmas, ArrayList<Arma> armasActivas, ArrayList<Armadura> listaArmaduras, ArrayList<Esbirro> listaEsbirros, int cantidadOro,int puntosRabia) {
        super(nombre,listaArmas,armasActivas,listaArmaduras,listaEsbirros,cantidadOro);
        this.puntosRabia = puntosRabia;
    }


    @Override
    public void añadirEsbirro(Esbirro esbirro) {
            super.addListaEsbirros(esbirro);
    }

    @Override
    public void eliminarEsbirro() {
    }

    public int getPuntosRabia() {
        return puntosRabia;
    }

    public void setPuntosRabia(int puntosRabia) {
        this.puntosRabia = puntosRabia;
    }
}
