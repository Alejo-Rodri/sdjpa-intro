package guru.springframework.sdjpaintro.bootstrap;


import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Design", "111", "RandomHouse");
        log.info("bookDDD id pre-save: {}", bookDDD);

        Book savedDDD = bookRepository.save(bookDDD);
        log.info("bookDDD id post-save: {}", savedDDD);

        Book bookMJ = new Book("Modern Java", "23412", "Manning");
        bookRepository.save(bookMJ);

        bookRepository.findAll().forEach(book -> log.info("Book: {}", book));

    }
}
