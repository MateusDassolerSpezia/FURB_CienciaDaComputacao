import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe6Uni2 {
    public Exe6Uni2() {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Digite o núemro do funcionário: ");
        int numeroFuncionario = entrada.nextInt();

        System.out.print("Digite a quantidade de horas trabalhadas: ");
        float quantidadeHoras = entrada.nextFloat();

        System.out.print("Digite o valor da hora: ");
        float valorHora = entrada.nextFloat();

        float salario = quantidadeHoras * valorHora;
        //System.out.print("Funcionário " + numeroFuncionario + "possui o salário R$ " + salario);

        DecimalFormat formatador = new DecimalFormat("0.00");
        System.out.print("Funcionário " + numeroFuncionario + "possui o salário R$ " + formatador.format(salario));






        
        
        
        
        entrada.close();
      }
    
    public static void main(String[] args) {
        new Exe6Uni2();
    }
    
}
