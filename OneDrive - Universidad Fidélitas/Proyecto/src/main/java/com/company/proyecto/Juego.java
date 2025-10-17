/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
import java.util.Scanner;


public class Juego {
//colas
    private final Cola<Jugador> colaListos = new Cola<>();

    // Pilas 
    private final Pila<CartaAccion> pilaPremios = new Pila<>();
    private final Pila<CartaAccion> pilaCastigos = new Pila<>();

    private final Scanner sc = new Scanner(System.in);

    private final String version;
    private final String[] desarrolladores;

    private static final int MAX_JUGADORES = 4;

    public Juego(String version, String[] desarrolladores) {
        this.version = version;
        this.desarrolladores = desarrolladores;
        cargarCartasEjemploSegunEnunciado();
    }

    private void cargarCartasEjemploSegunEnunciado() {
        // Premios
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 2, "Suma dos posiciones."));
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 8, "Suma ocho posiciones."));
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 0, "Se queda en la posicion actual."));
        // Castigos
        pilaCastigos.push(new CartaAccion(Operacion.RESTA, 3, "Resta tres posiciones."));
        pilaCastigos.push(new CartaAccion(Operacion.IGUAL, 1, "Se debe ir a la posicion 1."));
        pilaCastigos.push(new CartaAccion(Operacion.RESTA, 5, "Resta cinco posiciones."));
    }

    public void ejecutarSoloInscripcionYPilas() {
        int opcion;
        do {
            System.out.println("====== MVP (Inscripcion y Pilas) ======");
            System.out.println("1) Inscribir jugadores (cola preparados-listos, max. " + MAX_JUGADORES + ")");
            System.out.println("2) Mantenimiento de Pilas (Premios/Castigos)");
            System.out.println("3) Mostrar estado rapido (cola y pilas)");
            System.out.println("0) Salir");
            System.out.println("============================================");
            opcion = UtilEntrada.leerEntero(sc, "Elige una opcion: ");

            switch (opcion) {
                case 1 -> inscribirJugadores();
                case 2 -> mantenimientoPilas();
                case 3 -> mostrarEstadoRapido();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    // ======= (1) Inscripcion de jugadores =======
    private void inscribirJugadores() {
        int actuales = colaListos.size();
        int disponibles = MAX_JUGADORES - actuales;
        if (disponibles <= 0) {
            System.out.println("La cola ya tiene " + MAX_JUGADORES + " jugadores.");
            return;
        }
        int n = UtilEntrada.leerEntero(sc, "Cuantos jugadores deseas inscribir? (disponibles: " + disponibles + "): ");
        if (n < 1) return;
        if (n > disponibles) n = disponibles;

        for (int i = 1; i <= n; i++) {
            String nombre = UtilEntrada.leerNoVacio(sc, "Nombre del jugador " + i + ": ");
            colaListos.offer(new Jugador(nombre));
        }
        System.out.println("Inscripcion completa. En cola: " + colaListos.size());
    }

    // ======= (2) Mantenimiento de Pilas =======
    private void mantenimientoPilas() {
        System.out.println("---- MANTENIMIENTO DE PILAS ----");
        System.out.println("1) Premios");
        System.out.println("2) Castigos");
        int cual = UtilEntrada.leerEntero(sc, "Elige pila: ");

        Pila<CartaAccion> pila = (cual == 1) ? pilaPremios : (cual == 2 ? pilaCastigos : null);
        if (pila == null) {
            System.out.println("Pila invalida.");
            return;
        }

        System.out.println("1) Agregar elemento");
        System.out.println("2) Eliminar elemento (cima)");
        System.out.println("3) Modificar elemento por indice");
        System.out.println("4) Mostrar elementos de la pila");
        int op = UtilEntrada.leerEntero(sc, "Opcion: ");

        switch (op) {
            case 1 -> agregarElemento(pila);
            case 2 -> eliminarElemento(pila);
            case 3 -> modificarElemento(pila);
            case 4 -> mostrarPila(pila);
            default -> System.out.println("Opcion invalida.");
        }
    }

    private void agregarElemento(Pila<CartaAccion> pila) {
        System.out.println("Operacion: 1) + (SUMA)  2) - (RESTA)  3) = (IGUAL)");
        int o = UtilEntrada.leerEntero(sc, "Elige operacion: ");
        Operacion op = switch (o) {
            case 1 -> Operacion.SUMA;
            case 2 -> Operacion.RESTA;
            case 3 -> Operacion.IGUAL;
            default -> null;
        };
        if (op == null) { System.out.println("Operacion invalida."); return; }

        int numero = UtilEntrada.leerEntero(sc, "Numero: ");
        String desc = UtilEntrada.leerNoVacio(sc, "Descripcion: ");

        pila.push(new CartaAccion(op, numero, desc));
        System.out.println("Elemento agregado (en la cima).");
    }

    private void eliminarElemento(Pila<CartaAccion> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila esta vacia.");
            return;
        }
        CartaAccion top = pila.pop();
        System.out.println("Eliminado de la cima: " + top);
    }

    private void modificarElemento(Pila<CartaAccion> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila esta vacia.");
            return;
        }
        mostrarPila(pila);
        int idx = UtilEntrada.leerEntero(sc, "Indice a modificar (0 = base ... " + (pila.size() - 1) + " = cima): ");
        if (idx < 0 || idx >= pila.size()) {
            System.out.println("Indice invalido.");
            return;
        }
        System.out.println("Operacion: 1) + (SUMA)  2) - (RESTA)  3) = (IGUAL)");
        int o = UtilEntrada.leerEntero(sc, "Elige operacion: ");
        Operacion op = switch (o) {
            case 1 -> Operacion.SUMA;
            case 2 -> Operacion.RESTA;
            case 3 -> Operacion.IGUAL;
            default -> null;
        };
        if (op == null) { System.out.println("Operacion invalida."); return; }

        int numero = UtilEntrada.leerEntero(sc, "Numero: ");
        String desc = UtilEntrada.leerNoVacio(sc, "Descripcion: ");

        pila.set(idx, new CartaAccion(op, numero, desc));
        System.out.println("Elemento modificado.");
    }

    private void mostrarPila(Pila<CartaAccion> pila) {
        if (pila.isEmpty()) { System.out.println("[Vacia]"); return; }
        System.out.println("Elementos (0 = base ... " + (pila.size() - 1) + " = cima):");
        for (int i = 0; i < pila.size(); i++) {
            System.out.println("[" + i + "] " + pila.get(i));
        }
    }

    // ======= Mostrar cola y pilas =======
    private void mostrarEstadoRapido() {
        System.out.println("-- Cola (frente -> final) --");
        if (colaListos.isEmpty()) {
            System.out.println("[Cola vacia]");
        } else {
            for (int i = 0; i < colaListos.size(); i++) {
                Jugador j = colaListos.get(i);
                System.out.println((i + 1) + ") " + j.getNombre() + " (pos=" + j.getPosicion() + ")");
            }
        }
        System.out.println("\n-- Pila Premios --");  mostrarPila(pilaPremios);
        System.out.println("-- Pila Castigos --");  mostrarPila(pilaCastigos);
    }
}