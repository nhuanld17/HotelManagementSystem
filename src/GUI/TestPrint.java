package GUI;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import BUS.KhachHangBUS;
import DTO.KhachHang;
public class TestPrint extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestPrint frame = new TestPrint();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public TestPrint() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 848, 499);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 832, 458);
        contentPane.add(panel);
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel("BẢNG NHÂN VIÊN");
        //lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(233, 11, 323, 62);
        panel.add(lblNewLabel);
        table = new JTable();
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Column 1", "Column 2", "Column 3", "Column 4"
        	}
        ));
        table.setBounds(10, 106, 618, 229);
        ArrayList<KhachHang> khachHangs = new KhachHangBUS().listKhachHangs();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (KhachHang khachHang : khachHangs) {
            model.addRow(khachHang.toObjects());
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 106, 797, 229);
        panel.add(scrollPane);
        JButton btnExportToPdf = new JButton("Export to PDF");
        btnExportToPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToPdf();
			}
		});
        btnExportToPdf.setBounds(10, 346, 120, 23);
        panel.add(btnExportToPdf);
    }
    private void exportToPdf() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(TestPrint.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn được chọn bởi người dùng
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                // Nếu không có phần mở rộng .pdf, thêm nó vào
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                // Tạo tài liệu và xuất file PDF
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

                // Thiết lập font Unicode hỗ trợ tiếng Việt
                BaseFont bf = BaseFont.createFont("F:\\eclipse-workspace\\Java Rewrite Excercise\\Nonametoo\\icon\\ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                //Font font = new Font(bf, 12);
                com.lowagie.text.Font font = new com.lowagie.text.Font(bf, 12);

                document.open();

                // Xuất nội dung
                Paragraph labelParagraph = new Paragraph("BẢNG NHÂN VIÊN\n\n", font);
                document.add(labelParagraph);
                Paragraph tableParagraph = new Paragraph();
                tableParagraph.add(getTableData());
                document.add(tableParagraph);

                document.close();

                System.out.println("File đã được lưu tại: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private PdfPTable getTableData() {
        PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
        for (int i = 0; i < table.getColumnCount(); i++) {
            pdfTable.addCell(table.getColumnName(i));
        }
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object cellValue = table.getValueAt(i, j);
                // Kiểm tra xem cellValue có phải là null hay không trước khi gọi toString()
                if (cellValue != null) {
                    pdfTable.addCell(cellValue.toString());
                } else {
                    pdfTable.addCell(""); // hoặc thêm một giá trị mặc định nếu cellValue là null
                }
            }
        }
        return pdfTable;
    }
}
