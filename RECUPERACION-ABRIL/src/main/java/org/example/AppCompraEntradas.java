package org.example;

import java.time.LocalDate;

public class AppCompraEntradas {
    public static void main(String[] args) {

        TicketMutxa appTickets = new TicketMutxa();

        appTickets.generarUsuarios(4);
        appTickets.insertarEvento("Paellas", LocalDate.of(2025,4,16), 2.0, "festival");
        appTickets.iniciarSesion();
    }
}