package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.KhachHang;

public class KhachHangDAO {
	public ArrayList<KhachHang> listkKhachHangs() {
		try {
			ArrayList<KhachHang> result = new ArrayList<KhachHang>();
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM khachhang");
			while (resultSet.next()) {
				result.add(new KhachHang(resultSet.getInt("stt"),resultSet.getString("cccd"),resultSet.getString("name"),resultSet.getDate("ngaySinh")));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<KhachHang>();
	}
	public void addKhachHang(KhachHang khachHang) {
		try {
			new DBConn().updateDB("INSERT INTO khachhang(cccd,name,ngaysinh) VALUES('"+khachHang.getCccd()+"','"+khachHang.getName()+"','"+khachHang.getNgaySinh()+"')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteKhachHang(KhachHang khachHang) {
		try {
			new DBConn().updateDB("DELETE FROM khachhang WHERE stt = '"+khachHang.getStt()+"' AND cccd = '"+khachHang.getCccd()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editkhachhang(KhachHang khachHang) {
		try {
			new DBConn().updateDB("UPDATE khachhang SET cccd = '"+khachHang.getCccd()+"', name = '"+khachHang.getName()+"',ngaysinh = '"+khachHang.getNgaySinh()+"' WHERE stt = '"+khachHang.getStt()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isDuplicateCCCD(String cccd) {
		try {
			ArrayList<String> strings = new ArrayList<String>();
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM khachhang");
			while (resultSet.next()) {
				String CCCD = resultSet.getString("cccd");
				strings.add(CCCD);
			}
			if (strings.contains(cccd)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDuplicateName(String name, String cccd) {
		try {
			String Name = null;
			ResultSet resultSet = new DBConn().queryDB("SELECT name FROM khachhang WHERE cccd = '"+cccd+"'");
			while (resultSet.next()) {
				Name = resultSet.getString("name");
			}
			if (!Name.equals(name)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<KhachHang> findByName(String name) {
		ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
		try {
			String query = "SELECT * FROM khachhang WHERE name LIKE '%" + name + "%'";
			ResultSet resultSet = new DBConn().queryDB(query);

			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String Cccd = resultSet.getString("cccd");
				String Name = resultSet.getString("name");
				Date ngaySinh = resultSet.getDate("ngaysinh");
				
				KhachHang khachHang = new KhachHang(stt, Cccd, Name, ngaySinh);
				khachHangs.add(khachHang);
			}
			return khachHangs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<KhachHang>();
	}
	public ArrayList<KhachHang> sortByName() {
		ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
		try {
			String query = "SELECT * \r\n"
					+ "FROM khachhang\r\n"
					+ "ORDER BY name;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String cccd = resultSet.getString("cccd");
				String name = resultSet.getString("name");
				Date ngaySinh = resultSet.getDate("ngaysinh");
				
				khachHangs.add(new KhachHang(stt, cccd, name, ngaySinh));
			}
			return khachHangs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<KhachHang>();
	}
	public ArrayList<KhachHang> sortByBirthDate() {
		ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
		try {
			String query = "SELECT * \r\n"
					+ "FROM khachhang\r\n"
					+ "ORDER BY ngaysinh;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String cccd = resultSet.getString("cccd");
				String name = resultSet.getString("name");
				Date ngaySinh = resultSet.getDate("ngaysinh");
				
				khachHangs.add(new KhachHang(stt, cccd, name, ngaySinh));
			}
			return khachHangs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<KhachHang>();
	}
	public long revenueCurrentDate(Date currentDate) {
		long price = 0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE ngaytra = '"+currentDate+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				price = resultSet.getLong("SUM(gia)");
			}
			return price;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	public boolean returned(int stt) {
		try {
			String query = "SELECT ngaytra FROM hoadon WHERE stt = '"+stt+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			Date date = null;
			while (resultSet.next()) {
				date = resultSet.getDate("ngayTra");
			}
			// Nếu ngày trả là null, tức là khách hàng có stt vẫn chưa trả phòng
			if (date == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
