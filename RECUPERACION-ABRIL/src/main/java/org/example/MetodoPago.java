package org.example;

public enum MetodoPago {
    PAYPAL(0.5), BIZUM(1), APPLEPAY(1.5);

    private double precio;

    MetodoPago(double precio){
        this.precio=precio;
    }
}
