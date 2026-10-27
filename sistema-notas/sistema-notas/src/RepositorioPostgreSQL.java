import java.util.List;

/** Segunda implementação, usada para demonstrar a troca de banco de dados. */
public class RepositorioPostgreSQL implements RepositorioNotas {
    @Override
    public void salvar(String matricula, List<Double> notas) {
        System.out.println("[PostgreSQL] Notas " + notas + " salvas para a matrícula " + matricula);
    }
}
