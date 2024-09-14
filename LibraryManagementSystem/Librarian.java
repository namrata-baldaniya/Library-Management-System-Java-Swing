package projects.LibraryManagementSystem;

public class Librarian {
    private String id;
    private String name;
    private String password;
    private String address;
    private String city;
    private String contactNo;

    public Librarian(String id, String name, String password, String address, String city, String contactNo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.city = city;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String toString() {
        return "Librarian [ID=" + id + ", Name=" + name + ", City=" + city + ", Contact No=" + contactNo + "]";
    }
}
