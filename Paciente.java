public class Paciente {
    // ATRIBUTOS
    private String id;
    private String nombreCompleto;

    // CONSTRUCTOR
    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    // SETTER
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // MOSTRARINFO
    public void mostrarInfo() {
        System.out.println("===== Información del Paciente =====");
        System.out.println("ID:      " + id);
        System.out.println("Nombre:  " + nombreCompleto);
        System.out.println("====================================");
    }
}
