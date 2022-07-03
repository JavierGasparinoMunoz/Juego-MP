public class Jugador extends Usuario{
    private Personaje personaje;
    private String numRegistro;
    public Jugador(String nombre, String nick, String password, Personaje personaje) {
        super(nombre, nick, password);
    }
    //SI USUARIO ES ABSTRACTA HAY QUE VOLVER A PONER LOS GETTERS Y SETTERS
    //@Override
    public String getNombre() {
        return super.getNombre();
    }

    //@Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    //@Override
    public String getNick() {
        return super.getNick();
    }

    //@Override
    public void setNick(String nick) {
        super.setNick(nick);
    }

    //@Override
    public String getPassword() {
        return super.getPassword();
    }

    //@Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public Personaje getPersonaje() { return personaje; }

    public void setPersonaje(Personaje personaje) { this.personaje = personaje; }

    public String getNumRegistro() {
        return getNumRegistro();
    }

    public void setNumRegistro(String numRegistro) {
        setNumRegistro(numRegistro);
    }
}
