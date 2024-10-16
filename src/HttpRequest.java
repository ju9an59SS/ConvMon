import java.util.Scanner;

public class HttpRequest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su API key: ");
        String apiKey = scanner.nextLine();

        HttpCliente apiCliente = new HttpCliente(apiKey);

        while (true) {
            System.out.print("Ingrese la moneda base (ej. USD): ");
            String monedaBase = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese la moneda a convertir (ej. EUR o COP): ");
            String monedaObjetivo = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese el monto a convertir: ");
            double cantidad = scanner.nextDouble();

            double tasaDeCambio = apiCliente.obtenerTasaDeCambio(monedaBase, monedaObjetivo);
            if (tasaDeCambio > 0) {
                double cantidadConvertida = cantidad * tasaDeCambio;
                System.out.printf("%.2f %s equivale a %.2f %s%n", cantidad, monedaBase, cantidadConvertida, monedaObjetivo);
            } else {
                System.out.println("Error al obtener la tasa de cambio.");
            }

            System.out.print("¿Desea hacer otra conversión? (s/n): ");
            scanner.nextLine();
            String otraConversion = scanner.nextLine();
            if (!otraConversion.equalsIgnoreCase("s")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Gracias por usar el conversor de moneda. ¡Hasta luego!");
    }
}

