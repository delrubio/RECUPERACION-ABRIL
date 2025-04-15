package org.example;

import java.util.Scanner;

public class Concierto {
    Scanner teclado = new Scanner(System.in);
    private String artista;

    public Concierto(){
    }

    public void setArtista(){
        System.out.print("Dime el nombre del artista... ");
        artista = teclado.next();
    }
}
