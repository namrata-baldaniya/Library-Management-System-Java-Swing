package projects.LibraryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private Map<String, String> adminCredentials;
    private Map<String, String> librarianCredentials;

    public class Authentication {
    
        public boolean adminLogin(String username, String password) {
            // Validate admin credentials here (from database or predefined credentials)
            return username.equals("admin") && password.equals("admin123");
        }
    
        public boolean librarianLogin(String username, String password) {
            // Validate librarian credentials here (from database or predefined credentials)
            return username.equals("librarian") && password.equals("lib123");
        }
    }
    
}
