package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DTO.KhachHang;

public class KhachHangBUS {
	public ArrayList<KhachHang> listKhachHangs() {
		return new KhachHangDAO().listkKhachHangs();
	}
	public void addKhachHang(KhachHang khachHang) {
		new KhachHangDAO().addKhachHang(khachHang);
	}
	public void editKhachHang(KhachHang khachHang) {
		new KhachHangDAO().editkhachhang(khachHang);
	}
	public void deleteKhachHang(KhachHang khachHang) {
		new KhachHangDAO().deleteKhachHang(khachHang);
	}
	public boolean isDuplicateCCCD(String cccd) {
		return new KhachHangDAO().isDuplicateCCCD(cccd);
	}
	public boolean isDuplicateName(String name, String cccd) {
		return new KhachHangDAO().isDuplicateName(name,cccd);
	}
	public ArrayList<KhachHang> findByName(String name) {
		return new KhachHangDAO().findByName(name);
	}
	public void calculateMoney(Date ngayTra, long gia, int stt) {
		new HoaDonDAO().calculateMoney(ngayTra, gia, stt);
	}
	public ArrayList<KhachHang> sortByName() {
		return new KhachHangDAO().sortByName();
	}
	public ArrayList<KhachHang> sortByBirthDate() {
		return new KhachHangDAO().sortByBirthDate();
	}
	public boolean returned(int stt) {
		return new KhachHangDAO().returned(stt);
	}
}
