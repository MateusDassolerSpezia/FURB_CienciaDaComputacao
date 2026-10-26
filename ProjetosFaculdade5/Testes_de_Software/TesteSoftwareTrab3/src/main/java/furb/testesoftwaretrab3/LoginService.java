package furb.testesoftwaretrab3;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas De Antoni, Mateus Spezia, Gustavo Baum e Pablo Sautner 
 */

// CÓDIGO PARA EXECUÇÃO DA PARTE 2 DO TRABALHO
/*public class LoginService {

    public String login(String email, String senha) {
        return "";
    }

    public String recuperarSenha(String email) {
        return "";
    }
}*/

public class LoginService {

    private static final String EMAIL_VALIDO = "teste@gmail.com";
    private static final String SENHA_VALIDA = "teste123";
    private static final int LIMITE_TENTATIVAS = 5;

    private Map<String, Integer> tentativasFalhas = new HashMap<>();

    public String login(String email, String senha) {
        int tentativas = tentativasFalhas.getOrDefault(email, 0);

        if (tentativas >= LIMITE_TENTATIVAS) {
            return "Conta bloqueada temporariamente por segurança. Tente novamente em 15 minutos";
        }

        if (EMAIL_VALIDO.equals(email) && SENHA_VALIDA.equals(senha)) {
            tentativasFalhas.put(email, 0); 
            return "Login realizado com sucesso";
        } else {
            // Incrementa o contador de erros
            tentativasFalhas.put(email, tentativas + 1);
            return "Email e/ou senha informados estão incorretos";
        }
    }

    public String recuperarSenha(String email) {
        return "Email de recuperação de senha enviado";
    }
}
