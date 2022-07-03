import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VentaLog {
    private Date fecha;
    private String usuarioVendedor, usuarioComprador;
    private int precio;
    private ArrayList<String> listaEquipo = new ArrayList<>();
    private ArrayList<String> listaEsbirros = new ArrayList<>();
    public VentaLog(){

    }

    public VentaLog(Date fecha, String usuarioVendedor, String usuarioComprador, ArrayList<String> listaEquipo, ArrayList<String> listaEsbirros, int precio) {
        this.fecha = fecha;
        this.usuarioVendedor = usuarioVendedor;
        this.usuarioComprador = usuarioComprador;
        this.listaEquipo = listaEquipo;
        this.listaEsbirros = listaEsbirros;
        this.precio = precio;
    }
    public void imprimirLog(){
        System.out.print("Fecha: " + fecha);
        System.out.println("Usuario vendedor: " + usuarioVendedor);
        System.out.println("Usuario comprador: " + usuarioComprador);
        System.out.println("Precio: " + precio);
        System.out.println("Artículos vendidos: ");
        if (!listaEquipo.isEmpty()){
            String equipoVendido = "";
            for (String equipo: listaEquipo ) {
                equipoVendido.concat(equipo + " ");
            }
            System.out.print(equipoVendido);
        }
        if (!listaEsbirros.isEmpty()){
            String esbirrosVendidos = "";
            for (String esbirro: listaEsbirros ) {
                esbirrosVendidos.concat(esbirro + " ");
            }
            System.out.print(esbirrosVendidos);
        }
    }
}
