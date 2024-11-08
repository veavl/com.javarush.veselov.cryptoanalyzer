import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Scanner;

public class CaesarCipher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', '-', ':', '!', '?', ' '};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        char[] copyAlphabet = Arrays.copyOf(ALPHABET, ALPHABET.length);
        char[] alphabetShift;
        System.out.println("ДЕШИФРАТОР ТЕКТОВЫХ ФАЙЛОВ");

        Cipher cipher = new Cipher();
        DeCipherBruteForce deCipherBF = new DeCipherBruteForce();

        System.out.println("Шифрование текста введите: 0");
        System.out.println("Расшифровка с использованием известного ключа: 1");
        System.out.println("Расшифровка методом brute force введите: 2");
        System.out.println("Для выхода введите: 3");

        String input;
        while (true) {
            input = scanner.nextLine();
            switch (input) {

                case "0": {
                    int key = valueKey();
                    AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);
                    alphabetShift = alphaShift.alphabetShift();

                    System.out.println("ШИФРОВКА с КЛЮЧОМ " + key + ". Введите путь к файлу");
                    String inputFilePath = checkPath();

                    System.out.println("Введите путь к файлу: куда шифровать");
                    String outputFilePath = scanner.nextLine();
                    try {
                        cipher.getChiDeChiString(inputFilePath, outputFilePath, copyAlphabet, alphabetShift);
                    } catch (IOException | InvalidPathException e) {            // AccessDeniedException, IOException, InvalidPathException -- вводил "K:", "src\Files\" или "ОЩ"
                        System.out.println("Что-то пошло не так. Возможно вы не корректно ввели путь к файлу");
                        System.exit(0);
                    }
                    break;
                }

                case "1": {
                    int key = valueKey();
                    AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);
                    alphabetShift = alphaShift.alphabetShift();

                    System.out.println("РАСШИФРОВКА. Укажите путь к файлу, ЗАШИФРОВАННОМУ с использованием КЛЮЧА №" + key);
                    String inputFilePath = checkPath();

                    System.out.println("Введите путь к файлу: куда расшифровывать");
                    String outputFilePath = scanner.nextLine();
                    try {
                        cipher.getChiDeChiString(inputFilePath, outputFilePath, alphabetShift, copyAlphabet);
                    } catch (IOException | InvalidPathException e) {
                        System.out.println("Что-то пошло не так. Возможно вы не корректно ввели путь к файлу");
                    }
                    break;
                }

                case "2": {
                    System.out.println("РАСШИФРОВКА методом brute force. Укажите путь к ЗАШИФРОВАННОМУ файлу");
                    String inputFilePath = checkPath();
                    System.out.println("Введите путь к ПАПКЕ: куда расшифровывать");
                    String outputDir = checkDir();
                    deCipherBF.deCipher(inputFilePath, outputDir, copyAlphabet);
                    break;
                }
                case "3": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Не верный формат ввода: введите 0, 1, 2 или 3");
                    break;
                }
            }
        }
    }

    public static int valueKey() {
        int key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите КЛЮЧ ШИФРОВАНИЯ - целое положительное число");
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.isEmpty())
                    System.out.println("Вы ничего не ввели");
                else if (Integer.parseInt(input) <= 0) {
                    System.out.println("Введите целое положительное число");
                    continue;
                }
                key = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат данных. Введите целое положительное число");
            }
        }
        return key;
    }

    public static String checkPath() throws IOException, InvalidPathException {
        String inputPath;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            inputPath = scanner.nextLine();
            try {
                Path path = Path.of(inputPath.trim());
                if (!Files.exists(path)) {
                    System.out.println("Путь к файлу указан не верно, либо файл не существует. Укажите путь к файлу!");
                    continue;
                } else if (Files.readString(path).isEmpty()) {
                    System.out.println("Файл пустой. Укажите путь к файл с текстом");
                    continue;
                }
                inputPath = path.toString();
                break;
            } catch (AccessDeniedException | InvalidPathException e) {
                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }
        return inputPath;
    }

    public static String checkDir() throws InvalidPathException {
        String outputDir;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            outputDir = scanner.nextLine();
            try {
                if (outputDir.isEmpty()) {
                    System.out.println("Вы ничего не ввели. Укажите путь");
                    continue;
                }
                Path path = Path.of(outputDir.trim());
                if (!Files.isDirectory(path)) {
                    System.out.println("Укажите существующий путь");
                    continue;
                }
                outputDir = path.toString();
                break;
            } catch (InvalidPathException e) {
                System.out.println("Что-то пошло не так. Укажите путь!");
            }
        }
        return outputDir;
    }
}





























