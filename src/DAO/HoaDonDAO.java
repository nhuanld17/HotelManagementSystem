package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Chart;
import DTO.HoaDon;

public class HoaDonDAO {
	public ArrayList<HoaDon> listHoaDons(){
		try {
			ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM hoadon");
			
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String cccd = resultSet.getString("cccd");
				int roomNumber = resultSet.getInt("roomNumber");
				Date ngaynhan = resultSet.getDate("ngaynhan");
				Date ngaytra = resultSet.getDate("ngaytra");
				long gia = resultSet.getLong("gia");
				
				HoaDon hoaDon = new HoaDon(stt, cccd, roomNumber, ngaynhan, ngaytra, gia);
				hoaDons.add(hoaDon);
			}
			return hoaDons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<HoaDon>();
	}
	public void addHoaDon(HoaDon hoaDon) {
		try {
			new DBConn().updateDB("INSERT INTO hoadon(cccd,roomNumber,ngayNhan) VALUES('"+hoaDon.getCccd()+"','"+hoaDon.getRoomNumber()+"','"+hoaDon.getNgayNhan()+"')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteHoaDon(HoaDon hoaDon) {
		try {
			new DBConn().updateDB("DELETE FROM hoadon WHERE stt = '"+hoaDon.getStt()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateHoaDon(HoaDon hoaDon) {
		try {
			new DBConn().updateDB("UPDATE hoadon SET cccd = '"+hoaDon.getCccd()+"', roomNumber = '"+hoaDon.getRoomNumber()+"' WHERE stt = '"+hoaDon.getStt()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getSoPhong(int stt) {
		try {
			int sophong = 0;
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM hoadon WHERE stt = '"+stt+"'");
			while (resultSet.next()) {
				sophong = resultSet.getInt("roomNumber");
			}
			return sophong;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<HoaDon> findByCCCD(String cccd) {
		ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
		try {
			String query = "SELECT * FROM hoadon WHERE cccd = '"+cccd+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String Cccd = resultSet.getString("cccd");
				int roomNumber = resultSet.getInt("roomNumber");
				Date ngaynhan = resultSet.getDate("ngaynhan");
				Date ngaytra = resultSet.getDate("ngaytra");
				long gia = resultSet.getLong("gia");
				
				HoaDon hoaDon = new HoaDon(stt, Cccd, roomNumber, ngaynhan, ngaytra, gia);
				hoaDons.add(hoaDon);
			}
			return hoaDons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<HoaDon>();
	}
	public void calculateMoney(Date ngayTra, long gia, int stt) {
		try {
			new DBConn().updateDB("UPDATE hoadon SET ngaytra = '"+ngayTra+"', gia = '"+gia+"' WHERE stt = '"+stt+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getNameByStt(int stt) {
		String name = null;
		try {
			String query = "SELECT name \r\n"
					+ "FROM khachhang inner join hoadon\r\n"
					+ "on khachhang.stt = hoadon.stt\r\n"
					+ "where hoadon.stt = '"+stt+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	public long totalRevenue() {
		long total = 0;
		try {
			String query = "SELECT SUM(gia) \r\n"
					+ "FROM hoadon;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				total = resultSet.getLong("SUM(gia)");
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	public ArrayList<HoaDon> listPayed() {
		ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
		try {
			String query = "SELECT * \r\n"
					+ "FROM hoadon\r\n"
					+ "WHERE ngaytra IS NOT NULL;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String Cccd = resultSet.getString("cccd");
				int roomNumber = resultSet.getInt("roomNumber");
				Date ngaynhan = resultSet.getDate("ngaynhan");
				Date ngaytra = resultSet.getDate("ngaytra");
				long gia = resultSet.getLong("gia");
				
				HoaDon hoaDon = new HoaDon(stt, Cccd, roomNumber, ngaynhan, ngaytra, gia);
				hoaDons.add(hoaDon);
			}
			return hoaDons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<HoaDon>();
	}
	public ArrayList<HoaDon> listNotPayed() {
		ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
		try {
			String query = "SELECT * \r\n"
					+ "FROM hoadon\r\n"
					+ "WHERE ngaytra IS NULL;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int stt = resultSet.getInt("stt");
				String Cccd = resultSet.getString("cccd");
				int roomNumber = resultSet.getInt("roomNumber");
				Date ngaynhan = resultSet.getDate("ngaynhan");
				Date ngaytra = resultSet.getDate("ngaytra");
				long gia = resultSet.getLong("gia");
				
				HoaDon hoaDon = new HoaDon(stt, Cccd, roomNumber, ngaynhan, ngaytra, gia);
				hoaDons.add(hoaDon);
			}
			return hoaDons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<HoaDon>();
	}
	public long revenueDateToDate(Date thisDate, Date thatDate) {
		long total = 0;
		try {
			String query = "SELECT SUM(gia)\r\n"
					+ "FROM hoadon\r\n"
					+ "WHERE ngaytra between '"+thisDate+"' AND '"+thatDate+"';";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				total = resultSet.getLong("SUM(gia)");
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	public ArrayList<Chart> getChart() {
		ArrayList<Chart> charts = new ArrayList<Chart>();
		try {
			String query = "SELECT ngaytra, SUM(gia)\r\n"
					+ "FROM hoadon\r\n"
					+ "GROUP BY ngaytra;";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				Date ngayTra = resultSet.getDate("ngaytra");
				Long gia = resultSet.getLong("SUM(gia)");
				
				charts.add(new Chart(ngayTra, gia));
			}
			return charts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Chart>();
	}
	public ArrayList<Chart> getChartDate(Date date1, Date date2) {
		ArrayList<Chart> charts = new ArrayList<Chart>();
		try {
			String query = "SELECT ngaytra, SUM(gia) FROM hoadon WHERE ngaytra BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY ngaytra";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				Date ngayTra = resultSet.getDate("ngaytra");
				Long gia = resultSet.getLong("SUM(gia)");
				
				charts.add(new Chart(ngayTra, gia));
			}
			return charts;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Chart>();
	}
	public double januaryRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 1 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double februaryRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 2 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double MarchRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 3 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double AprilRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 4 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double MayRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 5 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double JuneRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 6 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double JulyRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 7 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double AugustRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 8 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double SeptemberRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 9 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double OctoberRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 10 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double NovemberRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 11 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public double DecemberRevenue(int year) {
		double res = 0.0;
		try {
			String query = "SELECT SUM(gia) FROM hoadon WHERE MONTH(ngaytra) = 12 AND YEAR(ngaytra) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(gia)");
				res = res / 1000000;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/////////////////////////////////////////////////////////////////
	public int januaryOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 1 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int februaryOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 2 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int MarchOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 3 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int AprilOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 4 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int MayOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 5 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int JuneOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 6 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int JulyOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 7 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int AugustOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 8 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int SeptemberOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 9 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int OctoberOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 10 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int NovemberOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 11 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int DecemberOrder(int year) {
		int res = 0;
		try {
			String query = "SELECT COUNT(ngaynhan) FROM hoadon WHERE MONTH(ngaynhan) = 12 AND YEAR(ngaynhan) = '"+year+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(ngaynhan)");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
