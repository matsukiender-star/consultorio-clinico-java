import java.util.ArrayList;

public class Doctor {
    // ATRIBUTOS
    private String id;               // Identificador único del doctor (ej: "D001")
    private String nombreCompleto;   // Nombre completo del doctor
    private String especialidad;     // Especialidad médica (ej: "Cardiología")
    private static final String ARCHIVO = "db/doctores.csv"; // Ruta del archivo donde se guardan

    // CONSTRUCTOR
    // Inicializa un objeto Doctor con sus datos básicos
    public Doctor(String id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    // GETTERS
    // Métodos para obtener los valores de los atributos privados
    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEspecialidad() { return especialidad; }

    //toCSV - Convierte el objeto Doctor a una línea de texto CSV.
    //Formato: "id,nombreCompleto,especialidad"
    //Se usa para guardar el doctor en el archivo de texto.

    public String toCSV() {
        return id + "," + nombreCompleto + "," + especialidad;
    }

    //fromCSV Metodo estático que crea un objeto Doctor a partir de una línea CSV.
    //Recibe una línea como "D001,Dr. Carlos López,Cardiología"
    //La separa por comas con split(",") y crea el objeto con esos datos.

    public static Doctor fromCSV(String linea) {
        String[] datos = linea.split(",");
        return new Doctor(datos[0], datos[1], datos[2]);
    }

    //mostrarInfo - Imprime los datos del doctor en consola con formato legible
    public void mostrarInfo() {
        System.out.println("  ID:            " + id);
        System.out.println("  Nombre:        " + nombreCompleto);
        System.out.println("  Especialidad:  " + especialidad);
    }

    //registrar - Guarda un nuevo doctor en el archivo CSV.
    //Usa GestorArchivos.agregarLinea() para añadir la línea al final del archivo.
    //Es estático porque no necesita una instancia previa para funcionar.

    public static void registrar(Doctor doctor) {
        GestorArchivos.agregarLinea(ARCHIVO, doctor.toCSV());
        System.out.println("Doctor registrado exitosamente.");
    }

    //obtenerTodos - Lee el archivo CSV y devuelve un ArrayList con todos los doctores.
    //Recorre cada línea del archivo y la convierte a un objeto Doctor con fromCSV().
    //Usa try-catch por si alguna línea tiene formato incorrecto.

    public static ArrayList<Doctor> obtenerTodos() {
        ArrayList<Doctor> doctores = new ArrayList<>();
        ArrayList<String> lineas = GestorArchivos.leerArchivo(ARCHIVO);
        for (String linea : lineas) {
            try {
                doctores.add(Doctor.fromCSV(linea));
            } catch (Exception e) {
                System.out.println("Error al leer doctor: " + e.getMessage());
            }
        }
        return doctores;
    }

    //buscarPorId - Busca un doctor específico por su ID.
    //Recorre la lista de doctores y compara el ID de cada uno.
    //Retorna el Doctor si lo encuentra, o null si no existe.

    public static Doctor buscarPorId(String id) {
        ArrayList<Doctor> doctores = obtenerTodos();
        for (Doctor doc : doctores) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }
}