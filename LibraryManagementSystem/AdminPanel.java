package projects.LibraryManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPanel extends JFrame {
    private Library library;
    private Authentication auth;

    public AdminPanel(Library library, Authentication auth) {
        this.library = library;
        this.auth = auth;

        setTitle("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create and add buttons
        JButton addLibrarianButton = new JButton("Add Librarian");
        JButton viewLibrariansButton = new JButton("View Librarians");
        JButton deleteLibrarianButton = new JButton("Delete Librarian");
        JButton logoutButton = new JButton("Logout");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addLibrarianButton, gbc);

        gbc.gridy = 1;
        add(viewLibrariansButton, gbc);

        gbc.gridy = 2;
        add(deleteLibrarianButton, gbc);

        gbc.gridy = 3;
        add(logoutButton, gbc);

        // Add action listeners
        addLibrarianButton.addActionListener(e -> openAddLibrarianForm());
        viewLibrariansButton.addActionListener(e -> viewLibrarians());
        deleteLibrarianButton.addActionListener(e -> deleteLibrarian());
        logoutButton.addActionListener(e -> logout());

        setLocationRelativeTo(null);
    }

    private void openAddLibrarianForm() {
        JTextField nameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField cityField = new JTextField(20);
        JTextField contactField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("Contact No:"));
        panel.add(contactField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Librarian", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String password = passwordField.getText().trim();
            String address = addressField.getText().trim();
            String city = cityField.getText().trim();
            String contact = contactField.getText().trim();

            // Validation
            if (name.isEmpty() || password.isEmpty() || address.isEmpty() || city.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Additional validation for contact number format
            if (!contact.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Contact number must be 10 digits long!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String id = String.valueOf(library.getLibrarians().size() + 1);
            Librarian librarian = new Librarian(id, name, password, address, city, contact);
            library.addLibrarian(librarian);
            auth.registerLibrarian(id, password);

            JOptionPane.showMessageDialog(this, "Librarian added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewLibrarians() {
        List<Librarian> librarians = library.getLibrarians();

        String[] columnNames = {"ID", "Name", "City", "Contact No"};
        String[][] data = new String[librarians.size()][4];

        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            data[i][0] = librarian.getId();
            data[i][1] = librarian.getName();
            data[i][2] = librarian.getCity();
            data[i][3] = librarian.getContactNo();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "View Librarians", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteLibrarian() {
        String id = JOptionPane.showInputDialog(this, "Enter Librarian ID to delete:", "Delete Librarian", JOptionPane.QUESTION_MESSAGE);
        if (id != null && !id.trim().isEmpty()) {
            boolean success = library.deleteLibrarian(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Librarian deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Librarian not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        dispose();
        LibraryManagementSystem.showLoginScreen();
    }

    public static void main(String[] args) {
        // Example usage
        Library mockLibrary = new Library();
        Authentication mockAuth = new Authentication(); // Assuming you have an Authentication class
        SwingUtilities.invokeLater(() -> new AdminPanel(mockLibrary, mockAuth).setVisible(true));
    }
}
