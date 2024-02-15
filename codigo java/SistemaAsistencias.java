import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaAsistencias {
    private List<Asignatura> asignaturas;

    public SistemaAsistencias() {
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    public void tomarAsistencia(String nombreProfesor, String nombreAsignatura, String paralelo, String periodoAcademico, List<Estudiante> estudiantesAsistieron, List<Estudiante> estudiantesFaltaron) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getNombre().equals(nombreAsignatura) && asignatura.getProfesor().getNombre().equals(nombreProfesor) &&
                    asignatura.getParalelo().equals(paralelo) && asignatura.getPeriodoAcademico().equals(periodoAcademico)) {
                // Registra la asistencia para los estudiantes que asistieron
                for (Estudiante estudiante : estudiantesAsistieron) {
                    asignatura.registrarAsistencia(estudiante);
                }
                // Registra las faltas para los estudiantes que faltaron
                for (Estudiante estudiante : estudiantesFaltaron) {
                    asignatura.registrarFalta(estudiante);
                }
                System.out.println("Asistencias registradas exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró la asignatura especificada.");
    }

    public static void main(String[] args) {
        SistemaAsistencias sistema = new SistemaAsistencias();
        try (Scanner scanner = new Scanner(System.in)) {
            // Ingreso del nombre del profesor
            System.out.println("Sistema de registro de asistencia");
            System.out.println("Ingrese su nombre profesor: ");
            String nombreProfesor = scanner.nextLine();

            // Ingreso de datos de la asignatura
            System.out.println("Ingrese su asignatura:");
            String nombreAsignatura = scanner.nextLine();
            System.out.println("Ingrese su paralelo:");
            String paralelo = scanner.nextLine();
            System.out.println("Ingrese el periodo académico:");
            String periodoAcademico = scanner.nextLine();

            // Creación de la asignatura y adición al sistema
            Profesor profesor = new Profesor(nombreProfesor);
            Asignatura asignatura = new Asignatura(nombreAsignatura, profesor, paralelo, periodoAcademico);
            sistema.agregarAsignatura(asignatura);

            // Ingreso de datos de los estudiantes que asistieron
            System.out.println("Ingrese los nombres de los estudiantes que asistieron (separados por coma):");
            String nombresEstudiantesAsistieronInput = scanner.nextLine();
            String[] nombresEstudiantesAsistieron = nombresEstudiantesAsistieronInput.split(",");
            List<Estudiante> estudiantesAsistieron = new ArrayList<>();
            for (String nombreEstudiante : nombresEstudiantesAsistieron) {
                estudiantesAsistieron.add(new Estudiante(nombreEstudiante.trim()));
            }

            // Ingreso de datos de los estudiantes que faltaron
            System.out.println("Ingrese los nombres de los estudiantes que faltaron (separados por coma):");
            String nombresEstudiantesFaltaronInput = scanner.nextLine();
            String[] nombresEstudiantesFaltaron = nombresEstudiantesFaltaronInput.split(",");
            List<Estudiante> estudiantesFaltaron = new ArrayList<>();
            for (String nombreEstudiante : nombresEstudiantesFaltaron) {
                estudiantesFaltaron.add(new Estudiante(nombreEstudiante.trim()));
            }

            // Tomar asistencia
            sistema.tomarAsistencia(nombreProfesor, nombreAsignatura, paralelo, periodoAcademico, estudiantesAsistieron, estudiantesFaltaron);

            // Mostrar lista de asistencias
            asignatura.mostrarAsistencias();
        }
    }
}

class Asignatura {
    private String nombre;
    private Profesor profesor;
    private String paralelo;
    private String periodoAcademico;
    private List<Estudiante> estudiantesAsistieron;
    private List<Estudiante> estudiantesFaltaron;

    public Asignatura(String nombre, Profesor profesor, String paralelo, String periodoAcademico) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.paralelo = paralelo;
        this.periodoAcademico = periodoAcademico;
        this.estudiantesAsistieron = new ArrayList<>();
        this.estudiantesFaltaron = new ArrayList<>();
    }

    public void registrarAsistencia(Estudiante estudiante) {
        estudiantesAsistieron.add(estudiante);
    }

    public void registrarFalta(Estudiante estudiante) {
        estudiantesFaltaron.add(estudiante);
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getParalelo() {
        return paralelo;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void mostrarAsistencias() {
        System.out.println("\nLista de asistencias:");
        System.out.println("Estudiantes que asistieron:");
        for (Estudiante estudiante : estudiantesAsistieron) {
            System.out.println("- " + estudiante.getNombre());
        }
        System.out.println("Estudiantes que faltaron:");
        for (Estudiante estudiante : estudiantesFaltaron) {
            System.out.println("- " + estudiante.getNombre());
        }
        System.out.println("Total de estudiantes que faltaron: " + (estudiantesFaltaron.size()));
        System.out.println("Total de estudiantes que Asistieron " + (estudiantesAsistieron.size()));
        System.out.println("Total de estudiantes : " + (estudiantesAsistieron.size() + estudiantesFaltaron.size()));
    }
}

class Profesor {
    private String nombre;

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Estudiante {
    private String nombre;

    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
