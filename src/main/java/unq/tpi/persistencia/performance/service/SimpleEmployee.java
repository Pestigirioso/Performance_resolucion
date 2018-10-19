package unq.tpi.persistencia.performance.service;

public class SimpleEmployee {
    private double salary;
    private String title;
    private String firstName;
    private String lastName;

    public SimpleEmployee(String firstName, String lastName, double salary, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.title = title;
    }

    public String getFullName() {
        return firstName + ", " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public Double getSalary() {
        return salary;
    }
}
