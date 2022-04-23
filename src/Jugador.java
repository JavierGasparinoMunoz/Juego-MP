public class Jugador extends Usuario{

    public Jugador(String nombre, String nick, String password) {
        super(nombre, nick, password);
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getNick() {
        return super.getNick();
    }

    @Override
    public void setNick(String nick) {
        super.setNick(nick);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getNumReistro() {
        return super.getNumReistro();
    }

    @Override
    public void setNumReistro(String numReistro) {
        super.setNumReistro(numReistro);
    }
}
