package org.example;
import lombok.Getter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Getter
public class Usuario implements Acciones {

    private String nombre;
    private String contraseña;
    private Map<Evento, Integer> caritoCompra;

    public Usuario(String nombre, String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
    }

    public void anyadirAlCarrito(Evento evento, int cantidad){
        caritoCompra.put(evento, cantidad);
    }

    public void setCarito(Evento evento, int cantidad){

    }

    public void verCarrito(){
        for (Map.Entry<Evento, Integer> mapita : caritoCompra.entrySet()){
            System.out.println("Carrito: " + mapita.getKey().getNombre() + " tienes " + mapita.getValue() + " entradas.");
        }
    }

    @Override
    public void pagar() {

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
