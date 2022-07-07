import java.io.Serializable;

public class Humano extends Esbirro implements Serializable {
    private String lealtad;

    public Humano(String nombre, int salud, String lealtad) {
        super(nombre, salud);
        this.lealtad = lealtad;
    }

    public String getLealtad() {
        return lealtad;
    }

    public void setLealtad(String lealtad) {
        this.lealtad = lealtad;
    }

    @Override
    public void mostrarEsbirro() {
        System.out.println("Tipo: Humano");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Salud: " + getSalud());
        System.out.println("Lealtad: " + getLealtad());
    }

}
