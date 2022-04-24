public class Armadura extends Equipo{
    public Armadura(int modAtaque, int modDefensea, String nombre){
        this.modDefensa = modDefensa;
        this.modAtaque = modAtaque;
        this.nombre = nombre;
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
