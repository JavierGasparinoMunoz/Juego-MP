import java.io.*;
import java.util.*;

public class Sistema implements Serializable {
    Scanner sc = new Scanner(System.in);
    private Notificacion notificacion;
    private Usuario usuario;
    private ArrayList<Usuario>  listaUsuarios = new ArrayList<>();
    private int opcion;

    public Sistema() throws IOException, ClassNotFoundException {
        String ruta = "Juego-Mp/archivos/usuarios.dat";
        File ficheroUsuarios = new File(ruta);
        if(ficheroUsuarios.canRead() == false){
            try {
                ficheroUsuarios.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            deserializableSistema(listaUsuarios);
        }
        menuInicio();
    }

    private void menuInicio(){
        System.out.println("----------------------------------");
        System.out.println("   Bienvenido al Menu de Inicio   ");
        System.out.println("----------------------------------");
        System.out.println("       Seleccione una opción      ");
        System.out.println("1 - Registrarse                   ");
        System.out.println("2 - Iniciar sesion                ");
        opcion = sc.nextInt();

        switch (opcion){
            case 1:
                registrarCuenta();
                break;
            case 2:
                iniciarSesion();
                break;
        }
    }

    private void registrarCuenta(){
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Introduce el nombre");
            String nombre = sc.nextLine();
            usuario.setNombre(nombre);

            System.out.println("Introduce el nick");
            String nick = sc.nextLine();
            usuario.setNick(nick);

            System.out.println("Introduce la contraseña");
            String contraseña = sc.nextLine();
            usuario.setPassword(contraseña);

            crearUsuario();
        }
    }

    public void crearUsuario(){
        usuario = new Usuario(usuario.getNombre(), usuario.getNick(), usuario.getPassword());
        listaUsuarios.add(usuario);
    }

    public void darseDeBaja(){
    }

    public void entrar(){
    }

    public void salir(){
    }

    public void registrarPersonaje(){
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

    public void elegirArmas(){
    }

    public void elegirArmaduras(){
    }

    public void desafiarUsuario(String nick, int oro){
    }

    public void aceptarDesafio(){
    }

    public void rechazarDesafio(){
    }

    public void consultarOroPartidasAnteriores(Personaje personaje){
    }

    public void consultarRankingGlobal(){
    }

    public void iniciarSesion(){
    }

    public HashSet<Arma> inicializarArmas(){
        //armas ofensivas
        Arma espadaPequeña = new Arma(2, 0, 1, "Espada pequeña");
        Arma espadon = new Arma(3, 1, 2, "Espadón");
        Arma guadanya = new Arma(3, 0, 2, "Guadaña");
        Arma palo = new Arma(1, 0, 2, "¡Un Palo!");
        Arma cuchillo = new Arma(1, 0, 1, "¡Un Palo!");
        Arma guantesMagicos = new Arma(3, 1, 2, "Guantes Mágicos");
        Arma varitaMagica = new Arma(2, 1, 2, "Varita Mágica");
        Arma varitaNoTanMagica = new Arma(1,0,2, "Varita No Tan Mágica");
        Arma ocarina = new Arma(1, 1, 2, "Ocarina");
        Arma bumeran = new Arma(2, 0, 1, "Bumerán");
        Arma bfs = new Arma(5, 1, 1, "B.F.Sword");
        Arma bajoAutoestima = new Arma(0, 0, 0, "Bajo Autoestima");

        //armas defensivas
        Arma escudoPequeño = new Arma(0, 2 , 1, "Escudo Pequeño");
        Arma escudoGrande = new Arma(0, 3, 1, "Escudo Grande");
        Arma hologramaFormacionTortuga = new Arma(0, 1, 1, "Holograma Formación Tortuga, (solamente intimida.)");

        HashSet<Arma> conjuntoArmas = new HashSet<Arma>(Arrays.asList(espadaPequeña, espadon, guadanya, palo, cuchillo, guantesMagicos, varitaMagica, varitaNoTanMagica, ocarina, bumeran, bfs, bajoAutoestima, escudoPequeño, escudoGrande, hologramaFormacionTortuga));
        return conjuntoArmas;

    }

    public HashSet<Armadura> inicializarArmaduras(){
        //armadura
        Armadura camisetaPrimark = new Armadura(0, 0, "Camiseta Primark");
        Armadura armaduraBasica = new Armadura(1,3, "Armadura Básica");
        Armadura armaduraTortuga = new Armadura(0,4, "Armadura Tortuga");
        Armadura armaduraDentada = new Armadura(2,2, "Armadura Dentada");

        HashSet<Armadura> conjuntoArmaduras = new HashSet<Armadura>(Arrays.asList(camisetaPrimark, armaduraBasica, armaduraTortuga, armaduraDentada));
        return conjuntoArmaduras;
    }
    public void serializableSistema(Usuario usuario) throws FileNotFoundException, IOException{
        String rutaArchivo ="Juego-MP/ficheros/usuarios.dat";
        File f1 = new File(rutaArchivo);
        ObjectOutputStream datosSalida = new ObjectOutputStream (new FileOutputStream(f1));
        datosSalida.writeObject(usuario);
    }
    //método encargado de obtener la información introducida por cliente/clientes anteriores.
    public void deserializableSistema (ArrayList<Usuario> listaUsuarios) throws FileNotFoundException, IOException, ClassNotFoundException {
        while (true) {
            String rutaArchivo = "Juego-MP/ficheros/usuarios.dat";
            ObjectInputStream datosEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
            Usuario dato = (Usuario) datosEntrada.readObject();
            listaUsuarios.add(dato);
        }
    }
}
