import java.io.*;

public class Inicio {
    private Sistema sistema;

    public Inicio() throws IOException, ClassNotFoundException {
        String ruta = "./informacion.bin";
        File ficheroUsuarios = new File(ruta);
        if( !ficheroUsuarios.exists()){
            try {
                ficheroUsuarios.createNewFile();
                sistema = new Sistema();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        else{
            sistema = deserializarSistema();
            sistema.menuInicio();
        }
    }

    public Sistema getSistema() {
        return sistema;
    }

    //método encargado de obtener la información en sesiones anteriores
    public Sistema deserializarSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
        String rutaArchivo = "./informacion.bin";
        ObjectInputStream datosEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
        Sistema datos = (Sistema) datosEntrada.readObject();
        return datos;
    }

}
