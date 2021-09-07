package GUI;

import logic.HandleFiles;
import models.Report;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrameComponentsStyles {
    private HandleFiles files = new HandleFiles();

    DefaultTableModel dataGridModel(){
        Object[][] data;
        final String[] HEADERS = {"Ejecución", "Metodo", "Tiempo de ejecucion"};
        List<Report> reports = files.GetReportsList();

        if(reports.isEmpty()){
            data =  new Object[1][3];
            data[0][0]= "";
            data[0][1]= "";
            data[0][2]= "";
        }else{
            data = new Object[reports.size()][3];
            int i = 0;
            for(Report report:reports){
                data[i][0] = report.getRegisters();
                data[i][1] = report.getSortMethod();
                data[i][2] = report.getTotalTime();
                if(i < reports.size()){
                    i++;
                }
            }

        }

        DefaultTableModel model = new DefaultTableModel(data, HEADERS);

        return model;
    }

    JTable dataGridStyles(DefaultTableModel model){
        JTable data_grid = new JTable(model);
        data_grid.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 14));
        data_grid.setFocusable(false);
        data_grid.setFont(new Font("Consolas", Font.PLAIN, 14));
        data_grid.setEnabled(false);
        return data_grid;
    }
}
