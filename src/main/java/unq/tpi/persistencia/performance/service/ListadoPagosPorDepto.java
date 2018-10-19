package unq.tpi.persistencia.performance.service;

import unq.tpi.persistencia.performance.dao.DepartmentDAO;

import java.util.List;

public class ListadoPagosPorDepto extends AbstractListado {

    private final String id;

    public ListadoPagosPorDepto(String fileName, String id) {
        super(fileName);
        this.id = id;
    }

    @Override
    public void doListado() throws Exception {
        List<SimpleEmployee> employees = new DepartmentDAO().getEmployees(this.id);
        this.newLine();
        this.addColumn("Total").addColumn(employees.stream().mapToDouble(SimpleEmployee::getSalary).sum()).newLine();
        this.newLine();
        this.addColumn("Nombre").addColumn("Titulo").addColumn("Monto").newLine();
        employees.forEach(it -> this.addColumn(it.getFullName())
                .addColumn(it.getTitle())
                .addColumn(it.getSalary())
                .newLine());
    }

}
