import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(">> Book added successfully.\n");
    }

    public void listBooks() {
        System.out.println("--- Library Catalog ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library yet.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).describe());
            }
        }
        System.out.println();
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isBorrowed()) {
                    book.borrow();
                    System.out.println(">> You borrowed '" + book.getTitle() + "'.\n");
                } else {
                    System.out.println(">> Sorry, '" + book.getTitle() + "' is already borrowed.\n");
                }
                return;
            }
        }
        System.out.println(">> Book not found in the library.\n");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println(">> You returned '" + book.getTitle() + "'.\n");
                } else {
                    System.out.println(">> '" + book.getTitle() + "' is not currently borrowed.\n");
                }
                return;
            }
        }
        System.out.println(">> Book not found in the library.\n");
    }

    public void searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(">> Book found: " + book.describe() + "\n");
                return;
            }
        }
        System.out.println(">> Book not found in the library.\n");
    }
}