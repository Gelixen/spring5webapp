package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Bootstrap started");

        Publisher publisher = new Publisher();
        publisher.setName("SFG");
        publisher.setCity("NY");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publishers count in repo: " + publisherRepository.count());

        Author ted = new Author("Ted", "Mosby");
        Book architecture101 = new Book("Atchitecture 101", "123");

        ted.getBooks().add(architecture101);
        architecture101.getAuthors().add(ted);

        architecture101.setPublisher(publisher);
        publisher.getBooks().add(architecture101);

        authorRepository.save(ted);
        bookRepository.save(architecture101);
        publisherRepository.save(publisher);

        Author samNewman = new Author("Sam", "Newman");
        Book microservices = new Book("Building microservices", "321");

        samNewman.getBooks().add(microservices);
        microservices.getAuthors().add(samNewman);
        publisher.getBooks().add(microservices);

        authorRepository.save(samNewman);
        bookRepository.save(microservices);
        publisherRepository.save(publisher);

        System.out.println("Books in repository: " + bookRepository.count());
        System.out.println("Publisher books: " + publisher.getBooks().size());
    }
}
