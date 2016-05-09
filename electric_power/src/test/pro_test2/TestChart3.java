package pro_test2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import java.awt.*;

/**
 * 绘制饼状图
 *
 * @author near on 2016/3/22.
 */
public class TestChart3 {

    @Test
    public void test() {
        //构件数据集合
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("北京", 12);
        dataset.setValue("上海", 8);
        dataset.setValue("深圳", 4);

        //构件核心对象
        JFreeChart chart = ChartFactory.createPieChart3D(
                "用户统计报表（所属单位）",    //图形的主标题
                dataset,
                true, //是否显示子标题
                true, //是否在图形上显示数值的提示
                true  // 是否生成URL地址
        );

        //解决主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        //解决子标题的乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        //获取图表区域对象
        PiePlot3D piePlot3D = (PiePlot3D) chart.getPlot();
        piePlot3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
        //在图形上生成数据，数据的格式：北京 12（60%）
        String formartLabel = "{0} {1} ({2})";
        piePlot3D.setLabelGenerator(new StandardPieSectionLabelGenerator(formartLabel));

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
