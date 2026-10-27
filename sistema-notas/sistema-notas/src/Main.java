public class Main {
    public static void main(String[] args) {
        ServicoEmail email = new ServicoEmail();

        // 1. Notas válidas e média
        System.out.println("=== 1. Adicionando notas ===");
        ServicoNotas servico = new ServicoNotas(new RepositorioMySQL(), email);
        servico.adicionarNota(8.0);
        servico.adicionarNota(9.0);
        servico.adicionarNota(7.0);
        System.out.println("Notas: " + servico.obterNotas());
        System.out.println("Média: " + servico.calcularMedia());

        // 2. Nota inválida
        System.out.println("\n=== 2. Tentando adicionar 12.0 ===");
        try {
            servico.adicionarNota(12.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro tratado: " + e.getMessage());
        }
        System.out.println("Notas após a tentativa: " + servico.obterNotas()
                + " (total: " + servico.obterNotas().size() + ")");

        // Consulta não permite alterar a coleção interna
        try {
            servico.obterNotas().add(10.0);
        } catch (UnsupportedOperationException e) {
            System.out.println("Lista retornada é somente leitura (modificação bloqueada).");
        }

        // 3. Persistência e e-mail
        System.out.println("\n=== 3. Persistência e e-mail (MySQL) ===");
        servico.salvar("2024001");
        servico.enviarEmail("aluno@universidade.edu");

        // 4. Troca do repositório sem alterar ServicoNotas
        System.out.println("\n=== 4. Trocando MySQL por PostgreSQL ===");
        ServicoNotas outroServico = new ServicoNotas(new RepositorioPostgreSQL(), email);
        outroServico.adicionarNota(8.0);
        outroServico.adicionarNota(9.0);
        outroServico.adicionarNota(7.0);
        outroServico.salvar("2024001");
        outroServico.enviarEmail("aluno@universidade.edu");
    }
}
