
package gestordeproductos_crud;

import java.util.Scanner;

public class Gestordeproductos_crud {

    static String[] nombres = new String[5];
    static double[] precios = new double[5];
    static int contador = 0;

    static final int rol_administrador = 1;
    static final int rol_usuario = 2;
    static final int SALIR = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int rol;
        int opcion;
        while(true){
        
        System.out.println("\n--- Seleccione su rol ---");
        System.out.println("1. Administrador");
        System.out.println("2. Usuario");
        System.out.println("3. Salir");
        System.out.print("Opcion: ");
        
        rol = sc.nextInt();
        sc.nextLine();

        
        if (rol == SALIR) {
            System.out.println("Saliendo del programa...");
            sc.close();
            return;
        }

        
        if (rol != rol_administrador && rol != rol_usuario) {
            System.out.println("Rol no valido.");
            sc.close();
            return;
        }

        do {

            mostrarMenu(rol);

            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            // Restricciones para el usuario
            if (rol == rol_usuario&& opcion != 0 && opcion != 2 && opcion != 3) {
                System.out.println("Acceso denegado. El usuario solo puede listar y buscar.");
                continue;
            }

            switch (opcion) {

                case 1 -> {
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio del producto: ");
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

                    int indice = buscar(nombre);

                    if (indice == -1) {
                        System.out.println("Producto no encontrado.");
                    } else {
                        System.out.println("Encontrado en posicion " + indice);
                    }
                }

                case 4 -> {
                    System.out.print("Nombre a actualizar: ");
                    String nombre = sc.nextLine();

                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = sc.nextDouble();
                    sc.nextLine();

                    actualizarPrecio(nombre, nuevoPrecio);
                }

                case 5 -> {
                    System.out.print("Nombre a eliminar: ");
                    String nombre = sc.nextLine();

                    eliminar(nombre);
                }
                
                case 6 -> {
                        System.out.println("Regresando a seleccion de rol...");
                    }

                case 0 -> {
                    System.out.println("Saliendo del programa...");
                }

                default -> {
                    System.out.println("Opcion no valida.");
                }
            }

        } while (opcion != 0 && opcion != 6); // sale del menu si elige 0 o 6

            // Si eligio 0, termina todo el programa
            if (opcion == 0) {
                break;
            }

            // Si eligio 6, el bucle externo continua y vuelve a pedir el rol
        }

        sc.close();
    }
    

    static void mostrarMenu(int rol) {

        System.out.println("\n--- Menu de Productos ---");

        if (rol == rol_administrador) {

            System.out.println("Rol: Administrador");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6.Salir de administrador");
            System.out.println("0. Salir");
            

        } else {

            System.out.println("Rol: Usuario");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("0.salir");
        }
    }

    static boolean agregar(String nombre, double precio) {

        if (contador >= nombres.length) {
            System.out.println("No hay espacio para mas productos.");
            return false;
        }

        nombres[contador] = nombre;
        precios[contador] = precio;

        contador++;

        System.out.println("Producto agregado correctamente.");

        return true;
    }

    static void Listar() {

        if (contador == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (int i = 0; i < contador; i++) {
            System.out.println(i + ". " + nombres[i] + " - $" + precios[i]);
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