public class Ghoul extends Esbirro{
    private int dependencia;

    public Ghoul(){
    }

    public Ghoul(int dependencia) {
        this.dependencia = dependencia;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }
}
