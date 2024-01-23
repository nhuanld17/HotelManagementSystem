package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.BillGUI;

public class BillController implements ActionListener{
	private BillGUI billGUI;

	public BillController(BillGUI billGUI) {
		this.billGUI = billGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch (cmd) {
		case "OK":
			billGUI.closeBill();
			break;
		case "PRINT":
			billGUI.printBill();
			break;
		default:
			break;
		}
	}
}
