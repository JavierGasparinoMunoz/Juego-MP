import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema implements Serializable {
    Scanner sc = new Scanner(System.in);
    private Notificacion notificacion;
    private Usuario usuario;
    private ArrayList<Usuario>  listaUsuarios = new ArrayList<>();
    private int opcion;

    public Sistema(){
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



}
