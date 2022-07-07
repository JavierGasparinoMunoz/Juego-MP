import java.io.Serializable;
import java.util.ArrayList;

public class Armadura extends Equipo implements Serializable {
    public Armadura(int modAtaque, int modDefensa, String nombre, String categoria, ArrayList<String> listaMateriales){
        super(modAtaque, modDefensa, nombre, categoria,listaMateriales);
    }

    @Override
    public void mostrarEquipo() {
        System.out.println("Tipo: Armadura");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Modificador de ataque: " + getModAtaque());
        System.out.println("Modificador de defensa: " + getModDefensa());
        System.out.println("Categoria: " + getCategoria());
        for(String material: getListaMateriales()){
            System.out.println("Materiales:");
            System.out.println("-" + material);
        }
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
}
