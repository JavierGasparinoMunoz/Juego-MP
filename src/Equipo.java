import java.util.ArrayList;

public abstract class Equipo {
    protected int modAtaque, modDefensa;
    protected String nombre, categoria; //no se si puede no ser protected
    protected ArrayList<String> listaMateriales = new ArrayList<>();

    public Equipo(){
    }

    public int getModAtaque() {
        return modAtaque;
    }

    public void setModAtaque(int modAtaque) {
        this.modAtaque = modAtaque;
    }

    public int getModDefensa() {
        return modDefensa;
    }

    public void setModDefensa(int modDefensa) {
        this.modDefensa = modDefensa;
    }
}
