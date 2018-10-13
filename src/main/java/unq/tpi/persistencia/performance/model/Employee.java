package unq.tpi.persistencia.performance.model;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees", indexes = {
		@Index(columnList = "first_name", name = "employees_first_name_idx"),
		@Index(columnList = "last_name", name = "employees_last_name_idx")
})
public class Employee {

	@Id
	@Column(name="emp_no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="birth_date")
	private Date birthDate;
	@Column(name="hire_date")
	private Date hireDate;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('M','F')")
	private Gender gender;

	@ManyToMany
	@JoinTable(name="dept_emp",
			joinColumns = @JoinColumn(name = "emp_no"),
			inverseJoinColumns = @JoinColumn(name = "dept_no"))
	@LazyCollection(value = LazyCollectionOption.EXTRA)
	@WhereJoinTable(clause = "to_date = '9999-01-01'")
	private Set<Department> departments;

	@ManyToMany
	@JoinTable(name="dept_emp",
			joinColumns = @JoinColumn(name = "emp_no"),
			inverseJoinColumns = @JoinColumn(name = "dept_no"))
	@WhereJoinTable(clause = "to_date != '9999-01-01'")
	@LazyCollection(value = LazyCollectionOption.EXTRA)
//	@OrderColumn(name="from_date", columnDefinition="date", insertable=false, updatable=false)
	private List<Department> historicDepartments;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
    @CollectionTable(
        name="titles",
        joinColumns=@JoinColumn(name="emp_no")
    )
	@Column(name="title")
	@BatchSize(size = 10)
    @Where(clause = "to_date = '9999-01-01'")
	private Set<String> titles;
	
	@ElementCollection
    @CollectionTable(
        name="titles",
        joinColumns=@JoinColumn(name="emp_no")
    )
	@Column(name="title")
	@LazyCollection(value = LazyCollectionOption.EXTRA)
    @Where(clause = "to_date != '9999-01-01'")
	private List<String> historicTitles;
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
	@BatchSize(size = 10)
    @JoinColumn(name="emp_no")
	@OrderBy(value = "from_date desc")
	private List<Salary> salaries;

	public Department getDepartment() {
		if (this.departments.isEmpty()) {
			return null;
		}
		return this.departments.iterator().next();
	}

	public double getSalary() {
		return this.salaries.get(0).getAmount();
	}

	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

	public String getTitle(){
		if (this.titles.isEmpty()) {
			return null;
		}
		return this.titles.iterator().next();
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public Set<Department> getDepartments() {
		return this.departments;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public int getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public List<Department> getHistoricDepartments() {
		return this.historicDepartments;
	}

	public Set<String> getTitles() {
		return this.titles;
	}

	public List<String> getHistoricTitles() {
		return this.historicTitles;
	}

	public List<Salary> getSalaries() {
		return this.salaries;
	}

}
