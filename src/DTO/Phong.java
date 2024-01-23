package DTO;

public class Phong {
	private int soPhong;
	
	public Phong(int soPhong) {
		this.soPhong = soPhong;
	}

	public int getSoPhong() {
		return soPhong;
	}

	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	
	public Object[] toObjects() {
		return new Object[] {""+soPhong};
	}
}
