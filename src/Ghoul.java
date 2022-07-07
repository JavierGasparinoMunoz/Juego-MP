import java.io.Serializable;

public class Ghoul extends Esbirro implements Serializable {
    private int dependencia;

    public Ghoul(String nombre, int salud, int dependencia) {
        super(nombre, salud);
        this.dependencia = dependencia;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }

    @Override
    public void mostrarEsbirro() {
        System.out.println("Tipo: Ghoul");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Salud: " + getSalud());
        System.out.println("Dependencia: " + getDependencia());
    }
}
