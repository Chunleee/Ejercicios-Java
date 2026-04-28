/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medacquenoentras;

import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

public class MEDACqueNoEntras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        int edad;
        int resp = 0;

        do {
            try {
                System.out.println("\n1. Nombre y Edad     2. Salir");
                resp = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer tras leer un entero

                switch (resp) {
                    case 1:
                        System.out.println("Introduce nombre:");
                        nombre = sc.nextLine();

                        System.out.println("Introduce una edad:");
                        try {
                            edad = sc.nextInt();
                            
                            Persona p = new Persona(nombre, edad);
                            registrarEntrada(p);
                            System.out.println("Te dejo entrar. Datos guardados.");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Formato de edad inválido.");
                            registrarError("Error de formato en edad");
                            sc.nextLine(); // Limpiar buffer por error
                        } catch (ExceptionEdad ex) {
                            System.out.println(ex.getMessage());
                            registrarError("Intento de entrada menor de edad: " + nombre);
                        }
                        break;
                    case 2:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el menú.");
                sc.nextLine(); // Limpiar buffer
            }
        } while (resp != 2);
    }

    private static void registrarEntrada(Persona p) {
        // Usamos try-with-resources para que el archivo se cierre automáticamente
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt", true))) {
            bw.write(p.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en input.txt: " + e.getMessage());
        }
    }

    private static void registrarError(String mensaje) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true))) {
            bw.write("LOG ERROR: " + mensaje);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en log.txt: " + e.getMessage());
        }
    }
}