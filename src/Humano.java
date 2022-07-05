public class Humano extends Esbirro{
    private int lealtad;

    public Humano(String nombre, int salud, int lealtad) {
        super(nombre, salud);
        this.lealtad = lealtad;
    }

    public int getLealtad() {
        return lealtad;
    }

    public void setLealtad(int lealtad) {
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
