import java.util.InputMismatchException;
import java.util.Scanner;

public class TextField {
    Scanner scanner = new Scanner(System.in);
    CoinSearch coinSearch = new CoinSearch();

    String moedaConvert;
    String moedaUser;
    double quantidade;
    int awnser;

    public void iniciarDialogo(){

        do {
            System.out.println("\n------------- MENU -------------");
            System.out.println("Bem vindo ao conversor de moedas!");
            System.out.println("Opções disponiveis:");
            System.out.println("---------------  ---------------\n");
            System.out.println("---------------  ---------------");
            System.out.println("0. Sair");
            System.out.println("1. Consultar moedas");
            System.out.println("2. Realizar conversão");
            System.out.println("3. Consultar histórico");
            System.out.println("---------------  ---------------");

            try {
                awnser = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e ){
                System.out.println("Entrada invalida!");
                scanner.nextLine();
            }

            if (awnser == 0) break;

            switch (awnser){
                case 1:
                    opcaoUm();
                    break;
                case 2:
                    opcaoDois();
                    break;
                case 3:
                    opcaoTres();
                    break;
            }
        }
        while (true);
    }

    public void opcaoUm(){
        System.out.println("------------- MOEDAS -------------");
        System.out.println("USD | BOB | JPY | GBP | AUD | CAD");
        System.out.println("BRL | ARS | CLP | COP | CNH | CZK");
        System.out.println("----------------  ----------------");
    }

    public void opcaoDois(){
        try {
            System.out.println("Digite a moeda POSSUIDA:");
            moedaUser = scanner.nextLine().toUpperCase();

            if (moedaUser.length() < 3){
                System.out.println("Moeda invalida!");
                opcaoDois();
            }

            System.out.println("Digite a QUANTIDADE dessa moeda:");
            quantidade = scanner.nextDouble();
            scanner.nextLine();

            if (quantidade < 0){
                System.out.println("Não há quantia necessária para conversão.");
                opcaoDois();
            }

            System.out.println("Digite a moeda a ser CONVERTIDA:");
            moedaConvert = scanner.nextLine().toUpperCase();

            if (moedaConvert.length() < 3){
                System.out.println("Moeda invalida!");
                opcaoDois();
            } else {
                coinSearch.convertCoin(moedaUser, quantidade, moedaConvert);
            }

        } catch (InputMismatchException e) {
            System.out.println("Digite apenas números!");
            scanner.nextLine();
        }
    }

    public void opcaoTres(){
        System.out.println("----------------  ----------------");
        coinSearch.mostrarLista();
        System.out.println("----------------  ----------------");
    }
}
