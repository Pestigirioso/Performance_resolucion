package unq.tpi.persistencia.performance.service;

public class SimpleEmployee {
    private double salary;
    private String title;
    private String firstName;
    private String lastName;

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
