package game;
import java.util.Scanner;


public class Hangman {

    private static Word word;
    private static int error_count;
    private static Word underline_word;
    private static String guesses = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantas partidas voce pretende jogar?");
        int rounds = sc.nextInt();

        for (int round = 0; round < rounds; round++) {
            System.out.println("Insira uma palavra:");
            String texto = sc.next();
            word = new Word(texto);
            String underline_string = new String(new char[word.getContent().length()]).replace("\0", "_");
            underline_word = new Word(underline_string);
            while (error_count < 5 && underline_word.getContent().contains("_")) {
                System.out.println("\nAdivinhe qualquer letra da palavra");
                for (int i = 0; i < underline_word.getContent().length(); i++) {
                    System.out.print(underline_word.getContent().charAt(i) + " ");
                }
                System.out.print("\n\n");
                String guess = sc.next();
                hang(guess);
            }
            error_count = 0;
            guesses = "";
        }
        sc.close();
    }

    public static void hang(String guess) {
        StringBuilder aux_underline_word = new StringBuilder();
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
            if (underline_word.getContent().equals(aux_underline_word.toString())) {
                error_count++;
                hangmanImage();

            } else {
                underline_word.setContent(aux_underline_word.toString());
            }
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