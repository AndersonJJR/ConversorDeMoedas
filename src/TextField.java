import java.util.Scanner;

public class TextField {
    Scanner scanner = new Scanner(System.in);
    CoinSearch coinSearch = new CoinSearch();

    String moedaConvert;
    String moedaUser;
    double quantidade;

    public void iniciarDialogo(){

        do {
            System.out.println("-------------  -------------");
            System.out.println("Bem vindo ao conversor de moedas!");
            System.out.println("Opções disponiveis:\n");
            System.out.println("0. Sair");
            System.out.println("1. Consultar moedas");
            System.out.println("2. Realizar conversão");

            int awnser = scanner.nextInt();
            scanner.nextLine();

            if (awnser == 0) break;

            switch (awnser){
                case 1:
                    opcaoUm();
                    break;
                case 2:
                    opcaoDois();
                    break;
            }
        }

        while (true);
    }

    public void opcaoUm(){
        System.out.println("------------- MOEDAS -------------");
        System.out.println("USD | BRL | JPY | GBP | AUD | CAD");
        System.out.println("CHF | HRK | MXN | NZD | CNH | CZK");
    }

    public void opcaoDois(){
        try {

            System.out.println("Digite a moeda possuida:");
            moedaUser = scanner.nextLine().toUpperCase();

            System.out.println("Digite a quantidade dessa moeda:");
            quantidade = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Digite a moeda a ser convertida:");
            moedaConvert = scanner.nextLine().toUpperCase();

            coinSearch.convertCoin(moedaUser, quantidade, moedaConvert);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
