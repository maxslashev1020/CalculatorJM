package calculator;

import java.util.Scanner;

public class Main {
    private static boolean arabic_numbers = true;

    private static String[] pars(String input) {
        String[] parser_input = input.split(" ");
        if (parser_input.length != 3) {
            Scanner inputValue = new Scanner(System.in);
            System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ пробелом");
            input = inputValue.nextLine();
            return pars(input);
        } else {
            return parser_input;
        }

    }

    public static void main(String[] args) {
        Scanner inputValueSC = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = inputValueSC.nextLine();
        Operation values;


        while (!input.isEmpty()) {
            String[] parser_input = Main.pars(input);
            String operation = parser_input[1];
            int value1 = 0;
            int value2 = 0;
            // Переводим в int. Если введены римские, выкинет исключение
            try {
                value1 = Integer.parseInt(parser_input[0]);
                value2 = Integer.parseInt(parser_input[2]);
            } catch (NumberFormatException e) {
                arabic_numbers = false;
                System.out.println("Введены римские цифры");
            }


            if (arabic_numbers) {
                if ((value1 < 1 || value1 > 10) || (value2 < 1 || value2 > 10)){
                    throw new IllegalArgumentException("Введены некорректные данные");
                }
                values = new Arabic(value1, value2);
            } else {

                values = new Rome(parser_input[0], parser_input[2]);
                if (( ((Rome) values).romesValue1Int < 1 || ((Rome) values).romesValue1Int > 10)
                        || (((Rome) values).romesValue2Int< 1 || ((Rome) values).romesValue2Int > 10)){
                    throw new IllegalArgumentException("Введены некорректные данные");
                }
            }



            if (operation.equals("+")) {
                values.sum();
            } else if (operation.equals("-")) {
                values.sub();
            } else if (operation.equals("/") || operation.equals(":")) {
                values.div();
            } else if (operation.equals("*") || operation.equals("x")) {
                values.mul();
            }
            if (arabic_numbers){
                System.out.println("Ответ: " + values.getResult());
            } else {
                System.out.println("Ответ: " + values.getStringResult());
            }
            System.out.println();
            System.out.print("Введите следующее выражение: ");
            input = inputValueSC.nextLine();
        }
        System.out.println("Вы вышли из калькулятора");
    }
}
