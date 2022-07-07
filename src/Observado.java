public interface Observado {
    public abstract void suscribirse(Observado observado);
    public abstract void desuscribirse(Observado observado);
    public abstract void notificar();
}
