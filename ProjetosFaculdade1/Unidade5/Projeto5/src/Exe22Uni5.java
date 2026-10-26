public class Exe22Uni5 {
    public Exe22Uni5() {
        
        float ano = 1996;
        double salario = 2000;
        double aumento = salario * 1.5f / 100;

        while (ano < 2024) {
            salario = salario + aumento;
            aumento = aumento * 2;
            ano++;
        }

        System.out.println("O salário atual do funcionario é de: R$ " + salario);

    }
    public static void main(String[] args) {
        new Exe22Uni5();
    }
}
