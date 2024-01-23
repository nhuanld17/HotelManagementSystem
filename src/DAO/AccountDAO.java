package DAO;

import java.sql.ResultSet;

import DTO.Account;

public class AccountDAO {
	public boolean isValidManager(Account account) {
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM manager WHERE username = '"+account.getUserName()+"'");
			
			if (resultSet.next()) {
				String password = resultSet.getString("password");
				if (account.getPassWord().equals(password)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isValidAdmin(Account account) {
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM admin where username = '"+account.getUserName()+"'");
			
			if (resultSet.next()) {
				String password = resultSet.getString("password");
				if (account.getPassWord().equals(password)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
