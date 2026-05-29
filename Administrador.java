import java.util.ArrayList;

public class Administrador {
    private String usuario;
    private String contrasena;
    private static final String ARCHIVO = "db/administradores.csv";

    public Administrador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() { return usuario; }

    public String toCSV() {
        return usuario + "," + contrasena;
    }

    // Valida si el usuario y contraseña existen en el archivo
    public static boolean validarCredenciales(String usuario, String contrasena) {
        ArrayList<String> lineas = GestorArchivos.leerArchivo(ARCHIVO);
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            if (datos.length == 2 && datos[0].equals(usuario) && datos[1].equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    // Crea un administrador por defecto si el archivo está vacío
    public static void crearAdminPorDefecto() {
        ArrayList<String> lineas = GestorArchivos.leerArchivo(ARCHIVO);
        if (lineas.isEmpty()) {
            GestorArchivos.agregarLinea(ARCHIVO, "admin,admin123");
            System.out.println("Administrador por defecto creado (usuario: admin, contraseña: admin123)");
        }
    }
}