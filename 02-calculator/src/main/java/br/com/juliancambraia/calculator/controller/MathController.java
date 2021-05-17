package br.com.juliancambraia.calculator.controller;

import br.com.juliancambraia.calculator.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);

        return sum;
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        Double result = convertToDouble(numberOne) - convertToDouble(numberTwo);

        return result;
    }

    @RequestMapping(value = "/multply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        Double result = convertToDouble(numberOne) * convertToDouble(numberTwo);

        return result;
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);

        if (convertToDouble(numberTwo) <= 0) {
            throw new UnsuportedMathOperationException("Divisão por zero não permitida!");
        }
        Double result = convertToDouble(numberOne) / convertToDouble(numberTwo);

        return result;
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);

        Double result = sum(numberOne, numberTwo) / 2;

        return result;
    }

    @RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable("number") String number) throws Exception {
        unsuportedException(number, "0");

        Double result = Math.sqrt(convertToDouble(number));

        return result;
    }

    private void unsuportedException(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
    }

    private Double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0D;
        String number = stringNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
