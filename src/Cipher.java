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

public class Cipher /* implements AutoCloseable*/ {

    public void getChiDeChiString(String fileTxt, char[] alfa1, char[] alfa2) throws IOException, InvalidPathException {      // Если на первом alphabet -- Шифровка, если alphabetShift - ДеШифровка
        Scanner scanner = new Scanner(System.in);
        String stringPath;                                                                                                    // Путь к ФАЙЛУ для рас/шифровки
        while (true) {
            stringPath = scanner.nextLine();                                                                                  // Считываем строку - Путь
            try {
                Path path = Path.of(stringPath.trim());                                                                       // Создаем объект Path. trim() - УДАЛЯЕМ ПРОБЕЛЫ
                if (!Files.exists(path)) {                                                                                    // Если ФАЙЛ НЕ существует
                    System.out.println("Путь к файлу указан не верно, либо файл не существует. Укажите путь к файлу!");
                } else if (Files.readString(path).isEmpty()) {
                    System.out.println("Файл пустой. Укажите путь к файл с текстом");
                }
                else {
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.setLength(0);                                           // Обнуляем StringBuilder - иначе при повторном вызове к уже существующему добавиться новое!
                    String readString = Files.readString(path);                        // Читаем содержимое файла -- результат в строку readString
                    // System.out.println(readString.length());
                    char[] arrayText = readString.toCharArray();                       // Строку readString -- в массив символов и шифруем ее или дешифруем -- смотря что нужно!
                    for (int i = 0; i < arrayText.length; i++) {
                        for (int j = 0; j < alfa1.length; j++) {
                            if (Character.toString(arrayText[i]).equalsIgnoreCase(Character.toString(alfa1[j]))) {
                                strBuilder.append(alfa2[j]);
                            }
                        }
                    }

                    String resultString = strBuilder.toString();                                             // Результат в строку resultString, которую нужно записать в ФАЙЛ
                    Path pathEnDecryptedFile = Path.of("src/Files/" + fileTxt);                              // Создаем путь с указанием имени ФАЙЛА: не важно есть файл или нет
                    Files.writeString(pathEnDecryptedFile, resultString);                                    // Записываем в ФАЙЛ рас/зашифрованную строку: ФАЙЛ создается --
                    // -- или перезаписывается его содержимое (если он есть).
                    System.out.println("Успешно! Файл доступен: /src/Files/" + fileTxt);
                    System.out.println(resultString);                                                        // Выводим зашифрованную строку
                    break;
                }
            } catch (AccessDeniedException | InvalidPathException e) {
                // AccessDeniedException - возникает: не ввели ничего и нажали ENTER, либо указан путь к ДИРЕКТОРИИ
                // InvalidPathException - возникает:
                // 1. Если в пути (в конце строки) был пробел "D:\ARHIVE\Ezo\No_Change\Doc\Пауло ". Убрал ЭТУ ОШИБКУ при помощи trim() - УДАЛЯЕТ ПРОБЕЛЫ В НАЧАЛЕ И КОНЦЕ СТРОКИ
                // 2. Когда написал хрень: C:/User/  /  safafdsdf/ - поэтому оставил это ИСКЛЮЧЕНИЕ
                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }

    }
//    @Override
//    public void close() throws Exception {
//        // Release the resource here (e.g., close a file, release a connection).
//        System.out.println("Resource released.");
//    }
}
