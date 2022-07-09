import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Sistema implements Serializable {
    private Usuario usuario;
    private ArrayList<Usuario> whiteList, blackList = new ArrayList<>();
    private ArrayList<Oferta> listaOfertas, listaOfertasNoValidadas = new ArrayList<>();
    private ArrayList<Notificador> listaNotificadores = new ArrayList<>();
    private ArrayList<VentaLog> listaLogs = new ArrayList<>();
    private ArrayList<Arma> conjuntoArmas = new ArrayList<>();
    private ArrayList<Armadura> conjuntoArmaduras = new ArrayList<>();
    private int opcionMI, opcionRol, opcionMP1, opcionMP2, opcionMOF, opcionMOP, opcionMU;
    private Personaje p;

    public Sistema() throws IOException, ClassNotFoundException {
        if (listaOfertas == null){
            listaOfertas = new ArrayList<>();
        }
        Scanner sc = new Scanner(System.in);
        inicializarArmaduras();
        inicializarArmas();
        menuInicio(sc);
    }

    private void consultarOferta(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        int i = 1;
        if (!listaOfertas.isEmpty()) {
            for (Oferta oferta : listaOfertas) {
                //todo falta no ver tus propias ofertas
                if(oferta.getUsuarioVendedor().getNick() != usuario.getNick()) {
                    System.out.println("Oferta numero " + i + ")");
                    oferta.mostrarOferta();
                    i++;
                }
            }
            System.out.println("----------------------------------");
            System.out.println("Seleccione el numero de la oferta que quiere comprar o presione 0 para salir");
            int option;
            do{
                option = sc.nextInt();
            } while(option > listaOfertas.size() || option < 0 );
            if (option != 0) {
                boolean oroDisponible = comprarOferta(listaOfertas.get(option - 1));
                if (!oroDisponible) {
                    System.out.println("No hay oro disponible");
                }
            }
        } else {
            System.out.println("No hay ofertas disponibles");
        }
    }

    private boolean comprarOferta(Oferta oferta) {
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        if (cantidadOro >= oferta.getPrecio()) {
            ((Jugador) usuario).getPersonaje().setCantidadOro(cantidadOro - oferta.getPrecio());
            for (Equipo equipo : oferta.getListaEquipo()) {
                if (equipo instanceof Arma) {
                    Arma arma = ((Arma) equipo);
                    ((Jugador) usuario).getPersonaje().addListaArmas(arma);
                } else {
                    Armadura armadura = ((Armadura) equipo);
                    ((Jugador) usuario).getPersonaje().addListaArmaduras(armadura);
                }
            }
            for (Esbirro esbirro : oferta.getListaEsbirros()) {
                if (!(((Jugador) usuario).getPersonaje() instanceof Vampiro && esbirro instanceof Humano)){
                    ((Jugador) usuario).getPersonaje().añadirEsbirro(esbirro);
                } else{
                    System.out.println("Un vampiro no puede comprar un humano");
                }
            }
            VentaLog ventaLog = oferta.generarVentaLog(usuario.getNombre());
            listaLogs.add(ventaLog);
            ((Jugador) oferta.getUsuarioVendedor()).getPersonaje().setCantidadOro(cantidadOro + oferta.getPrecio());
            return true;
        } else {
            return false;
        }
    }

    public void menuInicio(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("   Bienvenido al Menu de Inicio   ");
        System.out.println("----------------------------------");
        System.out.println("       Seleccione una opción      ");
        System.out.println("1 - Registrarse                   ");
        System.out.println("2 - Iniciar sesion                ");
        System.out.println("3 - Terminar ejecucion");
        System.out.println("----------------------------------");
        do{
            opcionMI = sc.nextInt();
            if(opcionMI < 1 || opcionMI > 3){
                System.out.println("Introduce una opcion correcta");
            }
        } while(opcionMI < 1 || opcionMI > 3);
        switch (opcionMI) {
            case 1:
                usuario = null;
                registrarCuenta(sc);
                break;
            case 2:
                usuario = null;
                iniciarSesion(sc);
                break;
            case 3:
                System.out.println("Saliendo...");
                //System.exit(0);
                break;
        }
    }

    private void registrarCuenta(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Como quieres registrate:");
        System.out.println("1. Jugador");
        System.out.println("2. Operador");
        System.out.println("3. Volver al menú de inicio");
        int opcion;
        do {
            opcion = sc.nextInt();
            if (opcion < 1 || opcion >3){
                System.out.println("Introduce una opcion correcta");
            }
        }while(opcion < 1 || opcion > 3);
        switch (opcion) {
            case 1:
                System.out.println("Introduce el nombre");
                String nombre = sc.next();
                String nick;
                do { //solo hacer encontrarNick() si !whitelist.isEmpty()
                    System.out.println("Introduce el nick");
                    nick = sc.next();
                } while (encontrarNick(nick));

                System.out.println("Introduce la contraseña");
                String contraseña = sc.next();
                while (contraseña.length() < 8 || contraseña.length() > 12) {
                    System.out.println("La contraseña debe ser entre 8 y 12, vuelva a intentarlo");
                    contraseña = sc.next();
                }
                registrarPersonaje(sc);
                Personaje personaje = p;

                String numeroRegistro = calcularNumRegistro();
                Jugador player = new Jugador(nombre, nick, contraseña, personaje, numeroRegistro);
                usuario = player;
                int opcionEquipo;
                do {
                    añadirEquipo(sc);
                    System.out.println("Si quieres añadir mas equipo pulsa 1, sino, pulsa 0");
                    opcionEquipo = sc.nextInt();
                }while(opcionEquipo != 0);
                elegirArmasActivas(sc);
                break;
            case 2:
                System.out.println("Introduzca el codigo secreto o introduzca 1 para cancelar");
                int codigo = sc.nextInt();
                while (codigo != 1 && codigo != 1234) {
                    System.out.println("Codigo incorrecto, vuelva intentarlo o cancele la accion");
                    codigo = sc.nextInt();
                }
                switch (codigo) {
                    case 1:
                        registrarCuenta(sc);
                        break;
                    case 1234:
                        System.out.println("Introduce el nombre");
                        String nombreOperador = sc.next();
                        String nickOperador;
                        do {
                            System.out.println("Introduce el nick");
                            nickOperador = sc.next();
                        } while (encontrarNick(nickOperador));

                        System.out.println("Introduce la contraseña");
                        String contraseñaOperador = sc.next();
                        Operador admin = new Operador(nombreOperador, nickOperador, contraseñaOperador);
                        usuario = admin;
                        break;
                }
                break;
            case 3:
                menuInicio(sc);
                break;
        }
        if (opcion != 3){
            crearUsuario();
            menuPrincipal(sc);
        }
    }

    public void crearUsuario() throws IOException {
        if (whiteList != null) {
            whiteList.add(usuario);
            serializarSistema();
        } else {
            whiteList = new ArrayList<>();
            whiteList.add(usuario);
            serializarSistema();
        }
    }

    private void menuPrincipal(Scanner sc) throws IOException {
        if (usuario instanceof Jugador) {
            menuJugador(sc);
        } else {
            menuOperador(sc);
        }
        serializarSistema();
    }

    private void menuOperador(Scanner sc) throws IOException {
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu principal " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Validar ofertas");
        System.out.println("2. Gestionar usuarios");
        System.out.println("3. Consultar las ventas realizadas");
        System.out.println("4. Darse de baja");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------------------");
        do{
            opcionMOP = sc.nextInt();
        } while(opcionMOP < 1 || opcionMOP > 5);

        switch (opcionMOP) {
            case 1:
                validarOferta(sc);
                break;
            case 2:
                menuUsuario(sc);
                break;
            case 3:
                consultarVentas();
                break;
            case 4:
                darseDeBaja(sc);
                break;
            case 5:
                salir(sc);
                break;
        }
        if (opcionMOP != 5 && opcionMOP != 4){
            menuPrincipal(sc);
        }
    }

    private void menuJugador(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu principal " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Gestion avanzada de personaje");
        System.out.println("2. Gestion avanzada de las ofertas");
        System.out.println("3. Darse de baja");
        System.out.println("4. Mostrar Notificaciones");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------------------");
        do{
            opcionMI = sc.nextInt();
            if(opcionMI > 5 || opcionMI < 1){
                System.out.println("Introduce una opcion correcta");
            }
        }while(opcionMI > 5 || opcionMI < 1);

        switch (opcionMI) {
            case 1:
                menuAvanzadoPersonaje(sc);
                break;
            case 2:
                menuAvanzadoOfertas(sc);
                break;
            case 3:
                darseDeBaja(sc);
                break;
            case 4:
                mostrarNotificaciones();
                break;
            case 5:
                salir(sc);
                break;

        }
        if (opcionMI != 5 && opcionMI != 3){
            menuPrincipal(sc);
        }
    }

    private void mostrarNotificaciones() {
        ((Jugador) usuario).mostrarNotificaciones();
        ((Jugador) usuario).vaciarListaNotificaciones();
    }

    private void menuAvanzadoPersonaje(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu avanzado para personajes " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Modificar equipo");
        System.out.println("2. Modificar oro");
        System.out.println("3. Consultar información del personaje");
        System.out.println("4. Volver al menu principal");
        System.out.println("-----------------------------------------------------");
        do{
            opcionMP2 = sc.nextInt();
            if (opcionMP2 > 4 || opcionMP2 < 1) {
                System.out.println("Introduce una opcion correcta");
            }
        }while(opcionMP2 > 4 || opcionMP2 < 1);
        switch (opcionMP2) {
            case 1:
                modificarEquipo(sc);
                break;
            case 2:
                modificarOro(sc);
                break;
            case 3:
                consultarInformacionPersonaje(sc);
                break;
        }
    }

    private void menuAvanzadoOfertas(Scanner sc) throws IOException {
        System.out.println("Bienvenido al menu Avanzado de Ofertas " + usuario.getNick());
        System.out.println("¿Que operacion desea realizar?");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear una oferta");
        System.out.println("2. Buscar y comprar ofertas");
        System.out.println("3. Suscribirse a una oferta");
        System.out.println("4. Desuscribirse a una oferta");
        System.out.println("5. Volver al menu principal");
        System.out.println("--------------------------------------");
        do{
            opcionMOF = sc.nextInt();
            if (opcionMOF > 5 || opcionMOF < 1){
                System.out.println("Introduce una opcion correcta");
            }
        }while(opcionMOF > 5 || opcionMOF < 1);
        switch (opcionMOF) {
            case 1:
                crearOferta(sc);
                break;
            case 2:
                consultarOferta(sc);
                break;
            case 3:
                suscribirseOferta(sc);
                break;
            case 4:
                desuscribirse(sc);
                break;
        }
    }

    public void darseDeBaja(Scanner sc) throws IOException {
        whiteList.remove(usuario);
        System.out.println("Se ha dado de baja correctamente");
        menuInicio(sc);
    }

    public void consultarInformacionPersonaje(Scanner sc) throws IOException {
        System.out.println("Cantidad de oro del Personaje: " + p.getCantidadOro() + " monedas de oro");
        System.out.println("Armas del Personaje:");
        int i = 0;
        if(!p.getListaArmas().isEmpty()) {
            while (i < p.getListaArmas().size()) {
                if(!p.getArmasActivas().contains(p.getListaArmas().get(i))) {
                    System.out.println(i + 1 + ". " + p.getListaArmas().get(i).getNombre());
                }
                i += 1;
            }
        }else {
            System.out.println("Este personaje no tiene Armas");
        }
        System.out.println("Armaduras del Personaje:");
        i = 0;
        if(!p.getListaArmaduras().isEmpty()) {
            while (i < p.getListaArmaduras().size()) {
                System.out.println(i + 1 + ". " + p.getListaArmaduras().get(i).getNombre());
                i += 1;
            }
        }else {
            System.out.println("Este personaje no tiene Armaduras");
        }
        System.out.println("Armas activas:");
        i = 0;
        if(!p.getArmasActivas().isEmpty()) {
            while (i < p.getArmasActivas().size()) {
                System.out.println(i + 1 + ".");
                p.getArmasActivas().get(i).mostrarEquipo();
                i += 1;
            }
        }else {
            System.out.println("Este personaje no tiene Armas Activas");
        }
        System.out.println("Esbirros del Personaje:");
        i = 0;
        if(!p.getListaEsbirros().isEmpty()) {
            while (i < p.getListaEsbirros().size()) {
                Esbirro esbirro = p.getListaEsbirros().get(i);
                esbirro.mostrarEsbirro();
                i += 1;
            }
        }else {
            System.out.println("Este personaje no tiene Esbirros");
        }
    }

    public void salir(Scanner sc) throws IOException {
        //Este método sale de la sesión
        menuInicio(sc);
    }

    private Personaje crearPersonajeBase(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del personaje");
        String nombre = sc.next();
        System.out.println("Introduzca la cantidad de oro del personaje");
        int cantidadOro = sc.nextInt();
        ArrayList<Arma> armasActivas = new ArrayList<>();
        ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
        p = new Personaje(nombre, new ArrayList<Arma>(), armasActivas, new ArrayList<Armadura>(), listaEsbirros, cantidadOro) {
            @Override
            public void añadirEsbirro(Esbirro esbirro) {

            }
        };
        return p;
    }

    public void crearEsbirro(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Elige el tipo de esbirro que quieres crear");
        System.out.println("1 - Humano");
        System.out.println("2 - Ghoul");
        System.out.println("3 - Demonio");
        int opcion = sc.nextInt();
        System.out.println("Introduce el nombre del esbirro");
        String nombreEsbirro = sc.next();
        System.out.println("Introduce la salud para el esbirro");
        int salud = sc.nextInt();
        switch (opcion) {
            case 1:
                if (p instanceof Vampiro) {
                    System.out.println("Los vampiros no pueden tener humanos, introduzca otro tipo de esbirro");
                    crearEsbirro(sc);
                } else {
                    System.out.println("Introduce el tipo de lealtad (ALTA,NORMAL o BAJA)");
                    String lealtad = sc.next().toUpperCase();
                    while (!lealtad.equals("ALTA") && !lealtad.equals("NORMAL") && !lealtad.equals("BAJA")) {
                        System.out.println("El tipo de lealtad tiene que ser ALTO,NORMAL o BAJA");
                        lealtad = sc.next().toUpperCase();
                    }
                    Humano h = new Humano(nombreEsbirro, salud, lealtad);
                    p.getListaEsbirros().add(h);
                }
                break;
            case 2:
                boolean error = false;
                System.out.println("Introduce la dependencia");
                int dependencia = 0;
                do {
                    try {
                        dependencia = sc.nextInt();
                    } catch (NumberFormatException e) {
                        System.out.println("El valor debe ser numerico");
                        error = true;
                    }
                    while (dependencia < 1 || dependencia > 5) {
                        System.out.println("La dependencia debe ser un numero entre 1 y 5");
                        dependencia = sc.nextInt();
                    }
                } while (error);
                Ghoul g = new Ghoul(nombreEsbirro, salud, dependencia);
                p.getListaEsbirros().add(g);
                break;
            case 3:
                System.out.println("Dime la descripcion del pacto");
                String descripcion = sc.next();
                Demonio demonio = new Demonio(nombreEsbirro, salud, descripcion);
                p.getListaEsbirros().add(demonio);
                break;
            default:
                System.out.println("Introduce una opcion correcta");
                crearEsbirro(sc);
                break;
        }
    }

    private void suscribirseOferta(Scanner sc) {
        String filtro = "";
        //Scanner sc = new Scanner(System.in);
        System.out.println("Elige el tipo de oferta al que te quieres suscribir");
        System.out.println("1 - Por tipo de equipo/esbirros");
        System.out.println("2 - Por categoría");
        System.out.println("3 - Por valor");
        System.out.println("4 - Por lealtad de esbirro");
        System.out.println("5 - Por tipo de esbirro");
        System.out.println("6 - Por tipo de usuario que realiza la oferta");
        System.out.println("7 - Por un precio mínimo-máximo");
        System.out.println("0 - Salir");
        int opcion;
        do {
            opcion = sc.nextInt();
        } while (opcion < 0 || opcion > 7);
        switch (opcion) {
            case 1:
                filtro = "Suscripcion por tipo de equipo/esbirros: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - Armas");
                System.out.println("2 - Armaduras");
                System.out.println("3 - Esbirros");
                System.out.println("4 - Armas y Armaduras");
                System.out.println("5 - Armas y Esbirros");
                System.out.println("6 - Armaduras y Esbirros");
                System.out.println("0 - Salir");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 6);
                switch (opcion) {
                    case 1:
                        filtro = filtro.concat("-Armas \n");
                        break;
                    case 2:
                        filtro = filtro.concat("-Armaduras \n");
                        break;
                    case 3:
                        filtro = filtro.concat("-Esbirros \n");
                        break;
                    case 4:
                        filtro = filtro.concat("-Armas \n-Armaduras \n");
                        break;
                    case 5:
                        filtro = filtro.concat("-Armas \n-Esbirros \n");
                        break;
                    case 6:
                        filtro = filtro.concat("-Armaduras \n-Esbirros \n");
                        break;
                }
                break;
            case 2:
                filtro = "Suscripcion por categoría: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - Comun");
                System.out.println("2 - Raro");
                System.out.println("3 - Epico");
                System.out.println("4 - Legendario");
                System.out.println("0 - Salir");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 4);
                switch (opcion) {
                    case 1:
                        filtro = filtro.concat("-Comun \n");
                        break;
                    case 2:
                        filtro = filtro.concat("-Raro \n");
                        break;
                    case 3:
                        filtro = filtro.concat("-Epico \n");
                        break;
                    case 4:
                        filtro = filtro.concat("-Legendario \n");
                        break;
                }
                break;
            case 3:
                filtro = "Suscripcion por valor de equipo: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - Valor de Armas");
                System.out.println("2 - Valor de Armaduras");
                System.out.println("0 - Salir");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 2);
                switch (opcion) {
                    case 1:
                        System.out.println("Elige una opcion para suscribirse");
                        System.out.println("1 - Modificador de Ataque del arma de 1");
                        System.out.println("2 - Modificador de Ataque del arma de 2");
                        System.out.println("3 - Modificador de Ataque del arma de 3");
                        System.out.println("4 - Modificador de Defensa del arma de 1");
                        System.out.println("5 - Modificador de Defensa del arma de 2");
                        System.out.println("6 - Modificador de Defensa del arma de 3");
                        System.out.println("0 - Salir");
                        do {
                            opcion = sc.nextInt();
                        } while (opcion < 0 || opcion > 6);
                        switch (opcion) {
                            case 1:
                                filtro = filtro.concat("-Modificador de Ataque del arma de 1");
                                break;
                            case 2:
                                filtro = filtro.concat("-Modificador de Ataque del arma de 2");
                                break;
                            case 3:
                                filtro = filtro.concat("-Modificador de Ataque del arma de 3");
                                break;
                            case 4:
                                filtro = filtro.concat("-Modificador de Defensa del arma de 1");
                                break;
                            case 5:
                                filtro = filtro.concat("-Modificador de Defensa del arma de 2");
                                break;
                            case 6:
                                filtro = filtro.concat("-Modificador de Defensa del arma de 3");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Elige una opcion para suscribirse");
                        System.out.println("1 - Modificador de Ataque de la armadura de 1");
                        System.out.println("2 - Modificador de Ataque de la armadura de 2");
                        System.out.println("3 - Modificador de Ataque de la armadura de 3");
                        System.out.println("4 - Modificador de Defensa de la armadura de 1");
                        System.out.println("5 - Modificador de Defensa de la armadura de 2");
                        System.out.println("6 - Modificador de Defensa de la armadura de 3");
                        System.out.println("0 - Salir");
                        do {
                            opcion = sc.nextInt();
                        } while (opcion < 0 || opcion > 6);
                        switch (opcion) {
                            case 1:
                                filtro = filtro.concat("-Modificador de Ataque de la armadura de 1");
                                break;
                            case 2:
                                filtro = filtro.concat("-Modificador de Ataque de la armadura del arma de 2");
                                break;
                            case 3:
                                filtro = filtro.concat("-Modificador de Ataque de la armadura del arma de 3");
                                break;
                            case 4:
                                filtro = filtro.concat("-Modificador de Defensa de la armadura del arma de 1");
                                break;
                            case 5:
                                filtro = filtro.concat("-Modificador de Defensa de la armadura del arma de 2");
                                break;
                            case 6:
                                filtro = filtro.concat("-Modificador de Defensa de la armadura del arma de 3");
                                break;
                        }
                        break;
                }
            case 4:
                filtro = "Suscripcion por lealtad de Esbirro: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - ALTA");
                System.out.println("2 - MEDIA");
                System.out.println("3 - BAJA");
                System.out.println("0 - Salir");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 3);
                switch (opcion) {
                    case 1:
                        filtro = filtro.concat("-ALTA");
                        break;
                    case 2:
                        filtro = filtro.concat("-MEDIA");
                        break;
                    case 3:
                        filtro = filtro.concat("-BAJA");
                        break;
                }
                break;
            case 5:
                filtro = "Suscripcion por tipo de esbirro: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - Demonio");
                System.out.println("2 - Ghoul");
                System.out.println("3 - Cazador");
                System.out.println("0 - Humano");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 3);
                switch (opcion) {
                    case 1:
                        filtro = filtro.concat("-Demonio");
                        break;
                    case 2:
                        filtro = filtro.concat("-Ghoul");
                        break;
                    case 3:
                        filtro = filtro.concat("-Humano");
                        break;
                }
                break;
            case 6:
                filtro = "Suscripcion por tipo de usuario: \n";
                System.out.println("Elige una opcion para suscribirse");
                System.out.println("1 - Licantropo");
                System.out.println("2 - Vampiro");
                System.out.println("3 - Cazador");
                System.out.println("0 - Salir");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 3);
                switch (opcion) {
                    case 1:
                        filtro = filtro.concat("-Licantropo");
                        break;
                    case 2:
                        filtro = filtro.concat("-Vampiro");
                        break;
                    case 3:
                        filtro = filtro.concat("-Cazador");
                        break;
                }
                break;
            case 7:
                filtro = "Suscripcion por un precio minimo-maximo: \n";
                System.out.println("Elige el precio minimo [0-1000]: ");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 1000);
                filtro = filtro.concat("Min: " + opcion + " oro \n");
                System.out.println("Elige el precio maximo [0-1000]: ");
                do {
                    opcion = sc.nextInt();
                } while (opcion < 0 || opcion > 999);
                filtro = filtro.concat("Max: " + opcion + " oro");
                break;
        }
                boolean encontrado = false;
                int i = 0;
                int indice = -1;
                for (Notificador notificador : listaNotificadores) {
                    if (notificador.getFiltro().equals(filtro)) {
                        encontrado = true;
                        indice = i;
                        i++;
                    }
                }
                if (!encontrado) {
                    Notificador notificador = new Notificador(filtro);
                    notificador.suscribirse((Observador) usuario);
                    listaNotificadores.add(notificador);
                    ((Jugador) usuario).addNotificador(notificador);

                } else {
                    listaNotificadores.get(indice).suscribirse((Observador) usuario);
                    ((Jugador) usuario).addNotificador(listaNotificadores.get(indice));
                }
    }



    public void desuscribirse(Scanner sc) {
        if (((Jugador) usuario).getListaNotificadores().isEmpty()) {
            System.out.println("No tienes ninguna suscripción!");
        } else {
            //Scanner sc = new Scanner(System.in);
            int opcion;
            System.out.println("Elija un filtro del que te quieres desuscribir:");
            int i = 0;
            for (Notificador notificador : ((Jugador) usuario).getListaNotificadores()) {
                System.out.println(i + ") " + notificador.getFiltro());
                i++;
            }
            do {
                opcion = sc.nextInt();
            } while (opcion > 0 || opcion > i--);
            ((Jugador) usuario).getListaNotificadores().get(opcion).desuscribirse((Observador) usuario);
            ((Jugador) usuario).getListaNotificadores().remove(opcion);
        }

    }

    public Personaje registrarPersonaje(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        p = crearPersonajeBase(sc);
        System.out.println("Elige un rol");
        System.out.println("1. Cazador");
        System.out.println("2. Vampiro");
        System.out.println("3. Licantropo");
        opcionRol = sc.nextInt();
        switch (opcionRol) {
            case 1:
                CrearCazador cazador = new CrearCazador();
                p = cazador.crearPersonaje(p.getNombre(), p.getListaArmas(), p.getArmasActivas(), p.getListaArmaduras(), p.getListaEsbirros(), p.getCantidadOro(), sc);
                break;
            case 2:
                CrearVampiro vampiro = new CrearVampiro();
                p = vampiro.crearPersonaje(p.getNombre(), p.getListaArmas(), p.getArmasActivas(), p.getListaArmaduras(), p.getListaEsbirros(), p.getCantidadOro(), sc);
                break;
            case 3:
                CrearLicantropo licantropo = new CrearLicantropo();
                p = licantropo.crearPersonaje(p.getNombre(), p.getListaArmas(), p.getArmasActivas(), p.getListaArmaduras(), p.getListaEsbirros(), p.getCantidadOro(), sc);
                break;
            default:
                System.out.println("Introduce una opcion correcta");
                registrarPersonaje(sc);
                break;
        }
        crearEsbirro(sc);
        return p;
    }


    public void iniciarSesion(Scanner sc) throws IOException {

            System.out.println("Nombre de usuario");
            String nick = sc.next();
            System.out.println("Contraseña");
            String contraseña = sc.next();

            if (comprobarSesion(nick, contraseña) && !encontrarBaneado(nick)) {
                usuario = atribuirUsuario(nick,contraseña, sc);
                if (usuario != null)
                    menuPrincipal(sc);
                else{
                    menuInicio(sc);
                }
            } else if (!comprobarSesion(nick, contraseña)) {
                System.out.println("Inicio de sesion erroneo vuelva a intentarlo");
                System.out.println();
                menuInicio(sc);
            } else {
                System.out.println("Su usuario esta baneado");
                System.out.println();
                menuInicio(sc);
            }
    }

    private Usuario atribuirUsuario(String nick, String contraseña, Scanner sc) throws IOException {
        if (whiteList != null) {
            int i = 0;
            boolean registrado = false;
            while (i < whiteList.size() && !registrado) {
                registrado = whiteList.get(i).getNick().equals(nick) && whiteList.get(i).getPassword().equals(contraseña);
                i = i + 1;
            }
            if (registrado && whiteList.get(i - 1) instanceof Jugador) {
                usuario = whiteList.get(i - 1);
                p = ((Jugador) whiteList.get(i - 1)).getPersonaje();
            }
            return whiteList.get(i-1);
        } else {
            System.out.println("Error");
        }
        return usuario;
    }

    public void modificarEquipo(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione una opcion");
        System.out.println("1. Añadir Equipo");
        System.out.println("2. Eliminar Equipo");
        System.out.println("3. Elegir Armas Activas");
        System.out.println("4. Volver al menu principal");
        int opcion;
        do {
            opcion = sc.nextInt();
            if(opcion < 1 || opcion > 4){
                System.out.println("Introduzca una opcion valida");
            }
        } while(opcion < 1 || opcion > 4);
        switch (opcion) {
            case 1:
                añadirEquipo(sc);
                break;
            case 2:
                eliminarEquipo(sc);
                break;
            case 3:
                elegirArmasActivas(sc);
                break;
        }
    }

    private void añadirEquipo(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        System.out.println("Seleccione el equipo que desea añadir");
        System.out.println("Armaduras:");
        int i = 1;
        for (Armadura armadura : conjuntoArmaduras) {
            if (!((Jugador) usuario).getPersonaje().getListaArmaduras().contains(armadura)) {
                System.out.println("Numero: " + i + ")");
                armadura.mostrarEquipo();
                System.out.println();
                listaEquipo.add(armadura);
                i++;
            }
        }
        System.out.println("Armas:");
        for (Arma arma : conjuntoArmas) {
            if (!((Jugador) usuario).getPersonaje().getListaArmas().contains(arma)) {
                System.out.println("Numero: " + i + ")");
                arma.mostrarEquipo();
                System.out.println();
                listaEquipo.add(arma);
                i++;
            }
        }
        int opcion;
        do {
            opcion = sc.nextInt();
        } while (opcion > listaEquipo.size() || opcion < 1 );
        Equipo e = listaEquipo.get(opcion-1);
        if (e instanceof Arma) {
            if (((Jugador) usuario).getPersonaje().getListaArmas().size() < 4) {
                ((Jugador) usuario).getPersonaje().addListaArmas((Arma) e);
            } else {
                System.out.println("No puedes añadir mas de 3 armas a tu personaje");
            }
        } else {
            if (((Jugador) usuario).getPersonaje().getListaArmaduras().size() < 4) {
                ((Jugador) usuario).getPersonaje().addListaArmaduras((Armadura) e);
            } else {
                System.out.println("No puedes añadir mas de 3 armaduras a tu personaje");
            }
        }
    }

    private void eliminarEquipo(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        int i;
        if (((Jugador) usuario).getPersonaje().getListaArmas().isEmpty() && ((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()) {
            System.out.println("No tienes equipo para eliminar");
        } else {
            if (!((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()) {
                System.out.println("Armaduras:");
                i = 1;
                for (Armadura armadura : ((Jugador) usuario).getPersonaje().getListaArmaduras()) {
                    System.out.println("Numero: " + i + ")");
                    armadura.mostrarEquipo();
                    System.out.println();
                    listaEquipo.add(armadura);
                    i++;
                }
            }
            if (!((Jugador) usuario).getPersonaje().getListaArmas().isEmpty()) {
                System.out.println("Armas:");
                i = 1;
                for (Arma arma : ((Jugador) usuario).getPersonaje().getListaArmas()) {
                    System.out.println("Numero: " + i + ")");
                    arma.mostrarEquipo();
                    System.out.println();
                    listaEquipo.add(arma);
                    i++;
                }
            }
            int opcion = sc.nextInt();
            if (opcion < listaEquipo.size() && 0 < opcion) {
                Equipo e = listaEquipo.get(opcion);
                if (e instanceof Arma) {
                    ((Jugador) usuario).getPersonaje().removeListaArmas((Arma) e);
                } else {
                    ((Jugador) usuario).getPersonaje().removeListaArmaduras((Armadura) e);
                }
            }
        }
    }

    private void elegirArmasActivas(Scanner sc) {
        if(!((Jugador) usuario).getPersonaje().getListaArmas().isEmpty()) {
            //Scanner sc = new Scanner(System.in);
            ((Jugador) usuario).getPersonaje().getArmasActivas().clear();
            System.out.println("Elija una o dos armas activas o pulse 0 para salir");
            System.out.println();
            int i = 0;
            int opcion;
            do {
                int j = 1;
                for(Arma arma:((Jugador) usuario).getPersonaje().getListaArmas()){
                    if(!p.getArmasActivas().contains(arma)) {
                        System.out.println("Arma " + (j) + ":");
                        System.out.println();
                        arma.mostrarEquipo();
                    }
                    j+=1;
                }
                System.out.println();
                opcion = sc.nextInt();
                if(opcion <=0 || opcion > ((Jugador) usuario).getPersonaje().getListaArmas().size()){
                    if(opcion == 0){
                        System.out.println("Saliendo del menu elegir armas...");
                    }else {
                        System.out.println("Ese numero no es valido, porfavor escoja un numero valido para poder elegir el arma");
                        System.out.println();
                        opcion = 1;
                    }
                } else{
                    if(p.getListaArmas().size() == 1) {
                        ((Jugador) usuario).getPersonaje().getArmasActivas().add(((Jugador) usuario).getPersonaje().getListaArmas().get(opcion - 1));
                        System.out.println("No tienes mas armas para poder ponerlas como armas activas");
                        i = 2;
                        opcion = 0;
                    }else{
                        ((Jugador) usuario).getPersonaje().getArmasActivas().add(((Jugador) usuario).getPersonaje().getListaArmas().get(opcion - 1));
                        i +=1;
                        System.out.println("Si quieres salir pulsa 0, sino, pulse 1");
                        opcion = sc.nextInt();
                    }
                }
            } while(i<2 && opcion != 0);
            if(i >= 2){
                System.out.println("Lo sentimos, no puede añadir mas de 2 armas activas");
            }
        }else{
            System.out.println("Este personaje no tiene armas, añada armas al personaje para poder añadirlas a las armas activas");
        }

    }

    public void inicializarArmas() {
        //armas ofensivas
        ArrayList<String> materiales1 = new ArrayList<>(Arrays.asList("Cobre", "Hierro", "Madera"));
        ArrayList<String> materiales2 = new ArrayList<>(Arrays.asList("Acero", "Hierro"));
        ArrayList<String> materiales3 = new ArrayList<>(Arrays.asList("Diamante", "Hierro", "Madera"));
        ArrayList<String> materiales4 = new ArrayList<>(Arrays.asList("Acero", "Cuero"));
        ArrayList<String> materiales5 = new ArrayList<>(Arrays.asList("Plata", "Acero"));
        ArrayList<String> materiales6 = new ArrayList<>(Arrays.asList("Madera"));
        ArrayList<String> materiales7 = new ArrayList<>(Arrays.asList("Cuero"));
        Arma espadaPequeña = new Arma(2, 0, 1, "Espada pequeña", "Comun", materiales2);
        Arma espadon = new Arma(3, 1, 2, "Espadón", "Raro", materiales1);
        Arma guadanya = new Arma(3, 1, 2, "Guadaña", "Raro", materiales3);
        Arma palo = new Arma(1, 1, 2, "¡Un Palo!", "Comun", materiales6);
        Arma cuchillo = new Arma(1, 1, 1, "Cuchillo", "Comun", materiales4);
        Arma guantesMagicos = new Arma(3, 1, 2, "Guantes Mágicos", "Epico", materiales7);
        Arma varitaMagica = new Arma(2, 1, 2, "Varita Mágica", "Legendario", materiales3);
        Arma varitaNoTanMagica = new Arma(1, 1, 2, "Varita No Tan Mágica", "Comun", materiales6);
        Arma ocarina = new Arma(1, 1, 2, "Ocarina", "Raro", materiales6);
        Arma bumeran = new Arma(2, 1, 1, "Bumerán", "Comun", materiales6);
        Arma bfs = new Arma(2, 1, 1, "B.F.Sword", "Epico", materiales5);
        Arma bajoAutoestima = new Arma(1, 1, 1, "Bajo Autoestima", "Legendario", materiales3);

        //armas defensivas
        Arma escudoPequeño = new Arma(1, 2, 1, "Escudo Pequeño", "Comun", materiales1);
        Arma escudoGrande = new Arma(1, 3, 1, "Escudo Grande", "Raro", materiales1);
        Arma hologramaFormacionTortuga = new Arma(1, 1, 1, "Holograma Formación Tortuga, (solamente intimida.)", "Epico", materiales5);

        conjuntoArmas = new ArrayList<>(Arrays.asList(espadaPequeña, espadon, guadanya, palo, cuchillo, guantesMagicos, varitaMagica, varitaNoTanMagica, ocarina, bumeran, bfs, bajoAutoestima, escudoPequeño, escudoGrande, hologramaFormacionTortuga));

    }

    public void inicializarArmaduras() {
        //armadura
        ArrayList<String> materiales1 = new ArrayList<>(Arrays.asList("Algodon"));
        ArrayList<String> materiales2 = new ArrayList<>(Arrays.asList("Cuero"));
        ArrayList<String> materiales3 = new ArrayList<>(Arrays.asList("Hiero", "Acero", "Cuero", "Diamante"));
        ArrayList<String> materiales4 = new ArrayList<>(Arrays.asList("Diamante", "Hierro"));
        Armadura camisetaPrimark = new Armadura(1, 1, "Camiseta Primark", "Comun", materiales1);
        Armadura armaduraBasica = new Armadura(1, 3, "Armadura Básica", "Raro", materiales2);
        Armadura armaduraTortuga = new Armadura(1, 3, "Armadura Tortuga", "Epico", materiales4);
        Armadura armaduraDentada = new Armadura(2, 2, "Armadura Dentada", "Legendaria", materiales3);

        conjuntoArmaduras = new ArrayList<Armadura>(Arrays.asList(camisetaPrimark, armaduraBasica, armaduraTortuga, armaduraDentada));

    }

    public void serializarSistema() throws FileNotFoundException, IOException {
        String rutaArchivo = "./informacion.bin";
        File f1 = new File(rutaArchivo);
        ObjectOutputStream datosSalida = new ObjectOutputStream(new FileOutputStream(f1));
        datosSalida.writeObject(this);
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

    private Boolean comprobarSesion(String nick, String contraseña) {
        if (whiteList != null) {
            int i = 0;
            boolean registrado = false;
            while (i < whiteList.size() && !registrado) {
                registrado = whiteList.get(i).getNick().equals(nick) && whiteList.get(i).getPassword().equals(contraseña);
                i = i + 1;
            }
            if (registrado && whiteList.get(i - 1) instanceof Jugador) {
                usuario = whiteList.get(i - 1);
                p = ((Jugador) whiteList.get(i - 1)).getPersonaje();
            }
            return registrado;
        } else {
            return false;
        }
    }

    public void modificarOro(Scanner sc) {
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        System.out.println("1) Sumar oro");
        System.out.println("2) Restar oro");
        int opcion;
        //Scanner sc = new Scanner(System.in);
        do {
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 2);
        if (opcion == 1) {
            System.out.println("Indica cuánto oro quieres sumarte entre 0 y 1000");
        } else {
            System.out.println("Indica cuánto oro quieres restarte entre 0 y 1000");
        }
        int oroASumar = -1;
        while (oroASumar < 0 || oroASumar > 1000) {
            oroASumar = sc.nextInt();
            if (oroASumar < 0 || oroASumar > 1000) {
                System.out.println("Vuélvelo a intentar, introduzca un número entre 0 y 1000");
            }
        }
        if (opcion == 1) {
            cantidadOro += oroASumar;
        } else {
            cantidadOro -= oroASumar;
            if (cantidadOro < 0) {
                cantidadOro = 0;
            }
        }

        ((Jugador) usuario).getPersonaje().setCantidadOro(cantidadOro);
        System.out.println("El oro se ha modificado correctamente");
        System.out.println("Nuevo saldo: " + ((Jugador) usuario).getPersonaje().getCantidadOro());
    }

    private void menuUsuario(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu de gestion de usuarios " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Banear usuario");
        System.out.println("2. Desbanear usuario");
        System.out.println("3. Volver al menu del operador");
        System.out.println("-----------------------------------------------------");
        do{
            opcionMU = sc.nextInt();
            if(opcionMU < 1 || opcionMU > 3){
                System.out.println("Introduce una opcion correcta");
            }
        } while(opcionMU < 1 || opcionMU > 3);
        switch (opcionMU) {
            case 1:
                banearUsuario(sc);
                break;
            case 2:
                desbanearUsuario(sc);
                break;
        }
    }

    public ArrayList<Usuario> getBlackList() {
        return blackList;
    }

    private void banearUsuario(Scanner sc) {
        if (!whiteList.isEmpty()) {
            System.out.println("¿Qué usuario quieres banear?");
            int i = 0;
            for (Usuario user : whiteList) {
                System.out.println(i + ") " + user.getNick());
                i += 1;
            }
            //Scanner sc = new Scanner(System.in);
            int opcion = -1;
            i -= 1;
            while (opcion < 0 || opcion > i) {
                opcion = sc.nextInt();
            }

            Usuario user = whiteList.get(opcion);
            whiteList.remove(user);
            blackList.add(user);
        } else {
            System.out.println("No hay jugadores para banear");
        }
    }

    private void desbanearUsuario(Scanner sc) {
        if (!blackList.isEmpty()) {
            System.out.println("¿Qué usuario quieres desbanear?");
            int i = 0;
            for (Usuario user : blackList) {
                System.out.println(i + ") " + ((Jugador) user).getNick());
                i += 1;
            }
            i -= 1;
            //Scanner sc = new Scanner(System.in);
            int opcion = -1;
            while (opcion < 0 || opcion > i) {
                opcion = sc.nextInt();
            }

            Usuario user = blackList.get(opcion);
            blackList.remove(user);
            whiteList.add(user);
        } else {
            System.out.println("No hay jugadores baneados");
        }
    }

    public void consultarVentas() {
        if (!listaLogs.isEmpty()) {
            int i = 0;
            for (VentaLog log : listaLogs) {
                System.out.println(i + ") ");
                log.imprimirLog();
                i += 1;
            }
        } else {
            System.out.println("No tuvo lugar ninguna venta");
        }
    }

    public void crearOferta(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
        int opcion = -1;
        int opcion2 = -1;
        int i;
        int contador = 0;
        while (opcion < 4 && contador < 3) {
            System.out.println("Introduzca el tipo de artículo que quieres vender: ");
            System.out.println("1) Armas");
            System.out.println("2) Armadura");
            System.out.println("3) Esbirros");
            System.out.println("4) Cancelar");
            if (contador >= 1) {
                System.out.println("5) Finalizar oferta");
                do {
                    opcion = sc.nextInt();
                } while (opcion > 5 || opcion < 1);
            } else {
                do{
                    opcion = sc.nextInt();
                } while (opcion > 4 || opcion < 1);
            }
            i = 0;
            switch (opcion) {
                case 1:
                    if (((Jugador) usuario).getPersonaje().getListaArmas().isEmpty()) {
                        System.out.println("No se ha encontrado nada!");
                    } else {
                        for (Arma arma : ((Jugador) usuario).getPersonaje().getListaArmas()) {
                            System.out.println(i + ") ");
                            arma.mostrarEquipo();
                            i++;
                        }
                        i -= 1;
                        while (opcion2 > i || opcion2 < 0) {
                            opcion2 = sc.nextInt();
                        }
                        listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmas().get(opcion2));
                        contador += 1;
                    }
                    break;
                case 2:
                    if (((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()) {
                        System.out.println("No se ha encontrado nada!");
                    } else {
                        for (Armadura armadura : ((Jugador) usuario).getPersonaje().getListaArmaduras()) {
                            System.out.println(i + ") ");
                            armadura.mostrarEquipo();
                            i++;
                        }
                        i -= 1;
                        while (opcion2 > i || opcion2 < 0) {
                            opcion2 = sc.nextInt();
                        }
                        listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmaduras().get(opcion2));
                        contador += 1;
                    }
                    break;
                case 3:
                    if (((Jugador) usuario).getPersonaje().getListaEsbirros().isEmpty()) {
                        System.out.println("No se ha encontrado nada!");
                    } else {
                        for (Esbirro esbirro : ((Jugador) usuario).getPersonaje().getListaEsbirros()) {
                            System.out.println(i + ") ");
                            esbirro.mostrarEsbirro();
                            i++;
                        }
                        i -= 1;
                        while (opcion2 > i || opcion2 < 0) {
                            opcion2 = sc.nextInt();
                        }
                        listaEsbirros.add(((Jugador) usuario).getPersonaje().getListaEsbirros().get(opcion2));
                        contador += 1;
                    }
                    break;
            }
        }

        if (opcion != 4) {
            System.out.println("Introduzca un precio valido: ");
            int precio;
            do {
                precio = sc.nextInt();
            } while (precio < 0);
            Oferta oferta = new Oferta(listaEquipo, listaEsbirros, precio, usuario);
            listaOfertasNoValidadas.add(oferta);

            //Quitar armas del inventario del usuario
            for (Equipo equipo : listaEquipo) {
                if (equipo instanceof Arma) {
                    ((Jugador) usuario).getPersonaje().getListaArmas().remove((Arma) equipo);
                } else {
                    ((Jugador) usuario).getPersonaje().getListaArmaduras().remove((Armadura) equipo);
                }
            }
            for (Esbirro esbirro : listaEsbirros) {
                ((Jugador) usuario).getPersonaje().getListaEsbirros().remove(esbirro);
            }
        }

    }

    public void validarOferta(Scanner sc) {
        if (listaOfertasNoValidadas.isEmpty()) {
            System.out.println("No hay ofertas para validar.");
        } else {
            int opcion;
            //Scanner sc = new Scanner(System.in);
            do {
                System.out.println("1) Validar ofertas");
                System.out.println("2) Salir");
                do {
                    opcion = sc.nextInt();
                    sc.nextLine();
                } while (opcion < 1 || opcion > 2);
                if (opcion != 2) {
                    int i = 0;
                    System.out.println("Elija una oferta para validar: ");
                    for (Oferta oferta : listaOfertasNoValidadas) {
                        System.out.println(i + ") ");
                        oferta.mostrarOferta();
                        i++;
                    }
                    i--;
                    int opcion2;
                    do {
                        opcion2 = sc.nextInt();
                        sc.nextLine();
                    } while (opcion2 < 0 || opcion2 > i);
                    System.out.println("¿Desea validar la oferta?");
                    System.out.println("0) Si");
                    System.out.println("1) No");
                    int opcion3;
                    do{
                        opcion3 = sc.nextInt();
                    } while(opcion3 != 0 && opcion3 != 1);
                    if (opcion3 == 0){
                        Oferta oferta = listaOfertasNoValidadas.remove(opcion2);
                        listaOfertas.add(oferta);
                        notificarOferta(oferta);
                        System.out.println("La oferta ha sido validada");
                    } else{
                        Oferta oferta = listaOfertasNoValidadas.remove(opcion2);
                        for (Equipo equipo: oferta.getListaEquipo()) {
                            if (equipo instanceof Arma){
                                ((Jugador) oferta.getUsuarioVendedor()).getPersonaje().getListaArmas().add((Arma) equipo);
                            } else{
                                ((Jugador) oferta.getUsuarioVendedor()).getPersonaje().getListaArmaduras().add((Armadura) equipo);
                            }
                        }
                        for (Esbirro esbirro: oferta.getListaEsbirros()) {
                            ((Jugador) oferta.getUsuarioVendedor()).getPersonaje().getListaEsbirros().add(esbirro);
                        }
                        System.out.println("Los articulos fueron devueltos al inventario del usuario vendedor");
                    }
                }
            } while (opcion != 2 && !listaOfertasNoValidadas.isEmpty());
            if (listaOfertasNoValidadas.isEmpty()) {
                System.out.println("No hay mas ofertas para validar.");
            }
        }
    }

    private Boolean encontrarNick(String nick) {
        if (whiteList != null && blackList != null) {
            int i = 0;
            boolean encontrado = false;
            while (i < whiteList.size() && !encontrado) {
                encontrado = whiteList.get(i).getNick().equals(nick);
                i = i + 1;
            }
            i = 0;
            while (i < blackList.size() && !encontrado) {
                encontrado = blackList.get(i).getNick().equals(nick);
                i = i + 1;
            }
            return encontrado;
        } else {
            return false;
        }
    }

    private Boolean encontrarBaneado(String nick) {
        if (!blackList.isEmpty()) {
            int i = 0;
            boolean encontrado = false;
            while (i < blackList.size() && !encontrado) {
                encontrado = blackList.get(i).getNick().equals(nick);
                i = i + 1;
            }
            return encontrado;
        } else {
            return false;
        }
    }

    public Boolean encontrarNumReg(String numReg) {
        if (whiteList != null) {
            int i = 0;
            boolean encontrado = false;
            while (i < whiteList.size() && !encontrado) {
                if (whiteList.get(i) instanceof Jugador) {
                    Jugador user = (Jugador) whiteList.get(i);
                    encontrado = user.getNumRegistro().equals(numReg);

                }
                i++;
            }
            i = 0;
            while (i < blackList.size() && !encontrado) {
                Jugador user2 = (Jugador) blackList.get(i);
                encontrado = user2.getNumRegistro().equals(numReg);
                i = i + 1;
            }
            return encontrado;
        } else {
            return false;
        }
    }

    public String calcularNumRegistro() {
        Random rd = new Random();
        String numero;
        do {
            numero = new String();
            char n = (char) (rd.nextInt(26) + 'a');
            String caracter = String.valueOf(n);
            numero = numero.concat(caracter);
            for (int i = 0; i < 2; i++) {
                n = (char) (rd.nextInt(9) + '0');
                caracter = String.valueOf(n);
                numero = numero.concat(caracter);
            }
            for (int i = 0; i < 2; i++) {
                n = (char) ((char) rd.nextInt(26) + 'a');
                caracter = String.valueOf(n);
                numero = numero.concat(caracter);
            }
        } while (encontrarNumReg(numero));
        return numero;
    }

    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
    }

    private void notificarOferta(Oferta oferta) {
        Jugador jugador = (Jugador) oferta.getUsuarioVendedor();

        for (Notificador notificador : listaNotificadores) {
            String filtro = notificador.getFiltro();
            if(filtro.startsWith("Min: ")) {
                String[] parts = filtro.split("\n");
                parts[0] = parts[0].replace("Min: ", "");
                parts[1] = parts[1].replace("Max: ", "");
                parts[0] = parts[0].replace(" oro \n", "");
                parts[1] = parts[1].replace(" oro", "");
                int min = Integer.parseInt(parts[0]);
                int max = Integer.parseInt(parts[1]);
            }
            boolean notificar = false;
            int max = -1,min = -1;
            if (!oferta.getListaEquipo().isEmpty()) {
                boolean hayArma = false, hayArmadura = false;
                for (Equipo equipo : oferta.getListaEquipo()) {
                    if (filtro.contains(equipo.getCategoria())) {
                        notificar = true;
                    }
                    if (equipo instanceof Arma) {
                        hayArma = true;
                    } else {
                        hayArmadura = true;
                    }
                }
                if (filtro.contains("Armas") && hayArma) {
                    notificar = true;
                } else if (filtro.contains("Armaduras") && hayArmadura) {
                    notificar = true;
                }
            } else if (!oferta.getListaEsbirros().isEmpty()) {
                if (filtro.contains("Esbirros")) {
                    notificar = true;
                }
                for (Esbirro esbirro : oferta.getListaEsbirros()) {
                    if (esbirro instanceof Humano) {
                        if (filtro.contains(((Humano) esbirro).getLealtad())) {
                            notificar = true;
                        }
                    } else if (filtro.contains(esbirro.getClass().getName())) {
                        notificar = true;
                    }
                }

            } else if (oferta.getPrecio() <= max && oferta.getPrecio() >= min) {
                notificar = true;
            } else if (filtro.contains(jugador.getPersonaje().getClass().getName())) {
                notificar = true;
            }
            if (notificar) {
                notificador.añadirOferta(oferta);
                notificador.notificar();
            }
        }
    }
}

