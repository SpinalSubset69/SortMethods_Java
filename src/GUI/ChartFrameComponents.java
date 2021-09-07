package GUI;

import models.Report;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logic.HandleFiles;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartFrameComponents {
    private JPanel chart_panel;

    private HandleFiles files = new HandleFiles();

    public JPanel ChartPanel(){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        List<Report> reports = files.GetReportsList();

        for(Report report: reports){
            //Agregar valores a la grafica
            data.setValue(report.getTotalTime(), report.getSortMethod(), String.valueOf(report.getRegisters()).toString());
        }
        //Creamos la grafica
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Métodos de Ordenamiento",
                "Número de registros",
                "Tiempo de Ejecución",
                data,
                PlotOrientation.HORIZONTAL, true, true, false
        );
        ChartPanel bar_panel = new ChartPanel(chart);
        bar_panel.setMouseWheelEnabled(true);
        bar_panel.setPreferredSize(new Dimension(1500, 500));
        chart_panel = new JPanel();
        chart_panel.setBounds(0,0, 1500, 500);
        chart_panel.add(bar_panel);
        return chart_panel;
    }
}
