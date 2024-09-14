package projects.LibraryManagementSystem;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Librarian> librarians;

    public Library() {
        this.books = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    // Book Management Methods
    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean borrowBook(String ISBN, String userID) {
        for (Book book : books) {
            if (book.getIsbn().equals(ISBN) && !book.isBorrowed()) {
                book.setBorrowed(true);
                return true;
            }
        }
        return false;
    }

    public void returnBook(String ISBN) {
        for (Book book : books) {
            if (book.getIsbn().equals(ISBN) && book.isBorrowed()) {
                book.setBorrowed(false);
            }
        }
    }

    // Librarian Management Methods
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public boolean deleteLibrarian(String id) {
        // Implement logic to delete a librarian and return true if successful, false otherwise
        for (Librarian librarian : librarians) {
            if (librarian.getId().equals(id)) {
                librarians.remove(librarian);
                return true; // Successfully deleted
            }
        }
        return false; // Not found
    }
}
