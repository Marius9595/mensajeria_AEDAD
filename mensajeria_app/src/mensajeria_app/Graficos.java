/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Jonathan
 */
public class Graficos {
    

    /* ---------- grafica de lineas -----------   */
    
    /**
     * gráfica de lineas
     * @param numeroLineas cantidad de lineas que quieres qeu se dibuje
     * @param orientacion la orientacion de la gráfica
     * @return ChartPanel con la gráfica
     */
    public static ChartPanel grafica_Rayas(int numeroLineas, String orientacion){
        XYDataset dataset = createDataset_XYSeriesCollection();
        JFreeChart chart = createChart_createXYLineChart(dataset, numeroLineas, orientacion);

        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
    }
    
    /**
     * datos de la gráfica de lineas
     * @return XYDataset con lso datos de la gráfica
     */
    private static XYDataset createDataset_XYSeriesCollection() {
        
        // aqui va la consulta qeu devuelve los datos y los metemos en iteracion
        XYSeries series1 = new XYSeries("2014");
        series1.add(18, 530);
        series1.add(20, 580);
        series1.add(25, 740);
        series1.add(30, 901);
        series1.add(40, 1300);
        series1.add(50, 2219);

        XYSeries series2 = new XYSeries("2016");
        series2.add(18, 567);
        series2.add(20, 612);
        series2.add(25, 800);
        series2.add(30, 980);
        series2.add(40, 1210);
        series2.add(50, 2350);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1); // raya 1
        dataset.addSeries(series2); // raya 2

        return dataset;
    }
    
    /**
     * dibuja la gráfica de lineas
     * @param dataset datos de la gráfica
     * @param numeroLineas cantidad de lineas que quieres qeu dibuje
     * @param orientacion orientacion de la gráfica
     * @return chart con la gráfica de líneas
     */
    private static JFreeChart createChart_createXYLineChart(XYDataset dataset, int numeroLineas, String orientacion) {
        PlotOrientation orienta;
        if(orientacion.equals("horizontal"))
            orienta = PlotOrientation.HORIZONTAL;
        else{
            orienta = PlotOrientation.VERTICAL;
        }

        // personalizar
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Average salary per age",
                "Age",
                "Salary (€)",
                dataset,
                orienta,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < numeroLineas; i++) {
            renderer.setSeriesPaint(i, Color.RED);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(1, Color.BLUE);
//        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Average Salary per Age",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
    
    
    /* ---------- grafica de barras -----------   */
    
    /**
     * gráfica de barras
     * @param orientacion orientacion de la gráfica
     * @return chartPanel con la gráfica
     */
    public static ChartPanel grafica_Barras(String orientacion){
        CategoryDataset dataset = createDataset_DefaultCategoryDataset();
        JFreeChart chart = createChart_createBarChart(dataset, orientacion);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
    }

    /**
     * datos de la gráfica de barras
     * @return datos de la gráfica
     */
    private static CategoryDataset createDataset_DefaultCategoryDataset() {

        // consulta e iteración
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(46, "Gold medals", "USA");
        dataset.setValue(38, "Gold medals", "China");
        dataset.setValue(29, "Gold medals", "UK");
        dataset.setValue(22, "Gold medals", "Russia");
        dataset.setValue(13, "Gold medals", "South Korea");
        dataset.setValue(11, "Gold medals", "Germany");

        return dataset;
    }

    /**
     * dibuja la gráfica de barras
     * @param dataset datos de la gráfica
     * @param orientacion orientacion de la gráfica
     * @return barChart con la gráfica de barras
     */
    private static JFreeChart createChart_createBarChart(CategoryDataset dataset, String orientacion) {
        // personalizar
        PlotOrientation orienta;
        if(orientacion.equals("horizontal"))
            orienta = PlotOrientation.HORIZONTAL;
        else{
            orienta = PlotOrientation.VERTICAL;
        }
        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Olympic gold medals in London",
                "",
                "Gold medals",
                dataset,
                orienta,
                false, true, false);

        return barChart;
    }
    
    /* ---------- grafica de quesitos -----------   */
    
    /**
     * grafica de queso
     * @return ChartPanel con la gráfica de queso
     */
    public static ChartPanel grafica_Queso(){
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }

    /**
     * datos de la gráfica de queso
     * @return DefaultPieDataset con los datos
     */
    private static DefaultPieDataset createDataset() {

        // aqui la consulta e iteración
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Apache", 52);
        dataset.setValue("Nginx", 31);
        dataset.setValue("IIS", 12);
        dataset.setValue("LiteSpeed", 2);
        dataset.setValue("Google server", 1);
        dataset.setValue("Others", 2);

        return dataset;
    }

    /**
     * dibuja graficas queso
     * @param dataset datos gráfica
     * @return pieChart con la grafica
     */
    private static JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Web servers market share",
                dataset,
                false, true, false);

        return pieChart;
    }
}
