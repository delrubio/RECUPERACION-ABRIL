package org.example;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.time.LocalDate;
import java.util.*;

public class TicketMutxa {

    public static final String[] EVENTOS = {"festival", "concierto"};
    public static Scanner teclado = new Scanner(System.in);

    static private Set<Usuario> usuariosRegistrados;
    static private Set<Evento> listaEventos;

    public static Usuario comprador;

    public static void main(String[] args) {


        generarUsuarios(4);
        insertarEvento("Paellas", LocalDate.of(2025,4,16), 2.0, "festival");
        iniciarSesion();

    }

    public static void iniciarSesion(){
        System.out.println("*** BIENVENIDO A TICKETMUTCHA ***");
        System.out.print("   Usuario: ");
        String usu = teclado.next();
        System.out.print("   Contraseña: ");
        String pass = teclado.next();

        comprador = new Usuario(usu, pass);

        if (comprador.autenticarse(getUsuariosRegistrados())){
            verEventos();
        }else {
            System.out.println("El usuario no es correcto.");
            iniciarSesion();
        }

    }

    public static void insertarEvento(String nombre, LocalDate fecha, double precio, String tipo){

        listaEventos = new HashSet<>();

        if (tipo.toLowerCase().matches(EVENTOS[0])){
            Evento eventoNuevo = new Evento(nombre, fecha, precio);
            listaEventos.add(eventoNuevo);
            Festival fest = new Festival();
        } else if (tipo.toLowerCase().matches(EVENTOS[1])){
            Evento eventoNuevo = new Evento(nombre, fecha, precio);
            listaEventos.add(eventoNuevo);
            Concierto concierto = new Concierto();
        } else {
            System.out.println("No existe el tipo de evento.");
        }
    }

    public static void generarUsuarios(int cantidad){
        usuariosRegistrados = new HashSet<>();
        Usuario usu1 = new Usuario("David", "david123");
        Usuario usu2 = new Usuario("Patri", "patri123");
        Usuario usu3 = new Usuario("Ivan", "ivan123");
        Usuario usu4 = new Usuario("Sara", "sara123");
        usuariosRegistrados.add(usu1);
        usuariosRegistrados.add(usu2);
        usuariosRegistrados.add(usu3);
        usuariosRegistrados.add(usu4);

    }

    public static void colaEntradas(){
        for (int i = 0; i < usuariosRegistrados.size(); i++) {
            System.out.println("Esperando en la cola. Quedan " + i + " personas...");
        }
    }

    public static Set<Usuario> getUsuariosRegistrados(){
        return Collections.unmodifiableSet(usuariosRegistrados);
    }

    public static void verEventos(){

        System.out.println("Eventos Próximos...");
        System.out.println(" ");

        Map<Integer, Evento> mapita = new HashMap<>();

        int cont = 0;
        for (Evento eventos : listaEventos){
            cont++;
            System.out.println(cont  + " - " + eventos.getNombre() + " " + eventos.getFecha());
            mapita.put(cont, eventos);
        }

        System.out.println(" ");
        System.out.println("------------------------");
        System.out.println(" ");
        System.out.print("Elige un evento: ");

        int opc = 0;

        try{
            opc = teclado.nextInt();
        }catch (InputMismatchException e1){
            System.out.println("ERROR. Esto no es una opcion...");
            verEventos();
        }

        for (Map.Entry<Integer, Evento> mapita2 : mapita.entrySet()){
            if (mapita2.getKey() == opc){

                System.out.println("Cuantas entradas quieres?");
                if (teclado.nextInt()>7){
                    System.out.println("No puedes añadir más de 7 entradas...");
                    verEventos();
                }else {
                    comprador.anyadirAlCarrito(mapita2.getValue(), teclado.nextInt());
                }
            }
        }

        comprador.verCarrito();

    }

//    public static Evento getEvento(int posicion){
//    }
}
