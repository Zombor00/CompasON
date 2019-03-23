package excepciones;

public class Excepcion extends Exception {

    private final String descripcion;

    public Excepcion(String description) {
        this.descripcion = description;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}