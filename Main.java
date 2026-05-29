import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Scanner como atributo estático para leer entrada del usuario en todo el programa
    private static Scanner scanner = new Scanner(System.in);

    //main - Metodo principal que ejecuta el programa.
    //1. Verifica que existan los archivos necesarios
    //2. Realiza el proceso de login
    //3. Muestra el menú principal en un ciclo do-while

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Sistema de Administración de Citas");
        System.out.println("  Consultorio Clínico");
        System.out.println("=============================================\n");

        // Verificar que existan la carpeta db/ y los archivos CSV
        // Si no existen, se crean automáticamente (vacíos)
        GestorArchivos.verificarCarpeta();
        GestorArchivos.verificarArchivo("db/doctores.csv");
        GestorArchivos.verificarArchivo("db/pacientes.csv");
        GestorArchivos.verificarArchivo("db/citas.csv");
        GestorArchivos.verificarArchivo("db/administradores.csv");

        // Crear admin por defecto si no hay ninguno
        Administrador.crearAdminPorDefecto();

        // Proceso de login - si falla 3 veces, cierra el programa
        if (!login()) {
            System.out.println("Demasiados intentos fallidos. Cerrando programa.");
            return; // Termina el programa sin ejecutar el menú
        }

        // Ciclo do-while para mantener el menú activo hasta que elija salir
        int opcion;
        do {
            mostrarMenu();

            // try-catch para manejar si el usuario escribe texto en vez de número
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = 0; // Valor por defecto para que entre al default del switch
            }

            // switch-case para ejecutar la opción seleccionada
            switch (opcion) {
                case 1: altaDoctor(); break;
                case 2: altaPaciente(); break;
                case 3: crearCita(); break;
                case 4: verCitas(); break;
                case 5: verDoctores(); break;
                case 6: verPacientes(); break;
                case 7:
                    System.out.println("\n¡Hasta luego! Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 7);

        scanner.close(); // Cerrar el Scanner al terminar
    }

    //login - Maneja el proceso de autenticación.
    //Permite 3 intentos de inicio de sesión.
    //Retorna true si las credenciales son válidas, false si se agotan los intentos.

    private static boolean login() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.println("--- Inicio de Sesión ---");
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            if (Administrador.validarCredenciales(usuario, contrasena)) {
                System.out.println("¡Bienvenido, " + usuario + "!\n");
                return true;
            } else {
                intentos++;
                System.out.println("Credenciales inválidas. Intentos restantes: " + (3 - intentos) + "\n");
            }
        }
        return false;
    }

    //mostrarMenu - Imprime las opciones del menú principal en consola.

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Dar de alta doctor");
        System.out.println("2. Dar de alta paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Ver citas programadas");
        System.out.println("5. Ver doctores registrados");
        System.out.println("6. Ver pacientes registrados");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    //altaDoctor - Solicita los datos de un nuevo doctor y lo registra.
    //Pide ID, nombre y especialidad, crea el objeto y lo guarda en CSV.

    private static void altaDoctor() {
        System.out.println("\n--- Alta de Doctor ---");
        System.out.print("ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        Doctor.registrar(doctor);
    }

    //altaPaciente - Solicita los datos de un nuevo paciente y lo registra.
    //Pide ID y nombre, crea el objeto y lo guarda en CSV.

    private static void altaPaciente() {
        System.out.println("\n--- Alta de Paciente ---");
        System.out.print("ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        Paciente.registrar(paciente);
    }

    //crearCita - Crea una nueva cita médica.
    //1. Verifica que existan doctores y pacientes registrados
    //2. Solicita los datos de la cita (ID, fecha, motivo)
    //3. Muestra los doctores disponibles y pide seleccionar uno
    //4. Muestra los pacientes disponibles y pide seleccionar uno
    //5. Valida que el doctor y paciente existan antes de crear la cita

    private static void crearCita() {
        System.out.println("\n--- Crear Cita ---");

        // Verificar que existan doctores registrados
        ArrayList<Doctor> doctores = Doctor.obtenerTodos();
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados. Registre uno primero.");
            return;
        }

        // Verificar que existan pacientes registrados
        ArrayList<Paciente> pacientes = Paciente.obtenerTodos();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados. Registre uno primero.");
            return;
        }

        // Solicitar datos de la cita
        System.out.print("ID de la cita: ");
        String id = scanner.nextLine();
        System.out.print("Fecha y hora (DD/MM/AAAA HH:MM): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();

        // Mostrar doctores disponibles para que el usuario elija
        System.out.println("\nDoctores disponibles:");
        for (Doctor doc : doctores) {
            System.out.println("  [" + doc.getId() + "] " + doc.getNombreCompleto() + " - " + doc.getEspecialidad());
        }
        System.out.print("ID del doctor: ");
        String idDoctor = scanner.nextLine();

        // Validar que el doctor existe
        Doctor doctor = Doctor.buscarPorId(idDoctor);
        if (doctor == null) {
            System.out.println("Error: Doctor no encontrado.");
            return;
        }

        // Mostrar pacientes disponibles para que el usuario elija
        System.out.println("\nPacientes disponibles:");
        for (Paciente pac : pacientes) {
            System.out.println("  [" + pac.getId() + "] " + pac.getNombreCompleto());
        }
        System.out.print("ID del paciente: ");
        String idPaciente = scanner.nextLine();

        // Validar que el paciente existe
        Paciente paciente = Paciente.buscarPorId(idPaciente);
        if (paciente == null) {
            System.out.println("Error: Paciente no encontrado.");
            return;
        }

        // Crear la cita y guardarla en el archivo
        Cita cita = new Cita(id, fechaHora, motivo, idDoctor, idPaciente);
        Cita.registrar(cita);
    }

    //verCitas - Muestra todas las citas programadas.
    //Lee el archivo de citas y muestra cada una con los datos del doctor y paciente asociados.

    private static void verCitas() {
        ArrayList<Cita> citas = Cita.obtenerTodas();
        if (citas.isEmpty()) {
            System.out.println("\nNo hay citas programadas.");
            return;
        }
        System.out.println("\n===== Citas Programadas (" + citas.size() + ") =====");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println("\n--- Cita " + (i + 1) + " ---");
            citas.get(i).mostrarInfo();
        }
        System.out.println("\n==========================================");
    }

    //verDoctores - Muestra todos los doctores registrados.

    private static void verDoctores() {
        ArrayList<Doctor> doctores = Doctor.obtenerTodos();
        if (doctores.isEmpty()) {
            System.out.println("\nNo hay doctores registrados.");
            return;
        }
        System.out.println("\n===== Doctores Registrados (" + doctores.size() + ") =====");
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println("\n--- Doctor " + (i + 1) + " ---");
            doctores.get(i).mostrarInfo();
        }
        System.out.println("\n=============================================");
    }

    //verPacientes - Muestra todos los pacientes registrados.

    private static void verPacientes() {
        ArrayList<Paciente> pacientes = Paciente.obtenerTodos();
        if (pacientes.isEmpty()) {
            System.out.println("\nNo hay pacientes registrados.");
            return;
        }
        System.out.println("\n===== Pacientes Registrados (" + pacientes.size() + ") =====");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println("\n--- Paciente " + (i + 1) + " ---");
            pacientes.get(i).mostrarInfo();
        }
        System.out.println("\n===============================================");
    }
}