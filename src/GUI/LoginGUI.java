package GUI;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import BUS.AccountBUS;
import DTO.Account;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-hotel-48.png"));
		setTitle("LOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN ѬLOGIN Ѭ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 907, 513);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 891, 474);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(419, 31, 462, 412);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(31, 86, 113, 57);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(31, 142, 113, 25);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(Color.BLACK, 2));
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Segoe UI", Font.BOLD, 20));
		textField.setBounds(31, 178, 388, 43);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(31, 232, 113, 25);
		panel_1.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('Ѭ');
		passwordField.setForeground(SystemColor.desktop);
		passwordField.setBorder(new LineBorder(Color.BLACK,2));
		passwordField.setFont(new Font("Segoe UI", Font.BOLD, 25));
		passwordField.setBounds(31, 268, 388, 43);
		panel_1.add(passwordField);
		
		lblNewLabel_2 = new JLabel("California Hotel");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_2.setBounds(74, 26, 349, 49);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Admin login");
		btnNewButton.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-admin-30.png"));
		btnNewButton.setBorder(new LineBorder(Color.BLACK, 2));
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				
				Account account = new Account(username, password);
				if (new AccountBUS().isValidAdmin(account)) {
					new EmployeeGUI();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed");
				}
			}
		});
		btnNewButton.setBounds(31, 340, 191, 43);
		panel_1.add(btnNewButton);
		
		JButton btnEmployeeLogin = new JButton("Employee login");
		btnEmployeeLogin.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-manager-30.png"));
		btnEmployeeLogin.setBorder(new LineBorder(Color.BLACK, 2));
		btnEmployeeLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				
				Account account = new Account(username, password);
				if (new AccountBUS().isValidManager(account)) {
					new KhachHangGUI();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed");
				}
			}
		});
		btnEmployeeLogin.setForeground(SystemColor.desktop);
		btnEmployeeLogin.setBackground(new Color(255, 165, 0));
		btnEmployeeLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnEmployeeLogin.setBounds(232, 340, 187, 43);
		panel_1.add(btnEmployeeLogin);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 409, 474);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\hotelcali1-1 (1).jpg"));
		lblNewLabel_3.setBounds(-105, 0, 514, 474);
		panel_2.add(lblNewLabel_3);
		setVisible(true);
	}
	
}
