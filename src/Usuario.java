import java.io.Serializable;
import java.util.Scanner;

public class Usuario implements Serializable {
    private String nombre;
    private String nick;
    private String password;
    private String numReistro;
    private Personaje personaje;

    public Usuario(){
    }

    public Usuario(String nombre, String nick, String password, Personaje personaje) {
        this.nombre = nombre;
        this.nick = nick;
        this.password = password;
        this.personaje = personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumReistro() {
        return numReistro;
    }

    public void setNumReistro(String numReistro) {
        this.numReistro = numReistro;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
