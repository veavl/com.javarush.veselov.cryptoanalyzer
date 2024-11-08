public class AlphabetShift {

    private final char[] alphabet;
    private int shift;

    public AlphabetShift(char[] alphabet, int shift) {
        this.alphabet = alphabet;
        this.shift = shift;                                                        // Ключ/сдвиг - Shift
    }

    public char[] getAlphabet() {
        return this.alphabet;
    }

    // Метод преобразует ЗНАЧЕНИЕ КЛЮЧА К ДОПУСТИМОМУ - не больше длины алфавита. Таким образом, ключ можно ввести любой
    public int getKey() {
        int lengthAlphabet = alphabet.length;
        if (shift > lengthAlphabet) {
            shift = shift - lengthAlphabet * (shift / lengthAlphabet);
            if (shift % lengthAlphabet == 0)                                        // Если shift > длины Алфавита и при этом кратен длине Алфавита
                shift = shift - lengthAlphabet * (shift / lengthAlphabet - 1);
        }
        return shift;                                                               // Получаем РЕАЛЬНЫЙ ключ
    }

    public char[] alphabetShift() {                                                 // Возвращает измененный Алфавит - со сдвигом на Key
        int lengthAlphabet = alphabet.length;                                       // Длина Алфавита

        shift = getKey();

        char[] alphabetShift = new char[lengthAlphabet];                            // Новый массив, равный длине исходного Массива Алфавит

        for (int i = 0; i < lengthAlphabet - shift; i++) {                          // Заполняем новый массив - Шаг №1
            alphabetShift[i] = alphabet[i + shift];
        }
        for (int i = 0; i < shift; i++) {                                           // Заполняем новый массив - Шаг №2
            alphabetShift[lengthAlphabet - shift + i] = alphabet[i];
        }

        return alphabetShift;                                                       //  char[] Shift-Алфавит
    }
}
