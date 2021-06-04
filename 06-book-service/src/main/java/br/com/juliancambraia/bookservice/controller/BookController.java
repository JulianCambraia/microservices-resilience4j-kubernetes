package br.com.juliancambraia.bookservice.controller;

import br.com.juliancambraia.bookservice.model.Book;
import br.com.juliancambraia.bookservice.proxy.CambioProxy;
import br.com.juliancambraia.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    private Environment environment;

    private final BookRepository repository;

    private final CambioProxy proxy;

    public BookController(Environment environment, BookRepository repository, CambioProxy proxy) {
        this.environment = environment;
        this.repository = repository;
        this.proxy = proxy;
    }

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        var book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not found");

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

    /**
     * Usando RestTemplate
     *
     * @param id
     * @param currency
     * @return
     */
    /*@GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {


        var book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }*/
}
