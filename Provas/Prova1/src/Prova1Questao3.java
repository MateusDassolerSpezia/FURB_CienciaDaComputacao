public class Prova1Questao3 {
    public Prova1Questao3() {
        

        String nome = "Teste";
        String pais = "Brasil";
        
        if (nome == "" && pais == "") {
            System.out.println("Dados incorretos");
            System.out.println("Vazio");
        } else {
            System.out.println("Nome: " + nome);
        }

        if (pais == "") {
            System.out.println("País Vazio");
        } else {
            System.out.println("País: " + pais);
        }
    
        System.out.println("Acabei");

    
    }
    public static void main(String[] args) {
        new Prova1Questao3();
    }
}
