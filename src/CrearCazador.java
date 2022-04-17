public class CrearCazador implements Creator{
    @Override
    public Personaje crearPersonaje() {
        Cazador c = new Cazador();
        return c;
    }
}
