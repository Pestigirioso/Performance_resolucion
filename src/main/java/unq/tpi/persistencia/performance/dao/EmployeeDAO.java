package unq.tpi.persistencia.performance.dao;

import org.hibernate.Session;

import unq.tpi.persistencia.performance.model.Employee;
import unq.tpi.persistencia.performance.service.runner.Runner;

public class EmployeeDAO extends BaseDAO<Employee> {

	public EmployeeDAO() {
		super(Employee.class, "Employee");
	}
	
	/**
	 * Gets an employee by name and lastName
	 * @return the employee found, otherwise null
	 */
	public Employee getByName(String name, String lastName) {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Employee where firstName = :name and lastName = :lastName";
		return session.createQuery(hql, Employee.class)
				.setParameter("name", name)
				.setParameter("lastName", lastName)
				.getSingleResult();
	}

	
}
