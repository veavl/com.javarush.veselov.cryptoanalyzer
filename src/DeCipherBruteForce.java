// Если КЛЮЧ равен длине алфавита -- текст уже не шифруется, так как смещения УЖЕ нет.
// Последнее смещение - это (длина Алфавита - 1)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DeCipherBruteForce {

    public void deCipher(String inputFilePath, String outputDir, char[] alfa) throws IOException, InvalidPathException {
        Path pathInput = Path.of(inputFilePath);
        char[] alphabetShift;

        for (int key = 0; key < alfa.length; key++) {                       // КОД -- аналог Cipher.java. Только выполняется для ВСЕХ возможных ключей
            AlphabetShift alphaShift = new AlphabetShift(alfa, key);
            alphabetShift = alphaShift.alphabetShift();

            List<String> list = new ArrayList<>();

            try (BufferedReader reader = Files.newBufferedReader(pathInput)) {
                String line;
                char[] arrayCipher;
                while (reader.ready()) {
                    line = reader.readLine();
                    arrayCipher = new char[line.length()];
                    char[] arrayLine = line.toCharArray();
                    for (int i = 0; i < arrayLine.length; i++) {
                        for (int j = 0; j < alfa.length; j++) {
                            if (Character.toString(arrayLine[i]).equalsIgnoreCase(Character.toString(alphabetShift[j]))) {
                                arrayCipher[i] = alfa[j];
                            }
                        }
                    }
                    String lineCipher = new String(arrayCipher);
                    list.add(lineCipher);
                }
                Path pathOut = Path.of(outputDir + "/decrypted_KEY №" + key + ".txt");
                try (BufferedWriter writer = Files.newBufferedWriter(pathOut)) {
                    for (String str : list) {
                        writer.write(str + "\n");
                        writer.flush();
                    }
                }
            }
        }
        System.out.println("Успешно!");
        System.exit(0);
    }
}