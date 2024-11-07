import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class CaesarCipher {

    // Алфавит
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', '-', ':', '!', '?', ' '};

    public static int valueKey () {
        int key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите КЛЮЧ ШИФРОВАНИЯ");
        while (true) {
            String input = scanner.next();
            try {
                key = Integer.parseInt(input);
//                if (key > ALPHABET.length)                                    // Преобразование КЛЮЧА. Например из №1235 в №31
//                    key = new AlphabetShift(ALPHABET, key).getKey();
                // System.out.println("Введен КЛЮЧ: " + key);
//                if (key <= 0) {
//                    break;
//                }
//                else {
//
//                    break;
//                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат данных. Введите целое число!");
            }
        }
        return key;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        char[] copyAlphabet = Arrays.copyOf(ALPHABET, ALPHABET.length);          // 1. Копия исходного Алфавита
        char[] alphabetShift;
        // System.out.println("Длина алфавита: " + copyAlphabet.length);                                // for TEST

        System.out.println("ДЕШИФРАТОР ТЕКТОВЫХ ФАЙЛОВ");

        Cipher cipher = new Cipher();                                            // Дешифратор
        DeCipherBruteForce deCipherBF = new DeCipherBruteForce();

        System.out.println("Шифрование текста введите: 0");
        System.out.println("Расшифровка с использованием известного ключа: 1");
        System.out.println("Расшифровка методом brute force введите: 2  ");

        String oneZero;                                                          // 1 или 0
        while (true) {
            oneZero = scanner.nextLine();
            if (oneZero.equals("0")) {

                int key = valueKey();                                                    // Получаем КЛЮЧ

                AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);         // Объект типа AlphabetShift -- передаем исходный Алфавит и КЛЮЧ
                alphabetShift = alphaShift.alphabetShift();                              // Алфавит Shift - со смещением по КЛЮЧУ

                // System.out.println(Arrays.toString(alphabetShift));                      // for TEST

                System.out.println("ШИФРОВКА с КЛЮЧОМ " + key + ". Укажите путь к файлу");

                String fileTxt = "encryptedText_" + "key_№" + key + ".txt";            // Файл в который будем ШИФРОВАТЬ
                cipher.getChiDeChiString(fileTxt, copyAlphabet, alphabetShift);
                break;
            } else if (oneZero.equals("1")) {
                int key = valueKey();                                                    // Получаем КЛЮЧ

                AlphabetShift alphaShift = new AlphabetShift(copyAlphabet, key);         // Объект типа AlphabetShift -- передаем исходный Алфавит и КЛЮЧ
                alphabetShift = alphaShift.alphabetShift();                              // Алфавит Shift - со смещением по КЛЮЧУ

                System.out.println("РАСШИФРОВКА. Укажите путь к файлу, ЗАШИФРОВАННОМУ с использованием КЛЮЧА №" + key);
                // Аналогично, но с перестановкой Алфавитов, выполняем Расшифровку
                String fileTxt = "decryptedText.txt";                               // Файл в который будем ДЕШИФРОВАТЬ
                cipher.getChiDeChiString(fileTxt, alphabetShift, copyAlphabet);
                break;
            } else if (oneZero.equals("2")) {
                System.out.println("РАСШИФРОВКА методом brute force. Укажите путь к ЗАШИФРОВАННОМУ файлу");
                deCipherBF.deCipher(copyAlphabet);
                break;
            } else {
                System.out.println("Не верный формат ввода: введите 1 или 0");
            }
        }

// src/Files/originalText.txt
// src/Files/encryptedText_key_№3.txt

        // src/Files/originalText.txt - путь к файлу с нормальным текстом
        // D:\ARHIVE\Ezo\No_Change\Doc\Пауло Коэльо\Дьявол и сеньорита - Part.txt

        // Логика меню
        // 1. Шифрование
        // 2. Расшифровка с ключом
        // 3. Brute force
        // 0. Выход

    }
}






























