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
            System.out.println("5. Reporte general");
            System.out.println("6. Clasificar pacientes");
            System.out.println("7. Pacientes con alta demanda");
            System.out.println("8. Salir");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                // Se implementaron las funciones de clasificación y filtro de alta demanda

                case 1:
                    mostrarCitas(paciente, codigo, precio, agendadas);
                    break;

                case 2:
                    System.out.print("Ingrese codigo o nombre: ");
                    String busqueda = sc.nextLine();
                    buscarPaciente(paciente, codigo, agendadas, busqueda);
                    break;

                case 3:
                    System.out.println("1. Agendar");
                    System.out.println("2. Desagendar");
                    int sub = Integer.parseInt(sc.nextLine());

                    System.out.print("Ingrese codigo del paciente: ");
                    String cod = sc.nextLine();

                    if (sub == 1) {
                        agendadas = agendarCita(codigo, agendadas, cod, maxCitas);
                    } else if (sub == 2) {
                        agendadas = desagendarCita(codigo, agendadas, cod);
                    }
                    break;

                case 4:
                    agendadas = ordenarCitas(paciente, codigo, precio, agendadas);
                    System.out.println("\nCitas ordenadas:");
                    mostrarCitas(paciente, codigo, precio, agendadas);
                    break;

                case 5:
                    int total = reporte(agendadas, 0);
                    System.out.println("Total de citas agendadas: " + total);
                    System.out.println("Capacidad maxima: " + (maxCitas * agendadas.length));
                    break;

                case 6:
                    clasificarPacientes(paciente, agendadas);
                    break;

                case 7:
                    pacientesAltaDemanda(paciente, agendadas);
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 8);
    }

    // Muestra la lista completa de pacientes con sus datos
    // Necesita: arreglos de pacientes, códigos, precios y citas agendadas
    static void mostrarCitas(String[] paciente, String[] codigo, double[] precio, int[] agendadas) {
        for (int i = 0; i < paciente.length; i++) {
            System.out.println(codigo[i] + " | " +paciente[i] + " | Precio: $" +precio[i] + " | Citas: " +agendadas[i]);
        }
    }

    // Busca un paciente por código o nombre y muestra sus citas
    // Necesita: arreglos de pacientes, códigos, citas agendadas y texto de búsqueda
    static void buscarPaciente(String[] paciente, String[] codigo, int[] agendadas, String busqueda) {
        boolean encontrado = false;

        for (int i = 0; i < paciente.length; i++) {
            if (codigo[i].equalsIgnoreCase(busqueda) ||paciente[i].equalsIgnoreCase(busqueda)) {

                System.out.println(paciente[i] + " tiene " + agendadas[i] + " citas");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Paciente no encontrado");
        }
    }

    // Incrementa el número de citas de un paciente
    // Necesita: arreglo de códigos, citas agendadas, código del paciente y máximo de citas
    static int[] agendarCita(String[] codigo, int[] agendadas, String cod, int maxCitas) {
        for (int i = 0; i < codigo.length; i++) {
            if (codigo[i].equalsIgnoreCase(cod)) {
                if (agendadas[i] < maxCitas) {
                    agendadas[i]++;
                } else {
                    System.out.println("Maximo de citas alcanzado");
                }
            }
        }
        return agendadas;
    }

    // Disminuye el número de citas de un paciente
    // Necesita: arreglo de códigos, citas agendadas y código del paciente
    static int[] desagendarCita(String[] codigo, int[] agendadas, String cod) {
        for (int i = 0; i < codigo.length; i++) {
            if (codigo[i].equalsIgnoreCase(cod)) {
                if (agendadas[i] > 0) {
                    agendadas[i]--;
                } else {
                    System.out.println("No se puede bajar de 0");
                }
            }
        }
        return agendadas;
    }

    // Ordena las citas de menor a mayor usando burbuja
    // Necesita: arreglos de pacientes, códigos, precios y citas agendadas
    static int[] ordenarCitas(String[] paciente, String[] codigo, double[] precio, int[] agendadas) {
        for (int i = 0; i < agendadas.length - 1; i++) {
            for (int j = 0; j < agendadas.length - 1; j++) {
                if (agendadas[j] > agendadas[j + 1]) {

                    int temp = agendadas[j];
                    agendadas[j] = agendadas[j + 1];
                    agendadas[j + 1] = temp;

                    String aux = paciente[j];
                    paciente[j] = paciente[j + 1];
                    paciente[j + 1] = aux;

                    aux = codigo[j];
                    codigo[j] = codigo[j + 1];
                    codigo[j + 1] = aux;

                    double p = precio[j];
                    precio[j] = precio[j + 1];
                    precio[j + 1] = p;
                }
            }
        }
        return agendadas;
    }

    // Calcula el total de citas usando recursividad
    // Necesita: arreglo de citas agendadas y posición inicial
    static int reporte(int[] agendadas, int i) {
        if (i == agendadas.length) {
            return 0;
        }
        return agendadas[i] + reporte(agendadas, i + 1);
    }

    // Clasifica pacientes según su cantidad de citas
    // Necesita: arreglos de pacientes y citas agendadas
    static void clasificarPacientes(String[] paciente, int[] agendadas) {
        for (int i = 0; i < paciente.length; i++) {
            if (agendadas[i] == 0) {
                System.out.println(paciente[i] + " -> Sin citas");
            } else if (agendadas[i] == 1) {
                System.out.println(paciente[i] + " -> Paciente espontaneo");
            } else {
                System.out.println(paciente[i] + " -> Paciente recurrentes");
            }
        }
    }

    // Muestra pacientes con más de 5 citas agendadas
    // Necesita: arreglos de pacientes y citas agendadas
    static void pacientesAltaDemanda(String[] paciente, int[] agendadas) {
        System.out.println("\n--- PACIENTES CON ALTA DEMANDA ---");
        boolean hay = false;

        for (int i = 0; i < paciente.length; i++) {
            if (agendadas[i] > 5) {
                System.out.println(paciente[i] + " -> " + agendadas[i] + " citas");
                hay = true;
            }
        }

        if (!hay) {
            System.out.println("No existen pacientes con alta demanda");
        }
    }
}

