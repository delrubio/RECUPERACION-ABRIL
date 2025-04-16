package org.example;

import java.time.LocalDate;
import java.util.*;

public class TicketMutxa {

    public static final String[] EVENTOS = {"festival", "concierto"};
    public static Scanner teclado = new Scanner(System.in);

    static private Set<Usuario> usuariosRegistrados;
    static private Set<Evento> listaEventos;
    public static Usuario comprador;

    public TicketMutxa(){
        listaEventos = new HashSet<>();
        usuariosRegistrados = new HashSet<>();
    }

    public void generarUsuarios(int cantidad){
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

    public void iniciarSesion(){
        System.out.println("*** BIENVENIDO A TICKETMUTCHA ***");
        System.out.print("   Usuario: ");
        String usu = teclado.next();
        System.out.print("   Contraseña: ");
        String pass = teclado.next();

        comprador = new Usuario(usu, pass);

        if (comprador.autenticarse(getUsuariosRegistrados())){
            verEventos();
        }else {
            System.out.println("ERROR. El usuario no es correcto.");
            iniciarSesion();
        }

    }

    public void insertarEvento(String nombre, LocalDate fecha, double precio, String tipo){

        if (tipo.toLowerCase().matches(EVENTOS[0])){
            Evento festival = new Festival(nombre, fecha, precio);
            listaEventos.add(festival);
        } else if (tipo.toLowerCase().matches(EVENTOS[1])){
            Evento eventoNuevo = new Concierto(nombre, fecha, precio);
            listaEventos.add(eventoNuevo);
        } else {
            System.out.println("ERROR. No existe el tipo de evento.");
        }
    }

    public static void colaEntradas(){
        for (int i = getUsuariosRegistrados().size(); i > 0; i--) {
            if (i == 1){
                System.out.println(" -- Esperando en la cola. Quedan " + (i) + " personas...");
                System.out.println("¡TU TURNO!");
            }else {
                System.out.println(" -- Esperando en la cola. Quedan " + (i) + " personas...");
            }
        }
    }

    public static Set<Usuario> getUsuariosRegistrados(){
        return Collections.unmodifiableSet(usuariosRegistrados);
    }

    public static void verEventos(){

        Map<Integer, Evento> mapita = new HashMap<>();
        int opc = 0;
        int cont = 0;
        int entradas = 0;

        System.out.println("Eventos Próximos...");
        System.out.println("------------------------");

        for (Evento eventos : listaEventos){
            cont++;
            System.out.println(cont  + " - " + eventos.getNombre() + " " + eventos.getFecha());
            mapita.put(cont, eventos);
        }

        System.out.println("------------------------");
        System.out.print("Elige un evento: ");

        opc = teclado.nextInt();

        if (mapita.containsKey(opc)){
            colaEntradas();

            System.out.println("¿Cuantas entradas quieres? (máximo 7)");
            entradas = teclado.nextInt();

            if (entradas > 7){
                System.out.println("ERROR. No puedes añadir más de 7 entradas...");
                verEventos();
            }else{
                comprador.anyadirAlCarrito(mapita.get(opc), entradas);
            }
        }else {
            System.out.println("ERROR. Introduce un evento correcto...");
            verEventos();

        }

    }

//    public static Evento getEvento(int posicion){
//        for (Evento eventillo : listaEventos){
//            return eventillo;
//        }
//    }
}
