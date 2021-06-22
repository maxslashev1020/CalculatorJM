package calculator;

public class Rome extends Operation {
    private String romesValue1;
    private String romesValue2;
    int romesValue1Int;
    int romesValue2Int;
    private int resultInt;
    private String sign = "";
    private String resultString;
    private final String[] romeFigures = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    Rome(String value1, String value2) {
        this.romesValue1 = value1;
        this.romesValue2 = value2;
        this.romesValue1Int = convert_to_int(romesValue1);
        this.romesValue2Int = convert_to_int(romesValue2);
    }
    private String convert_result_to_Romes(int n, int ostatok) {
        ostatok = n % 10;
        if (ostatok != 0) {
            try {
                return convert_result_to_Romes(n - ostatok, 0) + romeFigures[ostatok - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                sign = "-";
                return convert_result_to_Romes(n - ostatok, 0) + romeFigures[(ostatok + 1) * -1];
            }
        }
        //Возможность вывести отрицательное римское число
        if (n > 0) {
            n = n - 10;
            return convert_result_to_Romes(n,0) + "X";
        } else if (n < 0) {
            n = n + 10;
            return convert_result_to_Romes(n,0) + "X";
        }   else {
            return sign;
        }
    }

    @Override
    public void sum() {
        resultInt = romesValue1Int + romesValue2Int;
        resultString = convert_result_to_Romes(resultInt, resultInt);
    }

    @Override
    public void sub() {
        resultInt = romesValue1Int - romesValue2Int;
        resultString = convert_result_to_Romes(resultInt, resultInt);
    }

    @Override
    public void div() {
        try {
            resultInt = romesValue1Int / romesValue2Int;
            resultString = convert_result_to_Romes(resultInt, resultInt);
        } catch (ArithmeticException e) {
            System.out.print("Проверьте правильность ввода римских цифр. Вероятно введены и арабские и римские одновременно. ");
            return;
        }

    }

    @Override
    public void mul() {
        resultInt = romesValue1Int * romesValue2Int;
        resultString = convert_result_to_Romes(resultInt, resultInt);
    }

    @Override
    public int getResult() {
        return resultInt;
    }
    public String getStringResult() {
        return resultString;
    }

    private int convert_to_int(String romes_value){
        char[] value_char = romes_value.toCharArray();
        int[] values_int = new int[value_char.length];
        for (int i = 0; i < value_char.length; i++) {
            switch (value_char[i]) {
                case 'I':
                    values_int[i] = 1;
                    break;
                case 'V':
                    values_int[i] = 5;
                    break;
                case 'X':
                    values_int[i] = 10;
                    break;

                default:
                    System.out.println("Содержится неверный символ. Проверьте правильность ввода римских цифр:" + "\n" +
                            "I = 1" + "\n" +
                            "V = 5" + "\n" +
                            "X = 10");
                    break;
            }
        }
        int result = values_int[0];
        for (int i = 0; i < values_int.length && values_int.length > i + 1; i++) {
            if (values_int[i] >= values_int[i+1]) {
                result += values_int[i+1];
            } else if (values_int[i] < values_int[i+1]) {
                result = result + values_int[i+1] - 2;
            }
        }
        return result;
    }

    public String getRomes_value1() {
        return romesValue1;
    }

    public String getRomes_value2() {
        return romesValue2;
    }

    public void setRomes_value1(String romes_value1) {
        this.romesValue1 = romes_value1;
    }

    public void setRomes_value2(String romes_value2) {
        this.romesValue2 = romes_value2;
    }

    public int getRomes_value1_int() {
        return romesValue1Int;
    }

    public int getRomes_value2_int() {
        return romesValue2Int;
    }

    public void setRomes_value1_int(int romes_value1_int) {
        this.romesValue1Int = romes_value1_int;
    }

    public void setRomes_value2_int(int romes_value2_int) {
        this.romesValue2Int = romes_value2_int;
    }
}
