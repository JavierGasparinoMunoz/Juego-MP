import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class modificarOroTest {

    @Test
    public void modificarOroTest() throws IOException, ClassNotFoundException {
        int cantidadOroInicial = 500;
        int oroSumado = 100;
        String data = "1" +
                "\n" +
                "1" +
                "\n" +
                "1" +
                "\nTester"+
                "\nTester1"+
                "\n123412344"+
                "\nPersonaje"+
                "\n" + cantidadOroInicial+ //cantidad oro
                "\n1"+  //Rol de Personaje
                "\n1"+ //puntos de voluntad
                "\n1"+
                "\nHumano"+
                "\n1"+
                "\nALTA"+
                "\n12"+
                "\n0" +
                "\n1" + //Seleccionas arma activa
                "\n0"+ //entras al menú
                "\n1"+ //entras a Gestion avanzada del personaje
                "\n2"+ //entras a modificarOro()
                "\n1"+
                "\n" + oroSumado +
                "\n5" +//Salir
                "\n3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Sistema sistema = new Sistema();
        assertEquals(cantidadOroInicial + oroSumado, ((Jugador)sistema.getUsuario()).getPersonaje().getCantidadOro(), "Hay un error en el codigo");

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