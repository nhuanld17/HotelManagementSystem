package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Employee;

public class EmployeeDAO {
	public ArrayList<Employee> listEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM employee");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				boolean gioitinh = resultSet.getBoolean("gioitinh");
				String position = resultSet.getString("position");
				
				employees.add(new Employee(id, name, ngaysinh, gioitinh, position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Employee>();
	}
	public void addEmployee(Employee employee) {
		try {
			int gioitinhInt = employee.isGioitinh() ? 1 : 0;
			new DBConn().updateDB("INSERT into employee(name,ngaysinh,gioitinh,position) values('"+employee.getName()+"','"+employee.getBirthdate()+"','"+gioitinhInt+"','"+employee.getPosition()+"')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editEmployee(Employee employee) {
		try {
			int gioiTinhInt = employee.isGioitinh() ? 1 : 0;
			new DBConn().updateDB("UPDATE employee SET name = '"+employee.getName()+"',ngaysinh = '"+employee.getBirthdate()+"',gioitinh = '"+gioiTinhInt+"',position = '"+employee.getPosition()+"' WHERE id = '"+employee.getId()+"'");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void deleteEmployee(Employee employee) {
		try {
			new DBConn().updateDB("DELETE from employee WHERE id = '"+employee.getId()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Employee> findByName(String name) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM employee WHERE name LIKE '%" + name + "%'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				boolean gioitinh = resultSet.getBoolean("gioitinh");
				String position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, ngaysinh, gioitinh, position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Employee>();
	}
	public ArrayList<Employee> sortByName() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM employee \r\n"
					+ "ORDER BY name;";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				boolean gioitinh = resultSet.getBoolean("gioitinh");
				String position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, ngaysinh, gioitinh, position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Employee>();
	}
	public ArrayList<Employee> sortByBirthDate() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM employee \r\n"
					+ "ORDER BY ngaysinh;";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date ngaysinh = resultSet.getDate("ngaysinh");
				boolean gioitinh = resultSet.getBoolean("gioitinh");
				String position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, ngaysinh, gioitinh, position));
			}
			return employees;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Employee>();
	}
}
