package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Concierto extends Evento{

    Scanner teclado = new Scanner(System.in);
    private String artista;

    public Concierto(String nombre, LocalDate fecha, double precio) {
        super(nombre, fecha, precio);
    }

    public void setArtista(){
        System.out.print("Dime el nombre del artista... ");
        artista = teclado.next();
    }
}
