import java.util.List;

/** Responsável apenas pelo envio de e-mails. */
public class ServicoEmail {
    public void enviarNotas(String destinatario, List<Double> notas) {
        // Simulação: aqui conectaria ao servidor de e-mail.
        System.out.println("[E-mail] Notas " + notas + " enviadas para " + destinatario);
    }
}
