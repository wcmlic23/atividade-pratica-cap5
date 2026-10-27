import java.util.List;

/**
 * Abstração de persistência. O serviço depende desta interface,
 * e não de uma tecnologia de banco de dados específica.
 */
public interface RepositorioNotas {
    void salvar(String matricula, List<Double> notas);
}
