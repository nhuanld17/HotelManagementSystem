package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.KhachHangGUI;

public class KhachHangController implements ActionListener{
	private KhachHangGUI khachHangGUI;

	public KhachHangController(KhachHangGUI khachHangGUI) {
		this.khachHangGUI = khachHangGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = e.getActionCommand();
		
		switch (name) {
		case "THÊM":
			khachHangGUI.addKhachHang();
			break;
		case "XÓA":
			khachHangGUI.deleteKhachHang();
			break;
		case "SỬA":
			khachHangGUI.setForm();
			break;
		case "CẬP NHẬT":
			khachHangGUI.update();
			break;
		case "TÌM":
			khachHangGUI.findByName();
			break;
		case "TẢI LẠI":
			khachHangGUI.refreshTableKhachHang();
			break;
		case "Tìm":
			khachHangGUI.findByCCCD_HD();
			break;
		case "Tải lại":
			khachHangGUI.refreshTableHoaDon();
			break;
		case "Tính tiền":
			khachHangGUI.calculateMoney();
			break;
		case "Print":
			khachHangGUI.toExcel(khachHangGUI.table_hoadon.getModel());
			break;
		default:
			break;
		}
	}
}
