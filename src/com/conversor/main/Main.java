package com.conversor.main;

import com.conversor.requestApi.ConsultaApi;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    // Enumeración para representar las diferentes monedas
    private enum Currency {
        USD, ARS, BRL, COP
    }

    public static void main(String[] args) {
        // Instanciamos un Scanner para leer entradas del usuario
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Controla el ciclo del programa

        // Bucle principal del programa
        while (running) {
            try {
                // Muestra el menú y obtiene la opción del usuario
                int option = showMenu(scanner);

                // Si la opción es salir (7), finaliza el ciclo
                if (option == 7) {
                    running = false;
                    System.out.println("\n****************************************\n" +
                            "Gracias por utilizar nuestro conversor.\n");
                    break;
                }

                // Obtiene el par de monedas de acuerdo a la opción seleccionada
                String[] currencyPair = getCurrencyPair(option);
                if (currencyPair == null) {
                    System.out.println("La opción ingresada no es válida.\n");
                    continue; // Si la opción no es válida, vuelve a mostrar el menú
                }

                // Solicita al usuario el valor a convertir
                double value = getValue(scanner);

                // Llama a la API para obtener la tasa de conversión
                double conversionRate = ConsultaApi.getConversionRate(currencyPair[0], currencyPair[1]);

                // Calcula el valor final convertido
                double finalValue = value * conversionRate;

                // Imprime el resultado de la conversión
                System.out.printf("%.2f [%s] equivale a %.2f [%s]\n\n", value, currencyPair[0], finalValue, currencyPair[1]);

            } catch (InputMismatchException e) {
                // Maneja la excepción cuando el usuario ingresa un valor inválido
                System.out.println("El valor ingresado debe ser numérico. Intenta de nuevo.\n");
                scanner.next(); // Limpia el buffer de entrada
            }
        }

        // Cierra el scanner una vez que el programa finaliza
        scanner.close();
    }

    // Muestra el menú de opciones y retorna la opción seleccionada por el usuario
    private static int showMenu(Scanner scanner) {
        System.out.print("****************************************\n" +
                "Bienvenido/a al Conversor de Moneda\n\n" +
                "1) Dólar → Peso argentino\n" +
                "2) Peso argentino → Dólar\n" +
                "3) Dólar → Real brasileño\n" +
                "4) Real brasileño → Dólar\n" +
                "5) Dólar → Peso colombiano\n" +
                "6) Peso colombiano → Dólar\n" +
                "7) Salir\n\n" +
                "Elija una opción válida: ");
        return scanner.nextInt();
    }

    // Retorna el par de monedas correspondiente según la opción seleccionada
    private static String[] getCurrencyPair(int option) {
        switch (option) {
            case 1:
                return new String[]{Currency.USD.name(), Currency.ARS.name()};
            case 2:
                return new String[]{Currency.ARS.name(), Currency.USD.name()};
            case 3:
                return new String[]{Currency.USD.name(), Currency.BRL.name()};
            case 4:
                return new String[]{Currency.BRL.name(), Currency.USD.name()};
            case 5:
                return new String[]{Currency.USD.name(), Currency.COP.name()};
            case 6:
                return new String[]{Currency.COP.name(), Currency.USD.name()};
            default:
                return null; // Si la opción no es válida, retorna null
        }
    }

    // Solicita al usuario que ingrese el valor a convertir y lo retorna
    private static double getValue(Scanner scanner) throws InputMismatchException {
        System.out.print("Ingresa el valor que deseas convertir: ");
        return scanner.nextDouble();
    }
}
