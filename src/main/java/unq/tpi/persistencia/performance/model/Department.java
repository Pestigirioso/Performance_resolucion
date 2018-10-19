package unq.tpi.persistencia.performance.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", length = 4)
    private String code;

    @Column(name = "dept_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "dept_emp", joinColumns = @JoinColumn(name = "dept_no"), inverseJoinColumns = @JoinColumn(name = "emp_no"))
    @WhereJoinTable(clause = "to_date = '9999-01-01'")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<Employee> employees;

    @ManyToMany
    @JoinTable(name = "dept_emp", joinColumns = @JoinColumn(name = "dept_no"), inverseJoinColumns = @JoinColumn(name = "emp_no"))
    @WhereJoinTable(clause = "to_date != '9999-01-01'")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<Employee> historicEmployees;

    @ManyToMany
    @JoinTable(name = "dept_manager", joinColumns = @JoinColumn(name = "dept_no"), inverseJoinColumns = @JoinColumn(name = "emp_no"))
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private List<Employee> managers;

    public Double getTotalSalaries() {
        return this.employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public Employee getManager() {
        // last manager
        return this.managers.get(this.managers.size() - 1);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public Set<Employee> getHistoricEmployees() {
        return this.historicEmployees;
    }

    public List<Employee> getManagers() {
        return this.managers;
    }

}
