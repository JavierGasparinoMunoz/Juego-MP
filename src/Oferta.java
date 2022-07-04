import java.util.ArrayList;

public class Oferta {
    private ArrayList<Equipo> listaEquipo;
    private ArrayList<Esbirro> listaEsbirros;
    private int precio;
    private String usuarioVendedor;

    public Oferta(ArrayList<Equipo> listaEquipo, ArrayList<Esbirro> listaEsbirros, int precio, String usuarioVendedor){
        this.listaEquipo = listaEquipo;
        this.listaEsbirros = listaEsbirros;
        this.precio = precio;
        this.usuarioVendedor = usuarioVendedor;
    }
    public void publicarOferta(){}
    //public void comprarOferta(){}
    public void generarVentaLog(){}
}
