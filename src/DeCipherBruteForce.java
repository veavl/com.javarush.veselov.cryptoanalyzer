// Если КЛЮЧ равен длине алфавита -- текст уже не шифруется, так как смещения УЖЕ нет.
// Последнее смещение - это (длина Алфавита - 1)

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class DeCipherBruteForce {

    public void deCipher(char[] alfa) throws IOException, InvalidPathException {        // Параметр - КОПИЯ исходного Алфавита
        Scanner scanner = new Scanner(System.in);
        String stringPath;
        char[] alphabetShift;
        while (true) {
            stringPath = scanner.nextLine();
            try {
                Path path = Path.of(stringPath.trim());
                if (!Files.exists(path)) {
                    System.out.println("Путь к файлу указан не верно, либо файл не существует. Укажите путь к файлу!");
                } else if (Files.readString(path).isEmpty()) {
                    System.out.println("Файл пустой. Укажите путь к файл с текстом");
                } else {
                    for (int key = 0; key < alfa.length; key++) {                       // Ниже код -- аналог Cipher.java. Только выполняется для ВСЕХ возможных ключей
                        AlphabetShift alphaShift = new AlphabetShift(alfa, key);
                        alphabetShift = alphaShift.alphabetShift();

                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.setLength(0);
                        String readString = Files.readString(path);
                        char[] arrayText = readString.toCharArray();
                        for (int i = 0; i < arrayText.length; i++) {
                            for (int j = 0; j < alfa.length; j++) {
                                if (Character.toString(arrayText[i]).equalsIgnoreCase(Character.toString(alphabetShift[j]))) {
                                    strBuilder.append(alfa[j]);
                                }
                            }
                        }
                        String resultString = strBuilder.toString();
                        Path pathEnDecryptedFile = Path.of("src/Files/bruteForce/decrypted_KEY №" + key + ".txt");
                        Files.writeString(pathEnDecryptedFile, resultString);
                    }
                    System.out.println("Успешно! Файлы доступны: src/Files/bruteForce/");
                    break;
                }
            } catch (AccessDeniedException | InvalidPathException e) {
                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }
    }
}