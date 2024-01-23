package BUS;

import DAO.AccountDAO;
import DTO.Account;

public class AccountBUS {
	public boolean isValidManager(Account account) {
		if (new AccountDAO().isValidManager(account)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isValidAdmin(Account account) {
		if (new AccountDAO().isValidAdmin(account)) {
			return true;
		}else {
			return false;
		}
	}
}
