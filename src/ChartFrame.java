import javax.swing.*;

public class ChartFrame extends JFrame {

    private ImageIcon icon = new ImageIcon("Images/umbrella.png");
    ChartFrame(){
        ChartFrameComponents components = new ChartFrameComponents();

        this.getContentPane().add(components.ChartPanel());

        this.setTitle("Gráfica");
        this.setSize(1500, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setIconImage(icon.getImage());
    }
}
