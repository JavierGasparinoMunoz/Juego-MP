public class HabilidadEspecial {
    private String nombre;
    private int valorAtaque,valorDesfensa;

    public HabilidadEspecial(){
    }

    public HabilidadEspecial(String nombre, int valorAtaque, int valorDesfensa) {
        this.nombre = nombre;
        this.valorAtaque = valorAtaque;
        this.valorDesfensa = valorDesfensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorAtaque() {
        return valorAtaque;
    }

    public void setValorAtaque(int valorAtaque) {
        this.valorAtaque = valorAtaque;
    }

    public int getValorDesfensa() {
        return valorDesfensa;
    }

    public void setValorDesfensa(int valorDesfensa) {
        this.valorDesfensa = valorDesfensa;
    }
}
