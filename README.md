# Consultorio Clínico - Sistema de Administración de Citas

Sistema de administración de citas para un consultorio clínico desarrollado en Java 11 como proyecto final del curso de Computación en Java en Universidad Tecmilenio.

## Instalación y Configuración

### Requisitos
- Java SE Development Kit (JDK) 11 o superior
- Git (opcional, para clonar el repositorio)

### Pasos de instalación
1. Clonar el repositorio:git clone https://github.com/matsukiender-star/consultorio-clinico-java.git
2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Java.
3. Verificar que el JDK 11 esté configurado como SDK del proyecto.
4. Compilar y ejecutar la clase `Main.java`.

El programa creará automáticamente la carpeta `db/` y los archivos CSV necesarios en la primera ejecución.

## Uso del Programa

### Inicio de sesión
Al iniciar el programa se solicita usuario y contraseña. Las credenciales por defecto son:
- **Usuario:** admin
- **Contraseña:** admin123

### Funcionalidades
1. **Dar de alta doctor:** Registra un nuevo doctor con ID, nombre y especialidad.
2. **Dar de alta paciente:** Registra un nuevo paciente con ID y nombre.
3. **Crear cita:** Crea una cita seleccionando doctor, paciente, fecha/hora y motivo.
4. **Ver citas programadas:** Muestra todas las citas con la información del doctor y paciente.
5. **Ver doctores registrados:** Lista todos los doctores del consultorio.
6. **Ver pacientes registrados:** Lista todos los pacientes registrados.

### Almacenamiento
Los datos se almacenan en archivos CSV dentro de la carpeta `db/`:
- `doctores.csv` - Registro de doctores
- `pacientes.csv` - Registro de pacientes
- `citas.csv` - Registro de citas
- `administradores.csv` - Usuarios del sistema

## Créditos

- **Desarrollador:** Angel Gabriel Carrizales Trejo
- **Materia:** Computación en Java
- **Profesor:** Jesús Cazares Martínez
- **Universidad:** Tecmilenio

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
