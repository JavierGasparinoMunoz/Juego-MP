import java.io.Serializable;
import java.util.ArrayList;

public class Notificador implements Observado, Serializable {
    private ArrayList<Jugador> escuchadores;
    private String filtro;
    private Oferta oferta;


    public String getFiltro() {
        return filtro;
    }


    public Notificador(String filtro){
        this.filtro = filtro;
        this.escuchadores = new ArrayList<>();
    }

    public void añadirOferta(Oferta oferta){
        this.oferta = oferta;
        notificar();
    }

    @Override
    public void suscribirse(Observador observador) {
        escuchadores.add((Jugador) observador);
    }

    @Override
    public void desuscribirse(Observador observador) {

    }

    @Override
    public void notificar() {
        for (Jugador jugador: escuchadores){
            if (jugador.getNick().equals(oferta.getUsuarioVendedor().getNick())){
                String notificacion = "Hay una nueva oferta de las siguientes caracteristicas: \n";
                notificacion = notificacion.concat(filtro + "\n");
                jugador.actualizar(notificacion);
            }
        }
    }

}

