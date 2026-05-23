public class Doctor {
    // ATRIBUTOS
    private String id;
    private String nombreCompleto;
    private String especialidad;

    // CONSTRUCTOR
    public Doctor(String id, String nombreCompleto, String especialidad) {
        this.id = id;                           // "this" es como "self" en Python
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // SETTERS
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // MOSTRARINFO
    public void mostrarInfo() {
        System.out.println("===== Información del Doctor =====");
        System.out.println("ID:            " + id);
        System.out.println("Nombre:        " + nombreCompleto);
        System.out.println("Especialidad:  " + especialidad);
        System.out.println("==================================");
    }
}