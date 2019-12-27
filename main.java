package BTU;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String language = new Scanner(System.in).nextLine();
        String parameters;

        while(true) {
            parameters = new Scanner(System.in).nextLine();
            if (Generator.checkParams(parameters) != true) {
                System.out.println("Cadet tavidan!");
            } else {
                break;
            }

        }

        Generator generator = new Generator(language, parameters);

        System.out.println(generator.generateParagraph());
    }
}