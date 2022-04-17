public class Cazador extends Personaje{
    private int puntosVoluntad;

    public Cazador() {
    }

    public Cazador(int puntosVoluntad) {
        this.puntosVoluntad = puntosVoluntad;
    }

    @Override
    public void habilidadEspecial() {
    }

    @Override
    public void añadirEsbirro() {
    }

    @Override
    public void eliminarEsbirro() {
    }

    public int getPuntosVoluntad() {
        return puntosVoluntad;
    }

    public void setPuntosVoluntad(int puntosVoluntad) {
        this.puntosVoluntad = puntosVoluntad;
    }
}
