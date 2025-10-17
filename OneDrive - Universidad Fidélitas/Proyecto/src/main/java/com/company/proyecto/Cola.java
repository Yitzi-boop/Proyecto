/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 * Estructura FIFO: el primero en entrar es el primero en salir.
 */
public class Cola<T> {

    private Object[] datos;
    private int frente;
    private int size;

    public Cola() {
        datos = new Object[8];
        frente = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void offer(T elemento) { // Encola
        if (size == datos.length) {
            aumentarCapacidad();
        }
        int pos = (frente + size) % datos.length;
        datos[pos] = elemento;
        size++;
    }

    public T poll() { // Desencola (retorna null si vacia)
        if (isEmpty()) {
            System.out.println("Cola vacia, no se puede eliminar.");
            return null;
        }
        @SuppressWarnings("unchecked")
        T valor = (T) datos[frente];
        datos[frente] = null;
        frente = (frente + 1) % datos.length;
        size--;
        return valor;
    }

    public T peek() { // Ver primer elemento
        if (isEmpty()) {
            System.out.println("Cola vacia.");
            return null;
        }
        @SuppressWarnings("unchecked")
        T valor = (T) datos[frente];
        return valor;
    }

    public T get(int index) { // Obtener elemento sin quitarlo
        if (index < 0 || index >= size) {
            System.out.println("Indice fuera de rango.");
            return null;
        }
        int pos = (frente + index) % datos.length;
        @SuppressWarnings("unchecked")
        T valor = (T) datos[pos];
        return valor;
    }

    private void aumentarCapacidad() {
        Object[] nuevo = new Object[datos.length * 2];
        for (int i = 0; i < size; i++) {
            nuevo[i] = datos[(frente + i) % datos.length];
        }
        datos = nuevo;
        frente = 0;
    }
}
