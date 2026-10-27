import java.util.List;

/**
 * Coordena as operações. Recebe o repositório pela interface (inversão de dependências),
 * por isso não sabe qual banco de dados está sendo usado.
 */
public class ServicoNotas {

    private final Notas notas = new Notas();
    private final RepositorioNotas repositorio;
    private final ServicoEmail servicoEmail;

    public ServicoNotas(RepositorioNotas repositorio, ServicoEmail servicoEmail) {
        this.repositorio = repositorio;
        this.servicoEmail = servicoEmail;
    }

    public void adicionarNota(double nota) {
        notas.adicionar(nota);
    }

    public List<Double> obterNotas() {
        return notas.obterNotas();
    }

    public double calcularMedia() {
        return notas.calcularMedia();
    }

    public void salvar(String matricula) {
        repositorio.salvar(matricula, notas.obterNotas());
    }

    public void enviarEmail(String destinatario) {
        servicoEmail.enviarNotas(destinatario, notas.obterNotas());
    }
}
