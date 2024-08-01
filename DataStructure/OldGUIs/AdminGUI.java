import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdminGUI implements ActionListener {
    JFrame adminFrame;
    JPanel panel1;

    /*
        Buttons for GUI and features of all admin
     */
    JButton addLibrary;
    JButton removeLibrary;
    JButton addManager;
    JButton removeManager;
    JButton searchLibrary;
    JButton searchManager;
    /*
        Icon for title
     */
    URL book = new URL("https://i.pinimg.com/originals/2c/fc/93/2cfc93d7665f5d7728782700e50596e3.png");
    final ArrayList<Image> icons = new ArrayList<Image>();

    /**
     * Constructor of Admin and GUI
     * @throws Exception
     */
    public AdminGUI() throws Exception {
        super();
        adminFrame = new JFrame("Admin System");
        adminFrame.setTitle("Admin System");
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,1));
        adminFrame.setLocationRelativeTo( null );
        icons.add(ImageIO.read(book));
        adminFrame.setIconImages(icons);
        addLibrary = new JButton("Add Library");
        addLibrary.setPreferredSize(new Dimension(150, 35));
        removeLibrary = new JButton(("RemoveLibrary"));
        removeLibrary.setPreferredSize(new Dimension(150,35));
        addManager = new JButton(("Add Manager"));
        addManager.setPreferredSize(new Dimension(150,35));
        removeManager = new JButton(("Remove Manager"));
        removeManager.setPreferredSize(new Dimension(150,35));
        searchLibrary = new JButton(("Search Library"));
        searchLibrary.setPreferredSize(new Dimension(150,35));
        searchManager = new JButton(("Search Manager"));
        searchManager.setPreferredSize(new Dimension(150,35));
        panel1.add(addLibrary);
        panel1.add(removeLibrary);
        panel1.add(addManager);
        panel1.add(removeManager);
        panel1.add(searchLibrary);
        panel1.add(searchManager);

        adminFrame.add(panel1,BorderLayout.CENTER);

        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(350,400);
        adminFrame.setLocationByPlatform(true);
        adminFrame.setResizable(false);
        adminFrame.setVisible(true);
    }

    /**
     * Action of GUI
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(e.getSource() == ){
            Admin admin = new Admin();
        }*/
    }
}
