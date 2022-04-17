public class Licantropo extends Personaje{
    private int puntosRabia;

    public Licantropo() {
    }

    public Licantropo(int puntosRabia) {
        this.puntosRabia = puntosRabia;
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

    public int getPuntosRabia() {
        return puntosRabia;
    }

    public void setPuntosRabia(int puntosRabia) {
        this.puntosRabia = puntosRabia;
    }
}
