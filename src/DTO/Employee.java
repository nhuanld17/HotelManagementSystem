package DTO;

import java.sql.Date;

public class Employee {
	private int id;
	private String name;
	private Date birthdate;
	private boolean gioitinh;
	private String position;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int id, String name, Date birthdate, boolean gioitinh, String position) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.gioitinh = gioitinh;
		this.position = position;
	}

	public Employee(String name, Date birthdate, boolean gioitinh, String position) {
		this.name = name;
		this.birthdate = birthdate;
		this.gioitinh = gioitinh;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Object[] toObjects() {
		return new Object[] {""+id,name,birthdate,gioitinh?"Nam":"Nữ", position};
	}
}
