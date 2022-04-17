import java.util.HashSet;
import java.util.Set;

public class Demonio extends Esbirro{
    private Set<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private String pacto;

    public Demonio(){
    }

    public Demonio(Set<Esbirro> conjuntoEsbirros, String pacto) {
        this.conjuntoEsbirros = conjuntoEsbirros;
        this.pacto = pacto;
    }

    public Set<Esbirro> getConjuntoEsbirros() {
        return conjuntoEsbirros;
    }

    public void setConjuntoEsbirros(Set<Esbirro> conjuntoEsbirros) {
        this.conjuntoEsbirros = conjuntoEsbirros;
    }

    public String getPacto() {
        return pacto;
    }

    public void setPacto(String pacto) {
        this.pacto = pacto;
    }
}
