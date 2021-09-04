import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ImageIcon image = new ImageIcon("Images/umbrella.png");
    private MainFrameComponents components = new MainFrameComponents();
    MainFrame(){
        //JScrollPane for Data_Grid
        JScrollPane data_panel = new JScrollPane(components.DataGrid());
        data_panel.setBounds(400, 230, 500, 200);
        data_panel.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0), new Color(45, 43, 43)));

        //JScrollPane for Text Area
        JScrollPane numbers_panel = new JScrollPane(components.Text_Area());
        numbers_panel.setBounds(25, 270, 360, 160);
        numbers_panel.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0), new Color(45, 43, 43)));

        //Add components here
        this.add(components.Btn_Generate());
        this.add(components.Txt_Generate());
        this.add(components.UatImage());
        this.add(components.H1_Program());
        this.getContentPane().add(data_panel);
        this.getContentPane().add(numbers_panel);
        this.add(components.SortOptions());
        this.add(components.Sort());
        this.add(components.Btn_Graphic());

        //Frame Options
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Metodos de Ordenamiento");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1150, 550);
        this.setIconImage(image.getImage());
        this.setVisible(true);
    }
}
