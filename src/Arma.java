public class Arma extends Equipo{
    private int numManos;

    public Arma(){
    }

    public Arma(int numManos) {
        this.numManos = numManos;
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
