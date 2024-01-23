package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.lowagie.text.Header;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import BUS.EmployeeBUS;
import BUS.HoaDonBUS;
import CONTROLLER.EmployeeController;
import DAO.HoaDonDAO;
import DTO.Employee;
import DTO.HoaDon;

public class EmployeeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_DOB;
	private JTable table_nhanvien;
	private JTextField textField_find_name;
	public String regex_ngaysinh = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$\r\n";
	public String regex_cccd = "^(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64)[0-9]{10}$";
	private JRadioButton rdbtn_Nam;
	private JLabel lblNewLabel_1_2_2;
	private JComboBox<Object> comboBox_ChucVu;
	private JRadioButton rdbtn_Nu;
	private JTable table_hoa_don;
	private JTextField textField_find_cccd;
	private JTextField textField_thisDate;
	private JTextField textField_thatDate;
	private JRadioButton rdbtn_sortName;
	private JRadioButton rdbtn_sortBirthDate;
	private JRadioButton rdbtn_isPayed;
	private JRadioButton rdbtn_notPayed;
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup2;
	private ButtonGroup buttonGroup3;
	private JLabel label_totalRevenue;
	private JLabel label_RevenueCurrentDate;
	private long totalRevenue;
	public JTextField textField_RevenueDateChart;
	public JTextField textField_RevenueDateChart1;
	private JTable table_doanhthunam;
	private JTable table_luotdatphong;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmployeeGUI frame = new EmployeeGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EmployeeGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo\\icon\\icons8-hotel-48.png"));
		setResizable(false);
		setTitle("ADMIN AREA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 49, 63));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(SystemColor.desktop);
		tabbedPane.setBackground(SystemColor.controlHighlight);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 18));
		tabbedPane.setBounds(0, 0, 862, 457);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("DANH SÁCH NHÂN VIÊN", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblNewLabel.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-customer-50.png"));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(260, 11, 384, 34);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 69, 79, 28);
		panel_1.add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setForeground(SystemColor.desktop);
		textField_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_name.setBorder(new LineBorder(Color.BLACK,1));
		textField_name.setBackground(Color.WHITE);
		textField_name.setBounds(99, 69, 178, 28);
		panel_1.add(textField_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày sinh");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(SystemColor.text);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 114, 79, 28);
		panel_1.add(lblNewLabel_1_1);
		
		textField_DOB = new JTextField();
		textField_DOB.setForeground(SystemColor.desktop);
		textField_DOB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_DOB.setBorder(new LineBorder(Color.BLACK,1));
		textField_DOB.setBackground(Color.WHITE);
		textField_DOB.setBounds(99, 116, 178, 28);
		panel_1.add(textField_DOB);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Chức vụ");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_1.setForeground(SystemColor.text);
		lblNewLabel_1_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(310, 114, 79, 28);
		panel_1.add(lblNewLabel_1_2_1);
		
		comboBox_ChucVu = new JComboBox<Object>();
		comboBox_ChucVu.setModel(new DefaultComboBoxModel(new String[] {"Phục vụ", "Tiếp tân", "Bảo vệ"}));
		comboBox_ChucVu.setSelectedIndex(-1);
		comboBox_ChucVu.setForeground(SystemColor.desktop);
		comboBox_ChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_ChucVu.setBorder(new LineBorder(Color.BLACK,1));
		comboBox_ChucVu.setBackground(Color.WHITE);
		comboBox_ChucVu.setBounds(399, 116, 144, 28);
		panel_1.add(comboBox_ChucVu);
		
		lblNewLabel_1_2_2 = new JLabel("Giới tính");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_2.setForeground(SystemColor.text);
		lblNewLabel_1_2_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_2_2.setBounds(310, 69, 86, 28);
		panel_1.add(lblNewLabel_1_2_2);
		
		rdbtn_Nam = new JRadioButton("Nam");
		rdbtn_Nam.setForeground(Color.WHITE);
		rdbtn_Nam.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_Nam.setBackground(new Color(51, 51, 51));
		rdbtn_Nam.setBounds(395, 72, 79, 23);
		panel_1.add(rdbtn_Nam);
		
		rdbtn_Nu = new JRadioButton("Nữ");
		rdbtn_Nu.setForeground(Color.WHITE);
		rdbtn_Nu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_Nu.setBackground(new Color(51, 51, 51));
		rdbtn_Nu.setBounds(475, 72, 79, 23);
		panel_1.add(rdbtn_Nu);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_Nam);
		buttonGroup.add(rdbtn_Nu);
		
		ActionListener actionListener = new EmployeeController(this);
		
		JButton btn_Them = new JButton("THÊM");
		btn_Them.addActionListener(actionListener);
		btn_Them.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-add-24.png"));
		btn_Them.setForeground(SystemColor.desktop);
		btn_Them.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Them.setBorder(new LineBorder(Color.BLACK,1));
		btn_Them.setBackground(new Color(46, 204, 113));
		btn_Them.setBounds(595, 69, 113, 30);
		panel_1.add(btn_Them);
		
		JButton btn_Sua = new JButton("SỬA");
		btn_Sua.addActionListener(actionListener);
		btn_Sua.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-edit-30.png"));
		btn_Sua.setForeground(SystemColor.desktop);
		btn_Sua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Sua.setBorder(new LineBorder(Color.BLACK,1));
		btn_Sua.setBackground(new Color(255, 165, 0));
		btn_Sua.setBounds(720, 69, 113, 30);
		panel_1.add(btn_Sua);
		
		JButton btn_Xoa = new JButton("XÓA");
		btn_Xoa.addActionListener(actionListener);
		btn_Xoa.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-delete-24.png"));
		btn_Xoa.setForeground(SystemColor.desktop);
		btn_Xoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Xoa.setBorder(new LineBorder(Color.BLACK,1));
		btn_Xoa.setBackground(new Color(231, 76, 60));
		btn_Xoa.setBounds(595, 114, 113, 30);
		panel_1.add(btn_Xoa);
		
		JButton btn_CapNhat = new JButton("CẬP NHẬT");
		btn_CapNhat.addActionListener(actionListener);
		btn_CapNhat.setForeground(SystemColor.desktop);
		btn_CapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_CapNhat.setBorder(new LineBorder(Color.BLACK,1));
		btn_CapNhat.setBackground(new Color(255, 165, 0));
		btn_CapNhat.setBounds(720, 114, 113, 30);
		panel_1.add(btn_CapNhat);
		
		JButton btnNewButton = new JButton(".");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI();
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-sign-out-30.png"));
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setBounds(808, 0, 49, 34);
		panel_1.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(10, 153, 837, 3);
		panel_1.add(separator);
		
		table_nhanvien = new JTable();
		table_nhanvien.setBackground(SystemColor.controlHighlight);
		table_nhanvien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_nhanvien.setForeground(SystemColor.desktop);
		table_nhanvien.setBorder(new LineBorder(SystemColor.desktop,1));
		table_nhanvien.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Họ và tên", "Ngày sinh", "Giới tính", "Vị trí"
			}
		));
		JTableHeader header = table_nhanvien.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header.setForeground(SystemColor.text);
		header.setBorder(new LineBorder(SystemColor.desktop,1));
		header.setBackground(new Color( 0, 48, 143));
		table_nhanvien.setRowHeight(25);
		table_nhanvien.setBounds(10, 217, 837, 193);
		updateTableNhanVien();
		
		JScrollPane scrollPane = new JScrollPane(table_nhanvien);
		scrollPane.setBounds(10, 198, 837, 212);
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ và tên");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setForeground(SystemColor.text);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 159, 79, 28);
		panel_1.add(lblNewLabel_1_2);
		
		textField_find_name = new JTextField();
		textField_find_name.setForeground(SystemColor.desktop);
		textField_find_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_find_name.setBorder(new LineBorder(Color.BLACK,1));
		textField_find_name.setBackground(Color.WHITE);
		textField_find_name.setBounds(99, 159, 178, 28);
		panel_1.add(textField_find_name);
		
		JButton btn_find_name = new JButton("TÌM");
		btn_find_name.addActionListener(actionListener);
		btn_find_name.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-find-25.png"));
		btn_find_name.setForeground(SystemColor.desktop);
		btn_find_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_find_name.setBorder(new LineBorder(Color.BLACK,1));
		btn_find_name.setBackground(new Color(52, 152, 219));
		btn_find_name.setBounds(286, 159, 86, 28);
		panel_1.add(btn_find_name);
		
		JButton btn_Reload = new JButton("TẢI LẠI");
		btn_Reload.addActionListener(actionListener);
		btn_Reload.setForeground(SystemColor.desktop);
		btn_Reload.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Reload.setBorder(new LineBorder(Color.BLACK,1));
		btn_Reload.setBackground(new Color(255, 165, 0));
		btn_Reload.setBounds(382, 159, 97, 28);
		panel_1.add(btn_Reload);
		
		rdbtn_sortName = new JRadioButton("Sắp xếp theo tên");
		rdbtn_sortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
				
				ArrayList<Employee> employees = new EmployeeBUS().sortByName();
				clearTableNhanVien();
				for (Employee employee : employees) {
					model.addRow(employee.toObjects());
				}
			}
		});
		rdbtn_sortName.setForeground(SystemColor.window);
		rdbtn_sortName.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtn_sortName.setBackground(new Color(51, 51, 51));
		rdbtn_sortName.setBounds(485, 159, 165, 28);
		panel_1.add(rdbtn_sortName);
		
		rdbtn_sortBirthDate = new JRadioButton("Sắp xếp ngày sinh");
		rdbtn_sortBirthDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
				
				ArrayList<Employee> employees = new EmployeeBUS().sortByBirthDate();
				clearTableNhanVien();
				for (Employee employee : employees) {
					model.addRow(employee.toObjects());
				}
			}
		});
		rdbtn_sortBirthDate.setForeground(SystemColor.window);
		rdbtn_sortBirthDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtn_sortBirthDate.setBackground(new Color(51, 51, 51));
		rdbtn_sortBirthDate.setBounds(652, 159, 195, 28);
		panel_1.add(rdbtn_sortBirthDate);
		
		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdbtn_sortName);
		buttonGroup2.add(rdbtn_sortBirthDate);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.desktop);
		separator_2.setBackground(SystemColor.desktop);
		separator_2.setBounds(10, 62, 837, 3);
		panel_1.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.desktop);
		panel.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("       DOANH THU       ", null, panel, null);
		panel.setLayout(null);
		
		table_hoa_don = new JTable();
		table_hoa_don.setBackground(SystemColor.controlHighlight);
		table_hoa_don.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_hoa_don.setBorder(new LineBorder(Color.BLACK,1));
		table_hoa_don.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "CCCD", "Số phòng", "Ngày nhận", "Ngày trả", "Giá"
			}
		));
		JTableHeader header2 = table_hoa_don.getTableHeader();
		header2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header2.setForeground(SystemColor.text);
		header2.setBackground(new Color( 0, 48, 143));
		header2.setBorder(new LineBorder(Color.black,1));
		table_hoa_don.setRowHeight(25);
		table_hoa_don.setForeground(SystemColor.desktop);
		table_hoa_don.setBounds(10, 99, 837, 176);
		panel.add(table_hoa_don);
		updateTableHoaDon();
		
		JScrollPane scrollPane_1 = new JScrollPane(table_hoa_don);
		scrollPane_1.setBounds(10, 108, 837, 187);
		panel.add(scrollPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("DANH SÁCH HÓA ĐƠN");
		lblNewLabel_2.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-bill-50.png"));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Courier New", Font.BOLD, 25));
		lblNewLabel_2.setBounds(246, 11, 353, 38);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số CCCD");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 71, 84, 26);
		panel.add(lblNewLabel_3);
		
		textField_find_cccd = new JTextField();
		textField_find_cccd.setForeground(SystemColor.desktop);
		textField_find_cccd.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_find_cccd.setBorder(new LineBorder(Color.BLACK,1));
		textField_find_cccd.setBounds(82, 71, 130, 28);
		panel.add(textField_find_cccd);
		
		JButton btn_Tim_cccd_1 = new JButton("Tìm");
		btn_Tim_cccd_1.addActionListener(actionListener);
		btn_Tim_cccd_1.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-find-25.png"));
		btn_Tim_cccd_1.setForeground(SystemColor.desktop);
		btn_Tim_cccd_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Tim_cccd_1.setBorder(new LineBorder(Color.BLACK,1));
		btn_Tim_cccd_1.setBackground(new Color(52, 152, 219));
		btn_Tim_cccd_1.setBounds(222, 70, 84, 30);
		panel.add(btn_Tim_cccd_1);
		
		JButton btn_TAI_LAI_1 = new JButton("Tải lại");
		btn_TAI_LAI_1.addActionListener(actionListener);
		btn_TAI_LAI_1.setForeground(SystemColor.desktop);
		btn_TAI_LAI_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_TAI_LAI_1.setBorder(new LineBorder(Color.BLACK,1));
		btn_TAI_LAI_1.setBackground(new Color(255, 165, 0));
		btn_TAI_LAI_1.setBounds(316, 70, 84, 30);
		panel.add(btn_TAI_LAI_1);
		
		JButton btn_XoaHoaDon = new JButton("Xóa");
		btn_XoaHoaDon.addActionListener(actionListener);
		btn_XoaHoaDon.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-delete-24.png"));
		btn_XoaHoaDon.setForeground(SystemColor.desktop);
		btn_XoaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_XoaHoaDon.setBorder(new LineBorder(Color.BLACK,1));
		btn_XoaHoaDon.setBackground(new Color(231, 76, 60));
		btn_XoaHoaDon.setBounds(410, 70, 95, 30);
		panel.add(btn_XoaHoaDon);
		
		rdbtn_isPayed = new JRadioButton("Hóa đơn đã thanh toán");
		rdbtn_isPayed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
				
				ArrayList<HoaDon> hoaDons = new HoaDonBUS().listPayed();
				clearTableHoaDon();
				for (HoaDon hoaDon : hoaDons) {
					model.addRow(hoaDon.toObjects());
				}
			}
		});
		rdbtn_isPayed.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_isPayed.setForeground(SystemColor.window);
		rdbtn_isPayed.setBackground(new Color(51, 51, 51));
		rdbtn_isPayed.setBounds(630, 302, 210, 30);
		panel.add(rdbtn_isPayed);
		
		rdbtn_notPayed = new JRadioButton("Hóa đơn chưa thanh toán");
		rdbtn_notPayed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
				
				ArrayList<HoaDon> hoaDons = new HoaDonBUS().listNotPayed();
				clearTableHoaDon();
				for (HoaDon hoaDon : hoaDons) {
					model.addRow(hoaDon.toObjects());
				}
			}
		});
		rdbtn_notPayed.setForeground(SystemColor.window);
		rdbtn_notPayed.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_notPayed.setBackground(new Color(51, 51, 51));
		rdbtn_notPayed.setBounds(630, 330, 221, 30);
		panel.add(rdbtn_notPayed);
		
		buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(rdbtn_isPayed);
		buttonGroup3.add(rdbtn_notPayed);
		
		JButton btn_details = new JButton("Chi tiết");
		btn_details.addActionListener(actionListener);
		btn_details.setForeground(SystemColor.desktop);
		btn_details.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_details.setBorder(new LineBorder(Color.BLACK,1));
		btn_details.setBackground(new Color(52, 152, 219));
		btn_details.setBounds(515, 70, 84, 30);
		panel.add(btn_details);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(10, 56, 837, 3);
		panel.add(separator_1);
		
		JLabel lblNewLabel_4 = new JLabel("TỔNG DOANH THU:");
		lblNewLabel_4.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4.setForeground(SystemColor.window);
		lblNewLabel_4.setBounds(11, 302, 150, 26);
		panel.add(lblNewLabel_4);
		
		label_totalRevenue = new JLabel("");
		label_totalRevenue.setForeground(SystemColor.window);
		label_totalRevenue.setFont(new Font("Courier New", Font.BOLD, 16));
		label_totalRevenue.setBounds(168, 302, 150, 26);
		panel.add(label_totalRevenue);
		totalRevenue = new HoaDonBUS().totalRevenue();
		label_totalRevenue.setText(totalRevenue+" VNĐ");
		
		JLabel lblNewLabel_4_1 = new JLabel("DOANH THU HÔM NAY:");
		lblNewLabel_4_1.setForeground(SystemColor.window);
		lblNewLabel_4_1.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(11, 330, 180, 26);
		panel.add(lblNewLabel_4_1);
		
		label_RevenueCurrentDate = new JLabel("");
		label_RevenueCurrentDate.setForeground(SystemColor.window);
		label_RevenueCurrentDate.setFont(new Font("Courier New", Font.BOLD, 16));
		label_RevenueCurrentDate.setBounds(197, 330, 150, 26);
		panel.add(label_RevenueCurrentDate);
		Date currentDate = new Date(System.currentTimeMillis());
		Long RevenueCurrentDate = new HoaDonBUS().revenueCurrentDate(currentDate);
		
		label_RevenueCurrentDate.setText(RevenueCurrentDate+" VNĐ");
		
		JLabel lblNewLabel_4_1_1 = new JLabel("DOANH THU TỪ");
		lblNewLabel_4_1_1.setForeground(SystemColor.window);
		lblNewLabel_4_1_1.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4_1_1.setBounds(11, 358, 130, 26);
		panel.add(lblNewLabel_4_1_1);
		
		textField_thisDate = new JTextField();
		textField_thisDate.setForeground(SystemColor.desktop);
		textField_thisDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_thisDate.setColumns(10);
		textField_thisDate.setBorder(new LineBorder(Color.BLACK,1));
		textField_thisDate.setBounds(139, 356, 104, 28);
		panel.add(textField_thisDate);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("-");
		lblNewLabel_4_1_1_1.setForeground(SystemColor.window);
		lblNewLabel_4_1_1_1.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4_1_1_1.setBounds(249, 358, 10, 26);
		panel.add(lblNewLabel_4_1_1_1);
		
		textField_thatDate = new JTextField();
		textField_thatDate.setForeground(SystemColor.desktop);
		textField_thatDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_thatDate.setColumns(10);
		textField_thatDate.setBorder(new LineBorder(Color.BLACK,1));
		textField_thatDate.setBounds(266, 356, 104, 28);
		panel.add(textField_thatDate);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel(":");
		lblNewLabel_4_1_1_1_1.setForeground(SystemColor.window);
		lblNewLabel_4_1_1_1_1.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4_1_1_1_1.setBounds(371, 358, 15, 26);
		panel.add(lblNewLabel_4_1_1_1_1);
		
		JLabel label_RevenueFromDateToDate = new JLabel("");
		label_RevenueFromDateToDate.setForeground(SystemColor.window);
		label_RevenueFromDateToDate.setFont(new Font("Courier New", Font.BOLD, 16));
		label_RevenueFromDateToDate.setBounds(380, 356, 150, 26);
		panel.add(label_RevenueFromDateToDate);
		
		JButton btn_Tinh = new JButton("Tính");
		btn_Tinh.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-calculator-24.png"));
		btn_Tinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_thisDate.getText().isBlank()||textField_thatDate.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				}else if (!textField_thisDate.getText().matches(regex_ngaysinh)||!textField_thatDate.getText().matches(regex_ngaysinh)) {
					JOptionPane.showMessageDialog(null, "Nhập ngày theo địng dạng yyyy-MM-dd");
				}else {
					Date thisDate = Date.valueOf(textField_thisDate.getText());
					Date thatDate = Date.valueOf(textField_thatDate.getText());
					long total = new HoaDonBUS().revenueDateToDate(thisDate,thatDate);
					
					label_RevenueFromDateToDate.setText(total+" VNĐ");
				}
			}
		});
		btn_Tinh.setForeground(SystemColor.desktop);
		btn_Tinh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Tinh.setBorder(new LineBorder(Color.BLACK,1));
		btn_Tinh.setBackground(new Color(255, 165, 0));
		btn_Tinh.setBounds(540, 356, 84, 30);
		panel.add(btn_Tinh);
		
		JButton btn_Chart = new JButton("BIỂU ĐỒ");
		btn_Chart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChartGUI();
			}
		});
		btn_Chart.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-chart-25.png"));
		btn_Chart.setForeground(SystemColor.desktop);
		btn_Chart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Chart.setBorder(new LineBorder(Color.BLACK,1));
		btn_Chart.setBackground(new Color(255, 165, 0));
		btn_Chart.setBounds(734, 71, 113, 28);
		panel.add(btn_Chart);
		
		JLabel label_ChartRevenue = new JLabel("BIỂU ĐỒ DOANH THU TỪ");
		label_ChartRevenue.setForeground(SystemColor.window);
		label_ChartRevenue.setFont(new Font("Courier New", Font.BOLD, 16));
		label_ChartRevenue.setBounds(11, 387, 201, 26);
		panel.add(label_ChartRevenue);
		
		textField_RevenueDateChart = new JTextField();
		textField_RevenueDateChart.setForeground(SystemColor.desktop);
		textField_RevenueDateChart.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_RevenueDateChart.setColumns(10);
		textField_RevenueDateChart.setBorder(new LineBorder(Color.BLACK,1));
		textField_RevenueDateChart.setBounds(215, 387, 104, 28);
		panel.add(textField_RevenueDateChart);
		
		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("-");
		lblNewLabel_4_1_1_1_2.setForeground(SystemColor.window);
		lblNewLabel_4_1_1_1_2.setFont(new Font("Courier New", Font.BOLD, 16));
		lblNewLabel_4_1_1_1_2.setBounds(325, 387, 10, 26);
		panel.add(lblNewLabel_4_1_1_1_2);
		
		textField_RevenueDateChart1 = new JTextField();
		textField_RevenueDateChart1.setForeground(SystemColor.desktop);
		textField_RevenueDateChart1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_RevenueDateChart1.setColumns(10);
		textField_RevenueDateChart1.setBorder(new LineBorder(Color.BLACK,1));
		textField_RevenueDateChart1.setBounds(342, 387, 104, 28);
		panel.add(textField_RevenueDateChart1);
		
		JButton btn_Chart_1 = new JButton("CHART");
		btn_Chart_1.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-chart-25.png"));
		btn_Chart_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textField_RevenueDateChart.getText().isBlank() || textField_RevenueDateChart1.getText().isBlank() ) {
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
					return;
				}
				if (!textField_RevenueDateChart.getText().matches(regex_ngaysinh) || !textField_RevenueDateChart1.getText().matches(regex_ngaysinh)) {
					JOptionPane.showMessageDialog(null, "Nhập định ngày theo định dạng yyyy-MM-dd");
					return;
				}
				new ChartDateGUI(EmployeeGUI.this);
			}
		});
		btn_Chart_1.setForeground(SystemColor.desktop);
		btn_Chart_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Chart_1.setBorder(new LineBorder(Color.BLACK,1));
		btn_Chart_1.setBackground(new Color(255, 165, 0));
		btn_Chart_1.setBounds(463, 387, 113, 28);
		panel.add(btn_Chart_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("  TỔNG QUAN  ", null, panel_2, null);
		tabbedPane.setBackgroundAt(2, SystemColor.controlHighlight);
		panel_2.setLayout(null);
		
		JLabel lblDoanhThu = new JLabel("DOANH THU(Triệu đồng)");
		lblDoanhThu.setForeground(Color.WHITE);
		lblDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDoanhThu.setBounds(35, 25, 218, 34);
		panel_2.add(lblDoanhThu);
		
		JLabel lblLttPhng = new JLabel("LƯỢT ĐẶT PHÒNG");
		lblLttPhng.setForeground(Color.WHITE);
		lblLttPhng.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblLttPhng.setBounds(35, 211, 174, 34);
		panel_2.add(lblLttPhng);
		
		table_doanhthunam = new JTable();
		table_doanhthunam.setBorder(new LineBorder(Color.black,1));
		table_doanhthunam.setRowHeight(35);
		table_doanhthunam.setBackground(SystemColor.controlHighlight);
		table_doanhthunam.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table_doanhthunam.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"
			}
		));
		JTableHeader header3 = table_doanhthunam.getTableHeader();
		header3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header3.setForeground(SystemColor.text);
		header3.setBackground(new Color(0,48,143));
		header3.setBorder(new LineBorder(Color.BLACK,1));
		table_doanhthunam.setBounds(35, 81, 797, 123);
		updateTableDoanhThuNam(LocalDate.now().getYear());
		
		JScrollPane scrollPane_2 = new JScrollPane(table_doanhthunam);
		scrollPane_2.setBounds(35, 70, 797, 64);
		panel_2.add(scrollPane_2);
		
		table_luotdatphong = new JTable();
		table_luotdatphong.setRowHeight(35);
		table_luotdatphong.setBorder(new LineBorder(Color.black,1));
		table_luotdatphong.setBackground(SystemColor.controlHighlight);
		table_luotdatphong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table_luotdatphong.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"
			}
		));
		JTableHeader header4 = table_luotdatphong.getTableHeader();
		header4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header4.setForeground(SystemColor.text);
		header4.setBackground(new Color(0,48,143));
		header4.setBorder(new LineBorder(Color.black,1));
		table_luotdatphong.setBounds(35, 269, 797, 57);
		updateTableLuotDatPhong(LocalDate.now().getYear());
		
		JScrollPane scrollPane_3 = new JScrollPane(table_luotdatphong);
		scrollPane_3.setBounds(35, 256, 797, 64);
		panel_2.add(scrollPane_3);
		
		JComboBox<Object> comboBox_RevenueYear = new JComboBox<Object>();
		comboBox_RevenueYear.setModel(new DefaultComboBoxModel(new String[] {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBox_RevenueYear.setSelectedItem(LocalDate.now().getYear()+"");
		comboBox_RevenueYear.setForeground(SystemColor.desktop);
		comboBox_RevenueYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_RevenueYear.setBorder(new LineBorder(Color.BLACK,1));
		comboBox_RevenueYear.setBackground(Color.WHITE);
		comboBox_RevenueYear.setBounds(263, 31, 144, 28);
		panel_2.add(comboBox_RevenueYear);
		
		comboBox_RevenueYear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.valueOf((String) comboBox_RevenueYear.getSelectedItem());
				updateTableDoanhThuNam(year);
			}
		});
		
		JComboBox<Object> comboBox_luotdatphong = new JComboBox<Object>();
		comboBox_luotdatphong.setModel(new DefaultComboBoxModel(new String[] {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBox_luotdatphong.setSelectedItem(LocalDate.now().getYear()+"");
		comboBox_luotdatphong.setForeground(SystemColor.desktop);
		comboBox_luotdatphong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_luotdatphong.setBorder(new LineBorder(Color.BLACK,1));
		comboBox_luotdatphong.setBackground(Color.WHITE);
		comboBox_luotdatphong.setBounds(206, 215, 144, 28);
		panel_2.add(comboBox_luotdatphong);
		
		JButton btn_Chart_Revenue_Year = new JButton("DETAIL");
		btn_Chart_Revenue_Year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.valueOf((String) comboBox_RevenueYear.getSelectedItem());
				
				double thang1 = new HoaDonDAO().januaryRevenue(year);
				double thang2 = new HoaDonDAO().februaryRevenue(year);
				double thang3 = new HoaDonDAO().MarchRevenue(year);
				double thang4 = new HoaDonDAO().AprilRevenue(year);
				double thang5 = new HoaDonDAO().MayRevenue(year);
				double thang6 = new HoaDonDAO().JuneRevenue(year);
				double thang7 = new HoaDonDAO().JulyRevenue(year);
				double thang8 = new HoaDonDAO().AugustRevenue(year);
				double thang9 = new HoaDonDAO().SeptemberRevenue(year);
				double thang10 = new HoaDonDAO().OctoberRevenue(year);
				double thang11 = new HoaDonDAO().NovemberRevenue(year);
				double thang12 = new HoaDonDAO().DecemberRevenue(year);
				
				new ChartTotalRevenueYear(year, thang1, thang2, thang3, thang4, thang5, thang6, thang7, thang8, thang9, thang10, thang11, thang12);
			}
		});
		btn_Chart_Revenue_Year.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-chart-25.png"));
		btn_Chart_Revenue_Year.setForeground(SystemColor.desktop);
		btn_Chart_Revenue_Year.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Chart_Revenue_Year.setBorder(new LineBorder(Color.BLACK,1));
		btn_Chart_Revenue_Year.setBackground(new Color(255, 165, 0));
		btn_Chart_Revenue_Year.setBounds(429, 31, 113, 28);
		panel_2.add(btn_Chart_Revenue_Year);
		
		JButton btn_Chart_Order_Year = new JButton("Details");
		btn_Chart_Order_Year.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.valueOf((String) comboBox_luotdatphong.getSelectedItem());
				
				int thang1 = new HoaDonDAO().januaryOrder(year);
				int thang2 = new HoaDonDAO().februaryOrder(year);
				int thang3 = new HoaDonDAO().MarchOrder(year);
				int thang4 = new HoaDonDAO().AprilOrder(year);
				int thang5 = new HoaDonDAO().MayOrder(year);
				int thang6 = new HoaDonDAO().JuneOrder(year);
				int thang7 = new HoaDonDAO().JulyOrder(year);
				int thang8 = new HoaDonDAO().AugustOrder(year);
				int thang9 = new HoaDonDAO().SeptemberOrder(year);
				int thang10 = new HoaDonDAO().OctoberOrder(year);
				int thang11 = new HoaDonDAO().NovemberOrder(year);
				int thang12 = new HoaDonDAO().DecemberOrder(year);
				
				new ChartTotalOrderYear(year, thang1, thang2, thang3, thang4, thang5, thang6, thang7, thang8, thang9, thang10, thang11, thang12);
			}
		});
		btn_Chart_Order_Year.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-chart-25.png"));
		btn_Chart_Order_Year.setForeground(SystemColor.desktop);
		btn_Chart_Order_Year.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Chart_Order_Year.setBorder(new LineBorder(Color.BLACK,1));
		btn_Chart_Order_Year.setBackground(new Color(255, 165, 0));
		btn_Chart_Order_Year.setBounds(370, 215, 113, 28);
		panel_2.add(btn_Chart_Order_Year);
		comboBox_luotdatphong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int year = Integer.valueOf((String)comboBox_luotdatphong.getSelectedItem());
				updateTableLuotDatPhong(year);
			}
		});
		
		setVisible(true);
	}

	private void updateTableLuotDatPhong(int year) {
		DefaultTableModel model = (DefaultTableModel) table_luotdatphong.getModel();
		
		int thang1 = new HoaDonDAO().januaryOrder(year);
		int thang2 = new HoaDonDAO().februaryOrder(year);
		int thang3 = new HoaDonDAO().MarchOrder(year);
		int thang4 = new HoaDonDAO().AprilOrder(year);
		int thang5 = new HoaDonDAO().MayOrder(year);
		int thang6 = new HoaDonDAO().JuneOrder(year);
		int thang7 = new HoaDonDAO().JulyOrder(year);
		int thang8 = new HoaDonDAO().AugustOrder(year);
		int thang9 = new HoaDonDAO().SeptemberOrder(year);
		int thang10 = new HoaDonDAO().OctoberOrder(year);
		int thang11 = new HoaDonDAO().NovemberOrder(year);
		int thang12 = new HoaDonDAO().DecemberOrder(year);
		
		model.setValueAt(thang1, 0, 0);
		model.setValueAt(thang2, 0, 1);
		model.setValueAt(thang3, 0, 2);
		model.setValueAt(thang4, 0, 3);
		model.setValueAt(thang5, 0, 4);
		model.setValueAt(thang6, 0, 5);
		model.setValueAt(thang7, 0, 6);
		model.setValueAt(thang8, 0, 7);
		model.setValueAt(thang9, 0, 8);
		model.setValueAt(thang10, 0, 9);
		model.setValueAt(thang11, 0, 10);
		model.setValueAt(thang12, 0, 11);
	}

	private void updateTableDoanhThuNam(int year) {
		DefaultTableModel model = (DefaultTableModel) table_doanhthunam.getModel();
		
		double thang1 = new HoaDonDAO().januaryRevenue(year);
		double thang2 = new HoaDonDAO().februaryRevenue(year);
		double thang3 = new HoaDonDAO().MarchRevenue(year);
		double thang4 = new HoaDonDAO().AprilRevenue(year);
		double thang5 = new HoaDonDAO().MayRevenue(year);
		double thang6 = new HoaDonDAO().JuneRevenue(year);
		double thang7 = new HoaDonDAO().JulyRevenue(year);
		double thang8 = new HoaDonDAO().AugustRevenue(year);
		double thang9 = new HoaDonDAO().SeptemberRevenue(year);
		double thang10 = new HoaDonDAO().OctoberRevenue(year);
		double thang11 = new HoaDonDAO().NovemberRevenue(year);
		double thang12 = new HoaDonDAO().DecemberRevenue(year);
		
		model.setValueAt(thang1, 0, 0);
		model.setValueAt(thang2, 0, 1);
		model.setValueAt(thang3, 0, 2);
		model.setValueAt(thang4, 0, 3);
		model.setValueAt(thang5, 0, 4);
		model.setValueAt(thang6, 0, 5);
		model.setValueAt(thang7, 0, 6);
		model.setValueAt(thang8, 0, 7);
		model.setValueAt(thang9, 0, 8);
		model.setValueAt(thang10, 0, 9);
		model.setValueAt(thang11, 0, 10);
		model.setValueAt(thang12, 0, 11);
	}

	public void updateTableHoaDon() {
		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
		
		ArrayList<HoaDon> hoaDons = new HoaDonBUS().listHoaDons();
		clearTableHoaDon();
		for (HoaDon hoaDon : hoaDons) {
			model.addRow(hoaDon.toObjects());
		}
	}

	public void clearTableHoaDon() {
		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
		int rowCount = table_hoa_don.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	public void updateTableNhanVien() {
		DefaultTableModel model =(DefaultTableModel) table_nhanvien.getModel();
		
		ArrayList<Employee> employees = new EmployeeBUS().listEmployees();
		clearTableNhanVien();
		for (Employee employee : employees) {
			model.addRow(employee.toObjects());
		}
	}

	public void clearTableNhanVien() {
		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

//	public void addEmployee() {
//		if (textField_name.getText().isBlank() || textField_DOB.getText().isBlank()|| (!rdbtn_Nam.isSelected()) && (!rdbtn_Nu.isSelected())|| comboBox_ChucVu.getSelectedItem() == null) {
//			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
//		}else {
//			if (!textField_DOB.getText().matches(regex_ngaysinh)) {
//				JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
//			}else {
//				String name = textField_name.getText();
//				Date birthDate = Date.valueOf(textField_DOB.getText());
//				boolean gioitinh = true;
//				if (rdbtn_Nam.isSelected()) {
//					gioitinh = true;
//				}else if(rdbtn_Nu.isSelected()) {
//					gioitinh = false;
//				}
//				String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
//				new EmployeeBUS().addEmployee(new Employee(name, birthDate, gioitinh, position));
//				updateTableNhanVien();
//			}
//		}
//	}
	public void addEmployee() {
		if (textField_name.getText().isBlank() || textField_DOB.getText().isBlank()|| (!rdbtn_Nam.isSelected()) && (!rdbtn_Nu.isSelected())|| comboBox_ChucVu.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
			return;
		}
		if (!textField_DOB.getText().matches(regex_ngaysinh)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
			return;
		}
		String name = textField_name.getText();
		Date birthDate = Date.valueOf(textField_DOB.getText());
		boolean gioitinh = true;
		if (rdbtn_Nam.isSelected()) {
			gioitinh = true;
		}else if(rdbtn_Nu.isSelected()) {
			gioitinh = false;
		}
		String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
		new EmployeeBUS().addEmployee(new Employee(name, birthDate, gioitinh, position));
		updateTableNhanVien();
		textField_name.setText(null);
		textField_DOB.setText(null);
		buttonGroup.clearSelection();
		comboBox_ChucVu.setSelectedIndex(-1);
	}

//	public void deleteEmployee() {
//		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
//		int rowIndex = table_nhanvien.getSelectedRow();
//		
//		if (!table_nhanvien.isRowSelected(rowIndex)) {
//			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng để xóa");
//		}else {
//			int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
//			String name = String.valueOf(model.getValueAt(rowIndex, 1));
//			Date ngaysinh = (Date) model.getValueAt(rowIndex, 2);
//			String gioitinh = String.valueOf(model.getValueAt(rowIndex, 3));
//			boolean gioiTinh = true;
//			if (gioitinh.equals("Nam")) {
//				gioiTinh = true;
//			}else if (gioitinh.equals("Nữ")) {
//				gioiTinh = false;
//			}
//			String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
//			new EmployeeBUS().deleteEmployee(new Employee(id, name, ngaysinh, gioiTinh, position));
//			updateTableNhanVien();
//		}
//	}
	public void deleteEmployee() {
		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
		int rowIndex = table_nhanvien.getSelectedRow();
		
		if (!table_nhanvien.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để xóa");
			return;
		}
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		String name = String.valueOf(model.getValueAt(rowIndex, 1));
		Date ngaysinh = (Date) model.getValueAt(rowIndex, 2);
		String gioitinh = String.valueOf(model.getValueAt(rowIndex, 3));
		boolean gioiTinh = true;
		if (gioitinh.equals("Nam")) {
			gioiTinh = true;
		}else if (gioitinh.equals("Nữ")) {
			gioiTinh = false;
		}
		String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
		new EmployeeBUS().deleteEmployee(new Employee(id, name, ngaysinh, gioiTinh, position));
		updateTableNhanVien();
	}
//	public void setForm() {
//		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
//		int rowIndex = table_nhanvien.getSelectedRow();
//		
//		if (!table_nhanvien.isRowSelected(rowIndex)) {
//			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng để sửa");
//		}else {
//			String name = String.valueOf(model.getValueAt(rowIndex, 1));
//			Date ngaysinh = (Date) model.getValueAt(rowIndex, 2);
//			String gioitinh = String.valueOf(model.getValueAt(rowIndex, 3));
//			String position = String.valueOf(model.getValueAt(rowIndex, 4));
//			
//			textField_name.setText(name);
//			textField_DOB.setText(ngaysinh+"");
//			if (gioitinh.equals("Nam")) {
//				rdbtn_Nam.setSelected(true);
//			}else if (gioitinh.equals("Nữ")) {
//				rdbtn_Nu.setSelected(true);
//			}
//			comboBox_ChucVu.setSelectedItem(position);
//		}
//	}
	public void setForm() {
		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
		int rowIndex = table_nhanvien.getSelectedRow();
		
		if (!table_nhanvien.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 khách hàng để sửa thông tin");
			return;
		}
		String name = String.valueOf(model.getValueAt(rowIndex, 1));
		Date ngaysinh = (Date) model.getValueAt(rowIndex, 2);
		String gioitinh = String.valueOf(model.getValueAt(rowIndex, 3));
		String position = String.valueOf(model.getValueAt(rowIndex, 4));
		
		textField_name.setText(name);
		textField_DOB.setText(ngaysinh+"");
		if (gioitinh.equals("Nam")) {
			rdbtn_Nam.setSelected(true);
		}else if (gioitinh.equals("Nữ")) {
			rdbtn_Nu.setSelected(true);
		}
		comboBox_ChucVu.setSelectedItem(position);
	}

//	public void update() {
//		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
//		int rowIndex = table_nhanvien.getSelectedRow();
//		
//		if (!table_nhanvien.isRowSelected(rowIndex)) {
//			JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhân viên để xóa");
//		}else {
//			if (textField_name.getText().isBlank() || textField_DOB.getText().isBlank()|| (!rdbtn_Nam.isSelected()) && (!rdbtn_Nu.isSelected())|| comboBox_ChucVu.getSelectedItem() == null) {
//				JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
//			}else {
//				if (!textField_DOB.getText().matches(regex_ngaysinh)) {
//					JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
//				}else {
//					int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
//					String name = textField_name.getText();
//					Date birthDate = Date.valueOf(textField_DOB.getText());
//					boolean gioitinh = true;
//					if (rdbtn_Nam.isSelected()) {
//						gioitinh = true;
//					}else if(rdbtn_Nu.isSelected()) {
//						gioitinh = false;
//					}
//					String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
//					new EmployeeBUS().editEmployee(new Employee(id, name, birthDate, gioitinh, position));
//					updateTableNhanVien();
//				}
//			}
//		}
//	}
	
	public void update() {
		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
		int rowIndex = table_nhanvien.getSelectedRow();
		
		if (!table_nhanvien.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để cập nhật");
			return;
		}
		if (textField_name.getText().isBlank() || textField_DOB.getText().isBlank()|| (!rdbtn_Nam.isSelected()) && (!rdbtn_Nu.isSelected())|| comboBox_ChucVu.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
			return;
		}
		if (!textField_DOB.getText().matches(regex_ngaysinh)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
			return;
		}
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		String name = textField_name.getText();
		Date birthDate = Date.valueOf(textField_DOB.getText());
		boolean gioitinh = true;
		if (rdbtn_Nam.isSelected()) {
			gioitinh = true;
		}else if(rdbtn_Nu.isSelected()) {
			gioitinh = false;
		}
		String position = String.valueOf(comboBox_ChucVu.getSelectedItem());
		new EmployeeBUS().editEmployee(new Employee(id, name, birthDate, gioitinh, position));
		updateTableNhanVien();
		textField_name.setText(null);
		textField_DOB.setText(null);
		buttonGroup.clearSelection();
		comboBox_ChucVu.setSelectedIndex(-1);
	}

//	public void findByName() {
//		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
//		
//		if (textField_find_name.getText().isBlank()) {
//			JOptionPane.showMessageDialog(null, "Nhập tên để tìm kiếm");
//		}else {
//			String name = textField_find_name.getText();
//			ArrayList<Employee> employees = new EmployeeBUS().findByName(name);
//			clearTableNhanVien();
//			for (Employee employee : employees) {
//				model.addRow(employee.toObjects());
//			}
//		}
//	}
	
	public void findByName() {
		DefaultTableModel model = (DefaultTableModel) table_nhanvien.getModel();
		
		if (textField_find_name.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Nhập tên để tìm kiếm");
			return;
		}
		String name = textField_find_name.getText();
		ArrayList<Employee> employees = new EmployeeBUS().findByName(name);
		clearTableNhanVien();
		for (Employee employee : employees) {
			model.addRow(employee.toObjects());
		}
	}
	
	public void reloadTableNhanVien() {
		updateTableNhanVien();
		buttonGroup2.clearSelection();
	}

//	public void findByCCCD() {
//		DefaultTableModel model = (DefaultTableModel)table_hoa_don.getModel();
//		
//		if (textField_find_cccd.getText().isBlank()) {
//			JOptionPane.showMessageDialog(null, "Nhập số cccd để tìm");
//		}else {
//			if (!textField_find_cccd.getText().matches(regex_cccd)) {
//				JOptionPane.showMessageDialog(null, "Số cccd không đúng dạng, hãy nhập lại");
//			}else {
//				String cccd = textField_find_cccd.getText();
//				ArrayList<HoaDon> hoaDons = new HoaDonBUS().findByCCCD(cccd);
//				clearTableHoaDon();
//				for (HoaDon hoaDon : hoaDons) {
//					model.addRow(hoaDon.toObjects());
//				}
//			}
//		}
//	}
	
	public void findByCCCD() {
		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
		
		if (textField_find_cccd.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Nhập CCCD để tìm");
			return;
		}
		if (!textField_find_cccd.getText().matches(regex_cccd)) {
			JOptionPane.showMessageDialog(null, "Số CCCD không đúng, hãy nhập lại");
			return;
		}
		String cccd = textField_find_cccd.getText();
		ArrayList<HoaDon> hoaDons = new HoaDonBUS().findByCCCD(cccd);
		clearTableHoaDon();
		for (HoaDon hoaDon : hoaDons) {
			model.addRow(hoaDon.toObjects());
		}
	}

	public void reloadTableHoaDon() {
		buttonGroup3.clearSelection();
		updateTableHoaDon();
		textField_find_cccd.setText(null);
	}

	public void deleteHoaDon() {
		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
		int rowIndex = table_hoa_don.getSelectedRow();
		
		if (!table_hoa_don.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Hãy chọn 1 khách hàng để xóa");
		}else {
			int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
			String cccd = String.valueOf(model.getValueAt(rowIndex, 1));
			int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2));
			Date ngayNhan = (Date) model.getValueAt(rowIndex, 3);
			Date ngayTra = (Date) model.getValueAt(rowIndex, 4);
			long price =  Long.valueOf((String) model.getValueAt(rowIndex, 5));
			
			new HoaDonBUS().deleteHoaDon(new HoaDon(stt, cccd, roomNumber, ngayNhan, ngayTra, price));
			updateTableHoaDon();
			Date currentDate = new Date(System.currentTimeMillis());
			label_totalRevenue.setText((new HoaDonBUS().totalRevenue())+" VNĐ");
			label_RevenueCurrentDate.setText((new HoaDonBUS().revenueCurrentDate(currentDate))+" VNĐ");
		}
	}

//	public void showDetails() {
//		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
//		int rowIndex = table_hoa_don.getSelectedRow();
//
//		
//		if (!table_hoa_don.isRowSelected(rowIndex)) {
//			JOptionPane.showMessageDialog(null, "Hãy chọn 1 hóa đơn để xem chi tiết");
//		}else {
//			long Price = Long.valueOf((String) model.getValueAt(rowIndex, 5));
//			int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
//
//			if (Price == 0) {
//				JOptionPane.showMessageDialog(null, "Hóa đơn số "+stt+" chưa được thanh toán, vui lòng chọn 1 hóa đơn khác");
//			}else {
//				String name = new HoaDonBUS().getNameByStt(stt);
//				String cccd = String.valueOf(model.getValueAt(rowIndex, 1));
//				int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2));
//				Date ngayNhan = (Date) model.getValueAt(rowIndex, 3);
//				Date ngatTra = (Date) model.getValueAt(rowIndex, 4);
//				long price = Long.valueOf((String) model.getValueAt(rowIndex, 5));
//				
//				new BillGUI(stt,name, cccd, roomNumber, ngatTra, ngayNhan, price);
//			}
//		}
//	}
	public void showDetails() {
		DefaultTableModel model = (DefaultTableModel) table_hoa_don.getModel();
		int rowIndex = table_hoa_don.getSelectedRow();
		
		if (!table_hoa_don.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 hóa đơn để xem chi tiết");
			return;
		}
		long Price = Long.valueOf((String) model.getValueAt(rowIndex, 5));
		int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		if (Price == 0) {
			JOptionPane.showMessageDialog(null, "Hóa đơn số "+stt+" chưa được thanh toán, vui lòng chọn 1 hóa đơn khác");
			return;
		}
		String name = new HoaDonBUS().getNameByStt(stt);
		String cccd = String.valueOf(model.getValueAt(rowIndex, 1));
		int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2));
		Date ngayNhan = (Date) model.getValueAt(rowIndex, 3);
		Date ngatTra = (Date) model.getValueAt(rowIndex, 4);
		long price = Long.valueOf((String) model.getValueAt(rowIndex, 5));
		
		new BillGUI(stt,name, cccd, roomNumber, ngatTra, ngayNhan, price);
	}
}
