
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestordeproductos_crud;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Gestordeproductos_crud {

    static String[] nombres = new String[5];
    static double[] precios = new double[5];
    static int contador = 0;

    public static void main(String[] args) {
        

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- Menu de la empresa productos ---");
            System.out.println("1. Agregar (Create)");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Salir");

            System.out.println("Seleccione una opcion:");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.println("Nombre del producto:");
                    String nombre = sc.nextLine();

                    System.out.println("Precio del producto:");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    agregar(nombre, precio);
                }

                case 2 -> {
                    Listar();
                }

                case 3 -> {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();

                    int idx = buscar(nombre);

                    System.out.println(
                            idx == -1
                            ? "No encontrado."
                            : "Encontrado en posición " + idx
                    );
                }

                case 4 -> {
                    System.out.print("Nombre a actualizar: ");
                    String nombre = sc.nextLine();

                    System.out.print("Nuevo precio: ");
                    double nuevo = sc.nextDouble();
                    sc.nextLine();

                    actualizarPrecio(nombre, nuevo);
                }

                case 5 -> {
                    System.out.print("Nombre a eliminar: ");
                    String nombre = sc.nextLine();

                    eliminar(nombre);
                }

                case 0 -> {
                    System.out.println("Saliendo del programa...");
                }

                default -> {
                    System.out.println("Opcion no valida.");
                }
            }

        } while (opcion != 0);

        sc.close();
    }

    static boolean agregar(String nombre, double precio) {

        if (contador >= nombres.length) {

            System.out.println("No hay espacio en el vector para almacenar mas elementos");

            return false;
        }

        nombres[contador] = nombre;
        precios[contador] = precio;
        contador++;

        return true;
    }

    static void Listar() {

        if (contador == 0) {
            System.out.println("No hay productos registrados");
            return;
        }

        for (int i = 0; i < contador; i++) {
            System.out.println(i + " " + nombres[i] + " - $ " + precios[i]);
        }
    }

    static int buscar(String nombre) {

        for (int i = 0; i < contador; i++) {

            if (nombres[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }

        return -1;
    }

    static boolean actualizarPrecio(String nombre, double nuevoPrecio) {

        int indice = buscar(nombre);

        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        precios[indice] = nuevoPrecio;

        System.out.println("Precio actualizado correctamente.");

        return true;
    }

    static boolean eliminar(String nombre) {

        int indice = buscar(nombre);

        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        for (int i = indice; i < contador - 1; i++) {

            nombres[i] = nombres[i + 1];
            precios[i] = precios[i + 1];
        }

        nombres[contador - 1] = null;
        precios[contador - 1] = 0;

        contador--;

        System.out.println("Producto eliminado correctamente.");

        return true;
    }
}
