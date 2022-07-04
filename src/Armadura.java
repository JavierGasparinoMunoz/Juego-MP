public class Armadura extends Equipo{
    public Armadura(int modAtaque, int modDefensa, String nombre, String categoria){
        super(modAtaque, modDefensa, nombre, categoria);
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
