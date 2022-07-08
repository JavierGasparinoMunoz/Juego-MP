
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Oferta implements Serializable {
    private ArrayList<Equipo> listaEquipo;
    private ArrayList<Esbirro> listaEsbirros;
    private int precio;
    private Usuario usuarioVendedor;

    public Oferta(ArrayList<Equipo> listaEquipo, ArrayList<Esbirro> listaEsbirros, int precio, Usuario usuarioVendedor){
        this.listaEquipo = listaEquipo;
        this.listaEsbirros = listaEsbirros;
        this.precio = precio;
        this.usuarioVendedor = usuarioVendedor;
    }
    public void mostrarOferta(){
        System.out.println("Usuario vendedor: " + usuarioVendedor.getNick());
        System.out.println("Precio: " + precio);
        System.out.println("Artículos vendidos: ");
        if (!listaEquipo.isEmpty()){
            String equipoVendido = "";
            for (Equipo equipo: listaEquipo ) {
                equipo.mostrarEquipo();
            }
            System.out.print(equipoVendido);
        }
        if (!listaEsbirros.isEmpty()){
            String esbirrosVendidos = "";
            for (Esbirro esbirro: listaEsbirros ) {
                esbirro.mostrarEsbirro();
            }
            System.out.print(esbirrosVendidos);
        }
    }
    public ArrayList<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public ArrayList<Esbirro> getListaEsbirros() {
        return listaEsbirros;
    }

    public int getPrecio() {
        return precio;
    }

    public void generarVentaLog(String usuario){
        Date date = new Date();
        VentaLog ventalog = new VentaLog(date, usuarioVendedor.getNick(), usuario, listaEquipo, listaEsbirros, precio);
    }

    public Usuario getUsuarioVendedor() {
        return usuarioVendedor;
    }
}
