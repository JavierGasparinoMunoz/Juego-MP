import java.util.HashSet;
import java.util.Set;

public class Demonio extends Esbirro{
    private HashSet<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private String pacto;

    public Demonio(String nombre, int salud, HashSet<Esbirro> conjuntoEsbirros, String pacto) {
        super(nombre, salud);
        this.conjuntoEsbirros = conjuntoEsbirros;
        this.pacto = pacto;
    }

    public Set<Esbirro> getConjuntoEsbirros() {
        return conjuntoEsbirros;
    }

    public void setConjuntoEsbirros(HashSet<Esbirro> conjuntoEsbirros) {
        this.conjuntoEsbirros = conjuntoEsbirros;
    }

    public String getPacto() {
        return pacto;
    }

    public void setPacto(String pacto) {
        this.pacto = pacto;
    }

    @Override
    public void mostrarEsbirro() {
        System.out.println("Tipo: Demonio");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Salud: " + getSalud());
        if (!getConjuntoEsbirros().isEmpty()) {
            System.out.println("Esbirros: ");
            for (Esbirro esbirro : getConjuntoEsbirros()) {
                esbirro.mostrarEsbirro();
            }
        }else{
            System.out.println("Este demonio no tiene más esbirros");
        }
    }
}
