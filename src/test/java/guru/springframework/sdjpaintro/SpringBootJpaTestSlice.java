package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//  brings only the necessary layer for test, this way we avoid overcharging the test
// the minimum context for testing
@DataJpaTest
// lets us specify the order in which the test cases are executed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//  lets us add to the context a specific package i.e. the one that initializes the data
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;

    /*
    The default behaviour of Spring Boot on a test context is to run the transactions and roll them back
     with the Rollback or Commit annotation we deactivate this behaviour
     */
    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();
        // we expect nothing to be initialized
        assertThat(countBefore).isEqualTo(2);

        // the values don't care
        bookRepository.save(new Book("", "", ""));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        // we expect nothing to be initialized
        assertThat(countBefore).isEqualTo(3);
    }
}
