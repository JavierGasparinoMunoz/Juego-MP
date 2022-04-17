public class Equipo {
    private int modAtaque, modDefensa;

    public Equipo(){
    }

    public Equipo(int modAtaque, int modDefensa) {
        this.modAtaque = modAtaque;
        this.modDefensa = modDefensa;
    }

    public int getModAtaque() {
        return modAtaque;
    }

    public void setModAtaque(int modAtaque) {
        this.modAtaque = modAtaque;
    }

    public int getModDefensa() {
        return modDefensa;
    }

    public void setModDefensa(int modDefensa) {
        this.modDefensa = modDefensa;
    }
}
