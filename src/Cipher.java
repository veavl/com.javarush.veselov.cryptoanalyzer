
import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Cipher {

    public void getChiDeChiString(String inputFilePath, String outputFilePath, char[] alfa1, char[] alfa2) throws IOException, InvalidPathException {      // Если на первом месте alphabet -- Шифровка, если alphabetShift - ДеШифровка

        List<String> list = new ArrayList<>();
        Path pathInput = Path.of(inputFilePath);
        try (BufferedReader reader = Files.newBufferedReader(pathInput)) {
            String line;
            char[] arrayCipher;
            while (reader.ready()) {
                line = reader.readLine();
                arrayCipher = new char[line.length()];
                char[] arrayLine = line.toCharArray();
                for (int i = 0; i < arrayLine.length; i++) {
                    for (int j = 0; j < alfa1.length; j++) {
                        if (Character.toString(arrayLine[i]).equalsIgnoreCase(Character.toString(alfa1[j]))) {
                            arrayCipher[i] = alfa2[j];
                        }
                    }
                }
                String lineCipher = new String(arrayCipher);
                list.add(lineCipher);
            }
            Path pathOut = Path.of(outputFilePath);
            try (BufferedWriter writer = Files.newBufferedWriter(pathOut)) {
                for (String str : list) {
                    writer.write(str + "\n");
                    writer.flush();
                }
            }
        }
        System.out.println("Шифровка прошла успешно");
        System.exit(0);
    }
}
