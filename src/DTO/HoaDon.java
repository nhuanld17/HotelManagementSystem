package DTO;

import java.sql.Date;

public class HoaDon {
	private int stt;
	private String cccd;
	private int roomNumber;
	private Date ngayNhan;
	private Date ngayTra;
	private long gia;
	
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public HoaDon(int stt, String cccd, int roomNumber, Date ngayNhan, Date ngayTra, long gia) {
		this.stt = stt;
		this.cccd = cccd;
		this.roomNumber = roomNumber;
		this.ngayNhan = ngayNhan;
		this.ngayTra = ngayTra;
		this.gia = gia;
	}
	
	public HoaDon(int stt, String cccd, int roomNumber) {
		this.stt = stt;
		this.cccd = cccd;
		this.roomNumber = roomNumber;
	}

	public HoaDon(int stt, String cccd, int roomNumber, Date ngayNhan) {
		this.stt = stt;
		this.cccd = cccd;
		this.roomNumber = roomNumber;
		this.ngayNhan = ngayNhan;
	}

	public HoaDon(String cccd, int roomNumber, Date ngayNhan, Date ngayTra, long gia) {
		this.cccd = cccd;
		this.roomNumber = roomNumber;
		this.ngayNhan = ngayNhan;
		this.ngayTra = ngayTra;
		this.gia = gia;
	}
	
	
	public HoaDon(int stt, String cccd) {
		this.stt = stt;
		this.cccd = cccd;
	}

	public HoaDon(String cccd, int roomNumber, Date ngayNhan) {
		this.cccd = cccd;
		this.roomNumber = roomNumber;
		this.ngayNhan = ngayNhan;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}
	public Object[] toObjects() {
		return new Object[] {""+stt,cccd,""+roomNumber,ngayNhan,ngayTra,""+gia};
	}
}
