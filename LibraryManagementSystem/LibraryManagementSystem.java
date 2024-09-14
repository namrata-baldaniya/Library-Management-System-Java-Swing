package projects.LibraryManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton adminLoginButton;
    private JButton librarianLoginButton;
    private JLabel userLabel, passLabel;

    public LibraryManagementSystem() {
        setTitle("Library Management System - Login");
        setSize(450, 300);  // Adjust the window size for a better UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Define fonts
        Font labelFont = new Font("Arial", Font.PLAIN, 18);  // Increased font size to 18
        Font buttonFont = new Font("Arial", Font.BOLD, 20);  // Increased font size to 20

        // Username label and text field
        userLabel = new JLabel("Username: ");
        userLabel.setFont(labelFont);
        userField = new JTextField(20);
        userField.setFont(labelFont);
        userField.setPreferredSize(new Dimension(250, 35));  // Increase size

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(userField, gbc);

        // Password label and text field
        passLabel = new JLabel("Password: ");
        passLabel.setFont(labelFont);
        passField = new JPasswordField(20);
        passField.setFont(labelFont);
        passField.setPreferredSize(new Dimension(250, 35));  // Increase size

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passField, gbc);

        // Admin login button
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setFont(buttonFont);
        adminLoginButton.setPreferredSize(new Dimension(180, 45));  // Increase size
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin("admin");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(adminLoginButton, gbc);

        // Librarian login button
        librarianLoginButton = new JButton("Librarian Login");
        librarianLoginButton.setFont(buttonFont);
        librarianLoginButton.setPreferredSize(new Dimension(180, 45));  // Increase size
        librarianLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin("librarian");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(librarianLoginButton, gbc);

        setLocationRelativeTo(null);
    }

    private void handleLogin(String role) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        Authentication auth = new Authentication();  // Initialize the Authentication class

        if (role.equals("admin")) {
            if (auth.adminLogin(username, password)) {
                new AdminPanel(new Library(), auth).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (role.equals("librarian")) {
            if (auth.librarianLogin(username, password)) {  // Use the authentication class for librarians
                new LibrarianPanel(new Library(), username).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Librarian credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}
