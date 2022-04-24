import java.util.HashSet;
import java.util.Set;

public abstract class Personaje {
    private String nombre;
    private Set<Arma> conjuntoArmas, armasActivas = new HashSet<Arma>();
    private Set<Armadura> conjuntoArmaduras, armadurasActivas = new HashSet<Armadura>();
    private Set<Esbirro> conjuntoEsbirros = new HashSet<Esbirro>();
    private Set<Modificador> conjuntoDebilidades,conjuntoFortalezas = new HashSet<Modificador>();
    private int cantidadOro,valorPoder,salud,ataque,defensa;
    private HabilidadEspecial habilidadEspecial;

    public Personaje() {

        //armas ofensivas
        espadaPequeña = new Arma(2, 0, 1, "Espada pequeña");
        espadon = new Arma(3, 1, 2, "Espadón");
        guadanya = new Arma(3, 0, 2, "Guadaña");
        palo = new Arma(1, 0, 2, "¡Un Palo!");
        cuchillo = new Arma(1, 0, 1, "¡Un Palo!");
        guantesMagicos = new Arma(3, 1, 2, "Guantes Mágicos");
        varitaMagica = new Arma(2, 1, 2, "Varita Mágica");
        varitaNoTanMagica = new Arma(1,0,2, "Varita No Tan Mágica");
        ocarina = new Arma(1, 1, 2, "Ocarina");
        bumeran = new Arma(2, 0, 1, "Bumerán");
        bfs = new Arma(5, 1, 1, "B.F.Sword");
        bajoAutoestima = new Arma(0, 0, 0, "Bajo Autoestima");

        //armas defensivas
        escudoPequeño = new Arma(0, 2 , 1, "Escudo Pequeño");
        escudoGrande = new Arma(0, 3, 1, "Escudo Grande");
        hologramaFormacionTortuga = new Arma(0, 1, 1, "Holograma Formación Tortuga, (solamente intimida.)");

        //armadura
        camisetaPrimark = new Armadura(0, 0, "Camiseta Primark");
        armaduraBásica = new Armadura(1,3, "Armadura Básica");
        armaduraTortuga = new Armadura(0,4, "Armadura Tortuga");
        armaduraDentada = new Armadura(2,2,0, "Armadura Dentada");

        //Falta añadirlos a sus respectivo sets pero no sé si es mejor hacer todo esto en sistema y a cada personaje pasarles los sets directamente

    }

    public abstract void habilidadEspecial();
    public abstract void añadirEsbirro();
    public abstract void eliminarEsbirro();


}
