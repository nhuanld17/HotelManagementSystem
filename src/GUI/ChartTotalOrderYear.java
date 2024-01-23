package GUI;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartTotalOrderYear extends JFrame{
	public ChartTotalOrderYear(int year,double t1, double t2, double t3, double t4, double t5, double t6, double t7, double t8, double t9, double t10, double t11, double t12) {
		CategoryDataset dataset = createDataset(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12);
		
		JFreeChart chart = ChartFactory.createBarChart(
				"BIỂU ĐỒ LƯỢT ĐẶT PHÒNG NĂM "+year,
				"Tháng",
				"Lượt",
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
		this.setVisible(true);
	}

	private CategoryDataset createDataset(double t1, double t2, double t3, double t4, double t5, double t6, double t7,
			double t8, double t9, double t10, double t11, double t12) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(t1, "Lượt", "1");
		dataset.addValue(t2, "Lượt", "2");
		dataset.addValue(t3, "Lượt", "3");
		dataset.addValue(t4, "Lượt", "4");
		dataset.addValue(t5, "Lượt", "5");
		dataset.addValue(t6, "Lượt", "6");
		dataset.addValue(t7, "Lượt", "7");
		dataset.addValue(t8, "Lượt", "8");
		dataset.addValue(t9, "Lượt", "9");
		dataset.addValue(t10, "Lượt", "10");
		dataset.addValue(t11, "Lượt", "11");
		dataset.addValue(t12, "Lượt", "12");
		
		return dataset;
	}
}
