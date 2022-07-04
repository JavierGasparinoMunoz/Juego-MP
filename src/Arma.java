import java.util.ArrayList;

public class Arma extends Equipo{
    private int numManos;
    public Arma(int modAtaque, int modDefensa, int numManos, String nombre, String categoria){
        super(modAtaque, modDefensa, nombre, categoria);
        this.numManos = numManos;
    }

    public int getNumManos() {
        return numManos;
    }

    public void setNumManos(int numManos) {
        this.numManos = numManos;
    }

    @Override
    public int getModAtaque() {
        return super.getModAtaque();
    }

    @Override
    public void setModAtaque(int modAtaque) {
        super.setModAtaque(modAtaque);
    }

    @Override
    public int getModDefensa() {
        return super.getModDefensa();
    }

    @Override
    public void setModDefensa(int modDefensa) {
        super.setModDefensa(modDefensa);
    }

    @Override
    public String getNombre(){
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public ArrayList<String> getListaMateriales() {
        return super.getListaMateriales();
    }

    @Override
    public void setListaMateriales(ArrayList<String> listaMateriales) {
        super.setListaMateriales(listaMateriales);
    }

    @Override
    public String getCategoria() {
        return super.getCategoria();
    }

    @Override
    public void setCategoria(String categoria) {
        super.setCategoria(categoria);
    }
}
