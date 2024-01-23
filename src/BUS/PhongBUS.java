package BUS;

import java.util.ArrayList;

import DAO.PhongDAO;
import DTO.Phong;

public class PhongBUS {
	public ArrayList<Phong> listPhongs() {
		return new PhongDAO().listPhongs();
	}

	public boolean isOrdered(Phong phong) {
		return new PhongDAO().isOrdered(phong);
	}

	public ArrayList<Phong> showEmpty() {
		return new PhongDAO().showEmpty();
	}

	public ArrayList<Phong> showIsOrdered() {
		return new PhongDAO().showIsOrdered();
	}
}
