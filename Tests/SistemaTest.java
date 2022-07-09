import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class modificarOroTest {

    @Test
    public void modificarOroTest() throws IOException, ClassNotFoundException {
        int cantidadOroInicial = 500;
        int oroSumado = 100;
        String data = 1 +"\n" + 1 + "\n" + 1 +
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