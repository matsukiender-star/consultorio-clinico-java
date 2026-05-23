public class Cita {
    // ATRIBUTOS
    private String id;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;       // Una cita tiene UN doctor asociado
    private Paciente paciente;   // Una cita tiene UN paciente asociado

    // CONSTRUCTOR
    public Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    // =GETTERS
    public String getId() {
        return id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    // SETTERS
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    // =MOSTRARINFO
    public void mostrarInfo() {
        System.out.println("========== Información de la Cita ==========");
        System.out.println("ID Cita:       " + id);
        System.out.println("Fecha y hora:  " + fechaHora);
        System.out.println("Motivo:        " + motivo);
        System.out.println("--- Doctor asignado ---");
        System.out.println("  Nombre:        " + doctor.getNombreCompleto());
        System.out.println("  Especialidad:  " + doctor.getEspecialidad());
        System.out.println("--- Paciente ---");
        System.out.println("  Nombre:        " + paciente.getNombreCompleto());
        System.out.println("=============================================");
    }
}

