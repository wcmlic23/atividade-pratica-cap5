import java.util.List;

public class RepositorioMySQL implements RepositorioNotas {
    @Override
    public void salvar(String matricula, List<Double> notas) {
        // Simulação: aqui abriria a conexão e gravaria no MySQL.
        System.out.println("[MySQL] Notas " + notas + " salvas para a matrícula " + matricula);
    }
}
