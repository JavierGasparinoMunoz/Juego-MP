import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Desafio {
    int ronda;
    ArrayList<String> personajes= new ArrayList<String>();

    public void inicializarModificadores(Operador operador){
        auxInicializarModificadores(personajes.get(0).getlistaFortalezas(), personajes.get(1).getlistaFortalezasActivas());
        auxInicializarModificadores(personajes.get(0).getlistaDebilidades(), personajes.get(0).getlistaDebilidadesActivas());
        auxInicializarModificadores(personajes.get(1).getlistaFortalezas(), personajes.get(1).getlistaFortalezasActivas());
        auxInicializarModificadores(personajes.get(1).getlistaDebilidades(), personajes.get(1).getlistaDebilidadesActivas());
    }
    private void auxInicializarModificadores(ArrayList<Modificador> listaMod, ArrayList<Modificador> listaModInicializada){
        //debería poder recibir cualquier usuario y luego comprobar si es operador? Cómo se haría?
        listaModInicializada.clear();
        ArrayList<Modificador> listaModCopy = new ArrayList<>();
        listaModCopy.addAll(listaMod);
        Scanner sc = new Scanner(System.in);
        System.out.println("Elimine las fortalezas uno por uno que desee para P1: ");
        System.out.println("0) Pasar a siguiente pantalla...");
        int i = 1;
        String opcion = "";
        //Hay que cambiar los sets de modificadores por listas para acceder por índice.
        while (!listaModCopy.isEmpty() && !opcion.equals("0")) {
            for (Modificador modificador: listaModCopy) {
                System.out.println(i + ") " + modificador.getNombre() + " Valor: " + modificador.getValor());
                i += 1;
            }
            opcion = sc.nextLine();
            try {
                int opcionInt = Integer.parseInt(opcion);
                if (opcionInt > 0 && opcionInt < listaModCopy.size()){
                    listaModInicializada.add(listaModCopy.get(opcionInt - 1));
                    listaModCopy.remove(opcionInt-1);
                }
                else{
                    System.out.println("Por favor, introduzca un solo entero en el rango indicado: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un solo entero: ");
            }
        }
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public ArrayList<String> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<String> personajes) {
        this.personajes = personajes;
    }
}
