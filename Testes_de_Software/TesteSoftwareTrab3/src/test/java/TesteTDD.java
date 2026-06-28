/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import furb.testesoftwaretrab3.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lucas De Antoni, Mateus Spezia, Gustavo Baum e Pablo Sautner 
 */
public class TesteTDD {
    
   private LoginService loginService;

    @BeforeEach
    public void setup() {
        loginService = new LoginService();
    }

    // CENÁRIO 1 - Login com sucesso
    @Test
    public void TesteNormal() {

        String resultado = loginService.login("teste@gmail.com", "teste123");

        assertEquals("Login realizado com sucesso", resultado);
    }

    // CENÁRIO 2 - Login com senha incorreta
    @Test
    public void TesteSenhaIncorreta() {

        String resultado = loginService.login("teste@gmail.com", "invalida123");

        assertEquals("Email e/ou senha informados estão incorretos", resultado);
    }

    // CENÁRIO 3 - Recuperação de senha
    @Test
    public void TesteRecuperaçãoEmail() {

        String resultado = loginService.recuperarSenha("teste@gmail.com");

        assertEquals("Email de recuperação de senha enviado", resultado);
    }

    // CENÁRIO 5 - Bloqueio após 5 tentativas
    @Test
    public void TesteSegurança() {

        // 5 tentativas erradas
        for (int i = 0; i < 5; i++) {
            loginService.login("teste@gmail.com", "senhaErrada");
        }

        String resultado = loginService.login("teste@gmail.com", "senhaErrada");

        assertEquals("Conta bloqueada temporariamente por segurança. Tente novamente em 15 minutos",
                resultado);
    }
}
