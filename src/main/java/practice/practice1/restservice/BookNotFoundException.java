package practice.practice1.restservice;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }
}
