public class Exe11Uni5 {
    public Exe11Uni5() {

        int biscoitosQuebrados = 0;

        for (int horas = 1; horas <= 16; horas ++) {
            if (horas == 2) {
                biscoitosQuebrados += 3;
            } if (horas >= 3) {
                biscoitosQuebrados *= 3;
            } if (horas == 16) {
                biscoitosQuebrados += 1;
            }
        }

        System.out.println(biscoitosQuebrados + " biscoitos quebrados");

    }
    public static void main(String[] args) {
        new Exe11Uni5();
    }
}
