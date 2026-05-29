import java.util.ArrayList;

public class Cita {
    // === ATRIBUTOS ===
    private String id;           // Identificador único de la cita (ej: "C001")
    private String fechaHora;    // Fecha y hora de la cita (ej: "29/05/2026 10:00")
    private String motivo;       // Razón de la consulta (ej: "Revisión general")
    private String idDoctor;     // ID del doctor asignado (referencia al archivo de doctores)
    private String idPaciente;   // ID del paciente asignado (referencia al archivo de pacientes)
    private static final String ARCHIVO = "db/citas.csv"; // Ruta del archivo CSV

    // CONSTRUCTOR
    // Recibe los IDs del doctor y paciente en lugar de objetos completos
    public Cita(String id, String fechaHora, String motivo, String idDoctor, String idPaciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    // === GETTERS ===
    public String getId() { return id; }
    public String getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public String getIdDoctor() { return idDoctor; }
    public String getIdPaciente() { return idPaciente; }

    //toCSV - Convierte la cita a formato CSV.
    //Formato: "id,fechaHora,motivo,idDoctor,idPaciente"

    public String toCSV() {
        return id + "," + fechaHora + "," + motivo + "," + idDoctor + "," + idPaciente;
    }

    //fromCSV - Crea una Cita desde una línea CSV.
    //Separa los 5 campos por comas y crea el objeto.

    public static Cita fromCSV(String linea) {
        String[] datos = linea.split(",");
        return new Cita(datos[0], datos[1], datos[2], datos[3], datos[4]);
    }

    //mostrarInfo - Imprime la información completa de la cita.
    //Busca el doctor y paciente por sus IDs para mostrar sus nombres en lugar de solo los IDs. Si no los encuentra, muestra el ID.

    public void mostrarInfo() {
        System.out.println("  ID Cita:       " + id);
        System.out.println("  Fecha y hora:  " + fechaHora);
        System.out.println("  Motivo:        " + motivo);

        // Buscar el doctor por su ID para mostrar su nombre
        Doctor doctor = Doctor.buscarPorId(idDoctor);
        if (doctor != null) {
            System.out.println("  Doctor:        " + doctor.getNombreCompleto() + " (" + doctor.getEspecialidad() + ")");
        } else {
            System.out.println("  Doctor ID:     " + idDoctor);
        }

        // Buscar el paciente por su ID para mostrar su nombre
        Paciente paciente = Paciente.buscarPorId(idPaciente);
        if (paciente != null) {
            System.out.println("  Paciente:      " + paciente.getNombreCompleto());
        } else {
            System.out.println("  Paciente ID:   " + idPaciente);
        }
    }

    //registrar - Guarda una nueva cita en el archivo CSV.

    public static void registrar(Cita cita) {
        GestorArchivos.agregarLinea(ARCHIVO, cita.toCSV());
        System.out.println("Cita creada exitosamente.");
    }

    //obtenerTodas - Lee el archivo CSV y devuelve todas las citas.
    //Usa try-catch por si alguna línea tiene datos incompletos.

    public static ArrayList<Cita> obtenerTodas() {
        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<String> lineas = GestorArchivos.leerArchivo(ARCHIVO);
        for (String linea : lineas) {
            try {
                citas.add(Cita.fromCSV(linea));
            } catch (Exception e) {
                System.out.println("Error al leer cita: " + e.getMessage());
            }
        }
        return citas;
    }
}