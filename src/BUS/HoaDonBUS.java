package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DTO.Chart;
import DTO.HoaDon;

public class HoaDonBUS {
	public ArrayList<HoaDon> listHoaDons() {
		return new HoaDonDAO().listHoaDons();
	}
	public void addHoaDon(HoaDon hoaDon) {
		new HoaDonDAO().addHoaDon(hoaDon);
	}
	public void deleteHoaDon(HoaDon hoaDon) {
		new HoaDonDAO().deleteHoaDon(hoaDon);
	}
	public int getSoPhong(int stt) {
		return new HoaDonDAO().getSoPhong(stt);
	}
	public void updateHoaDon(HoaDon hoaDon) {
		new HoaDonDAO().updateHoaDon(hoaDon);
	}
	public ArrayList<HoaDon> findByCCCD(String cccd) {
		return new HoaDonDAO().findByCCCD(cccd);
	}
	public void calculateMoney(Date ngayTra, long gia, int stt) {
		new HoaDonDAO().calculateMoney(ngayTra, gia, stt);
	}
	public String getNameByStt(int stt) {
		return new HoaDonDAO().getNameByStt(stt);
	}
//	public boolean isPayed(int stt) {
//		return new HoaDonDAO().isPayed(stt);
//	}
	public long totalRevenue() {
		return new HoaDonDAO().totalRevenue();
	}
	public long revenueCurrentDate(Date currentDate) {
		return new KhachHangDAO().revenueCurrentDate(currentDate);
	}
	public ArrayList<HoaDon> listPayed() {
		return new HoaDonDAO().listPayed();
	}
	public ArrayList<HoaDon> listNotPayed() {
		return new HoaDonDAO().listNotPayed();
	}
	public long revenueDateToDate(Date thisDate, Date thatDate) {
		return new HoaDonDAO().revenueDateToDate(thisDate,thatDate);
	}
	public ArrayList<Chart> getChart() {
		return new HoaDonDAO().getChart();
	}
	public ArrayList<Chart> getChartDate(Date date1, Date date2) {
		return new HoaDonDAO().getChartDate(date1,date2);
	}
}
