import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Jugador extends Usuario implements Observador, Serializable {
    private Personaje personaje;
    private String numRegistro;
    private ArrayList<String> listaNotificaciones;
    private ArrayList<Notificador> listaNotificadores;
    public Jugador(String nombre, String nick, String password, Personaje personaje, String numRegistro) {
        super(nombre, nick, password);
        this.personaje = personaje;
        this.numRegistro = numRegistro;
        this.listaNotificaciones = new ArrayList<>();
        this.listaNotificadores = new ArrayList<>();
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) { setNumRegistro(numRegistro); }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
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
    public void actualizar(String notificacion) {
        listaNotificaciones.add(notificacion);
    }

    public void addNotificacion(String notificacion){
        listaNotificaciones.add(notificacion);
    }

    public void addNotificador(Notificador notificador){
        listaNotificadores.add(notificador);
    }

    public void mostrarNotificaciones() {
        if(!listaNotificaciones.isEmpty()) {
            for (String notificacion : listaNotificaciones) {
                System.out.println(notificacion);
            }
        }else{
            System.out.println("No hay notificaciones disponibles");
        }

    }

    public ArrayList<String> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public ArrayList<Notificador> getListaNotificadores() {
        return listaNotificadores;
    }

    public void vaciarListaNotificaciones() {
        listaNotificaciones = new ArrayList<>();
    }
}
