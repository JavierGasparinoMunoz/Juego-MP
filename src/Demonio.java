import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Demonio extends Esbirro implements Serializable {
    private ArrayList<Esbirro> conjuntoEsbirros = new ArrayList<>();
    private String pacto;

    public Demonio(String nombre, int salud, String pacto) {
        super(nombre, salud);
        this.pacto = pacto;

        //Creación de esbirros hijos aleatorios para no estar constantemente introduciendo información por pantalla.
        int numEsbirrosHijos = (int)(Math.random()*3);
        for (int i=0; i == numEsbirrosHijos; i++) { //Ponemos tope para que no se creen un número excesivo de esbirros.
            int tipoEsbirro = (int) (Math.random() * 3);
            String nombreHijo;
            int saludHijo = (int) (Math.random() * 3) + 1;
            switch (tipoEsbirro) {
                case 0:
                    nombreHijo = "Humano #" + (int) (Math.random() * 10000);
                    String lealtad = "";
                    int randLealtad = (int) (Math.random() * 3);
                    switch (randLealtad) {
                        case 0:
                            lealtad = "ALTA";
                            break;
                        case 1:
                            lealtad = "MEDIA";
                            break;
                        case 2:
                            lealtad = "BAJA";
                            break;
                    }

                    Humano humano = new Humano(nombreHijo, saludHijo, lealtad);
                    conjuntoEsbirros.add(humano);
                    break;
                case 1:
                    nombreHijo = "Ghoul #" + (int) (Math.random() * 10000);
                    int independencia = (int) (Math.random() * 5 + 1);
                    Ghoul ghoul = new Ghoul(nombreHijo, saludHijo, independencia);
                    conjuntoEsbirros.add(ghoul);
                    break;
                case 2:
                    nombreHijo = "Demonio #" + (int) (Math.random() * 10000);
                    String pactoHijo = nombreHijo + " ha pactado con " + nombre;
                    Demonio demonio = new Demonio(nombreHijo, saludHijo, pactoHijo);
                    conjuntoEsbirros.add(demonio);
                    break;
            }
        }
    }

    public ArrayList<Esbirro> getConjuntoEsbirros() {
        return conjuntoEsbirros;
    }

    public void setConjuntoEsbirros(ArrayList<Esbirro> conjuntoEsbirros) {
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
