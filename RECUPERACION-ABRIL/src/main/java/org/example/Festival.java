package org.example;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Festival extends Evento{

    Set<String> listaArtistas;

    public Festival(String nombre, LocalDate fecha, double precio) {
        super(nombre, fecha, precio);
        listaArtistas = new HashSet<>();
    }

    public void setArtistas(String art){
        listaArtistas.add(art);
    }

}
