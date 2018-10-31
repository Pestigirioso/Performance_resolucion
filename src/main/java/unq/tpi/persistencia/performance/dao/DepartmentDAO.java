package unq.tpi.persistencia.performance.dao;

import org.hibernate.Session;
import unq.tpi.persistencia.performance.model.Department;
import unq.tpi.persistencia.performance.service.SimpleEmployee;
import unq.tpi.persistencia.performance.service.runner.Runner;

import java.util.List;

public class DepartmentDAO extends BaseDAO<Department> {

    public DepartmentDAO() {
        super(Department.class, "Department");
    }

    /**
     * Gets the department by its name
     *
     * @return the department found, or null
     */
    public Department getByName(String name) {
        Session session = Runner.getCurrentSession();

        String hql = "from Department where name = :name";
        return session.createQuery(hql, Department.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<SimpleEmployee> getEmployees(String code) {
        String hql = "select new " + SimpleEmployee.class.getName() + "(e.firstName, e.lastName, s.amount, t) " +
                "from Department d inner join d.employees e inner join e.titles t inner join e.salaries s " +
                "where d.code = :code  and s.to = '9999-01-01'";

        return Runner.getCurrentSession()
                .createQuery(hql, SimpleEmployee.class)
                .setParameter("code", code)
                .getResultList();
    }
}
