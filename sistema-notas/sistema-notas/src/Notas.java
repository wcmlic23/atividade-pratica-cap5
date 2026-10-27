import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsável apenas pelas regras das notas: validar, armazenar e calcular a média.
 */
public class Notas {

    private final List<Double> notas = new ArrayList<>();

    public void adicionar(double nota) {
        if (!Double.isFinite(nota) || nota < 0 || nota > 10) {
            throw new IllegalArgumentException(
                "Nota inválida: " + nota + ". Informe um valor entre 0 e 10.");
        }
        notas.add(nota);
    }

    /** Retorna uma visão somente leitura: não permite modificar a lista interna. */
    public List<Double> obterNotas() {
        return Collections.unmodifiableList(notas);
    }

    public double calcularMedia() {
        return notas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }
}
