package electric.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 图形工具类
 *
 * @author near on 2016/3/22.
 */
public class ChartUtils {

    public static void createBarChart(java.util.List<Object[]> data) {

        //构件数据集合
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (data != null && data.size() > 0) {
            for (Object[] objects : data) {
                Integer count = Integer.parseInt(String.valueOf(objects[0]));
                dataset.addValue(count, String.valueOf(objects[1]), String.valueOf(objects[2]));
            }
        }

        //构件核心对象
        JFreeChart chart = ChartFactory.createBarChart3D(
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
        CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();
        //获取Y轴对象
        NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
        //解决X轴上的乱码
        categoryAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决X轴外的乱码
        categoryAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决Y轴上的乱码
        numberAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        //解决Y轴外的乱码
        numberAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));

        //改变Y轴的刻度，默认值从1计算
        numberAxis3D.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(2);
        numberAxis3D.setTickUnit(unit);

        //获取绘图区域对象
        BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
        //改变图形宽度
        barRenderer3D.setMaximumBarWidth(0.08);

        //让数值显示到页面上
        barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRenderer3D.setBaseItemLabelsVisible(true);
        barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));

        //将生成图形生成图片放在到项目的路径下
        String path = ServletActionContext.getServletContext().getRealPath("/upload/chart");
        String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".png";
        File file = new File(path + "\\" + fileName);
        ServletActionContext.getRequest().setAttribute("fileName", fileName);
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 600, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
