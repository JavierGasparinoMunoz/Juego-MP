public class CrearVampiro implements Creator{

    @Override
    public Personaje crearPersonaje() {
        Vampiro v = new Vampiro();
        return v;
    }
}
