
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cipher {

    public void getChiDeChiString(String fileTxt, char[] alfa1, char[] alfa2) throws IOException, InvalidPathException {      // Если на первом месте alphabet -- Шифровка, если alphabetShift - ДеШифровка
        Scanner scanner = new Scanner(System.in);
        String stringPath;
        List<String> list = new ArrayList<>();
        while (true) {
            stringPath = scanner.nextLine();                                                                                  // Путь к ФАЙЛУ для рас/шифровки
            try {
                Path path = Path.of(stringPath.trim());                                                                       // Создаем объект Path. trim() - УДАЛЯЕМ ПРОБЕЛЫ
                if (!Files.exists(path)) {                                                                                    // Если ФАЙЛ НЕ существует
                    System.out.println("Путь к файлу указан не верно, либо файл не существует. Укажите путь к файлу!");
                } else if (Files.readString(path).isEmpty()) {
                    System.out.println("Файл пустой. Укажите путь к файл с текстом");
                } else {

                    try (BufferedReader reader = Files.newBufferedReader(path)) {
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
                        Path pathOut = Path.of("src/Files/" + fileTxt);
                        try (BufferedWriter writer = Files.newBufferedWriter(pathOut)) {
                            for (String str : list) {
                                writer.write(str + "\n");
                                writer.flush();
                            }
                        }
                    }
                    System.out.println("Успешно! Файл доступен: /src/Files/" + fileTxt);
                    break;
                }
            } catch (AccessDeniedException | InvalidPathException e) {
                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }

    }

}
