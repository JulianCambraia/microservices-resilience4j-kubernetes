package br.com.juliancambraia.calculator.controller;

import br.com.juliancambraia.calculator.converter.NumberConverter;
import br.com.juliancambraia.calculator.exception.UnsuportedMathOperationException;
import br.com.juliancambraia.calculator.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private static SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        return simpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        return simpleMath.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        return simpleMath.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);

        if (NumberConverter.convertToDouble(numberTwo) <= 0) {
            throw new UnsuportedMathOperationException("Divisão por zero não permitida!");
        }
        return simpleMath.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        unsuportedException(numberOne, numberTwo);
        return sum(numberOne, numberTwo) / 2;
    }

    @RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable("number") String number) throws Exception {
        unsuportedException(number, "0");
        return Math.sqrt(NumberConverter.convertToDouble(number));
    }

    private void unsuportedException(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
    }


}
