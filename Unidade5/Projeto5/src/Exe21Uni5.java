public class Exe21Uni5 {
    public Exe21Uni5() {

        float alturaChico = 1.50f;
        float alturaZe = 1.10f;
        int anos = 0;

        while (alturaChico > alturaZe) {
            alturaChico += 0.02;
            alturaZe += 0.03;
            anos ++;
        }

        System.out.println();
        System.out.println("Serão necessários " + anos + " anos para que Zé fique maior que Chico");
        System.out.println();
        
    }
    public static void main(String[] args) {
        new Exe21Uni5();
    }
}
