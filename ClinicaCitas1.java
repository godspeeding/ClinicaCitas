package proyecto;

import java.util.Scanner;

public class ClinicaCitas1 {

    public static void main(String[] args) {

        final int maxCitas = 10;

        String[] paciente = {"Ana", "Luis", "Maria", "Pedro", "Sofia","Carlos", "Elena", "Diego", "Paula", "Juan"};

        String[] codigo = {"C01","C02","C03","C04","C05","C06","C07","C08","C09","C10"};

        double[] precio = {20, 25, 30, 15, 18,22, 28, 26, 24, 19};

        int[] agendadas = {1, 6, 0, 3, 1, 0, 7, 2, 0, 5};

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- SISTEMA DE CITAS CLINICA ---");
            System.out.println("1. Mostrar pacientes");
            System.out.println("2. Buscar paciente");
            System.out.println("3. Agendar / Desagendar cita");
            System.out.println("4. Ordenar citas");
            System.out.println("5. Reporte general (recursivo)");
            System.out.println("6. Salir");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                // Se creó la estructura básica del menú
                // Se inicializaron los arreglos con datos de pacientes
                // Switch preparado para implementar funcionalidades

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);
    }
}
