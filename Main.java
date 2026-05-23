//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Administración de Citas - Consultorio Clínico ===\n");

        // --- Crear doctores ---
        Doctor doc1 = new Doctor("D001", "Dr. Carlos López", "Cardiología");
        Doctor doc2 = new Doctor("D002", "Dra. María Fernández", "Pediatría");

        // --- Crear pacientes ---
        Paciente pac1 = new Paciente("P001", "Juan Pérez García");
        Paciente pac2 = new Paciente("P002", "Ana Martínez Ruiz");

        // --- Crear citas relacionando doctor y paciente ---
        Cita cita1 = new Cita("C001", "22/05/2026 10:00", "Revisión general", doc1, pac1);
        Cita cita2 = new Cita("C002", "23/05/2026 14:30", "Consulta pediátrica", doc2, pac2);

        // --- Mostrar información de los doctores ---
        System.out.println(">>> DOCTORES REGISTRADOS <<<\n");
        doc1.mostrarInfo();
        System.out.println();
        doc2.mostrarInfo();

        // --- Mostrar información de los pacientes ---
        System.out.println("\n>>> PACIENTES REGISTRADOS <<<\n");
        pac1.mostrarInfo();
        System.out.println();
        pac2.mostrarInfo();

        // --- Mostrar información de las citas ---
        System.out.println("\n>>> CITAS PROGRAMADAS <<<\n");
        cita1.mostrarInfo();
        System.out.println();
        cita2.mostrarInfo();

        // --- Demostrar uso de setters (modificar datos) ---
        System.out.println("\n>>> ACTUALIZANDO DATOS <<<\n");
        pac1.setNombreCompleto("Juan Pérez López");
        System.out.println("Nombre del paciente P001 actualizado.");
        pac1.mostrarInfo();

        System.out.println("\n=== Fin del programa ===");
    }
}