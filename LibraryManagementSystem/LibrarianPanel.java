package projects.LibraryManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibrarianPanel extends JFrame {
    private Library library;
    private String librarianId;

    public LibrarianPanel(Library library, String librarianId) {
        this.library = library;
        this.librarianId = librarianId;

        setTitle("Librarian Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize and layout components
        initComponents();
    }

    private void initComponents() {
        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Create and add buttons
        JButton addBookButton = new JButton("Add Book");
        JButton viewBooksButton = new JButton("View Books");
        JButton issueBookButton = new JButton("Issue Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton logoutButton = new JButton("Logout");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addBookButton, gbc);

        gbc.gridy = 1;
        add(viewBooksButton, gbc);

        gbc.gridy = 2;
        add(issueBookButton, gbc);

        gbc.gridy = 3;
        add(returnBookButton, gbc);

        gbc.gridy = 4;
        add(logoutButton, gbc);

        // Add action listeners
        addBookButton.addActionListener(e -> openAddBookForm());
        viewBooksButton.addActionListener(e -> viewBooks());
        issueBookButton.addActionListener(e -> issueBook());
        returnBookButton.addActionListener(e -> returnBook());
        logoutButton.addActionListener(e -> logout());
    }

    private void openAddBookForm() {
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField isbnField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book book = new Book(title, author, isbn);
            library.addBook(book);

            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewBooks() {
        List<Book> books = library.getBooks();

        String[] columnNames = {"Title", "Author", "ISBN", "Status"};
        String[][] data = new String[books.size()][4];

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getIsbn();
            data[i][3] = book.isBorrowed() ? "Borrowed" : "Available";
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "View Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private void issueBook() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN to issue:", "Issue Book", JOptionPane.QUESTION_MESSAGE);
        if (isbn != null && !isbn.trim().isEmpty()) {
            if (library.borrowBook(isbn, librarianId)) {
                JOptionPane.showMessageDialog(this, "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Book not available!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN to return:", "Return Book", JOptionPane.QUESTION_MESSAGE);
        if (isbn != null && !isbn.trim().isEmpty()) {
            library.returnBook(isbn);
            JOptionPane.showMessageDialog(this, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        dispose();
        new LibraryManagementSystem().setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage
        // Create a mock library and librarian ID for demonstration purposes
        Library mockLibrary = new Library();
        String mockLibrarianId = "1"; // Assuming librarian ID is "1"
        SwingUtilities.invokeLater(() -> new LibrarianPanel(mockLibrary, mockLibrarianId).setVisible(true));
    }
}
