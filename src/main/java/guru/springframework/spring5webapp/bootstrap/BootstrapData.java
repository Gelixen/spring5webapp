package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        Author ted = new Author("Ted", "Mosby");
        Book architecture101 = new Book("Atchitecture 101", "123");

        ted.getBooks().add(architecture101);
        architecture101.getAuthors().add(ted);

        authorRepository.save(ted);
        bookRepository.save(architecture101);

        Author samNewman = new Author("Sam", "Newman");
        Book microservices = new Book("Building microservices", "321");

        samNewman.getBooks().add(microservices);
        microservices.getAuthors().add(samNewman);

        authorRepository.save(samNewman);
        bookRepository.save(microservices);

        System.out.println("Bootstrap started");
        System.out.println("Books in repository: " + bookRepository.count());
    }
}
