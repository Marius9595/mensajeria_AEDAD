/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Mario
 * @author Jonathan
 */
public class Graficos {
    
    private static Controller_pedidos DB = new Controller_pedidos();
    

    /* ---------- grafica de lineas -----------   */
    
    /**
     * gr�fica de lineas
     * @param numeroLineas cantidad de lineas que quieres qeu se dibuje
     * @param orientacion la orientacion de la gr�fica
     * @return ChartPanel con la gr�fica
     */
    public static ChartPanel grafica_Rayas(int numeroLineas, String orientacion) throws SQLException{
        XYDataset dataset = createDataset_XYSeriesCollection();
        JFreeChart chart = createChart_createXYLineChart(dataset, numeroLineas, orientacion);

        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
    }
    
    /**
     * datos de la gr�fica de lineas
     * @return XYDataset con lso datos de la gr�fica
     */
    private static XYDataset createDataset_XYSeriesCollection() throws SQLException {
        
        /* ----------- CARGAR DATOS AQUI ------------  */
        //AQUI ES UN ArrayList<Double, double> 
        
        // es pedir un SUM() de los pedidos de este a�o y del pasado e iterar por mes
        
        LocalDateTime fecha_actual = LocalDateTime.now();
        
        // estos son los a�os a buscar
        String year_Actual = String.valueOf(fecha_actual.getYear());
        String year_Last = String.valueOf(fecha_actual.getYear() - 1);
        
        // las fechas para los minimos y maximos de la consulta
        String fecha_Actual_1Enero = year_Actual + "-01-01 00:01";
        String fecha_Actual_31Diciembre = year_Actual + "-12-31 23:59";        
        String fecha_Last_1Enero = year_Last + "-01-01 00:01";
        String fecha_Last_31Diciembre = year_Last + "-12-31 23:59";
        
        //SELECT sum(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE mensajeria.pedido.fecha_entrega BETWEEN fecha_Actual_1Enero AND fecha_Actual_31Diciembre;
        //SELECT sum(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE mensajeria.pedido.fecha_entrega BETWEEN '2021-01-01 00:00:01' AND '2021-12-31 23:59:59';
        
        // esto son lso contenedores
        double[] datoActual;
        double[] datoLast;
        
        double pedidos_last = DB.get_pedidos_periodo(fecha_Last_1Enero,fecha_Last_31Diciembre);
        double pedido_actual = DB.get_pedidos_periodo(fecha_Actual_1Enero,fecha_Actual_31Diciembre);
        
        //  ------- AQUI VA LA CONSULTA -----------
        
        // ejemplo
        datoActual = new double[] {Double.parseDouble(year_Actual), pedidos_last};
        datoLast = new double[] {Double.parseDouble(year_Last), pedido_actual};
        
        
        XYSeries series1 = new XYSeries(year_Actual);
        series1.add(2019, 0);
        series1.add(datoActual[0], datoActual[1]);
        
        XYSeries series2 = new XYSeries(year_Last);
        series2.add(2019, 0);
        series2.add(datoLast[0], datoLast[1]);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1); // raya 1
        dataset.addSeries(series2); // raya 2

        return dataset;
    }
    
    /**
     * dibuja la gr�fica de lineas
     * @param dataset datos de la gr�fica
     * @param numeroLineas cantidad de lineas que quieres qeu dibuje
     * @param orientacion orientacion de la gr�fica
     * @return chart con la gr�fica de l�neas
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
                "Paquetes repartidos por a�o",
                "A�o",
                "Paquetes",
                dataset,
                orienta,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < numeroLineas; i++) {
            if(i == 0)
                renderer.setSeriesPaint(i, Color.GREEN);
            else
                renderer.setSeriesPaint(i, Color.MAGENTA);
            
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Paquetes repartidos por a�o",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
    
    
    /* ---------- grafica de barras -----------   */
    
    /**
     * gr�fica de barras
     * @param orientacion orientacion de la gr�fica
     * @return chartPanel con la gr�fica
     */
    public static ChartPanel grafica_Barras(String orientacion) throws SQLException{
        CategoryDataset dataset = createDataset_DefaultCategoryDataset();
        JFreeChart chart = createChart_createBarChart(dataset, orientacion);
        
        cambiarColor(chart, "barras");
        
        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
    }

    /**
     * datos de la gr�fica de barras
     * @return datos de la gr�fica
     */
    private static CategoryDataset createDataset_DefaultCategoryDataset() throws SQLException {

        // son 3 consultas:
        // pedidos con fecha entrega, pedidos sin fecha entrega y pedidos sin repartidor
        // el resultado deberia ser 3 valores
        
        Integer[] valores; 
        
        //  ------- AQUI VA LA CONSULTA -----------
        
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NOT NULL;
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NOT NULL;
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NULL;
        
        // ejemplo
        valores = DB.get_estadistica_pedidos();
         
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(valores[0], "Pedidos", "Entregados");
        dataset.setValue(valores[1], "Pedidos", "Transito");
        dataset.setValue(valores[2], "Pedidos", "Sin entregar");

        return dataset;
    }

    /**
     * dibuja la gr�fica de barras
     * @param dataset datos de la gr�fica
     * @param orientacion orientacion de la gr�fica
     * @return barChart con la gr�fica de barras
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
                "Recuento de pedidos",
                "",
                "Pedidos",
                dataset,
                orienta,
                false, true, false);

        return barChart;
    }
    
    /* ---------- grafica de quesitos -----------   */
    
    /**
     * grafica de queso
     * @return ChartPanel con la gr�fica de queso
     */
    public static ChartPanel grafica_Queso() throws SQLException{
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        
        cambiarColor(chart, "queso");
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }

    /**
     * datos de la gr�fica de queso
     * @return DefaultPieDataset con los datos
     */
    private static DefaultPieDataset createDataset() throws SQLException {

        // REPETIMOS la misma que antes para simplificar
        
        Integer[] valores; 
        
        //  ------- AQUI VA LA CONSULTA -----------
        
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NOT NULL;
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NOT NULL;
//        SELECT SUM(mensajeria.pedido.id_articulo) FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NULL;
        
        // ejemplo
        valores = DB.get_estadistica_pedidos();
        
        int total = 0;
        
        for (int i = 0; i < valores.length; i++) {
            total = total + valores[i];
        }
        
        
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Entregados", Math.round(valores[0]*100/total));
        dataset.setValue("Transito", Math.round(valores[1]*100/total));
        dataset.setValue("Pendientes", Math.round(valores[2]*100/total));

        return dataset;
    }

    /**
     * dibuja graficas queso
     * @param dataset datos gr�fica
     * @return pieChart con la grafica
     */
    private static JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Total de pedidos",
                dataset,
                false, true, false);

        return pieChart;
    }
    
    /**
     * cambiar lso colores de las graficas
     * @param chart
     * @param graf 
     */
    private static void cambiarColor(JFreeChart chart, String graf){
        if("queso".equals(graf)){ // grafica queso
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionPaint("Entregados", Color.ORANGE);
            plot.setSectionPaint("Transito", Color.PINK);
            plot.setSectionPaint("Pendientes", Color.GREEN);
        } else if("barras".equals(graf)){ // grafica barras
            CategoryPlot cplot = (CategoryPlot)chart.getPlot();
            cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color

            //set  bar chart color
            ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());

            BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
            r.setSeriesPaint(0, Color.CYAN);
        } else {
            // no se hace nada -> aqui no deber�a entrar nunca
        }

    }
}
