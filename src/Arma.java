public class Arma extends Equipo{
    private int numManos;
    public Arma(int modAtaque, int modDefensa, int numManos, String nombre) {
        this.numManos = numManos;
        this.modDefensa = modDefensa;
        this.modAtaque = modAtaque;
        this.nombre = nombre;
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
}
