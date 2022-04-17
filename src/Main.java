public class Main {
    public static void main(String[] args) {
        Creator c = new Creator() {
            @Override
            public Personaje crearPersonaje() {
                Personaje p = new Personaje() {
                    @Override
                    public void habilidadEspecial() {
                    }

                    @Override
                    public void añadirEsbirro() {
                    }

                    @Override
                    public void eliminarEsbirro() {
                    }
                };
                return p;
            }
        };
    }
}
