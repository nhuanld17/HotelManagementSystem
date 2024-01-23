package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.PhongBUS;
import CONTROLLER.KhachHangController;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.Phong;

public class KhachHangGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_cccd;
	private JTextField textField_ngaysinh;
	private JTextField textField_roomNumber;
	private JTable table_KhachHang;
	private JTable table_phong;
	public JTable table_hoadon;
	public String regex = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$\r\n";
	public String regex_cccd = "^(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64)[0-9]{10}$";
	private JTextField textField_find_name;
	private JTextField textField_findBycccd_HD;
	private JLabel label_totalRevenue;
	private JLabel label_totalRevenue_1;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private JLabel label_title_list_hoadon;
	private long revenueCurrentDate;
	private JLabel label_totalRevenue_1_1;
	private JLabel label_totalRevenue_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					KhachHangGUI frame = new KhachHangGUI();
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
	public KhachHangGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-hotel-48.png"));
		setTitle("EMPLOYEE AREA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 49, 63));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(SystemColor.desktop);
		tabbedPane.setBackground(SystemColor.controlHighlight);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 18));
		tabbedPane.setBounds(0, 0, 862, 457);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("THÔNG TIN KHÁCH HÀNG", null, panel, "Thông tin khách hàng");
		tabbedPane.setBackgroundAt(0, SystemColor.controlHighlight);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Họ và tên");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 79, 84, 22);
		panel.add(lblNewLabel);
		
		textField_name = new JTextField();
		textField_name.setBorder(new LineBorder(Color.BLACK,1));
		textField_name.setForeground(SystemColor.desktop);
		textField_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_name.setBounds(104, 77, 175, 28);
		panel.add(textField_name);

		
		JLabel lblSCccd = new JLabel("Số cccd");
		lblSCccd.setForeground(Color.WHITE);
		lblSCccd.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSCccd.setBounds(10, 114, 84, 22);
		panel.add(lblSCccd);
		
		textField_cccd = new JTextField();
		textField_cccd.setBorder(new LineBorder(Color.BLACK,1));
		textField_cccd.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_cccd.setForeground(SystemColor.desktop);
		textField_cccd.setBounds(104, 112, 175, 28);
		panel.add(textField_cccd);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setForeground(Color.WHITE);
		lblNgySinh.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNgySinh.setBounds(302, 79, 84, 22);
		panel.add(lblNgySinh);
		
		textField_ngaysinh = new JTextField();
		textField_ngaysinh.setBorder(new LineBorder(Color.BLACK,1));
		textField_ngaysinh.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_ngaysinh.setForeground(SystemColor.desktop);
		textField_ngaysinh.setBounds(396, 77, 175, 28);
		panel.add(textField_ngaysinh);
		
		JLabel lblSPhng = new JLabel("Số phòng");
		lblSPhng.setForeground(Color.WHITE);
		lblSPhng.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSPhng.setBounds(302, 114, 84, 22);
		panel.add(lblSPhng);
		
		textField_roomNumber = new JTextField();
		textField_roomNumber.setBorder(new LineBorder(Color.BLACK,1));
		textField_roomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_roomNumber.setForeground(SystemColor.desktop);
		textField_roomNumber.setBounds(396, 112, 175, 28);
		panel.add(textField_roomNumber);
		
		ActionListener actionListener = new KhachHangController(this);
		
		JButton btn_THEM = new JButton("THÊM");
		btn_THEM.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-add-24.png"));
		btn_THEM.setBorder(new LineBorder(Color.BLACK,1));
		btn_THEM.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_THEM.setBackground(new Color(46, 204, 113));
		btn_THEM.setForeground(SystemColor.desktop);
		btn_THEM.addActionListener(actionListener);
		btn_THEM.setBounds(607, 77, 113, 30);
		panel.add(btn_THEM);
		
		table_KhachHang = new JTable();
		table_KhachHang.setRowHeight(25);
		table_KhachHang.setForeground(SystemColor.desktop);
		table_KhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_KhachHang.setBackground(SystemColor.controlHighlight);
		table_KhachHang.setBorder(new LineBorder(Color.BLACK,1));
		table_KhachHang.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Họ và tên", "Số cccd", "Ngày sinh"
			}
		));
		JTableHeader header = table_KhachHang.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header.setForeground(SystemColor.text);
		header.setBorder(new LineBorder(Color.black,1));
		header.setBackground(new Color(0, 48, 143));
		table_KhachHang.setBounds(10, 200, 837, 221);
		updateKhachHangTable();
		
		JScrollPane scrollPane = new JScrollPane(table_KhachHang);
		scrollPane.setBounds(10, 213, 837, 197);
		panel.add(scrollPane);
		
		JButton btn_XOA = new JButton("XÓA");
		btn_XOA.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-delete-24.png"));
		btn_XOA.setBackground(new Color(231, 76, 60));
		btn_XOA.setForeground(SystemColor.desktop);
		btn_XOA.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_XOA.setBorder(new LineBorder(Color.black,1));
		btn_XOA.addActionListener(actionListener);
		btn_XOA.setBounds(730, 77, 113, 30);
		panel.add(btn_XOA);
		
		JButton btn_SUA = new JButton("SỬA");
		btn_SUA.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-edit-30.png"));
		btn_SUA.setBackground(new Color(52, 152, 219));
		btn_SUA.setForeground(SystemColor.desktop);
		btn_SUA.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_SUA.setBorder(new LineBorder(Color.BLACK,1));
		btn_SUA.addActionListener(actionListener);
		btn_SUA.setBounds(607, 112, 113, 30);
		panel.add(btn_SUA);
		
		JButton btn_CAPNHAT = new JButton("CẬP NHẬT");
		btn_CAPNHAT.setBackground(new Color(52, 152, 219));
		btn_CAPNHAT.setForeground(SystemColor.desktop);
		btn_CAPNHAT.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_CAPNHAT.setBorder(new LineBorder(Color.BLACK,1));
		btn_CAPNHAT.addActionListener(actionListener);
		btn_CAPNHAT.setBounds(730, 112, 113, 30);
		panel.add(btn_CAPNHAT);
		
		JLabel lblNewLabel_1 = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-customer-50.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(267, 11, 317, 41);
		panel.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(10, 151, 833, 3);
		panel.add(separator);
		
		JLabel lblSCccd_1 = new JLabel("Họ và tên");
		lblSCccd_1.setForeground(Color.WHITE);
		lblSCccd_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSCccd_1.setBounds(10, 176, 84, 22);
		panel.add(lblSCccd_1);
		
		textField_find_name = new JTextField();
		textField_find_name.setForeground(SystemColor.desktop);
		textField_find_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_find_name.setBorder(new LineBorder(Color.BLACK,1));
		textField_find_name.setBounds(104, 174, 175, 28);
		panel.add(textField_find_name);
		
		JButton btn_Tim_cccd = new JButton("TÌM");
		btn_Tim_cccd.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-find-25.png"));
		btn_Tim_cccd.addActionListener(actionListener);
		btn_Tim_cccd.setForeground(SystemColor.desktop);
		btn_Tim_cccd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Tim_cccd.setBorder(new LineBorder(Color.BLACK,1));
		btn_Tim_cccd.setBackground(new Color(52, 152, 219));
		btn_Tim_cccd.setBounds(289, 173, 84, 30);
		panel.add(btn_Tim_cccd);
		
		JButton btn_TAI_LAI = new JButton("TẢI LẠI");
		btn_TAI_LAI.addActionListener(actionListener);
		btn_TAI_LAI.setForeground(SystemColor.desktop);
		btn_TAI_LAI.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_TAI_LAI.setBorder(new LineBorder(Color.BLACK,1));
		btn_TAI_LAI.setBackground(new Color(255, 165, 0));
		btn_TAI_LAI.setBounds(383, 173, 84, 30);
		panel.add(btn_TAI_LAI);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(10, 65, 833, 3);
		panel.add(separator_1);
		
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
		panel.add(btnNewButton);
		
		JRadioButton rdbtn_sortName = new JRadioButton("Sắp xếp theo tên");
		rdbtn_sortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();//Lấy đối tượng DefaultModeltable
																						 
				ArrayList<KhachHang> khachHangs = new KhachHangBUS().sortByName();// Lấy danh sách khách hàng đã được 
																				 // sắp xếp theo tên từ mysql
				clearTableKhachHang();		// Xóa bảng khách hàng									  
				for (KhachHang khachHang : khachHangs) {
					model.addRow(khachHang.toObjects());
				}
			}
		});
		rdbtn_sortName.setForeground(SystemColor.window);
		rdbtn_sortName.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_sortName.setBackground(new Color(51, 51, 51));
		rdbtn_sortName.setBounds(473, 173, 153, 30);
		panel.add(rdbtn_sortName);
		
		JRadioButton rdbtn_sortDOB = new JRadioButton("Sắp xếp theo ngày sinh");
		rdbtn_sortDOB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
				
				ArrayList<KhachHang> khachHangs = new KhachHangBUS().sortByBirthDate();
				clearTableKhachHang();
				for (KhachHang khachHang : khachHangs) {
					model.addRow(khachHang.toObjects());
				}
			}
		});
		rdbtn_sortDOB.setForeground(SystemColor.window);
		rdbtn_sortDOB.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_sortDOB.setBackground(new Color(51, 51, 51));
		rdbtn_sortDOB.setBounds(628, 173, 219, 30);
		panel.add(rdbtn_sortDOB);
		
		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rdbtn_sortName);
		buttonGroup1.add(rdbtn_sortDOB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("DANH SÁCH PHÒNG", null, panel_1, null);
		tabbedPane.setBackgroundAt(1, SystemColor.controlHighlight);
		panel_1.setLayout(null);
		
		table_phong = new JTable();
		table_phong.setRowHeight(25);
		table_phong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_phong.setBackground(SystemColor.controlHighlight);
		table_phong.setForeground(SystemColor.desktop);
		table_phong.setBorder(new LineBorder(Color.black,1));
		table_phong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Số phòng"
			}
		));
		JTableHeader header2 = table_phong.getTableHeader();
		header2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header2.setForeground(SystemColor.text);
		header2.setBorder(new LineBorder(Color.BLACK,1));
		header2.setBackground(new Color( 0, 48, 143));
		table_phong.setBounds(204, 58, 335, 352);
		updateTablePhong();
		
		JScrollPane scrollPane_1 = new JScrollPane(table_phong);
		scrollPane_1.setBounds(225, 69, 335, 352);
		panel_1.add(scrollPane_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DANH SÁCH PHÒNG");
		lblNewLabel_1_1.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-hotel-room-60.png"));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(211, 11, 349, 41);
		panel_1.add(lblNewLabel_1_1);
		
		JRadioButton rdbtn_Empty = new JRadioButton("Hiển thị phòng còn trống");
		rdbtn_Empty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_phong.getModel();
				
				ArrayList<Phong> phongs = new PhongBUS().showEmpty();
				clearTablePhong();
				for (Phong phong : phongs) {
					model.addRow(phong.toObjects());
				}
			}
		});
		rdbtn_Empty.setFont(new Font("Segoe UI", Font.BOLD, 18));
		rdbtn_Empty.setForeground(new Color(255, 255, 255));
		rdbtn_Empty.setBackground(new Color(51, 51, 51));
		rdbtn_Empty.setBounds(566, 69, 241, 23);
		panel_1.add(rdbtn_Empty);
		
		JRadioButton rdbtn_isOrdered = new JRadioButton("Hiển thị phòng đã được đặt");
		rdbtn_isOrdered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_phong.getModel();
				
				ArrayList<Phong> phongs = new PhongBUS().showIsOrdered();
				clearTablePhong();
				for (Phong phong : phongs) {
					model.addRow(phong.toObjects());
				}
			}
		});
		rdbtn_isOrdered.setForeground(Color.WHITE);
		rdbtn_isOrdered.setFont(new Font("Segoe UI", Font.BOLD, 18));
		rdbtn_isOrdered.setBackground(new Color(51, 51, 51));
		rdbtn_isOrdered.setBounds(566, 95, 269, 23);
		panel_1.add(rdbtn_isOrdered);
		
		JRadioButton rdbtn_showAll = new JRadioButton("Hiển thị tất cả phòng");
		rdbtn_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTablePhong();
				updateTablePhong();
			}
		});
		rdbtn_showAll.setForeground(Color.WHITE);
		rdbtn_showAll.setFont(new Font("Segoe UI", Font.BOLD, 18));
		rdbtn_showAll.setBackground(new Color(51, 51, 51));
		rdbtn_showAll.setBounds(566, 121, 241, 23);
		panel_1.add(rdbtn_showAll);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_Empty);
		buttonGroup.add(rdbtn_isOrdered);
		buttonGroup.add(rdbtn_showAll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("   HÓA ĐƠN   ", null, panel_2, null);
		tabbedPane.setBackgroundAt(2, SystemColor.controlHighlight);
		panel_2.setLayout(null);
		
		table_hoadon = new JTable();
		table_hoadon.setBackground(SystemColor.controlHighlight);
		table_hoadon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_hoadon.setForeground(SystemColor.desktop);
		table_hoadon.setBorder(new LineBorder(Color.BLACK,1));
		table_hoadon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "CCCD", "Số phòng", "Ngày nhận", "Ngày trả", "Giá"
			}
		));
		table_hoadon.setBounds(10, 143, 837, 267);
		JTableHeader header3 = table_hoadon.getTableHeader();
		header3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header3.setForeground(SystemColor.text);
		header3.setBorder(new LineBorder(Color.BLACK,1));
		header3.setBackground(new Color( 0, 48, 143));
		table_hoadon.setRowHeight(25);
		JScrollPane scrollPane_2 = new JScrollPane(table_hoadon);
		scrollPane_2.setBounds(10, 102, 837, 231);
		panel_2.add(scrollPane_2);
		updateTableHoaDon();
		
		label_title_list_hoadon = new JLabel("DANH SÁCH HÓA ĐƠN");
		label_title_list_hoadon.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-bill-50.png"));
		label_title_list_hoadon.setForeground(new Color(255, 255, 255));
		label_title_list_hoadon.setFont(new Font("Courier New", Font.BOLD, 25));
		label_title_list_hoadon.setBounds(239, 8, 353, 38);
		panel_2.add(label_title_list_hoadon);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.desktop);
		separator_2.setBackground(SystemColor.desktop);
		separator_2.setBounds(10, 49, 837, 3);
		panel_2.add(separator_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số CCCD");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 63, 84, 26);
		panel_2.add(lblNewLabel_3);
		
		textField_findBycccd_HD = new JTextField();
		textField_findBycccd_HD.setForeground(SystemColor.desktop);
		textField_findBycccd_HD.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_findBycccd_HD.setBorder(new LineBorder(Color.BLACK,1));
		textField_findBycccd_HD.setBounds(82, 63, 130, 28);
		panel_2.add(textField_findBycccd_HD);
		
		JButton btn_Tim_cccd_1 = new JButton("Tìm");
		btn_Tim_cccd_1.addActionListener(actionListener);
		btn_Tim_cccd_1.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-find-25.png"));
		btn_Tim_cccd_1.setForeground(SystemColor.desktop);
		btn_Tim_cccd_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Tim_cccd_1.setBorder(new LineBorder(Color.BLACK,1));
		btn_Tim_cccd_1.setBackground(new Color(52, 152, 219));
		btn_Tim_cccd_1.setBounds(222, 62, 84, 30);
		panel_2.add(btn_Tim_cccd_1);
		
		JButton btn_TAI_LAI_1 = new JButton("Tải lại");
		btn_TAI_LAI_1.addActionListener(actionListener);
		btn_TAI_LAI_1.setForeground(SystemColor.desktop);
		btn_TAI_LAI_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_TAI_LAI_1.setBorder(new LineBorder(Color.BLACK,1));
		btn_TAI_LAI_1.setBackground(new Color(52, 152, 219));
		btn_TAI_LAI_1.setBounds(316, 62, 84, 30);
		panel_2.add(btn_TAI_LAI_1);
		
		JButton btn_TinhTien = new JButton("Tính tiền");
		btn_TinhTien.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-calculator-24.png"));
		btn_TinhTien.addActionListener(actionListener);
		btn_TinhTien.setForeground(SystemColor.desktop);
		btn_TinhTien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_TinhTien.setBorder(new LineBorder(Color.BLACK,1));
		btn_TinhTien.setBackground(new Color(255, 165, 0));
		btn_TinhTien.setBounds(420, 62, 113, 30);
		panel_2.add(btn_TinhTien);
		
		label_totalRevenue = new JLabel("TỔNG DOANH THU : ");
		label_totalRevenue.setFont(new Font("Courier New", Font.BOLD, 18));
		label_totalRevenue.setForeground(SystemColor.window);
		label_totalRevenue.setBounds(43, 344, 187, 26);
		panel_2.add(label_totalRevenue);
		
		label_totalRevenue_1 = new JLabel("");
		label_totalRevenue_1.setForeground(SystemColor.window);
		label_totalRevenue_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_totalRevenue_1.setBounds(229, 344, 187, 26);
		panel_2.add(label_totalRevenue_1);
		long total = new HoaDonBUS().totalRevenue();
		this.label_totalRevenue_1.setText(total+" "+"VNĐ");
		
		label_totalRevenue_2 = new JLabel("DOANH THU NGÀY ");
		label_totalRevenue_2.setForeground(SystemColor.window);
		label_totalRevenue_2.setFont(new Font("Courier New", Font.BOLD, 18));
		label_totalRevenue_2.setBounds(43, 381, 297, 26);
		panel_2.add(label_totalRevenue_2);
		Date currentDate = new Date(System.currentTimeMillis());
		String string = "DOANH THU NGÀY "+currentDate+": "; 
		label_totalRevenue_2.setText(string);
		
		label_totalRevenue_1_1 = new JLabel("3000000 VNĐ");
		label_totalRevenue_1_1.setForeground(SystemColor.window);
		label_totalRevenue_1_1.setFont(new Font("Courier New", Font.BOLD, 18));
		label_totalRevenue_1_1.setBounds(333, 381, 187, 26);
		panel_2.add(label_totalRevenue_1_1);
		revenueCurrentDate = new HoaDonBUS().revenueCurrentDate(currentDate);
		label_totalRevenue_1_1.setText(revenueCurrentDate+" VNĐ");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Hóa đơn chưa thanh toán");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
				
				ArrayList<HoaDon> hoaDons = new HoaDonBUS().listNotPayed();
				clearTableHoaDon();
				for (HoaDon hoaDon : hoaDons) {
					model.addRow(hoaDon.toObjects());
				}
			}
		});
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBackground(new Color(51, 51, 51));
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton.setBounds(541, 62, 246, 30);
		panel_2.add(rdbtnNewRadioButton);
		
		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdbtnNewRadioButton);
		
		JButton btn_print = new JButton("Print");
		btn_print.addActionListener(actionListener);
		btn_print.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2_v3\\icon\\icons8-print-24.png"));
		btn_print.setForeground(SystemColor.desktop);
		btn_print.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_print.setBorder(new LineBorder(Color.BLACK,1));
		btn_print.setBackground(new Color(255, 165, 0));
		btn_print.setBounds(760, 344, 87, 30);
		panel_2.add(btn_print);
		
		setVisible(true);
	}



	private void updateTableHoaDon() {
		DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
		
		ArrayList<HoaDon> hoaDons = new HoaDonBUS().listHoaDons();
		clearTableHoaDon();
		for (HoaDon hoaDon : hoaDons) {
			model.addRow(hoaDon.toObjects());
		}
	}

	private void clearTableHoaDon() {
		DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	private void updateTablePhong() {
		DefaultTableModel model = (DefaultTableModel) table_phong.getModel();
		
		ArrayList<Phong> phongs = new PhongBUS().listPhongs();
		clearTablePhong();
		
		for (Phong phong : phongs) {
			model.addRow(phong.toObjects());
		}
	}

	private void clearTablePhong() {
		DefaultTableModel model = (DefaultTableModel) table_phong.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount-1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	private void updateKhachHangTable() {
		DefaultTableModel model = (DefaultTableModel)table_KhachHang.getModel();
		
		ArrayList<KhachHang> khachHangs = new KhachHangBUS().listKhachHangs();
		
		clearTableKhachHang();
		for (KhachHang khachHang : khachHangs) {
			model.addRow(khachHang.toObjects());
		}
	}

	private void clearTableKhachHang() {
		DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
	
	
	// Thêm khách hàng
	public void addKhachHang() {
	    String name = textField_name.getText();
	    String cccd = textField_cccd.getText();
	    String ngaySinhText = textField_ngaysinh.getText();
	    String roomNumberText = textField_roomNumber.getText();

	    if (name.isBlank() || cccd.isBlank() || ngaySinhText.isBlank() || roomNumberText.isBlank()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
	        return;
	    }

	    if (!cccd.matches(regex_cccd)) {
	        JOptionPane.showMessageDialog(null, "Số CCCD không đúng định dạng");
	        return;
	    }

	    if (!ngaySinhText.matches(regex)) {
	        JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo dạng yyyy-MM-dd");
	        return;
	    }

	    int roomNumber = Integer.parseInt(roomNumberText);
	    Phong phong = new Phong(roomNumber);

	    if (new PhongBUS().isOrdered(phong)) {
	        JOptionPane.showMessageDialog(null, "Phòng " + roomNumber + " đã được đặt");
	        return;
	    }

	    if (new KhachHangBUS().isDuplicateCCCD(cccd)) {
	        if (new KhachHangBUS().isDuplicateName(name, cccd)) {
	            JOptionPane.showMessageDialog(null, "Số CCCD bị trùng");
	        } else {
	            addKhachHangAndHoaDon(cccd, name, ngaySinhText, roomNumber);
	        }
	    } else {
	        addKhachHangAndHoaDon(cccd, name, ngaySinhText, roomNumber);
	    }
	}

	private void addKhachHangAndHoaDon(String cccd, String name, String ngaySinhText, int roomNumber) {
	    Date ngaysinh = Date.valueOf(ngaySinhText);
	    Date ngaynhan = new Date(System.currentTimeMillis());

	    KhachHang khachHang = new KhachHang(cccd, name, ngaysinh);
	    HoaDon hoaDon = new HoaDon(cccd, roomNumber, ngaynhan);

	    new KhachHangBUS().addKhachHang(khachHang);
	    new HoaDonBUS().addHoaDon(hoaDon);
	    updateKhachHangTable();
	    updateTableHoaDon();
	    textField_name.setText(null);
	    textField_ngaysinh.setText(null);
	    textField_cccd.setText(null);
	    textField_roomNumber.setText(null);
	}

	
	public void deleteKhachHang() {
		DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
		int rowIndex = table_KhachHang.getSelectedRow();
		
		if (!table_KhachHang.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng để xóa");
		}else {
			int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
			String cccd = String.valueOf(model.getValueAt(rowIndex, 2));
			String name = String.valueOf(model.getValueAt(rowIndex, 1));
			Date ngaySinh = (Date) model.getValueAt(rowIndex, 3);
			
			KhachHang khachHang = new KhachHang(stt, cccd, name, ngaySinh);
			HoaDon hoaDon = new HoaDon(stt, cccd);
			new KhachHangBUS().deleteKhachHang(khachHang);
			//new HoaDonBUS().deleteHoaDon(hoaDon);
			updateKhachHangTable();
			updateTableHoaDon();
			Date currentDate = new Date(System.currentTimeMillis());
			long totalRevenue = new HoaDonBUS().totalRevenue();
			long currentRevenue = new HoaDonBUS().revenueCurrentDate(currentDate);
			label_totalRevenue_1.setText(totalRevenue+" VNĐ");
			label_totalRevenue_1_1.setText(currentRevenue+" VNĐ");
		}
	}

	public void setForm() {
		DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
		int rowIndex = table_KhachHang.getSelectedRow();
		
		if (!table_KhachHang.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng trong bảng");
		}else {
			int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
			String name = (String) model.getValueAt(rowIndex, 1);
			String cccd = (String) model.getValueAt(rowIndex, 2);
			Date ngaysinh = (Date) model.getValueAt(rowIndex, 3);
			
			int sophong = new HoaDonBUS().getSoPhong(stt);
			
			textField_name.setText(name);
			textField_cccd.setText(cccd);
			textField_ngaysinh.setText(ngaysinh+"");
			textField_roomNumber.setText(sophong+"");
		}
	}

	
//	public void update() {
//	    DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
//	    int rowIndex = table_KhachHang.getSelectedRow();
//
//	    if (!table_KhachHang.isRowSelected(rowIndex)) {
//	        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng trong bảng");
//	        return;
//	    }
//
//	    String name = textField_name.getText();
//	    String cccd = textField_cccd.getText();
//	    String ngaySinhText = textField_ngaysinh.getText();
//	    String roomNumberText = textField_roomNumber.getText();
//
//	    if (name.isBlank() || cccd.isBlank() || ngaySinhText.isBlank() || roomNumberText.isBlank()) {
//	        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
//	        return;
//	    }
//
//	    if (!cccd.matches(regex_cccd)) {
//	        JOptionPane.showMessageDialog(null, "Số CCCD không đúng");
//	        return;
//	    }
//
//	    Date ngaySinh = null;
//	    try {
//	        ngaySinh = Date.valueOf(ngaySinhText);
//	    } catch (Exception e) {
//	        JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
//	        return;
//	    }
//
//	    int stt = Integer.parseInt((String) model.getValueAt(rowIndex, 0));
//
//	    int oldRoomNumber = new HoaDonBUS().getSoPhong(stt);
//	    int newRoomNumber = Integer.parseInt(roomNumberText);
//	    
//	    // Nếu kiểm tra khách hàng đã trả phong  == false (chưa trả phòng)
//	    // Kiểm tra nếu số phòng thay đổi = số phòng đã được đặt thì thông báo
//	    if (new KhachHangBUS().returned(stt) == false) {
//		    if (oldRoomNumber != newRoomNumber && new PhongBUS().isOrdered(new Phong(newRoomNumber))) {
//		        JOptionPane.showMessageDialog(null, "Phòng " + newRoomNumber + " đã được đặt");
//		        return;
//		    }
//		}
//
//	    KhachHang khachHang = new KhachHang(stt, cccd, name, ngaySinh);
//	    HoaDon hoaDon = new HoaDon(stt, cccd, newRoomNumber);
//
//	    new KhachHangBUS().editKhachHang(khachHang);
//	    new HoaDonBUS().updateHoaDon(hoaDon);
//	    updateKhachHangTable();
//	    updateTableHoaDon();
//	    textField_name.setText(null);
//	    textField_ngaysinh.setText(null);
//	    textField_cccd.setText(null);
//	    textField_roomNumber.setText(null);
//	}
	
	public void update() {
	    DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
	    int rowIndex = table_KhachHang.getSelectedRow();

	    if (!table_KhachHang.isRowSelected(rowIndex)) {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng trong bảng");
	        return;
	    }
	    if (textField_name.getText().isBlank() || textField_cccd.getText().isBlank() || textField_ngaysinh.getText().isBlank() || textField_roomNumber.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
			return;
		}
	    if (!textField_cccd.getText().matches(regex_cccd)) {
			JOptionPane.showMessageDialog(null, "Số CCCD không đúng");
			return;
		}
	    // Kiểm tra trùng số CCCD
	    String oldCCCD = String.valueOf(model.getValueAt(rowIndex, 2));
	    String newCCCd = textField_cccd.getText();
	    
	    // Nếu đổi số CCCD mới, kiểm tra sự trùng lặp của CCCD
	    if (!newCCCd.equals(oldCCCD)) {
	    	// Nếu CCCD mới trùng với CCCD đã có trong danh sách, kiểm tra sự trùng lặp của tên
			if (new KhachHangBUS().isDuplicateCCCD(newCCCd)) {
				// Nếu tên của CCCD mới khác với tên của CCCD trong danh sách 
				// ==> Bị trùng số CCCD
				if (new KhachHangBUS().isDuplicateName(textField_name.getText(), newCCCd)) {
					JOptionPane.showMessageDialog(null, "Số CCCD bị trùng");
					return;
				}
			}
		}
	    
	    if (!textField_ngaysinh.getText().matches(regex)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
			return;
		}
	    int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
	    int oldRoomNumber = new HoaDonBUS().getSoPhong(stt);
	    int newRoomNumber = Integer.parseInt(textField_roomNumber.getText());
	    // Thay đổi phòng, nếu khách hàng đã trả phòng thì cho thay đổi phòng tùy ý
	    // Nếu khách hàng chưa trả phòng thì kiểm tra phòng mới đã được đặt hay chưa
	    
	    // Nếu khách hàng trả phòng  == false(chưa trả)
	    //==> kiểm tra 
	    if (new KhachHangBUS().returned(stt) == false) {
		    if (oldRoomNumber != newRoomNumber && new PhongBUS().isOrdered(new Phong(newRoomNumber))) {
	        	JOptionPane.showMessageDialog(null, "Phòng " + newRoomNumber + " đã được đặt");
	        	return;
	    	}
		}
	    Date ngaySinh = Date.valueOf(textField_ngaysinh.getText());
	    KhachHang khachHang = new KhachHang(stt, newCCCd, textField_name.getText(), ngaySinh);
	    HoaDon hoaDon = new HoaDon(stt, newCCCd, newRoomNumber);
	    new KhachHangBUS().editKhachHang(khachHang);
	    new HoaDonBUS().updateHoaDon(hoaDon);
	    updateKhachHangTable();
	    updateTableHoaDon();
	    textField_name.setText(null);
	    textField_ngaysinh.setText(null);
	    textField_cccd.setText(null);
	    textField_roomNumber.setText(null);
	}

	public void findByName() {
		DefaultTableModel model = (DefaultTableModel)table_KhachHang.getModel();
		if (textField_find_name.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
		}else {
			String name = textField_find_name.getText();
			ArrayList<KhachHang> khachHangs = new KhachHangBUS().findByName(name);
			clearTableKhachHang();
			for (KhachHang khachHang : khachHangs) {
				model.addRow(khachHang.toObjects());
			}
		}
	}

	public void refreshTableKhachHang() {
		buttonGroup1.clearSelection();
		textField_find_name.setText(null);
		updateKhachHangTable();
	}

	public void findByCCCD_HD() {
		DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
		if (textField_findBycccd_HD.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
		}else {
			if (!textField_findBycccd_HD.getText().matches(regex_cccd)) {
				JOptionPane.showMessageDialog(null, "Số CCCD không đúng");
			}else {
				String cccd = textField_findBycccd_HD.getText();
				ArrayList<HoaDon> hoaDons = new HoaDonBUS().findByCCCD(cccd);
				clearTableHoaDon();
				for (HoaDon hoaDon : hoaDons) {
					model.addRow(hoaDon.toObjects());
				}
			}
		}
	}

	public void refreshTableHoaDon() {
		buttonGroup2.clearSelection();
		textField_findBycccd_HD.setText(null);
		clearTableHoaDon();
		updateTableHoaDon();
	}

	public void calculateMoney() {
		DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
		int rowIndex = table_hoadon.getSelectedRow();
		
		if (!table_hoadon.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Hãy chọn 1 khách hàng trong bảng để tính tiền");
		}else {
			long prices = Long.valueOf((String) model.getValueAt(rowIndex, 5));
			int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
			if (prices > 0) {
				JOptionPane.showMessageDialog(null, "Khách hàng đã thanh toán, vui lòng chọn khách hàng khác trong bảng");
			}else {
				String name = new HoaDonBUS().getNameByStt(stt);
				String cccd = (String) model.getValueAt(rowIndex, 1);
				int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2)) ;
				Date ngayTra = new Date(System.currentTimeMillis()); 
				Date ngayNhan = (Date) model.getValueAt(rowIndex, 3);
				
				LocalDate ngayTraLocal = ngayTra.toLocalDate();
				LocalDate ngayNhanLocal = ngayNhan.toLocalDate();
				long dayDiff = ChronoUnit.DAYS.between(ngayNhanLocal, ngayTraLocal);
				
				long price = (dayDiff+1)*500000;
				revenueCurrentDate += price;
				label_totalRevenue_1_1.setText(revenueCurrentDate+" VNĐ");
				label_totalRevenue_1.setText((new HoaDonBUS().totalRevenue()+price)+" VNĐ");
				new BillGUI(stt, name, cccd, roomNumber, ngayTra, ngayNhan, price);
				new HoaDonBUS().calculateMoney(ngayTra, price, stt);
				updateTableHoaDon();
			}
			
		}
	}



	public void toExcel(TableModel model) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showSaveDialog(KhachHangGUI.this);
			
			if (option == fileChooser.APPROVE_OPTION) {
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				
				if (!filePath.toLowerCase().endsWith(".xlsx")) {
					filePath+=".xlsx";
				}
				
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Bill List");
				
				Row headeRow = sheet.createRow(0);
				
				for (int col = 0; col < model.getColumnCount(); col++) {
					Cell cell = headeRow.createCell(col);
					cell.setCellValue(String.valueOf(model.getColumnName(col)));
				}
				
				for (int row = 0; row < model.getRowCount(); row++) {
					Row dataRow = sheet.createRow(row+1);
					for (int col = 0; col < model.getColumnCount(); col++) {
						Cell cell = dataRow.createCell(col);
						cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
					}
				}
				
				FileOutputStream fileOut = new FileOutputStream(filePath);
				workbook.write(fileOut);
				workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	public void printting() {
//		try {
//			JFileChooser fileChooser = new JFileChooser();
//			int option = fileChooser.showSaveDialog(KhachHangGUI.this);
//			if (option == fileChooser.APPROVE_OPTION) {
//				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//				
//				if (!filePath.toLowerCase().endsWith(".pdf")) {
//					filePath += ".pdf";
//				}
//				
//				Document document = new Document();
//				PdfWriter.getInstance(document, new FileOutputStream(filePath));
//				
//				BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\segoeui.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//				com.lowagie.text.Font font = new com.lowagie.text.Font(bf,11);
//				document.open();
//
//				Paragraph labelParagraph = new Paragraph("                                                                DANH SÁCH HÓA ĐƠN\n\n",font);
//				document.add(labelParagraph);
//				Paragraph tableParagraph = new Paragraph();
//				PdfPTable pdfPTable = getTableData(font);
//				tableParagraph.add(pdfPTable);
//				document.add(tableParagraph);
//				
//				document.add(new Paragraph("\n\n"));
//				long totalRevenue = new HoaDonBUS().totalRevenue();
//				Date currentDate = new Date(System.currentTimeMillis());
//				
//				long currentRevenue = new HoaDonBUS().revenueCurrentDate(currentDate);
//				
//				document.add(new Paragraph("               TỔNG DOANH THU: "+totalRevenue+" VNĐ\n",font));
//				document.add(new Paragraph("               DOANH THU NGÀY "+currentDate+": "+currentRevenue+" VNĐ",font));
//				
//				document.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
//	private PdfPTable getTableData(com.lowagie.text.Font font) {
//		PdfPTable pdfPTable = new PdfPTable(table_hoadon.getColumnCount());
//		for (int i = 0; i < table_hoadon.getColumnCount(); i++) {
//			pdfPTable.addCell(new Paragraph(table_hoadon.getColumnName(i),font));
//		}
//		for (int i = 0; i < table_hoadon.getRowCount(); i++) {
//			for (int j = 0; j < table_hoadon.getColumnCount(); j++) {
//				Object cellValue = table_hoadon.getValueAt(i, j);
//				if (cellValue != null) {
//					pdfPTable.addCell(new Paragraph(cellValue.toString(),font));
//				}else {
//					pdfPTable.addCell(new Paragraph("",font));
//				}
//			}
//		}
//		return pdfPTable;
//	}
}
