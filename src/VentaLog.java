import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;

public class VentaLog implements Serializable {
    private Date fecha;
    private String usuarioVendedor, usuarioComprador;
    private int precio;
    private ArrayList<Equipo> listaEquipo = new ArrayList<>();
    private ArrayList<Esbirro> listaEsbirros = new ArrayList<>();

    public VentaLog(Date fecha, String usuarioVendedor, String usuarioComprador, ArrayList<Equipo> listaEquipo, ArrayList<Esbirro> listaEsbirros, int precio) {
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
        int i;
        if (!listaEquipo.isEmpty()){
            i = 0;
            System.out.println("Equipo: ");
            for (Equipo equipo: listaEquipo ) {
                System.out.println(i + ") ");
                equipo.mostrarEquipo();
                i++;
            }
        }
        if (!listaEsbirros.isEmpty()){
            i = 0;
            System.out.println("Esbirros: ");
            for (Esbirro esbirro: listaEsbirros ) {
                System.out.println(i + ") ");
                esbirro.mostrarEsbirro();
                i++;
            }
        }
    }
}
