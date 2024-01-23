package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.EmployeeGUI;

public class EmployeeController implements ActionListener{
	private EmployeeGUI employeeGUI;

	public EmployeeController(EmployeeGUI employeeGUI) {
		this.employeeGUI = employeeGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		
		switch (name) {
		case "THÊM":
			employeeGUI.addEmployee();
			break;
		case "XÓA":
			employeeGUI.deleteEmployee();
			break;
		case "SỬA":
			employeeGUI.setForm();
			break;
		case "CẬP NHẬT":
			employeeGUI.update();
			break;
		case "TÌM":
			employeeGUI.findByName();
			break;
		case "TẢI LẠI":
			employeeGUI.reloadTableNhanVien();
			break;
		case "Tìm":
			employeeGUI.findByCCCD();
			break;
		case "Tải lại":
			employeeGUI.reloadTableHoaDon();
			break;
		case "Xóa":
			employeeGUI.deleteHoaDon();
			break;
		case "Chi tiết":
			employeeGUI.showDetails();
			break;
		default:
			break;
		}
	}
}
