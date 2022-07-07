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
    private int opcionMI, opcionRol, opcionMP1, opcionMP2,opcionMOF,opcionMOP,opcionMU;
    private Personaje p;

    public Sistema() throws IOException, ClassNotFoundException {
        menuInicio();
    }

    private void consultarOferta(){
        Scanner sc = new Scanner(System.in);
        int i = 1;
        if (!listaOfertas.isEmpty()) {
            for (Oferta oferta : listaOfertas) {
                System.out.println("Oferta numero " + i + ")");
                oferta.mostrarOferta();
                i++;
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
                System.out.println("El numero ingresado no es valido");
            }
            sc.close();
        }else{
            System.out.println("No hay ofertas disponibles");
        }
    }

    private boolean comprarOferta(Oferta oferta) { //TODO condicion en la cual un Vampiro compra un Humano
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        if (cantidadOro < oferta.getPrecio()) {
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
                ((Jugador) usuario).getPersonaje().añadirEsbirro(esbirro);
            }
            oferta.generarVentaLog(usuario.getNombre());
            return true;
        } else {
            return false;
        }
    }

    public void menuInicio() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("   Bienvenido al Menu de Inicio   ");
        System.out.println("----------------------------------");
        System.out.println("       Seleccione una opción      ");
        System.out.println("1 - Registrarse                   ");
        System.out.println("2 - Iniciar sesion                ");
        System.out.println("----------------------------------");
        opcionMI = sc.nextInt();

        switch (opcionMI) {
            case 1:
                registrarCuenta();
                break;
            case 2:
                iniciarSesion();
                break;
        }
    }

    private void registrarCuenta() throws IOException {
            Scanner sc = new Scanner(System.in);
            System.out.println("Como quieres registrate:");
            System.out.println("1. Jugador");
            System.out.println("2. Operador");
            System.out.println("3. Volver al menú de inicio");
            int opcion = sc.nextInt();

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
                    while (contraseña.length() < 8 || contraseña.length() > 12){
                        System.out.println("La contraseña debe ser entre 8 y 12, vuelva a intentarlo");
                        contraseña = sc.next();
                    }
                    registrarPersonaje();
                    Personaje personaje = p;

                    String numeroRegistro = calcularNumRegistro();
                    Jugador player = new Jugador(nombre, nick, contraseña, personaje,numeroRegistro);
                    usuario = player;
                    break;
                case 2:
                    System.out.println("Introduzca el codigo secreto o introduzca 1 para cancelar");
                    int codigo = sc.nextInt();
                    while (codigo != 1 && codigo != 1234){
                        System.out.println("Codigo incorrecto, vuelva intentarlo o cancele la accion");
                        codigo = sc.nextInt();
                    }
                    switch (codigo) {
                        case 1:
                            registrarCuenta();
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
                    menuInicio();
                    break;
            }
            sc.close();
            crearUsuario();
            menuPrincipal();
    }

    public void crearUsuario() throws IOException {
        if(whiteList != null) {
            whiteList.add(usuario);
            serializarSistema();
        }else{
            whiteList = new ArrayList<>();
            whiteList.add(usuario);
            serializarSistema();
        }
    }

    private void menuPrincipal() throws IOException {
        if (usuario instanceof Jugador) {
            menuJugador();
        } else {
            menuOperador();
        }
    }

    private void menuOperador() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu principal " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Validar ofertas");
        System.out.println("2. Gestionar usuarios");
        System.out.println("3. Consultar las ventas realizadas");
        System.out.println("4. Darse de baja");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------------------");
        opcionMOP = sc.nextInt();
        switch (opcionMOP){
            case 1:
                validarOferta();
                break;
            case 2:
                menuUsuario();
                break;
            case 3:
                consultarVentas();
                break;
            case 4:
                darseDeBaja();
                break;
            case 5:
                salir();
                break;
        }

    }

    private void menuJugador() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu principal " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Gestion avanzada de personaje");
        System.out.println("2. Gestion avanzada de las ofertas");
        System.out.println("3. Darse de baja");
        System.out.println("4. Salir");
        System.out.println("-----------------------------------------------------");
        opcionMI = sc.nextInt();
        switch (opcionMI) {
            case 1:
                menuAvanzadoPersonaje();
                break;
            case 2:
                menuAvanzadoOfertas();
                break;
            case 3:
                darseDeBaja();
                break;
            case 4:
                salir();
                break;
        }
    }

    private void menuAvanzadoPersonaje() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu avanzado para personajes " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Modificar equipo");
        System.out.println("2. Modificar oro");
        System.out.println("3. Consultar información del personaje");
        System.out.println("4. Volver al menu principal");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------------------");
        opcionMP2 = sc.nextInt();
        switch (opcionMP2) {
            case 1:
                modificarEquipo();
                // Si esta añadir equipo y eliminar equipo que hacia modificar equipo
            case 2:
                modificarOro();
                break;
            case 3:
                consultarInformacionPersonaje();
                break;
            case 4:
                menuPrincipal();
                break;
            case 5:
                salir();
        }
    }

    private void menuAvanzadoOfertas(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al menu Avanzado de Ofertas" + usuario.getNick());
        System.out.println("¿Que operacion desea realizar?");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear una oferta");
        System.out.println("2. Buscar y comprar ofertas");
        System.out.println("3. Suscribirse a una oferta");
        System.out.println("4. Volver al menu avanzado de personaje");
        System.out.println("--------------------------------------");
        opcionMOF = sc.nextInt();
        switch (opcionMOF){
            case 1:
                crearOferta();
                break;
            case 2:
                consultarOferta();
                break;
            case 3:
                // Falta hacer sucribirse a una oferta
                break;
        }
    }

    public void darseDeBaja() throws IOException {
        whiteList.remove(usuario);
        System.out.println("Se ha dado de baja correctamente");
        menuInicio();
    }

    public void consultarInformacionPersonaje(){
        System.out.println("Cantidad de oro del Personaje:" + p.getCantidadOro());
        System.out.println();
        System.out.println("Armas del Personaje:");
        int i = 0;
        while(i < p.getListaArmas().size()){
            System.out.println(i + ". " + p.getListaArmas().get(i).getNombre());
            i += 1;
        }
        System.out.println("Armaduras del Personaje:");
        i = 0;
        while(i < p.getListaArmas().size()){
            System.out.println(i + ". " + p.getListaArmaduras().get(i).getNombre());
            i += 1;
        }
        System.out.println("Esbirros del Personaje:");
        i = 0;
        while(i < p.getListaArmas().size()){
            System.out.println(i + ". " + p.getListaEsbirros().get(i).getNombre());
            if (p.getListaEsbirros().get(i) instanceof Demonio){
                Demonio dem = (Demonio) p.getListaEsbirros().get(i);
                int j = 0;
                ArrayList<Esbirro> demEsb = dem.getConjuntoEsbirros();
                while (j < dem.getConjuntoEsbirros().size()){
                    System.out.println("Esbirros del Demonio:");
                    System.out.println(i + "." + j + " " + demEsb.get(j).getNombre());
                    j += 1;
                }
            }
            i += 1;
        }

    }

    public void salir() throws IOException {
        //Este método sale de la sesión ()
        menuInicio();
    }

    private Personaje crearPersonajeBase(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del personaje");
        String nombre = sc.next();
        System.out.println("Introduzca la cantidad de oro del personaje");
        int cantidadOro = sc.nextInt();
        HashSet<Arma> armasActivas = new HashSet<>();
        ArrayList<Esbirro> listaEsbirros = new ArrayList<>();
        p = new Personaje(nombre,conjuntoArmas,armasActivas,conjuntoArmaduras,listaEsbirros,cantidadOro) {
            @Override
            public void añadirEsbirro(Esbirro esbirro) {
                System.out.println("Elige el tipo de esbirro que quieres crear");
                System.out.println("1 - Humano");
                System.out.println("2 - Ghoul");
                System.out.println("3 - Demonio");
                int opcion = sc.nextInt();
                System.out.println("Introduce el nombre del esbirro");
                String nombreEsbirro = sc.next();
                System.out.println("Introduce la salud para el esbirro");
                int salud = sc.nextInt();
                switch(opcion){
                    case 1:
                        System.out.println("Introduce el tipo de lealtad (ALTA,NORMAL o BAJA)");
                        String lealtad = sc.next().toUpperCase();
                        while (!lealtad.equals("ALTA") && !lealtad.equals("NORMAL") && !lealtad.equals("BAJA")) {
                             System.out.println("El tipo de lealtad tiene que ser ALTO,NORMAL o BAJA");
                             lealtad = sc.next().toUpperCase();
                        }
                        Humano h = new Humano(nombreEsbirro, salud, lealtad);
                        p.getListaEsbirros().add(h);
                        break;
                    case 2:
                        boolean error = false;
                        System.out.println("Introduce la dependencia");
                        int dependencia = 0;
                        do {
                            try {
                                dependencia = sc.nextInt();
                            } catch(NumberFormatException e){
                                System.out.println("El valor debe ser numerico");
                                error = true;
                            }
                            while (dependencia < 1 || dependencia > 5) {
                                System.out.println("La dependencia debe ser un numero entre 1 y 5");
                                dependencia = sc.nextInt();
                            }
                        } while (error);
                        Ghoul g = new Ghoul(nombreEsbirro,salud,dependencia);
                        p.getListaEsbirros().add(g);
                        break;
                    case 3:
                        int cantEsbirros = (int) (Math.random() * 3);
                        String descripcion = sc.next();
                        Demonio demonio = new Demonio(nombreEsbirro,salud,descripcion);
                        p.getListaEsbirros().add(demonio);
                        //Falta que los demonios puedan crear demonios
                        break;
                }
            }
        };
        return p;
    }

    private void suscribirseOferta() {
        String filtro;
        Scanner sc = new Scanner(System.in);
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
                        filtro.concat("-Armas \n");
                        break;
                    case 2:
                        filtro.concat("-Armaduras \n");
                        break;
                    case 3:
                        filtro.concat("-Esbirros \n");
                        break;
                    case 4:
                        filtro.concat("-Armas \n-Armaduras \n");
                        break;
                    case 5:
                        filtro.concat("-Armas \n-Esbirros \n");
                        break;
                    case 6:
                        filtro.concat("-Armaduras \n-Esbirros \n");
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
                        filtro.concat("-Comun \n");
                        break;
                    case 2:
                        filtro.concat("-Raro \n");
                        break;
                    case 3:
                        filtro.concat("-Epico \n");
                        break;
                    case 4:
                        filtro.concat("-Legendario \n");
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
                                filtro.concat("-Modificador de Ataque del arma de 1");
                                break;
                            case 2:
                                filtro.concat("-Modificador de Ataque del arma de 2");
                                break;
                            case 3:
                                filtro.concat("-Modificador de Ataque del arma de 3");
                                break;
                            case 4:
                                filtro.concat("-Modificador de Defensa del arma de 1");
                                break;
                            case 5:
                                filtro.concat("-Modificador de Defensa del arma de 2");
                                break;
                            case 6:
                                filtro.concat("-Modificador de Defensa del arma de 3");
                                break;
                        }
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
                                filtro.concat("-Modificador de Ataque de la armadura de 1");
                                break;
                            case 2:
                                filtro.concat("-Modificador de Ataque de la armadura del arma de 2");
                                break;
                            case 3:
                                filtro.concat("-Modificador de Ataque de la armadura del arma de 3");
                                break;
                            case 4:
                                filtro.concat("-Modificador de Defensa de la armadura del arma de 1");
                                break;
                            case 5:
                                filtro.concat("-Modificador de Defensa de la armadura del arma de 2");
                                break;
                            case 6:
                                filtro.concat("-Modificador de Defensa de la armadura del arma de 3");
                                break;
                        }
                        break;
                case 4:
                    filtro = "Suscripcion por lealtad de Esbirro: \n";
                    System.out.println("Elige una opcion para suscribirse");
                    System.out.println("1 - ALTA");
                    System.out.println("2 - MEDIA");
                    System.out.println("3 - BAJA");
                    System.out.println("0 - Salir");
                    do {
                        opcion = sc.nextInt();
                    } while(opcion<0 ||opcion>3);
                    switch(opcion) {
                        case 1:
                            filtro.concat("-ALTA");
                            break;
                        case 2:
                            filtro.concat("-MEDIA");
                            break;
                        case 3:
                            filtro.concat("-BAJA");
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
                            filtro.concat("-Demonio");
                        case 2:
                            filtro.concat("-Ghoul");
                            break;
                        case 3:
                            filtro.concat("-Humano");
                            break;
                    }

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
                                filtro.concat("-Licantropo");
                                break;
                            case 2:
                                filtro.concat("-Vampiro");
                                break;
                            case 3:
                                filtro.concat("-Cazador");
                                break;
                        }
                    case 7:
                        filtro = "Suscripcion por un precio minimo-maximo: \n";
                        System.out.println("Elige el precio minimo [0-1000]: ");
                        do {
                            opcion = sc.nextInt();
                        } while(opcion<0 || opcion>1000);
                        filtro.concat("Min: " + opcion + " oro \n");
                        System.out.println("Elige el precio maximo [0-1000]: ");
                        do {
                            opcion = sc.nextInt();
                        } while(opcion<0 || opcion>999);
                        filtro.concat("Max: " + opcion + " oro");
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
                    notificador.suscribirse((Observado) usuario);
                    listaNotificadores.add(notificador);
                    ((Jugador) usuario).addNotificador(notificador);

                } else {
                    listaNotificadores.get(indice).suscribirse((Observado) usuario);
                    ((Jugador) usuario).addNotificador(listaNotificadores.get(indice));
                }
        }
        sc.close();
    }
    public void desuscribirse(){
        if (((Jugador) usuario).getListaNotificadores().isEmpty()){
            System.out.println("No tienes ninguna suscripción!");
        } else{
            Scanner sc = new Scanner(System.in);
            int opcion;
            System.out.println("Elija un filtro del que te quieres desuscribir:");
            int i = 0;
            for (Notificador notificador: ((Jugador) usuario).getListaNotificadores()) {
                System.out.println(i + ") " + notificador.getFiltro());
                i++;
            }
            do{
                opcion = sc.nextInt();
            } while(opcion > 0 || opcion > i--);
            ((Jugador) usuario).getListaNotificadores().get(opcion).desuscribirse((Observado) usuario);
            ((Jugador) usuario).getListaNotificadores().remove(opcion);
        }

    }

    public Personaje registrarPersonaje() {
        Scanner sc = new Scanner(System.in);
        p = crearPersonajeBase();
        System.out.println("Elige un rol");
        System.out.println("1. Cazador");
        System.out.println("2. Vampiro");
        System.out.println("3. Licantropo");
        opcionRol = sc.nextInt();
        switch (opcionRol) {
            case 1:
                CrearCazador cazador = new CrearCazador();
                p = cazador.crearPersonaje(p.getNombre(),p.getListaArmas(),p.getArmasActivas(),p.getListaArmaduras(),p.getListaEsbirros(),p.getCantidadOro());
                break;
            case 2:
                CrearVampiro vampiro = new CrearVampiro();
                p = vampiro.crearPersonaje(p.getNombre(),p.getListaArmas(),p.getArmasActivas(),p.getListaArmaduras(),p.getListaEsbirros(),p.getCantidadOro());
                break;
            case 3:
                CrearLicantropo licantropo = new CrearLicantropo();
                p = licantropo.crearPersonaje(p.getNombre(),p.getListaArmas(),p.getArmasActivas(),p.getListaArmaduras(),p.getListaEsbirros(),p.getCantidadOro());
                break;
        }
        sc.close();
        return p;
    }


    public void iniciarSesion() throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Nombre de usuario");
            String nick = sc.nextLine();
            System.out.println("Contraseña");
            String contraseña = sc.nextLine();

            if (comprobarSesion(nick, contraseña) && !encontrarBaneado(nick)) {
                menuPrincipal();
            } else if (!comprobarSesion(nick, contraseña)){
                System.out.println("Inicio de sesión erroneo vuelva a intentarlo");
                System.out.println();
                menuInicio();
            }else {
                System.out.println("Su usuario esta baneado");
                System.out.println();
                menuInicio();
            }
        }
    }

    public void modificarEquipo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione una opcion");
        System.out.println("1. Añadir Equipo");
        System.out.println("2. Eliminar Equipo");
        System.out.println("3. Elegir Armas Activas");
        System.out.println("0. Salir");
        int opcion;
        do{
            opcion = sc.nextInt();
        } while(opcion < 0 || opcion >1);
        switch (opcion) {
            case 1:
                añadirEquipo();
            case 2:
                eliminarEquipo();
            case 3:
                elegirArmasActivas();
        }
    }

    private void añadirEquipo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        System.out.println("Seleccione el equipo que desea añadir");
        System.out.println("Armaduras:");
        int i = 1;
        for(Armadura armadura: conjuntoArmaduras){

            if(!((Jugador) usuario).getPersonaje().getListaArmaduras().contains(armadura)) {
                System.out.println("Numero" + i);
                armadura.mostrarEquipo();
                listaEquipo.add(armadura);
                i++;
            }
        }
        System.out.println("Armas:");
        for(Arma arma: conjuntoArmas){

            if(!((Jugador) usuario).getPersonaje().getListaArmas().contains(arma)) {
                System.out.println("Numero" + i);
                arma.mostrarEquipo();
                listaEquipo.add(arma);
                i++;
            }
        }
        int opcion = sc.nextInt();
        if (opcion < listaEquipo.size() && 0 < opcion) {
            Equipo e = listaEquipo.get(opcion);
            if (e instanceof Arma) {
                if (((Jugador) usuario).getPersonaje().getListaArmas().size() < 4) {
                    ((Jugador) usuario).getPersonaje().addListaArmas((Arma) e);
                } else {
                    System.out.println("No puedes añadir mas de 3 armas a tu personaje");
                }
            } else{
                if (((Jugador) usuario).getPersonaje().getListaArmaduras().size() < 4) {
                    ((Jugador) usuario).getPersonaje().addListaArmaduras((Armadura) e );
                } else{
                    System.out.println("No puedes añadir mas de 3 armaduras a tu personaje");
                }
            }
            }
    }

    private void eliminarEquipo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipo = new ArrayList<>();
        int i;
        if (((Jugador) usuario).getPersonaje().getListaArmas().isEmpty() && ((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()){
            System.out.println("No tienes equipo para eliminar");
        } else {
            if (!((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()){
                System.out.println("Armaduras:");
                i = 1;
                for (Armadura armadura : ((Jugador) usuario).getPersonaje().getListaArmaduras()) {
                    System.out.println("Numero" + i);
                    armadura.mostrarEquipo();
                    listaEquipo.add(armadura);
                    i++;
                }
            }
            if (!((Jugador) usuario).getPersonaje().getListaArmas().isEmpty()){
                System.out.println("Armas:");
                i = 1;
                for (Arma arma : ((Jugador) usuario).getPersonaje().getListaArmas()) {
                    System.out.println("Numero" + i);
                    arma.mostrarEquipo();
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

    private void elegirArmasActivas() {
        Scanner sc = new Scanner(System.in);
        for (Arma armaActiva:(((Jugador) usuario).getPersonaje().getArmasActivas())){
            ((Jugador) usuario).getPersonaje().removeArmasActivas(armaActiva);
        }
        System.out.println("Elija una o dos armas: ");
        int i = 1;
        int opcion;
        int contador = 0;
        for (int j=0; j <=1; j++){
            if (j == 1){
                System.out.println("0) Salir");
            }
            for(Arma arma: ((Jugador) usuario).getPersonaje().getListaArmas()){
                if(!((Jugador) usuario).getPersonaje().getArmasActivas().contains(arma)) {
                    System.out.println(i + " )");
                    arma.mostrarEquipo();
                }
                i++;
            }
            i--;
            if (contador == 0){
                do{
                    opcion = sc.nextInt();
                } while(opcion < 1 || opcion > i);
            } else{
                do{
                    opcion = sc.nextInt();
                } while(opcion < 0 || opcion > i);
            }
            if(opcion != 0){
                ((Jugador) usuario).getPersonaje().addArmasActivas(conjuntoArmas.get(i-1));
            }
            contador++;
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

    public void serializarSistema() throws FileNotFoundException, IOException {
        String rutaArchivo = "./informacion.bin";
        File f1 = new File(rutaArchivo);
        ObjectOutputStream datosSalida = new ObjectOutputStream(new FileOutputStream(f1));
        datosSalida.writeObject(this);
    }

    //método encargado de obtener la información introducida anteriormente en el sistema
    public Sistema deserializarSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
        String rutaArchivo = "./informacion.bin";
        ObjectInputStream datosEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
        Sistema datos = (Sistema) datosEntrada.readObject();
        return datos;
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
        if(whiteList != null) {
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
        }else{
            return false;
        }
    }

    public void modificarOro() {
        int cantidadOro = ((Jugador) usuario).getPersonaje().getCantidadOro();
        System.out.println("1) Sumar oro");
        System.out.println("2) Restar oro");
        int opcion;
        Scanner sc = new Scanner(System.in);
        do{
            opcion = sc.nextInt();
        }while (opcion < 1 || opcion > 2);
        if (opcion == 1) {
            System.out.println("Indica cuánto oro quieres sumarte entre 0 y 1000");
        } else{
            System.out.println("Indica cuánto oro quieres restarte entre 0 y 1000");
        }
        int oroASumar = -1;
        while (oroASumar < 0 || oroASumar > 1000) {
            oroASumar = sc.nextInt();
            if (oroASumar < 0 || oroASumar > 1000) {
                System.out.println("Vuélvelo a intentar, introduzca un número entre 0 y 1000");
            }
        }
        if (opcion == 1){
            cantidadOro += oroASumar;
        } else{
            cantidadOro -= oroASumar;
            if (cantidadOro < 0){
                cantidadOro = 0;
            }
        }
        sc.close();
        ((Jugador) usuario).getPersonaje().setCantidadOro(cantidadOro);
        System.out.println("El oro se ha modificado correctamente");
        System.out.println("Nuevo saldo: "+ ((Jugador) usuario).getPersonaje().getCantidadOro());
    }

    private void menuUsuario() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Bienvenido al menu de gestion de usuarios " + usuario.getNick());
        System.out.println("Elige una de las siguientes opciones");
        System.out.println("1. Banear usuario");
        System.out.println("2. Desbanear usuario");
        System.out.println("3. Volver al menu del operador");
        System.out.println("4. Salir");
        System.out.println("-----------------------------------------------------");
        opcionMU = sc.nextInt();
        switch (opcionMU){
            case 1:
                banearUsuario();
                break;
            case 2:
                desbanearUsuario();
                break;
            case 3:
                menuOperador();
                break;
            case 4:
                salir();
                break;
        }

    }

    private void banearUsuario() {
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
        } else {
            System.out.println("No hay jugadores para banear");
        }
    }

    private void desbanearUsuario() {
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

    public void crearOferta() {
        Scanner sc = new Scanner(System.in);
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
                }while (opcion > 5 || opcion < 1);
            } else {
                while (opcion > 4 || opcion < 1) {
                    opcion = sc.nextInt();
                }
            }
            i = 0;
            switch (opcion) {
                case 1:
                    if (((Jugador) usuario).getPersonaje().getListaArmas().isEmpty()){
                        System.out.println("No se ha encontrado nada!");
                    } else{
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
                    if (((Jugador) usuario).getPersonaje().getListaArmaduras().isEmpty()){
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
                    if (((Jugador) usuario).getPersonaje().getListaEsbirros().isEmpty()){
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
                        listaEquipo.add(((Jugador) usuario).getPersonaje().getListaArmaduras().get(opcion2));
                        contador += 1;
                    }
                    break;
            }
        }

        if (opcion != 4){
            System.out.println("Introduzca un precio valido: ");
            int precio;
            do {
                precio = sc.nextInt();
            } while (precio < 0);
            Oferta oferta = new Oferta(listaEquipo, listaEsbirros, precio, usuario);
            listaOfertasNoValidadas.add(oferta);

            //Quitar armas del inventario del usuario
            for (Equipo equipo: listaEquipo) {
                if (equipo instanceof Arma){
                    ((Jugador) usuario).getPersonaje().getListaArmas().remove((Arma) equipo);
                } else{
                    ((Jugador) usuario).getPersonaje().getListaArmaduras().remove((Armadura) equipo);
                }
            }
            for (Esbirro esbirro: listaEsbirros) {
                ((Jugador) usuario).getPersonaje().getListaEsbirros().remove(esbirro);
            }
        }
        sc.close();
    }

    public void validarOferta() {
        if (listaOfertasNoValidadas.isEmpty()) {
            System.out.println("No hay ofertas para validar.");
        } else {
            int opcion;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("1) Validar ofertas");
                System.out.println("2) Salir");
                do {
                    opcion = sc.nextInt();
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
                    } while (opcion2 < 0 || opcion2 > i);
                    Oferta oferta = listaOfertasNoValidadas.remove(opcion2);
                    listaOfertas.add(oferta);
                }
            } while (opcion != 2 && !listaOfertasNoValidadas.isEmpty());
            if (listaOfertasNoValidadas.isEmpty()){
                System.out.println("No hay ofertas para validar.");
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
        }else {
            return false;
        }
    }

    private Boolean encontrarBaneado(String nick){
        if (!blackList.isEmpty()) {
            int i = 0;
            boolean encontrado = false;
            while (i < blackList.size() && !encontrado) {
                encontrado = blackList.get(i).getNick().equals(nick);
                i = i + 1;
            }
            return encontrado;
        }else{
            return false;
        }
    }

    public Boolean encontrarNumReg(String numReg){
        if (whiteList != null) {
            int i = 0;
            boolean encontrado = false;
            while (i < whiteList.size() && !encontrado) {
                if(whiteList.get(i) instanceof Jugador) {
                    Jugador user = (Jugador) whiteList.get(i);
                    System.out.println(user.getNumRegistro());
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
        }else {
            return false;
        }
    }

    public String calcularNumRegistro(){
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
        }while(encontrarNumReg(numero));
        return numero;
    }

}

