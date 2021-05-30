package br.com.juliancambraia.cambioservice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Data
public class Cambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 3)
    private String from;

    @Column(name = "to_currency", nullable = false, length = 3)
    private String to;

    @Column(nullable = false, length = 3)
    private BigDecimal conversionFactor;

    @Transient
    private BigDecimal convertedValue;

    @Transient
    private String environment;

    public Cambio() {
    }

    public Cambio(Long id, String from, String to, BigDecimal conversionFactor, BigDecimal convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }
}
