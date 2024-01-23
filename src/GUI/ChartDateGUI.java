package GUI;

import javax.swing.*;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.HoaDonBUS;
import DTO.Chart;

public class ChartDateGUI extends JFrame {
	private EmployeeGUI employeeGUI;
	
    public ChartDateGUI(EmployeeGUI employeeGUI) {
    	this.employeeGUI = employeeGUI;
    	Date date1 = Date.valueOf(employeeGUI.textField_RevenueDateChart.getText());
    	Date date2 = Date.valueOf(employeeGUI.textField_RevenueDateChart1.getText());
        CategoryDataset dataset = createDataset(date1,date2);

        JFreeChart chart = ChartFactory.createLineChart(
                "Doanh thu theo từ "+date1+" đến "+date2,// Tiêu đề biểu đồ
                "Ngày", // Đơn vị trục x
                "Doanh thu (VNĐ)", // Đơn vị trục y
                dataset, // dataset
                PlotOrientation.VERTICAL, // Hướng của biểu đồ: thẳng đứng
                true, // Hiển thị đường giải thích
                true, // Tạo tooltip khi di chuột qua
                false // KO hiển thị url khi di chuột qua
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot(); // Lấy đối tượng plot từ biểu đồ và ép kiểu CategotyPlot
        CategoryAxis xAxis = plot.getDomainAxis();// Tạo biến xAxis đại diện cho trục x
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // đặt góc của nhãn x hướng lên 45 độ

        // Hiển thị biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);// tạo 1 chart panel từ biểu đồ
        chartPanel.setPreferredSize(new Dimension(1200, 670));


        // Set layout to FlowLayout
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add components to the content pane
        add(chartPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Hàm tạo dataset
    private CategoryDataset createDataset(Date date1, Date date2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();// Tạo đối tượng DefaultCategoryDataset để chứa dữ liệu của biểu đồ
        ArrayList<Chart> charts = new HoaDonBUS().getChartDate(date1,date2);

        for (Chart chart : charts) {
            java.sql.Date ngayTra = chart.getNgayTra();

            // Kiểm tra xem ngayTra có phải là null không
            if (ngayTra != null) {
                // Chuyển đổi ngayTra thành chuỗi
                String ngayTraString = ngayTra.toString();

                // Thêm giá trị vào dataset
                dataset.addValue(chart.getGia(), "Doanh Thu", ngayTraString);
            } else {
                // Xử lý trường hợp ngayTra là null
                System.out.println("Null");
            }
        }

        return dataset;
    }
}
