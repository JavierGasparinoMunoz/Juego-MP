import java.io.*;
import java.util.*;

public class Sistema implements Serializable {
    Scanner sc = new Scanner(System.in);
    private Usuario usuario;
    private ArrayList<Usuario> whiteList, blackList = new ArrayList<>();
    private ArrayList<Oferta>  listaOfertas = new ArrayList<>();
    private ArrayList<Notificacion>  listaNotificaciones = new ArrayList<>();
    private ArrayList<VentaLog> listaLogs = new ArrayList<>();
    private HashSet<Arma> conjuntoArmas = new HashSet<>();
    private HashSet<Armadura> conjuntoArmaduras = new HashSet<>();


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
            deserializarSistema();
        }
        menuInicio();
    }

    private void consultarOferta(){
        int i = 1;
        if (!listaOfertas.isEmpty()) {
            for (Oferta oferta : listaOfertas) {
                System.out.println("Oferta numero " + i);
                oferta.mostrarOferta();
            }
            System.out.println("----------------------------------");
            System.out.println("Seleccione el numero de la oferta que quiere comprar o presione 0 para salir");
            int option = sc.nextInt();
            if (option != 0 && option<=listaOfertas.size() && option >= 0) {
                boolean oroDisponible = comprarOferta(listaOfertas.get(option - 1));
                if (!oroDisponible) {
                    System.out.println("No hay oro disponible");
                }
            }else{
                System.out.println("Inserte un numero valido");
            }
            sc.close();
        }else{
            System.out.println("No hay ofertas disponibles");
        }
    }

    private boolean comprarOferta(Oferta oferta){
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        if (cantidadOro < oferta.getPrecio()){
            ((Jugador) usuario).getPersonaje().setCantidadOro(cantidadOro - oferta.getPrecio());
            for(Equipo equipo: oferta.getListaEquipo()){
                if (equipo instanceof Arma){
                    Arma arma = ((Arma) equipo);
                    ((Jugador) usuario).getPersonaje().addListaArmas(arma);
                }else{
                    Armadura armadura = ((Armadura) equipo) ;
                    ((Jugador) usuario).getPersonaje().addListaArmaduras(armadura);
                }
            }
            for(Esbirro esbirro: oferta.getListaEsbirros()){
                ((Jugador) usuario).getPersonaje().añadirEsbirro(esbirro);
            }
            oferta.generarVentaLog(usuario.getNombre());
            return true;
        }else{
            return false;
        }
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
        try(Scanner sc = new Scanner(System.in)){ //preguntar si se quiere registrar un Operador o Jugador antes
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
        whiteList.add(usuario);
        serializarSistema();
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


    public void elegirArmasActivas(){
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

    /*public HashSet<Arma> inicializarArmas(){
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
    }*/

    public void serializarSistema() throws FileNotFoundException, IOException{
        String rutaArchivo ="./usuarios.bin";
        File f1 = new File(rutaArchivo);
        ObjectOutputStream datosSalida = new ObjectOutputStream (new FileOutputStream(f1));
        datosSalida.writeObject(whiteList);
    }
    //método encargado de obtener la información introducida por cliente/clientes anteriores.
    public void deserializarSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
        String rutaArchivo = "./usuarios.bin";
        ObjectInputStream datosEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
        whiteList = (ArrayList<Usuario>) datosEntrada.readObject();
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Usuario> getWhiteList() {
        return whiteList;
    }

    public void setlistaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.whiteList = listaUsuarios;
    }

    private Boolean comprobarSesion(String nick, String contraseña){
        int i = 0;
        boolean registrado = false;
        while(i < whiteList.size() && !registrado) {
            registrado = whiteList.get(i).getNick().equals(nick) && whiteList.get(i).getPassword().equals(contraseña);
            i = i + 1;
        }
        return registrado;
    }

    public void modificarOro(){
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        System.out.println("Indica cuánto oro quieres sumarte entre 0 y 1000");
        Scanner sc = new Scanner(System.in);
        int oroASumar = -1;
        while (oroASumar < 0 || oroASumar > 1000) {
            oroASumar = sc.nextInt();
            if (oroASumar < 0 || oroASumar > 1000){
                System.out.println("Vuélvelo a intentar, introduzca un número entre 0 y 1000");
            }
        }
        sc.close();
        cantidadOro += oroASumar;
        ((Jugador) usuario).getPersonaje().setCantidadOro(cantidadOro);
        System.out.println("El dinero se ha sumado correctamente");
    }

    public void banearUsuario() {
        if (!whiteList.isEmpty()) {
            System.out.println("¿Qué usuario quieres banear?");
            int i = 0;
            for (Usuario user : whiteList) {
                System.out.println(i + ") " + ((Jugador) user).getNick());
                i += 1;
            }
            Scanner sc = new Scanner(System.in);
            int opcion = -1;
            i -= 1;
            while (opcion < 0 || opcion > i) {
                opcion = sc.nextInt();
            }
            sc.close();
            Usuario user = blackList.get(opcion);
            whiteList.remove(user);
            blackList.add(user);
        }
        else{
            System.out.println("No hay jugadores para banear");
        }
    }

    public void desbanearUsuario(){
        if (!blackList.isEmpty()) {
            System.out.println("¿Qué usuario quieres banear?");
            int i = 0;
            for (Usuario user : blackList) {
                System.out.println(i + ") " + ((Jugador) user).getNick());
                i += 1;
            }
            i -= 1;
            Scanner sc = new Scanner(System.in);
            int opcion = -1;
            while (opcion < 0 || opcion > i) {
                opcion = sc.nextInt();
            }
            sc.close();
            Usuario user = blackList.get(opcion);
            blackList.remove(user);
            whiteList.add(user);
        }
        else{
            System.out.println("No hay jugadores baneados");
        }
    }

    public void consultarVentas(){
        if (!listaLogs.isEmpty()){
            int i = 0;
            for (VentaLog log: listaLogs) {
                System.out.println(i + ") ");
                log.imprimirLog();
                i += 1;
            }
        }
        else{
            System.out.println("No tuvo lugar ninguna venta");
        }
    }

    public void crearOferta(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
        int precio;
        String usuarioVendedor = ((Jugador) usuario).getNick();
        int opcion = -1;
        int opcion2 = -1;
        int i;
        int contador = 0;
        while (opcion < 4){
            System.out.println("Introduzca el tipo de artículo que quieres vender: ");
            System.out.println("1) Armas");
            System.out.println("2) Armadura");
            System.out.println("3) Esbirros");
            System.out.println("4) Cancelar");
            if (contador >= 1) {
                System.out.println("5) Finalizar oferta");
                while (opcion > 5 || opcion <1){
                    opcion = sc.nextInt();
                }
            }
            else{
                while (opcion > 4 || opcion <1){
                    opcion = sc.nextInt();
                }
            }
            i = 0;
            switch (opcion){
                case 1:
                    for (Arma arma: ((Jugador) usuario).getPersonaje().getListaArmas()){
                        System.out.println(i + ") " + arma.getNombre() + " Categoría: "+ arma.getCategoria()  + " Ataque: " + arma.getModAtaque() + " Defensa: " + arma.getModDefensa() + " Manos: " + arma.getNumManos());
                        i += 1;
                    }
                    i -= 1;
                    while (opcion2 > i || opcion2 < 0) {
                        opcion2 = sc.nextInt();
                    }
                    listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmas().get(opcion2));
                    break;
                case 2:
                    for (Armadura armadura: ((Jugador) usuario).getPersonaje().getListaArmaduras()){
                        System.out.println(i + ") " +  armadura.getNombre() + " Categoría: " + armadura.getCategoria() + armadura.getNombre() + " Ataque: " + armadura.getModAtaque() + " Defensa: " + armadura.getModDefensa());
                        i += 1;
                    }
                    i -= 1;
                    while (opcion2 > i || opcion2 < 0) {
                        opcion2 = sc.nextInt();
                    }
                    listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmaduras().get(opcion2));
                    break;
                case 3:
                    for (Esbirro esbirro: ((Jugador) usuario).getPersonaje().getListaEsbirros()){
                        Class claseEsbirro = esbirro.getClass();
                        System.out.println(i + ") Tipo: " + esbirro.getClass().getSimpleName() + " Nombre: " + esbirro.getNombre() + " Salud: " + esbirro.getSalud() + esbirro + " Ataque: " + ((claseEsbirro) esbirro) + " Defensa: " + armadura.getModDefensa());
                        i += 1;
                    }
                    i -= 1;
                    while (opcion2 > i || opcion2 < 0) {
                        opcion2 = sc.nextInt();
                    }
                    listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmaduras().get(opcion2));
                    break;

            }
            contador += 1;
        }


    }
    /*private Boolean encontrarNick(String nick){ //Creo que sobra, no se para qué está
        int i = 0;
        boolean encontrado = false;
        while(i < listaUsuarios.size() && !encontrado) {
            encontrado = listaUsuarios.get(i).getNick().equals(nick);
            i = i + 1;
        }
        return encontrado;
    }*/

}

