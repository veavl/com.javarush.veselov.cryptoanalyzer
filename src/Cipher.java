// Смотрим подробную работу/логику Де/Шифратора (с пояснениями:
// ZIP/!_Модуль №1 - Итоговый проект/02.11_2 - AfterNoon - Работает шифр и Дешифр в Классах)
// Этот класс и, если точнее, его метод непосредственно меняет символы строки из одного Алфавита на символы из другого Алфавита
// И возвращает строку: Зашифрованную или Расшифрованную

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Cipher {

    public void getChiDeChiString(String fileTxt, char[] alfa1, char[] alfa2) throws IOException, InvalidPathException {      // Если на первом месте alphabet -- Шифровка, если alphabetShift - ДеШифровка
        Scanner scanner = new Scanner(System.in);
        String stringPath;
        while (true) {
            stringPath = scanner.nextLine();                                                                                  // Путь к ФАЙЛУ для рас/шифровки
            try {
                Path path = Path.of(stringPath.trim());                                                                       // Создаем объект Path. trim() - УДАЛЯЕМ ПРОБЕЛЫ
                if (!Files.exists(path)) {                                                                                    // Если ФАЙЛ НЕ существует
                    System.out.println("Путь к файлу указан не верно, либо файл не существует. Укажите путь к файлу!");
                } else if (Files.readString(path).isEmpty()) {
                    System.out.println("Файл пустой. Укажите путь к файл с текстом");
                } else {
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.setLength(0);                                           // Обнуляем StringBuilder
                    String readString = Files.readString(path);                        // Читаем содержимое файла
                    char[] arrayText = readString.toCharArray();                       // readString -- в массив char[] и шифруем/дешифруем ее -- смотря что нужно!
                    for (int i = 0; i < arrayText.length; i++) {
                        for (int j = 0; j < alfa1.length; j++) {
                            if (Character.toString(arrayText[i]).equalsIgnoreCase(Character.toString(alfa1[j]))) {
                                strBuilder.append(alfa2[j]);
                            }
                        }
                    }

                    String resultString = strBuilder.toString();                                             // resultString -- нужно записать в ФАЙЛ
                    Path pathEnDecryptedFile = Path.of("src/Files/" + fileTxt);                              // Куда будем писать файл
                    Files.writeString(pathEnDecryptedFile, resultString);                                    // Записываем в ФАЙЛ рас/зашифрованную строку
                    System.out.println("Успешно! Файл доступен: /src/Files/" + fileTxt);
                    break;
                }
            } catch (AccessDeniedException | InvalidPathException e) {
                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }

    }

}
