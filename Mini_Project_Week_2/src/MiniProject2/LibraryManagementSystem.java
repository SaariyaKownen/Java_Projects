package MiniProject2;
import java.util.ArrayList;
abstract class User {
    // Encapsulation: private attributes
    private String name;
    private int userId;

    // Constructor
    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    // Abstract method
    public abstract void displayUserType();
}

// Inheritance: StudentUser inherits User
class StudentUser extends User {

    public StudentUser(String name, int userId) {
        super(name, userId);
    }

    // Method overriding
    @Override
    public void displayUserType() {
        System.out.println("User Type: Student");
    }
}

// Book class
class Book {
    // Encapsulation: private attributes
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    // Constructor
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setter
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Display book information
    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " +
                (available ? "Available" : "Borrowed"));
        System.out.println("---------------------------");
    }
}

// Library class
class Library {
    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Add Book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: "
                + book.getTitle());
    }

    // Borrow Book
    public void borrowBook(int bookId) {
        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (book.isAvailable()) {
                    book.setAvailable(false);

                    System.out.println("Book borrowed successfully: "
                            + book.getTitle());
                } else {
                    System.out.println("Sorry, the book is already borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Return Book
    public void returnBook(int bookId) {
        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (!book.isAvailable()) {
                    book.setAvailable(true);

                    System.out.println("Book returned successfully: "
                            + book.getTitle());
                } else {
                    System.out.println("This book was not borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Display all books
    public void displayBooks() {
        System.out.println("\n===== Library Books =====");

        for (Book book : books) {
            book.displayBookInfo();
        }
    }
}

// Main class
public class LibraryManagementSystem {

    public static void main(String[] args) {

        // Create Library object
        Library library = new Library();

        // Create User object
        StudentUser user = new StudentUser("Saariya", 101);

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("User Name: " + user.getName());
        System.out.println("User ID: " + user.getUserId());

        user.displayUserType();

        System.out.println();

        // Create Book objects
        Book book1 = new Book(1, "Java Programming", "James Gosling");
        Book book2 = new Book(2, "Python Basics", "Guido van Rossum");
        Book book3 = new Book(3, "Data Structures", "Mark Allen");

        // Add books
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Display books
        library.displayBooks();

        // Borrow a book
        System.out.println("===== BORROW BOOK =====");
        library.borrowBook(1);

        // Display books after borrowing
        library.displayBooks();

        // Try to borrow the same book again
        System.out.println("===== BORROW SAME BOOK =====");
        library.borrowBook(1);

        // Return the book
        System.out.println("\n===== RETURN BOOK =====");
        library.returnBook(1);

        // Display final book status
        library.displayBooks();
    }
}
