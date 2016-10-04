package unq.tpi.persistencia.performance.service;

import unq.tpi.persistencia.performance.dao.DepartmentDAO;
import unq.tpi.persistencia.performance.model.Department;

public class ListadoPagosPorDepto extends AbstractListado {

	private final String id;

	public ListadoPagosPorDepto(String fileName, String id) {
		super(fileName);
		this.id = id;
	}

	@Override
	public void doListado() throws Exception {
		Department depto = new DepartmentDAO().getByCode(this.id);

		this.newLine();
		this.addColumn("Total").addColumn(depto.getTotalSalaries()).newLine();
		this.newLine();
		
		this.addColumn("Nombre").addColumn("Titulo").addColumn("Monto").newLine();
		depto.getEmployees().forEach(it -> {
			this.addColumn(it.getFullName())
				.addColumn(it.getTitle())
				.addColumn(it.getSalary())
				.newLine();
		});
	}
	
}
