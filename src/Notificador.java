import java.util.ArrayList;

public class Notificador implements Observado{
    private ArrayList<Jugador> escuchadores;
    private String filtro;
    private Oferta oferta;


    public String getFiltro() {
        return filtro;
    }


    public Notificador(String filtro){
        this.filtro = filtro;
    }

    public void añadirOferta(Oferta oferta){
        this.oferta = oferta;
        notificar();
    }

    @Override
    public void suscribirse(Observado observado) {
        escuchadores.add((Jugador) observado);
    }

    @Override
    public void desuscribirse(Observado observado) {

    }

    @Override
    public void notificar() {
        for (Jugador jugador: escuchadores){
            String notificacion = "Hay una nueva oferta de las siguientes caracteristicas: /n";
            notificacion.concat(filtro + "\n");
            jugador.actualizar(notificacion);
        }
    }

}

