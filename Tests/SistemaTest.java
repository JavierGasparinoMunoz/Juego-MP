import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class modificarOroTest {

    @Test
    void modificarOroTest() throws IOException, ClassNotFoundException {
        // Resta un valor mayor que 500, (700) y le suma 100 oro. Se espera que finalmente el jugador tenga 100 monedas de oro.
        int cantidadOroInicial = 500;
        int oroRestado = 700;
        int oroSumado = 100;
        String data = "1" +
                "\n1" + //registrar jugador
                "\nTester"+ //nombre usuario
                "\nTester1"+ //nick
                "\n123412344"+ //contraseña
                "\nPersonaje"+ //nombre personaje
                "\n" + cantidadOroInicial+ //cantidad oro
                "\n1"+  //Rol de Personaje
                "\n1"+ //puntos de voluntad
                "\n1"+ //tipo de esbirro
                "\nHumano"+ //nombre de esbirro
                "\n1"+ //salud de esbirro
                "\nALTA"+ //lealtad (en este caso)
                "\n12"+ //numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa
                "\n0"+ //No quieres mas armas activas y entras al menú
                "\n1"+ //Gestion avanzada del personaje
                "\n2"+ //modificarOro()
                "\n2"+ //restar oro
                "\n" + oroRestado    + //Ahora debería tener 0 oro.
                "\n1"+ //Gestion avanzada del personaje
                "\n2"+ //modificarOro()
                "\n1"+ //sumar oro
                "\n-1" + //Un número fuera del rango [0,1000] no debe aceptarse.
                "\n" + oroSumado +
                "\n5" +//Salir
                "\n3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(0 + oroSumado, ((Jugador)sistema.getUsuario()).getPersonaje().getCantidadOro(), "Hay un error en el codigo");
    }
}

class MenuAvanzadoOfertasTest{
    @Test
    void particionEquivalenteMenuOfertasTest() throws IOException {
        int numEquipo = 15; //Es un arma
        String data = "1" +
                "\n1" + //registrar jugador
                "\nTester" + //nombre usuario
                "\nTester1" + //nick
                "\n123412344" + //contraseña
                "\nPersonaje" + //nombre personaje
                "\n500" + //cantidad oro
                "\n1" +  //Rol de Personaje
                "\n1" + //puntos de voluntad
                "\n1" + //tipo de esbirro
                "\nHumano" + //nombre de esbirro
                "\n1" + //salud de esbirro
                "\nALTA" + //lealtad (en este caso)
                "\n" + numEquipo +//numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                "\n2" + //Gestion avanzada de las ofertas (valor esperado numero de 1 a 5)
                "\na" + //entrada invalida (letra/string)
                "\n0" + //entrada invalida (numero por debajo del rango de valores valido)
                "\n7" + //entrada invalida (numero por encima del rango de valores valido)
                "\n1" + //entrada valida (numero en el intervalo 1-5)
                "\n4" +//Cancelar
                "\n5" +//Salir
                "\n3"; //Terminar ejecucion

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
    }
}

class crearPersonajeTest {
    @Test
    void crearCazadorConGhoul() throws IOException { // hay que borrar los daatos para ejecutar este test
        int numEquipo = 15; //Es un arma
        String data =
                "1" + //registrarse
                        "\n1" + //registrar jugador
                        "\nTester" + //nombre usuario
                        "\nTester2" + //nick
                        "\n123412344" + //contraseña
                        "\nPersonaje" + //nombre personaje
                        "\n500" + //cantidad oro
                        "\n1" +  //Rol de Personaje
                        "\n1" + //puntos de sangre
                        "\n2" + //tipo de esbirro
                        "\nGhoul" + //nombre de esbirro
                        "\n1" + //salud de esbirro
                        "\n1" + //dependencia
                        "\n" + numEquipo +//numero de arma a elegir
                        "\n0" + //no quiero más armas
                        "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                        "\n5" +  //Log out
                        "\n3"; //Terminar ejecucion
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertInstanceOf(Cazador.class, ((Jugador)sistema.getUsuario()).getPersonaje());
        assertInstanceOf(Ghoul.class, ((Jugador)sistema.getUsuario()).getPersonaje().getListaEsbirros().get(0));
    }

    @Test
    void crearLicantropoConHumano() throws IOException { // hay que borrar los daatos para ejecutar este test
        int numEquipo = 15; //Es un arma
        String data =
                "1" + //registrarse
                        "\n1" + //registrar jugador
                        "\nTester" + //nombre usuario
                        "\nTester1" + //nick
                        "\n123412344" + //contraseña
                        "\nPersonaje" + //nombre personaje
                        "\n500" + //cantidad oro
                        "\n3" +  //Rol de Personaje
                        "\n1" + //puntos de voluntad
                        "\n1" + //tipo de esbirro
                        "\nHumano" + //nombre de esbirro
                        "\n1" + //salud de esbirro
                        "\nALTA" + //lealtad (en este caso)
                        "\n" + numEquipo +//numero de arma a elegir
                        "\n0" + //no quiero más armas
                        "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                        "\n5" + //Log out
                        "\n3"; //Terminar ejecucion
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertInstanceOf(Licantropo.class, ((Jugador)sistema.getUsuario()).getPersonaje());
        assertInstanceOf(Humano.class, ((Jugador)sistema.getUsuario()).getPersonaje().getListaEsbirros().get(0));
    }

    @Test
    void crearVampiroConDemonio() throws IOException { // hay que borrar los daatos para ejecutar este test
        int numEquipo = 15; //Es un arma
        String data =
                "\n1" +
                        "\n1" + //registrar jugador
                        "\nTester" + //nombre usuario
                        "\nTester1" + //nick
                        "\n123412344" + //contraseña
                        "\nPersonaje" + //nombre personaje
                        "\n500" + //cantidad oro
                        "\n2" +  //Rol de Personaje
                        "\n1" + //puntos de sangre
                        "\n19" + //edad
                        "\n3" + //tipo de esbirro
                        "\nDemonio" + //nombre de esbirro
                        "\n1" + //salud de esbirro
                        "\nALTA" + //pacto
                        "\n" + numEquipo +//numero de arma a elegir
                        "\n0" + //no quiero más armas
                        "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                        "\n5" + //Log out
                        "\n3"; //Terminar ejecucion
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertInstanceOf(Vampiro.class, ((Jugador)sistema.getUsuario()).getPersonaje());
        assertInstanceOf(Demonio.class, ((Jugador)sistema.getUsuario()).getPersonaje().getListaEsbirros().get(0));

    }
}

class crearNotificacionTest{
    @Test
    void crearNotificacionTest() throws IOException {
        int numEquipo = 18; //Es un arma
        String data = "1" +
                "\n1" + //registrar jugador
                "\nTester1" + //nombre usuario
                "\nPrimerTester" + //nick
                "\n123412344" + //contraseña
                "\nPersonaje" + //nombre personaje
                "\n500" + //cantidad oro
                "\n1" +  //Rol de Personaje
                "\n1" + //puntos de voluntad
                "\n1" + //tipo de esbirro
                "\nHumano" + //nombre de esbirro
                "\n1" + //salud de esbirro
                "\nALTA" + //lealtad (en este caso)
                "\n" + numEquipo +//numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                "\n2" + //Gestion avanzada de las ofertas
                "\n1" + //crear una oferta
                "\n1" + //Armas()
                "\n0" + //añadir arma 1 (Debe eliminar esta arma de armas activas también)
                "\n5" + //Finalizar Oferta
                "\n33" + //Introduzca un precio valido
                "\n5" +//Salir
                "\n1" + //registrarse
                "\n1" + //registrar jugador
                "\nTester2" + //nombre usuario
                "\nSegundoTester" + //nick
                "\n123412344" + //contraseña
                "\nPersonaje" + //nombre personaje
                "\n500" + //cantidad oro
                "\n1" +  //Rol de Personaje
                "\n1" + //puntos de voluntad
                "\n1" + //tipo de esbirro
                "\nHumano" + //nombre de esbirro
                "\n1" + //salud de esbirro
                "\nALTA" + //lealtad (en este caso)
                "\n" + numEquipo +//numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                "\n2" + //Gestion avanzada de las ofertas
                "\n3" + //Suscribirse a una oferta
                "\n2" + //Por categoria
                "\n2" + //Raro
                "\n5" +//Salir
                "\n1" + //registrarse
                "\n2" + //registrar operador
                "\n1234" + //codigo de operador
                "\nOperador" + //nick
                "\nOperador1" + //nick
                "\n123412344" + //contraseña
                "\n1" + //validar ofertas
                "\n1" + //validar ofertas
                "\n0" + //seleccionar primera oferta
                "\n0" + //validar primera oferta
                "\n5" +//Salir
                "\n2" +//Iniciar sesion
                "\nSegundoTester" + //nick
                "\n123412344" + //contraseña
                "\n5" + //Salir
                "\n3";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertNotEquals(((Jugador)sistema.getUsuario()).getListaNotificaciones().size(), 0);
    }
}



class crearOfertaTest {
    @Test
    void crearOfertaTest() throws IOException { // hay que borrar los daatos para ejecutar este test
        int numEquipo = 15; //Es un arma
        String data = "1" +
                "\n1" + //registrar jugador
                "\nTester" + //nombre usuario
                "\nTester1" + //nick
                "\n123412344" + //contraseña
                "\nPersonaje" + //nombre personaje
                "\n500" + //cantidad oro
                "\n1" +  //Rol de Personaje
                "\n1" + //puntos de voluntad
                "\n1" + //tipo de esbirro
                "\nHumano" + //nombre de esbirro
                "\n1" + //salud de esbirro
                "\nALTA" + //lealtad (en este caso)
                "\n" + numEquipo +//numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                "\n2" + //Gestion avanzada de las ofertas
                "\n1" + //crear una oferta
                "\n1" + //Armas()
                "\n0" + //añadir arma 1 (Debe eliminar esta arma de armas activas también)
                "\n5" + //Finalizar Oferta
                "\n33" + //Introduzca un precio valido
                "\n5" +//Salir
                "\n3";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(sistema.getListaOfertasNoValidadas().size(), 1);
        ArrayList<Equipo> listaArmas = new ArrayList<>();
        ArrayList<String> materiales5 = new ArrayList<>(Arrays.asList("Plata", "Acero"));
        Arma bfs = new Arma(2, 1, 1, "B.F.Sword", "Epico", materiales5);
        listaArmas.add(bfs);
        Oferta oferta = new Oferta(listaArmas, new ArrayList<>(), 33, null);
        String string1 = oferta.getListaEquipo().get(0).getNombre();
        String string2 = sistema.getListaOfertasNoValidadas().get(0).getListaEquipo().get(0).getNombre();
        assertEquals(string1, string2);
        assertEquals(oferta.getListaEquipo().get(0).getCategoria(), sistema.getListaOfertasNoValidadas().get(0).getListaEquipo().get(0).getCategoria());
        assertEquals(oferta.getListaEsbirros(), sistema.getListaOfertasNoValidadas().get(0).getListaEsbirros());
        assertEquals(oferta.getPrecio(), sistema.getListaOfertasNoValidadas().get(0).getPrecio());


    }
}
class modificarEquipoTest {
    @Test
    void modificarEquipoTest() throws IOException, ClassNotFoundException {
        int numEquipo1 = 15; //Es un arma
        int numEquipo2 = 14;
        String data = "1" +
                "\n1" + //registrar jugador
                "\nTester" + //nombre usuario
                "\nTester1" + //nick
                "\n123412344" + //contraseña
                "\nPersonaje" + //nombre personaje
                "\n500" + //cantidad oro
                "\n1" +  //Rol de Personaje
                "\n1" + //puntos de voluntad
                "\n1" + //tipo de esbirro
                "\nHumano" + //nombre de esbirro
                "\n1" + //salud de esbirro
                "\nALTA" + //lealtad (en este caso)
                "\n" + numEquipo1 +//numero de arma a elegir
                "\n0" + //no quiero más armas
                "\n1" + //Seleccionas arma activa y como no tengo más me envía al menú
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n2" + //eliminarEquipo()
                "\n1" + //eliminar arma 1 (Debe eliminar esta arma de armas activas también)
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n3" + //elegirArmasActivas() (Debe mandarme de nuevo al menú porque no tengo armas en el inventario)
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n1" +  //añadirEquipo()
                "\n" + numEquipo1 + //Equipar la misma arma del principio
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n1" +  //añadirEquipo()
                "\n" + numEquipo2 + //Equipar otra arma
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n3" + //elegirArmasActivas()
                "\n1" + //mi primer arma activa
                "\n1" + //selecciono que quiero añadir una segunda arma activa
                "\n2" + ///mi segunda arma activa
                "\n1" + //intentar añadir una tercera arma activa (No te permite)
                "\n5" +//salir
                "\n3"; //terminar ejecución del programa
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        Arma arma1 = sistema.getConjuntoArmas().get(numEquipo1-5); //10, arma1 = B.F. sword
        Arma arma2 = sistema.getConjuntoArmas().get(numEquipo2-5);//9, arma2 = Bumerán
        boolean solucion = false;
        if (((Jugador) sistema.getUsuario()).getPersonaje().getArmasActivas().contains(arma1) && ((Jugador) sistema.getUsuario()).getPersonaje().getArmasActivas().contains(arma2)){
            solucion = true;
        }
        assert(solucion);
    }
}

class validarOfertaTest {
    @Test
    public void validarOfertasTest() throws IOException, ClassNotFoundException {

    String data =
                    "\n" +
                    "1" + //entrar al menu para registrarse
                    "\n" +
                    "1" + //elegir registrarse como jugador
                    "\nTester"+ // nombre
                    "\nTester1"+ //nick
                    "\n123412344"+ //contraseña
                    "\nPersonaje"+ // nombre del personaje
                    "\n200" + //cantidad oro
                    "\n1"+  //Rol de Personaje(cazador)
                    "\n1"+ //puntos de voluntad
                    "\n1"+ // elegir el tipo de esbirro
                    "\nHumano"+ //nombre del esbirro
                    "\n1"+ //cantidad de salud del esbirro
                    "\nALTA"+ // elegir la lealtad
                    "\n12"+ // elegir el arma/armadura para el personaje
                    "\n0" + // salir de la eleccion de arma/armadura
                    "\n1" + //Seleccionas arma activa
                    "\n0"+ //entras al menú
                    "\n2"+ //seleccion del menu avanzado de ofertas
                    "\n1"+ // seleccion de crear oferta
                    "\n1" + // seleccion del tipo de equipo a ofertar(arma)
                    "\n0" + // seleccion del arma que se quiere ofertar
                    "\n100"+ //precio de la oferta
                    "\n5" + // vuelta al menu avanzado de oferta
                    "\n5" + // vuelta al menu principal
                    "\n5" + // vuelta al menu inicio
                    "\n1" +//registrar
                    "\n2" +// op
                    "\n1234" +//codigo secreto
                    "\nTester" +//nombre
                    "\nTester1OP" +//nick
                    "\n12341234" +//contraseña
                    "\n1" + //menu validar oferta
                    "\n1"+ //validar oferta
                    "\n0" + //eleccion de la oferta
                    "\n0" + //aceptar validacion
                    "\n5" +//salir al menu inicio
                    "\n3";//acabar programa
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(1, sistema.getListaOfertas().size(), "Hay un error en el codigo");

        String data2 =
                "\n" +
                        "1" + //entrar al menu para registrarse
                        "\n" +
                        "1" + //elegir registrarse como jugador
                        "\nTester"+ // nombre
                        "\nTester1"+ //nick
                        "\n123412344"+ //contraseña
                        "\nPersonaje"+ // nombre del personaje
                        "\n200" + //cantidad oro
                        "\n1"+  //Rol de Personaje(cazador)
                        "\n1"+ //puntos de voluntad
                        "\n1"+ // elegir el tipo de esbirro
                        "\nHumano"+ //nombre del esbirro
                        "\n1"+ //cantidad de salud del esbirro
                        "\nALTA"+ // elegir la lealtad
                        "\n12"+ // elegir el arma/armadura para el personaje
                        "\n0" + // salir de la eleccion de arma/armadura
                        "\n1" + //Seleccionas arma activa
                        "\n0"+ //entras al menú
                        "\n2"+ //seleccion del menu avanzado de ofertas
                        "\n1"+ // seleccion de crear oferta
                        "\n1" + // seleccion del tipo de equipo a ofertar(arma)
                        "\n0" + // seleccion del arma que se quiere ofertar
                        "\n100"+ //precio de la oferta
                        "\n5" + // vuelta al menu avanzado de oferta
                        "\n5" + // vuelta al menu principal
                        "\n5" + // vuelta al menu inicio
                        "\n1" +//registrar
                        "\n2" +// op
                        "\n1234" +//codigo secreto
                        "\nTester" +//nombre
                        "\nTester1OP" +//nick
                        "\n12341234" +//contraseña
                        "\n1" + //menu validar oferta
                        "\n1"+ //validar oferta
                        "\n0" + //eleccion de la oferta
                        "\n1" + //negacion de la validacion
                        "\n5" +//salir al menu inicio
                        "\n3";//acabar programa
        System.setIn(new ByteArrayInputStream(data2.getBytes()));
        Sistema sistema2 = new Sistema();
        assertEquals(0, sistema2.getListaOfertas().size(), "Hay un error en el codigo");
        assertEquals(0, sistema2.getListaOfertasNoValidadas().size(), "Hay un error en el codigo");
    }
}

    class banearUsuarioTest {
        @Test
        public void banearUsuarioTest() throws IOException, ClassNotFoundException {

            String data =
                            "\n" +
                            "1" + //entrar al menu para registrarse
                            "\n" +
                            "1" + //elegir registrarse como jugador
                            "\nTester"+ // nombre
                            "\nTester1"+ //nick
                            "\n123412344"+ //contraseña
                            "\nPersonaje"+ // nombre del personaje
                            "\n200" + //cantidad oro
                            "\n1"+  //Rol de Personaje(cazador)
                            "\n1"+ //puntos de voluntad
                            "\n1"+ // elegir el tipo de esbirro
                            "\nHumano"+ //nombre del esbirro
                            "\n1"+ //cantidad de salud del esbirro
                            "\nALTA"+ // elegir la lealtad
                            "\n12"+ // elegir el arma/armadura para el personaje
                            "\n0" + // salir de la eleccion de arma/armadura
                            "\n1" + //Seleccionas arma activa
                            "\n0"+ //entras al menú
                            "\n5" +
                            "\n1" +//registrar
                            "\n2" +// op
                            "\n1234" +//codigo secreto
                            "\nTester" +//nombre
                            "\nTester1OP" +//nick
                            "\n12341234" +//contraseña
                            "\n2" + //entrar al menu avanzado de usuarios
                            "\n1" + //seleccion del menu para banear usuarios
                            "\n0" + // eleccion del usuario a banear
                            "\n5" + // vuelta al menu inicio
                            "\n3"; // acabar programa
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Sistema sistema = new Sistema();
            assertEquals(1, sistema.getWhiteList().size(), "Hay un error en el codigo");
            assertEquals(1, sistema.getBlackList().size(), "Hay un error en el codigo");
        }
}

class crearDemonioTest{
    @Test
    void crearDemonioTest(){
        Demonio demonio1 = new Demonio("Demonio1", 3, "Ha pactado con x");
        Demonio demonio2 = new Demonio("Demonio2", 3, "Ha pactado con y");
        boolean sonDiferentes = true;
        if (demonio1.getConjuntoEsbirros().isEmpty() && demonio2.getConjuntoEsbirros().isEmpty()){
            System.out.println("Ambos demonios tienen los conjuntos de esbirros vacíos, vuelve a ejecutar el test");
            //sonDiferentes = true;
        } else if ((demonio1.getConjuntoEsbirros().size()) != demonio2.getConjuntoEsbirros().size()){
            System.out.println("Los conjuntos de esbirros tienen longitud diferente");
            //sonDiferentes = true;
        } else  {
            for (Esbirro esbirro1 : demonio1.getConjuntoEsbirros()) {
                for (Esbirro esbirro2 : demonio2.getConjuntoEsbirros()) {
                    if (esbirro1.getNombre().equals(esbirro2.getNombre())) { //el nombre es random
                        sonDiferentes = false;
                        System.out.println("Los conjuntos de esbirros tienen exactamente los mismos esbirros con el mismo nombre, por lo que es probable que no se estén generando aleatoriamente los esbirros de los demonios");
                    }
                }
            }
            if (sonDiferentes){
                System.out.println("Los conjuntos de esbirros no son vacíos, tienen la misma longitud y sus esbirros son diferentes");
            }
        }
        assert(sonDiferentes);
;    }
}
class desbanearUsuarioTest {
    @Test
    public void desbanearUsuarioTest() throws IOException, ClassNotFoundException {

        String data =
                        "\n" +
                        "1" + //entrar al menu para registrarse
                        "\n" +
                        "1" + //elegir registrarse como jugador
                        "\nTester"+ // nombre
                        "\nTester1"+ //nick
                        "\n123412344"+ //contraseña
                        "\nPersonaje"+ // nombre del personaje
                        "\n200" + //cantidad oro
                        "\n1"+  //Rol de Personaje(cazador)
                        "\n1"+ //puntos de voluntad
                        "\n1"+ // elegir el tipo de esbirro
                        "\nHumano"+ //nombre del esbirro
                        "\n1"+ //cantidad de salud del esbirro
                        "\nALTA"+ // elegir la lealtad
                        "\n12"+ // elegir el arma/armadura para el personaje
                        "\n0" + // salir de la eleccion de arma/armadura
                        "\n1" + //Seleccionas arma activa
                        "\n0"+ //entras al menú
                        "\n5" +
                        "\n1" +//registrar
                        "\n2" +// op
                        "\n1234" +//codigo secreto
                        "\nTester" +//nombre
                        "\nTester1OP" +//nick
                        "\n12341234" +//contraseña
                        "\n2" + //seleccion del menu avanzado de usuarios
                        "\n1" + //seleccion para banear usuarios
                        "\n0" + //seleccion del usuario a banear
                        "\n2" + //seleccion del menu avanzado de usuarios
                        "\n2" + //seleccion para desbanear usuarios
                        "\n0" + //seleccion del usurio a desbanear
                        "\n5" + //vuelta al menu inicio
                        "\n3"; // acabar programa
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(2, sistema.getWhiteList().size(), "Hay un error en el codigo");
        assertEquals(0, sistema.getBlackList().size(), "Hay un error en el codigo");
    }


}
class darseDeBajaTest {
    @Test
    public void darseDeBajaTest() throws IOException, ClassNotFoundException {

        String data =
                        "\n1" +//registrar
                        "\n2" +// op
                        "\n1234" +//codigo secreto
                        "\nTester" +//nombre
                        "\nTester1OP" +//nick
                        "\n12341234" +//contraseña
                        "\n4" +
                        "\n3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(0, sistema.getWhiteList().size(), "Hay un error en el codigo");
    }


}

class buscarComprarOfertaTest {
    @Test
    public void buscarComprarOfertaTest() throws IOException {
        String data =
                "\n" +
                        "1" + //entrar al menu para registrarse
                        "\n" +
                        "1" + //elegir registrarse como jugador
                        "\nTester" + // nombre
                        "\nTester1" + //nick
                        "\n123412344" + //contraseña
                        "\nPersonaje" + // nombre del personaje
                        "\n200" + //cantidad oro
                        "\n1" +  //Rol de Personaje(cazador)
                        "\n1" + //puntos de voluntad
                        "\n1" + // elegir el tipo de esbirro
                        "\nHumano" + //nombre del esbirro
                        "\n1" + //cantidad de salud del esbirro
                        "\nALTA" + // elegir la lealtad
                        "\n12" + // elegir el arma/armadura para el personaje
                        "\n0" + // salir de la eleccion de arma/armadura
                        "\n1" + //Seleccionas arma activa
                        "\n0" + //entras al menú
                        "\n2" + //seleccion del menu avanzado de ofertas
                        "\n1" + // seleccion de crear oferta
                        "\n1" + // seleccion del tipo de equipo a ofertar(arma)
                        "\n0" + // seleccion del arma que se quiere ofertar
                        "\n100" + //precio de la oferta
                        "\n5" + // vuelta al menu avanzado de oferta
                        "\n5" + // vuelta al menu principal
                        "\n5" + // vuelta al menu inicio
                        "\n1" +//registrar
                        "\n2" +// op
                        "\n1234" +//codigo secreto
                        "\nTester" +//nombre
                        "\nTester1OP" +//nick
                        "\n12341234" +//contraseña
                        "\n1" + //menu validar oferta
                        "\n1" + //validar oferta
                        "\n1" + // elegir oferta
                        "\n0" + //eleccion de la oferta
                        "\n0" + //aceptar validacion
                        "\n5" +//salir al menu inicio
                        "\n1" +
                        "\n1" + //elegir registrarse como jugador
                        "\nTester" + // nombre
                        "\nT" + //nick
                        "\n123412344" + //contraseña
                        "\nPersonaje" + // nombre del personaje
                        "\n500" + //cantidad oro
                        "\n1" +  //Rol de Personaje(cazador)
                        "\n1" + //puntos de voluntad
                        "\n1" + // elegir el tipo de esbirro
                        "\nHumano" + //nombre del esbirro
                        "\n1" + //cantidad de salud del esbirro
                        "\nALTA" + // elegir la lealtad
                        "\n12" + // elegir el arma/armadura para el personaje
                        "\n0" + // salir de la eleccion de arma/armadura
                        "\n1" + //Seleccionas arma activa
                        "\n0" + //entras al menú
                        "\n2" + //entras al menu avanzado de ofertas
                        "\n2" + //entras en buscar y comprar ofertas
                        "\n1" + //seleccionas una oferta
                        "\n5" + //volver al menu inicio
                        "\n3"; //terminar ejecucion
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(0, sistema.getListaOfertas().size(), "Hay un error en el codigo");
        assertEquals(1, sistema.getListaLogs().size(), "Hay un error en el codigo con el Log");
    }
}

    class darseDeBajaJugador{
        @Test
        public void darseDeBajaJugador() throws IOException {
            String data =
                    "\n1" +//registrar
                            "\n1" +// jugador
                            "\nTester" +//nombre
                            "\nTester1" +//nick
                            "\n12345678" +//contraseña
                            "\nPersonaje" +//nombre del personaje
                            "\n200" +//cantidad de oro
                            "\n1"+//rol personaje
                            "\n1"+//puntos de voluntad
                            "\n1"+//tipo de esbirro
                            "\nEsbirro"+//nombre del esbirro
                            "\n1" +//salud del esbirro
                            "\nALTA" +//tipo de lealtad
                            "\n19"+//seleccione equipo a añadir
                            "\n0"+//no se añaden mas
                            "\n1"+//elegir arma activa
                            "\n3"+//darse de baja
                            "\n3"; //terminar ejecucion

            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Sistema sistema = new Sistema();
            assertEquals(0, sistema.getWhiteList().size(), "Hay un error en el codigo");
        }
    }

    class registroAleatorioTest{
        @Test
        public void registroAleatorioTest() throws IOException {
            String data =
                    "\n1" +//registrar
                            "\n1" +// jugador
                            "\nTester" +//nombre
                            "\nTester1" +//nick
                            "\n12345678" +//contraseña
                            "\nPersonaje" +//nombre del personaje
                            "\n200" +//cantidad de oro
                            "\n1"+//rol personaje
                            "\n1"+//puntos de voluntad
                            "\n1"+//tipo de esbirro
                            "\nEsbirro"+//nombre del esbirro
                            "\n1" +//salud del esbirro
                            "\nALTA" +//tipo de lealtad
                            "\n19"+//seleccione equipo a añadir
                            "\n0"+//no se añaden mas
                            "\n1"+//elegir arma activa
                            "\n5"+//volver al menu de inicio
                            "\n1"+//registro
                            "\n1" +// jugador
                            "\nTester" +//nombre
                            "\nTester2" +//nick
                            "\n12345678" +//contraseña
                            "\nPersonaje" +//nombre del personaje
                            "\n300" +//cantidad de oro
                            "\n1"+//rol personaje
                            "\n1"+//puntos de voluntad
                            "\n1"+//tipo de esbirro
                            "\nEsbirro"+//nombre del esbirro
                            "\n1" +//salud del esbirro
                            "\nALTA" +//tipo de lealtad
                            "\n19"+//seleccione equipo a añadir
                            "\n0"+//no se añaden mas
                            "\n1"+//elegir arma activa
                            "\n5"+//volver al menu de inicio
                            "\n3"; //terminar ejecucion

            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Sistema sistema = new Sistema();
            assertEquals(false, ((Jugador) sistema.getWhiteList().get(0)).getNumRegistro().equals(((Jugador) sistema.getWhiteList().get(1)).getNumRegistro()), "Hay un error en el codigo");

        }
    }

    class nickNoRegistradoTest{
        @Test
        public void nickNoRegistradoTest() throws IOException {
            String data =
                    "\n1" +//registrar
                            "\n1" +// jugador
                            "\nTester" +//nombre
                            "\nTester1" +//nick
                            "\n12345678" +//contraseña
                            "\nPersonaje" +//nombre del personaje
                            "\n200" +//cantidad de oro
                            "\n1"+//rol personaje
                            "\n1"+//puntos de voluntad
                            "\n1"+//tipo de esbirro
                            "\nEsbirro"+//nombre del esbirro
                            "\n1" +//salud del esbirro
                            "\nALTA" +//tipo de lealtad
                            "\n19"+//seleccione equipo a añadir
                            "\n0"+//no se añaden mas
                            "\n1"+//elegir arma activa
                            "\n5"+//volver al menu de inicio
                            "\n1"+//registro
                            "\n1" +// jugador
                            "\nTester" +//nombre
                            "\nTester1" +//nick igual
                            "\nTester2"+//nick distinto
                            "\n12345678" +//contraseña
                            "\nPersonaje" +//nombre del personaje
                            "\n300" +//cantidad de oro
                            "\n1"+//rol personaje
                            "\n1"+//puntos de voluntad
                            "\n1"+//tipo de esbirro
                            "\nEsbirro"+//nombre del esbirro
                            "\n1" +//salud del esbirro
                            "\nALTA" +//tipo de lealtad
                            "\n19"+//seleccione equipo a añadir
                            "\n0"+//no se añaden mas
                            "\n1"+//elegir arma activa
                            "\n5"+//volver al menu de inicio
                            "\n3"; //terminar ejecucion

            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Sistema sistema = new Sistema();
            assertEquals(false, ((Jugador) sistema.getWhiteList().get(0)).getNick().equals(((Jugador) sistema.getWhiteList().get(1)).getNick()), "Hay un error en el codigo");
        }
    }
    class contraseñaTest{
        @Test
        public void contraseñaTest () throws IOException {
                String data =
                        "\n1" +//registrar
                                "\n1" +// jugador
                                "\nTester" +//nombre
                                "\nTester1" +//nick
                                "\n1234567" +//contraseña de 7 digitos, es decir, menos de 8 caracteres
                                "\n1234567890123" + //contraseña de 13 digitos, es decir, mas de 12 caracteres
                                "\n123456789" + //contraseña de 9 digitos, es decir, entre 8 y 12 caracteres
                                "\nPersonaje" +//nombre del personaje
                                "\n200" +//cantidad de oro
                                "\n1"+//rol personaje
                                "\n1"+//puntos de voluntad
                                "\n1"+//tipo de esbirro
                                "\nEsbirro"+//nombre del esbirro
                                "\n1" +//salud del esbirro
                                "\nALTA" +//tipo de lealtad
                                "\n19"+//seleccione equipo a añadir
                                "\n0"+//no se añaden mas
                                "\n1"+//elegir arma activa
                                "\n5"+//volver al menu de inicio
                                "\n3";
                System.setIn(new ByteArrayInputStream(data.getBytes()));
                Sistema sistema = new Sistema();
        }

    }


