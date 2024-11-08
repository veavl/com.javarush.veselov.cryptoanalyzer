import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CaesarCipher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', '-', ':', '!', '?', ' '};

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
        label:
        while (true) {
            input = scanner.nextLine();
            switch (input) {
                case "0": {
                    int key = valueKey();
                    AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);
                    alphabetShift = alphaShift.alphabetShift();                                  // Алфавит Shift - со смещением по КЛЮЧУ

                    System.out.println("ШИФРОВКА с КЛЮЧОМ " + key + ". Укажите путь к файлу");
                    String fileTxt = "encryptedText_" + "key_№" + key + ".txt";                  // Файл в который будем ШИФРОВАТЬ

                    cipher.getChiDeChiString(fileTxt, copyAlphabet, alphabetShift);
                    break label;
                }
                case "1": {
                    int key = valueKey();
                    AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);
                    alphabetShift = alphaShift.alphabetShift();

                    System.out.println("РАСШИФРОВКА. Укажите путь к файлу, ЗАШИФРОВАННОМУ с использованием КЛЮЧА №" + key);
                    String fileTxt = "decryptedText.txt";                                         // Файл в который будем ДЕШИФРОВАТЬ

                    cipher.getChiDeChiString(fileTxt, alphabetShift, copyAlphabet);
                    break label;
                }
                case "2":
                    System.out.println("РАСШИФРОВКА методом brute force. Укажите путь к ЗАШИФРОВАННОМУ файлу");
                    deCipherBF.deCipher(copyAlphabet);
                    break label;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Не верный формат ввода: введите 0, 1, 2 или 3");
                    break;
            }
        }
    }
}

// Для шифрования:
// C:\Users\veavl\IdeaProjects\com.javarush.veselov.cryptoanalyzer\
// src\Files\originalText.txt

// Для дешифрования:
// src\Files\encryptedText_key_№21.txt

// bruteForce
// src\Files\encryptedText_key_№21.txt
// C:\Users\veavl\IdeaProjects\com.javarush.veselov.cryptoanalyzer\src\Files\encryptedText_key_№5.txt


// C:\Users\veavl\IdeaProjects\com.javarush.veselov.cryptoanalyzer\src\Temp\Files_2\Maharadzh-Nisargadatta.txt



























