/**
 * Entrance of the library
 * @author Mustafa Gurler
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class Login extends JFrame{
    JLabel label;
    JPanel panel1;
    JPanel panel2;

    /*
        Users of Library
     */
    JButton admin;
    JButton manager;
    JButton librarian;
    JButton janitor;
    JButton reader;
    JButton register;
    /*
        Icon for title
     */
    URL book = new URL("https://i.pinimg.com/originals/2c/fc/93/2cfc93d7665f5d7728782700e50596e3.png");
    final ArrayList<Image> icons = new ArrayList<Image>();


    public Login() throws Exception {
        super("Library Automation System");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(0,1));
        setLocationRelativeTo( null );
        label = new JLabel("Welcome");
        icons.add(ImageIO.read(book));
        setIconImages(icons);
        admin = new JButton("Admin");
        admin.setPreferredSize(new Dimension(150, 35));
        manager = new JButton(("Manager"));
        manager.setPreferredSize(new Dimension(150,35));
        librarian = new JButton(("Librarian"));
        librarian.setPreferredSize(new Dimension(150,35));
        janitor = new JButton(("Janitor"));
        janitor.setPreferredSize(new Dimension(150,35));
        reader = new JButton(("Reader"));
        reader.setPreferredSize(new Dimension(150,35));
        register = new JButton((("Registration Form")));
        register.setPreferredSize((new Dimension(150,35)));
        panel1.add(label);
        panel2.add(admin);
        panel2.add(manager);
        panel2.add(librarian);
        panel2.add(janitor);
        panel2.add(reader);
        panel2.add(register);
        Handler handler = new Handler();
        admin.addActionListener(handler);
        register.addActionListener(handler);
        manager.addActionListener(handler);
        librarian.addActionListener(handler);
        janitor.addActionListener(handler);
        reader.addActionListener(handler);
        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.CENTER);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350,400);
        setLocationByPlatform(true);
        setVisible(true);

    }


    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == admin){
                try {
                    Admin adminb = new Admin();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else if(e.getSource() == manager) {
                try {
                    Manager managerb = new Manager();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }else if(e.getSource() == register){
                LoginSystem log = new LoginSystem();
            }else if(e.getSource() == librarian){
                try {
                    Librarian librarianb = new Librarian();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else if(e.getSource() == reader){
                try {
                    StandartReader readerb = new StandartReader();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
