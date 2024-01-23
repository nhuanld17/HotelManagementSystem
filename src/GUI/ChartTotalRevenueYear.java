package GUI;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartTotalRevenueYear extends JFrame{
	public ChartTotalRevenueYear(int year,double t1, double t2, double t3, double t4, double t5, double t6, double t7, double t8, double t9, double t10, double t11, double t12) {
		CategoryDataset dataset = createDataSet(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12);
		
		JFreeChart chart = ChartFactory.createBarChart(
				"BIỂU ĐỒ DOANH THU NĂM "+year+"(Triệu đồng)",
				"Tháng",
				"Doanh Thu",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private CategoryDataset createDataSet(double t1, double t2, double t3, double t4, double t5, double t6, double t7, double t8, double t9, double t10, double t11, double t12) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(t1, "Doanh Thu", "1");
		dataset.addValue(t2, "Doanh Thu", "2");
		dataset.addValue(t3, "Doanh Thu", "3");
		dataset.addValue(t4, "Doanh Thu", "4");
		dataset.addValue(t5, "Doanh Thu", "5");
		dataset.addValue(t6, "Doanh Thu", "6");
		dataset.addValue(t7, "Doanh Thu", "7");
		dataset.addValue(t8, "Doanh Thu", "8");
		dataset.addValue(t9, "Doanh Thu", "9");
		dataset.addValue(t10, "Doanh Thu", "10");
		dataset.addValue(t11, "Doanh Thu", "11");
		dataset.addValue(t12, "Doanh Thu", "12");
		
		return dataset;
		
	}
}
