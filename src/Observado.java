public interface Observado {
    public abstract void suscribirse(Observador observador);
    public abstract void desuscribirse(Observador observador);
    public abstract void notificar();
}
