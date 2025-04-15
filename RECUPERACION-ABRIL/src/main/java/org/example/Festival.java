package org.example;

import java.util.HashSet;
import java.util.Set;

public class Festival {
    Set<String> listaArtistas;

    public Festival(){
        listaArtistas = new HashSet<>();
    }

    public void setArtistas(String art){
        listaArtistas.add(art);
    }

}
