/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 * Pila (tipo LIFO) usando un arreglo dinámico.
 * Soporta: push, pop, peek, isEmpty, size, get(i), set(i).
 * Índice 0 = base | índice size-1 = cima.
 */

public class Pila<T> {

    private Object[] datos;
    private int size;

    public Pila() {
        this.datos = new Object[8]; // capacidad inicial
        this.size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void push(T valor) {
        asegurarCapacidad(size + 1);
        datos[size++] = valor; // agregamos al final (cima)
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Pila vacia");
        T val = (T) datos[size - 1];
        datos[size - 1] = null;
        size--;
        return val;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Pila vacia");
        return (T) datos[size - 1];
    }

    /** Acceso por índice (0 = base ... size-1 = cima) */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        validarIndice(index);
        return (T) datos[index];
    }

    /** Reemplazo por índice (0 = base ... size-1 = cima) */
    public void set(int index, T valor) {
        validarIndice(index);
        datos[index] = valor;
    }

    private void asegurarCapacidad(int requerida) {
        if (requerida <= datos.length) return;
        int nuevaCap = Math.max(datos.length * 2, requerida);
        Object[] nuevo = new Object[nuevaCap];
        System.arraycopy(datos, 0, nuevo, 0, size);
        datos = nuevo;
    }

    private void validarIndice(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("indice invalido: " + index + " (size=" + size + ")");
        }
    }
}