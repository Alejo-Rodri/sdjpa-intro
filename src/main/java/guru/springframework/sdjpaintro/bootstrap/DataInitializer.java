package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import guru.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(
            BookRepository bookRepository,
            AuthorUuidRepository authorUuidRepository,
            BookUuidRepository bookUuidRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "111", "RandomHouse", null);
        Book savedDDD = bookRepository.save(bookDDD);

        Book bookMJ = new Book("Modern Java", "23412", "Manning", null);
        Book savedMJ = bookRepository.save(bookMJ);

        bookRepository.findAll().forEach(book -> log.info("Book: {}", book));

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Black");
        var savedAuthor = authorUuidRepository.save(authorUuid);
        log.info("Saved author UUID: {}", savedAuthor);

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("Crimen & Castigo");
        var savedBook = bookUuidRepository.save(bookUuid);
        log.info("Saved book UUID: {}", savedBook);
    }
}
