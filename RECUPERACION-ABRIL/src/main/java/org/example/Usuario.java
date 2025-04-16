package org.example;
import lombok.Getter;

import java.util.*;

@Getter
public class Usuario implements Acciones {

    public Scanner teclado = new Scanner(System.in);

    private String nombre;
    private String contraseña;
    private Map<Evento, Integer> caritoCompra;

    public Usuario(String nombre, String contraseña){
        caritoCompra = new HashMap<>();
        this.nombre=nombre;
        this.contraseña=contraseña;
    }

    public void anyadirAlCarrito(Evento evento, int cantidad){
        caritoCompra.put(evento, caritoCompra.getOrDefault(evento, 0) + cantidad);
        verCarrito();
    }

    public void setCarito(){

        int opc;

        System.out.println("Elige una opcion: ");
        System.out.println("1. Añadir más entradas.");
        System.out.println("2. Eliminar entradas.");
        System.out.println("3. Finalizar y pagar.");
        System.out.println("------------------------");
        opc = teclado.nextInt();

        switch (opc){
            case 1:
                TicketMutxa.verEventos();
                break;
            case 2:
                System.out.println("Cuantas quieres quitar?");
                int numero = teclado.nextInt();

                for (Map.Entry<Evento, Integer> mapita : caritoCompra.entrySet()){
                    if (numero < mapita.getValue() && numero > 0){
                        mapita.setValue(mapita.getValue()-numero);
                    }else {
                        System.out.println("ERROR. No hay suficientes entradas.");
                    }
                }

                verCarrito();
                break;
            case 3:
                pagar();
                break;
            default:
                System.out.println("ERROR. Vuelve a intentarlo");
                setCarito();
        }

    }

    public void verCarrito(){
        for (Map.Entry<Evento, Integer> mapita : caritoCompra.entrySet()) {
            System.out.println("Carrito: " + mapita.getKey().getNombre() + " - tienes " + mapita.getValue() + " entradas. Importe total: " + (mapita.getKey().getPrecio() * mapita.getValue()) + "€");
        }setCarito();
    }

    @Override
    public void pagar() {

        String opc;

        System.out.println("Elige un método de pago:");
        System.out.println(MetodoPago.PAYPAL + " (gastos de gestión : " + MetodoPago.valueOf("PAYPAL").getPrecio() + " €)");
        System.out.println(MetodoPago.BIZUM + " (gastos de gestión : " + MetodoPago.valueOf("BIZUM").getPrecio() + " €)");
        System.out.println(MetodoPago.APPLEPAY + " (gastos de gestión : " + MetodoPago.valueOf("APPLEPAY").getPrecio() + " €)");
        System.out.println("------------------------");
        opc = teclado.next().toUpperCase();

        switch (opc){
            case "PAYPAL":
                System.out.println("Gracias por pagar con PAYPAL. Importte final sumando gastos de gestión: " + MetodoPago.valueOf("PAYPAL").getPrecio() + " €)");
                System.out.println("GRACIAS. DISFRUTA DEL EVENTO");
                break;
            case "BIZUM":
                System.out.println("Gracias por pagar con BIZUM. Importte final sumando gastos de gestión: " + MetodoPago.valueOf("BIZUM").getPrecio() + " €)");
                System.out.println("GRACIAS. DISFRUTA DEL EVENTO");
                break;
            case "APPLEPAY":
                System.out.println("Gracias por pagar con APPLEPAY. Importte final sumando gastos de gestión: " + MetodoPago.valueOf("APPLEPAY").getPrecio() + " €)");
                System.out.println("GRACIAS. DISFRUTA DEL EVENTO");
                break;
            default:
                System.out.println("ERROR. Método no válido");
                pagar();
                break;
        }

    }

    @Override
    public boolean autenticarse(Set<Usuario> usuariosRegistrados) {

        Iterator<Usuario> it = usuariosRegistrados.iterator();
        boolean correcto = false;

        while (it.hasNext()){

            Usuario usu = it.next();
            if (this.nombre.equals(usu.getNombre()) && this.getContraseña().equals(usu.getContraseña())){
                correcto = true;
                break;
            }else {
                correcto = false;
            }

        }return correcto;
    }
}
