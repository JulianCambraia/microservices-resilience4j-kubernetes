package br.com.juliancambraia.cambioservice.controller;

import br.com.juliancambraia.cambioservice.model.Cambio;
import br.com.juliancambraia.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio endpoint")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    private Logger logger = LoggerFactory.getLogger(CambioController.class);

    private Environment environment;

    private CambioRepository repository;

    public CambioController(Environment environment, CambioRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @Operation(summary = "Get Cambio from currency")
    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {

        logger.info("Chamada a Cambio Service -> {}, {}, {}", amount, from, to);

        var cambio = repository.findByFromAndTo(from, to);
        if (cambio == null) throw new RuntimeException("Currency Unsupported");

        var port = environment.getProperty("local.server.port");
        cambio.setEnvironment(port);
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setConversionFactor(conversionFactor);

        return cambio;
    }
}
