import javax.management.openmbean.OpenMBeanConstructorInfo;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class HandleFiles {
    private  int cont;
    private String text;
    private DefaultTableModel model;

    HandleFiles(){
        this.cont = 0;
    }

    HandleFiles(DefaultTableModel model){
        this.model = model;
    }

    public String[] ReadReports(String file_name){
        try{
            BufferedReader bf = new BufferedReader(new FileReader("Reports/"+file_name));
            String temp = "";
            String bfRead;

            while((bfRead = bf.readLine()) != null){
                temp = temp + bfRead;
            }

            bf.close();
            text = temp;
        }catch (Exception e){

        }
        String[] lines = text.split(" ");
        return  lines;
    }

    public void CreateReportFile(Report report){
        String path = "Reports";
        File folder = new File(path);

        if(!folder.isDirectory()){
            folder.mkdir();
        }

        String file_path = "Reports/reporte " + report.getSortMethod() + " " + cont + ".txt" ;
        DecimalFormat dm = new DecimalFormat("#.0000");
        File file = new File(file_path);

        if(!file.exists()){ //Si no existeel archivo lo creamos y rellenamos para el primer registro
            try {
                PrintWriter out = new PrintWriter(file);
                out.print("--------------------Reporte de Programa--------------------");
                out.print("\nNúmero de datos ordenados: " + report.getRegisters() + " " );
                out.print("\nMétodo de ordenamiento utilizado: " + report.getSortMethod() + " ");
                out.print("\nTiempo de ejecución: " + dm.format(report.getTotalTime()) + " Segundo(s) ");
                out.print("\nFecha de creación: " + report.getCreateAt());
                out.close();
                this.cont++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{ //En caso de que ya exista el archivo
           cont ++;
           CreateReportFile(report);

           //En caso de querer tener todo en un solo archivo descomentar esto
            //try {
            //    FileWriter out = new FileWriter(file_path, true);
            //    out.append("\n--------------------Reporte de Programa-------------------- \n");
            //    out.append("\nNúmero de datos ordenados: " + nElements + " ");
            //    out.append("\nMétodo de ordenamiento utilizado: " + method_sort + " ");
            //    out.append("\nTiempo de ejecución: " + dm.format(total_time) + " Segundo(s)"+ " ");
            //    out.close();
            //} catch (IOException e) {
            //    e.printStackTrace();
            // }
        }
    }

    public List<Report> GetReportsList(){
        List<Report> reports = new ArrayList<Report>();
        File folder_path = new File("Reports");

        //Verificamos si el directorio existe, caso contrario, lo creamos
        if(!folder_path.isDirectory()){
            folder_path.mkdir();
        }
        for(File file:folder_path.listFiles()){
            Report report = new Report();
            String[]array = ReadReports(file.getName());

            report.setRegisters(Integer.parseInt(array[6]));
            report.setSortMethod(array[11]+ " " + array[12]);
            report.setTotalTime(Double.parseDouble(array[16]));
            report.setCreateAt(array[20]+ " " + array[21]+ " " + array[22] + " " + array[23] + " " + array[24]);

            String[] temp = report.getCreateAt().split(" ");
            long milisec = Long.parseLong(temp[4].replace(":", ""));
            System.out.println("milisec = " + milisec);
            report.setTime(milisec);

            reports.add(report);
        }

        //Ordenamos los reportes dependiendo de la ultima fecha de modificación
        Collections.sort(reports, (x , y) -> (int)(x.getTime() - y.getTime()));
        return reports;
    }

    public void ShowDataOnGrid(){
        List<Report> reports = GetReportsList();

        for(int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
            i -=1;
        }
        for(Report sorted_report:reports){
            this.model.insertRow(0 , new Object[]{sorted_report.getRegisters(), sorted_report.getSortMethod(), sorted_report.getTotalTime()});
        }
    }
}
