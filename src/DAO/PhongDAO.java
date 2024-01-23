package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Phong;

public class PhongDAO {
	public ArrayList<Phong> listPhongs() {
		try {
			ArrayList<Phong> phongs = new ArrayList<Phong>();
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM phong");
			while (resultSet.next()) {
				int roomNumber = resultSet.getInt("soPhong");
				Phong phong = new Phong(roomNumber);
				phongs.add(phong);
			}
			return phongs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Phong>();
	}

	public boolean isOrdered(Phong phong) {
		try {
			ArrayList<Integer> phongs = new ArrayList<Integer>();
			ResultSet resultSet = new DBConn().queryDB("SELECT sophong "
					+ "from phong inner join hoadon on phong.sophong = hoadon.roomNumber "
					+ "where ngaytra is null");
			while (resultSet.next()) {
				int sophong = resultSet.getInt("sophong");
				phongs.add(sophong);
			}
			if (phongs.contains(phong.getSoPhong())) {
				return true;
			}
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Phong> showEmpty() {
		ArrayList<Phong> phongs = new ArrayList<Phong>();
		try {
			String query = "SELECT sophong \r\n"
					+ "FROM phong \r\n"
					+ "where sophong not in (\r\n"
					+ "	SELECT sophong \r\n"
					+ "FROM phong inner join hoadon\r\n"
					+ "on phong.sophong = hoadon.roomNumber\r\n"
					+ "where ngaytra is null\r\n"
					+ "	) order by sophong";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int sophong = resultSet.getInt("sophong");
				phongs.add(new Phong(sophong));
			}
			return phongs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Phong>();
	}

	public ArrayList<Phong> showIsOrdered() {
		ArrayList<Phong> phongs = new ArrayList<Phong>();
		try {
			String query = "SELECT sophong \r\n"
					+ "FROM phong inner join hoadon\r\n"
					+ "on phong.sophong = hoadon.roomNumber\r\n"
					+ "where ngaytra is null order by sophong";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int sophong = resultSet.getInt("sophong");
				phongs.add(new Phong(sophong));
			}
			return phongs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Phong>();
	}
}
