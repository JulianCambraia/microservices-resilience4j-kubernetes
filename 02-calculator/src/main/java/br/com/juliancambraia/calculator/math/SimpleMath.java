package br.com.juliancambraia.calculator.math;

public class SimpleMath {

    public SimpleMath() {
    }

    public Double sum(Double value1, Double value2) {
        return value1 + value2;
    }

    public Double subtraction(Double value1, Double value2) {
        return value1 - value2;
    }

    public Double multiplication(Double value1, Double value2) {
        return value1 * value2;
    }

    public Double division(Double value1, Double value2) {
        return value1 / value2;
    }

    public Double average(Double value1, Double value2) {
        return sum(value1, value2) / 2;
    }
}
