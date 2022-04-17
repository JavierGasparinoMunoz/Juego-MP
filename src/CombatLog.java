import java.io.Serializable;
import java.util.Date;

public class CombatLog implements Serializable {
    private Date fecha;
    private String usuarioDesafiante;
    private String usuarioDesafiado;
    private String usuarioVebcedor;
    private int oroGanado;

    public CombatLog(){

    }

    public CombatLog(Date fecha, String usuarioDesafiante, String usuarioDesafiado, String usuarioVebcedor, int oroGanado) {
        this.fecha = fecha;
        this.usuarioDesafiante = usuarioDesafiante;
        this.usuarioDesafiado = usuarioDesafiado;
        this.usuarioVebcedor = usuarioVebcedor;
        this.oroGanado = oroGanado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioDesafiante() {
        return usuarioDesafiante;
    }

    public void setUsuarioDesafiante(String usuarioDesafiante) {
        this.usuarioDesafiante = usuarioDesafiante;
    }

    public String getUsuarioDesafiado() {
        return usuarioDesafiado;
    }

    public void setUsuarioDesafiado(String usuarioDesafiado) {
        this.usuarioDesafiado = usuarioDesafiado;
    }

    public String getUsuarioVebcedor() {
        return usuarioVebcedor;
    }

    public void setUsuarioVebcedor(String usuarioVebcedor) {
        this.usuarioVebcedor = usuarioVebcedor;
    }

    public int getOroGanado() {
        return oroGanado;
    }

    public void setOroGanado(int oroGanado) {
        this.oroGanado = oroGanado;
    }
}
