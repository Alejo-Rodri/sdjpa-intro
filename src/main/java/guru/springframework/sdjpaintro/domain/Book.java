package guru.springframework.sdjpaintro.domain;

import jakarta.persistence.*;

import java.util.Objects;

// una entidad representa una tabla en la base de datos relacional
@Entity
@Table(name = "books")
public class Book {
    // anotacion para especificar la clave primaria
    @Id
    // AUTO es el valor por defecto cuando no se especifica nada
    // IDENTITY es el AUTO_INCREMENT de la base de datos
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;

    public Book() {
    }

    public Book(String title, String isbn, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    // definir la igualdad
    // sobreescribir esto no es necesario pero es una buena practica
    // porque en un futuro si al realizar cosas complejas los objetos se pueden comportar inesperadamente
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
