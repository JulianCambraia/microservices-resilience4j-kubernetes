package br.com.juliancambraia.calculator.converter;

public final class NumberConverter {
    public static Double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0D;
        String number = stringNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
