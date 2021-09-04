import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MainFrameComponents {
    //Componentes
    private JButton btn_generate;
    private JTextField txt_generate;
    private BufferedImage uat_image;
    private JLabel h1_program;
    private JTable data_grid;
    private JTextArea text_area;
    private JComboBox sort_options;
    private JButton sort;
    private DefaultTableModel model;
    private JButton btn_graphic;

    //variables globales
    private List<Report> reports = new ArrayList<Report>();
    private int[] array;
    private RandomNumberGenerator generate_array = new RandomNumberGenerator();
    private Util util = new Util();
    private SortMethods sort_array = new SortMethods();
    private HandleFiles file = new HandleFiles();
    private String[] HEADERS = {"Ejecución", "Metodo", "Tiempo de ejecucion"};

    public JButton Btn_Graphic(){
        btn_graphic = new JButton("Generar Gráfica");
        btn_graphic.setBounds(910, 230, 200, 25);
        btn_graphic.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        btn_graphic.addActionListener(e -> {
          if(file.GetReportsList().size() == 0){
              util.ShowMessage("No hay registros para graficar", "Error", 0);
          }else{
              ChartFrame chart = new ChartFrame();
              chart.setVisible(true);
          }
        });
        return btn_graphic;
    }

    public JButton Btn_Generate(){
        btn_generate = new JButton("Registros a Generar");
        btn_generate.setBounds(25, 230, 200, 25);
        btn_generate.setFocusable(false);
        btn_generate.setFont(new Font("Consolas", Font.PLAIN, 12));

        //Button Logic
        btn_generate.addActionListener(e -> {
            try{
                int number_of_registers = Integer.parseInt(txt_generate.getText().replace(" ", ""));
                if(number_of_registers == 0){
                    util.ShowMessage("Ingrese in valor mayor a 0", "Sort Methods" ,1);
                }
                array = generate_array.GenerateArrayWihtRandomNumbers(number_of_registers); //Rellenamos el arreglo con números aleatorios

                if(array.length > 0){
                    util.ShowMessage(number_of_registers + " Registros generados con éxito!", "Sort Methods", 1);
                    PaintNumbers(array);
                    if(!text_area.getText().isEmpty()){
                        sort.setEnabled(true);
                    }
                }
            }catch(NumberFormatException exception){
                util.ShowMessage("No se ingreso un caracter válido", "Error", 0);
            }
            txt_generate.setText("");
        });
        return  btn_generate;
    }

    public JTextField Txt_Generate(){
        txt_generate = new JTextField();
        txt_generate.setBounds(230 , 230, 150, 25);
        txt_generate.setFont(new Font("Consolas", Font.PLAIN, 12));
        return txt_generate;
    }

    public JLabel H1_Program(){
        ImageIcon icon = new ImageIcon("Images/sort.png");;
        h1_program = new JLabel("Métodos de Ordenamiento");
        h1_program.setFont(new Font("Consolas", Font.BOLD, 28));
        h1_program.setBounds(450, 100, 450, 50 );
        h1_program.setHorizontalTextPosition(JLabel.LEFT);
        h1_program.setIconTextGap(25);
        h1_program.setIcon(icon);
        return h1_program;
    }

    public JLabel UatImage(){
        try{
            uat_image = ImageIO.read(new File("Images/UAT.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        JLabel uat_pic = new JLabel(new ImageIcon(uat_image));
        uat_pic.setBounds(25,0,400, 230);
        return uat_pic;
    }

    public JTable DataGrid(){
        Object[][] data =  data = new Object[1][3];
        data[0][0]= "";
        data[0][1]= "";
        data[0][2]= "";
        model = new DefaultTableModel(data, HEADERS);
        data_grid = new JTable(model);
        data_grid.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 14));
        data_grid.setFocusable(false);
        data_grid.setFont(new Font("Consolas", Font.PLAIN, 14));
        data_grid.setEnabled(false);

        //Fill data grid with info
        HandleFiles f1 = new HandleFiles(this.model);
        f1.ShowDataOnGrid();
        return data_grid;
    }

    public JTextArea Text_Area(){
        text_area = new JTextArea();
        text_area.setEnabled(true);
        text_area.setEditable(false);
        text_area.setFont(new Font("Consolas", Font.BOLD, 12));
        return text_area;
    }

    public JComboBox SortOptions(){
        String[] options = {"Método Burbuja", "Método Shell", "Método Inserción", "Método Selección", "Método Merge"};
        sort_options = new JComboBox(options);
        sort_options.setBounds(25, 435, 180, 30);
        sort_options.setFocusable(false);
        sort_options.setSelectedIndex(0);
        return sort_options;
    }

    public JButton Sort(){
        sort = new JButton("Ordenar");
        sort.setBounds(210, 435, 180, 30);

        if(text_area.getText().isEmpty()){
            sort.setEnabled(false);
        }
        sort.addActionListener(e -> {
            switch (sort_options.getSelectedItem().toString()){
                case "Método Burbuja":
                    SortArray("Burble");
                    break;
                case "Método Shell":
                    SortArray("Shell");
                    break;
                case "Método Inserción":
                    SortArray("Insertion");
                    break;
                case "Método Selección":
                    SortArray("Selection");
                    break;
                case "Merge":
                    System.out.println("Merge");
                    break;
                default:
                    util.ShowMessage("Opción no Válilda", "Error", 0);
            }
        });
        return sort;
    }

    public void PaintNumbers(int[] array){
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

    public void SortArray(String sort_method){
        double time_start, time_end, total_time;
        time_start = System.currentTimeMillis();
        array = sort_array.SortMethod(array, sort_method);
        time_end = System.currentTimeMillis();
        total_time = (time_end - time_start) / 1000;

        PaintNumbers(array);
        CreateFile(total_time);
        sort.setEnabled(false);

        //Display data on table
        HandleFiles f1 = new HandleFiles(this.model);
        f1.ShowDataOnGrid();
    }

    public void CreateFile(Double total_time){
        Report report = new Report();
        report.setTotalTime(total_time);
        report.setRegisters(array.length);
        report.setSortMethod(sort_options.getSelectedItem().toString());

        file.CreateReportFile(report);
        util.ShowMessage("Arreglo Ordenado, se ha generado un archivo con el reporte", "Sort Methods", 1);
    }

}
