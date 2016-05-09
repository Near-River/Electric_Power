package pro_test2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;

import java.awt.*;

/**
 * 绘制线状图
 *
 * @author near on 2016/3/22.
 */
public class TestChart2 {

    @Test
    public void test() {
        //构件数据集合
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(12, "所属单位", "北京");
        dataset.addValue(6, "所属单位", "上海");
        dataset.addValue(2, "所属单位", "深圳");

        //构件核心对象
        JFreeChart chart = ChartFactory.createLineChart(
                "用户统计报表（所属单位）",    //图形的主标题
                "所属单位名称",                //X轴外标签的名称
                "数量",                        //Y轴外标签的名称
                dataset,
                PlotOrientation.VERTICAL,
                true, //是否显示子标题
                true, //是否在图形上显示数值的提示
                true  // 是否生成URL地址
        );

        //解决主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        //解决子标题的乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));

        //获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        //获取X轴对象
        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        //获取Y轴对象
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        //解决X轴上的乱码
        categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决X轴外的乱码
        categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决Y轴上的乱码
        numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决Y轴外的乱码
        numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));

        //改变Y轴的刻度，默认值从1计算
        numberAxis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis.setTickUnit(unit);

        //获取绘图区域对象
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer)categoryPlot.getRenderer();
        //让数值显示到页面上
        lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));

        //设置矩形为线状图的转折点，参数一：表示起始线段，默认是0
        Shape shape = new Rectangle(10,10);
        lineAndShapeRenderer.setSeriesShape(0, shape);
        lineAndShapeRenderer.setSeriesShapesVisible(0, true);

        //显示图形
        ChartFrame chartFrame = new ChartFrame("", chart);
        chartFrame.setVisible(true);
        chartFrame.pack();
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException ignored) {
        }
    }

}
