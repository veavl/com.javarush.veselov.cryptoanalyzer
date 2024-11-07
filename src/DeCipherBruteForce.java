// Если КЛЮЧ равен длине алфавита -- текст уже не шифруется
// Последнее смещение - это (длина Алфавита - 1)

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class DeCipherBruteForce {

    public void deCipher(char[] alfa) throws IOException, InvalidPathException {        // Параметр - КОПИЯ исходного Алфавита

        // Путь к файлу, который нужно расшифровать
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
                }
                else {
                    for (int key = 0; key < alfa.length ; key++) {
                        AlphabetShift alphaShift = new AlphabetShift(alfa, key);         // Объект типа AlphabetShift -- передаем исходный Алфавит и КЛЮЧ
                        alphabetShift  = alphaShift.alphabetShift();                     // Алфавит Shift - со смещением по КЛЮЧУ

                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.setLength(0);                                           // Обнуляем StringBuilder - иначе при повторном вызове к уже существующему добавиться новое!
                        String readString = Files.readString(path);                        // Читаем содержимое файла -- результат в строку readString
                        // System.out.println(readString.length());
                        char[] arrayText = readString.toCharArray();                       // Строку readString -- в массив символов и дешифруем ее
                        for (int i = 0; i < arrayText.length; i++) {
                            for (int j = 0; j < alfa.length; j++) {
                                if (Character.toString(arrayText[i]).equalsIgnoreCase(Character.toString(alphabetShift[j]))) {
                                    strBuilder.append(alfa[j]);
                                }
                            }
                        }

                        String resultString = strBuilder.toString();                                   // Результат в строку resultString, которую нужно записать в ФАЙЛ
                        Path pathEnDecryptedFile = Path.of("src/Files/bruteForce/decrypted_KEY №"+ key +".txt");                              // Создаем путь с указанием имени ФАЙЛА: не важно есть файл или нет
                        Files.writeString(pathEnDecryptedFile, resultString);                          // Записываем в ФАЙЛ расшифрованную строку: ФАЙЛ создается --
                        // -- или перезаписывается его содержимое (если он есть).
                        // System.out.println(resultString);                                                        // Выводим зашифрованную строку

//                        System.out.println(key);
//                    }
//                    int key = 1;
//                    while (key < alfa.length ) {
//                                                                                         // ДЛЯ КАЖДОГО ЗНАЧЕНИЯ КЛЮЧА ФОРМИРУЕМ СВОЙ АЛФАВИТ СО СДВИГОМ
////                        AlphabetShift alphaShift = new AlphabetShift(alfa, key);         // Объект типа AlphabetShift -- передаем исходный Алфавит и КЛЮЧ
////                        alphabetShift  = alphaShift.alphabetShift();                     // Алфавит Shift - со смещением по КЛЮЧУ
////
////                        StringBuilder strBuilder = new StringBuilder();
////                        strBuilder.setLength(0);                                           // Обнуляем StringBuilder - иначе при повторном вызове к уже существующему добавиться новое!
////                        String readString = Files.readString(path);                        // Читаем содержимое файла -- результат в строку readString
////                        // System.out.println(readString.length());
////                        char[] arrayText = readString.toCharArray();                       // Строку readString -- в массив символов и дешифруем ее
////                        for (int i = 0; i < arrayText.length; i++) {
////                            for (int j = 0; j < alfa.length; j++) {
////                                if (Character.toString(arrayText[i]).equalsIgnoreCase(Character.toString(alphabetShift[j]))) {
////                                    strBuilder.append(alfa[j]);
////                                }
////                            }
////                        }
////
////                        String resultString = strBuilder.toString();                                   // Результат в строку resultString, которую нужно записать в ФАЙЛ
////                        Path pathEnDecryptedFile = Path.of("src/Files/bruteForce/decrypted_KEY №"+ key +".txt");                              // Создаем путь с указанием имени ФАЙЛА: не важно есть файл или нет
////                        Files.writeString(pathEnDecryptedFile, resultString);                          // Записываем в ФАЙЛ расшифрованную строку: ФАЙЛ создается --
////                                                                                                        // -- или перезаписывается его содержимое (если он есть).
////                        System.out.println("Успешно! Файлы доступны: src/Files/bruteForce/");
////                        // System.out.println(resultString);                                                        // Выводим зашифрованную строку
////
////                        System.out.println(key);
//                        key++;
//
//                        break;
                    }
                    System.out.println("Успешно! Файлы доступны: src/Files/bruteForce/");
                }
            } catch (AccessDeniedException | InvalidPathException e) {

                System.out.println("Что-то пошло не так. Укажите путь к файлу!");
            }
        }

    }
}