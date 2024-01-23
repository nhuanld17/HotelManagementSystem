package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import CONTROLLER.BillController;

public class BillGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					BillGUI frame = new BillGUI();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillGUI(int stt,String name, String cccd, int roomNumber, Date ngayTra, Date ngayNhan, long price) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.desktop);
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(0, 0, 585, 375);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HÓA ĐƠN ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 25));
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBounds(210, 21, 185, 36);
		panel.add(lblNewLabel);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table.setBorder(new LineBorder(Color.BLACK,1));
		table.setForeground(SystemColor.desktop);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"STT",stt+""},
				{"Họ và tên", name+""},
				{"Số CCCD", cccd+""},
				{"Số phòng", roomNumber+""},
				{"Ngày nhận phòng", ngayNhan+""},
				{"Ngày trả phòng", ngayTra+""},
				{"Thành tiền", price+""},
			},
			new String[] {
				"卐卐卐卐卐", "卐卐卐卐卐"
			}
		));
		table.setRowHeight(28);
		JTableHeader header = table.getTableHeader();
		header.setBorder(new LineBorder(Color.black,1));
		header.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table.setBounds(10, 80, 565, 223);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 80, 565, 223);
		panel.add(scrollPane);
		
		ActionListener actionListener = new BillController(this);
		
		
		JButton btn_OK = new JButton("OK");
		btn_OK.setForeground(SystemColor.desktop);
		btn_OK.setBorder(new LineBorder(Color.BLACK,1));
		btn_OK.setBackground(new Color(255, 165, 0));
		btn_OK.addActionListener(actionListener);
		btn_OK.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_OK.setBounds(360, 332, 95, 30);
		panel.add(btn_OK);
		
		JButton btn_PRINT = new JButton("PRINT");
		btn_PRINT.setForeground(SystemColor.desktop);
		btn_PRINT.setBorder(new LineBorder(Color.BLACK,1));
		btn_PRINT.setIcon(new ImageIcon("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo_2\\icon\\icons8-print-24.png"));
		btn_PRINT.setBackground(new Color(255, 165, 0));
		btn_PRINT.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_PRINT.addActionListener(actionListener);
		btn_PRINT.setBounds(465, 332, 110, 30);
		panel.add(btn_PRINT);
		setVisible(true);
	}

	public void closeBill() {
		this.setVisible(false);
	}

	public void printBill() {
	    try {
	        JFileChooser fileChooser = new JFileChooser();
	        int option = fileChooser.showSaveDialog(BillGUI.this);
	        if (option == fileChooser.APPROVE_OPTION) {
	            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

	            if (!filePath.toLowerCase().endsWith(".pdf")) {
	                filePath += ".pdf";
	            }
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream(filePath));

	            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\segoeui.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            com.lowagie.text.Font font = new com.lowagie.text.Font(bf, 16);
	            document.open();

	            // Create a centered paragraph for the title
	            Paragraph labelParagraph = new Paragraph("HÓA ĐƠN", font);
	            labelParagraph.setAlignment(Element.ALIGN_CENTER);
	            labelParagraph.setSpacingBefore(150); // Adjust spacing before the title
	            labelParagraph.setSpacingAfter(50); // Adjust spacing after the title
	            document.add(labelParagraph);

	            // Add the table data
	            PdfPTable pdfPTable = getTableData(font);
	            document.add(pdfPTable);

	            document.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private PdfPTable getTableData(com.lowagie.text.Font font) {
		PdfPTable pdfPTable = new PdfPTable(table.getColumnCount());
//		for (int i = 0; i < table.getColumnCount(); i++) {
//			pdfPTable.addCell(new Paragraph(table.getColumnName(i),font));
//		}
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				Object cellValue = table.getValueAt(i, j);
				if (cellValue != null) {
					pdfPTable.addCell(new Paragraph(cellValue.toString(),font));
				}else {
					pdfPTable.addCell(new Paragraph("",font));
				}
			}
		}
		return pdfPTable;
	}
}
