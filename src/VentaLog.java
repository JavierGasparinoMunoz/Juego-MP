import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VentaLog implements Serializable {
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

}
