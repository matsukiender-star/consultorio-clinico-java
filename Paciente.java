import java.util.ArrayList;

public class Paciente {
    // ATRIBUTOS
    private String id;               // Identificador único del paciente (ej: "P001")
    private String nombreCompleto;   // Nombre completo del paciente
    private static final String ARCHIVO = "db/pacientes.csv"; // Ruta del archivo CSV

    // CONSTRUCTOR
    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    // GETTERS
    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }

    //toCSV - Convierte el paciente a formato CSV: "id,nombreCompleto"

    public String toCSV() {
        return id + "," + nombreCompleto;
    }

    //fromCSV - Crea un Paciente desde una línea CSV leída del archivo.
    //Separa la línea por comas y usa cada parte como parámetro.

    public static Paciente fromCSV(String linea) {
        String[] datos = linea.split(",");
        return new Paciente(datos[0], datos[1]);
    }

    //mostrarInfo - Imprime los datos del paciente en consola.

    public void mostrarInfo() {
        System.out.println("  ID:      " + id);
        System.out.println("  Nombre:  " + nombreCompleto);
    }

    //registrar - Guarda un nuevo paciente al final del archivo CSV.

    public static void registrar(Paciente paciente) {
        GestorArchivos.agregarLinea(ARCHIVO, paciente.toCSV());
        System.out.println("Paciente registrado exitosamente.");
    }

    //obtenerTodos - Lee el archivo CSV y devuelve todos los pacientes como ArrayList.
    //Convierte cada línea del archivo a un objeto Paciente.

    public static ArrayList<Paciente> obtenerTodos() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<String> lineas = GestorArchivos.leerArchivo(ARCHIVO);
        for (String linea : lineas) {
            try {
                pacientes.add(Paciente.fromCSV(linea));
            } catch (Exception e) {
                System.out.println("Error al leer paciente: " + e.getMessage());
            }
        }
        return pacientes;
    }

    //buscarPorId - Busca un paciente por su ID en el archivo.
    //Retorna el Paciente encontrado o null si no existe.

    public static Paciente buscarPorId(String id) {
        ArrayList<Paciente> pacientes = obtenerTodos();
        for (Paciente pac : pacientes) {
            if (pac.getId().equals(id)) {
                return pac;
            }
        }
        return null;
    }
}
