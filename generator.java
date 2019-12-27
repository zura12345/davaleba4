package BTU;
import java.util.ArrayList;
import java.util.Random;

public class Generator implements GeneratorInterface {

    private String language;
    private String parameters;
    private Random random;
    private ArrayList<Integer> intParams;

    public Generator(String language, String parameters) {
        this.language = language;
        this.parameters = parameters;
        intParams = new ArrayList<>();
        createParamArray();
        random = new Random();
    }

    public static boolean checkParams(String parameters) {
        String pattern = "^p-\\d-s-\\d-\\d-w-\\d-\\d";
        if (parameters.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createParamArray() {
        String [] allParams = parameters.split("-");
        for (String param: allParams) {
            try {
                intParams.add(Integer.parseInt(param));
            } catch (NumberFormatException e) {
            }
        }
    }

    @Override
    public String generateAlphabet() {
        char start, end;
        String alphabet = "";
        switch (language) {
            case "geo":
                start = 'ა'; end = 'ჰ';
                break;
            case "rus":
                start = 'а'; end = 'я';
                break;
            default:
                start = 'a'; end = 'z';
        }
        for(int i = (int)start; i <= (int)end; i++){
            alphabet += (char)i;
        }

        return alphabet;
    }

    @Override
    public char generateChar() {
        String letters = generateAlphabet();
        char character = letters.charAt(random.nextInt(letters.length()));
        return character;
    }

    @Override
    public String generateWord() {
        int min = (int)intParams.get(3);
        int max = (int)intParams.get(4);
        String word = "";
        int rand = random.nextInt(max - min + 1) + min;
        for(int i = 0; i < rand; i++){
            word += generateChar();
        }
        return word;
    }

    @Override
    public String generateSentence() {
        String sentence = "";
        int min = (int)intParams.get(1);
        int max = (int)intParams.get(2);
        int rand = random.nextInt(max - min + 1) + min;
        for (int i = 0; i < rand; i++) {
            sentence += generateWord();
            sentence += " ";
        }
        sentence = sentence.substring(0, sentence.length()-1) + ".";

        return sentence;
    }

    public String generateParagraph() {
        String para = "";
        int sentenceCount = (int)intParams.get(0);
        for (int i = 0; i < sentenceCount; i++) {
            para += generateSentence();
            para += " ";
        }
        return para;
    }
}