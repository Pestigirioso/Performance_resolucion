package unq.tpi.persistencia.performance.service;

import unq.tpi.persistencia.performance.dao.EmployeeDAO;

import java.util.List;

public class ListadoMaximosSalarios extends AbstractListado {

    public ListadoMaximosSalarios(String fileName) {
        super(fileName);
    }

    @Override
    public void doListado() throws Exception {
        List<SimpleEmployee> employees = new EmployeeDAO().getMaxSalaries();
        this.addColumn("Nombre").addColumn("Sueldo").newLine();
        employees.forEach(it -> this.addColumn(it.getFullName())
                .addColumn(it.getSalary())
                .newLine());
    }
}
