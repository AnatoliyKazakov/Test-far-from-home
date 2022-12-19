import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KeywordsGame {
    private static final List<String> keywords = List.of(
        "byte", "short", "int", "long", "float", "double", "char", "boolean",
        "if", "else", "for", "while", "do", "continue", "break", "switch", "case",
        "default", "new", "instanceof", "this", "super", "return", "void", "_",
        "class", "interface", "package", "import", "extends", "implements", "enum",
        "public", "private", "protected", "static", "final", "abstract", "try",
        "catch", "finally", "throw", "throws", "synchronized", "volatile",
        "native", "transient", "assert", "strictfp", "const", "goto"
    );
    private static final List<String> resrved = List.of("true", "false", "null", "var");
    private static final List<String> destructors = List.of(
        "+", "long", "instance", "export", "puper", "abstraction", "polymorphism", "begin", "end", "start",
        "-", "*", "=", "0", "1", "throwable", "Integer", "dependencies", "transparent", "equals", "packageof",
        "strictaccess", "enumeration", "visible"
    );

    private static final int MAX_INCORRECT_ATTEMPTS = 5;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new SecureRandom();

    public void runGame() {
        final int randomSizeOfList = random.nextInt(keywords.size());
        int incorrectAnswersCounter = 0;
        while (true) {
            final boolean providedAnswer;
            if (randomSizeOfList < resrved.size()) {
                providedAnswer = writeReservedQuestion();
            } else {
                providedAnswer = writeKeywordQuestion();
            }
            final boolean userAnswer = getAnswer("Выберите правильное утверджение: 1 - да, 0 - нет");
            if (userAnswer == providedAnswer) {
                System.out.println("Вы ответили правильно");
            } else {
                System.out.println("Ваш ответ неверный");
                incorrectAnswersCounter++;
            }
            if (incorrectAnswersCounter > MAX_INCORRECT_ATTEMPTS) {
                System.out.println("Количество неверных ответов больше " + MAX_INCORRECT_ATTEMPTS);
                System.out.println("Программа завершается с рандомной ошибкой");
                new WayToExit().exit();
            }
        }
    }

    private boolean writeReservedQuestion() {
        System.out.print("Является ли зарезервированным следующее слово: ");
        if (random.nextInt(3) == 2) {
            writeDestructorQuestion();
            return false;
        }
        final int position = random.nextInt(resrved.size());
        System.out.println(resrved.get(position));
        return true;
    }

    private boolean writeKeywordQuestion() {
        System.out.print("Является ли ключевым следующее слово: ");
        if (random.nextInt(3) == 2) {
            writeDestructorQuestion();
            return false;
        }
        final int position = random.nextInt(keywords.size());
        System.out.println(keywords.get(position));
        return true;
    }

    private void writeDestructorQuestion() {
        final int position = random.nextInt(destructors.size());
        System.out.println(destructors.get(position));
    }

    private boolean getAnswer(String hint) {
        System.out.println(hint);
        final String answer = scanner.nextLine();
        if (answer == null) {
            return getAnswer(hint);
        }
        if (answer.equals("1")) {
            return true;
        }
        if (answer.equals("0")) {
            return false;
        }
        System.out.println("Ответ не распознан");
        return getAnswer(hint);
    }
}
