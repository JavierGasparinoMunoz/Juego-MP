import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

class modificarOroTest {

    @Test
    void modificarOroTest() throws IOException, ClassNotFoundException {
        int cantidadOroInicial = 500;
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
                "\n1"+ //sumar oro
                "\n" + oroSumado +
                "\n5" +//Salir
                "\n3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(cantidadOroInicial + oroSumado, ((Jugador)sistema.getUsuario()).getPersonaje().getCantidadOro(), "Hay un error en el codigo");
    }
}

class crearOferta {
    @Test
    void crearEquipoTest() throws IOException {
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
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n2" + //eliminarEquipo()
                "\n1" + //eliminar arma 1 (Debe eliminar esta arma de armas activas también)
                "\n1" + //Gestion avanzada del personaje
                "\n3" + //elegirArmasActivas() (Debe mandarme de nuevo al menú)
                "\n1" + //Gestion avanzada del personaje
                "\n1" + //modificarEquipo()
                "\n1" +
        "\n1" + //modificarEquipo()
                "\n3";


        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();

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
                    "\n1"+ // elegir oferta
                    "\n0" + //eleccion de la oferta
                    "\n0" + //aceptar validacion
                    "\n5" +//salir al menu inicio
                    "\n3";//acabar programa
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(1, sistema.getListaOfertas().size(), "Hay un error en el codigo");
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