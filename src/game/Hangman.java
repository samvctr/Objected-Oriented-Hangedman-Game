package game;
import java.util.Scanner;


public class Hangman {

    private static Word word;
    private static int error_count;
    private static Word underline_word;
    private static String guesses = "";

    public static void main(String[] args) {
        //TODO: interface grafica
        int mode = 1337;
        Scanner sc = new Scanner(System.in);
        while(mode != 0){
            //O menu principal roda em loop ate o usuario decidir parar de jogar
            System.out.println("Ola, bem vindo ao menu principal do Jogo da Forca");
            System.out.println("1 - Jogo Solo");
            System.out.println("2 - Versus");
            System.out.println("0 - Sair do Jogo");
            mode = sc.nextInt();
            if(mode == 1){
                System.out.println("\nQuantas rodadas?\n");
                //Usuario decide o numero de rodadas, numero de rodadas, hang_maker chama hang em loop uma quantidade de vezes igual ao numero de rodadas
                int rounds = sc.nextInt();
                for(int i=0;i<rounds;i++){
                    hang_maker(sc, rounds);
                }
            }
            else if(mode ==2){
                System.out.println("Para jogar Versus, insira o Username dos jogadores");
                System.out.println("Username do jogador 1: ");
                String username1 = sc.next();
                User player1 = new User(username1, 0);
                System.out.println("Username do jogador 2: ");
                String username2 = sc.next();
                User player2 = new User(username2, 0);
                //Ambos os nomes de usuarios sao salvos e suas vitorias inicializadas como 0
                //TODO USER 1: criar um sistema para salvar os usuarios e suas vitorias em arquivos, para que o jogo mantenha a contagem apos o fim da sua inicializacao
                //TODO USER 2: usuario poder alterar seu nome de usuario ou zerar sua quantidade de vitorias

                //Usuario decide o numero de rodadas, numero de rodadas, hang_maker chama hang em loop uma quantidade de vezes igual ao numero de rodadas
                System.out.println("\nQuantas rodadas?\n");
                int rounds = sc.nextInt();
                System.out.println("\n" + player1.getUsername() + ", sua vez de escolher as palavras das " + rounds+" rodadas de " + player2.getUsername() + "\n");
                //O adversario escolhe as palavras do seu jogo da forca
                player1.setVictories(hang_maker(sc, rounds));

                System.out.println("\n" + player2.getUsername()+ ", sua vez de escolher as palavras das " + rounds+" rodadas de " + player1.getUsername() + "\n");
                //O adversario escolhe as palavras do seu jogo da forca
                player2.setVictories(hang_maker(sc, rounds));
                if(player1.getVictories() > player2.getVictories()){
                    System.out.println("\n" + player1.getUsername()+ " VENCEU!");
                }
                else if(player2.getVictories() > player1.getVictories()){
                    System.out.println("\n" + player2.getUsername()+ " VENCEU!");
                }
                else{
                    System.out.println("\nEMPATE!\n");
                }
                System.out.println("\nFIM DE JOGO\n\n");
            }
        }
        sc.close();
    }

    private static int hang_maker(Scanner sc, int rounds) {
        int wins = 0;
        for (int round = 0; round < rounds; round++) {
            System.out.println("Insira uma palavra:");
            //O usuario entra com uma string que vai ser a palavra que o usuario tentara acertar na forca
            String texto = sc.next();
            word = new Word(texto);
            String underline_string = new String(new char[word.getContent().length()]).replace("\0", "_");
            underline_word = new Word(underline_string);
            while (error_count < 5 && underline_word.getContent().contains("_")) {
                System.out.println("\nAdivinhe qualquer letra da palavra");
                for (int i = 0; i < underline_word.getContent().length(); i++) {
                    System.out.print(underline_word.getContent().charAt(i) + " ");
                }
                //O usuario entra com o char que vai ser testado se esta na string por hang
                System.out.print("\n\n");
                String guess = sc.next();
                //chamada de hang
                hang(guess);
            }
            if(error_count<5){
                wins++;
            }
            error_count = 0;
            guesses = "";
        }
        return wins;
    }

    public static void hang(String guess) {
        StringBuilder aux_underline_word = new StringBuilder();
        //hang cria uma string auxiliar que e coloca underline onde o char nao estiver na string "underline" ou se tiver sido inserido pelo usuario
        for (int i = 0; i < word.getContent().length(); i++) {
            if (word.getContent().charAt(i) == guess.charAt(0)) {
                aux_underline_word.append(guess.charAt(0));
            } else if (underline_word.getContent().charAt(i) != '_') {
                aux_underline_word.append(word.getContent().charAt(i));
            } else {
                aux_underline_word.append("_");
            }
        }

        if(guesses.contains(guess)){
            System.out.println("\nVoce ja chutou essa letra...\n");
            underline_word.setContent(aux_underline_word.toString());
        }
        else{
            //se o char nao esta presente na string == erro
            if (underline_word.getContent().equals(aux_underline_word.toString())) {
                error_count++;
                hangmanImage();

            }
            //underline = auxiliar
            else {
                underline_word.setContent(aux_underline_word.toString());
            }
            //se a palavra em underline tiver sido transformada em == a palavra inserida pelo usuario, uma vitoria e computada
            if (underline_word.getContent().equals(word.getContent())) {
                for (int i = 0; i < underline_word.getContent().length(); i++) {
                    System.out.print(underline_word.getContent().charAt(i) + " ");
                }
                System.out.println("\nVoce venceu e nao foi enforcado!");
                System.out.println("A palavra era " + word.getContent().toUpperCase());
            }
        }
        guesses += guess;
    }

    public static void hangmanImage() {
        if (error_count == 1) {
            System.out.println("Voce errou, a forca foi armada!");
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println("------------------------");
        }
        if (error_count == 2) {
            System.out.println("Voce errou, tente de novo");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println("------------------------");
        }
        if (error_count == 3) {
            System.out.println("Voce errou, tente de novo");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println("------------------------");
        }
        if (error_count == 4) {
            System.out.println("Voce errou, tente de novo");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println("------------------------");
        }
        if (error_count == 5) {
            System.out.println("GAME OVER! Voce foi enforcado!");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println("------------------------");
            System.out.println("A palavra era " + word.getContent().toUpperCase());
        }
    }
}