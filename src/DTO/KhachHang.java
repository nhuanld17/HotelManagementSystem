package DTO;

import java.sql.Date;

public class KhachHang {
	private int stt;
	private String cccd;
	private String name;
	private Date ngaySinh;
	
	public KhachHang() {
		// TODO Auto-generated constructor stub
	}
	
	public KhachHang(String cccd, String name, Date ngaySinh) {
		this.cccd = cccd;
		this.name = name;
		this.ngaySinh = ngaySinh;
	}

	public KhachHang(int stt, String cccd, String name, Date ngaySinh) {
		this.stt = stt;
		this.cccd = cccd;
		this.name = name;
		this.ngaySinh = ngaySinh;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	public Object[] toObjects() {
		return new Object[] {""+stt,name,cccd,ngaySinh};
	}
}
