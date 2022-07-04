import java.util.ArrayList;

public abstract class Equipo {
    private int modAtaque, modDefensa;
    private String nombre, categoria; //no se si puede no ser protected
    private ArrayList<String> listaMateriales = new ArrayList<>();

    public Equipo(int modAtaque, int modDefensa, String nombre, String categoria){
        this.modAtaque = modAtaque;
        this.modDefensa = modDefensa;
        this.nombre = nombre;
        this.categoria = categoria;

    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public ArrayList<String> getListaMateriales() { return listaMateriales; }

    public void setListaMateriales(ArrayList<String> listaMateriales) { this.listaMateriales = listaMateriales;}

    public int getModAtaque() {
        return modAtaque;
    }

    public void setModAtaque(int modAtaque) {
        this.modAtaque = modAtaque;
    }

    public int getModDefensa() {
        return modDefensa;
    }

    public void setModDefensa(int modDefensa) { this.modDefensa = modDefensa; }
}
