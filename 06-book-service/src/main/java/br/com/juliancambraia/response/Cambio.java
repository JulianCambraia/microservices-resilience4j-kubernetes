package br.com.juliancambraia.response;

import lombok.Data;

@Data
public class Cambio {

    private Long id;

    private String from;

    private String to;

    private Double conversionFactor;

    private Double convertedValue;

    private String environment;

    public Cambio() {
    }

    public Cambio(Long id, String from, String to, Double conversionFactor, Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }
}
