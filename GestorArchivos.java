import java.io.*;
import java.util.ArrayList;

public class GestorArchivos {
    // Verifica si la carpeta db existe, si no la crea
    public static void verificarCarpeta() {
        File carpeta = new File("db");
        if (!carpeta.exists()) {
            carpeta.mkdir();
            System.out.println("Carpeta 'db' creada.");
        }
    }

    // Verifica si un archivo existe, si no lo crea vacío
    public static void verificarArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear archivo: " + e.getMessage());
            }
        }
    }

    // Lee todas las líneas de un archivo CSV
    public static ArrayList<String> leerArchivo(String ruta) {
        ArrayList<String> lineas = new ArrayList<>();
        BufferedReader reader = null;
        try {
            verificarArchivo(ruta);
            reader = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
        return lineas;
    }

    // Escribe todas las líneas en un archivo CSV (sobreescribe)
    public static void escribirArchivo(String ruta, ArrayList<String> lineas) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(ruta));
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
    }

    // Agrega una línea al final de un archivo CSV
    public static void agregarLinea(String ruta, String linea) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(ruta, true)); // true = append
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
    }
}