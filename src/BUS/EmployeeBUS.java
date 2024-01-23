package BUS;

import java.util.ArrayList;

import DAO.EmployeeDAO;
import DTO.Employee;

public class EmployeeBUS {
	public ArrayList<Employee> listEmployees() {
		return new EmployeeDAO().listEmployees();
	}
	public void addEmployee(Employee employee) {
		new EmployeeDAO().addEmployee(employee);
	}
	public void editEmployee(Employee employee) {
		new EmployeeDAO().editEmployee(employee);
	}
	public void deleteEmployee(Employee employee) {
		new EmployeeDAO().deleteEmployee(employee);
	}
	public ArrayList<Employee> findByName(String name) {
		return new EmployeeDAO().findByName(name);
	}
	public ArrayList<Employee> sortByName() {
		return new EmployeeDAO().sortByName();
	}
	public ArrayList<Employee> sortByBirthDate() {
		return new EmployeeDAO().sortByBirthDate();
	}
}
