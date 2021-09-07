package GUI;

import logic.HandleFiles;
import logic.SortMethods;
import models.Report;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Util {
    private SortMethods sort_array = new SortMethods();
    private HandleFiles file = new HandleFiles();

    public void ShowMessage(String message, String title, int info){
         JOptionPane.showMessageDialog(null, message, title, info);
    }

    public int[] sortArray(int[] array, String sort_method, DefaultTableModel model){
        System.out.println(sort_method);
        double time_start, time_end, total_time;
        time_start = System.currentTimeMillis();
        array = sort_array.SortMethod(array, sort_method);
        time_end = System.currentTimeMillis();
        total_time = (time_end - time_start) / 1000;

        CreateFile(total_time, array, sort_method);

        //Display data on table
        HandleFiles f1 = new HandleFiles(model);
        f1.ShowDataOnGrid();

        return array;
    }

    public void CreateFile(Double total_time, int[] array, String sort_methd){
        Report report = new Report();
        report.setTotalTime(total_time);
        report.setRegisters(array.length);
        report.setSortMethod(sort_methd);
        file.CreateReportFile(report);
    }

    public void PaintNumbers(int[] array, JTextArea text_area){
        if(!text_area.getText().isEmpty()){
            text_area.setText("");
        }
        text_area.append("Números Generados: ");
        for(int i = 0; i < array.length; i++){
            if(i % 8 == 0){
                text_area.append("\n");
            }
            text_area.append("[" + array[i]+ "], ");
        }
    }
}
