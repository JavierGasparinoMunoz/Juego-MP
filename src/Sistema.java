import java.io.*;
import java.util.*;

public class Sistema implements Serializable {
    Scanner sc = new Scanner(System.in);
    private Notificacion notificacion;
    private Usuario usuario = new Usuario();
    private ArrayList<Usuario>  listaUsuarios = new ArrayList<>();
    private int opcionMI,opcionRol,opcionMP1,opcionMP2;
    private Personaje p;

    public Sistema() throws IOException, ClassNotFoundException {
        String ruta = "./usuarios.bin";
        File ficheroUsuarios = new File(ruta);
        if( !ficheroUsuarios.exists()){
            try {
                ficheroUsuarios.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        else{
            deserializableSistema();
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
        System.out.println("----------------------------------");
        opcionMI = sc.nextInt();

        switch (opcionMI){
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
            String nick;
            do {
                System.out.println("Introduce el nick");
                nick = sc.nextLine();
                usuario.setNick(nick);
            }while(encontrarNick(nick));

            System.out.println("Introduce la contraseña");
            String contraseña = sc.nextLine();
            usuario.setPassword(contraseña);

            crearUsuario();
            menuPrincipal();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void crearUsuario() throws IOException {
        usuario = new Usuario(usuario.getNombre(), usuario.getNick(), usuario.getPassword(), usuario.getPersonaje());
        listaUsuarios.add(usuario);
        serializableSistema();
    }

    private void menuPrincipal(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu principal " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Registrar un personaje / Gestion avanzada de personaje");
        System.out.println("2. Darse de baja");
        System.out.println("3. Salir");
        System.out.println("-----------------------------------------------------");
        opcionMP1 = sc.nextInt();
        switch (opcionMP1){
            case 1:
                if (usuario.getPersonaje()==null){
                    registrarPersonaje();
                } else {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Bienvenido al menu avanzado para personajes " + usuario.getNick());
                    System.out.println("Elige una de las siguientes opciones");
                    System.out.println("1. Elegir armas");
                    System.out.println("2. Elegir armaduras");
                    System.out.println("3. Consultar oro apostado en partidas anteriores");
                    System.out.println("4. Desafiar un usuario");
                    System.out.println("5. Consultar ranking global");
                    System.out.println("6. Darse de baja");
                    System.out.println("7. Salir");
                    System.out.println("-----------------------------------------------------");
                    opcionMP2 = sc.nextInt();
                    switch (opcionMP2){
                        case 1:
                            elegirArmas();
                            break;
                        case 2:
                            elegirArmaduras();
                            break;
                        case 3:
                            consultarOroPartidasAnteriores(p);
                            break;
                        case 4:
                            System.out.println("Introduce el nickname del usuario que quieres desafiar");
                            String nickDes = sc.nextLine();
                            if (encontrarNick(nickDes)){
                                System.out.println("Introduce el oro que quieres apostar");
                                int oroApostado = sc.nextInt();
                                desafiarUsuario(nickDes,oroApostado);
                            }
                            break;
                        case 5:
                            consultarRankingGlobal();
                            break;
                        case 6:
                            darseDeBaja();
                            break;
                        case 7:
                            break;
                    }
                }
                break;
            case 2:
                darseDeBaja();
                break;
            case 3:
                break;
        }
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
                p = usuario.getPersonaje();
                System.out.println("Elige un rol");
                System.out.println("1. Cazador");
                System.out.println("2. Vampiro");
                System.out.println("3. Licantropo");
                opcionRol = sc.nextInt();
                switch (opcionRol) {
                    case 1:
                        CrearCazador cazador = new CrearCazador();
                        p = cazador.crearPersonaje();
                        break;
                    case 2:
                        CrearVampiro vampiro = new CrearVampiro();
                        p =  vampiro.crearPersonaje();
                        break;
                    case 3:
                        CrearLicantropo licantropo = new CrearLicantropo();
                        p =  licantropo.crearPersonaje();
                        break;
                }
                usuario.setPersonaje(p);
                return p;
            }
        };
        c.crearPersonaje();
        elegirArmadurasDefecto();
        elegirArmasDefecto();
        elegirOroApostadoDefecto();
    }

    private void elegirOroApostadoDefecto() {
    }

    private void elegirArmasDefecto() {
    }

    private void elegirArmadurasDefecto() {
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
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Nombre de usuario");
            String nick = sc.nextLine();
            System.out.println("Contraseña");
            String contraseña = sc.nextLine();
            if (comprobarSesion(nick, contraseña)){
                usuario.setNick(nick);
                usuario.setPassword(contraseña);
                menuPrincipal();
            }
            else{
                System.out.println("Inicio de sesión erroneo vuelva a intentarlo");
                System.out.println();
                iniciarSesion();
            }
        }
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
    public void serializableSistema() throws FileNotFoundException, IOException{
        String rutaArchivo ="./usuarios.bin";
        File f1 = new File(rutaArchivo);
        ObjectOutputStream datosSalida = new ObjectOutputStream (new FileOutputStream(f1));
        datosSalida.writeObject(listaUsuarios);
    }
    //método encargado de obtener la información introducida por cliente/clientes anteriores.
    public void deserializableSistema () throws FileNotFoundException, IOException, ClassNotFoundException {
        String rutaArchivo = "./usuarios.bin";
        ObjectInputStream datosEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
        listaUsuarios = (ArrayList<Usuario>) datosEntrada.readObject();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    private Boolean comprobarSesion(String nick, String contraseña){
        int i = 0;
        boolean registrado = false;
        while(i < listaUsuarios.size() && !registrado) {
            registrado = listaUsuarios.get(i).getNick().equals(nick) && listaUsuarios.get(i).getPassword().equals(contraseña);
            i = i + 1;
        }
        return registrado;
    }

    private Boolean encontrarNick(String nick){
        int i = 0;
        boolean encontrado = false;
        while(i < listaUsuarios.size() && !encontrado) {
            encontrado = listaUsuarios.get(i).getNick().equals(nick);
            i = i + 1;
        }
        return encontrado;
    }

}

