import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JanitorGUI implements ActionListener {
    JFrame janitorFrame;
    JPanel panel1;
    /**
     * Tasks of Janitor
     */
    JButton viewTask;
    JButton updateTask;

    URL book = new URL("https://i.pinimg.com/originals/2c/fc/93/2cfc93d7665f5d7728782700e50596e3.png");
    final ArrayList<Image> icons = new ArrayList<Image>();

    /**
     * Constructor of Janitor and GUI
     * @throws Exception
     */
    public JanitorGUI() throws Exception {
        janitorFrame = new JFrame("Janitor System");
        janitorFrame.setTitle("Janitor System");
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,1));
        janitorFrame.setLocationRelativeTo( null );
        icons.add(ImageIO.read(book));
        janitorFrame.setIconImages(icons);
        viewTask = new JButton("View List of Task");
        viewTask.setPreferredSize(new Dimension(150, 35));
        updateTask = new JButton(("Update Task"));
        updateTask.setPreferredSize(new Dimension(150,35));
        panel1.add(viewTask);
        panel1.add(updateTask);

        janitorFrame.add(panel1,BorderLayout.CENTER);

        janitorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janitorFrame.setSize(350,400);
        janitorFrame.setLocationByPlatform(true);
        janitorFrame.setResizable(false);
        janitorFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(e.getSource() == ){
            Admin admin = new Admin();
        }*/
    }
}
