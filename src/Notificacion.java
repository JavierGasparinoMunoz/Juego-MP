import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Notificacion {
    ArrayList<Usuario> escuchadores;
    EscuchdorDesafios escuchdorDesafios;

    public void notificar(Escuchador tipoEscuchador, String texto){
        String ruta = "Juego-Mp/archivos/notificacion.dat";
        File ficheroNotificacion = new File(ruta);
        if(ficheroNotificacion.canRead() == false){
            try {
                ficheroNotificacion.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            subscribir(escuchdorDesafios,"Te han desafiado");
        }
    }

    public void subscribir(Escuchador tipoEscuchador, String texto){

    }

    public void desusbscribir(Escuchador tipoEscuchador, String texto){

    }
}

