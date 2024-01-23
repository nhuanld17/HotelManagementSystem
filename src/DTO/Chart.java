package DTO;

import java.sql.Date;

public class Chart {
	private Date ngayTra;
	private long gia;
	
	public Chart(Date ngayTra, long gia) {
		this.ngayTra = ngayTra;
		this.gia = gia;
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
	
}
